using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.StockTypeManger.Models;

namespace QzgfFrame.Archives.StockTypeManger.Domain
{
    public interface StockTypeFacade
    {
        ArchiveStockType Get(object id);
        ArchiveStockType Get(string order, string where);
        ArchiveStockType GetHql(string StockTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveStockType entity);

        bool Update(ArchiveStockType entity);

        IList<ArchiveStockType> LoadAll();

        IList<ArchiveStockType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
