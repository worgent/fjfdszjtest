using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.ApparatusManger.Models;

namespace QzgfFrame.Resources.ApparatusManger.Domain
{
    public interface ApparatusFacade
    {
        ResourceApparatus Get(object id);
        ResourceApparatus GetHql(string SeqNo);
        bool Delete(string id);

        bool Save(ResourceApparatus entity, string no);

        bool Update(ResourceApparatus entity);

        IList<ResourceApparatus> LoadAll();

        IList<ResourceApparatus> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
