/*
 * 文件名.........: TestFacadeEx.cs
 * 作者...........:  
 * 说明...........: 试卷业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/
using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.GetSubjectManger.Models;

namespace QzgfFrame.Controllers.Exam.ExaminerManger

{
    public interface TestFacadeEx
    {
         TestID GetTestContent(GetSubject entity);
         bool Save(TestContent entity);
         bool Update(TestContent entity);
         bool Delete(string id);
    }
}
