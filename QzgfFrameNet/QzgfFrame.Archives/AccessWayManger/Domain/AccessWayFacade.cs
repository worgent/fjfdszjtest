using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.AccessWayManger.Models;

namespace QzgfFrame.Archives.AccessWayManger.Domain
{
    public interface AccessWayFacade
    {
        ArchiveAccessWay Get(object id);
        ArchiveAccessWay GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveAccessWay entity);

        bool Update(ArchiveAccessWay entity);

        IList<ArchiveAccessWay> LoadAll();

        IList<ArchiveAccessWay> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
