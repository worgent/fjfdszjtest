using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.FiberCoreManger.Models
{
    /// <summary>
    ///纤芯表
    /// </summary>
    [Serializable]
    public sealed class ResourceFiberCore
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _seqno;
        private string _basestationname;
        private string _odustation;
        private string _toposition; 
        private string _core;
        private string _lineno;
        private string _clieid;
        private string _lineid;
        private short _delflag;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceFiberCore()
        {
            _id = String.Empty;
            _seqno = String.Empty;
            _basestationname = String.Empty;
            _odustation = String.Empty;
            _toposition = String.Empty; 
            _core = String.Empty;
            _lineno = String.Empty;
            _clieid = String.Empty;
            _lineid = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceFiberCore(string id, string seqno, string basestationname, string odustation,
            string toposition, string core, string lineno, string clieid, string lineid,short delflag)
        {
            _id = id;
            _seqno = seqno;
            _basestationname = basestationname; 
            _odustation = odustation;
            _toposition = toposition; 
            _core = core;
            _lineno = lineno;
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
        /// 排序号
        /// </summary>		
        public string SeqNo
        {
            get { return _seqno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SeqNo", value, value.ToString());

                _isChanged |= (_seqno != value); _seqno = value;
            }
        }
        /// <summary>
        /// 基站/机房名称
        /// </summary>		
        public string BaseStationName
        {
            get { return _basestationname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BaseStationName", value, value.ToString());

                _isChanged |= (_basestationname != value); _basestationname = value;
            }
        }
         /// <summary>
        /// ODU位置
        /// </summary>		
        public string ODUStation
        {
            get { return _odustation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ODUStation", value, value.ToString());

                _isChanged |= (_odustation != value); _odustation = value;
            }
        } 
         /// <summary>
        /// 转至 位置
        /// </summary>		
        public string ToPosition
        {
            get { return _toposition; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ToPosition", value, value.ToString());

                _isChanged |= (_toposition != value); _toposition = value;
            }
        }
         /// <summary>
        /// 纤芯
        /// </summary>		
        public string Core
        {
            get { return _core; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Core", value, value.ToString());

                _isChanged |= (_core != value); _core = value;
            }
        } 
         /// <summary>
        /// 专线编号
        /// </summary>		
        public string LineNo
        {
            get { return _lineno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineNo", value, value.ToString());

                _isChanged |= (_lineno != value); _lineno = value;
            }
        }
        /// <summary>
        /// 专线ID
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
        /// 集团客户ID
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
