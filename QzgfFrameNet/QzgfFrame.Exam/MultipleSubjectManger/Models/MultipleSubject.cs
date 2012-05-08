/*
 * 文件名.........: MultipleSubject.cs
 * 作者...........:  
 * 说明...........: 选择题扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.MultipleSubjectManger.Models
{
    /// <summary>
    /// 带考生答案的选择题
    /// </summary>
    public class MultipleSubject
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 选项内容
        /// </summary>
        public virtual string MultipleContent { get; set; }
        /// <summary>
        /// 试题内容ID
        /// </summary>
        public virtual string SubjectID { get; set; }
        /// <summary>
        /// 选择题排序
        /// </summary>
        public virtual string OrderNo { get; set; }
        /// <summary>
        /// 选项是否是答案
        /// </summary>
        public virtual bool Answer { get; set; }
        /// <summary>
        /// 考生答案
        /// </summary>
        public virtual string CandidatesAnswer { get; set; }
        /// <summary>
        /// 该题的得分
        /// </summary>
        public virtual string ProblemScore { get; set; }
    }
}
