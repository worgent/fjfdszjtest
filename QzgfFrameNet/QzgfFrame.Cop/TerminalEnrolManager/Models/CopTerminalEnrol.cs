using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.TerminalEnrolManager.Models
{
    /// <summary>
    /// 自助终端巡检登记周期
    /// </summary>
    [Serializable]
    public sealed class CopTerminalEnrol
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 巡检登记时间
        /// </summary>
        private DateTime? _terminalenroltime;
        /// <summary>
        /// 使用单位
        /// </summary>
        private string _unit;
        /// <summary>
        /// 联系人
        /// </summary>
        private string _contact;
        /// <summary>
        /// 巡检计划ID
        /// </summary>
        private string _terminalplanid;
        /// <summary>
        /// 联系电话
        /// </summary>
        private string _phone;
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
        /// 自助终端巡检登记创建时间
        /// </summary>
        private DateTime? _creationtime;
        /// <summary>
        /// 自助终端巡检登记删除时间
        /// </summary>
        private DateTime? _deletetime;

        #region 维护项目选择

        /// <summary>
        /// 运行状态调校(正常、不正常)
        /// </summary>
        private string _operatingstatus;
        /// <summary>
        /// 处理清洁（干净、不干净）
        /// </summary>
        private string _handlingclean;
        /// <summary>
        /// 纸仓内清洁（干净、不干净）
        /// </summary>
        private string _paperwarehouseclean;
        /// <summary>
        /// 热敏头片清洁（干净、不干净）
        /// </summary>
        private string _thermalheadfilmclean;

        #endregion 维护项目选择

        #region 维护项目选择

        /// <summary>
        /// 清单打印机
        /// </summary>
        private string _listofprinters;
        /// <summary>
        /// 发票打印机
        /// </summary>
        private string _invoiceprinter;
        /// <summary>
        /// 识币器
        /// </summary>
        private string _knowthecoin;
        /// <summary>
        /// UPS电源 
        /// </summary>
        private string _upspowersupply;
        /// <summary>
        /// 金属小键盘
        /// </summary>
        private string _metalkeypad;

        #endregion 维护项目选择

        /// <summary>
        /// 营业员培训与交流
        /// </summary>
        private string _trainingandexchange;
        /// <summary>
        /// 维护人员ID
        /// </summary>
        private string _personnelid;
        /// <summary>
        /// 维护人员
        /// </summary>
        private string _personnel;
        /// <summary>
        /// 完成保养时间
        /// </summary>
        private DateTime? _maintenancetime;
        /// <summary>
        /// 服务满意度：非常满意 满意 比较满意 服务一般 服务较差
        /// </summary>
        private string _servicesatisfaction;

        #endregion

        #region Default ( Empty ) Class Constructor 

        /// <summary>
        /// default constructor
        /// </summary>
        public CopTerminalEnrol()
        {
            _id = String.Empty;
            _terminalenroltime = DateTime.Now;
            _unit = String.Empty;
            _contact = String.Empty;
            _terminalplanid = String.Empty;
            _phone = String.Empty;
            _operatingstatus = String.Empty;
            _handlingclean = String.Empty;
            _paperwarehouseclean = String.Empty;
            _thermalheadfilmclean = String.Empty;
            _listofprinters = String.Empty;
            _invoiceprinter = String.Empty;
            _knowthecoin = String.Empty;
            _upspowersupply = String.Empty;
            _metalkeypad = String.Empty;
            _trainingandexchange = String.Empty;
            _personnelid = String.Empty;
            _maintenancetime = null;
            _servicesatisfaction = String.Empty;
            _isdelete = 0;
            _createuserid = String.Empty;
            _creationtime = null;
            _deletetime = null;
            _personnel = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public CopTerminalEnrol(string id, DateTime? terminalenroltime, string unit, string contact, string terminalplanid, 
            string phone, string operatingstatus, string handlingclean, string paperwarehouseclean, string thermalheadfilmclean,
            string listofprinters, string invoiceprinter, string knowthecoin, string upspowersupply, string metalkeypad,
            string trainingandexchange, string personnelid, DateTime? maintenancetime, string servicesatisfaction,int isdelete,
            DateTime? creationtime, DateTime? deletetime, string personnel, string createuserid)
        {
            _id = id;
            _terminalenroltime = terminalenroltime;
            _unit = unit;
            _contact = contact;
            _terminalplanid = terminalplanid;
            _phone = phone;
            _operatingstatus = operatingstatus;
            _handlingclean = handlingclean;
            _paperwarehouseclean = paperwarehouseclean;
            _thermalheadfilmclean = thermalheadfilmclean;
            _listofprinters = listofprinters;
            _invoiceprinter = invoiceprinter;
            _knowthecoin = knowthecoin;
            _upspowersupply = upspowersupply;
            _metalkeypad = metalkeypad;
            _trainingandexchange = trainingandexchange;
            _personnelid = personnelid;
            _maintenancetime = maintenancetime;
            _servicesatisfaction = servicesatisfaction;
            _isdelete = isdelete;
            _createuserid = createuserid;
            _creationtime = creationtime;
            _deletetime = deletetime;
            _personnel = personnel;
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
        /// 巡检登记时间
        /// </summary>		
        public DateTime? TerminalEnrolTime
        {
            get { return _terminalenroltime; }
            set
            {
                _isChanged |= (_terminalenroltime != value); _terminalenroltime = value;
            }
        }
        /// <summary>
        /// 使用单位
        /// </summary>		
        public string Unit
        {
            get { return _unit; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Unit", value, value.ToString());

                _isChanged |= (_unit != value); _unit = value;
            }
        }
        /// <summary>
        /// 联系人
        /// </summary>		
        public string Contact
        {
            get { return _contact; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Contact", value, value.ToString());

                _isChanged |= (_contact != value); _contact = value;
            }
        }
        /// <summary>
        /// 巡检计划ID
        /// </summary>		
        public string TerminalPlanId
        {
            get { return _terminalplanid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TerminalPlanId", value, value.ToString());

                _isChanged |= (_terminalplanid != value); _terminalplanid = value;
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
                        throw new ArgumentOutOfRangeException("Invalid value for ", value, value.ToString());

                _isChanged |= (_phone != value); _phone = value;
            }
        }

        #region 维护项目选择

        /// <summary>
        /// 运行状态调校(正常、不正常)
        /// </summary>		
        public string OperatingStatus
        {
            get { return _operatingstatus; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OperatingStatus", value, value.ToString());

                _isChanged |= (_operatingstatus != value); _operatingstatus = value;
            }
        }
        /// <summary>
        /// 处理清洁（干净、不干净）
        /// </summary>		
        public string HandlingClean
        {
            get { return _handlingclean; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for HandlingClean", value, value.ToString());

                _isChanged |= (_handlingclean != value); _handlingclean = value;
            }
        }
        /// <summary>
        /// 纸仓内清洁（干净、不干净）
        /// </summary>		
        public string PaperWarehouseClean
        {
            get { return _paperwarehouseclean; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PaperWarehouseClean", value, value.ToString());

                _isChanged |= (_paperwarehouseclean != value); _paperwarehouseclean = value;
            }
        }
        /// <summary>
        /// 热敏头片清洁（干净、不干净）
        /// </summary>		
        public string ThermalHeadFilmClean
        {
            get { return _thermalheadfilmclean; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ThermalHeadFilmClean", value, value.ToString());

                _isChanged |= (_thermalheadfilmclean != value); _thermalheadfilmclean = value;
            }
        }

        #endregion 维护项目选择

        #region 维护项目选择
        //易损零件检测与更换

        /// <summary>
        /// 清单打印机
        /// </summary>		
        public string ListOfPrinters
        {
            get { return _listofprinters; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ListOfPrinters", value, value.ToString());

                _isChanged |= (_listofprinters != value); _listofprinters = value;
            }
        }
        /// <summary>
        /// 发票打印机
        /// </summary>		
        public string InvoicePrinter
        {
            get { return _invoiceprinter; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for InvoicePrinter", value, value.ToString());

                _isChanged |= (_invoiceprinter != value); _invoiceprinter = value;
            }
        }
        /// <summary>
        /// 识币器
        /// </summary>		
        public string KnowTheCoin
        {
            get { return _knowthecoin; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for KnowTheCoin", value, value.ToString());

                _isChanged |= (_knowthecoin != value); _knowthecoin = value;
            }
        }        
        /// <summary>
        /// UPS电源
        /// </summary>		
        public string UPSPowerSupply
        {
            get { return _upspowersupply; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UPSPowerSupply", value, value.ToString());

                _isChanged |= (_upspowersupply != value); _upspowersupply = value;
            }
        }
        /// <summary>
        /// 金属小键盘
        /// </summary>		
        public string MetalKeypad
        {
            get { return _metalkeypad; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MetalKeypad", value, value.ToString());

                _isChanged |= (_metalkeypad != value); _metalkeypad = value;
            }
        }

        #endregion 维护项目选择

        /// <summary>
        /// 营业员培训与交流
        /// </summary>		
        public string TrainingAndExchange
        {
            get { return _trainingandexchange; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TrainingAndExchange", value, value.ToString());

                _isChanged |= (_trainingandexchange != value); _trainingandexchange = value;
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
                        throw new ArgumentOutOfRangeException("Invalid value for PersonnelId", value, value.ToString());

                _isChanged |= (_personnelid != value); _personnelid = value;
            }
        }
        /// <summary>
        /// 完成保养时间
        /// </summary>		
        public DateTime? MaintenanceTime
        {
            get { return _maintenancetime; }
            set
            {
                _isChanged |= (_maintenancetime != value); _maintenancetime = value;
            }
        }
        /// <summary>
        /// 服务满意度：非常满意 满意 比较满意 服务一般 服务较差
        /// </summary>		
        public string ServiceSatisfaction
        {
            get { return _servicesatisfaction; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ServiceSatisfaction", value, value.ToString());

                _isChanged |= (_servicesatisfaction != value); _servicesatisfaction = value;
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
        /// 自助终端巡检登记创建时间
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
        /// 自助终端巡检登记删除时间
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
