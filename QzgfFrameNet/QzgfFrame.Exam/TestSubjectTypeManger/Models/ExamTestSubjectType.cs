/*
 * 文件名.........: ExamTestSubjectType.cs
 * 作者...........:  
 * 说明...........: 试卷试题类型信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.TestSubjectTypeManger.Models
{
	/// <summary>
	/// 试卷试题类型
	/// </summary>
	[Serializable]
    public sealed class ExamTestSubjectType
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _testid;
        private string _subjecttypeid;
        private string _typename;
        private string _explaination;
        private string _orderno;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamTestSubjectType()
		{
			_id = String.Empty;
            _testid = String.Empty;
            _subjecttypeid = String.Empty;
            _typename = String.Empty;
            _explaination = String.Empty;
            _orderno = String.Empty;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamTestSubjectType(string id, string testid, string subjecttypeid, string typename, string explaination, string orderno)
		{
			_id = id;
            _testid = testid;
            _subjecttypeid = subjecttypeid;
            _typename = typename;
            _explaination = explaination;
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
        /// 试题类型ID
        /// </summary>		
        public string SubjectTypeID
        {
            get { return _subjecttypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SubjectTypeID", value, value.ToString());

                _isChanged |= (_subjecttypeid != value); _subjecttypeid = value;
            }
        } 
	  
		/// <summary>
		/// 试卷试题类型名称
		/// </summary>		
        public string TypeName
		{
            get { return _typename; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TypeName", value, value.ToString());

                _isChanged |= (_typename != value); _typename = value;
			}
		} 
	  
		/// <summary>
		/// 说明
		/// </summary>		
        public string Explaination
        {
            get { return _explaination; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Explaination", value, value.ToString());

                _isChanged |= (_explaination != value); _explaination = value;
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