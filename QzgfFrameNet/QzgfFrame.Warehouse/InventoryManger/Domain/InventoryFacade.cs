using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.InventoryManger.Models;

namespace QzgfFrame.Warehouse.InventoryManger.Domain
{
    public interface InventoryFacade
    {
        WarehouseInventory Get(object id);
        WarehouseInventory GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseInventory entity, string no);

        bool Update(WarehouseInventory entity);

        IList<WarehouseInventory> LoadAll();

        IList<WarehouseInventory> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
