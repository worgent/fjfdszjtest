using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.GroupClieManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceGroupClie
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _clieno;
        private string _cliename;
        private string _cliecontactel;
        private string _clieaddress;
        private string _cliearealongitude;//经度
        private string _cliearealatitude;//纬度
        private string _groupcliecontacts;
        private string _groupcliecontactel;
        private string _cliemanager;
        private string _cliemanagertel;
        private string _cliebizcontacts;
        private string _cliebizcontactel;

        private string _cityid;
        private string _districtid;
        private string _companyid;
        private string _gridid;
        private string _starleveid;
        private string _scalegradeid;
        private short _state;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceGroupClie()
        {
            _id = String.Empty;
            _clieno= String.Empty;
            _cliename = String.Empty;
            _cliecontactel = String.Empty;
            _clieaddress = String.Empty;
            _cliearealongitude = String.Empty;
            _cliearealatitude = String.Empty;
            _groupcliecontacts = String.Empty;
            _groupcliecontactel = String.Empty;            
            _cliemanager = String.Empty;
            _cliemanagertel = String.Empty;
            _cliebizcontacts = String.Empty;
            _cliebizcontactel = String.Empty;

            _cityid = String.Empty;
            _districtid = String.Empty;
            _companyid = String.Empty;
            _gridid = String.Empty;
            _starleveid = String.Empty;
            _scalegradeid = String.Empty;
            _state = 0;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceGroupClie(string id, string clieno, string cliename, string cliecontactel, string clieaddress, string cliearealongitude,
            string cliearealatitude, string groupcliecontacts, string groupcliecontactel, string cliemanager, string cliemanagertel, string cliebizcontacts, string cliebizcontactel,
            string cityid, string districtid, string companyid, string girdid, string starleveid, string scalegradeid,short state
            , string createuserid, DateTime createdate, short delflag)
        {
            _id = id;
            _clieno = clieno;
            _cliename = cliename;
            _cliecontactel = cliecontactel;
            _clieaddress = clieaddress;
            _cliearealongitude = cliearealongitude;
            _cliearealatitude = cliearealatitude;
            _groupcliecontacts = groupcliecontacts;
            _groupcliecontactel = groupcliecontactel;
            _cliemanager = cliemanager;
            _cliemanagertel = cliemanagertel;
            _cliebizcontacts = cliebizcontacts;
            _cliebizcontactel = cliebizcontactel;

            _cityid = cityid;
            _districtid = districtid;
            _companyid = companyid;
            _gridid = girdid;
            _starleveid = starleveid;
            _scalegradeid = scalegradeid;
            _state = state;
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
        /// 集团客户编号
        /// </summary>		
        public string ClieNo
        {
            get { return _clieno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieNo", value, value.ToString());

                _isChanged |= (_clieno != value); _clieno = value;
            }
        }
        /// <summary>
        /// 集团客户名称
        /// </summary>		
        public string ClieName
        {
            get { return _cliename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieName", value, value.ToString());

                _isChanged |= (_cliename != value); _cliename = value;
            }
        }/// <summary>
        /// 客户联系电话
        /// </summary>		
        public string ClieContacTel
        {
            get { return _cliecontactel; }
            set
            {
                if (value != null)
                    if (value.Length >15)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieContacTel", value, value.ToString());

                _isChanged |= (_cliecontactel != value); _cliecontactel = value;
            }
        }/// <summary>
        /// 客户联系地址
        /// </summary>		
        public string ClieAddress
        {
            get { return _clieaddress; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieAddress", value, value.ToString());

                _isChanged |= (_clieaddress != value); _clieaddress = value;
            }
        }
        /// <summary>
        /// 客户所属区域经度
        /// </summary>		
        public string ClieAreaLongitude
        {
            get { return _cliearealongitude; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieAreaLongitude", value, value.ToString());

                _isChanged |= (_cliearealongitude != value); _cliearealongitude = value;
            }
        }
        /// <summary>
        /// 客户所属区域纬度
        /// </summary>		
        public string ClieAreaLatitude
        {
            get { return _cliearealatitude; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieAreaLatitude", value, value.ToString());

                _isChanged |= (_cliearealatitude != value); _cliearealatitude = value;
            }
        }
        /// <summary>
        /// 集客部联系人
        /// </summary>		
        public string GroupClieContacts
        {
            get { return _groupcliecontacts; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GroupClieContacts", value, value.ToString());

                _isChanged |= (_groupcliecontacts != value); _groupcliecontacts = value;
            }
        }
        /// <summary>
        /// 集客部联系电话
        /// </summary>		
        public string GroupClieContacTel
        {
            get { return _groupcliecontactel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GroupClieContacTel", value, value.ToString());

                _isChanged |= (_groupcliecontactel != value); _groupcliecontactel = value;
            }
        }
        /// <summary>
        /// 客户经理
        /// </summary>		
        public string ClieManager
        {
            get { return _cliemanager; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieManager", value, value.ToString());

                _isChanged |= (_cliemanager != value); _cliemanager = value;
            }
        }
        /// <summary>
        /// 客户经理联系电话
        /// </summary>		
        public string ClieManagerTel
        {
            get { return _cliemanagertel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieManagerTel", value, value.ToString());

                _isChanged |= (_cliemanagertel != value); _cliemanagertel = value;
            }
        }
        /// <summary>
        /// 客户业务联系人
        /// </summary>		
        public string ClieBizContacts
        {
            get { return _cliebizcontacts; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieBizContacts", value, value.ToString());

                _isChanged |= (_cliebizcontacts != value); _cliebizcontacts = value;
            }
        }
        /// <summary>
        /// 客户业务联系电话
        /// </summary>		
        public string ClieBizContacTel
        {
            get { return _cliebizcontactel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityName", value, value.ToString());

                _isChanged |= (_cliebizcontactel != value); _cliebizcontactel = value;
            }
        }
        /// <summary>
        /// 所属城市ID号
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
        /// 所属单位ID
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
        /// 所属网格ID
        /// </summary>		
        public string GridIds
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
        /// 客户星级ID
        /// </summary>		
        public string StarLeveId
        {
            get { return _starleveid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StarLeveId", value, value.ToString());

                _isChanged |= (_starleveid != value); _starleveid = value;
            }
        }
        /// <summary>
        /// 规模等级ID
        /// </summary>		
        public string ScaleGradeId
        {
            get { return _scalegradeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ScaleGradeId", value, value.ToString());

                _isChanged |= (_scalegradeid != value); _scalegradeid = value;
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
        /// 是否需求本地修改数据标识
        /// </summary>		
        public short State
        {
            get { return _state; }
            set
            {
                _isChanged |= (_state != value); _state = value;
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
