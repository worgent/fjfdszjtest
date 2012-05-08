using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;

namespace QzgfFrame.Archives.SelfHelpFactoryManger.Domain
{
    public interface SelfHelpFactoryFacade
    {
        ArchiveSelfHelpFactory Get(object id);
        ArchiveSelfHelpFactory GetHql(string abbrevia);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSelfHelpFactory entity);

        bool Update(ArchiveSelfHelpFactory entity);

        IList<ArchiveSelfHelpFactory> LoadAll();

        IList<ArchiveSelfHelpFactory> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
