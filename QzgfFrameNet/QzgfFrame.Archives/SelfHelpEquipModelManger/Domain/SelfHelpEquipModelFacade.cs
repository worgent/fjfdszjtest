using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;

namespace QzgfFrame.Archives.SelfHelpEquipModelManger.Domain
{
    public interface SelfHelpEquipModelFacade
    {
        ArchiveSelfHelpEquipModel Get(object id);
        ArchiveSelfHelpEquipModel GetHql(string equipModelName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveSelfHelpEquipModel entity);

        bool Update(ArchiveSelfHelpEquipModel entity);

        IList<ArchiveSelfHelpEquipModel> LoadAll();

        IList<ArchiveSelfHelpEquipModel> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
