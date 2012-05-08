using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.AchievementManager.Models
{
    /// <summary>
    /// 考生考试排行榜
    /// </summary>
    public class TestList
    {
        /// <summary>
        /// 名次
        /// </summary>
        public virtual string Ranking { get; set; }
        /// <summary>
        /// 编号
        /// </summary>
        public virtual string Number { get; set; }
        /// <summary>
        /// 姓名
        /// </summary>
        public virtual string Name { get; set; }
        /// <summary>
        /// 总得分
        /// </summary>
        public virtual string TheTotalScore { get; set; }
        /// <summary>
        /// 是否及格
        /// </summary>
        public virtual string IsPassing { get; set; }
    }
}
