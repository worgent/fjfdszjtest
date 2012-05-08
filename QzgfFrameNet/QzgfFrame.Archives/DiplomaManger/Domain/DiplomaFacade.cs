using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.DiplomaManger.Models;

namespace QzgfFrame.Archives.DiplomaManger.Domain
{
    public interface DiplomaFacade
    {
        ArchiveDiploma Get(object id);
        ArchiveDiploma GetHql(string diplomaName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveDiploma entity);

        bool Update(ArchiveDiploma entity);

        IList<ArchiveDiploma> LoadAll();

        IList<ArchiveDiploma> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
