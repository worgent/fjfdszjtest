/*
 * 文件名.........: TestMockFacade.cs
 * 作者...........:  
 * 说明...........: 模拟试卷设置业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestMockManger.Models;

namespace QzgfFrame.Exam.TestMockManger.Domain

{
    public interface TestMockFacade
    {

        ExamTestMock Get(object id);

        TestMock GetTestMock(object id);

        bool Delete(string id);

        bool DeleteTestID(string id);

        bool Save(ExamTestMock entity);

        bool Update(ExamTestMock entity);

        IList<ExamTestMock> LoadAll();

        IList<ExamTestMock> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string userID, string Role);
    }
}
