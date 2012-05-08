using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.PayStandardManger.Models;

namespace QzgfFrame.Supplies.PayStandardManger.Domain
{
    public interface PayStandardFacade
    {
        SuppliesPayStandard Get(object id);

        bool Delete(string id);

        bool Save(SuppliesPayStandard entity, string no);

        bool Update(SuppliesPayStandard entity);

        IList<SuppliesPayStandard> LoadAll();

        IList<SuppliesPayStandard> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
