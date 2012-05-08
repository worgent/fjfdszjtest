using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.OneSevenZeroManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceOneSevenZero
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _jobno;
        private string _citycountryno;
        private string _ensuregrade;
        private string _clientgrade;
        private string _importlevel;
        private string _managertel;
        private string _name ;
        private Char _isflyover;
        private string _intendmoney;
        private string _expressremark;
        private string _operationtype;
        private string _applyman;
        private string _applytel;
        private string _applyfax;
        private string _clienttype;
        private string _communicateaddress;
        private string _groupid;
        private string _clientcompanyname ;
        private string _citycounty;
        private string _projectexpository ;
        private DateTime _completetime13; 
        private DateTime _completetime14; 
        private DateTime _completetime15; 
        private DateTime _completetime16;
        private string _wanequipment ;
        private string _wanequipmentport;
        private string _bandwidth;
        private string _circuitno;
        private string _transfersequipment;
        private string _transfersequipmentport;
        private string _coreinformation ;
        private string _orgnetmode;
        private string _vlan;
        private string _wanswitchequipment;
        private string _wanswitchequipmentport;
        private string _basestation ;
        private string _clientip;
        private string _clientgateway;
        private string _clientsubnetmask;
        private string _accesstothebasestation ;
        private string _noresource ;
        private string _wanbossfactorymode   ;
        private string _userfirstfactory;
        private string _userfirstmode ;
        private string _usersencodefactory;
        private string _usersencodemode ;
        private string _userthirdfactory  ;
        private string _userthirdmode ;
        private string _userfourfactory ;
        private string _userfourmode ;
        private string _userfivefactory;
        private string _userfivemode;
        private string _basestationfirstfactory; 
        private string _basestationfirstmode ;
        private string _basestationsencodefactory ;
        private string _basestationsencodemode;
        private string _groupinnet   ;
        private string _maintenancecompany;
        private Char _isintegratebeforehand;
        private Char _islinepipebefordhand;
        private Char _isintegrateindue;
        private Char _islinepipeindue;
        private string _state;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceOneSevenZero()
        {
            _id = String.Empty;
            //_filedata=;
            _jobno = String.Empty;
            _citycountryno = String.Empty;
            _ensuregrade = String.Empty;
            _clientgrade = String.Empty;
            _importlevel = String.Empty;
            _managertel = String.Empty;
        _name=String.Empty ;
        _isflyover ='\u0020';
        _intendmoney = String.Empty;
        _expressremark = String.Empty;
        _operationtype = String.Empty;
        _applyman = String.Empty;
        _applytel = String.Empty;
        _applyfax = String.Empty;
        _clienttype = String.Empty;
        _communicateaddress=String.Empty;
        _groupid = String.Empty;
        _clientcompanyname =String.Empty;
        _citycounty = String.Empty;
        _projectexpository =String.Empty;
        _completetime13=DateTime.Now; 
        _completetime14=DateTime.Now; 
        _completetime15=DateTime.Now; 
        _completetime16=DateTime.Now;
        _wanequipment =String.Empty;
        _wanequipmentport = String.Empty;
        _bandwidth = String.Empty;
        _circuitno = String.Empty;
        _transfersequipment = String.Empty;
        _transfersequipmentport = String.Empty;
        _coreinformation =String.Empty;
        _orgnetmode = String.Empty;
        _vlan = String.Empty;
        _wanswitchequipment = String.Empty;
        _wanswitchequipmentport = String.Empty;
        _basestation =String.Empty;
        _clientip = String.Empty;
        _clientgateway = String.Empty;
        _clientsubnetmask = String.Empty;
        _accesstothebasestation =String.Empty;
        _noresource =String.Empty;
        _wanbossfactorymode   =String.Empty;
        _userfirstfactory=String.Empty;
        _userfirstmode =String.Empty;
        _usersencodefactory=String.Empty;
        _usersencodemode =String.Empty;
        _userthirdfactory  =String.Empty;
        _userthirdmode =String.Empty;
        _userfourfactory =String.Empty;
        _userfourmode =String.Empty;
        _userfivefactory=String.Empty;
        _userfivemode=String.Empty;
        _basestationfirstfactory=String.Empty; 
        _basestationfirstmode =String.Empty;
        _basestationsencodefactory =String.Empty;
        _basestationsencodemode=String.Empty;
        _groupinnet   =String.Empty;
        _maintenancecompany=String.Empty;
        _isintegratebeforehand = '\u0020';
        _islinepipebefordhand = '\u0020';
        _isintegrateindue = '\u0020';
        _islinepipeindue = '\u0020';
        _state = "0";
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceOneSevenZero(string id,string jobno, string citycountryno, string ensuregrade, string clientgrade, string importlevel, string managertel,
           string name, Char isflyover, string intendmoney, string expressremark, string operationtype, string applyman,
           string applytel, string applyfax, string clienttype, string communicateaddress, string groupid,
           string clientcompanyname, string citycounty, string projectexpository, DateTime completetime13, DateTime completetime14,
           DateTime completetime15, DateTime completetime16, string wanequipment, string wanequipmentport, string bandwidth, string circuitno,
           string transfersequipment, string transfersequipmentport, string coreinformation, string orgnetmode, string vlan,
           string wanswitchequipment, string wanswitchequipmentport, string basestation, string clientip, string clientgateway,
           string clientsubnetmask, string accesstothebasestation, string noresource, string wanbossfactorymode, string userfirstfactory,
           string userfirstmode, string usersencodefactory, string usersencodemode,string userthirdfactory, string userthirdmode,
           string userfourfactory, string userfourmode, string userfivefactory,string userfivemode, string basestationfirstfactory,
           string basestationfirstmode, string basestationsencodefactory, string basestationsencodemode,string groupinnet, string maintenancecompany,
           Char isintegratebeforehand, Char islinepipebefordhand, Char isintegrateindue, Char islinepipeindue, string state
            )
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _jobno = jobno;
           _citycountryno=citycountryno;
           _ensuregrade=ensuregrade;
           _clientgrade=clientgrade;
           _importlevel =importlevel;
           _managertel =managertel;
           _name=name;
           _isflyover =isflyover;
           _intendmoney=intendmoney;
           _expressremark=expressremark;
           _operationtype=operationtype;
           _applyman=applyman ;
           _applytel=applytel ; 
           _applyfax=applyfax ; 
           _clienttype= clienttype;
           _communicateaddress=communicateaddress ;
           _groupid =groupid ;
           _clientcompanyname =clientcompanyname ;
           _citycounty =citycounty ;
           _projectexpository = projectexpository;
           _completetime13= completetime13; 
           _completetime14= completetime14; 
           _completetime15=completetime15 ; 
           _completetime16= completetime16;
           _wanequipment = wanequipment;
           _wanequipmentport= wanequipmentport;
           _bandwidth=bandwidth; 
           _circuitno=circuitno ;
           _transfersequipment = transfersequipment;
           _transfersequipmentport = transfersequipmentport;
           _coreinformation =coreinformation ;
           _orgnetmode =orgnetmode ;
           _vlan =vlan  ;
           _wanswitchequipment=wanswitchequipment ;
           _wanswitchequipmentport=wanswitchequipmentport ;
           _basestation = basestation;
           _clientip   = clientip;
           _clientgateway = clientgateway;
           _clientsubnetmask =clientsubnetmask ;
           _accesstothebasestation = accesstothebasestation;
           _noresource =noresource ;
           _wanbossfactorymode   = wanbossfactorymode;
           _userfirstfactory=userfirstfactory ;
           _userfirstmode = userfirstmode;
           _usersencodefactory=usersencodefactory ;
           _usersencodemode =usersencodemode ;
           _userthirdfactory  = userthirdfactory;
           _userthirdmode = userthirdmode ;
           _userfourfactory =userfourfactory  ;
           _userfourmode =userfourmode ;
           _userfivefactory=userfivefactory ;
           _userfivemode=userfivemode ;
           _basestationfirstfactory=basestationfirstfactory ; 
           _basestationfirstmode =basestationfirstmode ;
           _basestationsencodefactory = basestationsencodefactory;
           _basestationsencodemode= basestationsencodemode;
           _groupinnet   =groupinnet ;
           _maintenancecompany= maintenancecompany;
           _isintegratebeforehand=isintegratebeforehand ;
           _islinepipebefordhand =islinepipebefordhand ;
           _isintegrateindue = isintegrateindue;
           _islinepipeindue = islinepipeindue;
           _state = state;
        }

        #endregion // End Full Constructor

        #region Public Properties

        /// <summary>
        /// 主键
        /// </summary>		
        public String Id
        {
            get { return _id; }
            set
            {
                _isChanged |= (_id != value); _id = value;
            }
        }
        
        /// <summary>
        /// 工单号
        /// </summary>		
        public string JobNo
        {
            get { return _jobno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for JobNo", value, value.ToString());
                _isChanged |= (_jobno != value); _jobno = value;
            }
        }
        /// <summary>
        /// 跨县市专线工单号
        /// </summary>		
        public string CityCountryNo
        {
            get { return _citycountryno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityCountryNo", value, value.ToString());
                _isChanged |= (_citycountryno != value); _citycountryno = value;
            }
        }
        /// <summary>
        /// 业务保障等级
        /// </summary>		
        public string EnsureGrade
        {
            get { return _ensuregrade; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EnsureGrade", value, value.ToString());
                _isChanged |= (_ensuregrade != value); _ensuregrade = value;
            }
        }
        /// <summary>
        /// 客户星级
        /// </summary>		
        public string ClientGrade
        {
            get { return _clientgrade; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientGrade", value, value.ToString());

                _isChanged |= (_clientgrade != value); _clientgrade = value;
            }
        }

        /// <summary>
        /// 重要级别
        /// </summary>		
        public string ImportLevel
        {
            get { return _importlevel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ImportLevel", value, value.ToString());

               
                _isChanged |= (_importlevel != value); _importlevel = value;
            }
        }
        /// <summary>
        /// 客户经理联系电话
        /// </summary>		
        public string ManagerTel
        {
            get { return _managertel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ManagerTel", value, value.ToString());

                _isChanged |= (_managertel != value); _managertel = value;
            }
        }
        /// <summary>
        /// 姓名
        /// </summary>		
        public string Name
        {
            get { return _name; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Name", value, value.ToString());

                _isChanged |= (_name != value); _name = value;
            }
        }
        /// <summary>
        /// 是否跨县
        /// </summary>		
        public Char IsFlyOver
        {
            get { return _isflyover; }
            set
            {
                _isChanged |= (_isflyover != value); _isflyover = value;
            }
        }
        /// <summary>
        ///预计月收入（元）
        /// </summary>		
        public string IntendMoney
        {
            get { return _intendmoney; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IntendMoney", value, value.ToString());
                _isChanged |= (_intendmoney != value); _intendmoney = value;
            }
        }
        /// <summary>
        /// 特殊要求
        /// </summary>		
        public string ExpressRemark
        {
            get { return _expressremark; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ExpressRemark", value, value.ToString());

                _isChanged |= (_expressremark != value); _expressremark = value;
            }
        }
        /// <summary>
        /// 业务类型
        /// </summary>		
        public string OperationType
        {
            get { return _operationtype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OperationType", value, value.ToString());
                _isChanged |= (_operationtype != value); _operationtype = value;
            }
        }
        /// <summary>
        /// 申请联系人
        /// </summary>		
        public string ApplyMan
        {
            get { return _applyman; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ApplyMan", value, value.ToString());
                _isChanged |= (_applyman != value); _applyman = value;
            }
        }
        /// <summary>
        /// 联系电话
        /// </summary>		
        public string ApplyTel
        {
            get { return _applytel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ApplyTel", value, value.ToString());
                _isChanged |= (_applytel != value); _applytel = value;
            }
        }
        /// <summary>
        /// 传真
        /// </summary>		
        public string ApplyFax
        {
            get { return _applyfax; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ApplyFax", value, value.ToString());
                _isChanged |= (_applyfax != value); _applyfax = value;
            }
        }
       
        /// <summary>
        /// 客户类型
        /// </summary>		
        public string ClientType
        {
            get { return _clienttype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientType", value, value.ToString());
                _isChanged |= (_clienttype != value); _clienttype = value;
            }
        }
         /// <summary>
        /// 通信地址
        /// </summary>		
        public string CommunicateAddress
        {
            get { return _communicateaddress; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunicateAddress", value, value.ToString());

                _isChanged |= (_communicateaddress != value); _communicateaddress = value;
            }
        }
         /// <summary>
        /// 集团编码
        /// </summary>		
        public string GroupId
        {
            get { return _groupid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GroupId", value, value.ToString());
                _isChanged |= (_groupid != value); _groupid = value;
            }
        }
         /// <summary>
        /// 客户单位名称
        /// </summary>		
        public string ClientCompanyName
        {
            get { return _clientcompanyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientCompanyName", value, value.ToString());

                _isChanged |= (_clientcompanyname != value); _clientcompanyname = value;
            }
        }
         /// <summary>
        /// 县市选择
        /// </summary>		
        public string CityCounty
        {
            get { return _citycounty; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityCounty", value, value.ToString());

                _isChanged |= (_citycounty != value); _citycounty = value;
            }
        } /// <summary>
        /// 方案说明
        /// </summary>		
        public string ProjectexPository
        {
            get { return _projectexpository; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ProjectexPository", value, value.ToString());

                _isChanged |= (_projectexpository != value); _projectexpository = value;
            }
        }
        /// <summary>
        /// 完成时间13
        /// </summary>		
        public DateTime CompleteTime13
        {
            get { return _completetime13; }
            set
            {
                _isChanged |= (_completetime13 != value); _completetime13 = value;
            }
        }
        /// <summary>
        /// 完成时间14
        /// </summary>		
        public DateTime CompleteTime14
        {
            get { return _completetime14; }
            set
            {
                _isChanged |= (_completetime14 != value); _completetime14 = value;
            }
        }
        /// <summary>
        /// 完成时间15
        /// </summary>		
        public DateTime CompleteTime15
        {
            get { return _completetime15; }
            set
            {
                _isChanged |= (_completetime15 != value); _completetime15 = value;
            }
        }
        /// <summary>
        /// 完成时间16
        /// </summary>		
        public DateTime CompleteTime16
        {
            get { return _completetime16; }
            set
            {
                _isChanged |= (_completetime16 != value); _completetime16 = value;
            }
        }
        /// <summary>
        /// 城域网传输设备（或A端传输设备）
        /// </summary>		
        public string WanEquipment
        {
            get { return _wanequipment; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WanEquipment", value, value.ToString());

                _isChanged |= (_wanequipment != value); _wanequipment = value;
            }
        }
        /// <summary>
        /// 城域网传输设备端口（或A端传输设备端口）
        /// </summary>		
        public string WanEquipmentPort
        {
            get { return _wanequipmentport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WanEquipmentPort", value, value.ToString());

                _isChanged |= (_wanequipmentport != value); _wanequipmentport = value;
            }
        }
        /// <summary>
        /// 带宽
        /// </summary>		
        public string Bandwidth
        {
            get { return _bandwidth; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Bandwidth", value, value.ToString());
                _isChanged |= (_bandwidth != value); _bandwidth = value;
            }
        }
        /// <summary>
        /// 电路编号
        /// </summary>		
        public string CircuitNo
        {
            get { return _circuitno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CircuitNo", value, value.ToString());
                _isChanged |= (_circuitno != value); _circuitno = value;
            }
        }
        /// <summary>
        /// 基站端传输设备
        /// </summary>		
        public string TransfersEquipment
        {
            get { return _transfersequipment; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TransfersEquipment", value, value.ToString());
                _isChanged |= (_transfersequipment != value); _transfersequipment = value;
            }
        }
         /// <summary>
        /// 基站端传输设备端口
        /// </summary>		
        public string TransfersEquipmentPort
        {
            get { return _transfersequipmentport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TransfersEquipmentPort", value, value.ToString());
                _isChanged |= (_transfersequipmentport != value); _transfersequipmentport = value;
            }
        }
         /// <summary>
        /// 纤芯资料
        /// </summary>		
        public string CoreInformation
        {
            get { return _coreinformation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CoreInformation", value, value.ToString());

                _isChanged |= (_coreinformation != value); _coreinformation = value;
            }
        }
         /// <summary>
        /// 组网方式
        /// </summary>		
        public string OrgNetMode
        {
            get { return _orgnetmode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OrgNetMode", value, value.ToString());
                _isChanged |= (_orgnetmode != value); _orgnetmode = value;
            }
        }
         /// <summary>
        /// VLAN
        /// </summary>		
        public string VLAN
        {
            get { return _vlan; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for VLAN", value, value.ToString());
                _isChanged |= (_vlan != value); _vlan = value;
            }
        }
          /// <summary>
        /// 城域网交换设备
        /// </summary>		
        public string WanSwitchEquipment
        {
            get { return _wanswitchequipment; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WanSwitchEquipment", value, value.ToString());
                _isChanged |= (_wanswitchequipment != value); _wanswitchequipment = value;
            }
        }
          /// <summary>
        /// 城域网交换设备端口
        /// </summary>		
        public string WanSwitchEquipmentPort
        {
            get { return _wanswitchequipmentport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WanSwitchEquipmentPort", value, value.ToString());
                _isChanged |= (_wanswitchequipmentport != value); _wanswitchequipmentport = value;
            }
        }
          /// <summary>
        /// 接入基站/全业务机房
        /// </summary>		
        public string Basestation
        {
            get { return _basestation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Basestation", value, value.ToString());

                _isChanged |= (_basestation != value); _basestation = value;
            }
        }
          /// <summary>
        /// 客户IP
        /// </summary>		
        public string ClientIp
        {
            get { return _clientip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientIp", value, value.ToString());
                _isChanged |= (_clientip != value); _clientip = value;
            }
        }
          /// <summary>
        /// 网关IP
        /// </summary>		
        public string ClientGateway
        {
            get { return _clientgateway; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientGateway", value, value.ToString());
                _isChanged |= (_clientgateway != value); _clientgateway = value;
            }
        }
          /// <summary>
        /// 子网掩码
        /// </summary>		
        public string ClientSubnetMask
        {
            get { return _clientsubnetmask; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientSubnetMask", value, value.ToString());
                _isChanged |= (_clientsubnetmask != value); _clientsubnetmask = value;
            }
        }
          /// <summary>
        /// 接入基站
        /// </summary>		
        public string AccessToTheBasestation
        {
            get { return _accesstothebasestation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessToTheBasestation", value, value.ToString());

                _isChanged |= (_accesstothebasestation != value); _accesstothebasestation = value;
            }
        }
         /// <summary>
        /// 码号资源
        /// </summary>		
        public string NoResource
        {
            get { return _noresource; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NoResource", value, value.ToString());

                _isChanged |= (_noresource != value); _noresource = value;
            }
        }
         /// <summary>
        /// 城域网BOSS专线汇聚设备厂家及型号
        /// </summary>		
        public string WanBossFactoryMode
        {
            get { return _wanbossfactorymode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for WanBossFactoryMode", value, value.ToString());

                _isChanged |= (_wanbossfactorymode != value); _wanbossfactorymode = value;
            }
        }
         /// <summary>
        /// 用户端第一台设备厂家
        /// </summary>		
        public string UserFirstFactory
        {
            get { return _userfirstfactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFirstFactory", value, value.ToString());

                _isChanged |= (_userfirstfactory != value); _userfirstfactory = value;
            }
        }
         /// <summary>
        /// 用户端第一台设备型号
        /// </summary>		
        public string UserFirstMode
        {
            get { return _userfirstmode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFirstMode", value, value.ToString());

                _isChanged |= (_userfirstmode != value); _userfirstmode = value;
            }
        }
         /// <summary>
        /// 用户端第二台设备厂家
        /// </summary>		
        public string UserSencodeFactory
        {
            get { return _usersencodefactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserSencodeFactory", value, value.ToString());

                _isChanged |= (_usersencodefactory != value); _usersencodefactory = value;
            }
        }
         /// <summary>
        /// 用户端第二台设备型号
        /// </summary>		
        public string UserSencodeMode
        {
            get { return _usersencodemode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserSencodeMode", value, value.ToString());

                _isChanged |= (_usersencodemode != value); _usersencodemode = value;
            }
        }
         /// <summary>
        /// 用户端第三台设备厂家
        /// </summary>		
        public string UserThirdFactory
        {
            get { return _userthirdfactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserThirdFactory", value, value.ToString());

                _isChanged |= (_userthirdfactory != value); _userthirdfactory = value;
            }
        }
         /// <summary>
        /// 用户端第三台设备型号
        /// </summary>		
        public string UserThirdMode
        {
            get { return _userthirdmode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserThirdMode", value, value.ToString());

                _isChanged |= (_userthirdmode != value); _userthirdmode = value;
            }
        }
         /// <summary>
        /// 用户端第四台设备厂家
        /// </summary>		
        public string UserFourFactory
        {
            get { return _userfourfactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFourFactory", value, value.ToString());

                _isChanged |= (_userfourfactory != value); _userfourfactory = value;
            }
        }
         /// <summary>
        /// 用户端第四台设备型号
        /// </summary>		
        public string UserFourMode
        {
            get { return _userfourmode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFourMode", value, value.ToString());

                _isChanged |= (_userfourmode != value); _userfourmode = value;
            }
        }
         /// <summary>
        /// 用户端第五台设备厂家
        /// </summary>		
        public string UserFiveFactory
        {
            get { return _userfivefactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFiveFactory", value, value.ToString());

                _isChanged |= (_userfivefactory != value); _userfivefactory = value;
            }
        }
         /// <summary>
        /// 用户端第五台设备型号
        /// </summary>		
        public string UserFiveMode
        {
            get { return _userfivemode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UserFiveMode", value, value.ToString());

                _isChanged |= (_userfivemode != value); _userfivemode = value;
            }
        }
         /// <summary>
        /// 基站端第一台专线接入设备厂家
        /// </summary>		
        public string BasestationFirstFactory
        {
            get { return _basestationfirstfactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BasestationFirsFactory", value, value.ToString());

                _isChanged |= (_basestationfirstfactory != value); _basestationfirstfactory = value;
            }
        }
         /// <summary>
        /// 基站端第一台专线接入设备型号
        /// </summary>		
        public string BasestationFirstMode
        {
            get { return _basestationfirstmode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BasestationFirstMode", value, value.ToString());

                _isChanged |= (_basestationfirstmode != value); _basestationfirstmode = value;
            }
        }
         /// <summary>
        /// 基站端第二台专线接入设备厂家
        /// </summary>		
        public string BasestationSencodeFactory
        {
            get { return _basestationsencodefactory; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BasestationSencodeFactory", value, value.ToString());

                _isChanged |= (_basestationsencodefactory != value); _basestationsencodefactory = value;
            }
        }
         /// <summary>
        /// 基站端第二台专线接入设备型号
        /// </summary>		
        public string BasestationSencodeMode
        {
            get { return _basestationsencodemode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BasestationSencodeMode", value, value.ToString());

                _isChanged |= (_basestationsencodemode != value); _basestationsencodemode = value;
            }
        }
         /// <summary>
        /// 集团客户所属网格
        /// </summary>		
        public string GroupinNet
        {
            get { return _groupinnet; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GroupinNet", value, value.ToString());

                _isChanged |= (_groupinnet != value); _groupinnet = value;
            }
        }
         /// <summary>
        /// 维护单位
        /// </summary>		
        public string MaintenanceCompany
        {
            get { return _maintenancecompany; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintenanceCompany", value, value.ToString());

                _isChanged |= (_maintenancecompany != value); _maintenancecompany = value;
            }
        }
        /// <summary>
        /// 是否完成综维预移交
        /// </summary>		
        public Char IsIntegrateBeforeHand
        {
            get { return _isintegratebeforehand; }
            set
            {
                _isChanged |= (_isintegratebeforehand != value); _isintegratebeforehand = value;
            }
        }
        /// <summary>
        /// 是否完成管线预移交
        /// </summary>		
        public Char IsLinePipeBefordHand
        {
            get { return _islinepipebefordhand; }
            set
            {
                _isChanged |= (_islinepipebefordhand != value); _islinepipebefordhand = value;
            }
        }
        /// <summary>
        /// 是否完成综维正式移交
        /// </summary>		
        public Char IsIntegrateIndue
        {
            get { return _isintegrateindue; }
            set
            {
                _isChanged |= (_isintegrateindue != value); _isintegrateindue = value;
            }
        }
        /// <summary>
        /// 是否完成管线正式移交
        /// </summary>		
        public Char IsLinePipeIndue
        {
            get { return _islinepipeindue; }
            set
            {
                _isChanged |= (_islinepipeindue != value); _islinepipeindue = value;
            }
        }
        /// <summary>
        /// 单据状态
        /// </summary>		
        public string State
        {
            get { return _state; }
            set
            {
                _isChanged |= (_state != value); _state = value;
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
