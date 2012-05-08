using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.NumberGroupManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceNumberGroup
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _number;
        private string _username;
        private string _password;
        private string _clieid;
        private string _orderno;
        private string _lineid;
        private short _delflag;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceNumberGroup()
        {
            _id = String.Empty;
            _orderno = String.Empty;
            _number = String.Empty;
            _username = String.Empty;
            _password = String.Empty;
             _clieid= String.Empty;
             _lineid = String.Empty;
             _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceNumberGroup(string id, string orderno, string number, string username,
            string password, string clieid, string lineid, short delflag)
        {
            _id = id;
            _orderno = orderno;
            _number = number;
            _username = username;
            _password = password;
             _clieid = clieid;
             _lineid = lineid;
             _delflag = delflag;
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
        /// 顺序号
        /// </summary>		
        public string OrderNo
        {
            get { return _orderno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrderNo", value, value.ToString());

                _isChanged |= (_orderno != value); _orderno = value;
            }
        }
        /// <summary>
        /// 电话号码
        /// </summary>		
        public string TelNumber
        {
            get { return _number; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TelNumber", value, value.ToString());

                _isChanged |= (_number != value); _number = value;
            }
        }

        /// <summary>
        /// 用户名
        /// </summary>		
        public string UserName
        {
            get { return _username; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserName", value, value.ToString());

                _isChanged |= (_username != value); _username = value;
            }
        }
        /// <summary>
        /// 密码
        /// </summary>		
        public string PassWord
        {
            get { return _password; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PassWord", value, value.ToString());

                _isChanged |= (_password != value); _password = value;
            }
        } 
        /// <summary>
        /// 集团编号
        /// </summary>		
        public string ClieId
        {
            get { return _clieid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieId", value, value.ToString());

                _isChanged |= (_clieid != value); _clieid = value;
            }
        }
        /// <summary>
        /// 专线ID号
        /// </summary>		
        public string LineId
        {
            get { return _lineid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineId", value, value.ToString());

                _isChanged |= (_lineid != value); _lineid = value;
            }
        }
        /// <summary>
        /// 假删除标示
        /// </summary>		
        public short DelFlag
        {
            get { return _delflag; }
            set
            {
                _isChanged |= (_delflag != value); _delflag = value;
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
