/****************************************************************** 
 * 文件名.........: TestIDState.cs
 * 作者...........: 
 * 说明...........: 试卷状态信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
 * ******************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.TestManger.Models
{
    /// <summary>
    /// 试卷状态信息类
    /// </summary>
    public class TestIDState
    {
        public virtual string ID { get; set; }
        public virtual string State { get; set; }
    }
}
