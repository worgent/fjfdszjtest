using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.GroupClieManger.Models;

namespace QzgfFrame.Resources.GroupClieManger.Domain
{
    public interface GroupClieFacade
    {
        ResourceGroupClie Get(object id);
        ResourceGroupClie GetGC(object id);
        ResourceGroupClie GetHql(string clieNo);
        ResourceGroupClie GetStrHql(string hql);
        bool Delete(string id);

        bool Save(ResourceGroupClie entity, string no);

        bool Update(ResourceGroupClie entity);

        IList<ResourceGroupClie> LoadAll();

        IList<ResourceGroupClie> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string GetCombobox(string hql);
        bool DeleteFalse(string id);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
