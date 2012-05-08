using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.EquipTypeManger.Models;

namespace QzgfFrame.Archives.EquipTypeManger.Domain
{
    public interface EquipTypeFacade
    {
        ArchiveEquipType Get(object id);
        ArchiveEquipType GetHql(string equipTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveEquipType entity);

        bool Update(ArchiveEquipType entity);

        IList<ArchiveEquipType> LoadAll();

        IList<ArchiveEquipType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
