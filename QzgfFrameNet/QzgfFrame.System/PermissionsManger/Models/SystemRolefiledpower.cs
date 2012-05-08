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

namespace QzgfFrame.System.PermissionsManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemRolefiledpower 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _roleid; 
		private string _fieldid;
        private Int32 _powerval; 		

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemRolefiledpower()
		{
			_id = String.Empty; 
			_roleid = String.Empty;
            _fieldid = String.Empty; 
			_powerval = 0; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public SystemRolefiledpower(string id, string roleid, string fieldid, Int32 powerval)
		{
			_id = id; 
			_roleid = roleid;
            _fieldid = fieldid; 
			_powerval = powerval; 
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
		/// 角色id
		/// </summary>		
		public string Roleid
		{
			get { return _roleid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Roleid", value, value.ToString());
				
				_isChanged |= (_roleid != value); _roleid = value;
			}
		} 
	  
		/// <summary>
		/// 功能字段权限
		/// </summary>		
		public string Fieldid
		{
            get { return _fieldid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Filedid", value, value.ToString());

                _isChanged |= (_fieldid != value); _fieldid = value;
			}
		} 
	  
		/// <summary>
		/// 
		/// </summary>		
        public Int32 Powerval
		{
			get { return _powerval; }
			set { _isChanged |= (_powerval != value); _powerval = value; }
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