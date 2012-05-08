using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.DutyManger.Models;

namespace QzgfFrame.Archives.DutyManger.Domain
{
    public interface DutyFacade
    {
        ArchiveDuty Get(object id);
        ArchiveDuty GetHql(string dutyName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveDuty entity);

        bool Update(ArchiveDuty entity);

        IList<ArchiveDuty> LoadAll();

        IList<ArchiveDuty> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
