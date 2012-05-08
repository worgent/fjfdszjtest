using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.ItemPropertyManger.Models;
using QzgfFrame.Archives.ItemPropertyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.ItemPropertyManger.Domain
{
    public class ItemPropertyFacadeImpl : ItemPropertyFacade
    {
        private IRepository<ArchiveItemProperty> itemPropertyRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveItemProperty Get(object id)
        {
            return itemPropertyRepository.Get(id.ToString());
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='ItemProperty'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='ItemProperty'";
                    result = relationRepository.DeleteHql(sql);
                    result = itemPropertyRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='ItemProperty'";
                    result = relationRepository.DeleteHql(sql);
                    result = itemPropertyRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveItemProperty GetHql(string itemPropertyName)
        {
            string Hql = " ItemPropertyName = '" + itemPropertyName + "'";
            IList<ArchiveItemProperty> ItemPropertys = itemPropertyRepository.LoadAll("Id", Hql);
            if (ItemPropertys != null)
            {
                if (ItemPropertys.Count > 0)
                    return ItemPropertys[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveItemProperty entity)
        {
            entity.Id = itemPropertyRepository.NewSequence("SYSTEM_MENU");
            return itemPropertyRepository.Save(entity);
        }

        public bool Update(ArchiveItemProperty entity)
        {
            return itemPropertyRepository.Update(entity);
        }

        public IList<ArchiveItemProperty> LoadAll()
        {
            return itemPropertyRepository.LoadAll();
        }
        public IList<ArchiveItemProperty> LoadAll(string order, string where)
        {
            return itemPropertyRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveItemProperty";
            IList<ArchiveItemProperty> ls = itemPropertyRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = itemPropertyRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
