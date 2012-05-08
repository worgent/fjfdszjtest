using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.SuppliesStockManger.Models;

namespace QzgfFrame.Supplies.SuppliesStockManger.Domain
{
    public interface SuppliesStockFacade
    {
        SuppliesSuppliesStock Get(object id);
        SuppliesSuppliesStock GetHql(string hql);

        bool Delete(string id);

        bool Save(SuppliesSuppliesStock entity, string no);

        bool Update(SuppliesSuppliesStock entity);
        bool UpdateNum(SuppliesSuppliesStock entity);
        IList<SuppliesSuppliesStock> LoadAll();

        IList<SuppliesSuppliesStock> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindQxByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindYwByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
