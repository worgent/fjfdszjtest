using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;
using System.Collections;

namespace xzMesosphere.Source
{
    public class HyTradeOrder
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);

            SvrDatumProductItem.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrcOrderCustomer), "ISvrcOrderCustomer");

            //SvrDatumProduct.ConnectStr = sql;
            //FServerManage.Server.StartupTCPServer(typeof(SvrDatumProduct), "ISvrDatumProduct");

            //SvrDatumProductSort.ConnectStr = sql;
            //FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductSort), "ISvrDatumProductSort");
        }
    }

    /// <summary>
    /// 客户订单
    /// </summary>
    class SvrcOrderCustomer : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {
        }

        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);
            bool FSampleMake = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermOne);             //生产打样
            bool FManuFallow = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermTwo);             //生产跟踪
            bool FManuTest = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermThree);             //生产测试
            bool FManuCheck = BitTermMath.GetBitTrue((int)Row["IsUse"], BitTermMath.TermFour);             //生产测试

            //if (b)
            //{
            //    string update = "Update tbcOrderCustomerItem set Creater = '{0}' , CreateTime = '{1}', BargainCode = '{3}' where BillNo = '{2}' ";
            //    update = string.Format(update, Row["Creater"], Row["CreateTime"], Row["BillNo"],Row["BargainCode"]);
            //    SqlCommand updatecmd = new SqlCommand(update, Connect);
            //    updatecmd.Transaction = Tran;
            //    updatecmd.ExecuteNonQuery();
            //}

            if (FSampleMake || FManuFallow || FManuTest || FManuCheck)
            {
                HYTradeParam.Warn(HYTradeParam.BillNoCheck);
            }
        }       
    }
}
