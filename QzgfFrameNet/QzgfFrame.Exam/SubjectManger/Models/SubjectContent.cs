/*
 * 文件名.........: SubjectContent.cs
 * 作者...........:  
 * 说明...........: 试题内容扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Models;

namespace QzgfFrame.Exam.SubjectManger.Models
{
    public  class  SubjectContent
    {
        /// <summary>
        /// 试题等级类型
        /// </summary>
        public virtual string ScaleGradeType { get; set; }
        /// <summary>
        /// 试题科目类型
        /// </summary>
        public virtual string CourseName { get; set; }
        /// <summary>
        /// 试题类型
        /// </summary>
        public virtual string SubjectType { get; set; }
        /// <summary>
        /// 试题ID
        /// </summary>
        public virtual string ID { get; set; }
        /// <summary>
        /// 试题内容
        /// </summary>
        public virtual string Content { get; set; }
        /// <summary>
        /// 试题创建时间
        /// </summary>
        public virtual DateTime? CreateTime { get; set; }
        /// <summary>
        /// 状态
        /// </summary>
        public virtual string State { get; set; }
        /// <summary>
        /// 试题类模型
        /// </summary>
        public virtual ExamSubjectContent ExamSubContent { get; set; }     
        /// <summary>
        /// 选择题集合
        /// </summary>
        public virtual IList<ExamMultipleSubject> MultipleSubject { get; set; }
        /// <summary>
        /// 填空题集合
        /// </summary>
        public virtual IList<ExamFillBlanksSubject> FillBlanksSubject { get; set; }
        /// <summary>
        /// 判断题集合
        /// </summary>
        public virtual IList<ExamJudgeSubject> JudgeSubject { get; set; }
        /// <summary>
        /// 保存选择题字符串
        /// </summary>
        public virtual string multiples { get; set; }
        /// <summary>
        /// 保存填空题字符串
        /// </summary>
        public virtual string fillblanks { get; set; }
        /// <summary>
        /// 保存判断题字符串
        /// </summary>
        public virtual string judges { get; set; }

        /// <summary>
        /// 带考生答案的选择题集合
        /// </summary>
        public virtual IList<MultipleSubject> MultipleSubjectAnswer { get; set; }
        /// <summary>
        /// 带考生答案的判断题集合
        /// </summary>
        public virtual IList<JudgeSubject> JudgeSubjectAnswer { get; set; }
        /// <summary>
        /// 带考生答案的填空题集合
        /// </summary>
        public virtual IList<FillBlanksSubject> FillBlanksSubjectAnswer { get; set; }
    }
}
