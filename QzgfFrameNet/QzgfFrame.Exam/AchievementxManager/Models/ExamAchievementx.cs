using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.AchievementxManager.Models
{
    /// <summary>
    /// 成绩管理（详表）
    /// </summary>
    [Serializable]
    public class ExamAchievementx
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 题目顺序号
        /// </summary>
        private string _topicNum;
        /// <summary>
        /// 答案
        /// </summary>
        private string _answer;
        /// <summary>
        /// 得分
        /// </summary>
        private string _score;
        /// <summary>
        /// 成绩管理主表id
        /// </summary>
        private string _achievementId;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ExamAchievementx()
        {
            _id = String.Empty;
            _topicNum = String.Empty;
            _answer = String.Empty;
            _score = String.Empty;
            _achievementId = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ExamAchievementx(string id, string topicNum, string answer, string score, string achievementId)
        {
            _id = id;
            _topicNum = topicNum;
            _answer = answer;
            _score = score;
            _achievementId = achievementId;
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
        /// 题目顺序号
        /// </summary>		
        public string TopicNum
        {
            get { return _topicNum; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TopicNum", value, value.ToString());

                _isChanged |= (_topicNum != value); _topicNum = value;
            }
        }
        /// <summary>
        /// 答案
        /// </summary>		
        public string Answer
        {
            get { return _answer; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Answer", value, value.ToString());

                _isChanged |= (_answer != value); _answer = value;
            }
        }
        /// <summary>
        /// 得分
        /// </summary>		
        public string Score
        {
            get { return _score; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Score", value, value.ToString());

                _isChanged |= (_score != value); _score = value;
            }
        }
        /// <summary>
        /// 成绩管理主表id
        /// </summary>		
        public string AchievementId
        {
            get { return _achievementId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AchievementId", value, value.ToString());

                _isChanged |= (_achievementId != value); _achievementId = value;
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
