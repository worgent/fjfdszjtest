using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supervision.SendManager.Models;
using QzgfFrame.Supervision.BackManager.Models;

namespace QzgfFrame.Supervision.OrdersManager.Models
{
    /// <summary>
    /// 我的工单
    /// </summary>
    public class Order
    {
        /// <summary>
        /// 工单号
        /// </summary>
        public virtual string Id { get; set; }
        /// <summary>
        /// 工单号(用于传递id)
        /// </summary>
        public virtual string OrderId { get; set; }
        /// <summary>
        /// 操作（如：接单，回单等操作）
        /// </summary>
        public virtual string Operating { get; set; }
        /// <summary>
        /// 操作（如：审核工单等）
        /// </summary>
        public virtual string ApprovalOperating { get; set; }
        /// <summary>
        /// 工单主题
        /// </summary>
        public virtual string SendTitle { get; set; }
        /// <summary>
        /// 工单内容
        /// </summary>
        public virtual string SendContent { get; set; }
        /// <summary>
        /// 派单时间
        /// </summary>
        public virtual string DispatchTime { get; set; }
        /// <summary>
        /// 审核人员
        /// </summary>
        public virtual string AuditStaff { get; set; }
        /// <summary>
        /// 审核人联系电话
        /// </summary>
        public virtual string ReviewedPhone { get; set; }
        /// <summary>
        /// 接单超时次数
        /// </summary>
        public virtual int OrdersOvertimeNum { get; set; }
        /// <summary>
        /// 回单超时次数
        /// </summary>
        public virtual int BackOvertimeNum { get; set; }
        /// <summary>
        /// 退回次数
        /// </summary>
        public virtual int ReturnNum { get; set; }
        /// <summary>
        /// 附件名称(不包含全部的链接地址，只包含附件的名称，)
        /// </summary>
        public virtual string AttachmentName { get; set; }
        /// <summary>
        /// 回单附件
        /// </summary>
        public virtual string BackAttachment { get; set; }
        /// <summary>
        /// 工单状态:0,未接;1,已接;2,已回;3,通过;4,退回
        /// </summary>
        public virtual string Status { get; set; }
        /// <summary>
        /// 审核状态:0,未审核;1,已审核;(回单后，将状态设置为0)
        /// </summary>
        public virtual string ApprovalStatus { get; set; }
        /// <summary>
        /// 派遣对象单位名称
        /// </summary>
        public virtual string SendUnitName { get; set; }
        /// <summary>
        /// 派遣工单
        /// </summary>
        public virtual SupervisionSend SupervisionSend { get; set; }
        /// <summary>
        /// 退回工单
        /// </summary>
        public virtual SupervisionBack SupervisionBack { get; set; }
    }
}
