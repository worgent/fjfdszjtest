using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.AnswerExamManager.Models
{
    /// <summary>
    /// 考生考试回答表
    /// </summary>
    [Serializable]
    public sealed class ExamAnswerExam
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 考生做过的试卷表的id 
        /// </summary>
        private string _achievementid;
        /// <summary>
        /// 试卷试题ID
        /// </summary>
        private string _testsubjectid;
        /// <summary>
        /// 该题的得分
        /// </summary>
        private string _problemscore;
        /// <summary>
        /// 考生答案
        /// </summary>
        private string _subjectanswer;
        /// <summary>
        /// 评语
        /// </summary>
        private string _comments;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ExamAnswerExam()
        {
            _id = String.Empty;
            _achievementid = String.Empty;
            _testsubjectid = String.Empty;
            _problemscore = String.Empty;
            _subjectanswer = String.Empty;
            _comments = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ExamAnswerExam(string id, string achievementid, string testsubjectid, string problemscore, string subjectanswer,
            string comments)
        {
            _id = id;
            _achievementid = achievementid;
            _testsubjectid = testsubjectid;
            _problemscore = problemscore;
            _subjectanswer = subjectanswer;
            _comments = comments;
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
        /// 考生做过的试卷表的id
        /// </summary>		
        public string AchievementId
        {
            get { return _achievementid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AchievementId", value, value.ToString());

                _isChanged |= (_achievementid != value); _achievementid = value;
            }
        }

        /// <summary>
        /// 试卷试题ID
        /// </summary>		
        public string TestSubjectId
        {
            get { return _testsubjectid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TestSubjectId", value, value.ToString());

                _isChanged |= (_testsubjectid != value); _testsubjectid = value;
            }
        }

        /// <summary>
        /// 该题的得分
        /// </summary>		
        public string ProblemScore
        {
            get { return _problemscore; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ProblemScore", value, value.ToString());

                _isChanged |= (_problemscore != value); _problemscore = value;
            }
        }

        /// <summary>
        /// 考生答案
        /// </summary>
        public string SubjectAnswer
        {
            get { return _subjectanswer; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SubjectAnswer", value, value.ToString());

                _isChanged |= (_subjectanswer != value); _subjectanswer = value;
            }
        }

        /// <summary>
        /// 评语
        /// </summary>		
        public string Comments
        {
            get { return _comments; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Comments", value, value.ToString());

                _isChanged |= (_comments != value); _comments = value;
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
