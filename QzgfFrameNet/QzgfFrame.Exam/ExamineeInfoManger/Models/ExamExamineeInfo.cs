/*
 * 文件名.........: ExamExamineeInfo.cs
 * 作者...........:  
 * 说明...........: 考生信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.ExamineeInfoManger.Models
{
	/// <summary>
	/// 考生信息表
	/// </summary>
	[Serializable]
    public sealed class ExamExamineeInfo
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _fullname;
        private string _loginname;
        private string _password;
        private short _sex;
        private string _idcardnumber;
        private string _mobilenumber;
        private string _diplomaid;
        private DateTime? _birthdate;
        private string _examtypeid;
        private string _remark;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamExamineeInfo()
		{
			_id = String.Empty;
            _fullname = String.Empty;
            _loginname = String.Empty;
            _password = String.Empty;
            _sex = 0;
            _idcardnumber = String.Empty;
            _mobilenumber = String.Empty;
            _diplomaid = String.Empty;
            _birthdate = null;
            _examtypeid = String.Empty;
            _remark = string.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamExamineeInfo(string id, string fullname, string loginname, string password, short sex, string idcardnumber, string mobilenumber, string diplomaid, DateTime birthdate, string examtypeid, string remark)
		{
			_id = id;
            _fullname = fullname;
            _loginname = loginname;
            _password = password;
            _sex = sex;
            _idcardnumber = idcardnumber;
            _mobilenumber = mobilenumber;
            _diplomaid = diplomaid;
            _birthdate = birthdate;
            _examtypeid = examtypeid;
            _remark = remark;
		}
		
		#endregion // End Full Constructor

		#region Public Properties
			
		/// <summary>
		/// 主键
		/// </summary>		
		public string ID
		{
			get { return _id; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for ID", value, value.ToString());
				
				_isChanged |= (_id != value); _id = value;
			}
		} 

        /// <summary>
        /// 考生姓名
        /// </summary>		
        public string FullName
        {
            get { return _fullname; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for FullName", value, value.ToString());

                _isChanged |= (_fullname != value); _fullname = value;
            }
        }

        /// <summary>
        /// 考生登录名
        /// </summary>		
        public string LoginName
        {
            get { return _loginname; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for LoginName", value, value.ToString());

                _isChanged |= (_loginname != value); _loginname = value;
            }
        }

        /// <summary>
        /// 考生密码
        /// </summary>		
        public string PassWord
        {
            get { return _password; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PassWord", value, value.ToString());

                _isChanged |= (_password != value); _password = value;
            }
        }

        /// <summary>
        /// 性别
        /// </summary>		
        public short Sex
        {
            get { return _sex; }
            set
            {
                _isChanged |= (_sex != value); _sex = value;
            }
        }

        /// <summary>
        /// 身份证
        /// </summary>		
        public string IDCardNumber
        {
            get { return _idcardnumber; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for IDCardNumber", value, value.ToString());

                _isChanged |= (_idcardnumber != value); _idcardnumber = value;
            }
        }

        /// <summary>
        /// 手机号码
        /// </summary>		
        public string MobileNumber
        {
            get { return _mobilenumber; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for MobileNumber", value, value.ToString());

                _isChanged |= (_mobilenumber != value); _mobilenumber = value;
            }
        }

        /// <summary>
        /// 学历文凭ID
        /// </summary>		
        public string DiplomaID
        {
            get { return _diplomaid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DiplomaID", value, value.ToString());

                _isChanged |= (_diplomaid != value); _diplomaid = value;
            }
        }

        /// <summary>
        /// 出生日期
        /// </summary>		
        public DateTime? BirthDate
        {
            get { return _birthdate; }
            set
            {
                _isChanged |= (_birthdate != value); _birthdate = value;
            }
        }

        /// <summary>
        /// 考试类型
        /// </summary>		
        public string ExamTypeID
        {
            get { return _examtypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for ExamTypeID", value, value.ToString());

                _isChanged |= (_examtypeid != value); _examtypeid = value;
            }
        }

        /// <summary>
        /// 备注【主要资历、承担过项目、专业的主要经验】
        /// </summary>		
        public string Remark
        {
            get { return _remark; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());

                _isChanged |= (_remark != value); _remark = value;
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