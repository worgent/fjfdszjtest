using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.WorkloadManger.Models;
using QzgfFrame.Supplies.WorkloadManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.WorkloadManger.Domain
{
    public class WorkloadFacadeImpl : WorkloadFacade
    {
        private IRepository<SuppliesWorkload> workloadRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesWorkload Get(object id)
        {
            return workloadRepository.Get(id.ToString());
        }
        
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = workloadRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SuppliesWorkload entity, string no)
        {
            entity.Id = workloadRepository.NewSequence("SYSTEM_MENU",no);
            return workloadRepository.Save(entity);
        }

        public bool Update(SuppliesWorkload entity)
        {
            return workloadRepository.Update(entity);
        }

        public IList<SuppliesWorkload> LoadAll()
        {
            return workloadRepository.LoadAll();
        }
        public IList<SuppliesWorkload> LoadAll(string order, string where)
        {
            return workloadRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from SuppliesWorkload";
            IList<SuppliesWorkload> ls = workloadRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = workloadRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
