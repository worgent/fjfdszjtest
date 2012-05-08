using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.CycTimeManager.Models;

namespace QzgfFrame.Cop.CycTimeManager.Domain
{
    /// <summary>
    /// 巡检周期
    /// </summary>
    public interface CycTimeFacade
    {
        CopCycTime Get(object id);

        bool Delete(string id);

        bool Save(CopCycTime entity);

        bool Update(CopCycTime entity);

        IList<CopCycTime> LoadAll();

        IList<CopCycTime> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
