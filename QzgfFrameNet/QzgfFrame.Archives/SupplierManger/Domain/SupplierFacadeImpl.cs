using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SupplierManger.Models;
using QzgfFrame.Archives.SupplierManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SupplierManger.Domain
{
    public class SupplierFacadeImpl:SupplierFacade
    {
        private IRepository<ArchiveSupplier> supplierRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSupplier Get(object id)
        {
            return supplierRepository.Get(id.ToString());
        }
        public ArchiveSupplier Get(string order, string where)
        {
            IList<ArchiveSupplier> suppliers= supplierRepository.LoadAll(order, where);
            if (suppliers.Count > 0)
                return suppliers[0];
            else
                return null;
        }
        public ArchiveSupplier GetHql(string supplierName)
        {
            string Hql = " SupplierName like '%" + supplierName + "%'";
            IList<ArchiveSupplier> entitys = supplierRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='Supplier'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Supplier'";
                    result = relationRepository.DeleteHql(sql);
                    result = supplierRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Supplier'";
                    result = relationRepository.DeleteHql(sql);
                    result = supplierRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSupplier entity)
        {
            entity.Id = supplierRepository.NewSequence("SYSTEM_MENU");
            return supplierRepository.Save(entity);
        }

        public bool Update(ArchiveSupplier entity)
        {
            return supplierRepository.Update(entity);
        }

        public IList<ArchiveSupplier> LoadAll()
        {
            return supplierRepository.LoadAll();
        }
        public IList<ArchiveSupplier> LoadAll(string order, string where)
        {
            return supplierRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveSupplier";
            IList<ArchiveSupplier> ls = supplierRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = supplierRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ArchiveSupplier main where 1=1 ";
            hql += gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;

            IList<ArchiveSupplier> ls = supplierRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = supplierRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

    }
}
