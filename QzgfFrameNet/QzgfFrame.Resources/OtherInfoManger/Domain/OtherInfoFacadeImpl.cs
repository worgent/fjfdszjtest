using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.OtherInfoManger.Domain
{
    public class OtherInfoFacadeImpl : OtherInfoFacade
    {
        private IRepository<ResourceOtherInfo> infoRepository { set; get; }

        public ResourceOtherInfo Get(object id)
        {
            return infoRepository.Get(id.ToString());
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string hql)
        {
            return infoRepository.DeleteHql(hql);
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string hql)
        {
            return infoRepository.Update(" DelFlag='1' where "+hql);
        }

        public bool Save(ResourceOtherInfo entity, string no)
        {
            entity.Id = infoRepository.NewSequence("SYSTEM_MENU",no);
            return infoRepository.Save(entity);
        }

        public bool Update(ResourceOtherInfo entity)
        {
            return infoRepository.Update(entity);
        }

        public IList<ResourceOtherInfo> LoadAll()
        {
            return infoRepository.LoadAll();
        }
        public IList<ResourceOtherInfo> LoadAll(string order, string where)
        {
            return infoRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourceOtherInfo";
            IList<ResourceOtherInfo> ls = infoRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = infoRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
