using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;

namespace QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain
{
    public interface SelfHelpEquipTypeFacade
    {
        ArchiveSelfHelpEquipType Get(object id);
        ArchiveSelfHelpEquipType GetHql(string EquipTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSelfHelpEquipType entity);

        bool Update(ArchiveSelfHelpEquipType entity);

        IList<ArchiveSelfHelpEquipType> LoadAll();

        IList<ArchiveSelfHelpEquipType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
