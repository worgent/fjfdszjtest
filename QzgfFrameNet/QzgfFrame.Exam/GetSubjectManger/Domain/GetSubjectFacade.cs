/*
 * 文件名.........: GetSubjectFacade.cs
 * 作者...........:  
 * 说明...........: 随机获取试题业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectManger .Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.GetSubjectManger.Domain
{
    public interface GetSubjectFacade
    {
        IList<ExamSubjectContent> GetTestSubject(string typeid,string coursetypeid, int Number);
    }
}
