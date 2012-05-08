using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.RepertotyManger.Models;

namespace QzgfFrame.Warehouse.RepertotyManger.Domain
{
    public interface RepertotyFacade
    {
        WarehouseRepertoty Get(object id);
        WarehouseRepertoty GetHql(string fileName);
        bool Delete(string id);

        bool Save(WarehouseRepertoty entity, string no);

        bool Update(WarehouseRepertoty entity);

        IList<WarehouseRepertoty> LoadAll();

        IList<WarehouseRepertoty> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetUserCombobox();
    }
}
