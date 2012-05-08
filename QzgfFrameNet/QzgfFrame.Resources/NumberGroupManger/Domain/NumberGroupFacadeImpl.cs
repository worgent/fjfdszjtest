using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.NumberGroupManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.NumberGroupManger.Domain
{
    public class NumberGroupFacadeImpl : NumberGroupFacade
    {
        private IRepository<ResourceNumberGroup> numberRepository { set; get; }

        public ResourceNumberGroup Get(object id)
        {
            return numberRepository.Get(id.ToString());
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
                result = numberRepository.DeleteHql(hql);   
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
                result = numberRepository.Update(hql);
            }
            return result;
        }
        public bool Save(ResourceNumberGroup entity, string no)
        {
            entity.Id = numberRepository.NewSequence("SYSTEM_MENU",no);
            return numberRepository.Save(entity);
        }

        public bool Update(ResourceNumberGroup entity)
        {
            return numberRepository.Update(entity);
        }

        public IList<ResourceNumberGroup> LoadAll()
        {
            return numberRepository.LoadAll();
        }
        public IList<ResourceNumberGroup> LoadAll(string order, string where)
        {
            return numberRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourceNumberGroup";
            IList<ResourceNumberGroup> ls = numberRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = numberRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
