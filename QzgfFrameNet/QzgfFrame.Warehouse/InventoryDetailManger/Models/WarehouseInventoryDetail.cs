using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.InventoryDetailManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseInventoryDetail
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
        private string _unitid;
        private string _equipmodelid;
        private string _factoryid;
        private string _use;
        private int _currentnum;
        private int _lastweeknum;
        private int _inum;
        private int _onum;
        private int _repairnum;
        private int _returnnum;
        private int _scrapnum;

        private int _differcurrentnum;
        private int _differlastweeknum;
        private int _differinum;
        private int _differonum;
        private int _differrepairnum;
        private int _differreturnnum;
        private int _differscrapnum;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseInventoryDetail()
        {
            _id = String.Empty;
            //_filedata=;
            _equipname = String.Empty;
            _unitid = String.Empty;
            _equipmodelid = String.Empty;
            _factoryid = String.Empty;
            _use = String.Empty;
            _currentnum = 0;
            _lastweeknum = 0;
            _inum = 0;
            _onum = 0;
            _repairnum = 0;
            _returnnum = 0;
            _scrapnum = 0;

            _differcurrentnum = 0;
            _differlastweeknum = 0;
            _differinum = 0;
            _differonum = 0;
            _differrepairnum = 0;
            _differreturnnum = 0;
            _differscrapnum = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseInventoryDetail(string id, string equipname, string unitid, string equipmodelid, string factoryid, string use,
            int currentnum, int lastweeknum, int inum,int onum, int repairnum, int returnnum,int scrapnum,
            int differcurrentnum, int differlastweeknum, int differinum, int differonum,
            int differrepairnum, int differreturnnum, int differscrapnum)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _equipname = equipname;
            _unitid = unitid;
            _equipmodelid = equipmodelid;
            _factoryid = factoryid;
            _use = use;
            _currentnum = currentnum;
            _lastweeknum = lastweeknum;
            _inum = inum;
            _onum = onum;
            _repairnum = repairnum;
            _returnnum = returnnum;
            _scrapnum = scrapnum;

            _differcurrentnum = differcurrentnum;
            _differlastweeknum = differlastweeknum;
            _differinum = differinum;
            _differonum = differonum;
            _differrepairnum = differrepairnum;
            _differreturnnum = differreturnnum;
            _differscrapnum = differscrapnum;
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
        /// 单位
        /// </summary>		
        public string UnitId
        {
            get { return _unitid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UnitId", value, value.ToString());

                _isChanged |= (_unitid != value); _unitid = value;
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
        /// 用途
        /// </summary>		
        public string Use
        {
            get { return _use; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Use", value, value.ToString());

                _isChanged |= (_use != value); _use = value;
            }
        }
        /// <summary>
        /// 当前库存量
        /// </summary>		
        public int CurrentNum
        {
            get { return _currentnum; }
            set
            {
                _isChanged |= (_currentnum != value); _currentnum = value;
            }
        }
        /// <summary>
        /// 上周库存量
        /// </summary>		
        public int LastWeekNum
        {
            get { return _lastweeknum; }
            set
            {
                _isChanged |= (_lastweeknum != value); _lastweeknum = value;
            }
        }
        /// <summary>
        /// 入库数量
        /// </summary>		
        public int INum
        {
            get { return _inum; }
            set
            {
                _isChanged |= (_inum != value); _inum = value;
            }
        }
        /// <summary>
        /// 出库数量
        /// </summary>		
        public int ONum
        {
            get { return _onum; }
            set
            {
                _isChanged |= (_onum != value); _onum = value;
            }
        }
        /// <summary>
        /// 送修数量
        /// </summary>		
        public int RepairNum
        {
            get { return _repairnum; }
            set
            {
                _isChanged |= (_repairnum != value); _repairnum = value;
            }
        }
        /// <summary>
        /// 返修数量
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
        /// 相差当前库存量
        /// </summary>		
        public int DifferCurrentNum
        {
            get { return _differcurrentnum; }
            set
            {
                _isChanged |= (_differcurrentnum != value); _differcurrentnum = value;
            }
        }
        /// <summary>
        /// 相差上周库存量
        /// </summary>		
        public int DifferLastWeekNum
        {
            get { return _differlastweeknum; }
            set
            {
                _isChanged |= (_differlastweeknum != value); _differlastweeknum = value;
            }
        }
        /// <summary>
        /// 相差入库数量
        /// </summary>		
        public int DifferINum
        {
            get { return _differinum; }
            set
            {
                _isChanged |= (_differinum != value); _differinum = value;
            }
        }
        /// <summary>
        /// 相差出库数量
        /// </summary>		
        public int DifferONum
        {
            get { return _differonum; }
            set
            {
                _isChanged |= (_differonum != value); _differonum = value;
            }
        }
        /// <summary>
        /// 相差送修数量
        /// </summary>		
        public int DifferRepairNum
        {
            get { return _differrepairnum; }
            set
            {
                _isChanged |= (_differrepairnum != value); _differrepairnum = value;
            }
        }
        /// <summary>
        /// 相差返修数量
        /// </summary>		
        public int DifferReturnNum
        {
            get { return _differreturnnum; }
            set
            {
                _isChanged |= (_differreturnnum != value); _differreturnnum = value;
            }
        }
        /// <summary>
        /// 相差报废数量
        /// </summary>		
        public int DifferScrapNum
        {
            get { return _differscrapnum; }
            set
            {
                _isChanged |= (_differscrapnum != value); _differscrapnum = value;
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
