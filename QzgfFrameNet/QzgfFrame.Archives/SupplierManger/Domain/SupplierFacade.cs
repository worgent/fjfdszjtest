using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SupplierManger.Models;

namespace QzgfFrame.Archives.SupplierManger.Domain
{
    public interface SupplierFacade
    {
        ArchiveSupplier Get(object id);
        ArchiveSupplier Get(string order, string where);
        ArchiveSupplier GetHql(string supplierName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSupplier entity);

        bool Update(ArchiveSupplier entity);

        IList<ArchiveSupplier> LoadAll();

        IList<ArchiveSupplier> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
