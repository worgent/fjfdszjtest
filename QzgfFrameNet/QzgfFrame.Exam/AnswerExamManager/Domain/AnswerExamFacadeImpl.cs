using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.AnswerExamManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.AnswerExamManager.Domain
{
    /// <summary>
    /// 考生考试回答表
    /// </summary>
    public class AnswerExamFacadeImpl : AnswerExamFacade
    {
        /// <summary>
        /// 考生考试回答表
        /// </summary>
        private IRepository<ExamAnswerExam> answerExamRepository { set; get; }

        public ExamAnswerExam Get(object id)
        {
            return answerExamRepository.Get(id.ToString());
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
                result = answerExamRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamAnswerExam entity)
        {
            entity.Id = answerExamRepository.NewSequence("SYSTEM_MENU");
            return answerExamRepository.Save(entity);
        }

        /// <summary>
        /// 保存数据,并返回刚生成的id 
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public string Saves(ExamAnswerExam entity)
        {
            entity.Id = answerExamRepository.NewSequence("SYSTEM_MENU");
            if (answerExamRepository.Save(entity))
            {
                return entity.Id;
            }
            else
            {
                return "";
            }
        }


        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamAnswerExam entity)
        {
            return answerExamRepository.Update(entity);
        }

        public IList<ExamAnswerExam> LoadAll()
        {
            return answerExamRepository.LoadAll();
        }

        public IList<ExamAnswerExam> LoadAll(string order, string where)
        {
            return answerExamRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamAnswerExam";
            IList<ExamAnswerExam> ls = answerExamRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = answerExamRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
