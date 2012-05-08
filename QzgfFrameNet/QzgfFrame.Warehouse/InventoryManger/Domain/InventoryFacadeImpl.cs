using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.InventoryManger.Models;
using QzgfFrame.Warehouse.InventoryManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.InventoryManger.Domain
{
    public class InventoryFacadeImpl : InventoryFacade
    {
        private IRepository<WarehouseInventory> inventoryRepository { set; get; }

        public WarehouseInventory Get(object id)
        {
            return inventoryRepository.Get(id.ToString());
        }
        public WarehouseInventory GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseInventory> Inventorys = inventoryRepository.LoadAll("Id", Hql);
            if (Inventorys.Count > 0)
                return Inventorys[0];
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
                result = inventoryRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseInventory entity, string no)
        {
            entity.Id = inventoryRepository.NewSequence("SYSTEM_MENU", no);
            return inventoryRepository.Save(entity);
        }

        public bool Update(WarehouseInventory entity)
        {
            return inventoryRepository.Update(entity);
        }

        public IList<WarehouseInventory> LoadAll()
        {
            return inventoryRepository.LoadAll();
        }
        public IList<WarehouseInventory> LoadAll(string order, string where)
        {
            return inventoryRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from WarehouseInventory";
            IList<WarehouseInventory> ls = inventoryRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = inventoryRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
