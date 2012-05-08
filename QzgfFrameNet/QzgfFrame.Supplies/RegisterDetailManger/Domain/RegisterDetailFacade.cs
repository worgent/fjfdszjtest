using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.RegisterDetailManger.Models;

namespace QzgfFrame.Supplies.RegisterDetailManger.Domain
{
    public interface RegisterDetailFacade
    {
        SuppliesRegisterDetail Get(object id);

        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Save(SuppliesRegisterDetail entity, string no);

        bool Update(SuppliesRegisterDetail entity);

        IList<SuppliesRegisterDetail> LoadAll();

        IList<SuppliesRegisterDetail> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
