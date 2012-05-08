using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.EnrolManager.Models
{
    /// <summary>
    /// 巡检登记
    /// </summary>
    [Serializable]
    public sealed class CopEnrol
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        /// <summary>
        /// id
        /// </summary>
        private string _id;
        /// <summary>
        /// 巡检计划id
        /// </summary>
        private string _copplanid;
        /// <summary>
        /// 巡检登记时间
        /// </summary>
        private string _cycenroltime;
        /// <summary>
        /// 维护人员ID
        /// </summary>
        private string _personnelid;
        /// <summary>
        /// 维护人员
        /// 需要手动输入
        /// </summary>
        private string _personnel;
        /// <summary>
        /// 是否删除:0为可用,1为删除
        /// 本删除为假删除,删除后,在页面将不可见,但是数据仍然存在
        /// </summary>
        private int _isdelete;

        /// <summary>
        /// 创建用户id
        /// </summary>
        private string _createuserid;
        /// <summary>
        /// 专线巡检登记创建时间
        /// </summary>
        private DateTime? _creationtime;
        /// <summary>
        /// 专线巡检登记删除时间
        /// </summary>
        private DateTime? _deletetime;

        #region 客户信息

        /// <summary>
        /// 详细地址
        /// </summary>
        private string _address;
        /// <summary>
        /// 接入基站
        /// </summary>
        private string _connectstation;
        /// <summary>
        /// 接入局端
        /// </summary>
        private string _connectport;
        /// <summary>
        /// 客户联系人
        /// </summary>
        private string _linkman;
        /// <summary>
        /// 联系电话
        /// </summary>
        private string _phone;

        #endregion 客户信息

        #region 线路检查
        /// <summary>
        /// 连接情况
        /// </summary>
        private string _connectcircs; 
        /// <summary>
        /// 电源线路检查
        /// </summary>
        private string _electricallinecheck; 
        /// <summary>
        /// 线路是否杂乱
        /// </summary>
        private string _lineclutter; 
        /// <summary>
        /// 绑扎情况是否正常
        /// </summary>
        private string _iscolligationcircsingear;
        #endregion 线路检查

        #region 设备检查

        /// <summary>
        /// 是否有隐患存在
        /// </summary>
        private string _ishiddentroublebe;
        /// <summary>
        /// 设备运行状态检查
        /// </summary>
        private string _equipmentrunstatecheck; 
        /// <summary>
        /// 是否除尘
        /// </summary>
        private string _isdustcatcher; 
        /// <summary>
        /// 标签情况
        /// </summary>
        private string _labelcircs; 
        /// <summary>
        /// 端口资料
        /// </summary>
        private string _portdata;
        /// <summary>
        /// 是否环境隐患存在
        /// </summary>
        private string _isenvironmentalhazardsexist; 
        /// <summary>
        /// 环境隐患内容
        /// </summary>
        private string _entironmentcontent;
        /// <summary>
        /// 设备接地是否正常
        /// </summary>
        private string _isequipmentearth;
        /// <summary>
        /// 尾纤标签
        /// </summary>
        private string _empennagelabel;
        #endregion 设备检查

        #region GPRS专网登陆测试
        /// <summary>
        /// 是否正常登入(ping用户专线地址)
        /// </summary>
        private string _isnormallogin;
        /// <summary>
        /// 平均延时
        /// </summary>
        private string _theaveragedelay;
        /// <summary>
        /// 掉包率
        /// </summary>
        private string _substitutionrate;
        #endregion GPRS专网登陆测试

        #region IMS专线/VOIP专线
        /// <summary>
        /// 电话拨测
        /// </summary>
        private string _phonecalltesting;
        /// <summary>
        /// 传真拨测
        /// </summary>
        private string _faxcalltesting;
        #endregion IMS专线/VOIP专线

        #region 网络测试
        /// <summary>
        /// ping 网关最小延时
        /// </summary>
        private string _gatewayminimumdelay;
        /// <summary>
        /// ping 网关最大延时
        /// </summary>
        private string _gatewaymaximumdelay;
        /// <summary>
        /// ping 网关平均延时
        /// </summary>
        private string _gatewayaveragedelay;
        /// <summary>
        /// ping 网关掉包率
        /// </summary>
        private string _gatewaysubstitutionrate;
        /// <summary>
        /// ping DNS最小延时
        /// </summary>
        private string _dnsminimumdelay;
        /// <summary>
        /// ping DNS最大延时
        /// </summary>
        private string _dnsmaximumdelay;
        /// <summary>
        /// ping DNS平均延时
        /// </summary>
        private string _dnsaveragedelay;
        /// <summary>
        /// ping DNS掉包率
        /// </summary>
        private string _dnssubstitutionrate;
        /// <summary>
        /// 网速测试
        /// </summary>
        private string _networkspeedtest;
        /// <summary>
        /// 下载测试
        /// </summary>
        private string _downloadtest;
        #endregion 网络测试

        #region 环境
        /// <summary>
        /// 温度
        /// </summary>
        private string _temperature;
        /// <summary>
        /// 湿度
        /// </summary>
        private string _humidity;
        /// <summary>
        /// 光电缆布放
        /// </summary>
        private string _opticalcablelaying;
        #endregion 环境

        #region 标签（资料准确性）
        /// <summary>
        /// 设备标签
        /// </summary>
        private string _devicelabel;
        /// <summary>
        /// 电源标签
        /// </summary>
        private string _powertab;
        /// <summary>
        /// 光缆标签
        /// </summary>
        private string _fiberopticcablelabel;
        /// <summary>
        /// 电路标签
        /// </summary>
        private string _circuitlabel;
        #endregion 标签（资料准确性）

        #region 电源（如果属于移动维护）
        /// <summary>
        /// UPS告警
        /// </summary>
        private string _upsalarms;
        /// <summary>
        /// 整流器/逆变器告警
        /// </summary>
        private string _inverteralarm;
        /// <summary>
        /// 交流电压
        /// </summary>
        private string _acvoltage;
        /// <summary>
        /// 直流电压
        /// </summary>
        private string _dcvoltage;
        /// <summary>
        /// 零地电压
        /// </summary>
        private string _zerotovoltage;
        /// <summary>
        /// 设备接地
        /// </summary>
        private string _equipmentgrounding;
        #endregion 电源（如果属于移动维护）

        #region 维护情况
        /// <summary>
        /// 存在问题
        /// </summary>
        private string _problems;
        /// <summary>
        /// 上次问题解决情况
        /// </summary>
        private string _lastproblemsolvingsituations;
        /// <summary>
        /// 其他巡检项目记录
        /// </summary>
        private string _otherinspectionitems;
        /// <summary>
        /// 操作记录
        /// </summary>
        private string _operationalrecords;
        /// <summary>
        /// 总体评估
        /// </summary>
        private string _overallassessment;
        /// <summary>
        /// 客户意见及签字
        /// </summary>
        private string _clientopinionsignature;
        /// <summary>
        /// 附件名称，上传时的名称
        /// </summary>
        private string _attachmentname;
        /// <summary>
        /// 附件新名称，上传到服务器后的新名称
        /// </summary>
        private string _newattachmentname;
        #endregion 维护情况

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public CopEnrol()
        {
            _id = String.Empty;
            _copplanid = String.Empty;
            _address = String.Empty;
            _connectstation = String.Empty;
            _connectport = String.Empty;
            _linkman = String.Empty;
            _phone = String.Empty;
            _connectcircs = String.Empty;
            _electricallinecheck = String.Empty;
            _lineclutter = String.Empty;
            _iscolligationcircsingear = String.Empty;
            _ishiddentroublebe = String.Empty;
            _equipmentrunstatecheck = String.Empty;
            _isdustcatcher = String.Empty;
            _labelcircs = String.Empty;
            _portdata = String.Empty;
            _isenvironmentalhazardsexist = String.Empty;
            _entironmentcontent = String.Empty;
            _isequipmentearth = String.Empty;
            _empennagelabel = String.Empty;
            _networkspeedtest = String.Empty;
            _downloadtest = String.Empty;
            _isnormallogin = String.Empty;
            _theaveragedelay = String.Empty;
            _substitutionrate = String.Empty;
            _phonecalltesting = String.Empty;
            _faxcalltesting = String.Empty;
            _problems = String.Empty;
            _lastproblemsolvingsituations = String.Empty;
            _attachmentname = String.Empty;
            _newattachmentname = String.Empty;
            _gatewayminimumdelay = String.Empty;
            _gatewaymaximumdelay = String.Empty;
            _gatewayaveragedelay = String.Empty;
            _gatewaysubstitutionrate = String.Empty;
            _dnsminimumdelay = String.Empty;
            _dnsmaximumdelay = String.Empty;
            _dnsaveragedelay = String.Empty;
            _dnssubstitutionrate = String.Empty;
            _personnelid = String.Empty;
            _cycenroltime = String.Empty;
            _isdelete = 0;
            _createuserid = null;
            _creationtime = null;
            _deletetime = null;
            _personnel = String.Empty;

            //环境
            _temperature = String.Empty;
            _humidity = String.Empty;
            _opticalcablelaying = String.Empty;

            //标签（资料准确性）
            _devicelabel = String.Empty;
            _powertab = String.Empty;
            _fiberopticcablelabel = String.Empty;
            _circuitlabel = String.Empty;

            //电源（如果属于移动维护）
            _upsalarms = String.Empty;
            _inverteralarm = String.Empty;
            _acvoltage = String.Empty;
            _dcvoltage = String.Empty;
            _zerotovoltage = String.Empty;
            _equipmentgrounding = String.Empty;


            _otherinspectionitems = String.Empty;
            _operationalrecords = String.Empty;
            _overallassessment = String.Empty;
            _clientopinionsignature = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public CopEnrol(string id, string copplanid, string address, string connectstation, string connectport, string linkman, 
            string phone,string connectcircs,string electricallinecheck,string lineclutter,
            string iscolligationcircsingear,string ishiddentroublebe,string equipmentrunstatecheck,string isdustcatcher,
            string labelcircs,string portdata,string isenvironmentalhazardsexist,string entironmentcontent,
            string isequipmentearth,string empennagelabel,string networkspeedtest,string downloadtest,string isnormallogin,
            string theaveragedelay,string substitutionrate,string phonecalltesting,string faxcalltesting,
            string problems,string lastproblemsolvingsituations,string createuserid,string personnel,
            string attachmentname, string newattachmentname,string gatewayminimumdelay, string gatewaymaximumdelay, 
            string gatewayaveragedelay, string gatewaysubstitutionrate,string dnsminimumdelay, string dnsmaximumdelay, 
            string dnsaveragedelay, string dnssubstitutionrate,string personnelid,string temperature,string humidity,
            string opticalcablelaying,string devicelabel,string powertab,string fiberopticcablelabel,string circuitlabel,
            string upsalarms,string inverteralarm,string acvoltage,string dcvoltage,string zerotovoltage,
            string equipmentgrounding,string otherinspectionitems,string operationalrecords,string overallassessment,
            string clientopinionsignature, string cycenroltime, int isdelete, DateTime? creationtime, DateTime? deletetime)
        {
            #region
            _id = id;
            _copplanid = copplanid;
            _address = address;
            _connectstation = connectstation;
            _connectport = connectport;
            _linkman = linkman;
            _phone = phone;
            _connectcircs = connectcircs;
            _electricallinecheck = electricallinecheck;
            _lineclutter = lineclutter;
            _iscolligationcircsingear = iscolligationcircsingear;
            _ishiddentroublebe = ishiddentroublebe;
            _equipmentrunstatecheck = equipmentrunstatecheck;
            _isdustcatcher = isdustcatcher;
            _labelcircs = labelcircs;
            _portdata = portdata;
            _isenvironmentalhazardsexist = isenvironmentalhazardsexist;
            _entironmentcontent = entironmentcontent;
            _isequipmentearth = isequipmentearth;
            _empennagelabel = empennagelabel;
            _networkspeedtest = networkspeedtest;
            _downloadtest = downloadtest;
            _isnormallogin = isnormallogin;
            _theaveragedelay = theaveragedelay;
            _substitutionrate = substitutionrate;
            _phonecalltesting = phonecalltesting;
            _faxcalltesting = faxcalltesting;
            _problems = problems;
            _lastproblemsolvingsituations = lastproblemsolvingsituations;
            _attachmentname = attachmentname;
            _newattachmentname = newattachmentname;
            _gatewayminimumdelay = gatewayminimumdelay;
            _gatewaymaximumdelay = gatewaymaximumdelay;
            _gatewayaveragedelay = gatewayaveragedelay;
            _gatewaysubstitutionrate = gatewaysubstitutionrate;
            _dnsminimumdelay = dnsminimumdelay;
            _dnsmaximumdelay = dnsmaximumdelay;
            _dnsaveragedelay = dnsaveragedelay;
            _dnssubstitutionrate = dnssubstitutionrate;
            _personnelid = personnelid;
            _cycenroltime = cycenroltime;
            _isdelete = isdelete;
            _createuserid = createuserid;
            _creationtime = creationtime;
            _deletetime = deletetime;
            _personnel = personnel;
            #endregion

            //环境
            _temperature = temperature;
            _humidity = humidity;
            _opticalcablelaying = opticalcablelaying;

            //标签（资料准确性）
            _devicelabel = devicelabel;
            _powertab = powertab;
            _fiberopticcablelabel = fiberopticcablelabel;
            _circuitlabel = circuitlabel;

            //电源（如果属于移动维护）
            _upsalarms = upsalarms;
            _inverteralarm = inverteralarm;
            _acvoltage = acvoltage;
            _dcvoltage = dcvoltage;
            _zerotovoltage = zerotovoltage;
            _equipmentgrounding = equipmentgrounding;

            _otherinspectionitems = otherinspectionitems;
            _operationalrecords = operationalrecords;
            _overallassessment = overallassessment;
            _clientopinionsignature = clientopinionsignature;
        }

        #endregion // End Full Constructor

        #region Public Properties

        #region
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
        /// 巡检计划id
        /// </summary>		
        public string CopPlanId 
        {
            get { return _copplanid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CopPlanId ", value, value.ToString());

                _isChanged |= (_copplanid != value); _copplanid = value;
            }
        }

        /// <summary>
        /// 详细地址
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
        /// 接入基站
        /// </summary>		
        public string ConnectStation
        {
            get { return _connectstation; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ConnectStation", value, value.ToString());

                _isChanged |= (_connectstation != value); _connectstation = value;
            }
        }
        /// <summary>
        /// 接入局端
        /// </summary>		
        public string ConnectPort
        {
            get { return _connectport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ConnectPort", value, value.ToString());

                _isChanged |= (_connectport != value); _connectport = value;
            }
        }
        /// <summary>
        /// 客户联系人
        /// </summary>		
        public string Linkman
        {
            get { return _linkman; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Linkman", value, value.ToString());

                _isChanged |= (_linkman != value); _linkman = value;
            }
        }
        /// <summary>
        /// 联系电话
        /// </summary>		
        public string Phone
        {
            get { return _phone; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Phone", value, value.ToString());

                _isChanged |= (_phone != value); _phone = value;
            }
        }
        /// <summary>
        /// 巡检登记时间
        /// </summary>		
        public string CycEnrolTime
        {
            get { return _cycenroltime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CycEnrolTime", value, value.ToString());

                _isChanged |= (_cycenroltime != value); _cycenroltime = value;
            }
        }

        /// <summary>
        /// 连接情况
        /// </summary>		
        public string ConnectCircs
        {
            get { return _connectcircs; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ConnectCircs", value, value.ToString());

                _isChanged |= (_connectcircs != value); _connectcircs = value;
            }
        }
        /// <summary>
        /// 电源线路检查
        /// </summary>		
        public string ElectricalLineCheck
        {
            get { return _electricallinecheck; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ElectricalLineCheck", value, value.ToString());

                _isChanged |= (_electricallinecheck != value); _electricallinecheck = value;
            }
        }
        /// <summary>
        /// 线路是否杂乱
        /// </summary>		
        public string Lineclutter
        {
            get { return _lineclutter; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Lineclutter", value, value.ToString());

                _isChanged |= (_lineclutter != value); _lineclutter = value;
            }
        }
        /// <summary>
        /// 绑扎情况是否正常
        /// </summary>		
        public string IsColligationCircsInGear
        {
            get { return _iscolligationcircsingear; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsColligationCircsInGear", value, value.ToString());

                _isChanged |= (_iscolligationcircsingear != value); _iscolligationcircsingear = value;
            }
        }
        /// <summary>
        /// 是否有隐患存在
        /// </summary>		
        public string IsHiddenTroubleBe
        {
            get { return _ishiddentroublebe; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsHiddenTroubleBe", value, value.ToString());

                _isChanged |= (_ishiddentroublebe != value); _ishiddentroublebe = value;
            }
        }
        /// <summary>
        /// 设备运行状态检查
        /// </summary>		
        public string EquipmentRunStateCheck
        {
            get { return _equipmentrunstatecheck; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipmentRunStateCheck", value, value.ToString());

                _isChanged |= (_equipmentrunstatecheck != value); _equipmentrunstatecheck = value;
            }
        }
        /// <summary>
        /// 是否除尘
        /// </summary>		
        public string IsDustCatcher
        {
            get { return _isdustcatcher; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsDustCatcher", value, value.ToString());

                _isChanged |= (_isdustcatcher != value); _isdustcatcher = value;
            }
        }
        /// <summary>
        /// 标签情况
        /// </summary>		
        public string LabelCircs
        {
            get { return _labelcircs; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LabelCircs", value, value.ToString());

                _isChanged |= (_labelcircs != value); _labelcircs = value;
            }
        }
        /// <summary>
        /// 端口资料
        /// </summary>		
        public string PortData
        {
            get { return _portdata; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PortData", value, value.ToString());

                _isChanged |= (_portdata != value); _portdata = value;
            }
        }
        /// <summary>
        /// 是否环境隐患存在
        /// </summary>		
        public string IsEnvironmentalHazardsExist
        {
            get { return _isenvironmentalhazardsexist; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsEnvironmentalHazardsExist", value, value.ToString());

                _isChanged |= (_isenvironmentalhazardsexist != value); _isenvironmentalhazardsexist = value;
            }
        }
        /// <summary>
        /// 环境隐患内容
        /// </summary>		
        public string Entironmentcontent
        {
            get { return _entironmentcontent; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Entironmentcontent", value, value.ToString());

                _isChanged |= (_entironmentcontent != value); _entironmentcontent = value;
            }
        }
        /// <summary>
        /// 设备接地是否正常
        /// </summary>		
        public string IsEquipmentEarth
        {
            get { return _isequipmentearth; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsEquipmentEarth", value, value.ToString());

                _isChanged |= (_isequipmentearth != value); _isequipmentearth = value;
            }
        }
        /// <summary>
        /// 尾纤标签
        /// </summary>		
        public string EmpennageLabel
        {
            get { return _empennagelabel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EmpennageLabel", value, value.ToString());

                _isChanged |= (_empennagelabel != value); _empennagelabel = value;
            }
        }
        /// <summary>
        /// ping 网关最小延时
        /// </summary>		
        public string GatewayMinimumDelay
        {
            get { return _gatewayminimumdelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GatewayMinimumDelay", value, value.ToString());

                _isChanged |= (_gatewayminimumdelay != value); _gatewayminimumdelay = value;
            }
        }
        /// <summary>
        /// ping 网关最大延时
        /// </summary>		
        public string GatewayMaximumDelay
        {
            get { return _gatewaymaximumdelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GatewayMaximumDelay", value, value.ToString());

                _isChanged |= (_gatewaymaximumdelay != value); _gatewaymaximumdelay = value;
            }
        }
        /// <summary>
        /// ping 网关平均延时
        /// </summary>		
        public string GatewayAverageDelay
        {
            get { return _gatewayaveragedelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GatewayAverageDelay", value, value.ToString());

                _isChanged |= (_gatewayaveragedelay != value); _gatewayaveragedelay = value;
            }
        }
        /// <summary>
        /// ping 网关掉包率
        /// </summary>		
        public string GatewaySubstitutionRate
        {
            get { return _gatewaysubstitutionrate; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GatewaySubstitutionRate", value, value.ToString());

                _isChanged |= (_gatewaysubstitutionrate != value); _gatewaysubstitutionrate = value;
            }
        }
        /// <summary>
        /// ping DNS最小延时
        /// </summary>		
        public string DnsMinimumDelay
        {
            get { return _dnsminimumdelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DnsMinimumDelay", value, value.ToString());

                _isChanged |= (_dnsminimumdelay != value); _dnsminimumdelay = value;
            }
        }
        /// <summary>
        /// ping DNS最大延时
        /// </summary>		
        public string DnsMaximumDelay
        {
            get { return _dnsmaximumdelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DnsMaximumDelay", value, value.ToString());

                _isChanged |= (_dnsmaximumdelay != value); _dnsmaximumdelay = value;
            }
        }
        /// <summary>
        /// ping DNS平均延时
        /// </summary>		
        public string DnsAverageDelay
        {
            get { return _dnsaveragedelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DnsAverageDelay", value, value.ToString());

                _isChanged |= (_dnsaveragedelay != value); _dnsaveragedelay = value;
            }
        }
        /// <summary>
        /// ping DNS掉包率
        /// </summary>		
        public string DnsSubstitutionRate
        {
            get { return _dnssubstitutionrate; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DnsSubstitutionRate", value, value.ToString());

                _isChanged |= (_dnssubstitutionrate != value); _dnssubstitutionrate = value;
            }
        }
        /// <summary>
        /// 网速测试
        /// </summary>		
        public string NetworkSpeedTest
        {
            get { return _networkspeedtest; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NetworkSpeedTest", value, value.ToString());

                _isChanged |= (_networkspeedtest != value); _networkspeedtest = value;
            }
        }
        /// <summary>
        /// 下载测试
        /// </summary>		
        public string DownloadTest
        {
            get { return _downloadtest; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DownloadTest", value, value.ToString());

                _isChanged |= (_downloadtest != value); _downloadtest = value;
            }
        }
        /// <summary>
        /// 是否正常登入(ping用户专线地址)
        /// </summary>		
        public string IsNormalLogin
        {
            get { return _isnormallogin; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IsNormalLogin", value, value.ToString());

                _isChanged |= (_isnormallogin != value); _isnormallogin = value;
            }
        }
        /// <summary>
        /// 平均延时
        /// </summary>		
        public string TheAverageDelay
        {
            get { return _theaveragedelay; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TheAverageDelay", value, value.ToString());

                _isChanged |= (_theaveragedelay != value); _theaveragedelay = value;
            }
        }
        /// <summary>
        /// 掉包率
        /// </summary>		
        public string SubstitutionRate
        {
            get { return _substitutionrate; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SubstitutionRate", value, value.ToString());

                _isChanged |= (_substitutionrate != value); _substitutionrate = value;
            }
        }
        /// <summary>
        /// 电话拨测
        /// </summary>		
        public string PhoneCallTesting
        {
            get { return _phonecalltesting; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PhoneCallTesting", value, value.ToString());

                _isChanged |= (_phonecalltesting != value); _phonecalltesting = value;
            }
        }
        /// <summary>
        /// 传真拨测
        /// </summary>		
        public string FaxCallTesting
        {
            get { return _faxcalltesting; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FaxCallTesting", value, value.ToString());

                _isChanged |= (_faxcalltesting != value); _faxcalltesting = value;
            }
        }
        /// <summary>
        /// 存在问题
        /// </summary>		
        public string Problems
        {
            get { return _problems; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Problems", value, value.ToString());

                _isChanged |= (_problems != value); _problems = value;
            }
        }
        /// <summary>
        /// 上次问题解决情况
        /// </summary>		
        public string LastProblemSolvingSituations
        {
            get { return _lastproblemsolvingsituations; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LastProblemSolvingSituations", value, value.ToString());

                _isChanged |= (_lastproblemsolvingsituations != value); _lastproblemsolvingsituations = value;
            }
        }
        /// <summary>
        /// 附件名称,上传时的名称
        /// </summary>		
        public string AttachmentName
        {
            get { return _attachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AttachmentName", value, value.ToString());

                _isChanged |= (_attachmentname != value); _attachmentname = value;
            }
        }

        /// <summary>
        /// 附件新名称,上传到服务的名称
        /// </summary>		
        public string NewAttachmentName
        {
            get { return _newattachmentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NewAttachmentName", value, value.ToString());

                _isChanged |= (_newattachmentname != value); _newattachmentname = value;
            }
        }

        /// <summary>
        /// 维护人员ID
        /// </summary>		
        public string PersonnelId
        {
            get { return _personnelid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintenancePersonnelID", value, value.ToString());

                _isChanged |= (_personnelid != value); _personnelid = value;
            }
        }
        #endregion

        #region 环境
        /// <summary>
        /// 温度
        /// </summary>		
        public string Temperature
        {
            get { return _temperature; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for  Temperature", value, value.ToString());

                _isChanged |= (_temperature != value); _temperature = value;
            }
        }
        /// <summary>
        /// 湿度
        /// </summary>		
        public string Humidity
        {
            get { return _humidity; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for  Humidity", value, value.ToString());

                _isChanged |= (_humidity != value); _humidity = value;
            }
        }
        /// <summary>
        /// 光电缆布放
        /// </summary>		
        public string  OpticalCableLaying
        {
            get { return _opticalcablelaying; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for   OpticalCableLaying", value, value.ToString());

                _isChanged |= (_opticalcablelaying != value); _opticalcablelaying = value;
            }
        }
        #endregion 环境

        #region 标签（资料准确性）
        /// <summary>
        /// 设备标签
        /// </summary>		
        public string DeviceLabel
        {
            get { return _devicelabel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DeviceLabel ", value, value.ToString());

                _isChanged |= (_devicelabel != value); _devicelabel = value;
            }
        }
        /// <summary>
        /// 电源标签
        /// </summary>		
        public string PowerLabel
        {
            get { return _powertab; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PowerLabel ", value, value.ToString());

                _isChanged |= (_powertab != value); _powertab = value;
            }
        }
        /// <summary>
        /// 光缆标签
        /// </summary>		
        public string FiberOpticCableLabel
        {
            get { return _fiberopticcablelabel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FiberOpticCableLabel ", value, value.ToString());

                _isChanged |= (_fiberopticcablelabel != value); _fiberopticcablelabel = value;
            }
        }
        /// <summary>
        /// 电路标签
        /// </summary>		
        public string CircuitLabel
        {
            get { return _circuitlabel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CircuitLabel ", value, value.ToString());

                _isChanged |= (_circuitlabel != value); _circuitlabel = value;
            }
        }
        #endregion 标签（资料准确性）

        #region 电源（如果属于移动维护）
        /// <summary>
        /// UPS告警	
        /// </summary>		
        public string UPSAlarms
        {
            get { return _upsalarms; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UPSAlarms ", value, value.ToString());

                _isChanged |= (_upsalarms != value); _upsalarms = value;
            }
        }
        /// <summary>
        /// 整流器/逆变器告警
        /// </summary>		
        public string InverterAlarm
        {
            get { return _inverteralarm; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for InverterAlarm ", value, value.ToString());

                _isChanged |= (_inverteralarm != value); _inverteralarm = value;
            }
        }
        /// <summary>
        /// 交流电压
        /// </summary>		
        public string ACVoltage
        {
            get { return _acvoltage; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ACVoltage ", value, value.ToString());

                _isChanged |= (_acvoltage != value); _acvoltage = value;
            }
        }
        /// <summary>
        /// 直流电压
        /// </summary>		
        public string DCVoltage
        {
            get { return _dcvoltage; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DCVoltage ", value, value.ToString());

                _isChanged |= (_dcvoltage != value); _dcvoltage = value;
            }
        }
        /// <summary>
        /// 零地电压
        /// </summary>		
        public string ZeroToVoltage
        {
            get { return _zerotovoltage; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZeroToVoltage ", value, value.ToString());

                _isChanged |= (_zerotovoltage != value); _zerotovoltage = value;
            }
        }
        /// <summary>
        /// 设备接地
        /// </summary>		
        public string EquipmentGrounding
        {
            get { return _equipmentgrounding; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipmentGrounding ", value, value.ToString());

                _isChanged |= (_equipmentgrounding != value); _equipmentgrounding = value;
            }
        }
        #endregion 电源（如果属于移动维护）

        /// <summary>
        /// 其他巡检项目记录
        /// </summary>		
        public string OtherInspectionItems
        {
            get { return _otherinspectionitems; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OtherInspectionItems ", value, value.ToString());

                _isChanged |= (_otherinspectionitems != value); _otherinspectionitems = value;
            }
        }
        /// <summary>
        /// 操作记录
        /// </summary>		
        public string OperationalRecords
        {
            get { return _operationalrecords; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OperationalRecords ", value, value.ToString());

                _isChanged |= (_operationalrecords != value); _operationalrecords = value;
            }
        }
        /// <summary>
        /// 总体评估
        /// </summary>		
        public string OverallAssessment
        {
            get { return _overallassessment; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OverallAssessment ", value, value.ToString());

                _isChanged |= (_overallassessment != value); _overallassessment = value;
            }
        }
        /// <summary>
        /// 客户意见及签字
        /// </summary>		
        public string ClientOpinionSignature
        {
            get { return _clientopinionsignature; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClientOpinionSignature ", value, value.ToString());

                _isChanged |= (_clientopinionsignature != value); _clientopinionsignature = value;
            }
        }

        /// <summary>
        /// 是否删除:0为可用,1为删除
        /// 本删除为假删除,删除后,在页面将不可见,但是数据仍然存在
        /// </summary>		
        public int IsDelete
        {
            get { return _isdelete; }
            set
            {
                _isChanged |= (_isdelete != value); _isdelete = value;
            }
        }

        /// <summary>
        /// 创建用户id
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
        /// 专线巡检登记创建时间
        /// </summary>		
        public DateTime? CreationTime
        {
            get { return _creationtime; }
            set
            {
                _isChanged |= (_creationtime != value); _creationtime = value;
            }
        }

        /// <summary>
        /// 专线巡检登记删除时间
        /// </summary>		
        public DateTime? DeleteTime
        {
            get { return _deletetime; }
            set
            {
                _isChanged |= (_deletetime != value); _deletetime = value;
            }
        }

        /// <summary>
        /// 维护人员
        /// </summary>		
        public string Personnel
        {
            get { return _personnel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Personnel", value, value.ToString());

                _isChanged |= (_personnel != value); _personnel = value;
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
