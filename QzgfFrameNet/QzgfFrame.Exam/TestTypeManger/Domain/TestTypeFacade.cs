/*
 * 文件名.........: TestTypeFacade.cs
 * 作者...........:  
 * 说明...........: 试卷类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestTypeManger.Models;

namespace QzgfFrame.Exam.TestTypeManger.Domain

{
    public interface TestTypeFacade
    {
        ExamTestType Get(object id);

        ExamTestType Get(string order, string where);

        bool Delete(string id);

        bool Save(ExamTestType entity);

        bool Update(ExamTestType entity);

        IList<ExamTestType> LoadAll();

        IList<ExamTestType> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
