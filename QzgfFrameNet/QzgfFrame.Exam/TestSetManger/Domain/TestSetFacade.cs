/*
 * 文件名.........: TestSetFacade.cs
 * 作者...........:  
 * 说明...........: 试卷设置业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestSetManger.Models;

namespace QzgfFrame.Exam.TestSetManger.Domain

{
    public interface TestSetFacade
    {

        ExamTestSet Get(object id);

        TestSet GetTestSet(object id);

        bool Delete(string id);

        bool DeleteTestID(string id);

        bool Save(ExamTestSet entity);

        bool Update(ExamTestSet entity);

        IList<ExamTestSet> LoadAll();

        IList<ExamTestSet> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string userID,string Role);
    }
}
