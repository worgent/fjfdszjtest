using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.SaleDepartmentManger.Models
{
    /// <summary>
    /// 业务保障等级
    /// </summary>
    [Serializable]
    public sealed class ArchiveSaleDepartment
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _saledepartmentname;
        private string _companyid;
        private string _districtid;
        private string _companyname;
        private string _districtname;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveSaleDepartment()
        {
            _id = String.Empty;
            _saledepartmentname = String.Empty;
            _companyid = String.Empty;
            _districtid = String.Empty;
            _companyname = String.Empty;
            _districtname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveSaleDepartment(string id, string saledepartmentname, string companyid, string cityid)
        {
            _id = id;
            _saledepartmentname = saledepartmentname;
            _companyid = companyid;
            _districtid = cityid;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveSaleDepartment(string id, string saledepartmentname, string companyid, string districtid
            ,string companyname,string districtname)
        {
            _id = id;
            _saledepartmentname = saledepartmentname;
            _companyid = companyid;
            _districtid = districtid;
            _companyname = companyname;
            _districtname = districtname;
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
        /// 营业部/片区营销中心名称
        /// </summary>		
        public string SaleDepartmentName
        {
            get { return _saledepartmentname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SaleDepartmentName", value, value.ToString());

                _isChanged |= (_saledepartmentname != value); _saledepartmentname = value;
            }
        }
        /// <summary>
        /// 所属公司
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
        /// 所属区县
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
        /// 所属公司名称
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
        /// 所属区县名称
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
