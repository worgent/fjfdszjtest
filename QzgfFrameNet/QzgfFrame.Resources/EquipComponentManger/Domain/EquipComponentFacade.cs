using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipComponentManger.Models;

namespace QzgfFrame.Resources.EquipComponentManger.Domain
{
    public interface EquipComponentFacade
    {
        ResourceEquipComponent Get(object id);

        bool Delete(string id);

        bool Save(ResourceEquipComponent entity, string no);

        bool Update(ResourceEquipComponent entity);

        IList<ResourceEquipComponent> LoadAll();

        IList<ResourceEquipComponent> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string gridsearch);
        bool DeleteFalse(string id);
    }
}
