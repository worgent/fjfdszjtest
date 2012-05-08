using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.IOListManger.Models;

namespace QzgfFrame.Warehouse.IOListManger.Domain
{
    public interface IOListFacade
    {
        WarehouseIOList Get(object id);
        WarehouseIOList GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseIOList entity, string no);

        bool Update(WarehouseIOList entity);

        IList<WarehouseIOList> LoadAll();

        IList<WarehouseIOList> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
