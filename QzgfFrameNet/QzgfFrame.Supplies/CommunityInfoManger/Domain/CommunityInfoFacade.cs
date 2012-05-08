using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CommunityInfoManger.Models;

namespace QzgfFrame.Supplies.CommunityInfoManger.Domain
{
    public interface CommunityInfoFacade
    {
        SuppliesCommunityInfo Get(object id);
        SuppliesCommunityInfo GetHql(string CommunityCode);
        SuppliesCommunityInfo GetSql(object CommunityCode);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesCommunityInfo entity, string no);

        bool Update(SuppliesCommunityInfo entity);

        IList<SuppliesCommunityInfo> LoadAll();
        IList<SuppliesCommunityInfo> LoadAll(string where);
        IList<SuppliesCommunityInfo> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
