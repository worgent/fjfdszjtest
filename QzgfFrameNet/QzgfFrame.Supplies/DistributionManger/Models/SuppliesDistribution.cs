using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.DistributionManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesDistribution
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private DateTime _createdatetime;
        private string _distributionUser;
        private string _remark;
        private int _num;
        private string _cityid;
        private string _companyid;
        private string _districtid;
        private string _dcompanyid;
        private string _state;
        private string _reason;
        private string _districtname;
        private string _companyname;
        private string _ddistrictname;
        private string _dcompanyname;
        private string _saledepartmentid;
        private string _saledepartmentName;
        private short _delflag;
        private string _createuserid;
        private DateTime _createdate;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesDistribution()
        {
            _id = String.Empty;
            //_filedata=;
            _createdatetime = DateTime.Now;
            _distributionUser = String.Empty;
            _cityid = String.Empty;
            _remark = String.Empty;
            _companyid = String.Empty;
            _num = 0;
            _dcompanyid = String.Empty;
            _districtid = String.Empty;
            _state=String.Empty;
            _reason = String.Empty;
        _districtname=String.Empty;
        _companyname=String.Empty;
        _ddistrictname=String.Empty;
        _dcompanyname = String.Empty;
        _saledepartmentid = String.Empty;
        _saledepartmentName = String.Empty;
        _createuserid = String.Empty;
        _createdate = DateTime.Now;
        _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime,
            string cityid, string distributionUser,  string remark)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _createdatetime = createdatetime;
            _cityid = cityid;
            _distributionUser = distributionUser;
            _remark = remark;
        } 
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime, string distributionUser, int num,
            string cityid, string districtname,string companyid,string companyname)
        {
            _id = id;
            _createdatetime = createdatetime;
            _distributionUser = distributionUser;
            _num = num;
            _cityid = cityid;
            _companyid = companyid;
            _districtname = districtname;
            _companyname = companyname;
        }
        /// <summary>
        /// 包含分配方的所属区县,所属公司名称
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime, string distributionUser, int num,
            string cityid, string districtname, string companyid, string companyname,
            string districtid, string ddistrictname, string dcompanyid, string dcompanyname, string state)
        {
            _id = id;
            _createdatetime = createdatetime;
            _distributionUser = distributionUser;
            _num = num;
            _cityid = cityid;
            _companyid = companyid;
            _districtname = districtname;
            _companyname = companyname;
            _districtid = districtid;
            _dcompanyid = dcompanyid;
            _ddistrictname = ddistrictname;
            _dcompanyname = dcompanyname;
            _state = state;
        }

        /// <summary>
        /// 包含分配方的所属区县,所属公司名称\及营业部名称
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime, string distributionUser, int num,
            string cityid, string districtname, string companyid, string companyname,
            string districtid, string ddistrictname, string dcompanyid, string dcompanyname
            , string saledepartmentid, string saledepartmentName)
        {
            _id = id;
            _createdatetime = createdatetime;
            _distributionUser = distributionUser;
            _num = num;
            _cityid = cityid;
            _companyid = companyid;
            _districtname = districtname;
            _companyname = companyname;
            _districtid = districtid;
            _dcompanyid = dcompanyid;
            _ddistrictname = ddistrictname;
            _dcompanyname = dcompanyname;
        _saledepartmentid = saledepartmentid;
        _saledepartmentName = saledepartmentName;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime, string distributionUser, int num,
            string cityid, string districtname, string companyid, string companyname,
            string districtid, string ddistrictname, string dcompanyid, string dcompanyname,
            string remark,string reason,string state)
        {
            _id = id;
            _createdatetime = createdatetime;
            _distributionUser = distributionUser;
            _num = num;
            _cityid = cityid;
            _companyid = companyid;
            _districtname = districtname;
            _companyname = companyname;
            _districtid = districtid;
            _dcompanyid = dcompanyid;
            _ddistrictname = ddistrictname;
            _dcompanyname = dcompanyname;
            _remark = remark;
            _reason = reason;
            _state = state;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesDistribution(string id, DateTime createdatetime, string distributionUser, int num,
            string cityid, string districtname, string companyid, string companyname,
            string districtid, string ddistrictname, string dcompanyid, string dcompanyname,
            string remark, string reason, string saledepartmentid, string saledepartmentName)
        {
            _id = id;
            _createdatetime = createdatetime;
            _distributionUser = distributionUser;
            _num = num;
            _cityid = cityid;
            _companyid = companyid;
            _districtname = districtname;
            _companyname = companyname;
            _districtid = districtid;
            _dcompanyid = dcompanyid;
            _ddistrictname = ddistrictname;
            _dcompanyname = dcompanyname;
            _remark = remark;
            _reason = reason;
            _saledepartmentid = saledepartmentid;
            _saledepartmentName = saledepartmentName;
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
        /// 日期
        /// </summary>		
        public DateTime CreateDatetime
        {
            get { return _createdatetime; }
            set
            {
                 _isChanged |= (_createdatetime != value); _createdatetime = value;
            }
        }
        /// <summary>
        /// 分配操作员
        /// </summary>		
        public string DistributionUser
        {
            get { return _distributionUser; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistributionUser", value, value.ToString());

                _isChanged |= (_distributionUser != value); _distributionUser = value;
            }
        }
        /// <summary>
        /// 退回原因
        /// </summary>		
        public string Reason
        {
            get { return _reason; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Reason", value, value.ToString());

                _isChanged |= (_reason != value); _reason = value;
            }
        }
        /// <summary>
        /// 状态；0未确定，1已确定，2退回
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
        /// 所属城市名称
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
        /// 分配所属城市
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
        /// 分配所属区县名称
        /// </summary>		
        public string DDistrictName
        {
            get { return _ddistrictname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DDistrictName", value, value.ToString());


                _isChanged |= (_ddistrictname != value); _ddistrictname = value;
            }
        }
        /// <summary>
        /// 分配所属公司ID
        /// </summary>		
        public string DCompanyId
        {
            get { return _dcompanyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DCompanyId", value, value.ToString());


                _isChanged |= (_dcompanyid != value); _dcompanyid = value;
            }
        }
        /// <summary>
        /// 分配所属公司名称
        /// </summary>		
        public string DCompanyName
        {
            get { return _dcompanyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DCompanyName", value, value.ToString());


                _isChanged |= (_dcompanyname != value); _dcompanyname = value;
            }
        }
        /// <summary>
        /// 分配营业部/片区营销中心Id
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
        /// 分配营业部/片区营销中心Name
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
