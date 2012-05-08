using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.LineEquipManger.Models;

namespace QzgfFrame.Resources.LineEquipManger.Domain
{
    public interface LineEquipFacade
    {
        ResourceLineEquip Get(object id);

        bool Delete(string id);

        bool Save(ResourceLineEquip entity, string no);

        bool Update(ResourceLineEquip entity);

        IList<ResourceLineEquip> LoadAll();
        IList<ResourceLineEquip> LoadAll(string where);
        IList<ResourceLineEquip> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string gridsearch);
        bool DeleteFalse(string id);
    }
}
