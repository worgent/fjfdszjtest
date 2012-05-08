/*
 * 文件名.........: TestSubjectFacade.cs
 * 作者...........:  
 * 说明...........: 试卷试题业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestSubjectManger.Models;

namespace QzgfFrame.Exam.TestSubjectManger.Domain

{
    public interface TestSubjectFacade
    {

        ExamTestSubject Get(object id);

        bool Delete(string id);

        bool Save(ExamTestSubject entity);

        bool Update(ExamTestSubject entity);

        IList<ExamTestSubject> LoadAll();

        IList<ExamTestSubject> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
