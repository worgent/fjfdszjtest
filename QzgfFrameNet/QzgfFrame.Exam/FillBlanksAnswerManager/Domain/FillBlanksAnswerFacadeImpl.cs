using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.FillBlanksAnswerManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.FillBlanksAnswerManager.Domain
{
    /// <summary>
    /// 巡检周期
    /// </summary>
    public class FillBlanksAnswerFacadeImpl : FillBlanksAnswerFacade
    {
        private IRepository<ExamFillBlanksAnswer> fillBlanksAnswerRepository { set; get; }

        public ExamFillBlanksAnswer Get(object id)
        {
            return fillBlanksAnswerRepository.Get(id.ToString());
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
                result = fillBlanksAnswerRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamFillBlanksAnswer entity)
        {
            entity.Id = fillBlanksAnswerRepository.NewSequence("SYSTEM_MENU");
            return fillBlanksAnswerRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamFillBlanksAnswer entity)
        {
            return fillBlanksAnswerRepository.Update(entity);
        }

        public IList<ExamFillBlanksAnswer> LoadAll()
        {
            return fillBlanksAnswerRepository.LoadAll();
        }
        public IList<ExamFillBlanksAnswer> LoadAll(string order, string where)
        {
            return fillBlanksAnswerRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamFillBlanksAnswer";
            IList<ExamFillBlanksAnswer> ls = fillBlanksAnswerRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = fillBlanksAnswerRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
