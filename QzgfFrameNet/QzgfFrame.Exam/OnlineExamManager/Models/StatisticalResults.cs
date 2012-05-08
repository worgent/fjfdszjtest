using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.OnlineExamManager.Models
{
    /// <summary>
    /// 试卷提交后，用于保存页面提交的答案、试卷试题id等数据
    /// </summary>
    public class StatisticalResults
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 试卷试题id
        /// </summary>
        public virtual string TestSubjectId { get; set; }
        /// <summary>
        /// 选择题序号
        /// </summary>
        public virtual string OrderNo { get; set; }
        /// <summary>
        /// 试题类型id
        /// </summary>
        public virtual string SubjectTypeID { get; set; }
        /// <summary>
        /// 答案
        /// </summary>
        public virtual string Answer { get; set; }
    }
}
