using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;
using System.Collections;

namespace xzMesosphere.Source
{
    public class HYLoginArchives
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);

            SvrDatumProductBrand.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductBrand), "ISvrDatumProductBrand");
            SvrDatumProductModel.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductModel), "ISvrDatumProductModel");
            SvrDatumProductColor.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductColor), "ISvrDatumProductColor");
            SvrDatumProductConfig.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductConfig), "ISvrDatumProductConfig");
            SvrDatumOpposite.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumOpposite), "ISvrDatumOpposite");
            SvrDatumProductInfo.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumProductInfo), "ISvrDatumProductInfo");
            SvrDatumFitting.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumFitting), "ISvrDatumFitting");
            SvrDatumLargess.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumLargess), "ISvrDatumLargess");
            SvrDatumPhoneCard.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumPhoneCard), "ISvrDatumPhoneCard");
            SvrSystemGroup.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemGroup), "ISvrSystemGroup");

            //售价管理
            SvrDatumPriceManage.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumPriceManage), "ISvrDatumPriceManage");

            //会员管理
            SvrDatumMemberManage.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrDatumMemberManage), "ISvrDatumMemberManage");
        }
    }

    /// <summary>
    /// 通用的参数
    /// </summary>
    public sealed class HYArchivesParam
    {
        public const string _SqlOpposite = "select count(*) from vDatumOpposite where ClientCode={0} and TheCode={1} and ProductSortCode={2} and BillNo<>{3}";

        public const string BillNoCheck = "当前单据已经处理，不能取消确认！";

        public const string BillHasClose = "当前单据已经关闭！";

        public const string BillNoInvoice = "当前单据已经开票，不能取消确认！";

        public const string CurrProductIsUse = "当前商品已在使用中，不能删除！";

        public const string StocksNoEnough = "执行失败！库存不足！";

        public const string UptStorageItemErr = "更新库存串号数据错误！";

        public const string UptStorageErr = "更新库存数量错误！";

        public const string UptStorageListErr = "更新库存跟踪记录错误！";

        public const string HasCheck = "当前单据已审核，不能取消确认！";

        public const string HasCancellation = "当前单据已作废，不能取消确认！";

        public const string HasVerify = "当前单据已经钩稽，不能取消确认！";

        public const string HasNoConfirm = "成本维护时间段：{0}--{1} 内存在未确认的单据，请先确认单据！";

        public const string BillHasNoConfirm = "[{0}]存在未确认的单据，请先确认单据！";

        public const string _UptTheCode = " update tbStockOrderItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}'" + "\n"
                                        + " update tbStockInvoiceItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbSaleOrderItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbSaleOutItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbSaleOutItemDetail set ProductCode='{0}' where ProductCode='{2}' " + "\n"
                                        + " update tbSaleInvoiceItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorStockInItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorStockInItemDetail set ProductCode='{0}' where ProductCode='{2}'" + "\n"
                                        + " update tbStorRedeployItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorRedeployItemDetail set ProductCode='{0}' where ProductCode='{2}'" + "\n"
                                        + " update tbStorOtherInItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorOtherInItemDetail set ProductCode='{0}' where ProductCode='{2}'" + "\n"
                                        + " update tbStorOtherOutItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorOtherOutItemDetail set ProductCode='{0}' where ProductCode='{2}'" + "\n"
                                        + " update tbStorCheckItem set ProductCode='{0}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorCheckItemDetail set ProductCode='{0}' where ProductCode='{2}' " + "\n"
                                        + " update tbStorStorage set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorStorageItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorStorageList set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbSelfOrderItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbSelfSaleOutItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbCourseStorEntrust set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbCourseStorStorage set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbCourseStorMaintain set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbStorMaintainPostSelfItem set ProductCode='{0}',ProductSortCode='{1}' where ProductCode='{2}' and ProductSortCode='{1}' " + "\n"
                                        + " update tbDatumOppositeItem set ProductCode='{0}',ProductSort='{1}' where ProductCode='{2}' and ProductSort='{1}'" + "\n";

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
    /// 品牌档案提示信息处理
    /// </summary>
    public class SvrDatumProductBrand : SvrSystemDB, IServerSkedUserExec
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
                string SqlStr = HYArchivesParam.GetExistsSql("tbDatumProductModel", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYArchivesParam.GetExistsSql("tbDatumProductInfo", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYArchivesParam.GetExistsSql("tbDatumFitting", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYArchivesParam.GetExistsSql("tbDatumLargess", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYArchivesParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductModel", "BrandCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前品牌已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 型号档案提示信息处理
    /// </summary>
    public class SvrDatumProductModel : SvrSystemDB, IServerSkedUserExec
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
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ModelCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前型号已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 颜色档案提示信息处理
    /// </summary>
    public class SvrDatumProductColor : SvrSystemDB, IServerSkedUserExec
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
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ColorCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前颜色已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 配置档案提示信息处理
    /// </summary>
    public class SvrDatumProductConfig : SvrSystemDB, IServerSkedUserExec
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
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ConfigCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前配置已在使用中，不能删除！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 物料编码提示信息处理
    /// </summary>
    public class SvrDatumOpposite : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                if (ht[0] != null)
                {
                    DataTable MasterTable = ht[0] as DataTable;
                    if (ht[1] != null)
                    {
                        DataTable DetailTable = ht[1] as DataTable;
                        for (int i = 0; i < DetailTable.Rows.Count; i++)
                        {
                            string SqlStr = string.Format(HYArchivesParam._SqlOpposite, string.Format("'{0}'", MasterTable.Rows[0]["ClientCode"]),
                                string.Format("'{0}'", DetailTable.Rows[i]["ProductCode"]), string.Format("'{0}'", DetailTable.Rows[i]["ProductSort"]),
                                string.Format("'{0}'", MasterTable.Rows[0]["BillNo"]));
                            SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                            object ACount = cmd.ExecuteScalar();
                            if ((Int32)ACount > 0)
                            {
                                HYArchivesParam.Warn("第" + string.Format("[{0}]", DetailTable.Rows[i]["RowNo"]) 
                                    + "行商品编号" + string.Format("[{0}]", DetailTable.Rows[i]["ProductCode"]) + "所对应的客户物料对应编码已定义！");
                            }
                        }
                    }
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
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 删除商品编号判断
            Connect.Open();
            try
            {
                for (int i = 0; i < dt.Rows.Count; i++)
                {
                    string SqlStr = string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOrderItem", "ProductCode", 
                            string.Format("'{0}'", dt.Rows[0]["ProductCode"]), "ProductSortCode", string.Format("{0}", dt.Rows[0]["ProductSort"]))
                        + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOutItem", "ProductCode", 
                            string.Format("'{0}'", dt.Rows[0]["ProductCode"]), "ProductSortCode", string.Format("{0}", dt.Rows[0]["ProductSort"]))
                        + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleInvoiceItem", "ProductCode", 
                            string.Format("'{0}'", dt.Rows[0]["ProductCode"]), "ProductSortCode", string.Format("{0}", dt.Rows[0]["ProductSort"]))
                        + HYArchivesParam._SqlExit;
                    SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                    object ACount = cmd.ExecuteScalar();
                    if ((Int32)ACount > 0)
                    {
                        HYArchivesParam.Warn(HYArchivesParam.CurrProductIsUse);
                    }
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion

        }
    }

    /// <summary>
    /// 手机档案数据处理
    /// </summary>
    public class SvrDatumProductInfo : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 修改商品编号
            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "TheCode", string.Format("'{0}'", (string)(ht[0])));
                SqlCommand Sqlcmd = new SqlCommand(SqlStr, Connect);
                object ACount = Sqlcmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前商品编号已存在，请重新输入！");
                }

                string UpdateStr = string.Format(HYArchivesParam._UptTheCode + " update tbDatumProductInfo set TheCode='{0}' where TheCode='{2}' " + "\n",
                    (string)(ht[0]), (string)(ht[1]), (string)(ht[2]));

                SqlCommand cmd = new SqlCommand(UpdateStr, Connect);
                //HYArchivesParam.Warn(Convert.ToString(cmd));
                //cmd.Transaction = Tran;
                if (cmd.ExecuteNonQuery() <= 0) 
                {
                    HYArchivesParam.Warn("执行失败！");
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion
        }

        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {

        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 删除商品编号判断
            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlExistsProduct, "tbStockOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStockInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorCheckItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorage", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageList", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "001")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbDatumOppositeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSort", "001")
                    + HYArchivesParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ConfigCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn(HYArchivesParam.CurrProductIsUse);
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion
        }
    }

    /// <summary>
    /// 配件档案数据处理
    /// </summary>
    public class SvrDatumFitting : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumFitting", "TheCode", string.Format("'{0}'", (string)(ht[0])));
                SqlCommand Sqlcmd = new SqlCommand(SqlStr, Connect);
                object ACount = Sqlcmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前商品编号已存在，请重新输入！");
                }

                string UpdateStr = string.Format(HYArchivesParam._UptTheCode + " update tbDatumFitting set TheCode='{0}' where TheCode='{2}' " + "\n",
                    (string)(ht[0]), (string)(ht[1]), (string)(ht[2]));

                SqlCommand cmd = new SqlCommand(UpdateStr, Connect);
                //HYArchivesParam.Warn(Convert.ToString(cmd));
                //cmd.Transaction = Tran;
                if (cmd.ExecuteNonQuery() <= 0)
                {
                    HYArchivesParam.Warn("执行失败！");
                }
                else
                {
                    HYArchivesParam.Warn("执行成功！");
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
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 删除商品编号判断
            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlExistsProduct, "tbStockOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStockInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorCheckItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorage", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageList", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "002")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbDatumOppositeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSort", "002")
                    + HYArchivesParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ConfigCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn(HYArchivesParam.CurrProductIsUse);
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion
        }
    }

    /// <summary>
    /// 电话卡档案数据处理
    /// </summary>
    public class SvrDatumPhoneCard : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumPhoneCard", "TheCode", string.Format("'{0}'", (string)(ht[0])));
                SqlCommand Sqlcmd = new SqlCommand(SqlStr, Connect);
                object ACount = Sqlcmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前商品编号已存在，请重新输入！");
                }

                string UpdateStr = string.Format(HYArchivesParam._UptTheCode + " update tbDatumPhoneCard set TheCode='{0}' where TheCode='{2}' " + "\n",
                    (string)(ht[0]), (string)(ht[1]), (string)(ht[2]));

                SqlCommand cmd = new SqlCommand(UpdateStr, Connect);
                if (cmd.ExecuteNonQuery() <= 0)
                {
                    HYArchivesParam.Warn("执行失败！");
                }
                else
                {
                    HYArchivesParam.Warn("执行成功！");
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
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 删除商品编号判断
            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlExistsProduct, "tbStockOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockReturnNoticeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStockInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployLeagueItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorBorrowItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRestoreItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorCheckItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorage", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageList", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfLeagueSaleItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfPriceManageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "003")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbDatumOppositeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSort", "003")
                    + HYArchivesParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ConfigCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn(HYArchivesParam.CurrProductIsUse);
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion
        }
    }

    /// <summary>
    /// 赠品档案数据处理
    /// </summary>
    public class SvrDatumLargess : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumLargess", "TheCode", string.Format("'{0}'", (string)(ht[0])));
                SqlCommand Sqlcmd = new SqlCommand(SqlStr, Connect);
                object ACount = Sqlcmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前商品编号已存在，请重新输入！");
                }

                string UpdateStr = string.Format(HYArchivesParam._UptTheCode + " update tbDatumLargess set TheCode='{0}' where TheCode='{2}' " + "\n",
                    (string)(ht[0]), (string)(ht[1]), (string)(ht[2]));

                SqlCommand cmd = new SqlCommand(UpdateStr, Connect);
                if (cmd.ExecuteNonQuery() <= 0)
                {
                    HYArchivesParam.Warn("执行失败！");
                }
                else
                {
                    HYArchivesParam.Warn("执行成功！");
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
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 删除商品编号判断
            Connect.Open();
            try
            {
                string SqlStr = string.Format(HYArchivesParam._SqlExistsProduct, "tbStockOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStockReturnNoticeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSaleInvoiceItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStockInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRedeployLeagueItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherInItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorOtherOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorBorrowItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorRestoreItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorCheckItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorage", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbStorStorageList", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfOrderItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfSaleOutItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfLeagueSaleItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbSelfPriceManageItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSortCode", "004")
                    + string.Format(HYArchivesParam._SqlExistsProduct, "tbDatumOppositeItem", "ProductCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]), "ProductSort", "004")
                    + HYArchivesParam._SqlExit;

                //string SqlStr = string.Format(HYArchivesParam._SqlCount, "tbDatumProductInfo", "ConfigCode", string.Format("'{0}'", dt.Rows[0]["TheCode"]));
                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn(HYArchivesParam.CurrProductIsUse);
                }
            }
            finally
            {
                Connect.Close();
            }
            #endregion
        }
    }

    /// <summary>
    /// 用户组提示信息处理
    /// </summary>
    public class SvrSystemGroup : SvrSystemDB, IServerSkedUserExec
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
                string SqlStr = HYArchivesParam.GetExistsSql("tsSystemGroupUser", "GroupNo", string.Format("'{0}'", dt.Rows[0]["TheCode"]))
                                + HYArchivesParam._SqlExit;

                SqlCommand cmd = new SqlCommand(SqlStr, Connect);
                object ACount = cmd.ExecuteScalar();
                if ((Int32)ACount > 0)
                {
                    HYArchivesParam.Warn("当前组正在使用中，请先删除当前组的用户！");
                }
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 售价管理
    /// </summary>
    public class SvrDatumPriceManage : SvrSystemDB, IServerSkedUserExecEx
    {
        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            DataTable Table;

            Hashtable htResult = new Hashtable();
            Connect.Open();
            try
            {
                switch (ID)
                {
                    case 0:
                        #region 获取当前的统一价格清单
                        using (SqlDataAdapter SqlDa = new SqlDataAdapter())
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlDa.SelectCommand = FCommand;

                            //求出当前的售价表中的所有满足条件的数据
                            FCommand.CommandText = "Select getdate()";
                            DateTime TheTime = Convert.ToDateTime(FCommand.ExecuteScalar());
                            FCommand.CommandText = string.Format(@"select top 1 a.PriceSort,a.ProductSortCode,b.ProductSortName,a.ProductCode
                              ,b.TheName ProductName,b.ConfigName,b.UnitName, a.SerialNo
                              ,a.BeginTime,a.SalePrice,a.SelfSalePrice,a.SelfSalePrice1,a.Remark
                            from tbDatumPriceManage a
                                 left outer join vDatumProduct b on a.ProductCode=b.TheCode and a.ProductSortCode=b.ProductSort
                            where a.PriceSort = '001' and a.BeginTime<= '{0}'", TheTime);
                            Table = new DataTable();
                            SqlDa.Fill(Table);
                            ArrayList PriceManageArg = new ArrayList();
                            foreach (DataRow CurrRow in Table.Rows)
                                PriceManageArg.Add(string.Format("{0}\r{1}", CurrRow["ProductSortCode"], CurrRow["ProductCode"]));

                            //求出所有未满足条件的产品
                            DataTable tmpTable = new DataTable();
                            FCommand.CommandText = "select ProductSort, ProductSortName , TheCode, TheName  from vDatumProduct";
                            SqlDa.Fill(tmpTable);

                            //合并传送到客户端
                            foreach (DataRow CurrRow in tmpTable.Rows)
                            {
                                string key = string.Format("{0}\r{1}", CurrRow["ProductSort"], CurrRow["TheCode"]);
                                if (!PriceManageArg.Contains(key))
                                {
                                    DataRow newCurrRow = Table.NewRow();
                                    newCurrRow["PriceSort"] = "001";
                                    newCurrRow["ProductSortCode"] = CurrRow["ProductSort"];
                                    newCurrRow["ProductSortName"] = CurrRow["ProductSortName"];
                                    newCurrRow["ProductCode"] = CurrRow["TheCode"];
                                    newCurrRow["ProductName"] = CurrRow["TheName"];
                                    Table.Rows.Add(newCurrRow);
                                }
                            }

                            htResult.Add(0, Table);
                            return htResult;
                        }
                        #endregion                        
                    case 1:
                        #region 更新物料售价
                        Table = ht[2] as DataTable;
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlTransaction SqlTrans = Connect.BeginTransaction();
                            try
                            {
                                FCommand.Transaction = SqlTrans;
                                foreach (DataRow CurrRow in Table.Rows)
                                {
                                    FCommand.CommandText = string.Format(@"update tbDatumPriceManage set
                                            BeginTime = {6} , SalePrice = {7} , SelfSalePrice = {8}, SelfSalePrice1 = {9},
                                            Remark = '{10}',  Editer = '{11}', EditeTime = {12}
                                          where PriceSort = '{0}' and ProductSortCode = '{1}' and ProductCode = '{2}' 
                                             and TheSort = '{3}'  and TheCode = '{4}' and SerialNo = {5}"
                                       , CurrRow["PriceSort"], CurrRow["ProductSortCode"], CurrRow["ProductCode"]
                                       , CurrRow["TheSort"], CurrRow["TheCode"], CurrRow["SerialNo"],
                                        xzSqlAnalysis.GetNull(CurrRow["BeginTime"]),
                                        xzSqlAnalysis.getValue(CurrRow["SalePrice"]),
                                        xzSqlAnalysis.getValue(CurrRow["SelfSalePrice"]), xzSqlAnalysis.getValue(CurrRow["SelfSalePrice1"])
                                       , CurrRow["Remark"], ht[0],  xzSqlAnalysis.GetNull(ht[1]));                                   
                                    FCommand.ExecuteNonQuery();
                                }
                                SqlTrans.Commit();
                            }
                            catch (Exception err)
                            {
                                SqlTrans.Rollback();
                                xzException.WarnBox(err.Message);
                            }
                        }
                        #endregion
                        break;
                    case 2:
                        #region 清除物料售价
                        Table = ht[1] as DataTable;
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlTransaction SqlTrans = Connect.BeginTransaction();
                            try
                            {
                                FCommand.Transaction = SqlTrans;
                                foreach (DataRow CurrRow in Table.Rows)
                                {
                                    FCommand.CommandText = string.Format(@"delete tbDatumPriceManage
                                          where PriceSort = '{0}' and ProductSortCode = '{1}' and ProductCode = '{2}' 
                                             and TheSort = '{3}'  and TheCode = '{4}' and SerialNo = {5}"
                                       , CurrRow["PriceSort"], CurrRow["ProductSortCode"], CurrRow["ProductCode"]
                                       , CurrRow["TheSort"], CurrRow["TheCode"], CurrRow["SerialNo"]);
                                    FCommand.ExecuteNonQuery();
                                }
                                SqlTrans.Commit();
                            }
                            catch (Exception err)
                            {
                                SqlTrans.Rollback();
                                xzException.WarnBox(err.Message);
                            }
                        }
                        #endregion
                        break;
                    case 3:
                        #region 维护物料售价[排程]
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            FCommand.CommandText = string.Format(@"Select max(SerialNo) + 1  from tbDatumPriceManage 
                                  where PriceSort = '{0}' and ProductSortCode = '{1}' and ProductCode = '{2}' 
                                             and TheSort = '{3}'  and TheCode = '{4}'", ht[0], ht[1], ht[2], ht[5], ht[6]);
                            decimal SerialNo = xzSqlAnalysis.getValue(FCommand.ExecuteScalar());

                            FCommand.CommandText = string.Format(@"insert into tbDatumPriceManage 
                           (PriceSort, ProductSortCode, ProductCode, TheSort, TheCode, BeginTime , 
                                  SalePrice, SelfSalePrice, SelfSalePrice1, Remark, Creater, CreateTime, SerialNo)  values 
                           ('{0}', '{1}' , '{2}' , '{3}', '{4}', {5} ,{6} , {7} , {8}, '{9}', '{10}', {11}, {12})"
                            ,  ht[0], ht[1], ht[2], ht[5], ht[6],  xzSqlAnalysis.GetNull(ht[7])  ,   
                               xzSqlAnalysis.getValue(ht[9]),xzSqlAnalysis.getValue(ht[10]), xzSqlAnalysis.getValue(ht[11]),
                               ht[12], ht[3], xzSqlAnalysis.GetNull(ht[4]), SerialNo);
                            FCommand.ExecuteNonQuery();
                        }
                        #endregion
                        break;
                    case 10:
                        #region 更新物料售价
                        Table = ht[2] as DataTable;
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlTransaction SqlTrans = Connect.BeginTransaction();
                            try
                            {
                                FCommand.Transaction = SqlTrans;
                                foreach (DataRow CurrRow in Table.Rows)
                                {
                                    FCommand.CommandText = string.Format(@"update tbDatumPriceManage set
                                            TheState = dbo.SetByteToBoolean(TheState,0x0001,1), Stater = '{6}', StateTime = {7}
                                          where PriceSort = '{0}' and ProductSortCode = '{1}' and ProductCode = '{2}' 
                                             and TheSort = '{3}'  and TheCode = '{4}' and SerialNo = '{5}'"
                                       , CurrRow["PriceSort"], CurrRow["ProductSortCode"], CurrRow["ProductCode"]
                                       , CurrRow["TheSort"], CurrRow["TheCode"], CurrRow["SerialNo"]
                                       , ht[0], xzSqlAnalysis.GetNull(ht[1]));
                                    FCommand.ExecuteNonQuery();
                                }
                                SqlTrans.Commit();
                            }
                            catch (Exception err)
                            {
                                SqlTrans.Rollback();
                                xzException.WarnBox(err.Message);
                            }
                        }
                        #endregion
                        break;
                    case 11:
                        #region 更新物料售价
                        Table = ht[2] as DataTable;
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlTransaction SqlTrans = Connect.BeginTransaction();
                            try
                            {
                                FCommand.Transaction = SqlTrans;
                                foreach (DataRow CurrRow in Table.Rows)
                                {
                                    FCommand.CommandText = string.Format(@"update tbDatumPriceManage set
                                            TheState = dbo.SetByteToBoolean(TheState,0x0001,0), Stater = null, StateTime = null
                                          where PriceSort = '{0}' and ProductSortCode = '{1}' and ProductCode = '{2}' 
                                             and TheSort = '{3}'  and TheCode = '{4}' and SerialNo = '{5}'"
                                       , CurrRow["PriceSort"], CurrRow["ProductSortCode"], CurrRow["ProductCode"]
                                       , CurrRow["TheSort"], CurrRow["TheCode"], CurrRow["SerialNo"]);
                                    FCommand.ExecuteNonQuery();
                                }
                                SqlTrans.Commit();
                            }
                            catch (Exception err)
                            {
                                SqlTrans.Rollback();
                                xzException.WarnBox(err.Message);
                            }
                        }
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return null;
        }
    }

    /// <summary>
    /// 积分方案
    /// </summary>
    public class SvrDatumMemberManage : SvrSystemDB, IServerSkedUserExecEx
    {
        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;            
            Hashtable htResult = new Hashtable();
            Connect.Open();
            try
            {
                switch (ID)
                {
                    case 0:
                        #region 保存积分参数
                        using (SqlCommand FCommand = new SqlCommand())
                        {
                            FCommand.Connection = Connect;
                            SqlTransaction SqlTrans = Connect.BeginTransaction();
                            try
                            {
                                FCommand.Transaction = SqlTrans;
                                FCommand.Parameters.Add("@Expenditure", SqlDbType.Image);
                                foreach (string key in ht.Keys)
                                {
                                    FCommand.CommandText = string.Format("update tbDatumMemberSort set Expenditure=@Expenditure where TheCode='{0}'"
                                        , key);
                                    FCommand.Parameters["@Expenditure"].Value = ht[key];
                                    FCommand.ExecuteNonQuery();
                                }
                                SqlTrans.Commit();
                            }
                            catch (Exception err)
                            {
                                SqlTrans.Rollback();
                                xzException.WarnBox(err.Message);
                            }
                        }

                        #endregion
                        break;
                    case 1:
                        #region 获取积分参数
                        //string s_Sql = string.Format("select Expenditure from tbDatumProductSort where TheCode='{0}'", TheCode);
                        //SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                        //object objStream = cmd.ExecuteScalar();
                        //if (objStream != null && objStream != DBNull.Value)
                        //{
                        //    htResult.Add(0, objStream);
                        //}
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }            
            return htResult;            
        }
    }
}
