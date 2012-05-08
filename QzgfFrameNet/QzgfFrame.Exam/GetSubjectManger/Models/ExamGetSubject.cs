/*
 * 文件名.........: ExamGetSubject.cs
 * 作者...........:  
 * 说明...........: 随机获取试题信息类 
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
	/// 取题条件
	/// </summary>
	[Serializable]
    public sealed class ExamGetSubject
	{

		#region Public Properties
        /// <summary>
        /// 题型名称
        /// </summary>
        public string SubjectTypeName
        {
            get;
            set;
        }

        /// <summary>
        /// 题型名称ID
        /// </summary>
        public string SubjectTypeID
        {
            get;
            set;
        }

        /// <summary>
        /// 题型数量
        /// </summary>
        public int SubjectTypeNumber
        {
            get;
            set;
        }

        /// <summary>
        /// 每小题分值
        /// </summary>
        public int SubjectTypeScore
        {
            get;
            set;
        }

        /// <summary>
        /// 顺序
        /// </summary>
        public string OrderNo
        {
            get;
            set;
        }
		
		#endregion 


    }
}