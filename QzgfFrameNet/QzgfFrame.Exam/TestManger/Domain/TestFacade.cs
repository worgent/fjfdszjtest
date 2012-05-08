/*
 * 文件名.........: TestFacade.cs
 * 作者...........:  
 * 说明...........: 试卷业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestManger.Models;

namespace QzgfFrame.Exam.TestManger.Domain

{
    public interface TestFacade
    {

        ExamTestContent Get(object id);

        TestContent GetContent(object id);

        bool Delete(string id);

        bool Save(ExamTestContent entity);

        bool Update(ExamTestContent entity);

        IList<ExamTestContent> LoadAll();

        IList<ExamTestContent> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string typeid, string testid);
    }
}
