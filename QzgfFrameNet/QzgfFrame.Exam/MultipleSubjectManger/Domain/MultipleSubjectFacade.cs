/*
 * 文件名.........: MultipleSubjectFacade.cs
 * 作者...........:  
 * 说明...........: 选择题业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.MultipleSubjectManger.Models;

namespace QzgfFrame.Exam.MultipleSubjectManger.Domain

{
    public interface MultipleSubjectFacade 
    {

        ExamMultipleSubject Get(object id);

        bool Delete(string id);

        bool Save(ExamMultipleSubject entity);

        bool Update(ExamMultipleSubject entity);

        IList<ExamMultipleSubject> LoadAll();

        IList<ExamMultipleSubject> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
