/*
 * 文件名.........: TestMockFacadeEx.cs
 * 作者...........:  
 * 说明...........: 模拟试卷设置业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Exam.TestMockManger.Models;

namespace QzgfFrame.Controllers.Exam.TestMockManger

{
    public interface TestMockFacadeEx
    {
        bool Save(TestMock entity);
        bool Update(TestMock entity);
        bool Delete(string id);
    }
}
