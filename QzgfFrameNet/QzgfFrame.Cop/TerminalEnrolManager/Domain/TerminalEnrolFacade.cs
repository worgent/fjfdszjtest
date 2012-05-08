using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalEnrolManager.Models;

namespace QzgfFrame.Cop.TerminalEnrolManager.Domain
{
    /// <summary>
    /// 自助终端巡检登记
    /// </summary>
    public interface TerminalEnrolFacade
    {
        CopTerminalEnrol Get(object id);

        bool Delete(string id);

        bool Save(CopTerminalEnrol entity);

        bool Update(CopTerminalEnrol entity);

        IList<CopTerminalEnrol> LoadAll();

        IList<CopTerminalEnrol> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
