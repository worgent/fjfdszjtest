using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.WorkloadManger.Models;

namespace QzgfFrame.Supplies.WorkloadManger.Domain
{
    public interface WorkloadFacade
    {
        SuppliesWorkload Get(object id);

        bool Delete(string id);

        bool Save(SuppliesWorkload entity, string no);

        bool Update(SuppliesWorkload entity);

        IList<SuppliesWorkload> LoadAll();

        IList<SuppliesWorkload> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
