/*
 * 文件名.........: ExamTestMock.cs
 * 作者...........:  
 * 说明...........: 模拟试卷设置信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.TestMockManger.Models
{
	/// <summary>
	/// 试卷设置
	/// </summary>
	[Serializable]
    public sealed class ExamTestMock
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _testid;
        private string _timelength;
        private string _creatorid;
        private DateTime? _createtime;
        private string _pass;
        private bool _isdelete;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamTestMock()
		{
			_id = String.Empty;
            _testid = String.Empty;
            _timelength = String.Empty;
            _creatorid = String.Empty;
            _createtime = DateTime.Now;
            _pass = String.Empty;
            _isdelete = true;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamTestMock(string id, string testid, string timelength, string creatorid, DateTime createtime, string pass, bool isdelete)
		{
			_id = id;
            _testid = testid;
            _timelength = timelength;
            _creatorid = creatorid;
            _createtime = createtime;
            _pass = pass;
            _isdelete = isdelete;
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
		/// 试卷内容ID
		/// </summary>		
        public string TestID
		{
            get { return _testid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TestID", value, value.ToString());

                _isChanged |= (_testid != value); _testid = value;
			}
		} 
	    
		/// <summary>
		/// 考试时长
		/// </summary>		
        public string TimeLength
		{
            get { return _timelength; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TimeLength", value, value.ToString());

                _isChanged |= (_timelength != value); _timelength = value;
			}
		}
        /// <summary>
        /// 创建者ID
        /// </summary>		
        public string CreatorID
        {
            get { return _creatorid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CreatorID", value, value.ToString());

                _isChanged |= (_creatorid != value); _creatorid = value;
            }
        } 
        /// <summary>
        /// 创建时间
        /// </summary>		
        public DateTime? CreateTime
        {
            get { return _createtime; }
            set
            {
                _isChanged |= (_createtime != value); _createtime = value;
            }
        } 
	  
		/// <summary>
		/// 及格条件
		/// </summary>		
        public string Pass		{
            get { return _pass; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Pass", value, value.ToString());

                _isChanged |= (_pass != value); _pass = value;
			}
		}

        /// <summary>
        /// 是否删除
        /// </summary>		
        public bool IsDelete
        {
            get { return _isdelete; }
            set
            {
                _isChanged |= (_isdelete != value); _isdelete = value;
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