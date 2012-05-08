/*

comment

*/

using System;

namespace QzgfFrame.System.PermissionsManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemRolemenu 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _roleid; 
		private string _menuid;
        private Int32 _optval;

	    private string _menuname;
        private string _fatherid;
        private string _orderno;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemRolemenu()
		{
			_id = String.Empty; 
			_roleid = String.Empty; 
			_menuid = String.Empty;
		    _optval = 0;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public SystemRolemenu(string id, string roleid, string menuid, Int32 optval)
		{
			_id = id; 
			_roleid = roleid; 
			_menuid = menuid;
		    _optval = optval;
		}

        public SystemRolemenu(string menuid, Int32 optval, string menuname, string fatherid, string orderno)
        {
            _menuid = menuid;
            _optval = optval;
            _menuname = menuname;
            _fatherid = fatherid;
            _orderno = orderno;
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
		/// 菜单id
		/// </summary>		
		public string Menuid
		{
			get { return _menuid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Menuid", value, value.ToString());
				
				_isChanged |= (_menuid != value); _menuid = value;
			}
		}

        /// <summary>
        /// 
        /// </summary>		
        public Int32 Optval
        {
            get { return _optval; }
            set { _isChanged |= (_optval != value); _optval = value; }
        } 

		/// 菜单id
		/// </summary>		
		public string Menuname
		{
			get { return _menuname; }
			set	
			{
				 
				_isChanged |= (_menuname != value); _menuname = value;
			}
		}
        /// <summary>
		/// 菜单id
		/// </summary>		
        public string Fatherid
		{
            get { return _fatherid; }
			set	
			{

                _isChanged |= (_fatherid != value); _fatherid = value;
			}
		}
        /// <summary>
        /// 菜单id
        /// </summary>		
        public string Orderno
        {
            get { return _orderno; }
            set
            {

                _isChanged |= (_orderno != value); _orderno = value;
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