using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AnswerExamManager.Models;

namespace QzgfFrame.Exam.AnswerExamManager.Domain
{
    /// <summary>
    /// 考生考试回答表
    /// </summary>
    public interface AnswerExamFacade
    {
        ExamAnswerExam Get(object id);

        bool Delete(string id);

        bool Save(ExamAnswerExam entity);

        string Saves(ExamAnswerExam entity);

        bool Update(ExamAnswerExam entity);

        IList<ExamAnswerExam> LoadAll();

        IList<ExamAnswerExam> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
