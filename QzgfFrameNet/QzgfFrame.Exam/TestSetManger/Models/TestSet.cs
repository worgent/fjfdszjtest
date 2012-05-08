/*
 * 文件名.........: TestSet.cs
 * 作者...........:  
 * 说明...........: 试卷设置扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.ExamineeRangeManger.Models;

namespace QzgfFrame.Exam.TestSetManger.Models
{
    /// <summary>
    /// 试卷设置扩展信息类
    /// </summary>
    public class TestSet
    {
        /// <summary>
        /// 考生范围
        /// </summary>
        public virtual string examineeranges { get; set; }
        /// <summary>
        /// 可选考卷ID
        /// </summary>
        public virtual string testids { get; set; }
        /// <summary>
        /// 考卷设置信息类
        /// </summary>
        public virtual ExamTestSet ExamTestSet { get; set; }
        /// <summary>
        /// 考卷ID
        /// </summary>
        public virtual string TestID { get; set; }
        /// <summary>
        /// 考卷名称
        /// </summary>
        public virtual string TestName { get; set; }
        /// <summary>
        /// 考卷时长
        /// </summary>
        public virtual string TimeLength { get; set; }
        /// <summary>
        /// 考卷开始时间
        /// </summary>
        public virtual string BeginTime { get; set; }
        /// <summary>
        /// 考卷结束时间
        /// </summary>
        public virtual string EndTime { get; set; }
        /// <summary>
        /// 考卷创建时间
        /// </summary>
        public virtual DateTime? CreateTime { get; set; }
        /// <summary>
        /// 考卷状态
        /// </summary>
        public virtual string TestState { get; set; }
        /// <summary>
        /// 预览试卷页面地址
        /// </summary>
        public virtual string ViewTestUrl { get; set; }
        /// <summary>
        /// 进入“在线考试”页面地址
        /// </summary>
        public virtual string SetUrl { get; set; }
    }
}
