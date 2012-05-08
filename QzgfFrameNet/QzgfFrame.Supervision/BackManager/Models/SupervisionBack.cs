using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supervision.BackManager.Models
{
    /// <summary>
    /// 退回工单
    /// </summary>
    [Serializable]
    public sealed class SupervisionBack
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 退回原因
        /// </summary>
        private string _returnreason;

        /// <summary>
        /// 退回时间
        /// </summary>
        private DateTime? _returntime;

        /// <summary>
        /// 重新回单时限:被退回的工单,审核人员设置的回单时间
        /// </summary>
        private DateTime? _rebacktimeslimit;

        /// <summary>
        /// 审批人员
        /// </summary>
        private string _approvalstaff;

        /// <summary>
        /// 工单ID
        /// </summary>
        private string _ordersid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SupervisionBack()
        {
            _id = String.Empty;
            _returnreason = String.Empty;
            _returntime = DateTime.Now;
            _rebacktimeslimit = DateTime.Now;
            _approvalstaff = String.Empty;
            _ordersid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SupervisionBack(string id, string returnreason, DateTime returntime, DateTime rebacktimeslimit,
            string approvalstaff, string ordersid)
        {
            _id = id;
            _returnreason = returnreason;
            _returntime = returntime;
            _rebacktimeslimit = rebacktimeslimit;
            _approvalstaff = approvalstaff;
            _ordersid = ordersid;
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
        /// 退回原因
        /// </summary>		
        public string ReturnReason
        {
            get { return _returnreason; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReturnReason", value, value.ToString());

                _isChanged |= (_returnreason != value); _returnreason = value;
            }
        }

        /// <summary>
        /// 退回时间
        /// </summary>		
        public DateTime? ReturnTime
        {
            get { return _returntime; }
            set
            {
                _isChanged |= (_returntime != value); _returntime = value;
            }
        }

        /// <summary>
        /// 重新回单时限
        /// </summary>
        public DateTime? ReBackTimeLimit
        {
            get { return _rebacktimeslimit; }
            set
            {
                _isChanged |= (_rebacktimeslimit != value); _rebacktimeslimit = value;
            }
        }

        /// <summary>
        /// 审批人员
        /// </summary>		
        public string ApprovalStaff
        {
            get { return _approvalstaff; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ApprovalStaff", value, value.ToString());

                _isChanged |= (_approvalstaff != value); _approvalstaff = value;
            }
        }

        /// <summary>
        /// 工单ID
        /// </summary>		
        public string OrdersId
        {
            get { return _ordersid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrdersId", value, value.ToString());

                _isChanged |= (_ordersid != value); _ordersid = value;
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
