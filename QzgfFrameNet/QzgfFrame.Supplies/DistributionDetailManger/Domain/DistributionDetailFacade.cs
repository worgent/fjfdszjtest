using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.DistributionDetailManger.Models;

namespace QzgfFrame.Supplies.DistributionDetailManger.Domain
{
    public interface DistributionDetailFacade
    {
        SuppliesDistributionDetail Get(object id);

        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesDistributionDetail entity, string no);

        bool Update(SuppliesDistributionDetail entity);

        IList<SuppliesDistributionDetail> LoadAll();
        IList<SuppliesDistributionDetail> LoadAll(string where);
        IList<SuppliesDistributionDetail> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
