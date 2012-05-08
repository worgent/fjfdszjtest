using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supervision.OrdersManager.Models;

namespace QzgfFrame.Supervision.OrdersManager.Domain
{
    /// <summary>
    /// 下单表:下单/接单
    /// </summary>
    public interface OrdersFacade
    {
        SupervisionOrders Get(object id);

        bool Delete(string id);

        bool Save(SupervisionOrders entity);

        bool Update(SupervisionOrders entity);

        IList<SupervisionOrders> LoadAll();

        IList<SupervisionOrders> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        /// <summary>
        /// 带查询条件的分页
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="gridsearch">查询条件</param>
        /// <returns></returns>
        string FindByPage(int pageNo, int pageSize, string[] gridsearch);
    }
}
