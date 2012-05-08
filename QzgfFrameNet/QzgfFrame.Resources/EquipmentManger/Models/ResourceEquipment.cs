using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.EquipmentManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceEquipment
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string  _equipname ;
        private string _seqnumber;
        private short _position; 
        private string     _basestation;
        private string     _macaddress ;
        private DateTime?      _startdatetime ;
        private string     _loginstyle ;
        private string     _snmponlyread ;
        private string     _snmponlywrite ;
        private string     _username ;
        private string     _password;
        private string     _netmanageip ;
        private string _webport;
        private string _networkingmodeid;
        private string     _equipmodelid ;
        private string     _factoryid ;
        private string     _equiptypeid ;
        private string     _remark ;
        private string _orderno;
        private short _state;
        private string _createuserid;
        private DateTime _createdate;
        private string _districtid;
        private string _companyId;
        private short _delflag;
        private string _occupyport;


        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceEquipment()
        {
            _id = String.Empty;
            _equipname =String.Empty;
            _seqnumber = String.Empty;
            _position =0; 
            _basestation = String.Empty;
            _macaddress = String.Empty;
            _startdatetime = null;
            _loginstyle = String.Empty;
            _snmponlyread =String.Empty;
            _snmponlywrite =String.Empty;
            _username = String.Empty;
            _password =String.Empty;
            _netmanageip = String.Empty;
            _webport = String.Empty;
            _networkingmodeid = String.Empty;
            _equipmodelid = String.Empty;
            _factoryid = String.Empty;
            _equiptypeid = String.Empty;
            _remark = String.Empty;
            _orderno = String.Empty;
            _state = 0;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _districtid = String.Empty;
            _companyId = String.Empty;
            _occupyport = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceEquipment(string id, string equipname, string occupyport)
        {
            _id = id;
            _equipname = equipname;
            _occupyport = occupyport;
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
        /// 占用端口
        /// </summary>		
        public string OccupyPort
        {
            get { return _occupyport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OccupyPort", value, value.ToString());

                _isChanged |= (_occupyport != value); _occupyport = value;
            }
        }
        /// <summary>
        /// 设备名称
        /// </summary>		
        public string EquipName
        {
            get { return _equipname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipName", value, value.ToString());

                _isChanged |= (_equipname!= value); _equipname = value;
            }
        }

        /// <summary>
        /// 设备序列码
        /// </summary>		
        public string SeqNumber
        {
            get { return _seqnumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SeqNumber", value, value.ToString());

                _isChanged |= (_seqnumber != value); _seqnumber = value;
            }
        }
          /// <summary>
        /// 设备安装位置，用户机房或局端
        /// </summary>		
        public short Position
        {
            get { return _position; }
            set
            {
               _isChanged |= (_position != value); _position = value;
            }
        }

        /// <summary>
        /// 基站/机房名称
        /// </summary>		
        public string BaseStationName
        {
            get { return _basestation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BaseStationName", value, value.ToString());

                _isChanged |= (_basestation != value); _basestation = value;
            }
        } 
          /// <summary>
        /// MAC地址
        /// </summary>		
        public string MacAddress
        {
            get { return _macaddress; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MacAddress", value, value.ToString());

                _isChanged |= (_macaddress != value); _macaddress = value;
            }
        }

        /// <summary>
        /// 设备启用时间
        /// </summary>		
        public DateTime? StartDatetime
        {
            get { return _startdatetime; }
            set
            {
                _isChanged |= (_startdatetime != value); _startdatetime = value;
            }
        } 
          /// <summary>
        /// 登录方式
        /// </summary>		
        public string LoginStyle
        {
            get { return _loginstyle; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LoginStyle", value, value.ToString());

                _isChanged |= (_loginstyle != value); _loginstyle = value;
            }
        }
        /// <summary>
        /// SNMP只读共同体
        /// </summary>		
        public string SnmpOnlyRead
        {
            get { return _snmponlyread; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SnmpOnlyRead", value, value.ToString());

                _isChanged |= (_snmponlyread != value); _snmponlyread = value;
            }
        } 
          /// <summary>
        /// SNMP只写共同体
        /// </summary>		
        public string SnmpOnlyWrite
        {
            get { return _snmponlywrite; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SnmpOnlyWrite", value, value.ToString());

                _isChanged |= (_snmponlywrite != value); _snmponlywrite = value;
            }
        }

        /// <summary>
        /// 用户名
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
        /// 密码
        /// </summary>		
        public string PassWord
        {
            get { return _password; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PassWord", value, value.ToString());

                _isChanged |= (_password != value); _password = value;
            }
        }
        /// <summary>
        /// 网管IP
        /// </summary>		
        public string NetManageIp
        {
            get { return _netmanageip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NetManageIp", value, value.ToString());

                _isChanged |= (_netmanageip != value); _netmanageip = value;
            }
        } 
          /// <summary>
        /// Web端口
        /// </summary>		
        public string WebPort
        {
            get { return _webport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WebPort", value, value.ToString());

                _isChanged |= (_webport != value); _webport = value;
            }
        }

        /// <summary>
        /// 组网方式ID
        /// </summary>		
        public string NetWorkingModeId
        {
            get { return _networkingmodeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NetWorkingModeId", value, value.ToString());

                _isChanged |= (_networkingmodeid != value); _networkingmodeid = value;
            }
        } 
        /// <summary>
        /// 设备型号名称
        /// </summary>		
        public string EquipModelName
        {
            get { return _equipmodelid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipModelName", value, value.ToString());

                _isChanged |= (_equipmodelid != value); _equipmodelid = value;
            }
        } 
          /// <summary>
        /// 设备所属厂家ID
        /// </summary>		
        public string FactoryId
        {
            get { return _factoryid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FactoryId", value, value.ToString());

                _isChanged |= (_factoryid != value); _factoryid = value;
            }
        }

        /// <summary>
        /// 设备类型ID
        /// </summary>		
        public string EquipTypeId
        {
            get { return _equiptypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipTypeId", value, value.ToString());

                _isChanged |= (_equiptypeid != value); _equiptypeid = value;
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
        /// 排序号
        /// </summary>		
        public string OrderNo
        {
            get { return _orderno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrderNo", value, value.ToString());

                _isChanged |= (_orderno != value); _orderno = value;
            }
        }
        /// <summary>
        /// 状态
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
