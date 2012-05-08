using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.IODetailManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseIODetail
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
        private int _num;
        private string _equipmodelid;
        private string _equiptypeid;
        private string _factoryid;
        private string _equipstate;
        private string _state;
        private string _remark;
        private string _iolistid;
        private int _renum;
        private string _reiolistid;
        private string _reiodetailid;
        private string _factoryname;
        private string _equipmodelname;
        private string _isArrival;
        private int _arrivalnum;
        private string _unitname;
        private string _stocktypeid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseIODetail()
        {
            _id = String.Empty;
            //_filedata=;
            _equipname = String.Empty;
            _num=0;
            _equipmodelid = String.Empty;
            _equiptypeid = String.Empty;
            _factoryid = String.Empty;
            _equipstate= "0";
            _state="0";
            _remark = String.Empty;
            _iolistid = String.Empty;
            _renum = 0;
            _reiolistid = String.Empty;
            _reiodetailid = String.Empty;
            _factoryname = String.Empty;
            _equipmodelname = String.Empty;
            _isArrival = "0";
            _arrivalnum = 0;//默认到货为0
            _unitname = String.Empty;
            _stocktypeid = String.Empty;

        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseIODetail(string id, int num, string equipstate,string equipmodelid, string factoryid,string equiptypeid,
           string equipmodelname, string factoryname, string equiptypename, string unitname, string stocktypeid, string remark
           )
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _equipmodelid = equipmodelid;
            _equiptypeid = equiptypeid;
            _factoryid = factoryid;
            _equipmodelname = equipmodelname;
            _factoryname = factoryname;
            _equipname = equiptypename;
            _remark = remark;
            _equipstate = equipstate;
            _unitname = unitname;
            _stocktypeid = stocktypeid;
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
        /// 单位
        /// </summary>		
        public string UnitName
        {
            get { return _unitname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UnitName", value, value.ToString());

                _isChanged |= (_unitname != value); _unitname = value;
            }
        }
        /// <summary>
        /// 设备类型名称
        /// </summary>		
        public string EquipTypeName
        {
            get { return _equipname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipTypeName", value, value.ToString());

                _isChanged |= (_equipname != value); _equipname = value;
            }
        }
        
        /// <summary>
        /// 设备类型对应于设备名称
        /// </summary>		
        public string EquipTypeId
        {
            get { return _equiptypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipTypeId", value, value.ToString());

                _isChanged |= (_equiptypeid != value); _equiptypeid = value;
            }
        }
        /// <summary>
        /// 数量
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
        /// 设备厂家名称
        /// </summary>		
        public string FactoryName
        {
            get { return _factoryname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FactoryName", value, value.ToString());

                _isChanged |= (_factoryname != value); _factoryname = value;
            }
        }
        /// <summary>
        /// 设备型号名称
        /// </summary>		
        public string EquipModelName
        {
            get { return _equipmodelname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipModelName", value, value.ToString());

                _isChanged |= (_equipmodelname != value); _equipmodelname = value;
            }
        }
        /// <summary>
        /// 设备状态
        /// </summary>		
        public string EquipState
        {
            get { return _equipstate; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipState", value, value.ToString());

                _isChanged |= (_equipstate != value); _equipstate = value;
            }
        }
        /// <summary>
        /// 状态
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
        /// 库存备件用途Id
        /// </summary>		
        public string StockTypeId
        {
            get { return _stocktypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StockTypeId", value, value.ToString());

                _isChanged |= (_stocktypeid != value); _stocktypeid = value;
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
        /// 退回数量
        /// </summary>		
        public int ReNum
        {
            get { return _renum; }
            set
            {
                _isChanged |= (_renum != value); _renum = value;
            }
        }
        /// <summary>
        /// 出入库主表ID
        /// </summary>		
        public string IOListId
        {
            get { return _iolistid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IOListId", value, value.ToString());

                _isChanged |= (_iolistid != value); _iolistid = value;
            }
        }
        /// <summary>
        /// 退回出入库主表ID
        /// </summary>		
        public string ReIOListId
        {
            get { return _reiolistid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReIOListId", value, value.ToString());

                _isChanged |= (_reiolistid != value); _reiolistid = value;
            }
        }
        /// <summary>
        /// 退回详细表ID
        /// </summary>		
        public string ReIODetailId
        {
            get { return _reiodetailid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReIODetailId", value, value.ToString());

                _isChanged |= (_reiodetailid != value); _reiodetailid = value;
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
