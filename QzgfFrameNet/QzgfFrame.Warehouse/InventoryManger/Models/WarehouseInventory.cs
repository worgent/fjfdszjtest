using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.InventoryManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseInventory
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _operator;
        private DateTime _inventorydate;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseInventory()
        {
            _id = String.Empty;
            //_filedata=;
            _operator = String.Empty;
            _inventorydate =DateTime.Now;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseInventory(string id, string stroperator, DateTime inventorydate)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _operator = stroperator;
            _inventorydate = inventorydate;
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
        /// 盘点人员
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
        /// 盘点时间
        /// </summary>		
        public DateTime InventoryDate
        {
            get { return _inventorydate; }
            set
            {
                _isChanged |= (_inventorydate != value); _inventorydate = value;
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
