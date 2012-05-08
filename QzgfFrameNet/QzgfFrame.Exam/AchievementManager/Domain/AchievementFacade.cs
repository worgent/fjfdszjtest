using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AchievementManager.Models;

namespace QzgfFrame.Exam.AchievementManager.Domain
{
    /// <summary>
    /// 考生做过的试卷表
    /// </summary>
    public interface AchievementFacade
    {
        ExamAchievement Get(object id);

        bool Delete(string id);

        bool Save(ExamAchievement entity);

        string Saves(ExamAchievement entity);

        bool Update(ExamAchievement entity);

        IList<ExamAchievement> LoadAll();

        IList<ExamAchievement> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string gridsearch, string userId);

        /// <summary>
        /// 显示“考试排名”页面
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        string FindByListPage(int pageNo, int pageSize, string gridsearch);
    }
}
