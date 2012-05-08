using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalPlanManager.Models;

namespace QzgfFrame.Cop.TerminalEnrolManager.Models
{
    /// <summary>
    /// 自助终端巡检登记持久类，用于保存临时数据
    /// </summary>
    public class TerminalEnrol
    {
        /// <summary>
        /// 操作
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// id
        /// </summary>
        public virtual string Id { get; set; }
        /// <summary>
        /// 自助终端
        /// </summary>
        public virtual string SelfHelpEquip { get; set; }
        /// <summary>
        /// 网点类型
        /// </summary>
        public virtual string OutletsType { get; set; }
        /// <summary>
        /// 维护人员
        /// </summary>
        public virtual string PersonnelName { get; set; }
        /// <summary>
        /// 自助终端巡检计划id
        /// </summary>
        public virtual string CopTerminalPlanId { get; set; }
        /// <summary>
        /// 自助终端巡检登记时间
        /// </summary>
        public virtual string TerminalEnrolTime { get; set; }
        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        public virtual string TerminalTime { get; set; }

        /// <summary>
        /// 自助终端巡检登记
        /// </summary>
        public virtual CopTerminalEnrol CopTerminalEnrol { get; set; }
        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        public virtual CopTerminalPlan CopTerminalPlan { get; set; }
    }
}
