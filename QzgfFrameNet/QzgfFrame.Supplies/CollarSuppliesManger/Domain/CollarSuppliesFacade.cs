using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CollarSuppliesManger.Models;

namespace QzgfFrame.Supplies.CollarSuppliesManger.Domain
{
    public interface CollarSuppliesFacade
    {
        SuppliesCollarSupplies Get(object id);

        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesCollarSupplies entity, string no);

        bool Update(SuppliesCollarSupplies entity);
        bool UpdateNum(SuppliesCollarSupplies entity);
        IList<SuppliesCollarSupplies> LoadAll();
        IList<SuppliesCollarSupplies> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        IList<SuppliesCollar> LoadAllCollar(string order, string where);
        bool SaveCollar(SuppliesCollar entity, string no);
    }
}
