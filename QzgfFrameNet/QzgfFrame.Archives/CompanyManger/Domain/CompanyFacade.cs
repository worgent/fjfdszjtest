using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.CompanyManger.Models;

namespace QzgfFrame.Archives.CompanyManger.Domain
{
    public interface CompanyFacade
    {
        ArchiveCompany Get(object id);
        ArchiveCompany GetHql(string CompanyName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveCompany entity);

        bool Update(ArchiveCompany entity);

        IList<ArchiveCompany> LoadAll();

        IList<ArchiveCompany> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox(string hql);
    }
}
