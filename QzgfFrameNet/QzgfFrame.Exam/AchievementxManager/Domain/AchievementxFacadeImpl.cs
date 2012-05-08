using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AchievementxManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.AchievementxManager.Domain
{
    /// <summary>
    /// 成绩管理（详表）
    /// </summary>
    public class AchievementxFacadeImpl : AchievementxFacade
    {
        private IRepository<ExamAchievementx> achievementxRepository { set; get; }

        public ExamAchievementx Get(object id)
        {
            return achievementxRepository.Get(id.ToString());
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
                result = achievementxRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamAchievementx entity)
        {
            entity.Id = achievementxRepository.NewSequence("SYSTEM_MENU");
            return achievementxRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamAchievementx entity)
        {
            return achievementxRepository.Update(entity);
        }

        public IList<ExamAchievementx> LoadAll()
        {
            return achievementxRepository.LoadAll();
        }
        public IList<ExamAchievementx> LoadAll(string order, string where)
        {
            return achievementxRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamAchievementx";
            IList<ExamAchievementx> ls = achievementxRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = achievementxRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
