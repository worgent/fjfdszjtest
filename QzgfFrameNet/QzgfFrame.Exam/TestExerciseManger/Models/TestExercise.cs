/*
 * 文件名.........: TestExercise.cs
 * 作者...........:  
 * 说明...........: 练习试卷设置扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestExerciseManger.Models;
using QzgfFrame.Exam.ExamineeRangeManger.Models;

namespace QzgfFrame.Exam.TestExerciseManger.Models
{
    public class TestExercise
    {
        public virtual string examineeranges { get; set; }
        public virtual ExamTestExercise ExamTestExercise { get; set; }
        public virtual string TestID { get; set; }
        public virtual string TestName { get; set; }
        public virtual DateTime? CreateTime { get; set; }
        /// <summary>
        /// 预览试卷页面地址
        /// </summary>
        public virtual string ViewTestUrl { get; set; }
        /// <summary>
        /// 进入“在线练习”页面地址
        /// </summary>
        public virtual string ExerciseUrl { get; set; }
    }
}
