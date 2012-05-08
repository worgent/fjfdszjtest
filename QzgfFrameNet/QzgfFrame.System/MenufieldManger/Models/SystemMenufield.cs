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
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Common;

namespace QzgfFrame.System.MenufieldManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemMenufield 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _menuid;
        private string _menuname; 
		private string _fieldcode; 
		private string _fieldname; 
		private string _tablecode; 
		private string _tablename; 
		private string _remark;
	    private int _powerval;

	    private string _permissionsflag;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemMenufield()
		{
			_id = String.Empty; 
			_menuid = String.Empty;
            _fieldcode = String.Empty;
            _fieldname = String.Empty; 
			_tablecode = String.Empty; 
			_tablename = String.Empty; 
			_remark = String.Empty; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
		public SystemMenufield(string id, string menuid, string filedcode, string filedname, string tablecode, string tablename, string remark,string permissionsflag)
		{
			_id = id; 
			_menuid = menuid;
            _fieldcode = filedcode;
            _fieldname = filedname; 
			_tablecode = tablecode; 
			_tablename = tablename; 
			_remark = remark;
		    _permissionsflag = permissionsflag;
		}

	    /// <summary>
	    /// 列表信息
	    /// </summary>
	    /// <param name="id"></param>
	    /// <param name="menuid"></param>
	    /// <param name="menuname"></param>
	    /// <param name="fieldcode"></param>
	    /// <param name="fieldname"></param>
	    /// <param name="tablecode"></param>
	    /// <param name="tablename"></param>
	    /// <param name="remark"></param>
	    /// <param name="permissionsflag"></param>
	    public SystemMenufield(string id, string menuid, string menuname, string fieldcode, string fieldname, string tablecode, string tablename, string remark, string permissionsflag)
        {
            _id = id;
            _menuid = menuid;
            _menuname = menuname;
            _fieldcode = fieldcode;
            _fieldname = fieldname;
            _tablecode = tablecode;
            _tablename = tablename;
            _remark = remark;
            _permissionsflag = permissionsflag;
        }
        /// <summary>
        /// 查询权限时专用
        /// </summary>
        /// <param name="id"></param>
        /// <param name="menuid"></param>
        /// <param name="fieldcode"></param>
        /// <param name="tablecode"></param>
        /// <param name="powerval"></param>
        public SystemMenufield(string id, string menuid, string fieldcode, string tablecode, int powerval, string permissionsflag)
        {
            _id = id;
            _menuid = menuid;
            _fieldcode = fieldcode;
            _tablecode = tablecode;
            _powerval = powerval;
            _permissionsflag = permissionsflag;
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
		/// control主键
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
        /// control主键
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
		/// 字段代码
		/// </summary>		
		public string Fieldcode
		{
			get { return _fieldcode; }
			set	
			{
				if ( value != null )
					if( value.Length > 50)
						throw new ArgumentOutOfRangeException("Invalid value for Filedcode", value, value.ToString());
				
				_isChanged |= (_fieldcode != value); _fieldcode = value;
			}
		} 
	  
		/// <summary>
		/// 字段名称
		/// </summary>		
		public string Fieldname
		{
			get { return _fieldname; }
			set	
			{
				if ( value != null )
					if( value.Length > 100)
						throw new ArgumentOutOfRangeException("Invalid value for Filedname", value, value.ToString());
				
				_isChanged |= (_fieldname != value); _fieldname = value;
			}
		}

        /// <summary>
        /// 字段名称
        /// </summary>		
        public int Powerval
        {
            get { return _powerval; }
            set
            {
                _isChanged |= (_powerval != value); _powerval = value;
            }
        }

        /// <summary>
        /// 是否有显示权限
        /// </summary>
        public bool IsShow
        {
            get { return CommonHelper.CheckPower(_powerval, Constant.Show); }
        }
        /// <summary>
        /// 是否有打印权限
        /// </summary>
        public bool IsPrint
        {
            get { return CommonHelper.CheckPower(_powerval, Constant.Print); }
        }
        /// <summary>
        /// 是否有导出权限
        /// </summary>
        public bool IsExport
        {
            get { return CommonHelper.CheckPower(_powerval, Constant.Export); }
        } 

		/// <summary>
		/// 
		/// </summary>		
		public string Tablecode
		{
			get { return _tablecode; }
			set	
			{
				if ( value != null )
					if( value.Length > 50)
						throw new ArgumentOutOfRangeException("Invalid value for Tablecode", value, value.ToString());
				
				_isChanged |= (_tablecode != value); _tablecode = value;
			}
		} 
	  
		/// <summary>
		/// 
		/// </summary>		
		public string Tablename
		{
			get { return _tablename; }
			set	
			{
				if ( value != null )
					if( value.Length > 100)
						throw new ArgumentOutOfRangeException("Invalid value for Tablename", value, value.ToString());
				
				_isChanged |= (_tablename != value); _tablename = value;
			}
		} 
	  
		/// <summary>
		/// 
		/// </summary>		
		public string Remark
		{
			get { return _remark; }
			set	
			{
				if ( value != null )
					if( value.Length > 100)
						throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());
				
				_isChanged |= (_remark != value); _remark = value;
			}
		}

        public string Permissionsflag
        {
            get { return _permissionsflag; }
            set { _isChanged |= (_permissionsflag != value); _permissionsflag = value; }
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