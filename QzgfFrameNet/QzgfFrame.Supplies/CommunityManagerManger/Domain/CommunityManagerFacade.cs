using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CommunityManagerManger.Models;

namespace QzgfFrame.Supplies.CommunityManagerManger.Domain
{
    public interface CommunityManagerFacade
    {
        SuppliesCommunityManager Get(object id);
        SuppliesCommunityManager GetHql(string IDCardNo);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesCommunityManager entity, string no);

        bool Update(SuppliesCommunityManager entity);

        IList<SuppliesCommunityManager> LoadAll();

        IList<SuppliesCommunityManager> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string GetCombobox(string hql);
    }
}
