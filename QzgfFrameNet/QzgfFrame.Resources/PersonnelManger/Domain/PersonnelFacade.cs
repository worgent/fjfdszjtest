using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.PersonnelManger.Models;

namespace QzgfFrame.Resources.PersonnelManger.Domain
{
    public interface PersonnelFacade
    {
        ResourcePersonnel Get(object id);
        ResourcePersonnel GetHql(string IDCardNo);
        bool Delete(string id);

        bool Save(ResourcePersonnel entity, string no);

        bool Update(ResourcePersonnel entity);

        IList<ResourcePersonnel> LoadAll();

        IList<ResourcePersonnel> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id);

        string SelectByPage(int pageNo, int pageSize);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
