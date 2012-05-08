using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.DedicateLineManger.Models;

namespace QzgfFrame.Resources.DedicateLineManger.Domain
{
    public interface DedicateLineFacade
    {
        ResourceDedicateLine Get(object id);
        DedicateLine GetLine(object id);
        ResourceDedicateLine GetHql(string ProductNo);
        bool Delete(string id);
        bool Quit(string id);

        bool Save(ResourceDedicateLine entity);

        bool Update(ResourceDedicateLine entity);

        IList<ResourceDedicateLine> LoadAll();

        IList<ResourceDedicateLine> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id, out bool DelFlag);
        IList<object[]> FindExcel(string aryField, string gridsearch,int zcount,string biztype);
        IList<object[]> FindWideExcel(string aryField, string gridsearch, int zcount);
    }
}
