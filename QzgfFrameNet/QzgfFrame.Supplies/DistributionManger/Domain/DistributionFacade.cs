using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.DistributionManger.Models;

namespace QzgfFrame.Supplies.DistributionManger.Domain
{
    public interface DistributionFacade
    {
        SuppliesDistribution Get(object id);
        SuppliesDistribution GetYw(object id);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesDistribution entity, string no);

        bool Update(SuppliesDistribution entity);
        bool UpdateH(SuppliesDistribution entity);
        IList<SuppliesDistribution> LoadAll();

        IList<SuppliesDistribution> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindRByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindYwByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
