using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.CommunityManagerManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesCommunityManager
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _fullname;
        private string _iDCardnumber;
        private string _linktel;
        private decimal _wage;
        private DateTime? _quitdate;
        private DateTime? _entrydate;
        private short _isfulltime;
        private short _isinservice;
        private string _cityId;
        private string _districtid;
        private string _companyid;
        private string _saledepartmentid;
        private string _districtname;
        private string _companyname;
        private string _saledepartmentname;
        private short _delflag;
        private string _createuserid;
        private DateTime _createdate;
        #endregion
        
        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesCommunityManager()
        {
            _id = String.Empty;
            //_filedata=;
            _fullname = String.Empty;
            _iDCardnumber = String.Empty;
            _linktel = String.Empty;
            _wage = 0;
            _quitdate = null;
            _entrydate = DateTime.Now;
            _isfulltime = 0;
            _isinservice = 0;
            _cityId = String.Empty;
            _districtid = String.Empty;
            _companyid = String.Empty;
            _saledepartmentid = String.Empty;
            _districtname = String.Empty;
            _companyname = String.Empty;
            _saledepartmentname = String.Empty;
            _delflag = 0;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCommunityManager(string id, string fullname, string iDCardnumber,int wage,
           string linktel, DateTime quitdate, DateTime entrydate, short isfulltime, short isinservice, 
           string companyid, string saledepartmentid, string cityId, string districtid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _fullname = fullname;
            _iDCardnumber = iDCardnumber;
            _linktel = linktel;
            _wage = wage;
            _quitdate = quitdate;
            _entrydate = entrydate;
            _isfulltime = isfulltime;
            _isinservice = isinservice;
            _cityId = cityId;
            _districtid = districtid;
            _companyid = companyid;
            _saledepartmentid = saledepartmentid;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCommunityManager(string id, string fullname, string iDCardnumber, 
           string linktel, DateTime entrydate, short isfulltime, short isinservice,
           string companyid, string saledepartmentid,  string districtid
            , string companyname, string saledepartmentname, string districtname)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _fullname = fullname;
            _iDCardnumber = iDCardnumber;
            _linktel = linktel;
            _entrydate = entrydate;
            _isfulltime = isfulltime;
            _isinservice = isinservice;
            _districtid = districtid;
            _companyid = companyid;
            _saledepartmentid = saledepartmentid;
            _districtname = districtname;
            _companyname = companyname;
            _saledepartmentname = saledepartmentname;
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
        /// 姓名
        /// </summary>		
        public string FullName
        {
            get { return _fullname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FullName", value, value.ToString());

                _isChanged |= (_fullname != value); _fullname = value;
            }
        }
        /// <summary>
        /// 身份证号
        /// </summary>		
        public string IDCardNumber
        {
            get { return _iDCardnumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IDCardNumber", value, value.ToString());

                _isChanged |= (_iDCardnumber != value); _iDCardnumber = value;
            }
        }
            
        /// <summary>
        /// 基本工资
        /// </summary>		
        public Decimal Wage
        {
            get { return _wage; }
            set
            {
                _isChanged |= (_wage != value); _wage = value;
            }
        } 
        /// <summary>
        /// 联系方式
        /// </summary>		
        public string LinkTel
        {
            get { return _linktel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LinkTel", value, value.ToString());

                _isChanged |= (_linktel != value); _linktel = value;
            }
        }
        /// <summary>
        /// 离职时间
        /// </summary>		
        public DateTime? QuitDate
        {
            get { return _quitdate; }
            set
            {
                _isChanged |= (_quitdate != value); _quitdate = value;
            }
        } 
        /// <summary>
        /// 入职时间
        /// </summary>		
        public DateTime? EntryDate
        {
            get { return _entrydate; }
            set
            {
               _isChanged |= (_entrydate != value); _entrydate = value;
            }
        }
        /// <summary>
        /// 兼职/专职
        /// </summary>		
        public short IsFullTime
        {
            get { return _isfulltime; }
            set
            {
                _isChanged |= (_isfulltime != value); _isfulltime = value;
            }
        }
        /// <summary>
        /// 在职/离职
        /// </summary>		
        public short IsInService
        {
            get { return _isinservice; }
            set
            {
               _isChanged |= (_isinservice != value); _isinservice = value;
            }
        }
        /// <summary>
        /// 所属地市
        /// </summary>		
        public string CityId
        {
            get { return _cityId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityId", value, value.ToString());

                _isChanged |= (_cityId != value); _cityId = value;
            }
        }
        /// <summary>
        ///所属县区 
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
        /// 营业部（片区营销中心）
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
        ///所属县区 Name
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
        /// 所属公司Name
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
        /// 营业部（片区营销中心）Name
        /// </summary>		
        public string SaleDepartmentName
        {
            get { return _saledepartmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SaleDepartmentName", value, value.ToString());

                _isChanged |= (_saledepartmentname != value); _saledepartmentname = value;
            }
        }
        /// <summary>
        ///操作用户
        /// </summary>		
        public string CreateUserId
        {
            get { return _createuserid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CreateUserId", value, value.ToString());

                _isChanged |= (_createuserid != value); _createuserid = value;
            }
        }
        /// <summary>
        ///操作时间
        /// </summary>		
        public DateTime CreateDate
        {
            get { return _createdate; }
            set
            {
                _isChanged |= (_createdate != value); _createdate = value;
            }
        }
        /// <summary>
        /// 假删除标示
        /// </summary>		
        public short DelFlag
        {
            get { return _delflag; }
            set
            {
                _isChanged |= (_delflag != value); _delflag = value;
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
