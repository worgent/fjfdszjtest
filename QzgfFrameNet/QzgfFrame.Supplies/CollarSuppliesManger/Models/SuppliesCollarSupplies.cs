using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.CollarSuppliesManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesCollarSupplies
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
        private DateTime _collardate;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        private string _remark;
        private string _companyId;
        private string _collaruser;
        private int _num;
        private string _districtname;
        private string _companyname;
        private string _unitname;
        private string _suppliesTypeId;
        private string _suppliesTypeName;
        private string _isArrival;
        private int _arrivalnum;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesCollarSupplies()
        {
            _id = String.Empty;
            //_filedata=;
            _districtId = String.Empty;
            _companyId = String.Empty;
            _collardate = DateTime.Now;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
            _remark = String.Empty;
            _collaruser = String.Empty;
            _num = 0;
            _districtname = String.Empty;
            _companyname = String.Empty;
            _unitname = String.Empty;
            _suppliesTypeId = String.Empty;
            _suppliesTypeName = String.Empty;
            _isArrival = "0";
            _arrivalnum = 0;//默认到货为0
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCollarSupplies(string id, int num, string companyId, string districtId, string suppliesTypeName,
            DateTime collardate, string createuserid, DateTime createdate, short delflag,string collaruser)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _districtId = districtId;
            _collardate = collardate;
            _createuserid = createuserid;
            _createdate = createdate;
            _delflag = delflag;
            _collaruser = collaruser;
            _companyId = companyId;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCollarSupplies(string id, int num, int arrivalnum, DateTime collardate, string collaruser,
            string remark ,string companyId, string districtId, string suppliesTypeid,string companyname,
            string districtname, string suppliesTypename, string unitname, string isArrival)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _arrivalnum=arrivalnum;
            _collardate = collardate;
            _collaruser = collaruser;
            _remark = remark;
            _companyId = companyId;
            _districtId = districtId;
            _suppliesTypeId = suppliesTypeid;
            _districtname = districtname;
            _companyname = companyname;
            _suppliesTypeName = suppliesTypename;
            _unitname = unitname;
            _isArrival=isArrival;
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
        ///领料时间
        /// </summary>		
        public DateTime CollarDate
        {
            get { return _collardate; }
            set
            {
                _isChanged |= (_collardate != value); _collardate = value;
            }
        }
        /// <summary>
        ///是否全部到货:默认0,否,1为全部到货
        /// </summary>		
        public string IsArrival
        {
            get { return _isArrival; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for IsArrival", value, value.ToString());
                _isChanged |= (_isArrival != value); _isArrival = value;
            }
        }

        /// <summary>
        /// 到货数量
        /// </summary>		
        public int ArrivalNum
        {
            get { return _arrivalnum; }
            set
            {
                _isChanged |= (_arrivalnum != value); _arrivalnum = value;
            }
        }
        /// <summary>
        ///领料员
        /// </summary>		
        public string CollarUser
        {
            get { return _collaruser; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CollarUser", value, value.ToString());


                _isChanged |= (_collaruser != value); _collaruser = value;
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
