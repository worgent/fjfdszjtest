using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.FiberCoreManger.Domain
{
    public class FiberCoreFacadeImpl : FiberCoreFacade
    {
        private IRepository<ResourceFiberCore> fiberCoreRepository { set; get; }

        public ResourceFiberCore Get(object id)
        {
            return fiberCoreRepository.Get(id.ToString());
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
                string hql = " LineId='" + s + "'";
                result = fiberCoreRepository.DeleteHql(hql);            
            }
           return result;
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string hql = " DelFlag='1' where LineId='" + s + "'";
                result = fiberCoreRepository.Update(hql);
            }
            return result;
        }

        public bool Save(ResourceFiberCore entity,string no)
        {
            entity.Id = fiberCoreRepository.NewSequence("SYSTEM_MENU",no);
            return fiberCoreRepository.Save(entity);
        }

        public bool Update(ResourceFiberCore entity)
        {
            return fiberCoreRepository.Update(entity);
        }

        public IList<ResourceFiberCore> LoadAll()
        {
            return fiberCoreRepository.LoadAll();
        }
        public IList<ResourceFiberCore> LoadAll(string order, string where)
        {
            return fiberCoreRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourceFiberCore";
            IList<ResourceFiberCore> ls = fiberCoreRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = fiberCoreRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
