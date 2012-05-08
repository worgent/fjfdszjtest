using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AchievementManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Exam.AnswerExamManager.Models;

namespace QzgfFrame.Exam.AchievementManager.Domain
{
    /// <summary>
    /// 考生做过的试卷表
    /// </summary>
    public class AchievementFacadeImpl : AchievementFacade
    {
        #region

        /// <summary>
        /// 考生做过的试卷表
        /// </summary>
        private IRepository<ExamAchievement> achievementRepository { set; get; }
        /// <summary>
        /// 考生考试回答表
        /// </summary>
        private IRepository<ExamAnswerExam> answerExamRepository { set; get; }

        #endregion


        public ExamAchievement Get(object id)
        {
            return achievementRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = achievementRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamAchievement entity)
        {
            entity.Id = achievementRepository.NewSequence("SYSTEM_MENU");
            return achievementRepository.Save(entity);
        }

        /// <summary>
        /// 保存数据,并返回刚生成的id 
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public string Saves(ExamAchievement entity)
        {
            entity.Id = achievementRepository.NewSequence("SYSTEM_MENU");
            if (achievementRepository.Save(entity))
            {
                return entity.Id;
            }else
            {
                return "";
            }
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamAchievement entity)
        {
            return achievementRepository.Update(entity);
        }

        public IList<ExamAchievement> LoadAll()
        {
            return achievementRepository.LoadAll();
        }

        public IList<ExamAchievement> LoadAll(string order, string where)
        {
            return achievementRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string gridsearch, string userId)
        {

            string hql = " select a.Id,a.AnswerStartTime,b.FullName,c.Points,c.Name " +
                        " from ExamAchievement a,ExamExamineeInfo b,ExamTestContent c " +
                        " where a.UserId = b.ID and a.TestId = c.id and a.UserId = '" + userId + "' ";

            //判断考试类型：“在线考试”/“模拟考试”/“在线练习”
            if (gridsearch != null)
            {
                hql += " and a.ExamType = '" + gridsearch + "' ";
            }
            hql += " order by a.Id desc ";

            IList<object[]> ls = achievementRepository.FindByLinkPage(pageNo, pageSize, hql);

            IList<Achievement> achievements = new List<Achievement>();
            for (int i = 0; i < ls.Count; i++)
            {
                Achievement achievement = new Achievement();
                string id = "";
                int Scores = 0;
                string sql = "";

                if (ls[i][0] != null)
                {
                    
                    id = ls[i][0].ToString();
                    sql = " AchievementId = '" + id + "' ";
                    IList<ExamAnswerExam> examAnswerExams = new List<ExamAnswerExam>();
                    examAnswerExams = answerExamRepository.LoadAll("id", sql);
                    if (examAnswerExams != null)
                    {
                        foreach (var examAnswerExam in examAnswerExams)
                        {
                            Scores += Convert.ToInt32(examAnswerExam.ProblemScore);
                        }
                    }
                }
                achievement.Operating = "<a href='/Exam/Achievement/ExamRecords/" + id + "'>查看答案</a>";
                achievement.ViewRankings = "<a href='/Exam/Achievement/Ranking/" + id + "'>查看排名</a>";
                achievement.AnswerStartTime = ls[i][1] != null ? ls[i][1].ToString() : null;
                achievement.UserName = ls[i][2] != null ? ls[i][2].ToString() : null;
                achievement.Fraction = ls[i][3] != null ? ls[i][3].ToString() : null;
                achievement.Score = Scores.ToString();
                achievement.TestName = ls[i][4] != null ? ls[i][4].ToString() : null;
                achievements.Add(achievement);
            }

            string rowsjson = JsonConvert.SerializeObject(achievements);
            int recordCount = achievementRepository.FindByPageLinkCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        /// <summary>
        /// 显示“考试排名”页面
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindByListPage(int pageNo, int pageSize, string gridsearch)
        {
            string hql = "select b.Username,a.AnswerStartTime,c.Name,a.Id from ExamAchievement a,SystemUser b,ExamTestContent c " +
                        " where a.UserId = b.id and a.TestId = c.id";
            if (gridsearch != null)
            {
                hql += " and a.ExamType = '" + gridsearch + "' ";
            }
            hql += " order by a.Id desc ";

            IList<object[]> ls = achievementRepository.FindByLinkPage(pageNo, pageSize, hql);

            IList<Achievement> achievements = new List<Achievement>();
            for (int i = 0; i < ls.Count; i++)
            {
                Achievement achievement = new Achievement();
                achievement.UserName = ls[i][0] != null ? ls[i][0].ToString() : null;
                achievement.AnswerStartTime = ls[i][1] != null ? ls[i][1].ToString() : null;
                achievement.Fraction = "100";
                achievement.TestName = ls[i][2] != null ? ls[i][2].ToString() : null;
                string id = ls[i][3] != null ? ls[i][3].ToString() : null;
                achievement.Operating = "<a href='/Exam/Achievement/ExamRecords/" + id + "'>查看答案</a>";
                achievement.ViewRankings = "<a href='/Exam/Achievement/Ranking/" + id + "'>查看排名</a>";
                achievements.Add(achievement);
            }

            string rowsjson = JsonConvert.SerializeObject(achievements);
            int recordCount = achievementRepository.FindByPageLinkCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
