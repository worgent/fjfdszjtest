using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.MeterStateManger.Models;

namespace QzgfFrame.Archives.MeterStateManger.Domain
{
    public interface MeterStateFacade
    {
        ArchiveMeterState Get(object id);
        ArchiveMeterState GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveMeterState entity);

        bool Update(ArchiveMeterState entity);

        IList<ArchiveMeterState> LoadAll();

        IList<ArchiveMeterState> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
