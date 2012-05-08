using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supervision.SendManager.Models;

namespace QzgfFrame.Supervision.SendManager.Domain
{
    /// <summary>
    /// 巡检周期
    /// </summary>
    public interface SendFacade
    {
        SupervisionSend Get(object id);

        bool Delete(string id);

        bool DeleteFalse(string id);

        bool Save(SupervisionSend entity);

        bool Update(SupervisionSend entity);

        IList<SupervisionSend> LoadAll();

        IList<SupervisionSend> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
