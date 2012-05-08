using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SignalModelManger.Models;

namespace QzgfFrame.Archives.SignalModelManger.Domain
{
    public interface SignalModelFacade
    {
        ArchiveSignalModel Get(object id);
        ArchiveSignalModel GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSignalModel entity);

        bool Update(ArchiveSignalModel entity);

        IList<ArchiveSignalModel> LoadAll();

        IList<ArchiveSignalModel> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
