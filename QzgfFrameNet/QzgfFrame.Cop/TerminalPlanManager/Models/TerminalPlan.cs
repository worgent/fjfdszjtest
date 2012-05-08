using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;

namespace QzgfFrame.Cop.TerminalPlanManager.Models
{
    /// <summary>
    /// 自助终端巡检计划 持久类，用于保存临时数据
    /// </summary>
    public class TerminalPlan
    {
        /// <summary>
        /// 操作
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// Id
        /// </summary>
        public virtual string Id { get; set; }
        /// <summary>
        /// 自助终端
        /// </summary>
        public virtual string SelfHelpEquip { get; set; }
        /// <summary>
        /// 终端ID(自助终端唯一标示)
        /// </summary>
        public virtual string TermiIdSelfHelpEquip { get; set; }
        /// <summary>
        /// 所属区县
        /// </summary>
        public virtual string District { get; set; }
        /// <summary>
        /// 自助终端巡检起始时间
        /// </summary>
        public virtual string StartTerminalTime { get; set; }
        /// <summary>
        /// 自助终端下次巡检时间
        /// </summary>
        public virtual string NextTerminalTime { get; set; }
        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        public virtual string TerminalTime { get; set; }
        /// <summary>
        /// 网点类型
        /// </summary>
        public virtual string OutletsType { get; set; }
        /// <summary>
        /// 巡检延迟天数
        /// </summary>
        public virtual string DelayTime { get; set; }

        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        public virtual CopTerminalPlan CopTerminalPlan { get; set; }
        /// <summary>
        /// 网点类型
        /// </summary>
        public virtual ArchiveOutletsType ArchiveOutletsType { get; set; }
        /// <summary>
        /// 自助终端
        /// </summary>
        public virtual ResourceSelfHelpEquip ResourceSelfHelpEquip { get; set; }
    }
}
