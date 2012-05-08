/*
 * 文件名.........: FillBlanksSubjectFacade.cs
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
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;

namespace QzgfFrame.Exam.FillBlanksSubjectManger.Domain

{
    public interface FillBlanksSubjectFacade
    {

        ExamFillBlanksSubject Get(object id);

        bool Delete(string id);

        bool Save(ExamFillBlanksSubject entity);

        bool Update(ExamFillBlanksSubject entity);

        IList<ExamFillBlanksSubject> LoadAll();

        IList<ExamFillBlanksSubject> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
