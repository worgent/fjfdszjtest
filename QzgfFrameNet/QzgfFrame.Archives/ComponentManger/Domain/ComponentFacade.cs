using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.ComponentManger.Models;

namespace QzgfFrame.Archives.ComponentManger.Domain
{
    public interface ComponentFacade
    {
        ArchiveComponent Get(object id);
        ArchiveComponent Get(string order, string where);
        ArchiveComponent GetHql(string componentName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveComponent entity);

        bool Update(ArchiveComponent entity);

        IList<ArchiveComponent> LoadAll();

        IList<ArchiveComponent> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
