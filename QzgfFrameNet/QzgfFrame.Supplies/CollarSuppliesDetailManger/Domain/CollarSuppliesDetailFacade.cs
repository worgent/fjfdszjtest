using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Models;

namespace QzgfFrame.Supplies.CollarSuppliesDetailManger.Domain
{
    public interface CollarSuppliesDetailFacade
    {
        SuppliesCollarSuppliesDetail Get(object id);

        bool Delete(string id);

        bool Save(SuppliesCollarSuppliesDetail entity, string no);

        bool Update(SuppliesCollarSuppliesDetail entity);

        IList<SuppliesCollarSuppliesDetail> LoadAll();

        IList<SuppliesCollarSuppliesDetail> LoadAll(string where);
        IList<SuppliesCollarSuppliesDetail> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
