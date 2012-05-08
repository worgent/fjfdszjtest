using System.Collections.Generic;
using QzgfFrame.Supervision.BackManager.Models;

namespace QzgfFrame.Supervision.BackManager.Domain
{
    /// <summary>
    /// 退回工单
    /// </summary>
    public interface BackFacade
    {
        SupervisionBack Get(object id);

        bool Delete(string id);

        bool Save(SupervisionBack entity);

        bool Update(SupervisionBack entity);

        IList<SupervisionBack> LoadAll();

        IList<SupervisionBack> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);


    }
}
