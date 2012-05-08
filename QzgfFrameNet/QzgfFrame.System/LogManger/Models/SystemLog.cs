/*

comment

*/

using System;
using System.Collections;
using System.Collections.Generic;


namespace QzgfFrame.System.LogManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemLog 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
        private string _opercode;
		private string _opername;
        private string _controllerscode;
        private string _controllersname; 
		private string _userid;
        private string _username; 
		private DateTime _operdate; 
		private string _operresult; 
		private string _operip; 		

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemLog()
		{
			_id = String.Empty;
            _opercode = String.Empty;
			_opername = String.Empty; 
			_userid = String.Empty; 
			_operdate = DateTime.MinValue; 
			_operresult = String.Empty; 
			_operip = String.Empty;
            _controllersname = String.Empty;
            _controllerscode = String.Empty; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public SystemLog(string id, string opercode, string opername, string controllerscode, string controllersname, string userid, string username, DateTime operdate, string operresult, string operip)
		{
			_id = id; 
			_opername = opername;
            _opercode = opercode;
            _controllersname = controllersname;
            _controllerscode = controllerscode; 
			_userid = userid;
		    _username = username;
			_operdate = operdate; 
			_operresult = operresult; 
			_operip = operip; 
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
		/// 操作名称
		/// </summary>		
		public string Opername
		{
			get { return _opername; }
			set	
			{
				if ( value != null )
					if( value.Length > 100)
						throw new ArgumentOutOfRangeException("Invalid value for Opername", value, value.ToString());
				
				_isChanged |= (_opername != value); _opername = value;
			}
		}
        /// <summary>
        /// 操作名称
        /// </summary>		
        public string Opercode
        {
            get { return _opercode; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Opercode", value, value.ToString());

                _isChanged |= (_opercode != value); _opercode = value;
            }
        }
        /// <summary>
        /// 操作名称
        /// </summary>		
        public string Controllersname
        {
            get { return _controllersname; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Controllersname", value, value.ToString());

                _isChanged |= (_controllersname != value); _controllersname = value;
            }
        }

        /// <summary>
        /// 操作名称
        /// </summary>		
        public string Controllerscode
        {
            get { return _controllerscode; }
            set
            {
                if (value != null)
                    if (value.Length > 100)
                        throw new ArgumentOutOfRangeException("Invalid value for Controllerscode", value, value.ToString());

                _isChanged |= (_controllerscode != value); _controllerscode = value;
            }
        } 

		/// <summary>
		/// 用户id
		/// </summary>		
		public string Userid
		{
			get { return _userid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Userid", value, value.ToString());
				
				_isChanged |= (_userid != value); _userid = value;
			}
		}

        /// <summary>
        /// 用户username
        /// </summary>		
        public string Username
        {
            get { return _username; }
            set
            {
                _isChanged |= (_username != value); _username = value;
            }
        } 
	  

		/// <summary>
		/// 操作时间
		/// </summary>		
		public DateTime Operdate
		{
			get { return _operdate; }
			set { _isChanged |= (_operdate != value); _operdate = value; }
		} 
	  
		/// <summary>
		/// 操作结果
		/// </summary>		
		public string Operresult
		{
			get { return _operresult; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
						throw new ArgumentOutOfRangeException("Invalid value for Operresult", value, value.ToString());
				
				_isChanged |= (_operresult != value); _operresult = value;
			}
		} 
	  
		/// <summary>
		/// 操作地址ip
		/// </summary>		
		public string Operip
		{
			get { return _operip; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
						throw new ArgumentOutOfRangeException("Invalid value for Operip", value, value.ToString());
				
				_isChanged |= (_operip != value); _operip = value;
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