using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.EnrolManager.Models
{
    /// <summary>
    /// 巡检登记批量添加设备提交后，用于保存页面提交的批量添加设备的数据
    /// </summary>
    public class EquipmentData
    {
        /// <summary>
        /// 专线ID
        /// </summary>
        public virtual string TongTNo { get; set; }
        /// <summary>
        /// 设备名称
        /// </summary>
        public virtual string EquipName { get; set; }
        /// <summary>
        /// 设备型号
        /// </summary>
        public virtual string EquipTypeName { get; set; }
        /// <summary>
        /// 设备所属厂家
        /// </summary>
        public virtual string Abbrevia { get; set; }
        /// <summary>
        /// 客户名称
        /// </summary>
        public virtual string ClieNames { get; set; }
        /// <summary>
        /// 数量
        /// </summary>
        public virtual string Num { get; set; }
        /// <summary>
        /// 现有数量
        /// </summary>
        public virtual string NowNum { get; set; }
        /// <summary>
        /// 是否存在
        /// </summary>
        public virtual string IsPresence { get; set; }
    }
}
