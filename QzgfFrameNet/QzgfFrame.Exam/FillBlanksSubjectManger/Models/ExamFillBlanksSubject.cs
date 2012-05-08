/*
 * 文件名.........: ExamFillBlanksSubject.cs
 * 作者...........:  
 * 说明...........: 填空题信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.FillBlanksSubjectManger.Models
{
	/// <summary>
	/// 填空题
	/// </summary>
	[Serializable]
    public sealed class ExamFillBlanksSubject 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
        private string _subjectid; 
		private string _orderno;
		private string _answer;
		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamFillBlanksSubject()
		{
			_id = String.Empty;
            _subjectid = String.Empty;
            _orderno = String.Empty;
            _answer = String.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamFillBlanksSubject(string id, string subjectid, string orderno, string answer)
		{
			_id = id;
            _subjectid = subjectid;
            _orderno = orderno;
            _answer = answer;
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
		/// 试题内容ID
		/// </summary>		
        public string SubjectID
		{
            get { return _subjectid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BlankID", value, value.ToString());

                _isChanged |= (_subjectid != value); _subjectid = value;
			}
		} 
	  
		/// <summary>
		/// 空格题排序
		/// </summary>		
        public string OrderNo
		{
            get { return _orderno; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrderNo", value, value.ToString());

                _isChanged |= (_orderno != value); _orderno = value;
			}
		} 
	  
		/// <summary>
		///填空题答案
		/// </summary>		
		public string Answer
		{
            get { return _answer; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Answer", value, value.ToString());

                _isChanged |= (_answer != value); _answer = value;
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