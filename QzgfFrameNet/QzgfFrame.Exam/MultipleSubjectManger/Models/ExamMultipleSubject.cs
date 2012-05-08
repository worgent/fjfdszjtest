/*
 * 文件名.........: ExamMultipleSubject.cs
 * 作者...........:  
 * 说明...........: 选择题信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.MultipleSubjectManger.Models
{
	/// <summary>
	///  选择题
	/// </summary>
	[Serializable]
    public sealed class ExamMultipleSubject 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
        private string _multiplecontent;
        private string _subjectid; 
		private string _orderno;
		private bool _answer;
		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamMultipleSubject()
		{
			_id = String.Empty;
            _multiplecontent = String.Empty;
            _subjectid = String.Empty;
            _orderno = String.Empty;
            _answer = false;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamMultipleSubject(string id,string multiplecontent,string subjectid, string orderno, bool answer)
		{
			_id = id;
            _multiplecontent = multiplecontent;
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
        /// 选项内容
        /// </summary>		
        public string MultipleContent
        {
            get { return _multiplecontent; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MultipleContent", value, value.ToString());

                _isChanged |= (_multiplecontent != value); _multiplecontent = value;
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
		///选项是否是答案
		/// </summary>		
		public bool Answer
		{
            get { return _answer; }
			set	
			{
                //if ( value != null )
                //    ////if( value.Length > 20)
                //    //    throw new ArgumentOutOfRangeException("Invalid value for Answer", value, value.ToString());

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