/*
 * 文件名.........: ExamSubjectContent.cs
 * 作者...........:  
 * 说明...........: 试题内容信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.SubjectManger.Models
{
	/// <summary>
	/// 试题内容
	/// </summary>
	[Serializable]
    public sealed class ExamSubjectContent 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _content; 
		private string _creatorid;
        private DateTime? _createtime;
        private string _scalegradeid;
        private string _typeid;
        private string _courseid;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamSubjectContent()
		{
			_id = String.Empty;
            _content = String.Empty;
            _creatorid = String.Empty;
            _createtime = DateTime.Now;
            _scalegradeid = String.Empty;
            _typeid = String.Empty;
            _courseid = String.Empty; 
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamSubjectContent(string id, string content, string creatorid, DateTime createtime, string scalegradeid, string typeid, string courseid)
		{
			_id = id;
            _content = content;
            _creatorid = creatorid;
            _createtime = createtime;
            _scalegradeid = scalegradeid;
            _typeid = typeid;
            _courseid = courseid; 
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
		/// 试题内容
		/// </summary>		
		public string Content
		{
            get { return _content; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Content", value, value.ToString());

                _isChanged |= (_content != value); _content = value;
			}
		} 
	  
		/// <summary>
		/// 创建者ID
		/// </summary>		
        public string CreatorId
		{
            get { return _creatorid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CreatorId", value, value.ToString());

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
		/// 难易度
		/// </summary>		
        public string ScaleGradeId
		{
            get { return _scalegradeid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ScaleGrade", value, value.ToString());

                _isChanged |= (_scalegradeid != value); _scalegradeid = value;
			}
		} 
	  
		/// <summary>
		/// 试题类型
		/// </summary>		
        public string TypeId		{
            get { return _typeid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TypeId", value, value.ToString());

                _isChanged |= (_typeid != value); _typeid = value;
			}
		}

        /// <summary>
        /// 科目类型
        /// </summary>		
        public string CourseTypeId
        {
            get { return _courseid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Type", value, value.ToString());

                _isChanged |= (_courseid != value); _courseid = value;
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