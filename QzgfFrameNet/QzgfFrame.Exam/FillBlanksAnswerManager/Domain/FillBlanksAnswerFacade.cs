using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.FillBlanksAnswerManager.Models;

namespace QzgfFrame.Exam.FillBlanksAnswerManager.Domain
{
    /// <summary>
    /// "填空题"回答表
    /// </summary>
    public interface FillBlanksAnswerFacade
    {
        ExamFillBlanksAnswer Get(object id);

        bool Delete(string id);

        bool Save(ExamFillBlanksAnswer entity);

        bool Update(ExamFillBlanksAnswer entity);

        IList<ExamFillBlanksAnswer> LoadAll();

        IList<ExamFillBlanksAnswer> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
