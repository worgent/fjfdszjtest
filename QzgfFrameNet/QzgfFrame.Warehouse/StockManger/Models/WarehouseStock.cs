using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.StockManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseStock
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _equipmodelname;
        private int? _num;
        private string _unitname;
        private string _equiptypeid;
        private string _equiptypename;
        private string _factoryid;
        private string _factoryname;
        private string _companyid;
        private string _companyname;
        private string _districtid;
        private string _districtname;
        private string _stocktypeid;
        private string _stocktypename;
        private string _barcode;
        private string _state;
        private string _equipmodelid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseStock()
        {
            _id = String.Empty;
            //_filedata=;
            _equipmodelname = String.Empty;
            _num = 0;
            _unitname = String.Empty;
            _equiptypeid = String.Empty;
            _equiptypename = String.Empty;
            _factoryid = String.Empty;
            _factoryname = String.Empty;
            _companyid = String.Empty;
            _companyname = String.Empty;
            _districtid = String.Empty;
            _districtname = String.Empty;
            _stocktypeid = String.Empty;
            _stocktypename = String.Empty;
            _barcode = String.Empty;
            _state = String.Empty;
            _equipmodelid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseStock(string id, string equipname, int totalnum, string unitid, string equipmodelid, string factoryid,
            string companyid, string districtid,string stocktypeid,string barcode)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _equipmodelname = equipname;
            _num = totalnum;
            _unitname = unitid;
            _equiptypeid = equipmodelid;
            _factoryid = factoryid;
            _companyid = companyid;
            _districtid = districtid;
            _stocktypeid = stocktypeid;
            _barcode = barcode;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseStock(string id, int num, string state, string equipmodelid, string factoryid, string equiptypeid,
           string equipmodelname, string factoryname, string equiptypename, string unitname, string stocktypeid
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
            _equiptypename = equiptypename;
            _state = state;
            _unitname = unitname;
            _stocktypeid = stocktypeid;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseStock(string id, string equipmodelid,string companyid, string districtid,string state, int num)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _equipmodelid = equipmodelid;
            _num = num;
            _companyid = companyid;
            _state = state;
            _districtid = districtid;
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
        /// 条型码
        /// </summary>		
        public string BarCode
        {
            get { return _barcode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BarCode", value, value.ToString());

                _isChanged |= (_barcode != value); _barcode = value;
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
        /// 总量
        /// </summary>		
        public int? Num
        {
            get { return _num; }
            set
            {
                _isChanged |= (_num != value); _num = value;
            }
        }
        /// <summary>
        /// 状态:0正常，1：坏件，2：报废，3：冻结即在途
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
        /// 设备类型ID
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
        /// 设备类型名称
        /// </summary>		
        public string EquipTypeName
        {
            get { return _equiptypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipTypeName", value, value.ToString());

                _isChanged |= (_equiptypename != value); _equiptypename = value;
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
        /// 库存备件用途Name
        /// </summary>		
        public string StockTypeName
        {
            get { return _stocktypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StockTypeName", value, value.ToString());

                _isChanged |= (_stocktypename != value); _stocktypename = value;
            }
        }
        /// <summary>
        /// 维护单位
        /// </summary>		
        public string CompanyId
        {
            get { return _companyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyId", value, value.ToString());

                _isChanged |= (_companyid != value); _companyid = value;
            }
        }

        /// <summary>
        /// 维护单位名称
        /// </summary>		
        public string CompanyName
        {
            get { return _companyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyName", value, value.ToString());

                _isChanged |= (_companyname != value); _companyname = value;
            }
        }
        /// <summary>
        /// 所属区域
        /// </summary>		
        public string DistrictId
        {
            get { return _districtid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictId", value, value.ToString());

                _isChanged |= (_districtid != value); _districtid = value;
            }
        }

        /// <summary>
        /// 所属区域名称
        /// </summary>		
        public string DistrictName
        {
            get { return _districtname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictName", value, value.ToString());

                _isChanged |= (_districtname != value); _districtname = value;
            }
        }
        /// <summary>
        /// 设备型号ID
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
