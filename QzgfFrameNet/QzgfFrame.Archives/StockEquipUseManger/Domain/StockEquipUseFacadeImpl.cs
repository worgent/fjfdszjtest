using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.StockEquipUseManger.Models;
using QzgfFrame.Archives.StockEquipUseManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.StockEquipUseManger.Domain
{
    public class StockEquipUseFacadeImpl:StockEquipUseFacade
    {
        private IRepository<ArchiveStockEquipUse> stockEquipUseRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveStockEquipUse Get(object id)
        {
            return stockEquipUseRepository.Get(id.ToString());
        }
        public ArchiveStockEquipUse Get(string order, string where)
        {
            IList<ArchiveStockEquipUse> stockEquipUses= stockEquipUseRepository.LoadAll(order, where);
            if (stockEquipUses.Count > 0)
                return stockEquipUses[0];
            else
                return null;
        }
        public ArchiveStockEquipUse GetHql(string stockEquipUseName)
        {
            string Hql = " StockEquipUseName like '%" + stockEquipUseName + "%'";
            IList<ArchiveStockEquipUse> entitys = stockEquipUseRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='StockEquipUse'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='StockEquipUse'";
                    result = relationRepository.DeleteHql(sql);
                    result = stockEquipUseRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='StockEquipUse'";
                    result = relationRepository.DeleteHql(sql);
                    result = stockEquipUseRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveStockEquipUse entity)
        {
            entity.Id = stockEquipUseRepository.NewSequence("SYSTEM_MENU");
            return stockEquipUseRepository.Save(entity);
        }

        public bool Update(ArchiveStockEquipUse entity)
        {
            return stockEquipUseRepository.Update(entity);
        }

        public IList<ArchiveStockEquipUse> LoadAll()
        {
            return stockEquipUseRepository.LoadAll();
        }
        public IList<ArchiveStockEquipUse> LoadAll(string order, string where)
        {
            return stockEquipUseRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveStockEquipUse";
            IList<ArchiveStockEquipUse> ls = stockEquipUseRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = stockEquipUseRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
