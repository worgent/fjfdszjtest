using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.MaintainTypeManger.Models;

namespace QzgfFrame.Archives.MaintainTypeManger.Domain
{
    public interface MaintainTypeFacade
    {
        ArchiveMaintainType Get(object id);
        ArchiveMaintainType GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveMaintainType entity);

        bool Update(ArchiveMaintainType entity);

        IList<ArchiveMaintainType> LoadAll();

        IList<ArchiveMaintainType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
