using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AchievementxManager.Models;

namespace QzgfFrame.Exam.AchievementxManager.Domain
{
    /// <summary>
    ///  成绩管理（详表）
    /// </summary>
    public interface AchievementxFacade
    {
        ExamAchievementx Get(object id);

        bool Delete(string id);

        bool Save(ExamAchievementx entity);

        bool Update(ExamAchievementx entity);

        IList<ExamAchievementx> LoadAll();

        IList<ExamAchievementx> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
