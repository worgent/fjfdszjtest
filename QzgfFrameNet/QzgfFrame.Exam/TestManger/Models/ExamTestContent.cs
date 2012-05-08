/*
 * 文件名.........: ExamTestContent.cs
 * 作者...........:  
 * 说明...........: 试卷内容信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.TestManger.Models
{
	/// <summary>
	/// 试卷内容
	/// </summary>
	[Serializable]
    public sealed class ExamTestContent 
	{
		#region Private Members
		
		// Variabili di stato
		private bool _isChanged;
		private bool _isDeleted;

		// Primary Key(s) 
		private string _id; 
		
		// Properties 
		private string _name; 
		private string _creatorid;
        private DateTime? _createtime;
        private string _points;
        private string _typeid;
        private TestState _state;
        private bool _isdelete;

		#endregion
		
		#region Default ( Empty ) Class Constructor
		
		/// <summary>
		/// default constructor
		/// </summary>
		public ExamTestContent()
		{
			_id = String.Empty;
            _name = String.Empty;
            _creatorid = String.Empty;
            _createtime = DateTime.Now;
            _points = String.Empty;
            _typeid = String.Empty;
            _state =  TestState.未组织考试;
            _isdelete = true;
		}
		
		#endregion // End of Default ( Empty ) Class Constructor
		
		#region Full Constructor
		
		/// <summary>
		/// full constructor
		/// </summary>
        public ExamTestContent(string id, string name, string creatorid, DateTime createtime, string points, string typeid, TestState state, bool isdelete)
		{
			_id = id;
            _name = name;
            _creatorid = creatorid;
            _createtime = createtime;
            _points = points;
            _typeid = typeid;
            _state = state;
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
		/// 试卷名称
		/// </summary>		
		public string Name
		{
            get { return _name; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Name", value, value.ToString());

                _isChanged |= (_name != value); _name = value;
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
				if ( value != null )
					if( value.Length > 256)
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
		/// 总分
		/// </summary>		
        public string Points
		{
            get { return _points; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Points", value, value.ToString());

                _isChanged |= (_points != value); _points = value;
			}
		} 
	  
		/// <summary>
		/// 试卷类型
		/// </summary>		
        public string TypeID		{
            get { return _typeid; }
			set	
			{
				if ( value != null )
					if( value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TypeID", value, value.ToString());

                _isChanged |= (_typeid != value); _typeid = value;
			}
		}

        /// <summary>
        /// 状态
        /// </summary>		
        public TestState State
        {
            get { return _state; }
            set
            {
                _isChanged |= (_state != value); _state = value;
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

    public enum TestState
    {
        未组织考试 = 1, 已组织练习考=2, 已组织模拟考=3, 已组织考试 = 4, 已开考 = 5, 已结束 = 6
    }
}