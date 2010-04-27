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
    public class hySystem
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            SvrSystemParam.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemParam), "ISvrSystemParam");
            SvrSystemImput.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemImput), "ISvrSystemImput");
        }
    }

    /// <summary>
    /// 企业参数
    /// </summary>
    public class SvrSystemParam : SvrSystemDB, IServerSkedUserExecEx
    {
        public Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            Hashtable haResult = new Hashtable();
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand();
                cmd.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 获取参数
                        using (SqlDataAdapter Sqlda = new SqlDataAdapter())
                        {
                            DataTable Table = new DataTable();
                            cmd.CommandText = string.Format(@"select TheCode, ShortName,SpellHelp, TheName,Corporation,
                                        Addree, PostalCode, fax, Phone, Homepage, EMail, Linkman, LinkPhone, Remark,
                                        SystemTitle, StartTime, SystemOther from tsSystem where TheCode = '{0}'"
                                , SystemGuble._PlacelNo);
                            cmd.ExecuteNonQuery();
                            Sqlda.SelectCommand = cmd;
                            Sqlda.Fill(Table);
                            haResult.Add(0, Table);
                        }
                        #endregion
                        break;
                    case 1:
                        #region 保存参数
                        cmd.CommandText = string.Format(@"update tsSystem Set TheName = '{0}' 
                            ,ShortName = '{1}', SpellHelp = '{2}', Corporation = '{3}', Addree = '{4}'
                            , PostalCode = '{5}', Phone = '{6}', fax = '{7}',  Linkman = '{8}', LinkPhone = '{9}'
                            ,SystemTitle = '{10}', StartTime = {11} , SystemOther = @SystemOther 
                          where TheCode = '{12}'", ht[0] , ht[1] , ht[2],  ht[3], ht[4], ht[5], ht[6], ht[7], ht[8], ht[9], ht[10],
                            xzSqlAnalysis.GetNull(ht[11]), SystemGuble._PlacelNo);
                        cmd.Parameters.Add("@SystemOther", SqlDbType.Image);
                        cmd.Parameters["@SystemOther"].Value = ht[12];
                        cmd.ExecuteNonQuery();
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haResult;
        }
    }

    /// <summary>
    /// 导入参数
    /// </summary>
    public class SvrSystemImput : SvrSystemDB, IServerSkedUserExecEx
    {
        public virtual Hashtable getUserHashtable(Hashtable ht, int ID, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;

            SqlTransaction Trans = null;
            DataTable Table = null;
            Hashtable haResult = new Hashtable();
            Connect.Open();
            try
            {
                SqlCommand FCommand = new SqlCommand();
                FCommand.Connection = Connect;
                switch (ID)
                {
                    case 0:
                        #region 获取数据库表信息
                        ArrayList FTableNameArg = new ArrayList();
                        FCommand.CommandText = "exec sp_Tables_rowSet;2 null,null";
                        SqlDataReader Reader = FCommand.ExecuteReader();
                        while (Reader.Read())
                        {
                            if (Reader[3].ToString() != "SYSTEM TABLE" && Reader[3].ToString() != "VIEW"
                                && Reader[3].ToString() != "SYSTEM VIEW")
                            {
                                FTableNameArg.Add(Reader[2]);
                            }
                        }
                        Reader.Close();
                        haResult.Add(0, FTableNameArg);
                        #endregion
                        break;
                    case 1:
                        #region 获取表的结构信息
                        using (SqlDataAdapter Sqlda = new SqlDataAdapter())
                        {
                            Sqlda.SelectCommand = FCommand;
                            FCommand.CommandText = string.Format("select * from {0}", ht[0]);
                            Table = new DataTable();                            
                            Sqlda.FillSchema(Table, SchemaType.Mapped);
                            haResult.Add(0, Table);
                        }
                        #endregion
                        break;
                    case 2:
                        #region 将数据保存到数据库中
                        Trans = Connect.BeginTransaction();
                        try
                        {
                            FCommand.Transaction = Trans;
                            Table = ht[0] as DataTable;
                            foreach (DataRow CurrRow in Table.Rows)
                            {
                                string ins_fields = "", ins_values = "";
                                foreach (DataColumn Column in Table.Columns)
                                {
                                    if (ins_fields == "") ins_fields = string.Format("{0}", Column.ColumnName);
                                    else ins_fields = ins_fields + string.Format(", {0}", Column.ColumnName);

                                    if (ins_values == "") ins_values = string.Format("{0}", xzSqlAnalysis.GetNull(CurrRow[Column.ColumnName]));
                                    else ins_values = ins_values + string.Format(", {0}", xzSqlAnalysis.GetNull(CurrRow[Column.ColumnName]));
                                }

                                FCommand.CommandText = string.Format("insert into {0} ({1}) values ({2})", ht[1], ins_fields, ins_values);
                                FCommand.ExecuteNonQuery();
                            }
                            Trans.Commit();
                        }
                        catch (Exception ex)
                        {
                            Trans.Rollback();
                            xzException.WarnBox(ex.Message);
                        }
                        #endregion
                        break;
                    case 3:
                        #region 清除数据库中的数据
                        FCommand.CommandText = string.Format("delete {0}", ht[0]);
                        FCommand.ExecuteNonQuery();
                        #endregion
                        break;
                    case 4:
                        #region 获取导入参数
                        using (SqlDataAdapter Sqlda = new SqlDataAdapter())
                        {
                            Sqlda.SelectCommand = FCommand;
                            FCommand.CommandText = string.Format("select SystemLabel from tsSystemModule where theCode = '{0}'", ht[0]);
                            object sender = FCommand.ExecuteScalar();
                            haResult.Add(0, sender);
                        }
                        #endregion
                        break;
                    case 5:
                        #region 保存导入参数
                        FCommand.CommandText = string.Format("update tsSystemModule set SystemLabel=@SystemLabel where TheCode='{0}'", ht[0]);
                        FCommand.Parameters.Add("@SystemLabel", SqlDbType.Image);
                        FCommand.Parameters["@SystemLabel"].Value = ht[1];
                        FCommand.ExecuteNonQuery();
                        #endregion
                        break;
                }
            }
            finally
            {
                Connect.Close();
            }
            return haResult;
        }
    }
}
