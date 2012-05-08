using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.JudgeAnswerManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.JudgeAnswerManager.Domain
{
    /// <summary>
    /// "判断题"回答表
    /// </summary>
    public class JudgeAnswerFacadeImpl : JudgeAnswerFacade
    {
        private IRepository<ExamJudgeAnswer> judgeAnswerRepository { set; get; }

        public ExamJudgeAnswer Get(object id)
        {
            return judgeAnswerRepository.Get(id.ToString());
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
                result = judgeAnswerRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamJudgeAnswer entity)
        {
            entity.Id = judgeAnswerRepository.NewSequence("SYSTEM_MENU");
            return judgeAnswerRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamJudgeAnswer entity)
        {
            return judgeAnswerRepository.Update(entity);
        }

        public IList<ExamJudgeAnswer> LoadAll()
        {
            return judgeAnswerRepository.LoadAll();
        }
        public IList<ExamJudgeAnswer> LoadAll(string order, string where)
        {
            return judgeAnswerRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamJudgeAnswer";
            IList<ExamJudgeAnswer> ls = judgeAnswerRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = judgeAnswerRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
