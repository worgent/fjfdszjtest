using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.UseManger.Models;

namespace QzgfFrame.Archives.UseManger.Domain
{
    public interface UseFacade
    {
        ArchiveUse Get(object id);
        ArchiveUse GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveUse entity);

        bool Update(ArchiveUse entity);

        IList<ArchiveUse> LoadAll();

        IList<ArchiveUse> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
