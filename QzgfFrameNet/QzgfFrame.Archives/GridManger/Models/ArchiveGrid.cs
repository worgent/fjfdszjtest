using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.DistrictManger.Models; 

namespace QzgfFrame.Archives.GridManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ArchiveGrid
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _gridcode;
        private string _gridname;
        private string _partners;
        private string _nature;
        private string _gridarea;
        private string _districtid;
        private string _companyid;
        private string _officeid;
        private string _cityid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveGrid()
        {
            _id = String.Empty;
            _gridcode = String.Empty;
            _gridname = String.Empty;
            _partners =String.Empty;
            _nature =String.Empty;
            _gridarea =String.Empty;
            _districtid = String.Empty;
            _companyid = String.Empty;
            _officeid = String.Empty;
            _cityid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveGrid(string id, string gridcode, string gridname, string partners, string nature, 
            string gridarea, string districtid,string companyid,string officeid)
        {
            _id = id;
            _gridcode = gridcode;
            _gridname = gridname;
            _partners =partners ;
            _nature =nature ;
            _gridarea =gridarea ;
            _districtid = districtid;
            _companyid = companyid;
            _officeid = officeid;
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
        /// 网格编号
        /// </summary>		
        public string GridCode
        {
            get { return _gridcode.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GridCode", value, value.ToString());

                _isChanged |= (_gridcode != value); _gridcode = value;
            }
        }
         /// <summary>
        /// 网格名称
        /// </summary>		
        public string GridName
        {
            get { return _gridname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GridName", value, value.ToString());

                _isChanged |= (_gridname != value); _gridname = value;
            }
        } 
         /// <summary>
        /// 合作伙伴
        /// </summary>		
        public string Partners
        {
            get {
                if (_partners != null)
                    return _partners.Trim();
                else
                    return null;
            }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Partners", value, value.ToString());

                _isChanged |= (_partners != value); _partners = value;
            }
        }
        /// <summary>
        /// 维护性质
        /// </summary>		
        public string NatureId
        {
            get { return _nature; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NatureId", value, value.ToString());

                _isChanged |= (_nature != value); _nature = value;
            }
        } 
          /// <summary>
        /// 网格地域
        /// </summary>		
        public string GridArea
        {
            get {
                if (_gridarea != null)
                    return _gridarea.Trim();
                else
                    return null; 
            }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GridArea", value, value.ToString());

                _isChanged |= (_gridarea != value); _gridarea = value;
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
        /// 所属城市
        /// </summary>		
        public string CityId
        {
            get { return _cityid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityId", value, value.ToString());

                _isChanged |= (_cityid != value); _cityid = value;
            }
        }
        /// <summary>
        /// 所属维护单位
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
        /// 所属办事驻点
        /// </summary>		
        public string OfficeId
        {
            get { return _officeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OfficeId", value, value.ToString());

                _isChanged |= (_officeid != value); _officeid = value;
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
