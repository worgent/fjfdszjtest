using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.DistributionDetailManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesDistributionDetail
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
        private string _distributionId;
        private string _unitname;
        private string _suppliesTypeId;
        private string _suppliesTypeName;
        private string _companyid;
        private string _districtid;
        private string _companyName;
        private string _districtName;
        private string _saledepartmentid;
        private string _saledepartmentName;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesDistributionDetail()
        {
            _id = String.Empty;
            //_filedata=;
            _num = 0;
            _distributionId = String.Empty;
            _unitname = String.Empty;
            _suppliesTypeId = String.Empty;
            _suppliesTypeName = String.Empty;
            _districtid = String.Empty;
            _companyid = String.Empty;
            _districtName = String.Empty;
            _companyName = String.Empty;
            _saledepartmentid = String.Empty;
            _saledepartmentName = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistributionDetail(string id, int num, string distributionId, string suppliesTypeId,
            string unitname, string suppliesTypeName, string districtName, string companyName,
            string saledepartmentid, string saledepartmentName)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _distributionId = distributionId;
            _unitname = unitname;
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypeName = suppliesTypeName;
            _districtName = districtName;
            _companyName = companyName;
            _saledepartmentid = saledepartmentid;
            _saledepartmentName = saledepartmentName;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistributionDetail(string id, int num, string distributionId, string suppliesTypeId,
            string unitname, string suppliesTypeName, string districtName, string companyName)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _distributionId = distributionId;
            _unitname = unitname;
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypeName = suppliesTypeName;
            _districtName = districtName;
            _companyName = companyName;
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
        /// 耗材分配主表ID
        /// </summary>		
        public string DistributionId
        {
            get { return _distributionId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistributionId", value, value.ToString());

                _isChanged |= (_distributionId != value); _distributionId = value;
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
        /// 所属区县ID
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
        /// 所属公司ID
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
        /// 所属区县Name
        /// </summary>		
        public string DistrictName
        {
            get { return _districtName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictName", value, value.ToString());


                _isChanged |= (_districtName != value); _districtName = value;
            }
        }
        /// <summary>
        /// 所属公司Name
        /// </summary>		
        public string CompanyName
        {
            get { return _companyName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyName", value, value.ToString());


                _isChanged |= (_companyName != value); _companyName = value;
            }
        }
        /// <summary>
        /// 营业部/片区营销中心Id
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
        /// 营业部/片区营销中心Name
        /// </summary>		
        public string SaleDepartmentName
        {
            get { return _saledepartmentName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SaleDepartmentName", value, value.ToString());


                _isChanged |= (_saledepartmentName != value); _saledepartmentName = value;
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
