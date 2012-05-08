using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.StockEquipUseManger.Models;

namespace QzgfFrame.Archives.StockEquipUseManger.Domain
{
    public interface StockEquipUseFacade
    {
        ArchiveStockEquipUse Get(object id);
        ArchiveStockEquipUse Get(string order, string where);
        ArchiveStockEquipUse GetHql(string stockEquipUseName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveStockEquipUse entity);

        bool Update(ArchiveStockEquipUse entity);

        IList<ArchiveStockEquipUse> LoadAll();

        IList<ArchiveStockEquipUse> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
