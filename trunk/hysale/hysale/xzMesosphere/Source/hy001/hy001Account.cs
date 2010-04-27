using System;
using System.ComponentModel;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;
using System.Collections;

namespace xzMesosphere.Source
{
    public class HyLoginAccount
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);

            #region 账款
            SvrFinOrigin.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinOrigin), "ISvrFinOrigin");              //期初余额     

            SvrFinRecCash.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinRecCash), "ISvrFinRecCash");            //应收处理  

            SvrFinPayCash.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinPayCash), "ISvrFinPayCash");            //应付处理

            SvrFinChanger.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinChanger), "ISvrFinChanger");            //转户并户

            SvrFinLoseHandset.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinLoseHandset), "ISvrFinLoseHandset");     //员工丢机赔偿跟踪

            SvrFinPayCashLoseHandset.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrFinPayCashLoseHandset), "ISvrFinPayCashLoseHandset");//员工丢机赔偿付款
            #endregion

            #region 应付管理
            SvrStockChargeOther.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrStockChargeOther), "ISvrStockChargeOther");          //供应商其它费用

            SvrStockChargePromotion.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrStockChargePromotion), "ISvrStockChargePromotion");  //供应商市场费用

            SvrStockChargeSettlement.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrStockChargeSettlement), "ISvrStockChargeSettlement");//供应商费用结算

            SvrStockPayApply.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrStockPayApply), "ISvrStockPayApply");                //付款申请单

            #endregion

            #region 应收管理
            SvrSaleChargeOther.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSaleChargeOther), "ISvrSaleChargeOther");            //销售其它费用

            SvrSaleChargePromotion.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSaleChargePromotion), "ISvrSaleChargePromotion");    //供应商市场费用

            SvrSaleChargeSettlement.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSaleChargeSettlement), "ISvrSaleChargeSettlement"); //销售费用结算

            #endregion
        }
    }

    #region 账款

    /// <summary>
    /// 期初余额
    /// </summary>
    public class SvrFinOrigin : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select CopartnerType, CopartnerCode, FlowBit,
                              AccountMoney,UsBail,OtherBail, 
                              NoBalanceProfit,NoBalanceKeepPrice,NoBalanceCharge, 
                              HasBalanceProfit,HasBalanceKeepPrice,HasBalanceCharge
                         from tbFinOrigin where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbFinOrigin";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    bool isStorageIn = isCheck;

                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"],"AccountMoney"
                        , xzSqlAnalysis.getValue(MasterRow["AccountMoney"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "NoBalanceProfit"
                        , xzSqlAnalysis.getValue(MasterRow["NoBalanceProfit"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "NoBalanceKeepPrice"
                        , xzSqlAnalysis.getValue(MasterRow["NoBalanceKeepPrice"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "NoBalanceCharge"
                        , xzSqlAnalysis.getValue(MasterRow["NoBalanceCharge"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceProfit"
                        , xzSqlAnalysis.getValue(MasterRow["HasBalanceProfit"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceKeepPrice"
                        , xzSqlAnalysis.getValue(MasterRow["HasBalanceKeepPrice"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceCharge"
                        , xzSqlAnalysis.getValue(MasterRow["HasBalanceCharge"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "UsBail"
                        , xzSqlAnalysis.getValue(MasterRow["UsBail"]));
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "OtherBail"
                        , xzSqlAnalysis.getValue(MasterRow["OtherBail"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbFinOrigin", ht[0]);
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    /// <summary>
    /// 应付处理
    /// </summary>
    public class SvrFinPayCash : SvrSystemDB, IServerSkedUserExecEx
    {
        //更新付款申请单的处理
        private void UpdatePayApply(SqlCommand cmd, DataRow row, decimal ApplyMoney,bool isCheck)
        {
            int isUse = 1;
            if (!isCheck) isUse = -1;
            string StrSql = @"Update tbStockPayApply set ProMoney = ProMoney + {0}*{1} where BillNo = '{2}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();

            StrSql = @"Update tbStockPayApply set IsUse = IsUse + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
        }
        private bool GetPayApplyNoApplyMoney(SqlCommand cmd, DataRow DetailRow, bool isCheck)
        {
            if (DetailRow["SourceBillNo"].ToString() != "")
            {
                cmd.CommandText = string.Format(@"select UseState from tbStockPayApply where BillNo = '{0}' 
                    and dbo.GetByteToBoolean(theState,0x0001)=1"
                    , DetailRow["SourceBillNo"]);
                object value = cmd.ExecuteScalar();
                if (value == null)
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]不存在，可能原单未确认！",DetailRow["SourceBillNo"]));
                    return false;
                }

                if (BitTermMath.GetBitTrue((int)value, BitTermMath.TermSix))
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]己被锁定，当前操作不可执行！", DetailRow["SourceBillNo"]));
                    return false;
                }

                decimal TotalMoney = xzSqlAnalysis.getAbsValue(DetailRow["TotalMoney"]);
                if (!isCheck) TotalMoney = -TotalMoney;
                string sql = @"select ProMoney from tbStockPayApply where BillNo = '{0}'";
                cmd.CommandText = string.Format(sql, DetailRow["SourceBillNo"]);
                value = cmd.ExecuteScalar();
                if (value == null && (xzSqlAnalysis.getValue(value) + TotalMoney) < 0)
                {
                    xzException.WarnBox(string.Format("申请单单据号:{0}金额已经大于的未申请金额",DetailRow["SourceBillNo"]));
                    return false;
                }
            }
            return true;
        }

        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, SrcBillSort,UseState, TheDate, CopartnerType, CopartnerCode,  TotalMoney
                            from tbFinPayCash where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbFinPayCash";
                    DataRow MasterRow = MasterTable.Rows[0];

                    cmd.CommandText = string.Format(@"select BillNo,RowNo,SourceBillNo,SourceMoney,ApplyMoney,TotalMoney
                            from tbFinPayCashItem where BillNo = '{0}'", Row["BillNo"]);
                    DataTable DetailTable = new DataTable();
                    sqlda.Fill(DetailTable);
                    DetailTable.TableName = "tbFinPayCashItem";

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && !isCheck) || (isRadBill && isCheck);
                    #endregion

                    #region 判断
                    foreach (DataRow DetailRow in DetailTable.Rows)
                    {
                        decimal ApplyMoney = Convert.ToDecimal(DetailRow["TotalMoney"]);
                        #region 更新采购订单
                        if(GetPayApplyNoApplyMoney(cmd, DetailRow,isCheck))
                        UpdatePayApply(cmd, DetailRow, ApplyMoney, isCheck);
                        #endregion
                    }
                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;

                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "AccountMoney"
                        , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbFinPayCash", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        command.CommandText = string.Format(@"select AccountMoney  from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
                            , ht[0], ht[1]);
                        haReturn.Add(0, command.ExecuteScalar());
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    /// <summary>
    /// 应收处理
    /// </summary>
    public class SvrFinRecCash : SvrSystemDB, IServerSkedUserExecEx
    {
        //更新销售发票的处理
        private void UpdateSaleInvoice(SqlCommand cmd, DataRow row, decimal ApplyMoney,bool isCheck)
        {
            int isUse = 1;
            if (!isCheck) isUse = -1;
            string StrSql = @"Update tbSaleInvoice set ProMoney = ProMoney + {0}*{1} where BillNo = '{2}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();

            StrSql = @"Update tbSaleInvoice set IsUse = IsUse + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
        }
        private Boolean GetSaleInvoiceNoApplyMoney(SqlCommand cmd, DataRow DetailRow, bool isCheck)
        {
            if (DetailRow["SourceBillNo"].ToString() != "")
            {
                cmd.CommandText = string.Format(@"select UseState from tbSaleInvoice where BillNo = '{0}' 
                    and dbo.GetByteToBoolean(theState,0x0001)=1"
                    , DetailRow["SourceBillNo"]);
                object value = cmd.ExecuteScalar();
                if (value == null)
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]不存在，可能原单未确认！", DetailRow["SourceBillNo"]));
                    return false;
                }

                if (BitTermMath.GetBitTrue((int)value, BitTermMath.TermSix))
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]己被锁定，当前操作不可执行！", DetailRow["SourceBillNo"]));
                    return false;
                }

                decimal TotalMoney = xzSqlAnalysis.getAbsValue(DetailRow["TotalMoney"]);
                if (!isCheck) TotalMoney = -TotalMoney;
                string sql = @"select ProMoney from tbSaleInvoice where BillNo = '{0}'";
                cmd.CommandText = string.Format(sql, DetailRow["SourceBillNo"]);
                value = cmd.ExecuteScalar();
                if (value == null && (xzSqlAnalysis.getValue(value) + TotalMoney) < 0)
                {
                    xzException.WarnBox(string.Format("申请单单据号:{0}金额已经大于的未申请金额", DetailRow["SourceBillNo"]));
                    return false;
                }
            }
            return true;
        }

        //更新销售订单的处理
        private void UpdateSaleOrder(SqlCommand cmd, DataRow row, decimal ApplyMoney,bool isCheck)
        {
            int isUse = 1;
            if (!isCheck) isUse = -1;
            string StrSql = @"Update tbSaleOrder set ProMoney = ProMoney + {0}*{1} where BillNo = '{2}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
            StrSql = @"Update tbSaleOrder set IsUse = IsUse + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
        }
        private Boolean GetSaleOrderNoApplyMoney(SqlCommand cmd, DataRow DetailRow,bool isCheck)
        {
            if (DetailRow["SourceBillNo"].ToString() != "")
            {
                cmd.CommandText = string.Format(@"select UseState from tbSaleOrder where BillNo = '{0}' 
                    and dbo.GetByteToBoolean(theState,0x0001)=1"
                    , DetailRow["SourceBillNo"]);
                object value = cmd.ExecuteScalar();
                if (value == null)
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]不存在，可能原单未确认！", DetailRow["SourceBillNo"]));
                    return false;
                }

                if (BitTermMath.GetBitTrue((int)value, BitTermMath.TermSix))
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]己被锁定，当前操作不可执行！", DetailRow["SourceBillNo"]));
                    return false;
                }

                decimal TotalMoney = xzSqlAnalysis.getAbsValue(DetailRow["TotalMoney"]);
                if (!isCheck) TotalMoney = -TotalMoney;
                string sql = @"select ProMoney from tbSaleOrder where BillNo = '{0}'";
                cmd.CommandText = string.Format(sql, DetailRow["SourceBillNo"]);
                value = cmd.ExecuteScalar();
                if (value == null && (xzSqlAnalysis.getValue(value) + TotalMoney) < 0)
                {
                    xzException.WarnBox(string.Format("申请单单据号:{0}金额已经大于的未申请金额", DetailRow["SourceBillNo"]));
                    return false;
                }
            }
            return true;
        }

        //更新销售出库的处理
        private void UpdateSaleOut(SqlCommand cmd, DataRow row, decimal ApplyMoney,bool isCheck)
        {
            int isUse = 1;
            if (!isCheck) isUse = -1;
            string StrSql = @"Update tbSaleOut set ProMoney = ProMoney + {0}*{1} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();

            StrSql = @"Update tbSaleOut set IsUse = IsUse + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
        }
        private bool GetSaleOutNoApplyMoney(SqlCommand cmd, DataRow DetailRow, bool isCheck)
        {
            if (DetailRow["SourceBillNo"].ToString() != "")
            {
                cmd.CommandText = string.Format(@"select UseState from tbSaleOut where BillNo = '{0}' 
                    and dbo.GetByteToBoolean(theState,0x0001)=1"
                    , DetailRow["SourceBillNo"]);
                object value = cmd.ExecuteScalar();
                if (value == null)
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]不存在，可能原单未确认！", DetailRow["SourceBillNo"]));
                    return false;
                }

                if (BitTermMath.GetBitTrue((int)value, BitTermMath.TermSix))
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]己被锁定，当前操作不可执行！", DetailRow["SourceBillNo"]));
                    return false;
                }

                decimal TotalMoney = xzSqlAnalysis.getAbsValue(DetailRow["TotalMoney"]);
                if (!isCheck) TotalMoney = -TotalMoney;
                string sql = @"select ProMoney from tbSaleOut where BillNo = '{0}'";
                cmd.CommandText = string.Format(sql, DetailRow["SourceBillNo"]);
                value = cmd.ExecuteScalar();
                if (value == null && (xzSqlAnalysis.getValue(value) + TotalMoney) < 0)
                {
                    xzException.WarnBox(string.Format("申请单单据号:{0}金额已经大于的未申请金额", DetailRow["SourceBillNo"]));
                    return false;
                }
            }
            return true;
        }

        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate,SrcBillSort,  
                                CopartnerType, CopartnerCode, BalanceProfit, BalanceKeepPrice, BalanceCharge,  TotalMoney, FactPayMoney
                           from tbFinRecCash where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbFinRecCash";
                    DataRow MasterRow = MasterTable.Rows[0];

                    cmd.CommandText = string.Format(@"select BillNo,RowNo,SourceBillNo,SourceMoney,ApplyMoney,TotalMoney
                            from tbFinRecCashItem where BillNo = '{0}'", Row["BillNo"]);
                    DataTable DetailTable = new DataTable();
                    sqlda.Fill(DetailTable);
                    DetailTable.TableName = "tbFinRecCashItem";

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = isCheck;
                    #endregion

                    #region 判断
                    switch (Convert.ToInt32(MasterRow["SrcBillSort"]))
                    {
                        case 0:
                            break;
                        case 1:
                            #region 销售订单
                            foreach (DataRow DetailRow in DetailTable.Rows)
                            {
                                decimal ApplyMoney = Convert.ToDecimal(DetailRow["TotalMoney"]);
                                #region 更新采购订单
                                if (GetSaleOrderNoApplyMoney(cmd, DetailRow,isCheck)) 
                                UpdateSaleOrder(cmd, DetailRow, ApplyMoney, isCheck);
                                #endregion
                            }
                            #endregion                           
                            break;
                        case 2:
                            #region 销售出库
                            foreach (DataRow DetailRow in DetailTable.Rows)
                            {
                                decimal ApplyMoney = Convert.ToDecimal(DetailRow["TotalMoney"]);
                                #region 更新采购订单
                                if(GetSaleOutNoApplyMoney(cmd, DetailRow,isCheck))
                                UpdateSaleOut(cmd, DetailRow, ApplyMoney, isCheck);
                                #endregion
                            }
                            #endregion
                            break;
                        case 3:
                            #region 销售发票
                            foreach (DataRow DetailRow in DetailTable.Rows)
                            {
                                decimal ApplyMoney = Convert.ToDecimal(DetailRow["TotalMoney"]);
                                #region 更新采购订单
                                if (GetSaleInvoiceNoApplyMoney(cmd, DetailRow, isCheck))
                                    UpdateSaleInvoice(cmd, DetailRow, ApplyMoney, isCheck);
                                #endregion
                            }
                            #endregion
                            break;

                    }
                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    decimal Money = xzSqlAnalysis.getValue(MasterRow["BalanceProfit"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceProfit", Money);
                    Money = xzSqlAnalysis.getValue(MasterRow["BalanceKeepPrice"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceKeepPrice", Money);
                    Money = xzSqlAnalysis.getValue(MasterRow["BalanceCharge"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "HasBalanceCharge", Money);

                    FTotailMoney.CalcMoney(!isStorageIn, MasterRow["CopartnerType"], MasterRow["CopartnerCode"], "AccountMoney"
                        , xzSqlAnalysis.getValue(MasterRow["FactPayMoney"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();
            HYStorageManager FStorageManager;

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        FStorageManager = new HYStorageManager();
                        FStorageManager.Command = command;
                        haReturn.Add(0, FStorageManager.StorageHasListNo(ht[0], ht[1], ht[2]));
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbFinRecCash", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        command.CommandText = string.Format(@"select HasBalanceProfit, HasBalanceKeepPrice, HasBalanceCharge, AccountMoney
                               from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
                            , ht[0], ht[1]);
                        SqlDataReader Reader = command.ExecuteReader();
                        if (Reader.Read())
                        {
                            haReturn.Add(0, Reader["HasBalanceProfit"]);
                            haReturn.Add(1, Reader["HasBalanceKeepPrice"]);
                            haReturn.Add(2, Reader["HasBalanceCharge"]);
                            haReturn.Add(3, Reader["AccountMoney"]);
                        }
                        else
                        {
                            haReturn.Add(0, 0m);
                            haReturn.Add(1, 0m);
                            haReturn.Add(2, 0m);
                            haReturn.Add(3, 0m);
                        }
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    /// <summary>
    /// 转户并户
    /// </summary>
    public class SvrFinChanger : SvrSystemDB
    {
        //protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        //{
        //    bool b = BitTermMath.GetBitFalse((int)Row["TheState"], BitTermMath.TermOne);

        //    DataTable Dt = MoneyParam.GetFinChangeTable(Connect, Tran, "tbFinPurveyerChangerItem", "tbFinPurveyerChanger", string.Format("'{0}'", Row["BillNo"]));

        //    //定义更新语句{}
        //    string _UpdateFrom = "update tbFinPurveyCurBalance " + "\n"
        //                       + "set PayMoney=PayMoney{5}{1},NoBalanceProfit=NoBalanceProfit{5}{2},NoBalanceKeepPrice=NoBalanceKeepPrice{5}{3},NoBalanceCharge=NoBalanceCharge{5}{4} " + "\n"
        //                       + "where SupplyerCode='{0}' ",

        //            _UpdateTo = "update tbFinPurveyCurBalance " + "\n"
        //                       + "set PayMoney=PayMoney{5}{1},NoBalanceProfit=NoBalanceProfit{5}{2},NoBalanceKeepPrice=NoBalanceKeepPrice{5}{3},NoBalanceCharge=NoBalanceCharge{5}{4} " + "\n"
        //                       + "where SupplyerCode='{0}' ",

        //            _UpdateIns = "update tbFinPurveyCurBalance set InstantMoney=PayMoney-NoBalanceProfit-NoBalanceKeepPrice-NoBalanceCharge-VsBail+SupplyerBail";

        //    foreach (DataRow Dr in Dt.Rows)
        //    {
        //        //SqlCommand cmdFrom = new SqlCommand(string.Format(_UpdateFrom, (string)Dr["FromPurveyer"],
        //        //     Convert.ToDecimal(Dr["Cash"]), Convert.ToDecimal(Dr["BackBenefit"]), Convert.ToDecimal(Dr["KeepPrice"]),
        //        //     Convert.ToDecimal(Dr["Charge"]), MoneyParam.IsAdd(b)), Connect);
        //        SqlCommand cmdFrom = new SqlCommand(string.Format(_UpdateFrom, (string)Dr["FromPurveyer"],
        //                0, Convert.ToDecimal(Dr["BackBenefit"]), Convert.ToDecimal(Dr["KeepPrice"]),
        //                Convert.ToDecimal(Dr["Charge"]), MoneyParam.IsAdd(b)), Connect);
        //        cmdFrom.Transaction = Tran;
        //        cmdFrom.ExecuteNonQuery();

        //        //SqlCommand cmdTo = new SqlCommand(string.Format(_UpdateTo, (string)Dr["ToPurveyer"],
        //        //     Convert.ToDecimal(Dr["Cash"]), Convert.ToDecimal(Dr["BackBenefit"]), Convert.ToDecimal(Dr["KeepPrice"]),
        //        //     Convert.ToDecimal(Dr["Charge"]), MoneyParam.IsAdd(!b)), Connect);
        //        SqlCommand cmdTo = new SqlCommand(string.Format(_UpdateTo, (string)Dr["ToPurveyer"],
        //            0, Convert.ToDecimal(Dr["BackBenefit"]), Convert.ToDecimal(Dr["KeepPrice"]),
        //            Convert.ToDecimal(Dr["Charge"]), MoneyParam.IsAdd(!b)), Connect);
        //        cmdTo.Transaction = Tran;
        //        cmdTo.ExecuteNonQuery();
        //    }

        //    SqlCommand cmdIns = new SqlCommand(_UpdateIns, Connect);
        //    cmdIns.Transaction = Tran;
        //    cmdIns.ExecuteNonQuery();
        //}
    }

    /// <summary>
    /// 员工丢机赔偿跟踪
    /// </summary>
    public class SvrFinLoseHandset : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYAccountCommon HYAccountPro = new HYAccountCommon();
                    HYAccountPro.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse,FlowBit,SrcBillSort from tbFinLoseHandset where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbSelfLoseHandset";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    DataTable DetailTable = new DataTable();
                    cmd.CommandText = string.Format("select * from tbFinLoseHandsetItem where BillNo = '{0}'", Row["BillNo"]);
                    sqlda.Fill(DetailTable);
                    DetailTable.TableName = "tbSelfLoseHandsetItem";

                    #endregion

                    int SrcBillSort = (int)MasterRow["SrcBillSort"];

                    #region 判断
                    foreach (DataRow DetailRow in DetailTable.Rows)
                    {
                        switch (SrcBillSort)
                        {
                            case 0:
                                #region 仓库盘点单
                                HYAccountPro.UpdateSrcBill(Connect, Tran, isCheck, DetailTable, "tbStorCheck", "tbStorCheckItem", "CompensateNumber", "Number");
                                #endregion
                                break;
                        }
                    }
                    #endregion
                }
            }
        }
    }

    /// <summary>
    /// 员工丢机赔偿付款
    /// </summary>
    public class SvrFinPayCashLoseHandset : SvrSystemDB
    {
        //更新付款申请单的处理
        private void UpdateFinLoseHandset(SqlCommand cmd, DataRow row, decimal ApplyMoney, bool isCheck)
        {
            int isUse = 1;
            if (!isCheck) isUse = -1;
            string StrSql = @"Update tbFinLoseHandsetItemDetail set PlayMoney = PlayMoney + {0}*{1} where BillNo = '{2}' and RowNo={3} and DetailNo='{4}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, isUse, row["SourceBillNo"],row["RowNo"],row["DetailNo"]);
            cmd.ExecuteNonQuery();

            StrSql = @"Update tbFinLoseHandset set IsUse = IsUse + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, isUse, row["SourceBillNo"]);
            cmd.ExecuteNonQuery();
        }
        private bool GetFinLoseHandsetNoApplyMoney(SqlCommand cmd, DataRow DetailRow, bool isCheck)
        {
            if (DetailRow["SourceBillNo"].ToString() != "")
            {
                cmd.CommandText = string.Format(@"select UseState from tbFinLoseHandset where BillNo = '{0}' 
                    and dbo.GetByteToBoolean(theState,0x0001)=1"
                    , DetailRow["SourceBillNo"]);
                object value = cmd.ExecuteScalar();
                if (value == null)
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]不存在，可能原单未确认！", DetailRow["SourceBillNo"]));
                    return false;
                }

                if (BitTermMath.GetBitTrue((int)value, BitTermMath.TermSix))
                {
                    xzException.WarnBox(string.Format("源单据:[{0}]己被锁定，当前操作不可执行！", DetailRow["SourceBillNo"]));
                    return false;
                }

                decimal TotalMoney = xzSqlAnalysis.getAbsValue(DetailRow["TotalMoney"]);
                if (!isCheck) TotalMoney = -TotalMoney;
                string sql = @"select PlayMoney from tbFinLoseHandsetItemDetail where BillNo = '{0}' and RowNo={1} and DetailNo='{2]'";
                cmd.CommandText = string.Format(sql, DetailRow["SourceBillNo"], DetailRow["RowNo"],DetailRow["DetailNo"]);
                value = cmd.ExecuteScalar();
                if (value == null && (xzSqlAnalysis.getValue(value) + TotalMoney) < 0)
                {
                    xzException.WarnBox(string.Format("丢机单单据号:{0}金额与分摊金额不等!", DetailRow["SourceBillNo"]));
                    return false;
                }
            }
            return true;
        }

        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYAccountCommon HYAccountPro = new HYAccountCommon();
                    HYAccountPro.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate, SrcBillSort
                           from tbFinRecCashLoseHandset where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbFinRecCashLoseHandset";
                    DataRow MasterRow = MasterTable.Rows[0];

                    DataTable DetailTable = new DataTable();
                    cmd.CommandText = string.Format("select * from tbFinRecCashLoseHandsetItem where BillNo = '{0}'", Row["BillNo"]);
                    sqlda.Fill(DetailTable);
                    DetailTable.TableName = "tbFinRecCashLoseHandsetItem";

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = isCheck;
                    #endregion

                    int SrcBillSort = (int)MasterRow["SrcBillSort"];

                    switch (SrcBillSort)
                    {
                        case 0:
                            #region 员工丢机单
                            foreach (DataRow DetailRow in DetailTable.Rows)
                            {
                                decimal ApplyMoney = Convert.ToDecimal(DetailRow["TotalMoney"]);
                                #region 更新采购订单
                                if (GetFinLoseHandsetNoApplyMoney(cmd, DetailRow, isCheck))
                                    UpdateFinLoseHandset(cmd, DetailRow, ApplyMoney, isCheck);
                                #endregion
                            }
                            #endregion
                            break;
                    }

                    #region 更新即时金额

                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbFinRecCashLoseHandset", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        haReturn.Add(0, 0m);
//                        command.CommandText = string.Format(@"select HasBalanceProfit, HasBalanceKeepPrice, HasBalanceCharge, AccountMoney
//                               from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
//                            , ht[0], ht[1]);
//                        SqlDataReader Reader = command.ExecuteReader();
//                        if (Reader.Read())
//                        {
//                            haReturn.Add(0, Reader["HasBalanceProfit"]);
//                            haReturn.Add(1, Reader["HasBalanceKeepPrice"]);
//                            haReturn.Add(2, Reader["HasBalanceCharge"]);
//                            haReturn.Add(3, Reader["AccountMoney"]);
//                        }
//                        else
//                        {
//                            haReturn.Add(0, 0m);
//                            haReturn.Add(1, 0m);
//                            haReturn.Add(2, 0m);
//                            haReturn.Add(3, 0m);
//                        }
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }
    #endregion

    #region 应付管理
    /// <summary>
    /// 供应商其它费用
    /// </summary>
    public class SvrStockChargeOther : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate ,SupplyerType, SupplyerCode
                             , TotalMoney, SettlementMoney
                           from tbStockChargeOther where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbStockChargeOther";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    //DataTable DetailTable = new DataTable();
                    //cmd.CommandText = string.Format("select * from tbStockChargeOtherItem where BillNo = '{0}'", Row["BillNo"]);
                    //sqlda.Fill(DetailTable);
                    //DetailTable.TableName = "tbStockChargeOtherItem";

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && isCheck) || (isRadBill && !isCheck);

                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceCharge"
                        , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbStockChargeOther", ht[0]);
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        } 
    }

    /// <summary>
    ///供应商市场费用
    /// </summary>
    public class SvrStockChargePromotion : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate ,BillSort ,SupplyerType, SupplyerCode
                             , TotalMoney, SettlementMoney
                           from tbStockChargePromotion where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbStockChargePromotion";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);


                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && isCheck) || (isRadBill && !isCheck);

                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    switch (MasterRow["BillSort"].ToString())
                    {
                        case "001":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "002":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                    }
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在

                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbStockChargePromotion", ht[0]);
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        } 
    }

    /// <summary>
    ///供应商费用结算
    /// </summary>
    public class SvrStockChargeSettlement : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate, 
                                SrcBillSort, SupplyerType, SupplyerCode, TotalMoney
                           from tbStockChargeSettlement where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbStockChargeSettlement";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && !isCheck) || (isRadBill && isCheck);
                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    switch (MasterRow["SrcBillSort"].ToString())
                    {
                        case "1":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "2":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "3":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "NoBalanceCharge"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceCharge"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                    }
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbStockChargeSettlement", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        command.CommandText = string.Format(@"select isnull(NoBalanceProfit,0) + isnull(NoBalanceKeepPrice,0) 
                               + isnull(NoBalanceCharge,0) from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
                            , ht[0], ht[1]);
                        haReturn.Add(0, command.ExecuteScalar());
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }    
    }

    /// <summary>
    /// 付款申请单处理
    /// </summary>
    public class SvrStockPayApply : SvrSystemDB, IServerSkedUserExecEx
    {
        private void UpdateStockOrder(SqlCommand cmd, DataRow row, decimal ApplyMoney)
        {
            string StrSql = @"Update tbStockOrder set ApplyTotalMoney = ApplyTotalMoney + {0} where BillNo = '{1}'";
            cmd.CommandText = string.Format(StrSql, ApplyMoney, row["BillNo"]);
            cmd.ExecuteNonQuery();
        }

        private decimal GetStockOrderNoApplyMoney(SqlCommand cmd, DataRow row)
        {
            string StrSql = @"select  (TotalMoney-ApplyTotalMoney) NoApplyMoney from tbStockOrder where BillNo = '{0}'";
            cmd.CommandText = string.Format(StrSql, row["BillNo"]);
            return Convert.ToDecimal(cmd.ExecuteScalar());
        }

        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate,  
                                SupplyerType, SupplyerCode, BalanceProfit, BalanceKeepPrice, BalanceCharge,  TotalMoney, FactPayMoney
                           from tbStockPayApply where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbStockPayApply";
                    DataRow MasterRow = MasterTable.Rows[0];

                    cmd.CommandText = string.Format(@"select BillNo,RowNo,SrcBillSort,SourceBillNo,SourceMoney,ApplyMoney,
                        TotalMoney from tbStockPayApplyItem where BillNo = '{0}'", Row["BillNo"]);
                    DataTable DetailTable = new DataTable();
                    sqlda.Fill(DetailTable);
                    DetailTable.TableName = "tbStockPayApplyItem";

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = isCheck;
                    #endregion

                    #region 判断
                    foreach (DataRow DetailRow in DetailTable.Rows)
                    {
                        decimal NoApplyMoney= GetStockOrderNoApplyMoney(cmd, DetailRow);
                        decimal ApplyMoney=Convert.ToDecimal(DetailRow["TotalMoney"]);
                        if ((Convert.ToInt32(DetailRow["SrcBillSort"])!=0)&&( ApplyMoney>NoApplyMoney))
                        {
                            xzException.WarnBox(string.Format("申请金额已经大于单据号:{0}的未申请金额{1}!",DetailRow["BillNo"],NoApplyMoney));
                        }
                        
                        #region 更新采购订单
                        UpdateStockOrder(cmd, DetailRow, ApplyMoney);
                        #endregion
                    }
                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    decimal Money = xzSqlAnalysis.getValue(MasterRow["BalanceProfit"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceProfit", Money);
                    Money = xzSqlAnalysis.getValue(MasterRow["BalanceKeepPrice"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceKeepPrice", Money);
                    Money = xzSqlAnalysis.getValue(MasterRow["BalanceCharge"]);
                    if (Money > 0m) FTotailMoney.CalcMoney(!isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "HasBalanceCharge", Money);

                    //FTotailMoney.CalcMoney(isStorageIn, MasterRow["SupplyerType"], MasterRow["SupplyerCode"], "AccountMoney"
                    //    , xzSqlAnalysis.getValue(MasterRow["FactPayMoney"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();
            HYStorageManager FStorageManager;

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        FStorageManager = new HYStorageManager();
                        FStorageManager.Command = command;
                        haReturn.Add(0, FStorageManager.StorageHasListNo(ht[0], ht[1], ht[2]));
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbStockPayApply", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        command.CommandText = string.Format(@"select HasBalanceProfit, HasBalanceKeepPrice, HasBalanceCharge, AccountMoney
                               from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
                            , ht[0], ht[1]);
                        SqlDataReader Reader = command.ExecuteReader();
                        if (Reader.Read())
                        {
                            haReturn.Add(0, Reader["HasBalanceProfit"]);
                            haReturn.Add(1, Reader["HasBalanceKeepPrice"]);
                            haReturn.Add(2, Reader["HasBalanceCharge"]);
                            haReturn.Add(3, Reader["AccountMoney"]);
                        }
                        else
                        {
                            haReturn.Add(0, 0m);
                            haReturn.Add(1, 0m);
                            haReturn.Add(2, 0m);
                            haReturn.Add(3, 0m);
                        }
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    #endregion

    #region 应收管理
    /// <summary>
    /// 销售其它费用
    /// </summary>
    public class SvrSaleChargeOther : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate ,ClientType, ClientCode
                             , TotalMoney, SettlementMoney
                           from tbSaleChargeOther where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbSaleChargeOther";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    //DataTable DetailTable = new DataTable();
                    //cmd.CommandText = string.Format("select * from tbStockChargeOtherItem where BillNo = '{0}'", Row["BillNo"]);
                    //sqlda.Fill(DetailTable);
                    //DetailTable.TableName = "tbStockChargeOtherItem";

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && isCheck) || (isRadBill && !isCheck);

                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceCharge"
                        , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在

                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbSaleChargeOther", ht[0]);
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    /// <summary>
    ///售售市场费用
    /// </summary>
    public class SvrSaleChargePromotion : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate ,BillSort ,ClientType, ClientCode
                             , TotalMoney, SettlementMoney
                           from tbSaleChargePromotion where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbSaleChargePromotion";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);


                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && isCheck) || (isRadBill && !isCheck);

                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    switch (MasterRow["BillSort"].ToString())
                    {
                        case "001":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "002":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                    }
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在

                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbSaleChargePromotion", ht[0]);
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    /// <summary>
    ///销售费用结算
    /// </summary>
    public class SvrSaleChargeSettlement : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs e)
        {
            //确认操作
            if (Row != null && e.SignBit == BitTermMath.TermOne)
            {
                using (SqlCommand cmd = new SqlCommand())
                using (SqlDataAdapter sqlda = new SqlDataAdapter())
                {
                    cmd.Connection = Connect;
                    cmd.Transaction = Tran;
                    sqlda.SelectCommand = cmd;

                    bool isCheck = !BitTermMath.GetBitTrue(e.SourceStateValue, e.SignBit);

                    HYStorageManager FStorageManager = new HYStorageManager();
                    FStorageManager.Command = cmd;

                    #region 初始化参数
                    cmd.CommandText = string.Format(@"select IsUse, FlowBit, UseState, TheDate, 
                                SrcBillSort, ClientType, ClientCode, TotalMoney
                           from tbSaleChargeSettlement where BillNo = '{0}'", Row["BillNo"]);
                    DataTable MasterTable = new DataTable();
                    sqlda.Fill(MasterTable);
                    MasterTable.TableName = "tbSaleChargeSettlement";
                    DataRow MasterRow = MasterTable.Rows[0];

                    //财务审核
                    if (BitTermMath.GetBitTrue((int)MasterRow["FlowBit"], BitTermMath.TermOne))
                        xzException.WarnBox(HYStorageMessage._MsgHasAuditing);

                    //被其它的单据引用
                    if ((int)MasterRow["IsUse"] > 0)
                        xzException.WarnBox(HYStorageMessage._MsgBillNoCheck);

                    bool isRadBill = BitTermMath.GetBitTrue((int)MasterRow["UseState"], BitTermMath.TermOne);
                    bool isStorageIn = (!isRadBill && !isCheck) || (isRadBill && isCheck);
                    #endregion

                    #region 判断

                    #endregion

                    #region 更新即时金额
                    HYTotailMoney FTotailMoney = new HYTotailMoney();
                    FTotailMoney.Command = cmd;
                    switch (MasterRow["SrcBillSort"].ToString())
                    {
                        case "1":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "HasBalanceProfit"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "2":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "HasBalanceKeepPrice"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                        case "3":
                            FTotailMoney.CalcMoney(isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "NoBalanceCharge"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            FTotailMoney.CalcMoney(!isStorageIn, MasterRow["ClientType"], MasterRow["ClientCode"], "HasBalanceCharge"
                                , xzSqlAnalysis.getValue(MasterRow["TotalMoney"]));
                            break;
                    }
                    #endregion
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            Hashtable haReturn = new Hashtable();

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand command = new SqlCommand();
                command.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 判断串号是否在库存中存在
                        #endregion
                        break;
                    case 1:
                        #region 判断单据是否可以进入更改
                        HYStorageMessage.HintChange(command, "tbSaleChargeSettlement", ht[0]);
                        #endregion
                        break;
                    case 2:
                        #region 获取未结算金额
                        command.CommandText = string.Format(@"select isnull(NoBalanceProfit,0) + isnull(NoBalanceKeepPrice,0) 
                               + isnull(NoBalanceCharge,0) from tbFinTotalMoney where CopartnerType = '{0}' and CopartnerCode = '{1}'"
                            , ht[0], ht[1]);
                        haReturn.Add(0, command.ExecuteScalar());
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haReturn;
        }
    }

    #endregion
}