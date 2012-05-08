using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supervision.OrdersManager.Models
{
    /// <summary>
    /// 下单表:下单/接单
    /// </summary>
    [Serializable]
    public sealed class SupervisionOrders
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 派遣对象:县市单位(如:晋江,石狮)
        /// </summary>
        private string _sendingobject;
        /// <summary>
        /// 接单时间
        /// </summary>
        private DateTime? _orderstime;
        /// <summary>
        /// 回单时间
        /// </summary>
        private DateTime? _backtime;
        /// <summary>
        /// 重新回单时间
        /// </summary>
        private DateTime? _rebacktime;
        /// <summary>
        /// 接单超时次数
        /// </summary>
        private int _ordersovertimenum;
        /// <summary>
        /// 回单超时次数
        /// </summary>
        private int _backovertimenum;
        /// <summary>
        /// 退回次数
        /// </summary>
        private int _returnnum;
        /// <summary>
        /// 审核人员
        /// </summary>
        private string _auditstaff;
        /// <summary>
        /// 审核人联系电话
        /// </summary>
        private string _reviewedphone;
        /// <summary>
        /// 工单状态:0,未接;1,已接;2,已回;3,通过;4,返回
        /// </summary>
        private string _status;
        /// <summary>
        /// 审核状态:0,未审核;1,已审核
        /// </summary>
        private string _approvalstatus;
        /// <summary>
        /// 派遣单ID
        /// </summary>
        private string _sendid;
        /// <summary>
        /// 回单附件名称：上传时的文件名称
        /// </summary>
        private string _backattachmentname;
        /// <summary>
        /// 回单附件新名称：上传到服务器时的文件名称
        /// </summary>
        private string _newbackattachmentname;

        /// <summary>
        /// 派遣对象单位名称:机构（如：代维、移动等）
        /// </summary>
        private string _sendunitname;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SupervisionOrders()
        {
            _id = String.Empty;
            _sendingobject = String.Empty;
            _orderstime = DateTime.Now;
            _backtime = DateTime.Now;
            _rebacktime = DateTime.Now;
            _ordersovertimenum = 0;
            _backovertimenum = 0;
            _returnnum = 0;
            _auditstaff = String.Empty;
            _reviewedphone = String.Empty;
            _status = String.Empty;
            _approvalstatus = String.Empty;
            _sendid = String.Empty;
            _backattachmentname = String.Empty;
            _newbackattachmentname = String.Empty;
            _sendunitname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SupervisionOrders(string id, string sendingobject, DateTime orderstime, DateTime backtime, DateTime rebacktime,
            int ordersovertimenum, int backovertimenum, int returnnum, string auditstaff,
            string reviewedphone, string status, string approvalstatus, string sendid, string backattachmentname, 
            string newbackattachmentname, string sendunitname)
        {
            _id = id;
            _sendingobject = sendingobject;
            _orderstime = orderstime;
            _backtime = backtime;
            _rebacktime = rebacktime;
            _ordersovertimenum = ordersovertimenum;
            _backovertimenum = backovertimenum;
            _returnnum = returnnum;
            _auditstaff = auditstaff;
            _reviewedphone = reviewedphone;
            _status = status;
            _sendid = sendid;
            _backattachmentname = backattachmentname;
            _newbackattachmentname = newbackattachmentname;
            _sendunitname = sendunitname;
        }

        #endregion // End Full Constructor

        #region Public Properties

        /// <summary>
        /// 主键
        /// </summary>		
        public string Id
        {
            get { return _id; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());

                _isChanged |= (_id != value); _id = value;
            }
        }

        /// <summary>
        /// 派遣对象
        /// </summary>		
        public string SendingObject
        {
            get { return _sendingobject; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SendingObject", value, value.ToString());

                _isChanged |= (_sendingobject != value); _sendingobject = value;
            }
        }

        /// <summary>
        /// 接单时间
        /// </summary>		
        public DateTime? OrdersTime
        {
            get { return _orderstime; }
            set
            {
                _isChanged |= (_orderstime != value); _orderstime = value;
            }
        }
        /// <summary>
        /// 回单时间
        /// </summary>		
        public DateTime? BackTime
        {
            get { return _backtime; }
            set
            {
                _isChanged |= (_backtime != value); _backtime = value;
            }
        }
        /// <summary>
        /// 重新回单时间
        /// </summary>
        public DateTime? ReBackTime
        {
            get { return _rebacktime; }
            set
            {
                _isChanged |= (_rebacktime != value); _rebacktime = value;
            }
        }
        /// <summary>
        /// 接单超时次数
        /// </summary>		
        public int OrdersOvertimeNum
        {
            get { return _ordersovertimenum; }
            set
            {
                _isChanged |= (_ordersovertimenum != value); _ordersovertimenum = value;
            }
        }
        /// <summary>
        /// 回单超时次数
        /// </summary>		
        public int BackOvertimeNum
        {
            get { return _backovertimenum; }
            set
            {
                _isChanged |= (_backovertimenum != value); _backovertimenum = value;
            }
        }
        /// <summary>
        /// 退回次数
        /// </summary>		
        public int ReturnNum
        {
            get { return _returnnum; }
            set
            {
                _isChanged |= (_returnnum != value); _returnnum = value;
            }
        }
        /// <summary>
        /// 审核人员
        /// </summary>		
        public string AuditStaff
        {
            get { return _auditstaff; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AuditStaff", value, value.ToString());

                _isChanged |= (_auditstaff != value); _auditstaff = value;
            }
        }
        /// <summary>
        /// 审核人联系电话
        /// </summary>		
        public string ReviewedPhone
        {
            get { return _reviewedphone; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReviewedPhone", value, value.ToString());

                _isChanged |= (_reviewedphone != value); _reviewedphone = value;
            }
        }
        /// <summary>
        /// 工单状态:0,未接;1,已接;2,已回;3,通过;4,退回
        /// </summary>		
        public string Status
        {
            get { return _status; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Status", value, value.ToString());

                _isChanged |= (_status != value); _status = value;
            }
        }
        /// <summary>
        /// 审核状态:0,未审核;1,已审核;(回单后，将状态设置为0)
        /// </summary>		
        public string ApprovalStatus
        {
            get { return _approvalstatus; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ApprovalStatus", value, value.ToString());

                _isChanged |= (_approvalstatus != value); _approvalstatus = value;
            }
        }
        /// <summary>
        /// 派遣单ID
        /// </summary>		
        public string SendId
        {
            get { return _sendid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SendId", value, value.ToString());

                _isChanged |= (_sendid != value); _sendid = value;
            }
        }

        /// <summary>
        /// 回单附件名称：上传时的文件名称
        /// </summary>		
        public string BackAttachmentName
        {
            get { return _backattachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BackAttachmentName", value, value.ToString());

                _isChanged |= (_backattachmentname != value); _backattachmentname = value;
            }
        }

        /// <summary>
        /// 回单附件新名称：上传到服务器时的文件名称
        /// </summary>		
        public string NewBackAttachmentName
        {
            get { return _newbackattachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NewBackAttachmentName", value, value.ToString());

                _isChanged |= (_newbackattachmentname != value); _newbackattachmentname = value;
            }
        }

        /// <summary>
        /// 派遣对象单位名称
        /// </summary>		
        public string SendUnitName
        {
            get { return _sendunitname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SendUnitName", value, value.ToString());

                _isChanged |= (_sendunitname != value); _sendunitname = value;
            }
        }

        /// <summary>
        /// Returns whether or not the object has changed it's values.
        /// </summary>
        public bool IsChanged
        {
            get { return _isChanged; }
        }

        /// <summary>
        /// Returns whether or not the object has changed it's values.
        /// </summary>
        public bool IsDeleted
        {
            get { return _isDeleted; }
        }

        #endregion

        #region Public Functions

        /// <summary>
        /// mark the item as deleted
        /// </summary>
        public void MarkAsDeleted()
        {
            _isDeleted = true;
            _isChanged = true;
        }

        #endregion
    }
}
