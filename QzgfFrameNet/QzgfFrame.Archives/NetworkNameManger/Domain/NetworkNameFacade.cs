using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.NetworkNameManger.Models;

namespace QzgfFrame.Archives.NetworkNameManger.Domain
{
    public interface NetworkNameFacade
    {
        ArchiveNetworkName Get(object id);
        ArchiveNetworkName Get(string order, string where);
        ArchiveNetworkName GetHql(string networkName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveNetworkName entity);

        bool Update(ArchiveNetworkName entity);

        IList<ArchiveNetworkName> LoadAll();

        IList<ArchiveNetworkName> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
