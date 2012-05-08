using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.EnrolManager.Models;

namespace QzgfFrame.Cop.EnrolManager.Domain
{
    /// <summary>
    /// 巡检登记
    /// </summary>
    public interface EnrolFacade
    {
        CopEnrol Get(object id);

        bool Delete(string id);

        bool Save(CopEnrol entity);

        bool Update(CopEnrol entity);

        IList<CopEnrol> LoadAll();

        IList<CopEnrol> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);

    }
}
