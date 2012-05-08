using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.BuildWayManger.Models;

namespace QzgfFrame.Archives.BuildWayManger.Domain
{
    public interface BuildWayFacade
    {
        ArchiveBuildWay Get(object id);
        ArchiveBuildWay GetHql(string Hql);

        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveBuildWay entity);

        bool Update(ArchiveBuildWay entity);

        IList<ArchiveBuildWay> LoadAll();

        IList<ArchiveBuildWay> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
