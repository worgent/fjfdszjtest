using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supervision.SendManager.Models
{
    /// <summary>
    /// 派遣工单
    /// </summary>
    [Serializable]
    public sealed class SupervisionSend
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        /// <summary>
        /// id
        /// </summary>
        private string _id;
        /// <summary>
        /// 派遣时间
        /// </summary>
        private string _dispatchtime;
        /// <summary>
        /// 接单时限
        /// </summary>
        private DateTime? _limitorders;
        /// <summary>
        /// 回单时限
        /// </summary>
        private DateTime? _limitback;
        /// <summary>
        /// 派遣单主题
        /// </summary>
        private string _sendtitle;
        /// <summary>
        /// 派遣单内容
        /// </summary>
        private string _sendcontent;
        /// <summary>
        /// 附件名称，上传时的名称
        /// </summary>
        private string _attachmentname;
        /// <summary>
        /// 附件名称，上传后保存在服务器的名称
        /// </summary>
        private string _newattachmentname;
        /// <summary>
        /// 派单人员
        /// </summary>
        private string _sendperson;
        /// <summary>
        /// 用户联系电话
        /// </summary>
        private string _phone;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SupervisionSend()
        {
            _id = String.Empty;
            _dispatchtime = String.Empty;
            _limitorders = DateTime.Now;
            _limitback = DateTime.Now;
            _sendtitle = String.Empty;
            _sendcontent = String.Empty;
            _attachmentname = String.Empty;
            _newattachmentname = String.Empty;
            _sendperson = String.Empty;
            _phone = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SupervisionSend(string id, string dispatchtime, DateTime? limitorders, DateTime? limitback, string sendtitle,
            string sendcontent, string attachmentname, string newattachmentname, string sendperson, string phone)
        {
            _id = id;
            _dispatchtime = dispatchtime;
            _limitorders = limitorders;
            _limitback = limitback;
            _sendtitle = sendtitle;
            _sendcontent = sendcontent;
            _attachmentname = attachmentname;
            _newattachmentname = newattachmentname;
            _sendperson = sendperson;
            _phone = phone;
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
        /// 派遣工单主题
        /// </summary>		
        public string SendTitle
        {
            get { return _sendtitle; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SendTitle", value, value.ToString());

                _isChanged |= (_sendtitle != value); _sendtitle = value;
            }
        }

        /// <summary>
        /// 派遣工单内容
        /// </summary>		
        public string SendContent
        {
            get { return _sendcontent; }
            set
            {
                if (value != null)
                    if (value.Length > 500)
                        throw new ArgumentOutOfRangeException("Invalid value for SendContent", value, value.ToString());

                _isChanged |= (_sendcontent != value); _sendcontent = value;
            }
        }
        /// <summary>
        /// 派单时间
        /// </summary>		
        public string DispatchTime
        {
            get { return _dispatchtime; }
            set
            {
                if (value != null)
                    if (value.Length > 500)
                        throw new ArgumentOutOfRangeException("Invalid value for DispatchTime", value, value.ToString());

                _isChanged |= (_dispatchtime != value); _dispatchtime = value;
            }
        }

        /// <summary>
        /// 接单时限
        /// </summary>		
        public DateTime? LimitOrders
        {
            get { return _limitorders; }
            set
            {
                _isChanged |= (_limitorders != value); _limitorders = value;
            }
        }

        /// <summary>
        /// 回单时限
        /// </summary>		
        public DateTime? LimitBack
        {
            get { return _limitback; }
            set
            {
                _isChanged |= (_limitback != value); _limitback = value;
            }
        }

        /// <summary>
        /// 附件名称，上传时的名称
        /// </summary>		
        public string AttachmentName
        {
            get { return _attachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AttachmentName", value, value.ToString());

                _isChanged |= (_attachmentname != value); _attachmentname = value;
            }
        }

        /// <summary>
        /// 附件名称，上传后保存在服务器的名称
        /// </summary>		
        public string NewAttachmentName
        {
            get { return _newattachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NewAttachmentName", value, value.ToString());

                _isChanged |= (_newattachmentname != value); _newattachmentname = value;
            }
        }

        /// <summary>
        /// 用户名(派单人员)
        /// </summary>		
        public string SendPerson
        {
            get { return _sendperson; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SendPerson", value, value.ToString());

                _isChanged |= (_sendperson != value); _sendperson = value;
            }
        }

        /// <summary>
        /// 用户联系电话
        /// </summary>		
        public string Phone
        {
            get { return _phone; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Phone", value, value.ToString());

                _isChanged |= (_phone != value); _phone = value;
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
