using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;

namespace QzgfFrame.Archives.MaintainSpecialtyManger.Domain
{
    public interface MaintainSpecialtyFacade
    {
        ArchiveMaintainSpecialty Get(object id);
        ArchiveMaintainSpecialty GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveMaintainSpecialty entity);

        bool Update(ArchiveMaintainSpecialty entity);

        IList<ArchiveMaintainSpecialty> LoadAll();

        IList<ArchiveMaintainSpecialty> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
