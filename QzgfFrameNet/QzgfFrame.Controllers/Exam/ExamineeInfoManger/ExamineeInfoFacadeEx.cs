/*
 * 文件名.........: ExamineeInfoFacadeEx.cs
 * 作者...........:  
 * 说明...........: 考生信息业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamineeInfoManger.Models;

namespace QzgfFrame.Controllers.Exam.ExamineeInfoManger
{
    public interface ExamineeInfoFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
