/*
 * 文件名.........: TestContent.cs
 * 作者...........:  
 * 说明...........: 试卷内容扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.GetSubjectManger.Models;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestMockManger.Models;
using QzgfFrame.Exam.TestExerciseManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Models;

namespace QzgfFrame.Exam.TestManger.Models
{
    public class TestContent
    {
        /// <summary>
        /// 试卷ID
        /// </summary>
        public virtual string TestID { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        public virtual string TestName { get; set; }
        /// <summary>
        /// 试卷总分
        /// </summary>
        public virtual string Points { get; set; }
        /// <summary>
        /// 当前试卷总分
        /// </summary>
        public virtual string CurrentPoints { get; set; }
        /// <summary>
        /// 创建时间
        /// </summary>
        public virtual DateTime? CreateTime { get; set; }
        /// <summary>
        /// 试卷类型
        /// </summary>
        public virtual string TestType { get; set; }
        /// <summary>
        /// 试卷内容
        /// </summary>
        public virtual ExamTestContent ExamTestContent { get; set; }
        /// <summary>
        /// 试卷设置表
        /// </summary>
        public virtual ExamTestSet ExamTestSet { get; set; }
        /// <summary>
        /// 模拟考试表
        /// </summary>
        public virtual ExamTestMock ExamTestMock { get; set; }
        /// <summary>
        /// 在线练习考试表
        /// </summary>
        public virtual ExamTestExercise ExamTestExercise { get; set; }
        /// <summary>
        /// 随机试卷内容
        /// </summary>
        public virtual ExamGetSubject ExamGetSubject { get; set; }
        /// <summary>
        /// 试卷试题类型集合
        /// </summary>
        public virtual IList<TestSubjectType> TestSubjectsType { get; set; }
        /// <summary>
        /// 试卷试题类型字符串
        /// </summary>
        public virtual string testsubjecttypes { get; set; }
        /// <summary>
        /// 试卷试题字符串
        /// </summary>
        public virtual string testsubjects { get; set; }
        /// <summary>
        /// 试卷状态
        /// </summary>
        public virtual string TestState { get; set; }
        /// <summary>
        /// 预览试卷页面地址
        /// </summary>
        public virtual string ViewTestUrl { get; set; }
        /// <summary>
        /// “在线练习”页面数据
        /// </summary>
        public virtual string exercisesData { get; set; }
    }
}
