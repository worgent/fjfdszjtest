using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Cop.PlanManager.Models;

namespace QzgfFrame.Cop.EnrolManager.Models
{
    /// <summary>
    /// 专线巡检登记持久类，用于保存临时数据
    /// </summary>
    public class Enrol
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
        /// 巡检周期
        /// </summary>
        public virtual string CycTime { get; set; }
        /// <summary>
        /// 业务保障等级
        /// </summary>
        public virtual string BizAssuranLeve { get; set; }
        /// <summary>
        /// 集团名称
        /// </summary>
        public virtual string GroupName { get; set; }
        /// <summary>
        /// 所属区县
        /// </summary>
        public virtual string District { get; set; }
        /// <summary>
        /// 所属公司
        /// </summary>
        public virtual string Company { get; set; }
        /// <summary>
        /// 专线类型
        /// </summary>
        public virtual string BizType { get; set; }
        /// <summary>
        /// 附件名称,上传时的名称
        /// </summary>
        public virtual string AttachmentName { get; set; }
        /// <summary>
        /// 附件新名称,上传到服务的名称
        /// </summary>
        public virtual string NewAttachmentName { get; set; }
        /// <summary>
        /// 巡检周期id,保存在隐藏域中
        /// </summary>
        public virtual string HidCycTimeId { get; set; }
        /// <summary>
        /// 维护人员
        /// </summary>
        public virtual string PersonnelName { get; set; }
        /// <summary>
        /// 巡检计划id
        /// </summary>
        public virtual string CopPlanId { get; set; }
        /// <summary>
        /// 巡检登记时间
        /// </summary>
        public virtual string CycEnrolTime { get; set; }

        /// <summary>
        /// 巡检登记
        /// </summary>
        public virtual CopEnrol CopEnrol { get; set; }
        /// <summary>
        /// 专线
        /// </summary>
        public virtual ResourceDedicateLine ResourceDedicateLine { get; set; }
        /// <summary>
        /// 巡检计划
        /// </summary>
        public virtual CopPlan CopPlan { get; set; }
    }
}
