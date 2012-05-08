using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SuppliesTypeManger.Domain
{
    public class SuppliesTypeFacadeImpl:SuppliesTypeFacade
    {
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSuppliesType Get(object id)
        {
            return suppliesTypeRepository.Get(id.ToString());
        }
        public ArchiveSuppliesType Get(string order, string where)
        {
            IList<ArchiveSuppliesType> suppliesTypes= suppliesTypeRepository.LoadAll(order, where);
            if (suppliesTypes.Count > 0)
                return suppliesTypes[0];
            else
                return null;
        }
        public ArchiveSuppliesType GetHql(string suppliesTypeName)
        {
            string Hql = " SuppliesTypeName like '%" + suppliesTypeName + "%'";
            IList<ArchiveSuppliesType> entitys = suppliesTypeRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
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
                string strsql = " CId='" + s + "' and RelationName='SuppliesType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SuppliesType'";
                    result = relationRepository.DeleteHql(sql);
                    result = suppliesTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SuppliesType'";
                    result = relationRepository.DeleteHql(sql);
                    result = suppliesTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSuppliesType entity)
        {
            entity.Id = suppliesTypeRepository.NewSequence("SYSTEM_MENU");
            return suppliesTypeRepository.Save(entity);
        }

        public bool Update(ArchiveSuppliesType entity)
        {
            return suppliesTypeRepository.Update(entity);
        }

        public IList<ArchiveSuppliesType> LoadAll()
        {
            return suppliesTypeRepository.LoadAll();
        }
        public IList<ArchiveSuppliesType> LoadAll(string order, string where)
        {
            return suppliesTypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveSuppliesType";
            IList<ArchiveSuppliesType> ls = suppliesTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = suppliesTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ArchiveSuppliesType main where 1=1 ";
            hql += gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;

            IList<ArchiveSuppliesType> ls = suppliesTypeRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = suppliesTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

    }
}
