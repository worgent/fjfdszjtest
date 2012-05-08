using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.MultipleAnswerManager.Models;

namespace QzgfFrame.Exam.MultipleAnswerManager.Domain
{
    /// <summary>
    ///"选择题"回答表
    /// </summary>
    public interface MultipleAnswerFacade
    {
        ExamMultipleAnswer Get(object id);

        bool Delete(string id);

        bool Save(ExamMultipleAnswer entity);

        bool Update(ExamMultipleAnswer entity);

        IList<ExamMultipleAnswer> LoadAll();

        IList<ExamMultipleAnswer> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
