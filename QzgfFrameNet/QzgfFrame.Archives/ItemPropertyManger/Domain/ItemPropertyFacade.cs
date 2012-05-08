using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.ItemPropertyManger.Models;

namespace QzgfFrame.Archives.ItemPropertyManger.Domain
{
    public interface ItemPropertyFacade
    {
        ArchiveItemProperty Get(object id);
        ArchiveItemProperty GetHql(string itemPropertyName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveItemProperty entity);

        bool Update(ArchiveItemProperty entity);

        IList<ArchiveItemProperty> LoadAll();

        IList<ArchiveItemProperty> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
