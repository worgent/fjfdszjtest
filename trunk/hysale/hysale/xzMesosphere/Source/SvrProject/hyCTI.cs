using System;
using System.Collections.Generic;
using System.Collections;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using XzzLibrary.Source;

namespace xzMesosphere.Source
{
    public class ProjectCTI
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            SvrCTISaveLeaveWord.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrCTISaveLeaveWord), "ISvrCTISaveLeaveWord");
            SvrCTIRecLawNote.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrCTIRecLawNote), "ISvrCTIRecLawNote");
        }
    }

    /// <summary>
    /// 保存留言音

    /// </summary>
    class SvrCTIRecLawNote : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(Hashtable ParamArg, int ID, xzRemotingParam RemotingParam)
        {
            Connect.Open();
            try
            {
                SqlCommand cmdLeaveWord = new SqlCommand("", Connect);
                cmdLeaveWord.CommandText = "update tbCTIAllRecordFile set RecordFile=@RecordFile where RecordFileName=@RecordFileName";
                cmdLeaveWord.Parameters.Add("@RecordFileName", SqlDbType.VarChar);
                cmdLeaveWord.Parameters.Add("@RecordFile", SqlDbType.Image);
                cmdLeaveWord.Parameters["@RecordFileName"].Value = ParamArg[0];
                cmdLeaveWord.Parameters["@RecordFile"].Value = ParamArg[1];
                cmdLeaveWord.ExecuteNonQuery();
                cmdLeaveWord.Parameters.Clear();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {

        }
    }

    /// <summary>
    /// 保存留言音


    /// </summary>
    class SvrCTISaveLeaveWord : SvrSystemDB, IServerSkedUserExec
    {
        public void UserExec(DataSet ds, int ID, xzRemotingParam RemotingParam)
        {
        }

        public void UserExec(Hashtable ParamArg, int ID, xzRemotingParam RemotingParam)
        {
            Connect.Open();
            try
            {
                SqlCommand cmdLeaveWord = new SqlCommand("", Connect);
                cmdLeaveWord.CommandText = "update tbCTICfgChannel set LeaveWordFile=@LeaveWordFile where ChannelNo=@ChannelNo";
                cmdLeaveWord.Parameters.Add("@ChannelNo", SqlDbType.VarChar);
                cmdLeaveWord.Parameters.Add("@LeaveWordFile", SqlDbType.Image);
                cmdLeaveWord.Parameters["@ChannelNo"].Value = ParamArg[0];
                cmdLeaveWord.Parameters["@LeaveWordFile"].Value = ParamArg[1];
                cmdLeaveWord.ExecuteNonQuery();
                cmdLeaveWord.Parameters.Clear();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void UserExec(DataTable dt, int ID, xzRemotingParam RemotingParam)
        {

        }
    }
}
