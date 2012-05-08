using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.PersonnelManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourcePersonnel
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _twodimensionalcode;
        private string _fullname;
        private short _sex;
        private DateTime? _birthdate;
        private string _iDCardnumber;
        private string _mobilenumber;
        private string _diploma;
        private string _duty;
        private string _qualificationtype;
        private string _certificationno;
        private string _certificate1;
        private DateTime? _certificateawarddate1;
        private string _certificate2;
        private DateTime? _certificateawarddate2;
        private string _certificate3;
        private DateTime? _certificateawarddate3;
        private string _certificate4;
        private DateTime? _certificateawarddate4;
        private string _certificate5;
        private DateTime? _certificateawarddate5;
        private string _stagnation;
        private DateTime? _workdate;
        private DateTime? _entrydate;
        private short _isbackbone;
        private short _isSelfMaintain;
        private string _itemname;
        private string _itemproperty;
        private string _maintainspecialty;
        private string _remark;
        private string _cityId;
        private string _districtid;
        private string _companyid;
        private string _gridid;
        private string _postsid;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourcePersonnel()
        {
            _id = String.Empty;
            //_filedata=;
            _twodimensionalcode = String.Empty;
            _fullname = String.Empty;
            _sex = 0;
            _birthdate = null;
            _iDCardnumber = String.Empty;
            _mobilenumber = String.Empty;
            _diploma = String.Empty;
            _duty = String.Empty;
            _qualificationtype = String.Empty;
            _certificationno = String.Empty;
            _certificate1 = String.Empty;
            _certificateawarddate1 = null;
            _certificate2 = String.Empty;
            _certificateawarddate2 = null;
            _certificate3 = String.Empty;
            _certificateawarddate3 = null;
            _certificate4 = String.Empty;
            _certificateawarddate4 = null;
            _certificate5 = String.Empty;
            _certificateawarddate5 = null;
            _stagnation = String.Empty;
            _workdate = null;
            _entrydate = null;
            _isbackbone = 0;
            _itemname = String.Empty;
            _itemproperty = String.Empty;
            _maintainspecialty = String.Empty;
            _remark = String.Empty;
            _cityId = String.Empty;
            _districtid = String.Empty;
            _companyid = String.Empty;
            _gridid = String.Empty;
            _postsid = String.Empty;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
            _isSelfMaintain = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourcePersonnel(string id, string twodimensionalcode, string fullname, short sex, DateTime birthdate, string iDCardnumber,
           string mobilenumber, string diploma, string duty, string qualificationtype, string certificationno, string certificate1,
           DateTime certificateawarddate1, string certificate2, DateTime certificateawarddate2, string certificate3, DateTime certificateawarddate3,
           string certificate4, DateTime certificateawarddate4, string certificate5, DateTime certificateawarddate5, string stagnation,
           DateTime workdate, DateTime entrydate, short isbackbone, string itemname, string itemproperty, string maintainspecialty,
           string remark, string companyid, string gridid, string cityId, string districtid, string postid,
            string createuserid, DateTime createdate, short delflag)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _twodimensionalcode = twodimensionalcode;
            _fullname = fullname;
            _sex = sex;
            _birthdate = birthdate;
            _iDCardnumber = iDCardnumber;
            _mobilenumber = mobilenumber;
            _diploma = diploma;
            _duty = duty;
            _qualificationtype = qualificationtype;
            _certificationno = certificationno;
            _certificate1 = certificate1;
            _certificateawarddate1 = certificateawarddate1;
            _certificate2 = certificate2;
            _certificateawarddate2 = certificateawarddate2;
            _certificate3 = certificate3;
            _certificateawarddate3 = certificateawarddate3;
            _certificate4 = certificate4;
            _certificateawarddate4 = certificateawarddate4;
            _certificate5 = certificate5;
            _certificateawarddate5 = certificateawarddate5;
            _stagnation = stagnation;
            _workdate = workdate;
            _entrydate = entrydate;
            _isbackbone = isbackbone;
            _itemname = itemname;
            _itemproperty = itemproperty;
            _maintainspecialty = maintainspecialty;
            _remark = remark;
            _cityId = cityId;
            _districtid = districtid;
            _companyid = companyid;
            _gridid = gridid;
            _postsid = postid;
            _createuserid = createuserid;
            _createdate = createdate;
            _delflag = delflag;

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
        /// 二维码
        /// </summary>		
        public string TwoDimensionalCode
        {
            get { return _twodimensionalcode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TwoDimensionalCode", value, value.ToString());

                _isChanged |= (_twodimensionalcode != value); _twodimensionalcode = value;
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
        /// 性别
        /// </summary>		
        public short Sex
        {
            get { return _sex; }
            set
            {
                _isChanged |= (_sex != value); _sex = value;
            }
        }

        /// <summary>
        /// 出生日期
        /// </summary>		
        public DateTime? BirthDate
        {
            get { return _birthdate; }
            set
            {
                _isChanged |= (_birthdate != value); _birthdate = value;
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
                    if (value.Length > 18)
                        throw new ArgumentOutOfRangeException("Invalid value for IDCardNumber", value, value.ToString());

                _isChanged |= (_iDCardnumber != value); _iDCardnumber = value;
            }
        }
        /// <summary>
        /// 手机号码
        /// </summary>		
        public string MobileNumber
        {
            get { return _mobilenumber; }
            set
            {
                if (value != null)
                    if (value.Length > 15)
                        throw new ArgumentOutOfRangeException("Invalid value for MobileNumber", value, value.ToString());

                _isChanged |= (_mobilenumber != value); _mobilenumber = value;
            }
        }
        /// <summary>
        /// 学历文凭
        /// </summary>		
        public string DiplomaId
        {
            get { return _diploma; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DiplomaId", value, value.ToString());

                _isChanged |= (_diploma != value); _diploma = value;
            }
        }
        /// <summary>
        ///人员职责
        /// </summary>		
        public string PostsId
        {
            get { return _postsid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PostId", value, value.ToString());

                _isChanged |= (_postsid != value); _postsid = value;
            }
        }
        /// <summary>
        ///人员职责
        /// </summary>		
        public string DutyId
        {
            get { return _duty; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DutyId", value, value.ToString());

                _isChanged |= (_duty != value); _duty = value;
            }
        }
        /// <summary>
        /// 认证资历类型
        /// </summary>		
        public string QualificationTypeId
        {
            get { return _qualificationtype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for QualificationTypeId", value, value.ToString());

                _isChanged |= (_qualificationtype != value); _qualificationtype = value;
            }
        }
        /// <summary>
        /// 认证编号
        /// </summary>		
        public string CertificationNo
        {
            get { return _certificationno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CertificationNo", value, value.ToString());

                _isChanged |= (_certificationno != value); _certificationno = value;
            }
        }
        /// <summary>
        /// 移动公司上岗证1
        /// </summary>		
        public string Certificate1
        {
            get { return _certificate1; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Certificate1", value, value.ToString());

                _isChanged |= (_certificate1 != value); _certificate1 = value;
            }
        }
        /// <summary>
        /// 上岗证1颁发时间
        /// </summary>		
        public DateTime? CertificateAwardDate1
        {
            get { return _certificateawarddate1; }
            set
            {
                _isChanged |= (_certificateawarddate1 != value); _certificateawarddate1 = value;
            }
        }
        /// <summary>
        /// 移动公司上岗证2
        /// </summary>		
        public string Certificate2
        {
            get { return _certificate2; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Certificate2", value, value.ToString());

                _isChanged |= (_certificate2 != value); _certificate2 = value;
            }
        }
        /// <summary>
        /// 上岗证2颁发时间
        /// </summary>		
        public DateTime? CertificateAwardDate2
        {
            get { return _certificateawarddate2; }
            set
            {
                _isChanged |= (_certificateawarddate2 != value); _certificateawarddate2 = value;
            }
        }
        /// <summary>
        /// 移动公司上岗证3
        /// </summary>		
        public string Certificate3
        {
            get { return _certificate3; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Certificate3", value, value.ToString());

                _isChanged |= (_certificate3 != value); _certificate3 = value;
            }
        }
        /// <summary>
        /// 上岗证3颁发时间
        /// </summary>		
        public DateTime? CertificateAwardDate3
        {
            get { return _certificateawarddate3; }
            set
            {
                _isChanged |= (_certificateawarddate3 != value); _certificateawarddate3 = value;
            }
        }
        /// <summary>
        /// 移动公司上岗证4
        /// </summary>		
        public string Certificate4
        {
            get { return _certificate4; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Certificate4", value, value.ToString());

                _isChanged |= (_certificate4 != value); _certificate4 = value;
            }
        }
        /// <summary>
        /// 上岗证4颁发时间
        /// </summary>		
        public DateTime? CertificateAwardDate4
        {
            get { return _certificateawarddate4; }
            set
            {
                _isChanged |= (_certificateawarddate4 != value); _certificateawarddate4 = value;
            }
        }
        /// <summary>
        /// 移动公司上岗证5
        /// </summary>		
        public string Certificate5
        {
            get { return _certificate5; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Certificate5", value, value.ToString());

                _isChanged |= (_certificate5 != value); _certificate5 = value;
            }
        }
        /// <summary>
        /// 上岗证5颁发时间
        /// </summary>		
        public DateTime? CertificateAwardDate5
        {
            get { return _certificateawarddate5; }
            set
            {
                _isChanged |= (_certificateawarddate5 != value); _certificateawarddate5 = value;
            }
        }
        /// <summary>
        /// 所属驻点
        /// </summary>		
        public string StagnationId
        {
            get { return _stagnation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StagnationId", value, value.ToString());

                _isChanged |= (_stagnation != value); _stagnation = value;
            }
        } 
        /// <summary>
        /// 工作时间
        /// </summary>		
        public DateTime? WorkDate
        {
            get { return _workdate; }
            set
            {
                _isChanged |= (_workdate != value); _workdate = value;
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
        /// 是否骨干
        /// </summary>		
        public short IsBackbone
        {
            get { return _isbackbone; }
            set
            {
                _isChanged |= (_isbackbone != value); _isbackbone = value;
            }
        }
        /// <summary>
        /// 是否自维
        /// </summary>		
        public short IsSelfMaintain
        {
            get { return _isSelfMaintain; }
            set
            {
                _isChanged |= (_isSelfMaintain != value); _isSelfMaintain = value;
            }
        }
        /// <summary>
        /// 项目名称
        /// </summary>		
        public string ItemName
        {
            get { return _itemname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ItemName", value, value.ToString());

                _isChanged |= (_itemname != value); _itemname = value;
            }
        }
        /// <summary>
        /// 项目属性
        /// </summary>		
        public string ItemPropertyId
        {
            get { return _itemproperty; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ItemPropertyId", value, value.ToString());

                _isChanged |= (_itemproperty != value); _itemproperty = value;
            }
        }
        /// <summary>
        /// 维护专业
        /// </summary>		
        public string MaintainSpecialtyId
        {
            get { return _maintainspecialty; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainSpecialtyId", value, value.ToString());

                _isChanged |= (_maintainspecialty != value); _maintainspecialty = value;
            }
        }
        /// <summary>
        /// 备注【主要资历、承担过项目、专业的主要经验】
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
        /// 所属网格
        /// </summary>		
        public string GridId
        {
            get { return _gridid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GridId", value, value.ToString());

                _isChanged |= (_gridid != value); _gridid = value;
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
