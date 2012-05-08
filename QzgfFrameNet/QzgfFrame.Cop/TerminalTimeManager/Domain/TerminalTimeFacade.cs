using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalTimeManager.Models;

namespace QzgfFrame.Cop.TerminalTimeManager.Domain
{
    /// <summary>
    /// 自助终端巡检周期
    /// </summary>
    public interface TerminalTimeFacade
    {
        CopTerminalTime Get(object id);

        bool Delete(string id);

        bool Save(CopTerminalTime entity);

        bool Update(CopTerminalTime entity);

        IList<CopTerminalTime> LoadAll();

        IList<CopTerminalTime> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
