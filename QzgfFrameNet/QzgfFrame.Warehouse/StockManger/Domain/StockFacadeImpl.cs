using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.StockManger.Models;
using QzgfFrame.Warehouse.StockManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.StockManger.Domain
{
    public class StockFacadeImpl : StockFacade
    {
        private IRepository<WarehouseStock> stockRepository { set; get; }

        public WarehouseStock Get(object id)
        {
            return stockRepository.Get(id.ToString());
        }

        public WarehouseStock GetHql(string hql)
        {
            string strhql =
            @"select new WarehouseStock(main.Id,main.EquipModelId,main.CompanyId,main.DistrictId,
            main.State,main.Num) from WarehouseStock main where 1=1 ";
            strhql += hql;
            return stockRepository.GetbyHql(strhql);
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, string savePath)
        {
            string[] idarr = id.Split(',');
            bool result = false;

            foreach (var s in idarr)
            {
                result = stockRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseStock entity, string no)
        {
            entity.Id = stockRepository.NewSequence("SYSTEM_MENU", no);
            return stockRepository.Save(entity);
        }

        public bool Update(WarehouseStock entity)
        {
            return stockRepository.Update(entity);
        }

        public bool UpdateNum(WarehouseStock entity)
        {
            string strhql = "  Num='" + entity.Num + "' where Id='" + entity.Id + "'";
            return this.stockRepository.Update(strhql);
        }
        public IList<WarehouseStock> LoadAll()
        {
            return stockRepository.LoadAll();
        }
        public IList<WarehouseStock> LoadAll(string order, string where)
        {
            return stockRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from WarehouseStock";
            IList<WarehouseStock> ls = stockRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = stockRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        public string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new WarehouseStock(main.Id,main.Num,main.State,main.EquipModelId,model.FactoryId,model.EquipTypeId,
            model.EquipModelName,factory.Abbrevia as FactoryName,etype.EquipTypeName ,main.UnitName,main.StockTypeId)
            from WarehouseStock main,ArchiveEquipModel model,ArchiveFactory factory,ArchiveEquipType etype 
            where main.EquipModelId=model.Id and model.FactoryId=factory.Id  and model.EquipTypeId=etype.Id";
            
            string vSql = hql + gridsearch;
            vSql += " order by main." + sortname + " " + sortorder;
            IList<WarehouseStock> lsSuppliesStock = stockRepository.FindByPage(pageNo, pageSize, vSql);

            int recordCount = stockRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsSuppliesStock) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
