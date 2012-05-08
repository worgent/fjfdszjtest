/*
 * 文件名.........: ExamTestSubject.cs
 * 作者...........:  
 * 说明...........: 试卷试题信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.TestSubjectManger.Models
{
	/// <summary>
	/// 试卷试题类型
	/// </summary>
	[Serializable]
    public sealed class ExamTestSubject
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _testsubjecttypeid;
        private string _subjectid;
        private string _score;
        private string _orderno;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamTestSubject()
		{
			_id = String.Empty;
            _testsubjecttypeid = String.Empty;
            _subjectid = String.Empty;
            _score = String.Empty;
            _orderno = String.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamTestSubject(string id, string testsubjecttypeid,string subjectid, string score,string orderno)
		{
			_id = id;
            _testsubjecttypeid = testsubjecttypeid;
            _subjectid = subjectid;
            _score = score;
            _orderno = orderno;
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
		/// 试卷试题类型ID
		/// </summary>		
        public string TestSubjectTypeID
		{
            get { return _testsubjecttypeid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TestSubjectTypeID", value, value.ToString());

                _isChanged |= (_testsubjecttypeid != value); _testsubjecttypeid = value;
			}
		} 
	  
		/// <summary>
		/// 试题ID
		/// </summary>		
        public string SubjectID
		{
            get { return _subjectid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SubjectID", value, value.ToString());

                _isChanged |= (_subjectid != value); _subjectid = value;
			}
		} 
	  
		/// <summary>
		/// 分值
		/// </summary>		
        public string Score		{
            get { return _score; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Score", value, value.ToString());

                _isChanged |= (_score != value); _score = value;
			}
		}

        /// <summary>
        /// 顺序
        /// </summary>		
        public string OrderNo
        {
            get { return _orderno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrderNo", value, value.ToString());

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