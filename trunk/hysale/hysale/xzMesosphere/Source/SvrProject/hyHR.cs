using System;
using System.Collections.Generic;
using System.Collections;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;

namespace xzMesosphere.Source
{
    public class hyHR
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            SvrHRDatumEmployee.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrHRDatumEmployee), "ISvrHRDatumEmployee");//员工管理

            SvrHREmployeePostChange.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrHREmployeePostChange), "ISvrHREmployeePostChange");//职位调整

            SvrHREmployeePayChange.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrHREmployeePayChange), "ISvrHREmployeePayChange");//工资调整
        }
    }

    /// <summary>
    /// 员工管理
    /// </summary>
    public class SvrHRDatumEmployee : SvrSystemDB
    {
    }

    /// <summary>
    /// 职位调整
    /// </summary>
    public class SvrHREmployeePostChange : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(Hashtable ParamsArg, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "") Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                switch (ID)
                {
                    case 0:
                        string sql_update = string.Format("update tbDatumEmployee set theDept = '{1}',thePosition = '{2}' where  theCode = '{0}'"
                             , ParamsArg[0], ParamsArg[1], ParamsArg[2]);
                        SqlCommand cmd = new SqlCommand(sql_update, Connect);
                        cmd.ExecuteNonQuery();
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

    //工资调整
    public class SvrHREmployeePayChange : SvrSystemDB, IServerSkedUserExecEx
    {
        protected override void ExecState(SqlConnection Connect, SqlTransaction Tran, DataRow Row, xzSourceStateEventArgs ParamArg)
        {
            if (Row!=null && ParamArg.SignBit == BitTermMath.TermOne)
            {
                if (!BitTermMath.GetBitTrue(ParamArg.SourceStateValue, ParamArg.SignBit))
                {
                    string sql_update = string.Format("update tbDatumEmployee set PostLevelNo = '{0}',BaddishLevelNo = '{1}' where  theCode = '{2}'"
                         , Row["newPostLevelNo"], Row["newBaddishLevelNo"], Row["EmployeeNo"]);
                    SqlCommand cmd = new SqlCommand(sql_update, Connect);
                    cmd.Transaction = Tran;
                    cmd.ExecuteNonQuery();
                }
            }
        }

        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            #region 获取工资
            Hashtable htResult = new Hashtable();
            string BaddishLevel = ht[0].ToString(), PostLevel = ht[1].ToString();
            switch (ID)
            {
                case 0:
                    Connect.Open();
                    try
                    {
                        string s_Sql = string.Format("select BaddishPay,PostPay from tbEmployeeNormalPay where BaddishLevel = '{0}' and PostLevel = '{1}'", BaddishLevel, PostLevel);
                        SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                        SqlDataReader reader = cmd.ExecuteReader();
                        if (reader.Read())
                        {
                            htResult.Add(0, reader[0].ToString());
                            htResult.Add(1, reader[1].ToString());
                        }
                    }
                    finally
                    {
                        Connect.Close();
                    }
                    break;
            }
            return htResult;
            #endregion
        }
    }
}
