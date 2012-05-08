/*
 * 文件名.........: TestID.cs
 * 作者...........:  
 * 说明...........: 试卷ID信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Exam.TestManger.Models
{
    public class TestID
    {
        public virtual string ID { get; set; }
        public virtual bool result { get; set; }
        public virtual string msg { get; set; }
    }
}
