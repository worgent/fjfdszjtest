using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.RegisterDetailManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesRegisterDetail
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

         // Properties 
       // private Byte[] _filedata;
        private int _num;
        private string _registerId;
        private string _unitname;
        private string _suppliesTypeId;
        private string _suppliesTypeName;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesRegisterDetail()
        {
            _id = String.Empty;
            //_filedata=;
            _num = 0;
            _registerId = String.Empty;
            _unitname = String.Empty;
            _suppliesTypeId = String.Empty;
            _suppliesTypeName = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesRegisterDetail(string id, int num, string registerId,
            string unitname, string suppliesTypeId, string suppliesTypeName)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _registerId = registerId;
            _unitname = unitname;
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypeName = suppliesTypeName;
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
        /// 耗材登记主表ID
        /// </summary>		
        public string RegisterId
        {
            get { return _registerId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RegisterId", value, value.ToString());

                _isChanged |= (_registerId != value); _registerId = value;
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
        /// 耗材类型ID
        /// </summary>		
        public string SuppliesTypeId
        {
            get { return _suppliesTypeId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeId", value, value.ToString());


                _isChanged |= (_suppliesTypeId != value); _suppliesTypeId = value;
            }
        }

        /// <summary>
        /// 耗材类型名称
        /// </summary>		
        public string SuppliesTypeName
        {
            get { return _suppliesTypeName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeName", value, value.ToString());


                _isChanged |= (_suppliesTypeName != value); _suppliesTypeName = value;
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
