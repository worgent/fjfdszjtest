using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.MultipleAnswerManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.MultipleAnswerManager.Domain
{
    /// <summary>
    /// 巡检周期
    /// </summary>
    public class MultipleAnswerFacadeImpl : MultipleAnswerFacade
    {
        private IRepository<ExamMultipleAnswer> multipleAnswerRepository { set; get; }

        public ExamMultipleAnswer Get(object id)
        {
            return multipleAnswerRepository.Get(id.ToString());
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
                result = multipleAnswerRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamMultipleAnswer entity)
        {
            entity.Id = multipleAnswerRepository.NewSequence("SYSTEM_MENU");
            return multipleAnswerRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamMultipleAnswer entity)
        {
            return multipleAnswerRepository.Update(entity);
        }

        public IList<ExamMultipleAnswer> LoadAll()
        {
            return multipleAnswerRepository.LoadAll();
        }
        public IList<ExamMultipleAnswer> LoadAll(string order, string where)
        {
            return multipleAnswerRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamMultipleAnswer";
            IList<ExamMultipleAnswer> ls = multipleAnswerRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = multipleAnswerRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
