using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;


namespace xzMesosphere.Source
{
    public class HardwareStorage
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareCheck), "ISvrHardwareCheck");
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareOut), "ISvrHardwareOut");
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareIn), "IsvrHardwareIn");
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareRestore), "IsvrHardwareRestore");
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareLoan), "IsvrHardwareLoan");
            FServerManage.Server.StartupTCPServer(typeof(SvrHardwareTransfer), "IsvrHardwareTransfer");
            FServerManage.Server.StartupHTTPServer(typeof(SvrHardwareEmployeeCheck), "IsvrHardwareEmployeeCheck");
          
        }
    }
    /// <summary>
    /// 配件仓库的参数
    /// </summary>
    public sealed class HardwareStorageParam
    {
        //库存表
        public const string _StorageTableName = "tbHardwareStorage";
        public const string _StorageKey = "StorageCode;PlaceCode;HardwareCode;NewOld";
        public const string _StorageAmount = "Amount";

        //员工
        public const string _EmployeeStorageTableName = "tbHardwareEmployeeStorage";
        public const string _EmployeeStorageKey = "EmployeeCode;HardwareCode";
        public const string _EmployeeStorageAmount = "Amount";


        //计算库存套用格式 
        public const string _Hardware = "Select b.Store,a.Shelf,a.theName,a.NewOld,a.Amount from {0} a Left Outer JOin {1} b on a.BillNo=b.BIllNo where a.BIllNo='{2}'";
        public const string _Employee = "Select b.WorkNo,a.theName,a.Amount from {0} a Left Outer Join {1} b on a.BillNo=b.BIllNo where a.BIllNo='{2}'";

        /// <summary>
        //主从关系表
        /// <summary>
        public static DataTable GetMasterDetail(SqlConnection Connect, SqlTransaction Tran, string ADataTable, string HDataTable, string ADataCol)
        {
            using (SqlDataAdapter Comm = new SqlDataAdapter(string.Format(_Hardware, ADataTable, HDataTable, ADataCol), Connect))
            {
                Comm.SelectCommand.Transaction = Tran;
                DataTable DDt = new DataTable();
                Comm.Fill(DDt);
                return DDt;
            }
        }

        public static DataTable GetEmployee(SqlConnection Connect, SqlTransaction Tran, string ADataTable, string HDataTable, string ADataCol)
        {
            using (SqlDataAdapter Comm = new SqlDataAdapter(string.Format(_Employee, ADataTable, HDataTable, ADataCol), Connect))
            {
                Comm.SelectCommand.Transaction = Tran;
                DataTable DDt = new DataTable();
                Comm.Fill(DDt);
                return DDt;
            }
        }
    }

    /// <summary>
    /// 配件入库
    /// </summary>
    class SvrHardwareIn : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey
                 , HardwareStorageParam._StorageAmount);
                DataTable RestTable = HardwareStorageParam.GetMasterDetail(Connect, Tran, "tbHardwareInItem", "tbHardwareIn", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["Store"], dr["Shelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }
            }
        }
    }


    /// <summary>
    /// 配件出库
    /// </summary>
    class SvrHardwareOut : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey
                 , HardwareStorageParam._StorageAmount);
                DataTable RestTable = HardwareStorageParam.GetMasterDetail(Connect, Tran, "tbHardwareOutItem", "tbHardwareOut", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(!b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["Store"], dr["Shelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }
            }
        }
    }

    /// <summary>
    /// 配件领用
    /// </summary>
    class SvrHardwareLoan : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                //库存
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey, HardwareStorageParam._StorageAmount);
                DataTable RestTable = HardwareStorageParam.GetMasterDetail(Connect, Tran, "tbHardwareLoanItem", "tbHardwareLoan", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(!b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["Store"], dr["Shelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }

                //员工库存
                Depository.Initial(HardwareStorageParam._EmployeeStorageTableName, HardwareStorageParam._EmployeeStorageKey, HardwareStorageParam._EmployeeStorageAmount);
                DataTable RestTalbeEmployee = HardwareStorageParam.GetEmployee(Connect, Tran, "tbHardwareLoanItem", "tbHardwareLoan", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTalbeEmployee.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}'", dr["WorkNo"], dr["theName"]), dr["Amount"].ToString());
                }
            }
        }
    }
    /// <summary>
    /// 配件归还
    /// </summary>
    class SvrHardwareRestore : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                //库存
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey
                 , HardwareStorageParam._StorageAmount);
                DataTable RestTable = HardwareStorageParam.GetMasterDetail(Connect, Tran, "tbHardwareRestoreItem", "tbHardwareRestore", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["Store"], dr["Shelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }

                //员工库存
                Depository.Initial(HardwareStorageParam._EmployeeStorageTableName, HardwareStorageParam._EmployeeStorageKey, HardwareStorageParam._EmployeeStorageAmount);
                DataTable RestTalbeEmployee = HardwareStorageParam.GetEmployee(Connect, Tran, "tbHardwareRestoreItem", "tbHardwareRestore", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTalbeEmployee.Rows)
                {
                    Depository.Exec(!b, Connect, Tran, string.Format("'{0}';'{1}'", dr["WorkNo"], dr["theName"]), dr["Amount"].ToString());
                }
            }
        }
    }
    /// <summary>
    /// 配件调拨
    /// </summary>
    class SvrHardwareTransfer : SvrSystemDB
    {

        public const string _Transfer = "select b.BeginStore ,b.EndStore,a.BeginShelf,a.EndShelf,a.theName,a.NewOld,a.Amount from {0} a left Outer Join {1} b on a.BillNo=b.BillNo where a.BillNo='{2}'";

        public static DataTable GetMasterDetail(SqlConnection Connect, SqlTransaction Tran, string ADataTable, string HDataTable, string ADataCol)
        {
            using (SqlDataAdapter Comm = new SqlDataAdapter(string.Format(_Transfer, ADataTable, HDataTable, ADataCol), Connect))
            {
                Comm.SelectCommand.Transaction = Tran;
                DataTable DDt = new DataTable();
                Comm.Fill(DDt);
                return DDt;
            }
        }
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey
                 , HardwareStorageParam._StorageAmount);
                DataTable RestTable = SvrHardwareTransfer.GetMasterDetail(Connect, Tran, "tbHardwareTransferItem", "tbHardwareTransfer", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(!b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["BeginStore"], dr["BeginShelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["EndStore"], dr["EndShelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }
            }
        }
    }

    /// <summary>
    /// 配件盘点
    /// </summary>
    class SvrHardwareCheck : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
                Depository.Initial(HardwareStorageParam._StorageTableName, HardwareStorageParam._StorageKey
                 , HardwareStorageParam._StorageAmount);
                DataTable RestTable = HardwareStorageParam.GetMasterDetail(Connect, Tran, "tbHardwareCheckItem", "tbHardwareCheck", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTable.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}';'{2}';'{3}'", dr["Store"], dr["Shelf"], dr["theName"], dr["NewOld"]), dr["Amount"].ToString());
                }
            }
        }
    }
    /// 配件员工盘点
    /// </summary>
    class SvrHardwareEmployeeCheck : SvrSystemDB
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row)
        {
            bool a = BitTermMath.GetBitTrue((int)Row["TheState", DataRowVersion.Original], BitTermMath.TermOne);
            bool c = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);
            if (a ^ c)
            {
                bool b = BitTermMath.GetBitTrue((int)Row["TheState"], BitTermMath.TermOne);

                xzDepositoryStorage Depository = new xzDepositoryStorage();
               
                //员工库存
                Depository.Initial(HardwareStorageParam._EmployeeStorageTableName, HardwareStorageParam._EmployeeStorageKey, HardwareStorageParam._EmployeeStorageAmount);
                DataTable RestTalbeEmployee = HardwareStorageParam.GetEmployee(Connect, Tran, "tbHardwareEmployeeCheckItem", "tbHardwareEmployeeCheck", (string)Row["BillNo"]);
                foreach (DataRow dr in RestTalbeEmployee.Rows)
                {
                    Depository.Exec(b, Connect, Tran, string.Format("'{0}';'{1}'", dr["WorkNo"], dr["theName"]), dr["Amount"].ToString());
                }
            }
        }
    }

}

  


