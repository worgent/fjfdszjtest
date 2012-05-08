using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.InventoryDetailManger.Models;
using QzgfFrame.Warehouse.InventoryDetailManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.InventoryDetailManger.Domain
{
    public class InventoryDetailFacadeImpl : InventoryDetailFacade
    {
        private IRepository<WarehouseInventoryDetail> inventoryDetailRepository { set; get; }

        public WarehouseInventoryDetail Get(object id)
        {
            return inventoryDetailRepository.Get(id.ToString());
        }
        public WarehouseInventoryDetail GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseInventoryDetail> InventoryDetails = inventoryDetailRepository.LoadAll("Id", Hql);
            if (InventoryDetails.Count > 0)
                return InventoryDetails[0];
            else return null;
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
                result = inventoryDetailRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseInventoryDetail entity, string no)
        {
            entity.Id = inventoryDetailRepository.NewSequence("SYSTEM_MENU", no);
            return inventoryDetailRepository.Save(entity);
        }

        public bool Update(WarehouseInventoryDetail entity)
        {
            return inventoryDetailRepository.Update(entity);
        }

        public IList<WarehouseInventoryDetail> LoadAll()
        {
            return inventoryDetailRepository.LoadAll();
        }
        public IList<WarehouseInventoryDetail> LoadAll(string order, string where)
        {
            return inventoryDetailRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from WarehouseInventoryDetail";
            IList<WarehouseInventoryDetail> ls = inventoryDetailRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = inventoryDetailRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
