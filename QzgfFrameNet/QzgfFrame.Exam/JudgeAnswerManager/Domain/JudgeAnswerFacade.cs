using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.JudgeAnswerManager.Models;

namespace QzgfFrame.Exam.JudgeAnswerManager.Domain
{
    /// <summary>
    /// "判断题"回答表
    /// </summary>
    public interface JudgeAnswerFacade
    {
        ExamJudgeAnswer Get(object id);

        bool Delete(string id);

        bool Save(ExamJudgeAnswer entity);

        bool Update(ExamJudgeAnswer entity);

        IList<ExamJudgeAnswer> LoadAll();

        IList<ExamJudgeAnswer> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
