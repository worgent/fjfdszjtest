using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.DistrictManger.Models
{
    /// <summary>
    /// 区县类
    /// </summary>
    [Serializable]
    public sealed class ArchiveDistrict
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _districtname;
        private string _parentid;
        private string _seqno;
        private int _hno;
        private string _state;
        private string _parentname;
        private short _type;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveDistrict()
        {
            _id = String.Empty;
            _districtname = String.Empty;
            _hno = 0;
            _parentid = "0";
            _seqno = String.Empty;
            _state = "0";
            _parentname = String.Empty;
            _type = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveDistrict(string id, string districtname, int hno, string parentid, string seqno,
            string state, string parentname, short type)
        {
            _id = id;
            _districtname = districtname;
            _hno = hno;
            _parentid = parentid;
            _seqno = seqno;
            _state = state;
            _parentname = parentname;
            _type = type;
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
        /// 区县名称
        /// </summary>		
        public string DistrictName
        {
            get { return _districtname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictName", value, value.ToString());

                _isChanged |= (_districtname != value); _districtname = value;
            }
        }

        /// <summary>
        /// 上级区县
        /// </summary>		
        public string ParentId
        {
            get { return _parentid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ParentId", value, value.ToString());

                _isChanged |= (_parentid != value); _parentid = value;
            }
        }
        /// <summary>
        /// 上级结点
        /// </summary>		
        public string ParentName
        {
            get { return _parentname; }
            set
            {
                _isChanged |= (_parentname != value); _parentname = value;
            }
        } 
        /// <summary>
        /// 区县的树层次域
        /// </summary>		
        public int HNo
        {
            get { return _hno; }
            set
            {
                _isChanged |= (_hno != value); _hno = value;
            }
        }
        /// <summary>
        /// 类型标识 1:省汇,2:市级,3:区县,4:驻点
        /// </summary>		
        public short TypeFlag
        {
            get { return _type; }
            set
            {
                _isChanged |= (_type != value); _type = value;
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
        /// 0:正常,1:停用,2删除
        /// </summary>		
        public string State
        {
            get { return _state; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for State", value, value.ToString());

                _isChanged |= (_state != value); _state = value;
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
