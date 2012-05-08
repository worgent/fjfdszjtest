using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;

namespace QzgfFrame.Supervision.SendManager.Models
{
    public class Send
    {
        /// <summary>
        /// 操作（如：选择派遣单位）
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// 派遣工单id
        /// </summary>
        public virtual string Id { get; set; }
        /// <summary>
        /// 派遣工单主题
        /// </summary>
        public virtual string SendTitle { get; set; }
        /// <summary>
        /// 派遣工单内容
        /// </summary>
        public virtual string SendContent { get; set; }
        /// <summary>
        /// 派单时间
        /// </summary>
        public virtual string DispatchTime { get; set; }
        /// <summary>
        /// 派遣单位
        /// </summary>
        public virtual IList<ArchiveDistrict> archiveDistrict { get; set; }
        /// <summary>
        /// 单位名称（用于保存页面传过来的，所选择的单位字符串数据）
        /// </summary>
        public virtual string unitData { get; set; }
    }
}
