using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.RepairManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseRepair
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _equipname;
        private short _issendorigi;
        private string _repairfactoryid;
        private string _seqno;
        private string _equipmodelid;
        private string _factoryid;
        private string _repertotyid;
        private string _reason;
        private int _num;
        private string _clieid;
        private string _clieno;
        private string _ocompanyid;
        private string _odistrictid;
        private DateTime _repairdate;
        private short _isfix;
        private DateTime _scheduledate;
        private DateTime _factdate;
        private string _handlenote;
        private short _state;
        private string _operator;
        private string _reoperator;
        private short _isstorage;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseRepair()
        {
            _id = String.Empty;
            //_filedata=;
            _equipname = String.Empty;
            _issendorigi=0;
            _repairfactoryid = String.Empty;
            _seqno = String.Empty;
            _equipmodelid = String.Empty;
            _factoryid = String.Empty;
            _repertotyid = String.Empty;
            _reason = String.Empty;
            _num=0;
            _clieid = String.Empty;
            _clieno = String.Empty;
            _ocompanyid = String.Empty;
            _odistrictid = String.Empty;
            _repairdate=DateTime.Now;
            _isfix=0;
            _scheduledate=DateTime.Now;
            _factdate=DateTime.Now;
            _handlenote = String.Empty;
            _state=0;
            _operator = String.Empty;
            _reoperator = String.Empty;
            _isstorage=0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseRepair(string id, string equipname, short issendorigi, string repairfactoryid, string seqno,
            string equipmodelid, string factoryid, string repertotyid, string reason, int num, string clieid
            , string clieno, string ocompanyid, string odistrictid, DateTime repairdate, short isfix, DateTime scheduledate
            , DateTime factdate, string handlenote, short state, string stroperator,string reoperator, short isstorage)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _equipname = equipname;
            _issendorigi = issendorigi;
            _repairfactoryid = repairfactoryid;
            _seqno = seqno;
            _equipmodelid = equipmodelid;
            _factoryid = factoryid;
            _repertotyid = repertotyid;
            _reason = reason;
            _num = num;
            _clieid = clieid;
            _clieno=clieno;
            _ocompanyid = ocompanyid;
            _odistrictid = odistrictid;
            _repairdate = repairdate;
            _isfix = isfix;
            _scheduledate=scheduledate;
            _factdate=factdate;
            _handlenote = handlenote;
            _state = state;
            _operator=stroperator;
            _reoperator = reoperator;
            _isstorage = isstorage;
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
        /// 设备名称
        /// </summary>		
        public string EquipName
        {
            get { return _equipname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipName", value, value.ToString());

                _isChanged |= (_equipname != value); _equipname = value;
            }
        }
        /// <summary>
        /// 是否送修原厂家
        /// </summary>		
        public short IsSendOrigi
        {
            get { return _issendorigi; }
            set
            {
                _isChanged |= (_issendorigi != value); _issendorigi = value;
            }
        }
        /// <summary>
        /// 送修厂家
        /// </summary>		
        public string RepairFactoryId
        {
            get { return _repairfactoryid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RepairFactoryId", value, value.ToString());

                _isChanged |= (_repairfactoryid != value); _repairfactoryid = value;
            }
        }
        /// <summary>
        /// 序列号
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
        /// 设备型号
        /// </summary>		
        public string EquipModelId
        {
            get { return _equipmodelid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipModelId", value, value.ToString());

                _isChanged |= (_equipmodelid != value); _equipmodelid = value;
            }
        }
        /// <summary>
        /// 设备厂家
        /// </summary>		
        public string FactoryId
        {
            get { return _factoryid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FactoryId", value, value.ToString());

                _isChanged |= (_factoryid != value); _factoryid = value;
            }
        }
        /// <summary>
        /// 送修原因
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
        /// 仓库Id
        /// </summary>		
        public string RepertotyId
        {
            get { return _repertotyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RepertotyId", value, value.ToString());

                _isChanged |= (_repertotyid != value); _repertotyid = value;
            }
        }
        /// <summary>
        /// 送修数量
        /// </summary>		
        public int Num
        {
            get { return _num; }
            set
            {
                _isChanged |= (_num != value); _num = value;
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
        /// 集团客户编号
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
        /// 送修时间
        /// </summary>		
        public DateTime RepairDate
        {
            get { return _repairdate; }
            set
            {
                _isChanged |= (_repairdate != value); _repairdate = value;
            }
        }
        /// <summary>
        /// 是否修好
        /// </summary>		
        public short IsFix
        {
            get { return _isfix; }
            set
            {
                _isChanged |= (_isfix != value); _isfix = value;
            }
        }
        /// <summary>
        /// 预定时间
        /// </summary>		
        public DateTime ScheduleDate
        {
            get { return _scheduledate; }
            set
            {
                _isChanged |= (_scheduledate != value); _scheduledate = value;
            }
        }
        /// <summary>
        /// 事实时间
        /// </summary>		
        public DateTime FactDate
        {
            get { return _factdate; }
            set
            {
                _isChanged |= (_factdate != value); _factdate = value;
            }
        }
        /// <summary>
        /// 处理说明
        /// </summary>		
        public string HandleNote
        {
            get { return _handlenote; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for HandleNote", value, value.ToString());

                _isChanged |= (_handlenote != value); _handlenote = value;
            }
        }
        /// <summary>
        /// 状态
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
        ///操作员/送修员
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
        ///返修操作员/送修员
        /// </summary>		
        public string ReOperator
        {
            get { return _reoperator; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReOperator", value, value.ToString());

                _isChanged |= (_reoperator != value); _reoperator = value;
            }
        }
        /// <summary>
        /// 返修后是否入库
        /// </summary>		
        public short IsStorage
        {
            get { return _isstorage; }
            set
            {
                _isChanged |= (_isstorage != value); _isstorage = value;
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
