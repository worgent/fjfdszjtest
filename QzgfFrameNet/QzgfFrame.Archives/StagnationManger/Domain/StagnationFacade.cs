using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.StagnationManger.Models;

namespace QzgfFrame.Archives.StagnationManger.Domain
{
    public interface StagnationFacade
    {
        ArchiveStagnation Get(object id);
        ArchiveStagnation GetHql(string Hql);
        ArchiveStagnation GetHql(string stagnationName, string strhql);

        bool Delete(string id, out bool DelFlag);
        bool Save(ArchiveStagnation entity);

        bool Update(ArchiveStagnation entity);

        IList<ArchiveStagnation> LoadAll();

        IList<ArchiveStagnation> LoadAll(string order, string where);
        IList<ArchiveStagnation> LoadLAll(string hql);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
