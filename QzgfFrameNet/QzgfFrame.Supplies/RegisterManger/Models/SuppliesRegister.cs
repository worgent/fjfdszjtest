using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.RegisterManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesRegister
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _pbossjobno;
        private string _maintainer;
        private string _maintainerid;
        private string _communityname;
        private string _communitycode;
        private string _address;
        private string _username;
        private string _paynumber;
        private DateTime _createdatetime;
        private string _remark;
        private string _companyid;
        private string _cityid;
        private string _districtid;
        private string _communitytypeid;
        private string _maintaintypeid;
        private string _buildwayid;
        private string _accesswayid;
        private string _saledepartmentid;
        private int _num;
        private string _districtname;
        private string _companyname;
        private string _saledepartmentname;
        private string _communitytypename;
        private string _maintaintypename;
        private string _buildwayname;
        private string _accesswayname;
        private DateTime _maintaindatetime;
        private string _handletime;
        private short _delflag;
        private string _createuserid;
        private DateTime _createdate;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesRegister()
        {
            _id = String.Empty;
            //_filedata=;
            _pbossjobno = String.Empty;
            _maintainer = String.Empty;
            _communityname = String.Empty;
            _communitycode = String.Empty;
            _address = String.Empty;
            _username = String.Empty;
            _paynumber = String.Empty;
            _createdatetime = DateTime.Now;
            _remark = String.Empty;
            _companyid = String.Empty;
            _cityid = String.Empty;
            _districtid = String.Empty;
            _communitytypeid = String.Empty;
            _maintaintypeid = String.Empty;
            _buildwayid = String.Empty;
            _accesswayid = String.Empty;
            _saledepartmentid = String.Empty;
            _num = 0;
            _districtname=String.Empty;
            _companyname=String.Empty;
            _saledepartmentname=String.Empty;
            _communitytypename=String.Empty;
            _maintaintypename=String.Empty;
            _buildwayname=String.Empty;
            _accesswayname=String.Empty;
            _maintaindatetime = DateTime.Now;
            _maintainerid = String.Empty;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _handletime = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesRegister(string id, string pbossjobno, string maintainer, string communityname,
            string communitycode, string address, string username, string paynumber, DateTime createdatetime,
           string remark, string companyid, string cityid, string districtid,
            string communitytypeid, string maintaintypeid, string buildwayid, string accesswayid, string saledepartmentid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _pbossjobno = pbossjobno;
            _maintainer = maintainer;
            _communityname = communityname;
            _communitycode = communitycode;
            _address = address;
            _username = username;
            _paynumber = paynumber;
            _createdatetime = createdatetime;
            _remark = remark;
            _companyid = companyid;
            _cityid = cityid;
            _districtid = districtid;
            _communitytypeid = communitytypeid;
            _maintaintypeid = maintaintypeid;
            _buildwayid = buildwayid;
            _accesswayid = accesswayid;
            _saledepartmentid = saledepartmentid;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesRegister(string id,int num, string pbossjobno,string maintainerid, string maintainer, string communityname,
            string communitycode, string address, string username, string paynumber, DateTime createdatetime,
           string remark, string companyid, string districtid, string saledepartmentid,
            string communitytypeid, string maintaintypeid, string buildwayid, string accesswayid,
            string districtname, string companyname, string saledepartmentname, string communitytypename,
            string maintaintypename, string buildwayname, string accesswayname,DateTime maintainerdatetime)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _pbossjobno = pbossjobno;
            _maintainerid = maintainerid;
            _maintainer = maintainer;
            _communityname = communityname;
            _communitycode = communitycode;
            _address = address;
            _username = username;
            _paynumber = paynumber;
            _createdatetime = createdatetime;
            _remark = remark;
            _companyid = companyid;
            _districtid = districtid;
            _communitytypeid = communitytypeid;
            _maintaintypeid = maintaintypeid;
            _buildwayid = buildwayid;
            _accesswayid = accesswayid;
            _saledepartmentid = saledepartmentid;

            _districtname = districtname;
            _companyname = companyname;
            _saledepartmentname = saledepartmentname;
            _communitytypename = communitytypename;
            _maintaintypename = maintaintypename;
            _buildwayname = buildwayname;
            _accesswayname = accesswayname;
            _maintaindatetime = maintainerdatetime;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesRegister(string id, int num, string pbossjobno, string maintainerid, string maintainer, string communityname,
            string communitycode, string address, string username, string paynumber, DateTime createdatetime,
           string remark, string companyid, string districtid, string saledepartmentid,
            string communitytypeid, string maintaintypeid, string buildwayid, string accesswayid,
            string districtname, string companyname, string saledepartmentname, string communitytypename,
            string maintaintypename, string buildwayname, string accesswayname, DateTime maintainerdatetime,string handletime)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _pbossjobno = pbossjobno;
            _maintainerid = maintainerid;
            _maintainer = maintainer;
            _communityname = communityname;
            _communitycode = communitycode;
            _address = address;
            _username = username;
            _paynumber = paynumber;
            _createdatetime = createdatetime;
            _remark = remark;
            _companyid = companyid;
            _districtid = districtid;
            _communitytypeid = communitytypeid;
            _maintaintypeid = maintaintypeid;
            _buildwayid = buildwayid;
            _accesswayid = accesswayid;
            _saledepartmentid = saledepartmentid;

            _districtname = districtname;
            _companyname = companyname;
            _saledepartmentname = saledepartmentname;
            _communitytypename = communitytypename;
            _maintaintypename = maintaintypename;
            _buildwayname = buildwayname;
            _accesswayname = accesswayname;
            _maintaindatetime = maintainerdatetime;
            _handletime = handletime;
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
        /// 故障解决时长(以分钟显示)
        /// </summary>		
        public string HandleTime
        {
            get { return _handletime; }
            set
            {
                if (value != null)
                    if (value.Length > 8)
                        throw new ArgumentOutOfRangeException("Invalid value for PBOSSJobNo", value, value.ToString());
                _isChanged |= (_handletime != value); _handletime = value;
            }
        }
        /// <summary>
        /// PBOSS工单号
        /// </summary>		
        public string PBOSSJobNo
        {
            get { return _pbossjobno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PBOSSJobNo", value, value.ToString());

                _isChanged |= (_pbossjobno != value); _pbossjobno = value;
            }
        }
        /// <summary>
        /// 装维人员
        /// </summary>		
        public string Maintainer
        {
            get { return _maintainer; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Maintainer", value, value.ToString());

                _isChanged |= (_maintainer != value); _maintainer = value;
            }
        }
        /// <summary>
        /// 装维人员Id
        /// </summary>		
        public string MaintainerId
        {
            get { return _maintainerid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainerId", value, value.ToString());

                _isChanged |= (_maintainerid != value); _maintainerid = value;
            }
        }
        /// <summary>
        /// 小区名称
        /// </summary>		
        public string CommunityName
        {
            get { return _communityname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityName", value, value.ToString());

                
                _isChanged |= (_communityname != value); _communityname = value;
            }
        }

        /// <summary>
        /// 小区编码
        /// </summary>		
        public string CommunityCode
        {
            get { return _communitycode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityCode", value, value.ToString());


                _isChanged |= (_communitycode != value); _communitycode = value;
            }
        }
        /// <summary>
        /// 装维地址
        /// </summary>		
        public string Address
        {
            get { return _address; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Address", value, value.ToString());

                _isChanged |= (_address != value); _address = value;
            }
        }
        /// <summary>
        /// 用户姓名
        /// </summary>		
        public string UserName
        {
            get { return _username; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserName", value, value.ToString());

                _isChanged |= (_username != value); _username = value;
            }
        }
        /// <summary>
        /// 付费号码
        /// </summary>		
        public string PayNumber
        {
            get { return _paynumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PayNumber", value, value.ToString());

                _isChanged |= (_paynumber != value); _paynumber = value;
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
        /// 日期
        /// </summary>		
        public DateTime MaintainDatetime
        {
            get { return _maintaindatetime; }
            set
            {
                _isChanged |= (_maintaindatetime != value); _maintaindatetime = value;
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
        /// 所属地市
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
        /// 所属县区
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
        /// 小区类型
        /// </summary>		
        public string CommunityTypeId
        {
            get { return _communitytypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityTypeId", value, value.ToString());

                _isChanged |= (_communitytypeid != value); _communitytypeid = value;
            }
        } 
        /// <summary>
        /// 装维类型
        /// </summary>		
        public string MaintainTypeId
        {
            get { return _maintaintypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainTypeId", value, value.ToString());

                _isChanged |= (_maintaintypeid != value); _maintaintypeid = value;
            }
        }
        /// <summary>
        /// 建设方式
        /// </summary>		
        public string BuildWayId
        {
            get { return _buildwayid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BuildWayId", value, value.ToString());

                _isChanged |= (_buildwayid != value); _buildwayid = value;
            }
        }
        /// <summary>
        /// 接入方式
        /// </summary>		
        public string AccessWayId
        {
            get { return _accesswayid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessWayId", value, value.ToString());

                _isChanged |= (_accesswayid != value); _accesswayid = value;
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
        /// 分配营业部/片区营销中心Name
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
        /// 小区类型Name
        /// </summary>		
        public string CommunityTypeName
        {
            get { return _communitytypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityTypeName", value, value.ToString());


                _isChanged |= (_communitytypename != value); _communitytypename = value;
            }
        }

        /// <summary>
        /// 建设方式Name
        /// </summary>		
        public string BuildWayName
        {
            get { return _buildwayname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BuildWayName", value, value.ToString());


                _isChanged |= (_buildwayname != value); _buildwayname = value;
            }
        }

        /// <summary>
        /// 装维类型Name
        /// </summary>		
        public string MaintainTypeName
        {
            get { return _maintaintypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainTypeName", value, value.ToString());


                _isChanged |= (_maintaintypename != value); _maintaintypename = value;
            }
        }

        /// <summary>
        /// 接入方式Name
        /// </summary>		
        public string AccessWayName
        {
            get { return _accesswayname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessWayName", value, value.ToString());


                _isChanged |= (_accesswayname != value); _accesswayname = value;
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
