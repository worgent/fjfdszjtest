using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.IODetailManger.Models;

namespace QzgfFrame.Warehouse.IODetailManger.Domain
{
    public interface IODetailFacade
    {
        WarehouseIODetail Get(object id);
        WarehouseIODetail GetHql(string fileName);
        bool Delete(string id);

        bool Save(WarehouseIODetail entity, string no);

        bool Update(WarehouseIODetail entity);

        IList<WarehouseIODetail> LoadAll();
        IList<WarehouseIODetail> LoadAll(string where);
        IList<WarehouseIODetail> LoadAll(string order, string where);
        string LoadAllViewIODetail(string ioListId);
        //分页
        string FindByPage(int pageNo, int pageSize, string gridsearch);
    }
}
