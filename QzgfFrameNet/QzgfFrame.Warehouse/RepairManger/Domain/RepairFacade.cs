using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.RepairManger.Models;

namespace QzgfFrame.Warehouse.RepairManger.Domain
{
    public interface RepairFacade
    {
        WarehouseRepair Get(object id);
        WarehouseRepair GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseRepair entity, string no);

        bool Update(WarehouseRepair entity);

        IList<WarehouseRepair> LoadAll();

        IList<WarehouseRepair> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
