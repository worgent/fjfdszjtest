using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalPlanManager.Models;

namespace QzgfFrame.Cop.TerminalPlanManager.Domain
{
    /// <summary>
    /// 自助终端巡检时间
    /// </summary>
    public interface TerminalPlanFacade
    {
        CopTerminalPlan Get(object id);

        bool Delete(string id);

        bool Save(CopTerminalPlan entity);

        bool Save(CopTerminalPlan entity, string no);

        bool Update(CopTerminalPlan entity);

        IList<CopTerminalPlan> LoadAll();

        IList<CopTerminalPlan> LoadAll(string order, string where);

        /// <summary>
        /// 添加自助终端巡检计划
        /// </summary>
        /// <param name="ArchiveOutletsTypeId">网点类型id</param>
        /// <returns></returns>
        bool AddSelfHelpEquip(string ArchiveOutletsTypeId);

        /// <summary>
        /// 根据自助终端网点类型的变化，修改自助终端巡检计划的巡检周期
        /// </summary>
        /// <param name="ResourceSelfHelpEquipId">自助终端id</param>
        /// <returns></returns>
        bool UpdateSelfHelpEquip(string ResourceSelfHelpEquipId);

        /// <summary>
        /// 自助终端退网，删除计划（退网后，不可再重新加入）
        /// </summary>
        /// <param name="ResourceSelfHelpEquipId">自助终端id</param>
        /// <returns></returns>
        bool DeleteSelfHelpEquip(string ResourceSelfHelpEquipId);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
    }
}
