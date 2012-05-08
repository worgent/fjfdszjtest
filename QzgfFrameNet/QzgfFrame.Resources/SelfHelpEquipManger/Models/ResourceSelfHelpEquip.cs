using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.SelfHelpEquipManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceSelfHelpEquip
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _termiid;
        private string _usenetname;
        private string _netaddress;
        private DateTime? _startdatetime;
        private string _repairmonth;
        private string _nettype;
        private string _ip;
        private string _subnetmask;
        private string _macaddress; 
        private short _isoverinsuran;
        private string _equipmodelName;
        private string _factoryId;
        private string _equiptypeId;
        private string _districtId;
        private string _remark;
        private DateTime? _buydatetime;
        private int _life;
        private short _state;
        private int _faultnum;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        private string _usenetno;
        private string _districtname;
        private string _factoryname;

  
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceSelfHelpEquip()
        {
            _id = String.Empty;
            _termiid = String.Empty;
            _usenetname = String.Empty;
            _netaddress = String.Empty;
            _startdatetime = null;
            _repairmonth = String.Empty;
            _nettype = String.Empty;
            _ip = String.Empty;
            _subnetmask = String.Empty;
            _macaddress = String.Empty;
            _isoverinsuran = 0;
            _equipmodelName = String.Empty;
            _factoryId = String.Empty;
            _equiptypeId = String.Empty;
            _districtId = String.Empty;
            _remark = String.Empty;
            _buydatetime = null;
            _life = 3;
            _state = 0;
            _faultnum = 0;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
            _usenetno = String.Empty;
            _districtname = String.Empty;
            _factoryname = String.Empty;    
        }
        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceSelfHelpEquip(string id, string termiid, string usenetname, string netaddress, DateTime startdatetime, string repairmonth,
        string nettype, string ip, string subnetmask, string macaddress, short isoverinsuran, string equipmodelId,
         string factoryId, string equiptypeId, string districtId, string remark, DateTime buydatetime, int life, short state, int faultnum
            , string createuserid, DateTime createdate, short delflag, string usenetno)
        {
            _id = id;
            _termiid = termiid;
            _usenetname = usenetname;
            _netaddress = netaddress;
            _startdatetime = startdatetime;
            _repairmonth = repairmonth;
            _nettype = nettype;
            _ip = ip;
            _subnetmask = subnetmask;
            _macaddress = macaddress;
            _isoverinsuran = isoverinsuran;
            _equipmodelName = equipmodelId;
            _factoryId = factoryId;
            _equiptypeId = equiptypeId;
            _districtId = districtId;
            _remark = remark;
            _buydatetime = buydatetime;
            _life = life;
            _state = state;
            _faultnum = faultnum;
            _createuserid = createuserid;
            _createdate = createdate;
            _delflag = delflag;
            _usenetno = usenetno;
        }
        /// <summary>
        ///获取终端设备
        /// </summary>
        public ResourceSelfHelpEquip(string id, string termiid, string districtId, string districtName,
        string useNetNo, string useNetName, string factoryId, string factoryName, string equipModelName)
        {
            _id = id;
            _termiid = termiid;
            _districtId = districtId;
            _districtname = districtName;
            _usenetno = useNetNo;
            _usenetname = useNetName;
            _factoryId = factoryId;
            _factoryname = factoryName;
            _equipmodelName = equipModelName;
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
        /// 终端ID
        /// </summary>		
        public string TermiId
        {
            get
            {
                if (_termiid != null)
                    return _termiid.Trim();
                else
                    return null;
            }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TermiId", value, value.ToString());

                _isChanged |= (_termiid != value); _termiid = value;
            }
        }
  
        /// <summary>
        /// 使用网点名称
        /// </summary>		
        public string UseNetName
        {
            get { return _usenetname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UseNetName", value, value.ToString());

                _isChanged |= (_usenetname != value); _usenetname = value;
            }
        }
        /// <summary>
        /// 使用网点编号
        /// </summary>		
        public string UseNetNo
        {
            get { return _usenetno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UseNetNo", value, value.ToString());

                _isChanged |= (_usenetno != value); _usenetno = value;
            }
        }
        /// <summary>
        /// 使用网点地址
        /// </summary>		
        public string NetAddress
        {
            get { return _netaddress; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NetAddress", value, value.ToString());

                 _isChanged |= (_netaddress != value); _netaddress = value;
            }
        }

        /// <summary>
        /// 网点类型
        /// </summary>		
        public string NetType
        {
            get { return _nettype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NetType", value, value.ToString());

                _isChanged |= (_nettype != value); _nettype = value;
            }
        }  
        /// <summary>
        /// 设备安装时间
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
        /// 设备采购时间
        /// </summary>		
        public DateTime? BuyDatetime
        {
            get { return _buydatetime; }
            set
            {
                _isChanged |= (_buydatetime != value); _buydatetime = value;
            }
        } 
        /// <summary>
        /// 维修月份/个
        /// </summary>		
        public string RepairMonth
        {
            get { return _repairmonth; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RepairMonth", value, value.ToString());

                _isChanged |= (_repairmonth != value); _repairmonth = value;
            }
        } 
         /// <summary>
        /// 终端IP地址
        /// </summary>		
        public string TerimIP
        {
            get { return _ip; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TerimIP", value, value.ToString());

                _isChanged |= (_ip != value); _ip = value;
            }
        }  
         /// <summary>
        /// 掩码
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
        /// 终端MAC地址
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
        /// 是否过保
        /// </summary>		
        public short IsOverInsuran
        {
            get { return _isoverinsuran; }
            set
            {
               _isChanged |= (_isoverinsuran != value); _isoverinsuran = value;
            }
        }
        /// <summary>
        /// 年限
        /// </summary>		
        public int Life
        {
            get { return _life; }
            set
            {
                _isChanged |= (_life != value); _life = value;
            }
        }
        /// <summary>
        /// 故障次数
        /// </summary>		
        public int FaultNum
        {
            get { return _faultnum; }
            set
            {
                _isChanged |= (_faultnum != value); _faultnum = value;
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
        /// 设备厂家
        /// </summary>		
        public string FactoryId
        {
            get { return _factoryId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FactoryId", value, value.ToString());

                _isChanged |= (_factoryId != value); _factoryId = value;
            }
        } 
         /// <summary>
        /// 设备类型
        /// </summary>		
        public string EquipTypeId
        {
            get { return _equiptypeId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipTypeId", value, value.ToString());

                _isChanged |= (_equiptypeId != value); _equiptypeId = value;
            }
        }
        /// <summary>
        /// 设备型号名称
        /// </summary>		
        public string EquipModelName
        {
            get { return _equipmodelName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipModelName", value, value.ToString());

                _isChanged |= (_equipmodelName != value); _equipmodelName = value;
            }
        } 
         /// <summary>
        /// 县市
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
        /// 县市名称
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
        /// 所属厂家名称
        /// </summary>		
        public string FactoryName
        {
            get { return _factoryname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FactoryName", value, value.ToString());

                _isChanged |= (_factoryname != value); _factoryname = value;
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
