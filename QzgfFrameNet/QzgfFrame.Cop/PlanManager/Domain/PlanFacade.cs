using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.PlanManager.Models;

namespace QzgfFrame.Cop.PlanManager.Domain
{
    /// <summary>
    /// 专线巡检时间
    /// </summary>
    public interface PlanFacade
    {
        CopPlan Get(object id);

        bool Delete(string id);

        bool DeleteFlase(string id,out string msg);

        bool Save(CopPlan entity);

        bool Save(CopPlan entity,string no);

        bool Update(CopPlan entity);

        IList<CopPlan> LoadAll();

        IList<CopPlan> LoadAll(string order, string where);

        /// <summary>
        /// 添加专线巡检计划
        /// </summary>
        /// <param name="BizAssuranLeveId">业务保障等级id</param>
        /// <returns></returns>
        bool AddLinePlan(string BizAssuranLeveId);

        /// <summary>
        /// 根据专线业务保障等级的变化，修改专线巡检计划的巡检周期
        /// </summary>
        /// <param name="DedicateLineId">专线id</param>
        /// <returns></returns>
        bool UpdateLinePlan(string DedicateLineId);

        /// <summary>
        /// 专线退网，删除计划（退网后，不可再重新加入）
        /// </summary>
        /// <param name="DedicateLineId">专线id</param>
        /// <returns></returns>
        bool DeleteLinePlan(string DedicateLineId);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
