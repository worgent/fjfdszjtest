using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipFaultManger.Models;

namespace QzgfFrame.Resources.EquipFaultManger.Domain
{
    public interface EquipFaultFacade
    {
        ResourceEquipFault Get(object id);

        bool Delete(string id);
        bool Quit(string id);

        bool Save(ResourceEquipFault entity, string no);

        bool Update(ResourceEquipFault entity);

        IList<ResourceEquipFault> LoadAll();

        IList<ResourceEquipFault> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
