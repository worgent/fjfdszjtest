/*
 * 文件名.........: GetSubject.cs
 * 作者...........:  
 * 说明...........: 随机获取试题扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using System.Collections.Generic;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.GetSubjectManger.Models
{
	/// <summary>
	/// 取题条件集合
	/// </summary>
    public class GetSubject
	{
        /// <summary>
        /// 试卷类型ID
        /// </summary>
        public virtual string CourseTypeID { get; set; }
        /// <summary>
        /// 创建者ID
        /// </summary>
        public virtual string CreatorID { get; set; }
        public virtual IList<ExamGetSubject> ExamGetSubject { get; set; }
        public virtual string getsubjects { get; set; }
    }
}