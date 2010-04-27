using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;
using System.Collections;

namespace xzMesosphere.Source
{
    public class HyTradeSample
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);

            SvrDatumProductItem.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrcOrderCustomerItem), "ISvrcOrderCustomerItem");   //生产打样

            SvrDatumProductSort.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrcSampleSend), "ISvrcSampleSend");                 //寄样管理

            SvrDatumProduct.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrcManuFollow), "ISvrcManuFollow");                 //生产跟踪

            SvrDatumProduct.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrcManuTest), "ISvrcManuTest");                     //跟踪测试               
        }
    }

    public sealed class SampleParam
    {
        /// <summary>
        /// 取得状态相对应的运算符号
        /// </summary>
        /// <param name="Isplus">状态位 tree & false</param>
        /// <returns></returns> 返回运算符号 + & -
        public static string IsAdd(bool Isplus)
        {
            return (Isplus) ? "+" : "-";
        }

        /// <summary>
        /// 取得状态相对应的整型数值
        /// </summary>
        /// <param name="Isplus"></param>
        /// <returns></returns> 真 0 假 1
        public static string IsOne(bool Isplus)
        {
            return (Isplus) ? "0" : "1";
        }

        //更新单据是否被使用
        public const string _UptIsUse = "Update {0} set IsUse = dbo.SetByteToBoolean({0}.IsUse,{4},{3}) from {0},{1} where {0}.BargainCode = {1}.SrcBillNo and {1}.BillNo = {2}";
        
        //更新寄样单据从表记录状态
        public const string _UptSendUseState = "Update tbcSampleSendItem set UseState = dbo.SetByteToBoolean(UseState,0x0001,{0}) where BillNo = '{1}' and RowNo = {2}";

        //更新客户订单三从表记录第一状态位
        public const string _UptOrderDetailOne = "Update tbcOrderCustomerItemDetail set UseState = dbo.SetByteToBoolean(UseState,0x0001,{0}) where BillNo = '{1}' and RowNo = {2} and DetailNo = {3}"; 

        //更新客户订单三从表记录第二状态位
        public const string _UptOrderDetailTwo = "Update tbcOrderCustomerItemDetail set UseState = dbo.SetByteToBoolean(UseState,0x0002,{0}) where BillNo = '{1}' and RowNo = {2} and DetailNo = {3}";

        /// <summary>
        /// 更新引用表的IsUse字段来标志单据已被关联
        /// </summary>
        /// <param name="Conn"></param>
        /// <param name="Tran"></param>
        /// <param name="UptSql">更新的SQL语句</param>
        /// <param name="MasterTable">所引用的表</param>
        /// <param name="DetailTable">引用表</param>
        /// <param name="MasterKey">主键</param>
        /// <param name="isPlus">标志当前操作是审核还是反审</param>
        /// <param name="valueKey">关键值</param>
        /// <returns></returns>
        public static DataTable UptIsUse(SqlConnection Conn, SqlTransaction Tran, string UptSql, string MasterTable, string DetailTable, string MasterKey, string isPlus, int valueKey)
        {
            using (SqlDataAdapter SqlData = new SqlDataAdapter())
            {
                SqlCommand Comm = new SqlCommand(string.Format(UptSql, MasterTable, DetailTable, string.Format("'{0}'", MasterKey), isPlus, valueKey), Conn);
                Comm.Transaction = Tran;
                SqlData.SelectCommand = Comm;
                DataTable UptIsUse = new DataTable();
                SqlData.Fill(UptIsUse);
                return UptIsUse;
            }
        }
    }


    /// <summary>
    /// 生产打样
    /// </summary>
    class SvrcOrderCustomerItem : SvrSystemDB, IServerSkedUserExec, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            //bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);

            //bool FSampleSend = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermOne);                //寄样管理
            //if (FSampleSend)
            //{
            //    HYTradeParam.Warn(HYTradeParam.BillNoCheck);
            //}

            ////2007-09-25 更新反写源单IsUse字段
            //DataTable UptIsUse = SampleParam.UptIsUse(Connect, Tran, SampleParam._UptIsUse,
            //                    "tbcOrderCustomer", "tbcSampleMake", (string)Row["BillNo"], SampleParam.IsOne(b), 1);
        }

        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                SqlCommand up_cmd = new SqlCommand();
                up_cmd.Connection = Connect;
                switch (ID)
                {
                    case 0: //打样完成处理
                        SqlTransaction Trans = Connect.BeginTransaction();
                        try
                        {
                            up_cmd.Transaction = Trans;
                            foreach (Hashtable haRecord in ht.Values)
                            {
                                up_cmd.CommandText = string.Format(@"Update tbcOrderCustomerItemDetail set UseState = {0}  where 
                                   BillNo = '{1}' and RowNo = {2} and DetailNo = {3}", haRecord[0], haRecord[1], haRecord[2], haRecord[3]);
                                up_cmd.ExecuteNonQuery();
                            }
                            Trans.Commit();
                        }
                        catch (Exception e)
                        {
                            Trans.Rollback();
                            xzException.WarnBox(e.Message);
                        }
                        break;
                    case 1:  //打样意见处理
                        up_cmd.CommandText = "update tbcOrderCustomerItemDetail set Accessory = @Accessory where BillNo=@BillNo and RowNo = @RowNo and DetailNo = @DetailNo";
                        up_cmd.Parameters.Add("@Accessory", SqlDbType.Image);
                        up_cmd.Parameters["@Accessory"].Value = ht[0] as byte[];
                        up_cmd.Parameters.Add("@BillNo", SqlDbType.VarChar, 30);
                        up_cmd.Parameters["@BillNo"].Value = ht[1].ToString();
                        up_cmd.Parameters.Add("@RowNo", SqlDbType.Int);
                        up_cmd.Parameters["@RowNo"].Value = ht[2].ToString();
                        up_cmd.Parameters.Add("@DetailNo", SqlDbType.Int);
                        up_cmd.Parameters["@DetailNo"].Value = ht[3].ToString();
                        up_cmd.ExecuteNonQuery();
                        break;
                }
            }
            finally
            {
                Connect.Close(); 
            }
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {

        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "") Connect.ConnectionString = RemotingParam.ConnectionString;
            string sql = "select isnull(Max(NewNo),0)+1 from tbcOrderCustomerItem where SourceBillNo = '{0}' and RowNo={1}";
            Hashtable htResult = new Hashtable();
            switch (ID)
            {
                case 0: //获取单据号
                    Connect.Open();
                    try
                    {
                        SqlCommand cmd = new SqlCommand(string.Format(sql, ht[0], ht[1]), Connect);
                        object obj = cmd.ExecuteScalar();
                        if (obj != null && obj!=DBNull.Value)
                            htResult.Add(0, obj.ToString());
                    }
                    finally
                    {
                        Connect.Close();
                    }
                    break;
            }
            return htResult;
        }
    }

    /// <summary>
    /// 寄样管理
    /// </summary>
    class SvrcSampleSend : SvrSystemDB, IServerSkedUserExec
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
           //  bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);

            //bool FSampleSend = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermOne);                //寄样管理
            //if (FSampleSend)
            //{
            //    HYTradeParam.Warn(HYTradeParam.BillNoCheck);
            //}

            ////2007-09-25 更新反写源单IsUse字段
            //DataTable UptIsUse = SampleParam.UptIsUse(Connect, Tran, SampleParam._UptIsUse,
            //                    "tbcSampleMake", "tbcSampleSend", (string)Row["BillNo"], SampleParam.IsOne(b), 8);
        }

        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                SqlCommand up_cmd = new SqlCommand();
                up_cmd.Connection = Connect;
                switch (ID)
                {
                    case 0: //寄样完成处理
                        SqlTransaction Trans = Connect.BeginTransaction();
                        try
                        {
                            up_cmd.Transaction = Trans;
                            foreach (Hashtable haRecord in ht.Values)
                            {
                                up_cmd.CommandText = string.Format(@"Update tbcSampleSendItem set UseState = {0}  where 
                                   BillNo = '{1}' and RowNo = {2}", haRecord[0], haRecord[1], haRecord[2]);
                                up_cmd.ExecuteNonQuery();
                            }
                            Trans.Commit();
                        }
                        catch(Exception e)
                        {
                            Trans.Rollback();
                            xzException.WarnBox(e.Message);
                        }
                        break;
                    case 1:  //寄样意见处理
                        up_cmd.CommandText = "update tbcSampleSendItem set Accessory = @Accessory where BillNo=@BillNo and RowNo = @RowNo";
                        up_cmd.Parameters.Add("@Accessory", SqlDbType.Image);
                        up_cmd.Parameters["@Accessory"].Value = ht[0] as byte[];
                        up_cmd.Parameters.Add("@BillNo", SqlDbType.VarChar, 30);
                        up_cmd.Parameters["@BillNo"].Value = ht[1].ToString();
                        up_cmd.Parameters.Add("@RowNo", SqlDbType.Int);
                        up_cmd.Parameters["@RowNo"].Value = ht[2].ToString();
                        up_cmd.ExecuteNonQuery();
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {

        }
    }

    /// <summary>
    /// 生产跟踪
    /// </summary>
    class SvrcManuFollow : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            //bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);

            ////bool FSampleSend = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermOne);          
            ////if (FSampleSend)
            ////{
            ////    HYTradeParam.Warn(HYTradeParam.BillNoCheck);
            ////}

            ////2007-09-25 更新反写源单IsUse字段
            //DataTable UptIsUse = SampleParam.UptIsUse(Connect, Tran, SampleParam._UptIsUse,
            //                    "tbcOrderCustomer", "tbcManuFollow", (string)Row["BillNo"], SampleParam.IsOne(b), 2);
        }
    }

    /// <summary>
    /// 生产测试
    /// </summary>
    class SvrcManuTest : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            //bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);

            ////bool FSampleSend = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermOne);          
            ////if (FSampleSend)
            ////{
            ////    HYTradeParam.Warn(HYTradeParam.BillNoCheck);
            ////}

            ////2007-09-25 更新反写源单IsUse字段
            //DataTable UptIsUse = SampleParam.UptIsUse(Connect, Tran, SampleParam._UptIsUse,
            //                    "tbcOrderCustomer", "tbcManuTest", (string)Row["BillNo"], SampleParam.IsOne(b), 4);
        }
    }

}
