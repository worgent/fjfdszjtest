using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.NetworkingModeManger.Models;

namespace QzgfFrame.Archives.NetworkingModeManger.Domain
{
    public interface NetworkingModeFacade
    {
        ArchiveNetworkingMode Get(object id);
        ArchiveNetworkingMode GetHql(string modeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveNetworkingMode entity);

        bool Update(ArchiveNetworkingMode entity);

        IList<ArchiveNetworkingMode> LoadAll();

        IList<ArchiveNetworkingMode> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
