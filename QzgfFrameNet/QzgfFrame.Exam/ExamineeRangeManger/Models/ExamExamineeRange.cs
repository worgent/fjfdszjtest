/*
 * 文件名.........: ExamExamineeRange.cs
 * 作者...........:  
 * 说明...........: 考生范围信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.ExamineeRangeManger.Models
{
	/// <summary>
	/// 试题类型
	/// </summary>
	[Serializable]
    public sealed class ExamExamineeRange
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _testsetid;
        private string _examineeid;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamExamineeRange()
		{
			_id = String.Empty;
            _testsetid = String.Empty;
            _examineeid = String.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamExamineeRange(string id, string testsetid, string examineeid)
		{
			_id = id;
            _testsetid = testsetid;
            _examineeid = examineeid;
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
		/// 试卷设置ID
		/// </summary>		
		public string TestSetID
		{
            get { return _testsetid; }
			set	
			{
				if ( value != null )
					if( value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for TestSetID", value, value.ToString());

                _isChanged |= (_testsetid != value); _testsetid = value;
			}
		}

        /// <summary>
        /// 考生ID
        /// </summary>		
        public string ExamineeID
        {
            get { return _examineeid; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for ExamineeID", value, value.ToString());

                _isChanged |= (_examineeid != value); _examineeid = value;
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