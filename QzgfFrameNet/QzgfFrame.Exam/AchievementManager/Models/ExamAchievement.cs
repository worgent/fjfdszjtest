using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.AchievementManager.Models
{
    /// <summary>
    /// 考生做过的试卷表
    /// </summary>
    [Serializable]
    public class ExamAchievement
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 考生ID
        /// </summary>
        private string _userid;
        /// <summary>
        /// 答题开始时间
        /// </summary>
        private DateTime? _answerstarttime;
        /// <summary>
        /// 答题结束时间
        /// </summary>
        private DateTime? _answerendtime;
        /// <summary>
        /// 分数
        /// </summary>
        private string _fraction;
        /// <summary>
        /// 是否是已提交的试卷:0,未交;1,已交
        /// </summary>
        private Int32 _issubmitted;
        /// <summary>
        /// 批改状态:0,未批改;1,已批改
        /// </summary>
        private Int32 _markingstate;
        /// <summary>
        /// 批改时间
        /// </summary>
        private DateTime? _markingtime;
        /// <summary>
        /// 试卷id 
        /// </summary>
        private string _testid;
        /// <summary>
        /// 试卷设置表的id
        /// </summary>
        private string _setupid;
        /// <summary>
        /// 考试类型(0,在线考试; 1,模拟考试; 2,在线练习)
        /// </summary>
        private string _examtype;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ExamAchievement()
        {
            _id = String.Empty;
            _userid = String.Empty;
            _answerstarttime = DateTime.Now;
            _answerendtime = DateTime.Now;
            _fraction = String.Empty;
            _issubmitted = 0;
            _markingstate = 0;
            _markingtime = DateTime.Now;
            _testid = String.Empty;
            _setupid = String.Empty;
            _examtype = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ExamAchievement(string id, string userid, DateTime answerstarttime, DateTime answerendtime, string fraction,
            Int32 issubmitted, Int32 markingstate, DateTime markingtime, string testid, string setupid, string examtype)
        {
            _id = id;
            _userid = userid;
            _answerstarttime = answerstarttime;
            _answerendtime = answerendtime;
            _fraction = fraction;
            _issubmitted = issubmitted;
            _markingstate = markingstate;
            _markingtime = markingtime;
            _testid = testid;
            _setupid = setupid;
            _examtype = examtype;
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
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());

                _isChanged |= (_id != value); _id = value;
            }
        }

        /// <summary>
        /// 考生ID
        /// </summary>		
        public string UserId
        {
            get { return _userid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserId", value, value.ToString());

                _isChanged |= (_userid != value); _userid = value;
            }
        }
        /// <summary>
        /// 答题开始时间
        /// </summary>		
        public DateTime?  AnswerStartTime
        {
            get { return _answerstarttime; }
            set
            {
                _isChanged |= (_answerstarttime != value); _answerstarttime = value;
            }
        }
        /// <summary>
        /// 答题结束时间
        /// </summary>		
        public DateTime? AnswerEndTime
        {
            get { return _answerendtime; }
            set
            {
                _isChanged |= (_answerendtime != value); _answerendtime = value;
            }
        }

        /// <summary>
        /// 总分
        /// </summary>		
        public string Fraction
        {
            get { return _fraction; }
            set
            {
                _fraction = value;
            }
        }
        /// <summary>
        /// 是否是已提交的试卷:0,未交;1,已交
        /// </summary>		
        public Int32 IsSubmitted
        {
            get { return _issubmitted; }
            set
            {
                _isChanged |= (_issubmitted != value); _issubmitted = value;
            }
        }
        /// <summary>
        /// 批改状态:0,未批改;1,已批改
        /// </summary>		
        public Int32 MarkingState
        {
            get { return _markingstate; }
            set
            {
                _isChanged |= (_markingstate != value); _markingstate = value;
            }
        }
        /// <summary>
        /// 批改时间
        /// </summary>		
        public DateTime? MarkingTime
        {
            get { return _markingtime; }
            set
            {
                _isChanged |= (_markingtime != value); _markingtime = value;
            }
        }

        /// <summary>
        /// 试卷id
        /// </summary>		
        public string TestId
        {
            get { return _testid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TestId", value, value.ToString());

                _isChanged |= (_testid != value); _testid = value;
            }
        }

        /// <summary>
        /// 试卷设置表的id
        /// </summary>
        public string SetUpId
        {
            get { return _setupid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SetUpId", value, value.ToString());

                _isChanged |= (_setupid != value); _setupid = value;
            }
        }

        /// <summary>
        /// 考试类型(0,在线考试; 1,模拟考试; 2,在线练习)
        /// </summary>		
        public string ExamType
        {
            get { return _examtype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ExamType", value, value.ToString());

                _isChanged |= (_examtype != value); _examtype = value;
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
