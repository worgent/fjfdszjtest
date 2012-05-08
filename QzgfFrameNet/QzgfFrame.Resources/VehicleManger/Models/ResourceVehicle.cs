using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations;

namespace QzgfFrame.Resources.VehicleManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceVehicle
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
        private string _stagnation;
        private string _licenseplatenumber  ;
        private string _maintainspecialty  ;
        private DateTime? _startdatetime;
        private int _usemileage;
        private string _nature   ;
        private string _licenseno;
        private string _engineno;
        private DateTime? _annualInspecttime;
        private string _manufacturer;
        private string _modelspecification;
        private string _displacement;
        private string _remark;
        private string _companyId;
        private string _cityId;
        private string _districtid;
        private string _gridid;
        private string _use;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        private string _linktel;
        private string _fullname;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceVehicle()
        {
            _id = String.Empty;
            //_filedata=;
            _twodimensionalcode = String.Empty;
            _stagnation = String.Empty;
            _licenseplatenumber = String.Empty;
            _maintainspecialty = String.Empty;
            _startdatetime = null;
            _usemileage =0;
            _nature = String.Empty;
            _licenseno = String.Empty;
            _engineno = String.Empty;
            _annualInspecttime = null;
            _manufacturer = String.Empty;
            _modelspecification = String.Empty;
            _displacement = String.Empty;
            _remark = String.Empty;
            _companyId = String.Empty;
            _cityId = String.Empty;
            _districtid = String.Empty;
            _gridid = String.Empty;
            _use = String.Empty;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
            _linktel = String.Empty;
            _fullname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceVehicle(string id, string twodimensionalcode, string stagnation, string licenseplatenumber, string maintainspecialty,
            DateTime startdatetime, int usemileage, string nature, string licenseno, string engineno, DateTime annualInspecttime,
            string manufacturer, string modelspecification, string displacement, string remark, string companyId,string cityId,
            string districtid, string gridid, string use, string createuserid, DateTime createdate, short delflag)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _twodimensionalcode = twodimensionalcode;
            _stagnation = stagnation;
            _licenseplatenumber = licenseplatenumber;
            _maintainspecialty = maintainspecialty;
            _startdatetime = startdatetime;
            _usemileage = usemileage;
            _nature = nature;
            _licenseno = licenseno;
            _engineno = engineno;
            _annualInspecttime = annualInspecttime;
            _manufacturer = manufacturer;
            _modelspecification = modelspecification;
            _displacement = displacement;
            _remark = remark;
            _companyId = companyId;
            _cityId = cityId;
            _districtid = districtid;
            _gridid = gridid;
            _use = use;
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
        ///二维码
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
        /// 车牌号
        /// </summary>		
        public string LicensePlateNumber
        {
            get { return _licenseplatenumber; }
            set
            {
                if (value != null)
                    if (value.Length > 30)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainSpecialtyId", value, value.ToString());
                _isChanged |= (_licenseplatenumber != value); _licenseplatenumber = value;
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
                    if (value.Length > 30)
                        throw new ArgumentOutOfRangeException("Invalid value for FullName", value, value.ToString());
                _isChanged |= (_fullname != value); _fullname = value;
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
                    if (value.Length > 15)
                        throw new ArgumentOutOfRangeException("Invalid value for LinkTel", value, value.ToString());
                _isChanged |= (_linktel != value); _linktel = value;
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
        /// 开始使用时间
        /// </summary>	
        [DisplayFormat(DataFormatString = "{0:d}")] 
        public DateTime? StartDatetime
        {
            get { return _startdatetime; }
            set
            {
                _isChanged |= (_startdatetime != value); _startdatetime = value;
            }
        }
        /// <summary>
        /// 开始使用里程数
        /// </summary>		
        public int UseMileage
        {
            get { return _usemileage; }
            set
            {
                _isChanged |= (_usemileage != value); _usemileage = value;
            }
        }
        /// <summary>
        /// 性质
        /// </summary>		
        public string VehicleNatureId
        {
            get { return _nature; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for VehicleNatureId", value, value.ToString());

                _isChanged |= (_nature != value); _nature = value;
            }
        }
        /// <summary>
        /// 用 途
        /// </summary>		
        public string UseId
        {
            get { return _use; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UseId", value, value.ToString());

                _isChanged |= (_use != value); _use = value;
            }
        }
        /// <summary>
        /// 行驶证号
        /// </summary>		
        public string DrivingLicenseNo
        {
            get { return _licenseno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DrivingLicenseNo", value, value.ToString());

                _isChanged |= (_licenseno != value); _licenseno = value;
            }
        }
        /// <summary>
        /// 发动机号 
        /// </summary>		
        public string EngineNo
        {
            get { return _engineno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EngineNo", value, value.ToString());

                _isChanged |= (_engineno != value); _engineno = value;
            }
        }
        /// <summary>
        /// 年检时间
        /// </summary>		
        [DisplayFormat(DataFormatString = "{0:d}")]
        public DateTime? AnnualInspectTime
        {
            get { return _annualInspecttime; }
            set
            {
                _isChanged |= (_annualInspecttime != value); _annualInspecttime = value;
            }
        }
        /// <summary>
        /// 生产厂家
        /// </summary>		
        public string ManuFacturer
        {
            get { return _manufacturer; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ManuFacturer", value, value.ToString());

                _isChanged |= (_manufacturer != value); _manufacturer = value;
            }
        }
        /// <summary>
        /// 型号规格
        /// </summary>		
        public string ModelSpecification
        {
            get { return _modelspecification; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ModelSpecification", value, value.ToString());

                _isChanged |= (_modelspecification != value); _modelspecification = value;
            }
        }
        /// <summary>
        /// 排气量
        /// </summary>		
        public string Displacement
        {
            get { return _displacement; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Displacement", value, value.ToString());

                _isChanged |= (_displacement != value); _displacement = value;
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
        ///所属网格
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
