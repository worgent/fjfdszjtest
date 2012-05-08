using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.StockTypeManger.Models;
using QzgfFrame.Archives.StockTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.StockTypeManger.Domain
{
    public class StockTypeFacadeImpl:StockTypeFacade
    {
        private IRepository<ArchiveStockType> stockTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveStockType Get(object id)
        {
            return stockTypeRepository.Get(id.ToString());
        }
        public ArchiveStockType Get(string order, string where)
        {
            IList<ArchiveStockType> StockTypes = stockTypeRepository.LoadAll(order, where);
            if (StockTypes.Count > 0)
                return StockTypes[0];
            else
                return null;
        }
        public ArchiveStockType GetHql(string StockTypeName)
        {
            string Hql = " StockTypeName like '%" + StockTypeName + "%'";
            IList<ArchiveStockType> entitys = stockTypeRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='StockType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='StockType'";
                    result = relationRepository.DeleteHql(sql);
                    result = stockTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='StockType'";
                    result = relationRepository.DeleteHql(sql);
                    result = stockTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveStockType entity)
        {
            entity.Id = stockTypeRepository.NewSequence("SYSTEM_MENU");
            return stockTypeRepository.Save(entity);
        }

        public bool Update(ArchiveStockType entity)
        {
            return stockTypeRepository.Update(entity);
        }

        public IList<ArchiveStockType> LoadAll()
        {
            return stockTypeRepository.LoadAll();
        }
        public IList<ArchiveStockType> LoadAll(string order, string where)
        {
            return stockTypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveStockType";
            IList<ArchiveStockType> ls = stockTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = stockTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
