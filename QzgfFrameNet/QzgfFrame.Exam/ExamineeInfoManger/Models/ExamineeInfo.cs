/*
 * 文件名.........: ExamineeInfo.cs
 * 作者...........:  
 * 说明...........: 考生扩展信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Exam.ExamineeInfoManger.Models
{
	/// <summary>
	/// 考生信息表扩展
	/// </summary>
	[Serializable]
    public class ExamineeInfo
	{
        /// <summary>
        /// 考生信息
        /// </summary>
        public ExamExamineeInfo ExamExamineeInfo { get; set; }
        /// <summary>
        ///  考生ID
        /// </summary>
        public string ID { get; set; }
        /// <summary>
        ///  考生姓名
        /// </summary>
        public string FullName { get; set; }
        /// <summary>
        /// 性别
        /// </summary>
        public string Sex { get; set; }
        /// <summary>
        ///  考生登录名
        /// </summary>
        public string LoginName { get; set; }
        /// <summary>
        /// 身份证号码
        /// </summary>
        public string IDCardNumber { get; set; }
        /// <summary>
        /// 手机号码
        /// </summary>
        public string MobileNumber { get; set; }
        /// <summary>
        /// 学历文凭
        /// </summary>
        public string Diploma { get; set; }
        /// <summary>
        /// 学历文凭ID
        /// </summary>
        public string DiplomaID { get; set; }
        /// <summary>
        /// 出生日期
        /// </summary>
        public DateTime? BirthDate { get; set; }
        /// <summary>
        /// 考试类型
        /// </summary>
        public string ExamType { get; set; }
        /// <summary>
        /// 考生信息字符串
        /// </summary>
        public virtual string examineeinfos { get; set; }
	}
}