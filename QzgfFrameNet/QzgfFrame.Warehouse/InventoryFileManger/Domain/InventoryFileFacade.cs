using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.InventoryFileManger.Models;

namespace QzgfFrame.Warehouse.InventoryFileManger.Domain
{
    public interface InventoryFileFacade
    {
        WarehouseInventoryFile Get(object id);
        WarehouseInventoryFile GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseInventoryFile entity, string no);

        bool Update(WarehouseInventoryFile entity);

        IList<WarehouseInventoryFile> LoadAll();

        IList<WarehouseInventoryFile> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
