using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supervision.BackManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Supervision.BackManager.Domain
{
    /// <summary>
    /// 退回工单
    /// </summary>
    public class BackFacadeImpl : BackFacade
    {
        private IRepository<SupervisionBack> backRepository { set; get; }

        public SupervisionBack Get(object id)
        {
            return backRepository.Get(id.ToString());
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
                result = backRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SupervisionBack entity)
        {
            entity.Id = backRepository.NewSequence("SYSTEM_MENU");
            return backRepository.Save(entity);
        }

        public bool Update(SupervisionBack entity)
        {
            return backRepository.Update(entity);
        }

        public IList<SupervisionBack> LoadAll()
        {
            return backRepository.LoadAll();
        }
        public IList<SupervisionBack> LoadAll(string order, string where)
        {
            return backRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from SupervisionBack";
            IList<SupervisionBack> ls = backRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = backRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
