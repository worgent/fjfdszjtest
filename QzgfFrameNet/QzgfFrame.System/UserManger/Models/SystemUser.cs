/*

 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能

*/

using System;

namespace QzgfFrame.System.UserManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SystemUser
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _username;
        private string _nickname;
        private string _tel;
        private string _email;
        private string _password;
        private string _departmentid;
        private string _departmentname;
        private string _areaid;
        private string _areaname;
        private string _isrepair;
        private string _remark;
        private string _createman;
        private DateTime _createdate;
        private string _state;
        private string _statename;
        private string _roleids;
        private string _rolenames;
        private string _levelno;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SystemUser()
        {
            _id = String.Empty;
            _username = String.Empty;
            _nickname = String.Empty;
            _tel = String.Empty;
            _email = String.Empty;
            _password = String.Empty;
            _departmentid = String.Empty;
            _areaid = String.Empty;
            _isrepair = String.Empty;
            _remark = String.Empty;
            _createman = String.Empty;
            _createdate = DateTime.MinValue;
            _departmentname = String.Empty;
            _state = String.Empty;
            _levelno = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SystemUser(string id, string username, string nickname, string tel, string email, string password, string departmentid, string remark, string createman, DateTime createdate, string state)
        {
            _id = id;
            _username = username;
            _nickname = nickname;
            _tel = tel;
            _email = email;
            _password = password;
            _departmentid = departmentid;
            _remark = remark;
            _createman = createman;
            _createdate = createdate;
            _state = state;
        }
        /// <summary>
        /// 列表信息专用
        /// </summary>
        /// <param name="id"></param>
        /// <param name="username"></param>
        /// <param name="tel"></param>
        /// <param name="email"></param>
        /// <param name="password"></param>
        /// <param name="departmentid"></param>
        /// <param name="departmentname"></param>
        /// <param name="remark"></param>
        /// <param name="createman"></param>
        /// <param name="createdate"></param>
        /// <param name="state"></param>
        /// <param name="statename"></param>
        public SystemUser(string id, string username, string nickname, string tel, string email, string password,
            string departmentid,string areaid, string remark, string state, string statename)
        {
            _id = id;
            _username = username;
            _nickname = nickname;
            _tel = tel;
            _email = email;
            _password = password;
            _departmentid = departmentid;
            _areaid = areaid;
            _remark = remark;
            _state = state;
            _statename = statename;
        }

        #endregion // End Full Constructor

        #region Public Properties

        /// <summary>
        /// 主键
        /// </summary>		
        public string Id
        {
            get { return _id; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());

                _isChanged |= (_id != value); _id = value;
            }
        }

        /// <summary>
        /// 用户名
        /// </summary>		
        public string Username
        {
            get { return _username; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Username", value, value.ToString());

                _isChanged |= (_username != value); _username = value;
            }
        }

        public string Nickname
        {
            get { return _nickname; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Nickname", value, value.ToString());

                _isChanged |= (_nickname != value); _nickname = value;
            }
        }
        /// <summary>
        /// 电话
        /// </summary>		
        public string Tel
        {
            get { return _tel; }
            set
            {
                if (value != null)
                    if (value.Length > 50)
                        throw new ArgumentOutOfRangeException("Invalid value for Tel", value, value.ToString());

                _isChanged |= (_tel != value); _tel = value;
            }
        }

        /// <summary>
        /// 邮箱
        /// </summary>		
        public string Email
        {
            get { return _email; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Email", value, value.ToString());

                _isChanged |= (_email != value); _email = value;
            }
        }

        /// <summary>
        /// 密码
        /// </summary>		
        public string Password
        {
            get { return _password; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Password", value, value.ToString());

                _isChanged |= (_password != value); _password = value;
            }
        }

        /// <summary>
        /// 部门id
        /// </summary>		
        public string Departmentid
        {
            get { return _departmentid; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Departmentid", value, value.ToString());

                _isChanged |= (_departmentid != value); _departmentid = value;
            }
        }
        /// <summary>
        /// 部门名称
        /// </summary>		
        public string Departmentname
        {
            get { return _departmentname; }
            set
            {

                _isChanged |= (_departmentname != value); _departmentname = value;
            }
        }
        /// <summary>
        /// 区域
        /// </summary>		
        public string Areaid
        {
            get { return _areaid; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for _areaid", value, value.ToString());

                _isChanged |= (_areaid != value); _areaid = value;
            }
        }
        /// <summary>
        /// 区域名称
        /// </summary>		
        public string Areaname
        {
            get { return _areaname; }
            set
            {

                _isChanged |= (_areaname != value); _areaname = value;
            }
        }
        /// <summary>
        /// 是否代维
        /// </summary>		
        public string Isrepair
        {
            get { return _isrepair; }
            set
            {
                if (value != null)
                    if (value.Length >2)
                        throw new ArgumentOutOfRangeException("Invalid value for _isrepair", value, value.ToString());

                _isChanged |= (_isrepair != value); _isrepair = value;
            }
        }
        /// <summary>
        /// 机构层号
        /// </summary>		
        public string LEVELNO
        {
            get { return _levelno; }
            set
            {
                if (value != null)
                    if (value.Length >2)
                        throw new ArgumentOutOfRangeException("Invalid value for LEVELNO", value, value.ToString());

                _isChanged |= (_levelno != value); _levelno = value;
            }
        }
        /// <summary>
        /// 备注
        /// </summary>		
        public string Remark
        {
            get { return _remark; }
            set
            {
                if (value != null)
                    if (value.Length > 200)
                        throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());

                _isChanged |= (_remark != value); _remark = value;
            }
        }

        /// <summary>
        /// 创建人编号
        /// </summary>		
        public string Createman
        {
            get { return _createman; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Createman", value, value.ToString());

                _isChanged |= (_createman != value); _createman = value;
            }
        }

        /// <summary>
        /// 创建时间
        /// </summary>		
        public DateTime Createdate
        {
            get { return _createdate; }
            set { _isChanged |= (_createdate != value); _createdate = value; }
        }

        /// <summary>
        /// 0:删除,1:正常,2.停用
        /// </summary>		
        public string State
        {
            get { return _state; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for State", value, value.ToString());

                _isChanged |= (_state != value); _state = value;
            }
        }

        public string Roleids
        {
            get { return _roleids; }
            set
            {
                _isChanged |= (_roleids != value); _roleids = value;
            }
        }

        public string Rolenames
        {
            get { return _rolenames; }
            set
            {
                _isChanged |= (_rolenames != value); _rolenames = value;
            }
        }

        /// <summary>
        /// 0:删除,1:正常,2.停用
        /// </summary>		
        public string Statename
        {
            get { return _statename; }
            set
            {

                _isChanged |= (_statename != value); _statename = value;
            }
        }

        /// <summary>
        /// Returns whether or not the object has changed it's values.
        /// </summary>
        public bool IsChanged
        {
            get { return _isChanged; }
        }

        /// <summary>
        /// Returns whether or not the object has changed it's values.
        /// </summary>
        public bool IsDeleted
        {
            get { return _isDeleted; }
        }

        #endregion

        #region Public Functions

        /// <summary>
        /// mark the item as deleted
        /// </summary>
        public void MarkAsDeleted()
        {
            _isDeleted = true;
            _isChanged = true;
        }

        #endregion


    }
}