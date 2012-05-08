using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.GridManger.Models;

namespace QzgfFrame.Archives.GridManger.Domain
{
    public interface  GridFacade
    {
        ArchiveGrid Get(object id);
        ArchiveGrid GetHql(string GridName);
        ArchiveGrid GetHql(string GridName, string strhql);
        string GetOSZHql(string GridName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveGrid entity);

        bool Update(ArchiveGrid entity);

        IList<ArchiveGrid> LoadAll();

        IList<ArchiveGrid> LoadAll(string order, string where);
        IList<ArchiveGrid> LoadLAll(string hql);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
