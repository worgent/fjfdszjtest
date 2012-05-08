using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.StockManger.Models;

namespace QzgfFrame.Warehouse.StockManger.Domain
{
    public interface StockFacade
    {
        WarehouseStock Get(object id);
        WarehouseStock GetHql(string fileName);
        bool Delete(string id, string savePath);

        bool Save(WarehouseStock entity, string no);

        bool Update(WarehouseStock entity);
        bool UpdateNum(WarehouseStock entity);
        IList<WarehouseStock> LoadAll();

        IList<WarehouseStock> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
