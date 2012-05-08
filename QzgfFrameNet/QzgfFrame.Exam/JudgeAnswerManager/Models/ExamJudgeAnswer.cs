using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.JudgeAnswerManager.Models
{
    /// <summary>
    /// "判断题"回答表
    /// </summary>
    [Serializable]
    public sealed class ExamJudgeAnswer
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 考生考试回答表id
        /// </summary>
        private string _answerexamid;
        /// <summary>
        /// 回答内容
        /// </summary>
        private string _answercontent;


        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ExamJudgeAnswer()
        {
            _id = String.Empty;
            _answerexamid = String.Empty;
            _answercontent = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ExamJudgeAnswer(string id, string answerexamid, string answercontent)
        {
            _id = id;
            _answerexamid = answerexamid;
            _answercontent = answercontent;
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
        /// 考生考试回答表id
        /// </summary>		
        public string AnswerExamId
        {
            get { return _answerexamid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AnswerExamId", value, value.ToString());

                _isChanged |= (_answerexamid != value); _answerexamid = value;
            }
        }
        /// <summary>
        /// 回答内容
        /// </summary>		
        public string AnswerContent
        {
            get { return _answercontent; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AnswerContent", value, value.ToString());

                _isChanged |= (_answercontent != value); _answercontent = value;
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
