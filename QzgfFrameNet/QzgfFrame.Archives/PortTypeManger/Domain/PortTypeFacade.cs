using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.PortTypeManger.Models;

namespace QzgfFrame.Archives.PortTypeManger.Domain
{
    public interface PortTypeFacade
    {
        ArchivePortType Get(object id);
        ArchivePortType GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchivePortType entity);

        bool Update(ArchivePortType entity);

        IList<ArchivePortType> LoadAll();

        IList<ArchivePortType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
