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

namespace QzgfFrame.System.DepartmentManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemDepartment 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _departname; 
		private string _charger; 
		private string _tel; 
		private string _address; 
		private string _father;
        private string _fathername; 
		private string _remark;
        private string _type;
        private string _typename;
		private string _state;
        private string _isrepair;
        private string _levelno;
        private string _orderno;
        private string _statename; 
		private string _createman; 
		private DateTime _createdate; 		

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemDepartment()
		{
			_id = String.Empty; 
			_departname = String.Empty; 
			_charger = String.Empty; 
			_tel = String.Empty; 
			_address = String.Empty; 
			_father = String.Empty; 
			_remark = String.Empty;
            _type = String.Empty;
            _typename = String.Empty;
            _state = String.Empty;
		    _levelno = String.Empty;
		    _isrepair = String.Empty;
			_orderno = String.Empty; 
			_createman = String.Empty; 
			_createdate = DateTime.MinValue;
		    _fathername = String.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
		public SystemDepartment(string id, string departname, string charger, string tel, string address, string father, string remark, string state, string createman, DateTime createdate)
		{
			_id = id; 
			_departname = departname; 
			_charger = charger; 
			_tel = tel; 
			_address = address; 
			_father = father; 
			_remark = remark; 
			_state = state; 
			_createman = createman; 
			_createdate = createdate; 
		}
        //列表包括创建时间信息(暂不用)
        public SystemDepartment(string id, string departname, string charger, string tel, string address, string father, string fathername, string remark, string state, string createman, DateTime createdate, string statename)
        {
            _id = id;
            _departname = departname;
            _charger = charger;
            _tel = tel;
            _address = address;
            _father = father;
            _fathername = fathername;
            _remark = remark;
            _state = state;
            _createman = createman;
            _createdate = createdate;
            _statename = statename;
        }
        //列表信息不包括创建时间(列表使用中)
        public SystemDepartment(string id, string departname, string charger, string tel, string address, string father, string fathername, string remark, string statename, string type, string typename, string orderno, string isrepair, string levelno)
        {
            _id = id;
            _departname = departname;
            _charger = charger;
            _tel = tel;
            _address = address;
            _father = father;
            _fathername = fathername;
            _remark = remark;
            _type = type;
            _typename = typename;
            _orderno = orderno;
            _levelno = levelno;
            _isrepair = isrepair;
            _statename = statename;
        }
        //下拉列表专用
        public SystemDepartment(string id, string father, string departname)
        {
            _id = id;
            _departname = departname;
            _father = father;
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
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());
				
				_isChanged |= (_id != value); _id = value;
			}
		} 
	  
		/// <summary>
		/// 部门名称
		/// </summary>		
		public string Departname
		{
			get { return _departname; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
						throw new ArgumentOutOfRangeException("Invalid value for Departname", value, value.ToString());
				
				_isChanged |= (_departname != value); _departname = value;
			}
		} 
	  
		/// <summary>
		/// 部门负责人
		/// </summary>		
		public string Charger
		{
			get { return _charger; }
			set	
			{
				if ( value != null )
					if( value.Length > 50)
						throw new ArgumentOutOfRangeException("Invalid value for Charger", value, value.ToString());
				
				_isChanged |= (_charger != value); _charger = value;
			}
		} 
	  
		/// <summary>
		/// 负责人电话
		/// </summary>		
		public string Tel
		{
			get { return _tel; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Tel", value, value.ToString());
				
				_isChanged |= (_tel != value); _tel = value;
			}
		} 
	  
		/// <summary>
		/// 负责人地址
		/// </summary>		
		public string Address
		{
			get { return _address; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
						throw new ArgumentOutOfRangeException("Invalid value for Address", value, value.ToString());
				
				_isChanged |= (_address != value); _address = value;
			}
		} 
	  
		/// <summary>
		/// 上级结点
		/// </summary>		
		public string Father
		{
			get { return _father; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Father", value, value.ToString());
				
				_isChanged |= (_father != value); _father = value;
			}
		}

        /// <summary>
        /// 上级结点
        /// </summary>		
        public string Fathername
        {
            get { return _fathername; }
            set
            {
                _isChanged |= (_fathername != value); _fathername = value;
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
				if ( value != null )
					if( value.Length > 256)
						throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());
				
				_isChanged |= (_remark != value); _remark = value;
			}
		} 
	  
		/// <summary>
		/// 0:删除,1:正常,2停用
		/// </summary>		
		public string State
		{
			get { return _state; }
			set	
			{
				if ( value != null )
					if( value.Length > 2)
						throw new ArgumentOutOfRangeException("Invalid value for State", value, value.ToString());
				
				_isChanged |= (_state != value); _state = value;
			}
		}

        /// <summary>
        /// 0:删除,1:正常,2停用
        /// </summary>		
        public string Levelno
        {
            get { return _levelno; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for _levelno", value, value.ToString());

                _isChanged |= (_levelno != value); _levelno = value;
            }
        }

        /// <summary>
        /// </summary>		
        public string Isrepair
        {
            get {
                if (_isrepair != null)
                    return _isrepair.Trim();
                else
                    return "0"; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for _isrepair", value, value.ToString());

                _isChanged |= (_isrepair != value); _isrepair = value;
            }
        }

        public string Type
        {
            get { return _type; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for _type", value, value.ToString());

                _isChanged |= (_type != value); _type = value;
            }
        }

        public string Typename
        {
            get { return _typename; }
            set
            {
                _isChanged |= (_typename != value); _typename = value;
            }
        }
        public string Orderno
        {
            get { return _orderno; }
            set
            {
                if (value != null)
                    if (value.Length > 10)
                        throw new ArgumentOutOfRangeException("Invalid value for _orderno", value, value.ToString());

                _isChanged |= (_orderno != value); _orderno = value;
            }
        }

        /// <summary>
        /// 0:删除,1:正常,2停用
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
		/// 
		/// </summary>		
		public string Createman
		{
			get { return _createman; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
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