using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SaleDepartmentManger.Models;

namespace QzgfFrame.Archives.SaleDepartmentManger.Domain
{
    public interface SaleDepartmentFacade
    {
        ArchiveSaleDepartment Get(object id);
        ArchiveSaleDepartment GetHql(object SaleDepartmentName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSaleDepartment entity);

        bool Update(ArchiveSaleDepartment entity);

        IList<ArchiveSaleDepartment> LoadAll();

        IList<ArchiveSaleDepartment> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string GetCombobox(string hql);
    }
}
