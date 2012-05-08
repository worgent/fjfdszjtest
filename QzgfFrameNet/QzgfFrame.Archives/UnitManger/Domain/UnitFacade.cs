using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.UnitManger.Models;

namespace QzgfFrame.Archives.UnitManger.Domain
{
    public interface UnitFacade
    {
        ArchiveUnit Get(object id);
        ArchiveUnit Get(string order, string where);
        ArchiveUnit GetHql(string unitName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveUnit entity);

        bool Update(ArchiveUnit entity);

        IList<ArchiveUnit> LoadAll();

        IList<ArchiveUnit> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
