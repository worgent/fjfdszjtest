using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.DedicateLineManger.Models
{
    /// <summary>
    /// 专线表
    /// </summary>
    [Serializable]
    public sealed class ResourceDedicateLine
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // Properties     
        private string _contractbandwidth ;
        private string _actualbandwidth ;
        private string  _rate ;
        private string _ralaynumber ; 
        private string _signalmodel;
        private string _telnumber ; 
        private string _vlannumber ;
        private string _gateway ; 
        private string _ip ;
        private string  _subnetmask ;
        private string _citynetswitch;
        private string _citynetswitchport;
        private string _computroomtequip;
        private string _computroomtequipport;
        private string  _termiequip ; 
        private string _termiport;
        private string _addft ; 
        private string _addfe;
        private string _atequip ; 
        private string _atequipport ;
        private string _abizequip; 
        private string _abizequipport ;
        private string _zbizequip ;
        private string  _zbizequipport; 
        private string  _circuitnumber;
        private string  _computerroomport ; 
        private string  _stationname ;
        private string _stationtequipaport; 
        private string _abizcomputerroomname ;
        private string  _oltequipaport ; 
        private string _loid ; 
        private string  _ddft ;
        private string _ddfe;
        private string _zddft;
        private string _zddfe;
        private DateTime?  _startdatetime ; 
        private string  _otherinfo ;
        private short _isaccesslocalnet;
        private short _isaccessprovinet;
        private string  _remark ;
        private short  _state;
        private string  _groupnumberid; 
        private string  _coreid ;
        private string _networkingmodeid;
        private string _biztypeid ; 
        private string  _clieid ;
        private string _lineno;
        private string _bizassuranleveid;
        private string _productno;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        private string _districtid;
        private string _companyId;

        private string _zstationName;
        private string _zcomputRoomName;
        private string _ztequip;
        private string _ztequipPort;
        private string _accessProviNet;
        private string _accessLocalNet;
        private string _zclieid;
        private string _bizNote;
        private string _abizEquipId;
        private string _zbizEquipId;
        private string _znetworkingmodeid;
        private string _lineType;

        private string _updateuserid;
        private string _updateusername;
        private DateTime? _updatedate;
        #endregion
        
        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceDedicateLine()
        {
            _id = String.Empty;
            _contractbandwidth = String.Empty;
            _actualbandwidth = String.Empty;
            _rate = String.Empty;
            _ralaynumber = String.Empty; 
            _signalmodel = String.Empty;
            _telnumber = String.Empty; 
            _vlannumber = String.Empty;
            _gateway = String.Empty; 
            _ip = String.Empty;
            _subnetmask = String.Empty;
            _citynetswitch = String.Empty;
            _citynetswitchport = String.Empty;
            _computroomtequip = String.Empty;
            _computroomtequipport = String.Empty;
            _termiequip = String.Empty; 
            _termiport = String.Empty;
            _addft = String.Empty; 
            _zbizequipport = String.Empty; 
            _circuitnumber= String.Empty;
            _computerroomport = String.Empty; 
            _stationname = String.Empty;
            _stationtequipaport = String.Empty; 
            _abizcomputerroomname = String.Empty;
            _oltequipaport = String.Empty; 
            _loid = String.Empty; 
            _ddft = String.Empty; 
            _ddfe= String.Empty;
            _zddft = String.Empty;
            _zddfe = String.Empty;
            _startdatetime = null; 
            _otherinfo = String.Empty;
            _isaccesslocalnet =0;
            _isaccessprovinet = 0;
            _remark = String.Empty;
            _state = 0;
            _groupnumberid = String.Empty; 
            _coreid = String.Empty;
            _networkingmodeid = String.Empty;
            _biztypeid = String.Empty; 
            _clieid = String.Empty;
            _lineno = String.Empty;
            _abizequipport = String.Empty;
            _bizassuranleveid = String.Empty;
            _productno = String.Empty;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _districtid = String.Empty;
            _companyId = String.Empty;
            _delflag = 0;
            _zstationName = String.Empty;
            _zcomputRoomName = String.Empty;
            _ztequip = String.Empty;
            _ztequipPort = String.Empty;
            _accessProviNet = String.Empty;
            _accessLocalNet = String.Empty;
            _zclieid = String.Empty;
            _bizNote = String.Empty;
            _abizEquipId = String.Empty;
            _zbizEquipId = String.Empty;
            _znetworkingmodeid = String.Empty;
            _lineType = String.Empty;

            _updateuserid = String.Empty;
            _updateusername = String.Empty;
            _updatedate = null;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceDedicateLine(string id, string contractbandwidth, string actualbandwidth, string rate, string ralaynumber, string signalmodel,
            string telnumber, string vlannumber, string gateway, string ip, string subnetmask,
            string citynetswitch, string citynetswitchport, string computerroomtequip, string computerroomtport, string termiequip, string termiport, string addft, string addfe,
            string atequip, string atequipport, string abizequip, string abizequipport, string zbizequip, string zbizequipport,string circuitnumber,
            string computerroomport, string stationname, string stationequipaport, string abizcomputerroomname, string oltequipaport,
            string loid, string ddft, string ddfe, string zddft, string zddfe, DateTime startdatetime, string otherinfo, short isaccesslocalnet, short isaccessprovinet,
            string remark, short state, string groupnumberid, string coreid, string networkingmodeid,
            string biztypeid, string clieid, string lineno, string bizassuranleveid, string productno,
            string createuserid, DateTime createdate, short delflag, string districtid, string companyId)
        {
            _id = id;
            _contractbandwidth = contractbandwidth;
            _actualbandwidth = actualbandwidth;
            _rate = rate;
            _ralaynumber = ralaynumber; 
            _signalmodel = signalmodel;
            _telnumber = telnumber; 
            _vlannumber = vlannumber;
            _gateway = gateway; 
            _ip = ip;
            _subnetmask = subnetmask;
            _citynetswitch = citynetswitch;
            _citynetswitchport = citynetswitchport;
            _computroomtequip = computerroomtequip;
            _computroomtequipport = computerroomtport;
            _termiequip = termiequip; 
            _termiport = termiport;
            _addft = addft;
            _abizequipport = abizequipport;
            _abizequip = abizequip;
            _zbizequipport = zbizequipport; 
            _circuitnumber= circuitnumber;
            _computerroomport = computerroomport; 
            _stationname = stationname;
            _stationtequipaport = stationequipaport; 
            _abizcomputerroomname = abizcomputerroomname;
            _oltequipaport = oltequipaport; 
            _loid = loid; 
            _ddft = ddft; 
            _ddfe= ddfe;
            _zddft = zddft;
            _zddfe = zddfe;
            _startdatetime = startdatetime; 
            _otherinfo = otherinfo;
            _isaccesslocalnet = isaccesslocalnet; 
            _isaccessprovinet = isaccessprovinet;
            _remark = remark;
            _state = state;
            _groupnumberid = groupnumberid; 
            _coreid = coreid; 
            _networkingmodeid = networkingmodeid;
            _biztypeid = biztypeid; 
            _clieid = clieid;
            _lineno = lineno;
            _bizassuranleveid = bizassuranleveid;
            _productno = productno;
            _createuserid = createuserid;
            _createdate = createdate;
            _delflag = delflag;
            _districtid = districtid;
            _companyId = companyId;
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
        /// 合同带宽
        /// </summary>		
        public string ContractBandwidth
        {
            get { return _contractbandwidth; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ContractBandwidth", value, value.ToString());

                _isChanged |= (_contractbandwidth != value); _contractbandwidth = value;
            }
        }

        /// <summary>
        /// 实际带宽
        /// </summary>		
        public string ActualBandwidth
        {
            get { return _actualbandwidth; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ActualBandwidth", value, value.ToString());

                _isChanged |= (_actualbandwidth != value); _actualbandwidth = value;
            }
        }
        /// <summary>
        /// VLan号
        /// </summary>		
        public string VLANNumber
        {
            get { return _vlannumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for VLANNumber", value, value.ToString());

                _isChanged |= (_vlannumber != value); _vlannumber = value;
            }
        }

        /// <summary>
        /// 城市编码（0595）
        /// </summary>		
        public string GateWay
        {
            get { return _gateway; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GateWay", value, value.ToString());

                _isChanged |= (_gateway != value); _gateway = value;
            }
        }
         /// <summary>
        /// IP地址
        /// </summary>		
        public string IP
        {
            get { return _ip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for IP", value, value.ToString());

                _isChanged |= (_ip != value); _ip = value;
            }
        }

        /// <summary>
        /// 子网掩码
        /// </summary>		
        public string SubnetMask
        {
            get { return _subnetmask; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SubnetMask", value, value.ToString());

                _isChanged |= (_subnetmask != value); _subnetmask = value;
            }
        } 
        /// <summary>
        /// VOIP ,速率
        /// </summary>		
        public string Rate
        {
            get { return _rate; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Rate", value, value.ToString());

                _isChanged |= (_rate != value); _rate = value;
            }
        }

        /// <summary>
        /// VOIP 中继数量
        /// </summary>		
        public string RalayNumber
        {
            get { return _ralaynumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RalayNumber", value, value.ToString());

                _isChanged |= (_ralaynumber != value); _ralaynumber = value;
            }
        } 
        /// <summary>
        /// VOIP 信令方式
        /// </summary>		
        public string SignalModel
        {
            get { return _signalmodel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SignalModel", value, value.ToString());

                _isChanged |= (_signalmodel != value); _signalmodel = value;
            }
        }

        /// <summary>
        /// VOIP 电话号码
        /// </summary>		
        public string TelNumber
        {
            get { return _telnumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TelNumber", value, value.ToString());

                _isChanged |= (_telnumber != value); _telnumber = value;
            }
        }
        /// <summary>
        /// 城域网接入交换机
        /// </summary>		
        public string CityNetSwitch
        {
            get { return _citynetswitch; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityNetSwitch", value, value.ToString());

                _isChanged |= (_citynetswitch != value); _citynetswitch = value;
            }
        }

        /// <summary>
        /// 城域网接入交换机 端口
        /// </summary>		
        public string CityNetSwitchPort
        {
            get { return _citynetswitchport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityNetSwitchPort", value, value.ToString());

                _isChanged |= (_citynetswitchport != value); _citynetswitchport = value;
            }
        } 
        /// <summary>
        /// 城域网A端，机房传输设备
        /// </summary>		
        public string ComputRoomtTEquip
        {
            get { return _computroomtequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ComputRoomtTEquip", value, value.ToString());

                _isChanged |= (_computroomtequip != value); _computroomtequip = value;
            }
        }

        /// <summary>
        /// 城域网A端，机房传输设备 端口
        /// </summary>		
        public string ComputRoomtTEquipPort
        {
            get { return _computroomtequipport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ComputRoomtTEquipPort", value, value.ToString());

                _isChanged |= (_computroomtequipport != value); _computroomtequipport = value;
            }
        } 
        /// <summary>
        /// 基站端即Z端 基站名称
        /// </summary>		
        public string StationName
        {
            get { return _stationname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StationName", value, value.ToString());

                _isChanged |= (_stationname != value); _stationname = value;
            }
        }

        /// <summary>
        /// 基站端即Z端，基站传输设备及端口
        /// </summary>		
        public string StationTEquipAndPort
        {
            get { return _stationtequipaport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StationTEquipAndPort", value, value.ToString());

                _isChanged |= (_stationtequipaport != value); _stationtequipaport = value;
            }
        }
        /// <summary>
        /// 基站端即Z端,电路编号
        /// </summary>		
        public string CircuitNumber
        {
            get { return _circuitnumber; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CircuitNumber", value, value.ToString());

                _isChanged |= (_circuitnumber != value); _circuitnumber = value;
            }
        }

        /// <summary>
        /// VOIP基站端即Z端 ,DDF架传输侧位置
        /// </summary>		
        public string ZDDFT
        {
            get { return _zddft; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZDDFT", value, value.ToString());

                _isChanged |= (_zddft != value); _zddft = value;
            }
        }
        /// <summary>
        /// VOIP基站端即Z端 ,DDF架设备侧位置
        /// </summary>		
        public string ZDDFE
        {
            get { return _zddfe; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZDDFE", value, value.ToString());

                _isChanged |= (_zddfe != value); _zddfe = value;
            }
        }
        /// <summary>
        ///  VOIP ,A端传输,局端设备
        /// </summary>		
        public string TermiEquip
        {
            get { return _termiequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TermiEquip", value, value.ToString());

                _isChanged |= (_termiequip != value); _termiequip = value;
            }
        }
        /// <summary>
        /// VOIP ,A端传输,局端端口
        /// </summary>		
        public string TermiPort
        {
            get { return _termiport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TermiPort", value, value.ToString());

                _isChanged |= (_termiport != value); _termiport = value;
            }
        } 
        /// <summary>
        /// VOIP ,A端传输,DDF架传输侧位置
        /// </summary>		
        public string ADDFT
        {
            get { return _addft; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ADDFT", value, value.ToString());

                _isChanged |= (_addft != value); _addft = value;
            }
        }
        /// <summary>
        /// VOIP ,A端传输,DDF架设备侧位置
        /// </summary>		
        public string ADDFE
        {
            get { return _addfe; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ADDFE", value, value.ToString());

                _isChanged |= (_addfe != value); _addfe = value;
            }
        } 
        /// <summary>
        /// 广域网,Z端,业务设备
        /// </summary>		
        public string ZBizEquip
        {
            get { return _zbizequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZBizEquip", value, value.ToString());

                _isChanged |= (_zbizequip != value); _zbizequip = value;
            }
        }
        /// <summary>
        /// 广域网,Z端,业务设备
        /// </summary>		
        public string ZBizEquipPort
        {
            get { return _zbizequipport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZBizEquipPort", value, value.ToString());

                _isChanged |= (_zbizequipport != value); _zbizequipport = value;
            }
        }
        /// <summary>
        /// 广域网,A端,传输设备
        /// </summary>		
        public string ATEquip
        {
            get { return _atequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ATEquip", value, value.ToString());

                _isChanged |= (_atequip != value); _atequip = value;
            }
        }
        /// <summary>
        /// 广域网,A端,传输设备端口
        /// </summary>		
        public string ATEquipPort
        {
            get { return _atequipport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ATEquipPort", value, value.ToString());

                _isChanged |= (_atequipport != value); _atequipport = value;
            }
        } 
        /// <summary>
        /// 广域网,A端,业务设备
        /// </summary>		
        public string ABizEquip
        {
            get { return _abizequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ABizEquip", value, value.ToString());

                _isChanged |= (_abizequip != value); _abizequip = value;
            }
        }
        /// <summary>
        /// 广域网,A端,业务设备端口
        /// </summary>		
        public string ABizEquipPort
        {
            get { return _abizequipport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ABizEquipPort", value, value.ToString());

                _isChanged |= (_abizequipport != value); _abizequipport = value;
            }
        }
        /// <summary>
        /// 广域网,Z端,接入站点
        /// </summary>		
        public string ZStationName
        {
            get { return _zstationName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZStationName", value, value.ToString());

                _isChanged |= (_zstationName != value); _zstationName = value;
            }
        }
        /// <summary>
        /// 广域网,Z端,接入机房
        /// </summary>		
        public string ZComputRoomName
        {
            get { return _zcomputRoomName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZComputRoomName", value, value.ToString());

                _isChanged |= (_zcomputRoomName != value); _zcomputRoomName = value;
            }
        }
        /// <summary>
        /// 广域网,Z端,传输设备
        /// </summary>		
        public string ZTEquip
        {
            get { return _ztequip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZTEquip", value, value.ToString());

                _isChanged |= (_ztequip != value); _ztequip = value;
            }
        }
        /// <summary>
        /// 广域网,Z端,传输设备端口
        /// </summary>		
        public string ZTEquipPort
        {
            get { return _ztequipPort; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZTEquipPort", value, value.ToString());

                _isChanged |= (_ztequipPort != value); _ztequipPort = value;
            }
        }
        /// <summary>
        /// 广域网,接入省网管平台名称
        /// </summary>		
        public string AccessProviNet
        {
            get { return _accessProviNet; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessProviNet", value, value.ToString());

                _isChanged |= (_accessProviNet != value); _accessProviNet = value;
            }
        }
        /// <summary>
        /// 广域网,接入本地网管平台名称
        /// </summary>		
        public string AccessLocalNet
        {
            get { return _accessLocalNet; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessLocalNet", value, value.ToString());

                _isChanged |= (_accessLocalNet != value); _accessLocalNet = value;
            }
        }
        /// <summary>
        ///全业务机房名称
        /// </summary>		
        public string BizComputRoomName
        {
            get { return _abizcomputerroomname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BizComputRoomName", value, value.ToString());
                _isChanged |= (_abizcomputerroomname != value); _abizcomputerroomname = value;
            }
        }
        /// <summary>
        ///OLT设备及端口
        /// </summary>		
        public string OLTEquipAPort
        {
            get { return _oltequipaport; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OLTEquipAPort", value, value.ToString());
                _isChanged |= (_oltequipaport != value); _oltequipaport = value;
            }
        }
        /// <summary>
        ///LOID
        /// </summary>		
        public string LOID
        {
            get { return _loid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OLTEquipAPort", value, value.ToString());
                _isChanged |= (_loid != value); _loid = value;
            }
        }

        /// <summary>
        ///DDF架传输侧位置
        /// </summary>		
        public string DDFT
        {
            get { return _ddft; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DDFT", value, value.ToString());
                _isChanged |= (_ddft != value); _ddft = value;
            }
        }
        /// <summary>
        ///DDF架设备侧位置
        /// </summary>		
        public string DDFE
        {
            get { return _ddfe; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DDFE", value, value.ToString());
                _isChanged |= (_ddfe != value); _ddfe = value;
            }
        }
        /// <summary>
        ///接入时间
        /// </summary>		
        public DateTime? StartDateTime
        {
            get { return _startdatetime; }
            set
            {
                _isChanged |= (_startdatetime != value); _startdatetime = value;
            }
        }
        
        /// <summary>
        /// 是否接入本地网管监控
        /// </summary>		
        public short IsAccessLocalNet
        {
            get { return _isaccesslocalnet; }
            set
            {
                _isChanged |= (_isaccesslocalnet != value); _isaccesslocalnet = value;
            }
        }
        /// <summary>
        /// 是否接入省网管监控
        /// </summary>		
        public short IsAccessProviNet
        {
            get { return _isaccessprovinet; }
            set
            {
                _isChanged |= (_isaccessprovinet != value); _isaccessprovinet = value;
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
        /// 其它资料表ID
        /// </summary>		
        public string OtherInfoID
        {
            get { return _otherinfo; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OtherInfoID", value, value.ToString());

                _isChanged |= (_otherinfo != value); _otherinfo = value;
            }
        }

        /// <summary>
        /// 具有号码群
        /// </summary>		
        public string GroupNumberId
        {
            get { return _groupnumberid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for GroupNumberId", value, value.ToString());

                _isChanged |= (_groupnumberid != value); _groupnumberid = value;
            }
        }
        /// <summary>
        /// 业务保障等级ID
        /// </summary>		
        public string BizAssuranLeveId
        {
            get { return _bizassuranleveid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BizAssuranLeveId", value, value.ToString());

                _isChanged |= (_bizassuranleveid != value); _bizassuranleveid = value;
            }
        } 
        /// <summary>
        /// 具有纤芯ID
        /// </summary>		
        public string CoreId
        {
            get { return _coreid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CoreId", value, value.ToString());

                _isChanged |= (_coreid != value); _coreid = value;
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
        /// Z端组网方式ID
        /// </summary>		
        public string ZNetWorkingModeId
        {
            get { return _znetworkingmodeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZNetWorkingModeId", value, value.ToString());

                _isChanged |= (_znetworkingmodeid != value); _znetworkingmodeid = value;
            }
        } 
        
        /// <summary>
        /// 专线类型
        /// </summary>		
        public string LineType
        {
            get { return _lineType; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineType", value, value.ToString());

                _isChanged |= (_lineType != value); _lineType = value;
            }
        } 
        /// <summary>
        /// 业务类型ID
        /// </summary>		
        public string BizTypeId
        {
            get { return _biztypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BizTypeId", value, value.ToString());

                _isChanged |= (_biztypeid != value); _biztypeid = value;
            }
        }
        
        /// <summary>
        /// 集团客户ID
        /// </summary>		
        public string ClieId
        {
            get { return _clieid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieId", value, value.ToString());

                _isChanged |= (_clieid != value); _clieid = value;
            }
        }
        /// <summary>
        /// Z端集团客户ID
        /// </summary>		
        public string ZClieId
        {
            get { return _zclieid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZClieId", value, value.ToString());

                _isChanged |= (_zclieid != value); _zclieid = value;
            }
        }
        /// <summary>
        /// 专线工单号
        /// </summary>		
        public string LineNo
        {
            get { return _lineno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineNo", value, value.ToString());

                _isChanged |= (_lineno != value); _lineno = value;
            }
        }
        /// <summary>
        /// 产品编号(为导入修改时用)
        /// </summary>		
        public string ProductNo
        {
            get { return _productno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ProductNo", value, value.ToString());

                _isChanged |= (_productno != value); _productno = value;
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
        /// A端关联设备ID
        /// </summary>		
        public string ABizEquipId
        {
            get { return _abizEquipId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ABizEquipId", value, value.ToString());

                _isChanged |= (_abizEquipId != value); _abizEquipId = value;
            }
        }
        /// <summary>
        /// Z端关系设备ID
        /// </summary>		
        public string ZBizEquipId
        {
            get { return _zbizEquipId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ZBizEquipId", value, value.ToString());

                _isChanged |= (_zbizEquipId != value); _zbizEquipId = value;
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
        ///业务描述
        /// </summary>		
        public string BizNote
        {
            get { return _bizNote; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BizNote", value, value.ToString());

                _isChanged |= (_bizNote != value); _bizNote = value;
            }
        }
        /// <summary>
        ///添加时间
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
        ///修改时间
        /// </summary>		
        public DateTime? UpdateDate
        {
            get { return _updatedate; }
            set
            {
                _isChanged |= (_updatedate != value); _updatedate = value;
            }
        }
        /// <summary>
        ///修改用户
        /// </summary>		
        public string UpdateUserId
        {
            get { return _updateuserid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UpdateUserId", value, value.ToString());

                _isChanged |= (_updateuserid != value); _updateuserid = value;
            }
        }
        /// <summary>
        ///修改用户名称
        /// </summary>		
        public string UpdateUserName
        {
            get { return _updateusername; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UpdateUserName", value, value.ToString());

                _isChanged |= (_updateusername != value); _updateusername = value;
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
