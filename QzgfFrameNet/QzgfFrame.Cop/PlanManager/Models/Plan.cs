using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.DedicateLineManger.Models;

namespace QzgfFrame.Cop.PlanManager.Models
{
    /// <summary>
    /// 专线巡检计划 持久类，用于保存临时数据
    /// </summary>
    public class Plan
    {
        public Plan()
        { 
            
        }

        /// <summary>
        /// 操作
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// id
        /// </summary>
        public virtual string Id { get; set; }
        /// <summary>
        /// 专线唯一编号
        /// </summary>
        public virtual string ProductNoLine { get; set; }
        /// <summary>
        /// 专线类型
        /// </summary>
        public virtual string BizType { get; set; }
        /// <summary>
        /// 所属区县
        /// </summary>
        public virtual string District { get; set; }
        /// <summary>
        /// 所属公司
        /// </summary>
        public virtual string Company { get; set; }
        /// <summary>
        /// 巡检起始时间
        /// </summary>
        public virtual string StartCycTime { get; set; }
        /// <summary>
        /// 下次巡检时间
        /// </summary>
        public virtual string NextCycTime { get; set; }
        /// <summary>
        /// 巡检周期
        /// </summary>
        public virtual string CopCycTime { get; set; }
        /// <summary>
        /// 集团名称
        /// </summary>
        public virtual string GroupName { get; set; }
        /// <summary>
        /// 业务保障等级
        /// </summary>
        public virtual string BizAssuranLeve { get;set;}
        /// <summary>
        /// 巡检延迟时间
        /// </summary>
        public virtual string DelayTime { get; set; }
        /// <summary>
        /// 创建用户
        /// </summary>
        public virtual string CreateUser { get; set; }

        /// <summary>
        /// 巡检计划
        /// </summary>
        public virtual CopPlan CopPlan { get; set; }
        /// <summary>
        /// 专线
        /// </summary>
        public virtual ResourceDedicateLine ResourceDedicateLine { get; set; }
    }
}
