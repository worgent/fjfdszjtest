using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.IOClassManger.Models;

namespace QzgfFrame.Archives.IOClassManger.Domain
{
    public interface IOClassFacade
    {
        ArchiveIOClass Get(object id);
        ArchiveIOClass Get(string order, string where);
        ArchiveIOClass GetHql(string ioClassName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveIOClass entity);

        bool Update(ArchiveIOClass entity);

        IList<ArchiveIOClass> LoadAll();

        IList<ArchiveIOClass> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
