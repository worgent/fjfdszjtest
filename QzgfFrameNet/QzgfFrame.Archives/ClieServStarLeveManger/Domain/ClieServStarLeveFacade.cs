using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.ClieServStarLeveManger.Models;

namespace QzgfFrame.Archives.ClieServStarLeveManger.Domain
{
    public interface ClieServStarLeveFacade
    {
        ArchiveClieServStarLeve Get(object id);
        ArchiveClieServStarLeve GetHql(object StarLeveName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveClieServStarLeve entity);

        bool Update(ArchiveClieServStarLeve entity);

        IList<ArchiveClieServStarLeve> LoadAll();

        IList<ArchiveClieServStarLeve> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
