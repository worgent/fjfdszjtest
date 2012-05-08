using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supervision.SendManager.Models
{
    /// <summary>
    /// 保存页面选择的派遣单位数据
    /// </summary>
    public class UnitData
    {
        public virtual string Id { get; set; }
        /// <summary>
        /// 单位名称
        /// </summary>
        public virtual string UnitName { get; set; }
    }
}
