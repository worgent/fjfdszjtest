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

namespace QzgfFrame.System.SmsManger.Models
{
	/// <summary>
	/// auto gen
	/// </summary>
	[Serializable]
	public sealed class TabQywyySmsSend 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _send_qydm; 
		private decimal _msisdn; 
		private string _send_msg; 
		private DateTime _send_date; 
		private string _src; 
		private decimal _send_mk; 		

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public TabQywyySmsSend()
		{
			_id = String.Empty; 
			_send_qydm = String.Empty; 
			_msisdn = 0; 
			_send_msg = String.Empty; 
			_send_date = DateTime.MinValue; 
			_src = String.Empty; 
			_send_mk = 0; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
		public TabQywyySmsSend(string id, string send_qydm, decimal msisdn, string send_msg, DateTime send_date, string src, decimal send_mk)
		{
			_id = id; 
			_send_qydm = send_qydm; 
			_msisdn = msisdn; 
			_send_msg = send_msg; 
			_send_date = send_date; 
			_src = src; 
			_send_mk = send_mk; 
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
		/// 企业代码，本系统固定值为”8000”
		/// </summary>		
		public string SendQydm
		{
			get { return _send_qydm; }
			set	
			{
				if ( value != null )
					if( value.Length > 12)
						throw new ArgumentOutOfRangeException("Invalid value for SendQydm", value, value.ToString());
				
				_isChanged |= (_send_qydm != value); _send_qydm = value;
			}
		} 
	  
		/// <summary>
		/// 手机号码
		/// </summary>		
		public decimal Msisdn
		{
			get { return _msisdn; }
			set { _isChanged |= (_msisdn != value); _msisdn = value; }
		} 
	  
		/// <summary>
		/// 短信内容
		/// </summary>		
		public string SendMsg
		{
			get { return _send_msg; }
			set	
			{
				if ( value != null )
					if( value.Length > 512)
						throw new ArgumentOutOfRangeException("Invalid value for SendMsg", value, value.ToString());
				
				_isChanged |= (_send_msg != value); _send_msg = value;
			}
		} 
	  
		/// <summary>
		/// 发送时间
		/// </summary>		
		public DateTime SendDate
		{
			get { return _send_date; }
			set { _isChanged |= (_send_date != value); _send_date = value; }
		} 
	  
		/// <summary>
		/// 短信端口号，本系统固定值为”10658473030”
		/// </summary>		
		public string Src
		{
			get { return _src; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
						throw new ArgumentOutOfRangeException("Invalid value for Src", value, value.ToString());
				
				_isChanged |= (_src != value); _src = value;
			}
		} 
	  
		/// <summary>
		/// 发送标志：0 未发送，1 正处理，9 已发送，用触发器实现时暂时未用，请直接插入值9;
		/// </summary>		
		public decimal SendMk
		{
			get { return _send_mk; }
			set { _isChanged |= (_send_mk != value); _send_mk = value; }
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