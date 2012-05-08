using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipmentManger.Models;

namespace QzgfFrame.Resources.EquipmentManger.Domain
{
    public interface EquipmentFacade
    {
        ResourceEquipment Get(object id);
        ResourceEquipment GetHql(string EquipName);
        ResourceEquipment GetHql(string EquipName, string ClieId);
        bool Delete(string id);
        bool Quit(string id);

        bool Save(ResourceEquipment entity, string no);

        bool Update(ResourceEquipment entity);

        IList<ResourceEquipment> LoadAll();

        IList<ResourceEquipment> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch); 
        string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id, out bool DelFlag);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
