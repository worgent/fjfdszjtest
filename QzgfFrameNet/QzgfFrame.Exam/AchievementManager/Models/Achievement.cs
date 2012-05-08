using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.AchievementManager.Models
{
    /// <summary>
    /// 考生已考过试卷
    /// </summary>
    public class Achievement
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 操作（如：查看试卷等）
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// 查看排名
        /// </summary>
        public virtual string ViewRankings { get; set; }
        /// <summary>
        /// 查看排名
        /// </summary>
        public virtual string Ranking { get; set; }
        /// <summary>
        /// 考生姓名
        /// </summary>
        public virtual string UserName { get; set; }
        /// <summary>
        /// 考生开始答题时间
        /// </summary>
        public virtual string AnswerStartTime { get; set; }
        /// <summary>
        /// 试卷总分
        /// </summary>
        public virtual string Fraction { get; set; }
        /// <summary>
        /// 考生得分
        /// </summary>
        public virtual string Score { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        public virtual string TestName { get; set; }
    }
}
