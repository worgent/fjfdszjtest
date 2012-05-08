using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.ApparatusManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceApparatus
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
        private string _maintainspecialty;
        private string _seqno;
        private string _use;
        private string _manufacturer;
        private string _metername;
        private string _metermodel;
        private string _meterstateid;
        private string _remark;
        private string _companyid;
        private string _cityid;
        private string _districtid;
        private string _districtname;
        private string _gridid;
        private string _metertype;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceApparatus()
        {
            _id = String.Empty;
            //_filedata=;
            _twodimensionalcode = String.Empty;
            _stagnation = String.Empty;
            _maintainspecialty = String.Empty;
            _seqno = String.Empty;
            _use = String.Empty;
            _manufacturer = String.Empty;
            _metername = String.Empty;
            _metermodel = String.Empty;
            _meterstateid = String.Empty;
            _remark = String.Empty;
            _companyid = String.Empty;
            _cityid = String.Empty;
            _districtid = String.Empty;
            _districtname = String.Empty;
            _gridid = String.Empty;
            _metertype = String.Empty;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag=0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceApparatus(string id, string twodimensionalcode, string stagnation, string maintainspecialty,
            string seqno, string use, string manufacturer, string metername, string metermodel,string metertype,
            string state, string remark, string companyid, string cityid, string districtid,string districtname, string gridid
            , string createuserid, DateTime createdate,short delflag)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _twodimensionalcode = twodimensionalcode;
            _stagnation = stagnation;
            _maintainspecialty = maintainspecialty;
            _seqno = seqno;
            _use = use;
            _manufacturer = manufacturer;
            _metername = metername;
            _metermodel = metermodel;
            _meterstateid = state;
            _remark = remark;
            _companyid = companyid;
            _cityid = cityid;
            _districtid = districtid;
            _districtname = districtname;
            _gridid = gridid;
            _metertype = metertype;
            _createuserid = createuserid;
            _createdate = createdate;
            _delflag=delflag;
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
        /// 序列号
        /// </summary>		
        public string SeqNo
        {
            get { return _seqno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SeqNo", value, value.ToString());


                _isChanged |= (_seqno != value); _seqno = value;
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
        /// 仪表厂商
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
        /// 仪表类别
        /// </summary>		
        public string MeterType
        {
            get { return _metertype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MeterType", value, value.ToString());

                _isChanged |= (_metertype != value); _metertype = value;
            }
        }
        /// <summary>
        /// 仪表名称
        /// </summary>		
        public string MeterName
        {
            get { return _metername; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MeterName", value, value.ToString());

                _isChanged |= (_metername != value); _metername = value;
            }
        }
        /// <summary>
        /// 仪表型号
        /// </summary>		
        public string MeterModel
        {
            get { return _metermodel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MeterModel", value, value.ToString());

                _isChanged |= (_metermodel != value); _metermodel = value;
            }
        }
        /// <summary>
        /// 状 态
        /// </summary>		
        public string MeterStateId
        {
            get { return _meterstateid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MeterStateId", value, value.ToString());

                _isChanged |= (_meterstateid != value); _meterstateid = value;
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
        /// 所属县区名称
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
