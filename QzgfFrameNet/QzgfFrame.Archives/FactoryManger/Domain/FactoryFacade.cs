using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.FactoryManger.Models;

namespace QzgfFrame.Archives.FactoryManger.Domain
{
    public interface FactoryFacade
    {
        ArchiveFactory Get(object id);
        ArchiveFactory GetHql(string abbrevia);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveFactory entity);

        bool Update(ArchiveFactory entity);
        IList<ArchiveFactory> OSZLoadAll();
        IList<ArchiveFactory> LoadAll();

        IList<ArchiveFactory> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
