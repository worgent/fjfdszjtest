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
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.RelationManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class SystemRelation
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;


        // Primary Key(s) 
        private string _id;
		// Properties 
        private string _relationname;
        private string _controllername;
        private string _mid;
        private string _cid;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public SystemRelation()
        {
            _id = "0";
			_relationname = String.Empty; 
            _controllername = String.Empty;
            _mid = String.Empty;
            _cid = String.Empty; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public SystemRelation(string id, string relationname, string controllername, string mid, string cid)
        {
            _id = id;
            _relationname = relationname;
            _controllername = controllername;
            _mid = mid;
            _cid = cid; 
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
                    if (value.Length > 50)
                        throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());

                _isChanged |= (_id != value); _id = value;
            }
        }	
		/// <summary>
		/// 主表的控制器名称
		/// </summary>		
        public string ControllerName
		{
            get { return _controllername; }
			set	
			{
				if ( value != null )
					if( value.Length > 50)
                        throw new ArgumentOutOfRangeException("Invalid value for ControllerName", value, value.ToString());

                _isChanged |= (_controllername != value); _controllername = value;
			}
		} 
	  
		/// <summary>
		/// 主表ID
		/// </summary>		
        public string MId
		{
            get { return _mid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for MId", value, value.ToString());

                _isChanged |= (_mid != value); _mid = value;
			}
		} 
		/// <summary>
		/// 关系表ID
		/// </summary>		
        public string CId
		{
			get { return _cid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for CId", value, value.ToString());

                _isChanged |= (_cid != value); _cid = value;
			}
		}
        /// <summary>
        /// 关系表的控制器名称
        /// </summary>		
        public string RelationName
        {
            get { return _relationname; }
            set
            {
                if (value != null)
                    if (value.Length > 50)
                        throw new ArgumentOutOfRangeException("Invalid value for RelationName", value, value.ToString());

                _isChanged |= (_relationname != value); _relationname = value;
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