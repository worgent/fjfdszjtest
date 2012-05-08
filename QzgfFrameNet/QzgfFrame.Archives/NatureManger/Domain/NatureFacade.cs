using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.NatureManger.Models;

namespace QzgfFrame.Archives.NatureManger.Domain
{
    public interface NatureFacade
    {
        ArchiveNature Get(object id);
        ArchiveNature GetHql(string natureName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveNature entity);

        bool Update(ArchiveNature entity);

        IList<ArchiveNature> LoadAll();

        IList<ArchiveNature> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
