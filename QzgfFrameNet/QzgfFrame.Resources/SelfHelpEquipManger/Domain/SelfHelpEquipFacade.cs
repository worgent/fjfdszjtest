using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;

namespace QzgfFrame.Resources.SelfHelpEquipManger.Domain
{
    public interface SelfHelpEquipFacade
    {
        ResourceSelfHelpEquip Get(object id);
        ResourceSelfHelpEquip GetHql(object TermiId);
        ResourceSelfHelpEquip GetSql(object TermiId);
        bool Delete(string id);
        bool Quit(string id);

        bool Save(ResourceSelfHelpEquip entity, string no);

        bool Update(ResourceSelfHelpEquip entity);

        IList<ResourceSelfHelpEquip> LoadAll();
        IList<ResourceSelfHelpEquip> LoadAll(string where);
        IList<ResourceSelfHelpEquip> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id, out bool DelFlag);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
