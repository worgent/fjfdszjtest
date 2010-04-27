using System;
using System.ComponentModel;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Text;
using XzzLibrary.Source;
using System.IO;
using System.Data.SqlClient;
using System.Data;
using System.Net;
using System.Net.Sockets;
using System.Collections;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;
using XzzLibrary.Source.Dialog;

namespace xzMesosphere.Source
{
    public class LoginServer
    {
        public static void Login(xzServerManage FServerManage)
        {
            string sql = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            //注册功能模块的远程服务
            SvrSystemServer.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemServer), "ISvrSystem");              
            //注册数据处理的远程服务
            SvrSystemDB.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemDB), "ISvrObjectDB");                
            //注册报表处理的远程服务
            SvrReport.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrReport), "ISvrReport");                    
            //注册权限处理的远程服务
            SvrPopedomEx.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrPopedomEx), "ISvrPopedomEx");              
            //注册消息处理的远程服务
            SvrSystemMessage.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrSystemMessage), "ISvrSystemMessage");      
            //注册考勤管理的远程服务
            SvrAttendInterface.ConnectStr = sql;
            FServerManage.Server.StartupTCPServer(typeof(SvrAttendInterface), "ISvrAttendInterface");  
        }
    }

    /// <summary>
    /// 系统服务
    /// </summary>
    public class SvrSystemServer : SvrSystem, IServerSkedUser, IServerSkedUpgrade
        , IServerSkedLable, IServerSkedIndividuationParam, IServerSkedLinkParam, IServerSkedSqlAccount
    {
        #region IServerSkedSystem
        /// <summary>
        /// 获取标题
        /// </summary>
        public override string GetTitle(xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    string s = string.Format("select SystemTitle from tsSystem where TheCode='{0}'", SystemGuble._PlacelNo);
                    SqlCommand cmd = new SqlCommand(s, Connect);
                    object objTitle = cmd.ExecuteScalar();
                    if (objTitle != null && objTitle.ToString() != "") return objTitle.ToString();
                    else return GoubleConst._DefaultTitle;
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetTitle(RemotingParam);
        }

        /// <summary>
        /// 获取企业信息
        /// </summary>
        public override xzEnterpriseInfo GetEnterpriseInfo(xzRemotingParam RemotingParam)
        {
            xzEnterpriseInfo Finfo = base.GetEnterpriseInfo(RemotingParam);
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    SqlCommand cmd = new SqlCommand(string.Format(@"Select TheName,ShortName,StartTime,ReckonTime 
                        from tsSystem where TheCode = '{0}'", SystemGuble._PlacelNo), Connect);
                    SqlDataReader Reader = cmd.ExecuteReader();
                    if (Reader.Read())
                    {
                        Finfo.TheName = Reader["TheName"].ToString();
                        Finfo.ShortName = Reader["ShortName"].ToString();
                        if (Reader["StartTime"] == DBNull.Value) Finfo.StartTime = Convert.ToDateTime("2007-1-1");
                        else Finfo.StartTime = Convert.ToDateTime(Reader["StartTime"]);
                    }
                }
                finally
                {
                    Connect.Close();
                }
            }
            return Finfo;
        }

        /// <summary>
        /// 获取结帐日期
        /// </summary>
        public override DateTime GetReckonTime(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SvrGoubleParam FSvrGoubleParam = new SvrGoubleParam();
                FSvrGoubleParam.Command = new SqlCommand();
                FSvrGoubleParam.Command.Connection = Connect;
                return FSvrGoubleParam.getReckonTime();
            }
            finally
            {
                Connect.Close();
            }
        }

        /// <summary>
        /// 获取成本核算期间
        /// </summary>
        public override byte[] GetFinCosting(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format(@"Select FinCosting from tsSystem where TheCode = '{0}'", SystemGuble._PlacelNo)
                    , Connect);
                object sender = cmd.ExecuteScalar();
                if (sender != null && sender != DBNull.Value) return sender as byte[];
                else return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public override void SetFinCosting(byte[] bytes, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                using (SqlCommand cmd = new SqlCommand())
                {
                    cmd.Connection = Connect;
                    string sql = @"update tsSystem set FinCosting=@FinCosting where TheCode = '{0}'";
                    cmd.CommandText = string.Format(sql, SystemGuble._PlacelNo);
                    cmd.Parameters.Add("@FinCosting", SqlDbType.Image);
                    cmd.Parameters["@FinCosting"].Value = bytes;
                    cmd.ExecuteNonQuery();
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        /// <summary>
        /// 获取财务结帐期间
        /// </summary>
        public override byte[] GetFinReckoning(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format(@"Select FinReckoning from tsSystem where TheCode = '{0}'", SystemGuble._PlacelNo)
                    , Connect);
                object sender = cmd.ExecuteScalar();
                if (sender != null && sender != DBNull.Value) return sender as byte[];
                else return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public override void SetFinReckoning(byte[] bytes, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                using (SqlCommand cmd = new SqlCommand())
                {
                    cmd.Connection = Connect;                    
                    string sql = @"update tsSystem set FinReckoning=@FinReckoning where TheCode = '{0}'";

                    cmd.CommandText = string.Format(sql, SystemGuble._PlacelNo);
                    cmd.Parameters.Add("@FinReckoning", SqlDbType.Image);
                    cmd.Parameters["@FinReckoning"].Value = bytes;
                    cmd.ExecuteNonQuery();
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        /// <summary>
        /// 获取扩展数据清单流
        /// </summary>
        public override byte[] GetExtendDataStream(string FileName, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    return SystemModule.LoadExtendDataStream(Connect);
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetExtendDataStream(FileName, RemotingParam);
        }

        /// <summary>
        /// 获取模块清单流
        /// </summary>
        public override byte[] GetModuleStream(string FileName, xzRemotingParam RemotingParam)
        {            
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    return SystemModule.LoadModuleStream(Connect);
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetModuleStream(FileName, RemotingParam);
        }

        /// <summary>
        /// 获取报表清单流
        /// </summary>
        public override byte[] GetReportStream(string FileName, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    return SystemModule.LoadReportStream(Connect);
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetModuleStream(FileName, RemotingParam);
        }

        /// <summary>
        /// 获取扩展权限清单流
        /// </summary>
        public override byte[] GetPopedomExStream(string FileName, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    return SystemModule.LoadPopedomExStream(Connect);
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetModuleStream(FileName, RemotingParam);
        }
        #endregion

        #region IServerSkedLable 标签效果的实现
        public void Save(byte[] b_byte, string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format("update tsSystemModule set SystemLabel=@SystemLabel where TheCode='{0}'"
                    , TheCode), Connect);
                cmd.Parameters.Add("@SystemLabel", SqlDbType.Image);
                cmd.Parameters["@SystemLabel"].Value = b_byte;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public byte[] Load(string TheCode, xzRemotingParam RemotingParam)
        {
            byte[] b_byte = null;
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format("select SystemLabel from tsSystemModule where TheCode='{0}'", TheCode), Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null)
                {
                    b_byte = objStream as byte[];
                }
                return b_byte;
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedUser 接口的实现方式

        public bool isSystemUser(string User, string PassWord, xzRemotingParam RemotingParam)
        {
            return User == PopedomInfo._UserNo && PassWord == PopedomInfo._PassWord;
        }

        public xzLoginInfo Login(string User, string PassWord, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            
            Connect.Open();
            try
            {
                string StrSql = string.Format(
                    @"select a.TheCode, a.TheName, a.PassWord, a.EmployeeNo, a.GroupNo , b.TheName EmployeeName
                                   from tsSystemGroupUser a
                                Left Outer Join tbDatumEmployee b on a.EmployeeNo = b.TheCode 
                                where a.TheCode = '{0}' ", User);

                SqlCommand command = new SqlCommand(StrSql, Connect);
                using (SqlDataReader Reader = command.ExecuteReader())
                {
                    if (Reader.Read())
                    {
                        if (Reader["TheCode"].ToString() != User) return null;
                        string readPassWord = Reader["PassWord"].ToString();
                        if ((new xzStrPassword(readPassWord)).UnchainPassword() != PassWord)
                        {
                            Reader.Close();
                            return null;
                        }
                        xzLoginInfo FLoginInfo = new xzLoginInfo();
                        FLoginInfo.LoginUser = Reader["TheCode"].ToString();
                        FLoginInfo.LoginUserName = Reader["TheName"].ToString();
                        FLoginInfo.LoginEmployeeNo = Reader["EmployeeNo"].ToString();
                        FLoginInfo.LoginEmployeeName = Reader["EmployeeName"].ToString();
                        Reader.Close();
                        return FLoginInfo;
                    }
                    else
                    {
                        Reader.Close();
                        return null;
                    }
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        public bool UpdatePassWord(string User, string PassWord, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_cmd = string.Format("update tsSystemGroupUser set PassWord = '{0}' where TheCode = '{1}' "
                   , (new xzStrPassword(PassWord)).EncryptPassword(), User);
                SqlCommand command = new SqlCommand(s_cmd, Connect);
                command.ExecuteNonQuery();
                return true;
            }
            finally
            {
                Connect.Close();
            }
        }

        public bool RegisterLoginInfo(xzLoginInfo LoginInfo, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_cmd = string.Format("update tsSystemGroupUser set IPAddress = '{0}', Port = {1} where TheCode = '{2}' ",
                    LoginInfo.IPAddress, LoginInfo.Port, LoginInfo.LoginUser);
                SqlCommand command = new SqlCommand(s_cmd, Connect);
                command.ExecuteNonQuery();
                return true;
            }
            finally
            {
                Connect.Close();
            }
        }

        public DataTable LoadUserGroup(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                using (SqlDataAdapter SqlDA = new SqlDataAdapter())
                {
                    DataTable Table = new DataTable();
                    string s_sql = @"select a.TheCode,a.TheName,a.GroupNo,b.TheName GroupName 
                          from tsSystemGroupUser a
                        Left Outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                        where dbo.GetByteToBoolean(a.TheState,0x0002)=0
                        Order by a.GroupNo,a.TheCode";
                    SqlCommand cmd = new SqlCommand(s_sql, Connect);
                    SqlDA.SelectCommand = cmd;
                    SqlDA.Fill(Table);
                    return Table;
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        public DataTable LoadUserList(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                using (SqlDataAdapter SqlDA = new SqlDataAdapter())
                {
                    DataTable Table = new DataTable();
                    string s_sql = @"select TheCode,TheName,IPAddress from tsSystemGroupUser";
                    SqlCommand cmd = new SqlCommand(s_sql, Connect);
                    SqlDA.SelectCommand = cmd;
                    SqlDA.Fill(Table);
                    return Table;
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        #region 模块
        public void SavePopedom(byte[] b_byte, string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql ;
                if (isUser) s_Sql = string.Format("update tsSystemGroupUser set Popedom=@Popedom where TheCode='{0}'", TheCode);
                else s_Sql  = string.Format("update tsSystemGroup set Popedom=@Popedom where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.Parameters.Add("@Popedom", SqlDbType.Image);
                cmd.Parameters["@Popedom"].Value = b_byte;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public byte[] LoadPopedom(string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql;
                if (isUser) s_Sql = string.Format("select Popedom from tsSystemGroupUser where TheCode='{0}'", TheCode);
                else s_Sql = string.Format("select Popedom from tsSystemGroup where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void InheritGroup(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql =@"update tsSystemGroupUser set Popedom=b.Popedom
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where a.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void AssignUser(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set Popedom=b.Popedom
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where b.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region 模块报表
        public void SavePopedomReport(byte[] b_byte, string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql;
                if (isUser) s_Sql = string.Format("update tsSystemGroupUser set PopedomReport=@PopedomReport where TheCode='{0}'", TheCode);
                else s_Sql = string.Format("update tsSystemGroup set PopedomReport=@PopedomReport where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.Parameters.Add("@PopedomReport", SqlDbType.Image);
                cmd.Parameters["@PopedomReport"].Value = b_byte;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public byte[] LoadPopedomReport(string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql;
                if (isUser) s_Sql = string.Format("select PopedomReport from tsSystemGroupUser where TheCode='{0}'", TheCode);
                else s_Sql = string.Format("select PopedomReport from tsSystemGroup where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream!=DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void InheritGroupReport(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set PopedomReport=b.PopedomReport
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where a.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void AssignUserReport(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set PopedomReport=b.PopedomReport
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where b.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region 扩展权限
        public void SavePopedomEx(byte[] b_byte, string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql;
                if (isUser) s_Sql = string.Format("update tsSystemGroupUser set PopedomEx=@PopedomEx where TheCode='{0}'", TheCode);
                else s_Sql = string.Format("update tsSystemGroup set PopedomEx=@PopedomEx where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.Parameters.Add("@PopedomEx", SqlDbType.Image);
                cmd.Parameters["@PopedomEx"].Value = b_byte;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public byte[] LoadPopedomEx(string TheCode, bool isUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql;
                if (isUser) s_Sql = string.Format("select PopedomEx from tsSystemGroupUser where TheCode='{0}'", TheCode);
                else s_Sql = string.Format("select PopedomEx from tsSystemGroup where TheCode='{0}'", TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void InheritGroupPopedomEx(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set PopedomEx=b.PopedomEx
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where a.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void AssignUserPopedomEx(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set PopedomEx=b.PopedomEx
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where b.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region 模块自定义参数
        public byte[] LoadModuleCustom(string TheCode, bool isSystemUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = string.Format("select SystemModuleCustom from tsSystem where TheCode = '{0}'", SystemGuble._PlacelNo);
                else s = "select SystemModuleCustom from tsSystemGroupUser where TheCode=@TheCode";
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = TheCode;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                    return objStream as byte[];
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveModuleCustom(string TheCode, byte[] bytes, bool isSystemUser, string ModuleName, xzRemotingParam RemotingParam)
        {
            xzSystemModuleCustom FSystemModuleCustom = new xzSystemModuleCustom();
            if (bytes != null) FSystemModuleCustom = xzSystemModuleCustomDeal.getSystemModuleCustom(bytes);
            Hashtable SystemModuleCustomArg = new Hashtable();
            byte[] Sourcebytes = LoadModuleCustom(TheCode, isSystemUser, RemotingParam);
            if (Sourcebytes != null) SystemModuleCustomArg = xzSystemModuleCustomDeal.getSystemModuleCustomArg(Sourcebytes);
            if (SystemModuleCustomArg.ContainsKey(ModuleName)) SystemModuleCustomArg[ModuleName] = FSystemModuleCustom;
            else SystemModuleCustomArg.Add(ModuleName, FSystemModuleCustom);

            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = string.Format("update tsSystem set SystemModuleCustom=@SystemModuleCustom where TheCode = '{0}'", SystemGuble._PlacelNo);
                else s = "update tsSystemGroupUser set SystemModuleCustom=@SystemModuleCustom where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = TheCode;
                cmd.Parameters.Add("@SystemModuleCustom", SqlDbType.Image);
                cmd.Parameters["@SystemModuleCustom"].Value = xzSystemModuleCustomDeal.getSystemModuleCustomArgStream(SystemModuleCustomArg);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion
        #endregion

        #region IServerSkedUpgrade 接口的实现方式
        public override int GetUpgradeNo
        {
            get
            {
                if (SystemGuble.isFormDB)
                {
                    Connect.Open();
                    try
                    {
                        string s = string.Format("select SystemUpgradeNo from tsSystem where TheCode='{0}'", SystemGuble._PlacelNo);
                        SqlCommand cmd = new SqlCommand(s, Connect);
                        object objUpgradeNo = cmd.ExecuteScalar();
                        if (objUpgradeNo != null) return (int)objUpgradeNo;
                        else return GoubleConst._DefaultUpgradeNo;
                    }
                    finally
                    {
                        Connect.Close();
                    }
                }
                else return base.GetUpgradeNo;
            }
        }

        private string GetUpgradeDir()
        {
            Connect.Open();
            try
            {
                string s = string.Format("select SystemUpgradeDir from tsSystem where TheCode='{0}'", SystemGuble._PlacelNo);
                SqlCommand cmd = new SqlCommand(s, Connect);
                s = cmd.ExecuteScalar().ToString();
                return s;
            }
            finally
            {
                Connect.Close();
            }
        }

        public Hashtable GetUpgradeDll()
        {
            Hashtable htfile = new Hashtable();
            string[] files = Directory.GetFiles(GetUpgradeDir());
            foreach (string fileName in files)
            {
                string s = xzConstMath.GetDirFileName(fileName);

                if (s.ToLower() != "sn.txt")
                {
                    FileStream fStream = new FileStream(fileName, FileMode.Open);
                    BinaryReader Reader = new BinaryReader(fStream);
                    int m = Convert.ToInt32(fStream.Length);
                    byte[] bytes = Reader.ReadBytes(m);

                    htfile.Add(s, bytes);

                    Reader.Close();
                    fStream.Close();
                }
            }
            return htfile;
        }

        public string GetUpgradeSn()
        {
            string s = "";
            string[] files = Directory.GetFiles(GetUpgradeDir(), "sn.txt");
            foreach (string fileName in files)
            {
                StreamReader Reader = File.OpenText(fileName);
                s = Reader.ReadToEnd();
                Reader.Close();
            }
            return s;
        }

        #endregion

        #region IServerSkedIndividuationParam 接口的实现方式
        public byte[] LoadIndividuationParam(string TheCode, bool isSystemUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = "select SystemIndividuation from tsSystem where TheCode=@TheCode";
                else s = "select SystemIndividuation from tsSystemGroupUser where TheCode=@TheCode";
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                if (isSystemUser) cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                else cmd.Parameters["@TheCode"].Value = TheCode;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveIndividuationParam(string TheCode, byte[] bytes, bool isSystemUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = "update tsSystem set SystemIndividuation=@SystemIndividuation where TheCode=@TheCode";
                else s = "update tsSystemGroupUser set SystemIndividuation=@SystemIndividuation where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemIndividuation", SqlDbType.Image);
                if (isSystemUser) cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                else cmd.Parameters["@TheCode"].Value = TheCode;
                cmd.Parameters["@SystemIndividuation"].Value = bytes;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public byte[] LoadBetimesParam(string TheCode, bool isSystemUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = "select SystemBetimes from tsSystem where TheCode=@TheCode";
                else s = "select SystemBetimes from tsSystemGroupUser where TheCode=@TheCode";
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                if (isSystemUser) cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                else cmd.Parameters["@TheCode"].Value = TheCode;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream!=DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveBetimesParam(string TheCode, byte[] bytes, bool isSystemUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                if (isSystemUser) s = "update tsSystem set SystemBetimes=@SystemBetimes where TheCode=@TheCode";
                else s = "update tsSystemGroupUser set SystemBetimes=@SystemBetimes where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemBetimes", SqlDbType.Image);
                if (isSystemUser) cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                else cmd.Parameters["@TheCode"].Value = TheCode;

                cmd.Parameters["@SystemBetimes"].Value = bytes;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedLinkParam 接口的实现方式
        public byte[] LoadLinkParam(string TheCode, xzLinkUser LinkUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                switch (LinkUser)
                {
                    case xzLinkUser.System:
                        s = "select SystemLinks from tsSystem where TheCode=@TheCode";
                        break;
                    case xzLinkUser.Group:
                        s = "select SystemLinks from tsSystemGroup where TheCode=@TheCode";
                        break;
                    case xzLinkUser.User:
                        s = "select SystemLinks from tsSystemGroupUser where TheCode=@TheCode";
                        break;
                }
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                switch (LinkUser)
                {
                    case xzLinkUser.System:
                        cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                        break;
                    case xzLinkUser.Group:
                    case xzLinkUser.User:
                        cmd.Parameters["@TheCode"].Value = TheCode;
                        break;
                }
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                    return objStream as byte[];
                return null;
                //return SystemCallParam.LoadParam(Connect, TheCode, isSystemUser);
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveLinkParam(string TheCode, byte[] bytes, xzLinkUser LinkUser, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "";
                switch (LinkUser)
                {
                    case xzLinkUser.System:
                        s = "update tsSystem set SystemLinks=@SystemLinks where TheCode=@TheCode";
                        break;
                    case xzLinkUser.Group:
                        s = "update tsSystemGroup set SystemLinks=@SystemLinks where TheCode=@TheCode";
                        break;
                    case xzLinkUser.User:
                        s = "update tsSystemGroupUser set SystemLinks=@SystemLinks where TheCode=@TheCode";
                        break;
                }


                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                switch (LinkUser)
                {
                    case xzLinkUser.System:
                        cmd.Parameters["@TheCode"].Value = SystemGuble._PlacelNo;
                        break;
                    case xzLinkUser.Group:
                    case xzLinkUser.User:
                        cmd.Parameters["@TheCode"].Value = TheCode;
                        break;
                }
                cmd.Parameters.Add("@SystemLinks", SqlDbType.Image);
                cmd.Parameters["@SystemLinks"].Value = bytes;
                cmd.ExecuteNonQuery();
                //SystemCallParam.SaveParam(Connect, TheCode, bytes, isSystemUser);
            }
            finally
            {
                Connect.Close();
            }
        }

        public void LinkInheritGroup(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set SystemLinks=b.SystemLinks
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where a.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public void LinkAssignUser(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s_Sql = @"update tsSystemGroupUser set SystemLinks=b.SystemLinks
                                    from tsSystemGroupUser a
                                Left outer Join tsSystemGroup b on a.GroupNo=b.TheCode
                                where b.theCode = '{0}'";
                s_Sql = string.Format(s_Sql, TheCode);

                SqlCommand cmd = new SqlCommand(s_Sql, Connect);
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        public int RegeditKey(string[] keys, string Notifys, xzRemotingParam RemotingParam)
        {
            if (keys==null || keys.Length != 3) return 0;
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format(@"select Count(*) from tsSystemKey where theCode = '{0}'", keys[0]), Connect);
                if (Convert.ToInt32(cmd.ExecuteScalar()) > 0) return 1;
                using (MemoryStream AStream = new MemoryStream())
                {
                    cmd = new SqlCommand(@"insert tsSystemKey (TheCode , TheKey, Notifys) values (@TheCode, @TheKey, @Notifys)", Connect);
                    cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 50);
                    cmd.Parameters["@TheCode"].Value = keys[0];

                    cmd.Parameters.Add("@TheKey", SqlDbType.Image);
                    BinaryFormatter FBinaryFormatter = new BinaryFormatter();
                    FBinaryFormatter.Serialize(AStream, keys);
                    cmd.Parameters["@TheKey"].Value = AStream.ToArray();

                    cmd.Parameters.Add("@Notifys", SqlDbType.VarChar, 200);
                    cmd.Parameters["@Notifys"].Value = Notifys;

                    AStream.Close();
                    cmd.ExecuteNonQuery();
                    return 2;
                }
            }
            finally
            {
                Connect.Close();
            }
        }

        public int ValidateKey(string[] keys, xzRemotingParam RemotingParam)
        {
            if (keys == null || keys.Length != 3) return 0;
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format(@"select TheKey  from tsSystemKey
                   where theCode = '{0}' and dbo.GetByteToBoolean(theState,0x0001)=1", keys[0]), Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    using (MemoryStream AStream = new MemoryStream(objStream as byte[]))
                    {
                        BinaryFormatter FBinaryFormatter = new BinaryFormatter();
                        string[] d_keys = FBinaryFormatter.Deserialize(AStream) as string[];
                        AStream.Close();
                        if (d_keys[0] == keys[0] && d_keys[1] == keys[1] && d_keys[2] == keys[2]) return 2;
                    }
                }
                return 1;
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedSqlAccount 接口的实现方式
        public byte[] LoadSqlAccount(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format("select SystemSqlAccount from tsSystem where TheCode='{0}'", SystemGuble._PlacelNo)
                    , Connect);
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                    return objStream as byte[];
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveSqlAccount(byte[] bytes, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(string.Format("update tsSystem set SystemSqlAccount=@SystemSqlAccount where TheCode='{0}'", SystemGuble._PlacelNo)
                    , Connect);
                cmd.Parameters.Add("@SystemSqlAccount", SqlDbType.Image);
                cmd.Parameters["@SystemSqlAccount"].Value = bytes;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion
    }

    /// <summary>
    /// 数据操作
    /// </summary>
    public class SvrSystemDB : SvrObjectDB, IServerSkedSystemModuleParam, IServerSkedPrint, IServerSkedFlow
    {
        public override xzTableViewItem[] GetTableParamStream(string Identify, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString!="") Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    xzTableViewDeal TableViewDeal = new xzTableViewDeal();                     
                    xzTableViewItem[] TableViewItems = TableViewDeal.LoadStream(SystemModuleParam.LoadParamStream(Connect, Identify));
                    return TableViewItems;
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetTableParamStream(Identify, RemotingParam);
        }

        public override Object GetTableParams(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                return SystemModuleParam.LoadParam(Connect, Identify);
            }
            finally
            {
                Connect.Close();
            }
        }

        #region IServerSkedSystemModuleParam 接口的实现方式
        public byte[] LoadSystemModuleParam(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = string.Format("select SystemModuleParam from tsSystemModule where TheCode=@TheCode");
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = TheCode;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveSystemModuleParam(string TheCode, byte[] bytes, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "update tsSystemModule set SystemModuleParam=@SystemModuleParam where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemModuleParam", SqlDbType.Image);
                cmd.Parameters["@TheCode"].Value = TheCode;
                cmd.Parameters["@SystemModuleParam"].Value = bytes;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedPrint 接口的实现方式

        public byte[] LoadPrint(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "select SystemPrint from tsSystemModule where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = Identify;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                    return objStream as byte[];
                return null;

                //object o = cmd.ExecuteScalar();
                //if (o != null && o != DBNull.Value) return o as byte[];
                //else return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SavePrint(byte[] b, string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "update tsSystemModule set SystemPrint=@SystemPrint where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemPrint", SqlDbType.Image);
                cmd.Parameters["@TheCode"].Value = Identify;
                cmd.Parameters["@SystemPrint"].Value = b;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }

        #endregion

        #region IServerSkedFlow 接口的实现方式
        protected virtual void ExecUpdateFlow(DataTable Table, int FlowBit, bool value, SqlTransaction sqlTran)
        {
        }

        protected virtual void ExecUpdateFlowDepose(DataTable Table, int FlowBit, bool value, SqlTransaction sqlTran)
        {
        }

        public virtual void UpdateFlow(DataTable Table, Hashtable htFlowParam, int Mode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            SqlTransaction sqlTran = Connect.BeginTransaction();
            try
            {
                //DataRow row = Table.Rows[0];
                int i = 1;
                foreach (DataRow row in Table.Rows)
                {
                    string s_cmd = "update {0} set FlowBit = dbo.SetByteToBoolean(FlowBit,@FlowBit,{1}), FlowChart = @FlowChart where {2}"
                        , s_cause = "", s_value = "0";

                    xzServerSkedFlowParam FServerSkedFlowParam = htFlowParam[i] as xzServerSkedFlowParam;
                    //bool value = FServerSkedFlowParam.FlowValue;

                    if (FServerSkedFlowParam.FlowValue) s_value = "1";
                    foreach (DataColumn Column in Table.PrimaryKey)
                    {
                        if (s_cause == "") s_cause = string.Format("{0} = {1}", Column.ColumnName
                            , xzSQLDbTypeTransform.GetSqlDbValue(Column.DataType, row[Column.ColumnName].ToString()));
                        else s_cause = s_cause + string.Format("and {0} = {1}", Column.ColumnName
                            , xzSQLDbTypeTransform.GetSqlDbValue(Column.DataType, row[Column.ColumnName].ToString()));
                    }

                    s_cmd = string.Format(s_cmd, Table.TableName, s_value, s_cause);

                    switch (Mode)
                    {
                        case 0:
                            ExecUpdateFlow(Table, FServerSkedFlowParam.FlowBit, FServerSkedFlowParam.FlowValue, sqlTran);
                            break;
                        case 1:
                            ExecUpdateFlowDepose(Table, FServerSkedFlowParam.FlowBit, FServerSkedFlowParam.FlowValue, sqlTran);
                            break;
                    }

                    SqlCommand cmd = new SqlCommand(s_cmd, Connect);
                    cmd.Transaction = sqlTran;
                    cmd.Parameters.Add("@FlowBit", SqlDbType.Int);
                    cmd.Parameters.Add("@FlowChart", SqlDbType.Image);

                    cmd.Parameters["@FlowBit"].Value = FServerSkedFlowParam.FlowBit;
                    cmd.Parameters["@FlowChart"].Value = row["FlowChart"];
                    cmd.ExecuteNonQuery();
                    i++;
                }
                sqlTran.Commit();
            }
            catch
            {
                sqlTran.Rollback();
                throw;
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion
    }

    /// <summary>
    /// 报表
    /// </summary>
    public class SvrReport : SvrReportBase, IServerSkedSystemModuleParam, IServerSkedReport, IServerSkedPrint, IServerSkedFlow 
    {
        public override xzTableViewItem[] GetTableParamStream(string Identify, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "") Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {  // TableViewDeal.LoadStream(SystemReportParam.LoadParamStream(Connect, Identify));
                    xzTableViewDeal TableViewDeal = new xzTableViewDeal();
                    xzTableViewItem[] TableViewItems = TableViewDeal.LoadStream(SystemReportParam.LoadParamStream(Connect, Identify));
                    return TableViewItems;
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetTableParamStream(Identify, RemotingParam);
        }

        public override Object GetTableParams(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                return SystemReportParam.LoadParam(Connect, Identify);
            }
            finally
            {
                Connect.Close();
            }
        }

        #region IServerSkedReport 接口的实现方式
        public byte[] LoadUIStream(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                return SystemReportParam.LoadUIStream(Connect, Identify);
            }
            finally
            {
                Connect.Close();
            }
        }

        public virtual DataTable GetDataTable(Hashtable htParameters, string command, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            DataTable Table = new DataTable();
            Connect.Open();            
            try
            {
                using (SqlDataAdapter SqlDA = new SqlDataAdapter())
                {
                    SqlCommand cmd = new SqlCommand(command, Connect);
                    cmd.CommandTimeout = 600;
                    if (htParameters != null)
                    {
                        foreach (xzSqlParameter sp in htParameters.Values)
                        {
                            SqlParameter spItem = new SqlParameter("@" + sp.Name, sp.DbType, sp.Size);
                            spItem.Value = sp.Value;
                            cmd.Parameters.Add(spItem);
                        }
                    }
                    SqlDA.SelectCommand = cmd;
                    SqlDA.Fill(Table);
                }
            }
            finally
            {
                Connect.Close();
            }
            return Table;
        }
        #endregion

        #region IServerSkedSystemModuleParam 接口的实现方式
        public byte[] LoadSystemModuleParam(string TheCode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = string.Format("select SystemModuleParam from tsSystemReport where TheCode=@TheCode");
                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = TheCode;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SaveSystemModuleParam(string TheCode, byte[] bytes, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "update tsSystemReport set SystemModuleParam=@SystemModuleParam where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemModuleParam", SqlDbType.Image);
                cmd.Parameters["@TheCode"].Value = TheCode;
                cmd.Parameters["@SystemModuleParam"].Value = bytes;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedPrint 接口的实现方式
        public byte[] LoadPrint(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "select SystemPrint from tsSystemReport where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters["@TheCode"].Value = Identify;
                object objStream = cmd.ExecuteScalar();
                if (objStream != null && objStream != DBNull.Value)
                {
                    return objStream as byte[];
                }
                return null;
            }
            finally
            {
                Connect.Close();
            }
        }

        public void SavePrint(byte[] b, string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                string s = "update tsSystemReport set SystemPrint=@SystemPrint where TheCode=@TheCode";

                SqlCommand cmd = new SqlCommand(s, Connect);
                cmd.Parameters.Add("@TheCode", SqlDbType.VarChar, 30);
                cmd.Parameters.Add("@SystemPrint", SqlDbType.Image);
                cmd.Parameters["@TheCode"].Value = Identify;
                cmd.Parameters["@SystemPrint"].Value = b;
                cmd.ExecuteNonQuery();
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion

        #region IServerSkedFlow 接口的实现方式
        protected virtual void ExecUpdateFlow(DataTable Table, int FlowBit, bool value, SqlTransaction sqlTran)
        {
        }

        protected virtual void ExecUpdateFlowDepose(DataTable Table, int FlowBit, bool value, SqlTransaction sqlTran)
        {
        }

        public virtual void UpdateFlow(DataTable Table, Hashtable htFlowParam, int Mode, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            SqlTransaction sqlTran = Connect.BeginTransaction();
            try
            {
                //DataRow row = Table.Rows[0];
                int i = 1;
                foreach (DataRow row in Table.Rows)
                {
                    string s_cmd = "update {0} set FlowBit = dbo.SetByteToBoolean(FlowBit,@FlowBit,{1}), FlowChart = @FlowChart where {2}"
                        , s_cause = "", s_value = "0";

                    xzServerSkedFlowParam FServerSkedFlowParam = htFlowParam[i] as xzServerSkedFlowParam;
                    //bool value = FServerSkedFlowParam.FlowValue;

                    if (FServerSkedFlowParam.FlowValue) s_value = "1";
                    foreach (DataColumn Column in Table.PrimaryKey)
                    {
                        if (s_cause == "") s_cause = string.Format("{0} = {1}", Column.ColumnName
                            , xzSQLDbTypeTransform.GetSqlDbValue(Column.DataType, row[Column.ColumnName].ToString()));
                        else s_cause = s_cause + string.Format("and {0} = {1}", Column.ColumnName
                            , xzSQLDbTypeTransform.GetSqlDbValue(Column.DataType, row[Column.ColumnName].ToString()));
                    }

                    s_cmd = string.Format(s_cmd, Table.TableName, s_value, s_cause);

                    switch (Mode)
                    {
                        case 0:
                            ExecUpdateFlow(Table, FServerSkedFlowParam.FlowBit, FServerSkedFlowParam.FlowValue, sqlTran);
                            break;
                        case 1:
                            ExecUpdateFlowDepose(Table, FServerSkedFlowParam.FlowBit, FServerSkedFlowParam.FlowValue, sqlTran);
                            break;
                    }

                    SqlCommand cmd = new SqlCommand(s_cmd, Connect);
                    cmd.Transaction = sqlTran;
                    cmd.Parameters.Add("@FlowBit", SqlDbType.Int);
                    cmd.Parameters.Add("@FlowChart", SqlDbType.Image);

                    cmd.Parameters["@FlowBit"].Value = FServerSkedFlowParam.FlowBit;
                    cmd.Parameters["@FlowChart"].Value = row["FlowChart"];
                    cmd.ExecuteNonQuery();
                    i++;
                }
                sqlTran.Commit();
            }
            catch
            {
                sqlTran.Rollback();
                throw;
            }
            finally
            {
                Connect.Close();
            }
        }
        #endregion
    }

    /// <summary>
    /// 扩展权限
    /// </summary>
    public class SvrPopedomEx : SvrPopedomExBase
    {
        public override xzTableViewItem[] GetTableParamStream(string Identify, xzRemotingParam RemotingParam)
        {
            if (SystemGuble.isFormDB)
            {
                if (RemotingParam.ConnectionString != "")
                    Connect.ConnectionString = RemotingParam.ConnectionString;
                Connect.Open();
                try
                {
                    xzTableViewDeal TableViewDeal = new xzTableViewDeal();
                    return TableViewDeal.LoadStream(SystemPopedomExParam.LoadParamStream(Connect, Identify));
                }
                finally
                {
                    Connect.Close();
                }
            }
            else return base.GetTableParamStream(Identify, RemotingParam);
        }

        public override Object GetTableParams(string Identify, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                return SystemPopedomExParam.LoadParamObject(Connect, Identify);
            }
            finally
            {
                Connect.Close();
            }
        }
    }

    /// <summary>
    /// 消息
    /// </summary>
    public class SvrSystemMessage : SvrSystemDB
    {
    }

    /// <summary>
    /// 考勤
    /// </summary>
    public class SvrAttendInterface : SvrSystemDB, IServerSkedAttend
    {
        #region IServerSkedAttend 接口的实现方式
        public DataSet GetAttendRule(String AttendanceNo, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                DataSet ResultDataSet = new DataSet();                
                #region 寻找考勤规则 0 基本考勤规则 1 员工考勤规则 2 部门考勤规则

                int i = 0;
                string sql_emp = @"select count(*) from tbDatumEmployee where dbo.GetByteToBoolean(TheState,0x0002)=0 and isUsing =1 and AttendanceNo='{0}'";
                sql_emp = string.Format(sql_emp, AttendanceNo);
                SqlCommand cmdEmp = new SqlCommand(sql_emp, Connect);
                object objItem = cmdEmp.ExecuteScalar();
                if ((int)objItem > 0)
                {
                    i = 1;
                }
                else
                {
                    string sql_dept = @"select count(*) from tbDatumDept a Left outer join tbDatumEmployee b on a.theCode=b.theDept 
                                         where dbo.GetByteToBoolean(a.TheState,0x0002)=0 and a.isUsing=1 and b.AttendanceNo='{0}'";
                    sql_dept = string.Format(sql_dept, AttendanceNo);
                    SqlCommand cmdDept = new SqlCommand(sql_dept, Connect);
                    object objItem_dept = cmdDept.ExecuteScalar();
                    if ((int)objItem_dept > 0) i = 2;
                }

                #endregion

                //基本考勤规则
                string s_format = "CAST(CAST({0} as float)-FLOOR(CAST({0} as float)) as datetime) {0}",  //转换成以1900-1-1 开始的日期格式
                    s_cmd = @"select top 1 {0},{1},isnull(isOvertime1,0) isOvertime1,{2},{3},isnull(isOvertime2,0) isOvertime2
                                          ,{4},{5},isnull(isOvertime3,0) isOvertime3,{6},{7},isnull(isOvertime4,0) isOvertime4
                                          ,{8},{9},isnull(isOvertime5,0) isOvertime5,{10},{11},isnull(isOvertime6,0) isOvertime6
                                          ,{12},{13},isnull(isOvertime7,0) isOvertime7,{14},{15},isnull(isOvertime8,0) isOvertime8
                                          ,isnull(isCard1,0) isCard1,isnull(isCard2,0) isCard2,isnull(isCard3,0) isCard3
                                          ,isnull(isCard4,0) isCard4,isnull(isCard5,0) isCard5
                                          ,isnull(isCard60,0) isCard60,isnull(isCard61,0) isCard61
                                          ,isnull(isCard70,0) isCard70,isnull(isCard71,0) isCard71
                                          ,TheCode
                              from tbEmpAttendanceRuleBasic where dbo.GetByteToBoolean(theState,0x0002)=0",

                    //员工考勤规则
                    s_emp = @"select top 1 {0},{1},isnull(isOvertime1,0) isOvertime1,{2},{3},isnull(isOvertime2,0) isOvertime2
                                          ,{4},{5},isnull(isOvertime3,0) isOvertime3,{6},{7},isnull(isOvertime4,0) isOvertime4
                                          ,{8},{9},isnull(isOvertime5,0) isOvertime5,{10},{11},isnull(isOvertime6,0) isOvertime6
                                          ,{12},{13},isnull(isOvertime7,0) isOvertime7,{14},{15},isnull(isOvertime8,0) isOvertime8 
                                          ,isnull(isCard1,0) isCard1,isnull(isCard2,0) isCard2,isnull(isCard3,0) isCard3
                                          ,isnull(isCard4,0) isCard4,isnull(isCard5,0) isCard5
                                          ,isnull(isCard60,0) isCard60,isnull(isCard61,0) isCard61
                                          ,isnull(isCard70,0) isCard70,isnull(isCard71,0) isCard71 
                                          ,TheCode
                              from tbDatumEmployee where dbo.GetByteToBoolean(theState,0x0002)=0 and isUsing = 1 and AttendanceNo = '{8}'",

                    //部门考勤规则
                    s_dept = @"select top 1 {0},{1},isnull(a.isOvertime1,0) isOvertime1,{2},{3},isnull(a.isOvertime2,0) isOvertime2
                                          ,{4},{5},isnull(a.isOvertime3,0) isOvertime3,{6},{7},isnull(a.isOvertime4,0) isOvertime4
                                          ,{8},{9},isnull(a.isOvertime5,0) isOvertime5,{10},{11},isnull(a.isOvertime6,0) isOvertime6
                                          ,{12},{13},isnull(a.isOvertime7,0) isOvertime7,{14},{15},isnull(a.isOvertime8,0) isOvertime8
                                          ,isnull(isCard1,0) isCard1,isnull(isCard2,0) isCard2,isnull(isCard3,0) isCard3
                                          ,isnull(isCard4,0) isCard4,isnull(isCard5,0) isCard5
                                          ,isnull(isCard60,0) isCard60,isnull(isCard61,0) isCard61
                                          ,isnull(isCard70,0) isCard70,isnull(isCard71,0) isCard71
                                          ,TheCode
                              from tbDatumDept a Left outer join tbDatumEmployee b on a.theCode=b.theDept 
                              where dbo.GetByteToBoolean(a.theState,0x0002)=0 and a.isUsing = 1 and b.AttendanceNo = '{8}'";

                DataTable Table = new DataTable();
                switch (i)
                {
                    case 0:
                        s_cmd = string.Format(s_cmd, 
                                  string.Format(s_format, "OnDuty1"), string.Format(s_format, "OffDuty1")
                                , string.Format(s_format, "OnDuty2"), string.Format(s_format, "OffDuty2")
                                , string.Format(s_format, "OnDuty3"), string.Format(s_format, "OffDuty3")
                                , string.Format(s_format, "OnDuty4"), string.Format(s_format, "OffDuty4")
                                , string.Format(s_format, "OnDuty5"), string.Format(s_format, "OffDuty5")
                                , string.Format(s_format, "OnDuty6"), string.Format(s_format, "OffDuty6")
                                , string.Format(s_format, "OnDuty7"), string.Format(s_format, "OffDuty7")
                                , string.Format(s_format, "OnDuty8"), string.Format(s_format, "OffDuty8"));
                        SqlCommand cmd_cmd = new SqlCommand(s_cmd, Connect);
                        SqlDataAdapter sqlda_Comd = new SqlDataAdapter();
                        sqlda_Comd.SelectCommand = cmd_cmd;
                        sqlda_Comd.Fill(Table);
                        Table.TableName = "tbRuleBasic";
                        ResultDataSet.Tables.Add(Table);

                        break;
                    case 1:
                        s_emp = string.Format(s_emp, 
                                  string.Format(s_format, "OnDuty1"), string.Format(s_format, "OffDuty1")
                                , string.Format(s_format, "OnDuty2"), string.Format(s_format, "OffDuty2")
                                , string.Format(s_format, "OnDuty3"), string.Format(s_format, "OffDuty3")
                                , string.Format(s_format, "OnDuty4"), string.Format(s_format, "OffDuty4")
                                , string.Format(s_format, "OnDuty5"), string.Format(s_format, "OffDuty5")
                                , string.Format(s_format, "OnDuty6"), string.Format(s_format, "OffDuty6")
                                , string.Format(s_format, "OnDuty7"), string.Format(s_format, "OffDuty7")
                                , string.Format(s_format, "OnDuty8"), string.Format(s_format, "OffDuty8"), AttendanceNo);

                        SqlCommand cmd_emp = new SqlCommand(s_emp, Connect);
                        SqlDataAdapter sqlda_Emp = new SqlDataAdapter();
                        sqlda_Emp.SelectCommand = cmd_emp;
                        sqlda_Emp.Fill(Table);
                        Table.TableName = "tbRuleBasic";
                        ResultDataSet.Tables.Add(Table);
                        
                        break;
                    case 2:
                        s_dept = string.Format(s_dept, 
                                  string.Format(s_format, "a.OnDuty1"), string.Format(s_format, "a.OffDuty1")
                                , string.Format(s_format, "a.OnDuty2"), string.Format(s_format, "a.OffDuty2")
                                , string.Format(s_format, "a.OnDuty3"), string.Format(s_format, "a.OffDuty3")
                                , string.Format(s_format, "a.OnDuty4"), string.Format(s_format, "a.OffDuty4")
                                , string.Format(s_format, "a.OnDuty5"), string.Format(s_format, "a.OffDuty5")
                                , string.Format(s_format, "a.OnDuty6"), string.Format(s_format, "a.OffDuty6")
                                , string.Format(s_format, "a.OnDuty7"), string.Format(s_format, "a.OffDuty7")
                                , string.Format(s_format, "a.OnDuty8"), string.Format(s_format, "a.OffDuty8"), AttendanceNo);

                        SqlCommand cmd_dept = new SqlCommand(s_dept, Connect);
                        SqlDataAdapter sqlda_Dept = new SqlDataAdapter();
                        sqlda_Dept.SelectCommand = cmd_dept;
                        sqlda_Dept.Fill(Table);
                        Table.TableName = "tbRuleBasic";
                        ResultDataSet.Tables.Add(Table);
                        
                        break;
                    default:
                        break;
                }
                return ResultDataSet;
            }
            finally
            {
                Connect.Close();
            }
        }

        public string[] GetEmployeeNo(String AttendanceNo, xzRemotingParam RemotingParam)
        {
            string[] strValues = new string[2] { "", ""};
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            try
            {
                //string s = "";
                string s_cmd = string.Format("select TheCode,theDept from tbDatumEmployee where AttendanceNo = '{0}'", AttendanceNo);
                SqlCommand cmd = new SqlCommand(s_cmd, Connect);
                SqlDataReader Reader = cmd.ExecuteReader();
                if (Reader.Read())
                {
                    strValues[0] = Reader[0].ToString();
                    strValues[1] = Reader[1].ToString();
                    //if (o != null && o != DBNull.Value)
                    //{
                    //    if (o != null) s = o.ToString(); else s = "";
                    //}
                }
                return strValues;
            }
            finally
            {
                Connect.Close();
            }
        }

        public int AttendOffset(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            int i = 30;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(@"select TheName from tsSystemSubjectRecord where SystemSubject = 
                      'HR' and SystemRecord = 'HR_Attendance' And SubjectAssistant = 'HR001'", Connect);
                object obj = cmd.ExecuteScalar();
                if (obj != null && obj!=DBNull.Value) i = Convert.ToInt32(obj);
            }
            finally
            {
                Connect.Close();
            }
            return i;
        }

        public int AttendEffect(xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            int i = 30;
            Connect.Open();
            try
            {
                SqlCommand cmd = new SqlCommand(@"select TheName from tsSystemSubjectRecord where SystemSubject = 
                      'HR' and SystemRecord = 'HR_Attendance' And SubjectAssistant = 'HR002'", Connect);
                object obj = cmd.ExecuteScalar();
                if (obj != null && obj != DBNull.Value) i = Convert.ToInt32(obj);
            }
            finally
            {
                Connect.Close();
            }
            return i;
        }

        public void UpdateData(xzSqlCauseDate CauseDate, DataTable Table, DataTable AttendTable, string Creater, xzRemotingParam RemotingParam)
        {
            if (RemotingParam.ConnectionString != "")
                Connect.ConnectionString = RemotingParam.ConnectionString;
            Connect.Open();
            //SqlTransaction SqlTran = Connect.BeginTransaction();
            try
            {
                
                string s_cmd = "delete tbEmpAttendInterface where TheDate >= '{0}' and TheDate <= '{1}'";
                SqlCommand cmd = new SqlCommand(string.Format(s_cmd, CauseDate.BeginDateTime, CauseDate.EndDateTime), Connect);

                //cmd.Transaction = SqlTran;

                cmd.ExecuteNonQuery();

                if (Table != null)
                {
                    s_cmd = @"insert into tbEmpAttendInterface (BillNo,TheCode,TheDate,OnDuty1,OffDuty1,OnDuty2,OffDuty2 
                             ,OnDuty3,OffDuty3,OnDuty4,OffDuty4,OnDuty5,OffDuty5,OnDuty6,OffDuty6,OnDuty7,OffDuty7,OnDuty8,OffDuty8,
                                LateTime,EarlyTime,OverTime,Creater,CreateTime, TheWeek,DeptNo) 
                         values ('{0}','{1}','{2}',{3},{4},{5},{6},{7},{8},{9},{10},{11},{12},{13},{14},{15},{16},{17},{18},{19}
                           ,{20},{21},'{22}',getdate(),{23},{24})";
                    foreach (DataRow row in Table.Rows)
                    {
                        if ((DateTime)row["TheDate"] >= CauseDate.BeginDateTime && (DateTime)row["TheDate"] <= CauseDate.EndDateTime)
                        {
                            string s_BillNo = GetBillNo("yyMMdd?????", row["theCode"].ToString() + "-", "tbEmpAttendInterface", "BillNo", Connect);
                            cmd.CommandText = string.Format(s_cmd, s_BillNo, row["theCode"], row["TheDate"]
                                , xzSqlAnalysis.GetNull(row["OnDuty1"]), xzSqlAnalysis.GetNull(row["OffDuty1"])
                                , xzSqlAnalysis.GetNull(row["OnDuty2"]), xzSqlAnalysis.GetNull(row["OffDuty2"])
                                , xzSqlAnalysis.GetNull(row["OnDuty3"]), xzSqlAnalysis.GetNull(row["OffDuty3"])
                                , xzSqlAnalysis.GetNull(row["OnDuty4"]), xzSqlAnalysis.GetNull(row["OffDuty4"])
                                , xzSqlAnalysis.GetNull(row["OnDuty5"]), xzSqlAnalysis.GetNull(row["OffDuty5"])
                                , xzSqlAnalysis.GetNull(row["OnDuty6"]), xzSqlAnalysis.GetNull(row["OffDuty6"])
                                , xzSqlAnalysis.GetNull(row["OnDuty7"]), xzSqlAnalysis.GetNull(row["OffDuty7"])
                                , xzSqlAnalysis.GetNull(row["OnDuty8"]), xzSqlAnalysis.GetNull(row["OffDuty8"])
                                , xzSqlAnalysis.GetNull(row["LateTime"]), xzSqlAnalysis.GetNull(row["EarlyTime"])
                                , xzSqlAnalysis.GetNull(row["OverTime"]), Creater, xzSqlAnalysis.GetNull(row["TheWeek"])
                                , xzSqlAnalysis.GetNull(row["DeptNo"]));
                            cmd.ExecuteNonQuery();
                        }
                    }
                }

                s_cmd = "delete tbEmpAttendInterfaceList where TheTime >= '{0}' and TheTime <= '{1}'";

                if (AttendTable != null)
                {
                    cmd.CommandText =string.Format(s_cmd, CauseDate.BeginDateTime, CauseDate.EndDateTime);
                    cmd.ExecuteNonQuery();
                    s_cmd = @"insert into tbEmpAttendInterfaceList (EnrollNo,TheTime,InOutMode,InOutName) values ('{0}','{1}',{2},{3})";

                    foreach (DataRow row in AttendTable.Rows)
                    {
                        if ((DateTime)row["TheTime"] >= CauseDate.BeginDateTime && (DateTime)row["TheTime"] <= CauseDate.EndDateTime)
                        {
                            try
                            {
                                cmd.CommandText = string.Format(s_cmd, row["EnrollNo"], row["TheTime"], row["InOutMode"]
                                    , xzSqlAnalysis.GetNull(row["InOutName"]));
                                cmd.ExecuteNonQuery();
                            }
                            catch
                            {
                                continue;
                            }
                        }
                    }
                }

                //SqlTran.Commit();
            }
            catch
            {
                //SqlTran.Rollback();
                throw;
            }
            finally
            {
                Connect.Close();
            }
        }

        #endregion
    }

    /// <summary>
    /// 一些全局的参数
    /// </summary>
    public class SvrGoubleParam
    {
        private SqlCommand FCommand = null;
        [Description("命令"), Category("Xzz")]
        public SqlCommand Command
        {
            get
            {
                return FCommand;
            }
            set
            {
                if (FCommand != value)
                {
                    FCommand = value;
                }
            }
        }

        public DateTime getReckonTime()
        {
            //求出结账日期
            DateTime ReckonTime = Convert.ToDateTime("2007-01-01");
            FCommand.CommandText = string.Format("Select ReckonTime from tsSystem where TheCode = '{0}'", SystemGuble._PlacelNo);
            object objReckonTime = FCommand.ExecuteScalar();
            if (objReckonTime != null && objReckonTime != DBNull.Value)
                ReckonTime = Convert.ToDateTime(objReckonTime);
            return ReckonTime;
        }
    }
}
