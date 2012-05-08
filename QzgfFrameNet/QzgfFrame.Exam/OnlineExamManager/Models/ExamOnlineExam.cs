using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.OnlineExamManager.Models
{
    /// <summary>
    /// 在线考试
    /// </summary>
    public class ExamOnlineExam
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 操作（如：列表中的“进入考场”，“开始考试”等）
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        public virtual string PaperName { get; set; }
        /// <summary>
        /// 考试时长
        /// </summary>
        public virtual string LongExamination { get; set; }
        /// <summary>
        /// 开始时间
        /// </summary>
        public virtual string StartTime { get; set; }
        /// <summary>
        /// 结束时间
        /// </summary>
        public virtual string EndTime { get; set; }
        /// <summary>
        /// 总分
        /// </summary>
        public virtual string Score { get; set; }
        /// <summary>
        /// 试卷类型
        /// </summary>
        public virtual string PaperType { get; set; }
    }
}
