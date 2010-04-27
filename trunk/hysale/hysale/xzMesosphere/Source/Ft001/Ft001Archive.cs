using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;
using System.Collections;

namespace xzMesosphere.Source
{
    public class HyTradeArchives
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);

            SvrDatumProductItem.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductItem), "ISvrDatumProductItem");

            SvrDatumProduct.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProduct), "ISvrDatumProduct");

            SvrDatumProductSort.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductSort), "ISvrDatumProductSort");
        }
    }

    /// <summary>
    /// 通用的参数
    /// </summary>
    public sealed class HYTradeParam
    {

        public const string BillNoCheck = "当前单据已经处理，不能取消确认！";

        public const string BillHasClose = "当前单据已经关闭！";

        public const string BillNoInvoice = "当前单据已经开票，不能取消确认！";

        public const string CurrProductIsUse = "当前商品已在使用中，不能删除！";


        //构造查询语句用于查询结果是否存在，如果存在返回大于0
        public const string _SqlCount = "select count(*) from {0} where {1}={2}";

        //构造查询语句用于查询结果是否存在，如果存在返回1，不存在为0
        public const string _SqlExists = "if exists(select 1 from {0} where {1}={2} having Count(*)>0 ) " + "\n"
                                      + "begin " + "\n"
                                      + "  goto Ex " + "\n"
                                      + "end " + "\n";

        public const string _SqlExistsProduct = "if exists(select 1 from {0} where {1}={2} and {3}='{4}' having Count(*)>0 ) " + "\n"
                                      + "begin " + "\n"
                                      + "  goto Ex " + "\n"
                                      + "end " + "\n";

        public const string _SqlExit = "select 0 " + "\n"
                                      + "return " + "\n"
                                      + "Ex: " + "\n"
                                      + "begin " + "\n"
                                      + "  select 1 " + "\n"
                                      + "  return " + "\n"
                                      + "end " + "\n";

        public static string GetExistsSql(String TableName, String FieldName, String FieldValue)
        {
            string SqlStr = "";
            SqlStr = string.Format(_SqlExists, TableName, FieldName, FieldValue);
            return SqlStr;
        }


        //抛出异常处理
        public static void Warn(string Msg)
        {
            xzException.WarnBox(Msg);
            //Exception e = new Exception(Msg);
            //e.Data.Add(xzException.Key, new xzException(xzExceptionMode.User));
            //throw e;
        }
    }

    /// <summary>
    /// 产品类型档案提示信息处理
    /// </summary>
    class SvrDatumProductSort : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = HYTradeParam.GetExistsSql("tbDatumProductItem", "BelongSort", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYTradeParam.GetExistsSql("tbDatumProduct", "BelongSort", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYTradeParam._SqlExit;

                //string SqlStr = string.Format(HYTradeParam._SqlCount, "tbDatumProductModel", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYTradeParam.Warn("当前产品已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 产品类型档案提示信息处理
    /// </summary>
    class SvrDatumProductItem : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = HYTradeParam.GetExistsSql("tbDatumProduct", "BelongSort", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYTradeParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductModel", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYTradeParam.Warn("当前品牌已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 产品档案提示信息处理   
    /// </summary>
    class SvrDatumProduct : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = HYTradeParam.GetExistsSql("tbcOrderCartonItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                //+ HYTradeParam.GetExistsSql("tbDatumProductInfo", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                //+ HYTradeParam.GetExistsSql("tbDatumFitting", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                //+ HYTradeParam.GetExistsSql("tbDatumLargess", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYTradeParam._SqlExit;

                //string SqlStr = string.Format(HYTradeParam._SqlCount, "tbDatumProductModel", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYTradeParam.Warn("当前品牌已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    
}
