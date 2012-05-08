using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.ClieEquipManger.Models;

namespace QzgfFrame.Resources.ClieEquipManger.Domain
{
    public interface ClieEquipFacade
    {
        ResourceClieEquip Get(object id);

        bool Delete(string id);

        bool Save(ResourceClieEquip entity, string no);

        bool Update(ResourceClieEquip entity);

        IList<ResourceClieEquip> LoadAll();
        IList<ResourceClieEquip> LoadAll(string hql);
        IList<ResourceClieEquip> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        bool DeleteFalse(string id);
    }
}
