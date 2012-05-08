/*
 * 文件名.........: FillBlanksSubject.cs
 * 作者...........:  
 * 说明...........: 填空题扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.FillBlanksSubjectManger.Models
{
    /// <summary>
    /// 带考生答案的填空题
    /// </summary>
    public class FillBlanksSubject
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 试题内容ID
        /// </summary>
        public virtual string SubjectID { get; set; }
        /// <summary>
        /// 空格题排序
        /// </summary>
        public virtual string OrderNo { get; set; }
        /// <summary>
        /// 填空题答案
        /// </summary>
        public virtual string Answer { get; set; }
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
