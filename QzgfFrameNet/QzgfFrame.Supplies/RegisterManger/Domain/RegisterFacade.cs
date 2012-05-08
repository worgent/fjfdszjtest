using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.RegisterManger.Models;

namespace QzgfFrame.Supplies.RegisterManger.Domain
{
    public interface RegisterFacade
    {
        SuppliesRegister Get(object id);
        SuppliesRegister GetHql(string PBOSSJobNo);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesRegister entity, string no);

        bool Update(SuppliesRegister entity);

        IList<SuppliesRegister> LoadAll();

        IList<SuppliesRegister> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        IList<object[]> LoadAll(string registerId, string strYear, string strMonth); 
        string FindJLByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindTZByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        string FindPJByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
