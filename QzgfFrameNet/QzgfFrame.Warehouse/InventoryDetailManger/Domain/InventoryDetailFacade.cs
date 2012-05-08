using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.InventoryDetailManger.Models;

namespace QzgfFrame.Warehouse.InventoryDetailManger.Domain
{
    public interface InventoryDetailFacade
    {
        WarehouseInventoryDetail Get(object id);
        WarehouseInventoryDetail GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseInventoryDetail entity, string no);

        bool Update(WarehouseInventoryDetail entity);

        IList<WarehouseInventoryDetail> LoadAll();

        IList<WarehouseInventoryDetail> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
