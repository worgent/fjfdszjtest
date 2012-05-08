using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.BreakdownTypeManger.Models;

namespace QzgfFrame.Archives.BreakdownTypeManger.Domain
{
    public interface BreakdownTypeFacade
    {
        ArchiveBreakdownType Get(object id);
        ArchiveBreakdownType Get(string order, string where);
        ArchiveBreakdownType GetHql(string breakdownTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveBreakdownType entity);

        bool Update(ArchiveBreakdownType entity);

        IList<ArchiveBreakdownType> LoadAll();

        IList<ArchiveBreakdownType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
