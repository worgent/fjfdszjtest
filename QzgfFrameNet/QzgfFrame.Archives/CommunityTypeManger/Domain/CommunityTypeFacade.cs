using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.CommunityTypeManger.Models;

namespace QzgfFrame.Archives.CommunityTypeManger.Domain
{
    public interface CommunityTypeFacade
    {
        ArchiveCommunityType Get(object id);
        ArchiveCommunityType GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveCommunityType entity);

        bool Update(ArchiveCommunityType entity);

        IList<ArchiveCommunityType> LoadAll();

        IList<ArchiveCommunityType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
