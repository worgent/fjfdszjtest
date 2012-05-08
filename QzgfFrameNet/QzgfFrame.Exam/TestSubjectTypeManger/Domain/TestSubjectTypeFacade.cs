/*
 * 文件名.........: TestSubjectTypeFacade.cs
 * 作者...........:  
 * 说明...........: 试卷试题类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;

namespace QzgfFrame.Exam.TestSubjectTypeManger.Domain

{
    public interface TestSubjectTypeFacade
    {

        ExamTestSubjectType Get(object id);

        IList<TestSubjectType> GetTestSubjectTypes(object id);

        bool Delete(string id);

        bool Save(ExamTestSubjectType entity);

        bool Update(ExamTestSubjectType entity);

        IList<ExamTestSubjectType> LoadAll();

        IList<ExamTestSubjectType> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
