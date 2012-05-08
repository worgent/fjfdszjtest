using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.EquipModelManger.Models;

namespace QzgfFrame.Archives.EquipModelManger.Domain
{
    public interface EquipModelFacade
    {
        ArchiveEquipModel Get(object id);
        ArchiveEquipModel GetHql(string equipModelName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveEquipModel entity);

        bool Update(ArchiveEquipModel entity);
        IList<ArchiveEquipModel> OSZLoadAll();
        IList<ArchiveEquipModel> LoadAll();

        IList<ArchiveEquipModel> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
