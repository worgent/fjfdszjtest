using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.SuppliesStockManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesSuppliesStock
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _districtId;
        private string _companyId;
        private string _suppliesTypeId;
        private int _num;
        //状态：正常0、在途1
        private string _state;


        private int _Count;
        private int _ZNum;
        private int _DNum;
        private string _saledepartmentid;
        private string _suppliesTypename;
        private string _unitname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesSuppliesStock()
        {
            _id = String.Empty;
            //_filedata=;
            _districtId = String.Empty;
            _companyId = String.Empty;
            _suppliesTypeId = String.Empty;
            _num = 0;
            _state = "0";
            _ZNum = 0;
            _DNum = 0;
            _Count = 0;
            _saledepartmentid = String.Empty;
            _suppliesTypename = String.Empty;
            _unitname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesSuppliesStock(string id, string companyid, string districtId, string suppliesTypeId,
            string state, int num)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _districtId = districtId;
            _companyId = companyid;
            _suppliesTypeId = suppliesTypeId;
            _state = state;
            _num = num;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesSuppliesStock(string id,  string suppliesTypeId,string unitname,
            string suppliesTypeName, int num)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypename = suppliesTypeName;
            _unitname = unitname;
            _num = num;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesSuppliesStock(string id, string suppliesTypeId, string unitname,
            string suppliesTypeName, int num,string districtId,string saledepartmentid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypename = suppliesTypeName;
            _unitname = unitname;
            _num = num;
            _districtId = districtId;
            _saledepartmentid = saledepartmentid;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesSuppliesStock(string suppliesTypeId, int Count, int ZNum, int DNum, string companyid, string districtId)
        {
            _suppliesTypeId = suppliesTypeId;
            _Count = Count;
            _ZNum=ZNum;
            _DNum = DNum;
            _districtId = districtId;
            _companyId = companyid;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesSuppliesStock(string suppliesTypeId,string suppliesTypename, int Count, int ZNum, int DNum)
        {
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypename = suppliesTypename;
            _Count = Count;
            _ZNum = ZNum;
            _DNum = DNum;
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
        /// 区县ID
        /// </summary>		
        public string DistrictId
        {
            get { return _districtId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictId", value, value.ToString());


                _isChanged |= (_districtId != value); _districtId = value;
            }
        }
        /// <summary>
        /// 所属公司ID
        /// </summary>		
        public string CompanyId
        {
            get { return _companyId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyId", value, value.ToString());


                _isChanged |= (_companyId != value); _companyId = value;
            }
        }

        /// <summary>
        ///数量
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
        ///状态
        /// </summary>		
        public string State
        {
            get { return _state; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for State", value, value.ToString());

                _isChanged |= (_state != value); _state = value;
            }
        }
        /// <summary>
        ///耗材类型
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
        ///耗材类型名称
        /// </summary>		
        public string SuppliesTypeName
        {
            get { return _suppliesTypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeName", value, value.ToString());

                _isChanged |= (_suppliesTypename != value); _suppliesTypename = value;
            }
        }
        /// <summary>
        ///总数量
        /// </summary>		
        public int Count
        {
            get { return _Count; }
            set
            {
                _isChanged |= (_Count != value); _Count = value;
            }
        }

        /// <summary>
        ///正常数量
        /// </summary>		
        public int ZNum
        {
            get { return _ZNum; }
            set
            {
                _isChanged |= (_ZNum != value); _ZNum = value;
            }
        }
        /// <summary>
        ///在途数量 
        /// </summary>		
        public int DNum
        {
            get { return _DNum; }
            set
            {
                _isChanged |= (_DNum != value); _DNum = value;
            }
        }
        /// <summary>
        /// 营业部/片区营销中心
        /// </summary>		
        public string SaleDepartmentId
        {
            get { return _saledepartmentid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SaleDepartmentId", value, value.ToString());

                _isChanged |= (_saledepartmentid != value); _saledepartmentid = value;
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
