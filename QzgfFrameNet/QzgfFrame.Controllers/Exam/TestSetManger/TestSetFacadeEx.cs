/*
 * 文件名.........: TestSetFacadeEx.cs
 * 作者...........:  
 * 说明...........: 试卷设置业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Exam.TestSetManger.Models;

namespace QzgfFrame.Controllers.Exam.TestSetManger

{
    public interface TestSetFacadeEx
    {
        bool Save(TestSet entity);
        bool Update(TestSet entity);
        bool Delete(string id);
    }
}
