/*
 * 文件名.........: ExamTestSubjectType.cs
 * 作者...........:  
 * 说明...........: 试卷试题类型扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.GetSubjectManger.Models;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Models;

namespace QzgfFrame.Exam.TestSubjectTypeManger.Models
{
    public class TestSubjectType
    {
        public virtual string testsubjectPoints { get; set; }
        public virtual ExamTestSubjectType ExamTestSubjectType { get; set; }
        public virtual IList<ExamTestSubject> TestSubjects { get; set; }
        public virtual IList<SubjectContent> SubjectContents { get; set; }
    }
}
