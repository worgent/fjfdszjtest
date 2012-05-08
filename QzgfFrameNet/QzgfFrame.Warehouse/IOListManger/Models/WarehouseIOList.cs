using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.IOListManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseIOList
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _type;
        private string _ioclassid;
        private int _totalnum;
        private int _badnum;
        private int _scrapnum;
        private string _operator;
        private DateTime _iodate;
        private string _auditor;
        private DateTime _auditdate;
        private short _state;
        private string _supplier;
        private string _icompanyid;
        private string _icompanyname;
        private string _idistrictid;
        private string _idistrictname;
        private string _ocompanyid;
        private string _ocompanyname;
        private string _odistrictid;
        private string _odistrictname;
        private string _clieid;
        private string _clieno;
        private string _remark;
        private string _iodetailid;
        private string _isArrival;
        private int _arrivalnum;
        private string _reason;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseIOList()
        {
            _id = String.Empty;
            //_filedata=;
            _type = String.Empty;
            _ioclassid = String.Empty;
            _totalnum=0;
            _badnum=0;
            _scrapnum=0;
            _operator = String.Empty;
            _iodate=DateTime.Now;
            _auditor = String.Empty;
            _auditdate = DateTime.Now;
            _state = 0;
            _supplier = String.Empty;
            _icompanyid = String.Empty;
            _idistrictid = String.Empty;
            _ocompanyid = String.Empty;
            _odistrictid = String.Empty;
            _ocompanyname = String.Empty;
            _odistrictname = String.Empty;
            _clieid = String.Empty;
            _clieno = String.Empty;
            _remark = String.Empty;
            _iodetailid = String.Empty;
            _isArrival = "0";
            _arrivalnum = 0;//默认到货为0
            _icompanyname = String.Empty;
            _idistrictname = String.Empty;
            _reason = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseIOList(string id, string type, string ioclassid, int totalnum, int badnum, int scrapnum,
            string stroperator, DateTime iodate, string auditor, DateTime auditdate, short state, string supplier,
            string icompanyid, string idistrictid, string ocompanyid, string odistrictid, string clieid
            , string clieno, string remark, string iodetailid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _type=type;
            _ioclassid=ioclassid;
            _totalnum = totalnum;
            _badnum = badnum;
            _scrapnum = scrapnum;
            _operator = stroperator;
            _iodate=iodate;
            _auditor = auditor;
            _auditdate = auditdate;
            _state = state;
            _supplier = supplier;
            _icompanyid=icompanyid;
            _idistrictid = idistrictid;
            _ocompanyid = ocompanyid;
            _odistrictid = odistrictid;
            _clieid = clieid;
            _clieno = clieno;
            _remark = remark;
            _iodetailid = iodetailid;
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
        /// 类型:出库\入库\调拨
        /// </summary>		
        public string Type
        {
            get { return _type; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Type", value, value.ToString());

                _isChanged |= (_type != value); _type = value;
            }
        }
        /// <summary>
        /// 方式
        /// </summary>		
        public string IOClassId
        {
            get { return _ioclassid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IOClassId", value, value.ToString());

                _isChanged |= (_ioclassid != value); _ioclassid = value;
            }
        }
        /// <summary>
        /// 总数量
        /// </summary>		
        public int TotalNum
        {
            get { return _totalnum; }
            set
            {
                _isChanged |= (_totalnum != value); _totalnum = value;
            }
        }
        /// <summary>
        /// 坏件数量
        /// </summary>		
        public int BadNum
        {
            get { return _badnum; }
            set
            {
                _isChanged |= (_badnum != value); _badnum = value;
            }
        }
        /// <summary>
        /// 报废数量
        /// </summary>		
        public int ScrapNum
        {
            get { return _scrapnum; }
            set
            {
                _isChanged |= (_scrapnum != value); _scrapnum = value;
            }
        }
        /// <summary>
        /// 操作员
        /// </summary>		
        public string Operator
        {
            get { return _operator; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Operator", value, value.ToString());

                _isChanged |= (_operator != value); _operator = value;
            }
        }

        /// <summary>
        /// 操作时间
        /// </summary>		
        public DateTime IODate
        {
            get { return _iodate; }
            set
            {
                _isChanged |= (_iodate != value); _iodate = value;
            }
        }

        /// <summary>
        /// 确认人员
        /// </summary>		
        public string Auditor
        {
            get { return _auditor; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Auditor", value, value.ToString());

                _isChanged |= (_auditor != value); _auditor = value;
            }
        }

        /// <summary>
        /// 确定时间
        /// </summary>		
        public DateTime AuditDate
        {
            get { return _auditdate; }
            set
            {
                _isChanged |= (_auditdate != value); _auditdate = value;
            }
        }
        /// <summary>
        /// 状态:
        /// </summary>		
        public short State
        {
            get { return _state; }
            set
            {
                _isChanged |= (_state != value); _state = value;
            }
        }
        /// <summary>
        /// 供应商
        /// </summary>		
        public string Supplier
        {
            get { return _supplier; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Supplier", value, value.ToString());

                _isChanged |= (_supplier != value); _supplier = value;
            }
        }
        /// <summary>
        ///入库单位
        /// </summary>		
        public string ICompanyId
        {
            get { return _icompanyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ICompanyId", value, value.ToString());

                _isChanged |= (_icompanyid != value); _icompanyid = value;
            }
        }
        /// <summary>
        ///入库单位名称
        /// </summary>		
        public string ICompanyName
        {
            get { return _icompanyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ICompanyName", value, value.ToString());

                _isChanged |= (_icompanyname != value); _icompanyname = value;
            }
        }
        /// <summary>
        /// 入库区县名称
        /// </summary>		
        public string IDistrictName
        {
            get { return _idistrictname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IDistrictName", value, value.ToString());

                _isChanged |= (_idistrictname != value); _idistrictname = value;
            }
        }
        /// <summary>
        /// 入库区县
        /// </summary>		
        public string IDistrictId
        {
            get { return _idistrictid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IDistrictId", value, value.ToString());

                _isChanged |= (_idistrictid != value); _idistrictid = value;
            }
        }
        /// <summary>
        /// 出库单位
        /// </summary>		
        public string OCompanyId
        {
            get { return _ocompanyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OCompanyId", value, value.ToString());

                _isChanged |= (_ocompanyid != value); _ocompanyid = value;
            }
        }
        /// <summary>
        /// 出库区县
        /// </summary>		
        public string ODistrictId
        {
            get { return _odistrictid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ODistrictId", value, value.ToString());

                _isChanged |= (_odistrictid != value); _odistrictid = value;
            }
        }
        /// <summary>
        ///出库单位名称
        /// </summary>		
        public string OCompanyName
        {
            get { return _ocompanyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OCompanyName", value, value.ToString());

                _isChanged |= (_ocompanyname != value); _ocompanyname = value;
            }
        }
        /// <summary>
        /// 出库区县名称
        /// </summary>		
        public string ODistrictName
        {
            get { return _odistrictname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ODistrictName", value, value.ToString());

                _isChanged |= (_odistrictname != value); _odistrictname = value;
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
        /// 集团编号
        /// </summary>		
        public string ClieNo
        {
            get { return _clieno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieNo", value, value.ToString());

                _isChanged |= (_clieno != value); _clieno = value;
            }
        }
        /// <summary>
        /// 备注
        /// </summary>		
        public string Remark
        {
            get { return _remark; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());

                _isChanged |= (_remark != value); _remark = value;
            }
        }
        /// <summary>
        /// 调拨原因
        /// </summary>		
        public string Reason
        {
            get { return _reason; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Reason", value, value.ToString());

                _isChanged |= (_reason != value); _reason = value;
            }
        }
        /// <summary>
        ///详细 ID
        /// </summary>		
        public string IODetailId
        {
            get { return _iodetailid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IODetailId", value, value.ToString());

                _isChanged |= (_iodetailid != value); _iodetailid = value;
            }
        }
        /// <summary>
        ///是否全部到货:默认0,否,1为全部到货
        /// </summary>		
        public string IsArrival
        {
            get { return _isArrival; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for IsArrival", value, value.ToString());
                _isChanged |= (_isArrival != value); _isArrival = value;
            }
        }

        /// <summary>
        /// 到货数量
        /// </summary>		
        public int ArrivalNum
        {
            get { return _arrivalnum; }
            set
            {
                _isChanged |= (_arrivalnum != value); _arrivalnum = value;
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
