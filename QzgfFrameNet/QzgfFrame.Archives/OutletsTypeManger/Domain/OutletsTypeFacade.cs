using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.OutletsTypeManger.Models;

namespace QzgfFrame.Archives.OutletsTypeManger.Domain
{
    public interface OutletsTypeFacade
    {
        ArchiveOutletsType Get(object id);
        ArchiveOutletsType GetHql(string outletsTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveOutletsType entity);

        bool Update(ArchiveOutletsType entity);

        IList<ArchiveOutletsType> LoadAll();

        IList<ArchiveOutletsType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
