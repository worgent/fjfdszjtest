using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SuppliesTypeManger.Models;

namespace QzgfFrame.Archives.SuppliesTypeManger.Domain
{
    public interface SuppliesTypeFacade
    {
        ArchiveSuppliesType Get(object id);
        ArchiveSuppliesType Get(string order, string where);
        ArchiveSuppliesType GetHql(string suppliesTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSuppliesType entity);

        bool Update(ArchiveSuppliesType entity);

        IList<ArchiveSuppliesType> LoadAll();

        IList<ArchiveSuppliesType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
