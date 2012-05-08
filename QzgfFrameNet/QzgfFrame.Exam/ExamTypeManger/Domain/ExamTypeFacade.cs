/*
 * 文件名.........: ExamTypeFacade.cs
 * 作者...........:  
 * 说明...........: 考试类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.ExamTypeManger.Models;

namespace QzgfFrame.Exam.ExamTypeManger.Domain

{
    public interface ExamTypeFacade
    {
        ExamType Get(object id);

        ExamType Get(string order, string where);

        bool Delete(string id);

        ExamType GetHql(string type);

        bool Save(ExamType entity);

        bool Update(ExamType entity);

        IList<ExamType> LoadAll();

        IList<ExamType> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
