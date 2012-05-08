using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;

namespace QzgfFrame.Archives.BizAssuranLeveManger.Domain
{
    public interface BizAssuranLeveFacade
    {
        ArchiveBizAssuranLeve Get(object id);
        ArchiveBizAssuranLeve GetHql(object assuranLeveName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveBizAssuranLeve entity);

        bool Update(ArchiveBizAssuranLeve entity);

        IList<ArchiveBizAssuranLeve> LoadAll();

        IList<ArchiveBizAssuranLeve> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetCombobox();
    }
}
