using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.EquipFaultManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceEquipFault
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties                                           
        private DateTime? _noticedatetime;
        private DateTime? _reachdatetime;
        private DateTime? _solvedatetime;
        private string _handletime;
        private string _troubleshooter;
        private string _description;
        private string _handleprocess;
        private string _handleresult;
        private string _componentid;
        private short _isreplace;
        private string _replaceequipname;
        private string _breakdowntypeid;
        private string _remark;
        private string _selfhelpequipid;
        private string _clietel;
        private string _usenetname;
        private string _termiid;
        private string _districtid;
        private string _factoryid;
        private string _equipmodename;
        private string _companyid;
        private short _state;
        private short _delflag;
        private string _districtname;
        private string _factoryname;

        private string _createuserid;
        private DateTime _createdate;
        private string _usenetno;
        private string _companyname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceEquipFault()
        {
            _id = String.Empty;
            _noticedatetime = DateTime.Now;
            _reachdatetime = null;
            _solvedatetime = null;
            _handletime = String.Empty;
            _troubleshooter = String.Empty;
            _description = String.Empty;
            _handleprocess = String.Empty;
            _handleresult = String.Empty;
            _componentid = String.Empty;
            _isreplace = 0;
            _replaceequipname = String.Empty;
            _breakdowntypeid = String.Empty;
            _remark = String.Empty;
            _selfhelpequipid = String.Empty;
            _clietel = String.Empty;
            _usenetname = String.Empty;
            _termiid = String.Empty;
            _districtid = String.Empty;
            _factoryid = String.Empty;
            _equipmodename = String.Empty;
            _companyid = String.Empty;
            _state = 0;
            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
            _usenetno = String.Empty;
            _districtname = String.Empty;
            _factoryname = String.Empty;
            _companyname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceEquipFault(string id, DateTime noticedatetime, DateTime reachdatetime, DateTime solvedatetime, string handletime,
        string troubleshooter, string description, string handleprocess, string handleresult, string componentid, short isreplace,
         string breakdowntypeid, string remark, string selfhelpid,
         string clietel, string usenetno, string usenetname, string termiid, string districtid, string factoryid,
            string equipmodename, string companyid, short state, string districtname,string factoryname,string companyname)
        {
            _id = id;
            _noticedatetime = noticedatetime;
            _reachdatetime = reachdatetime;
            _solvedatetime = solvedatetime;
            _handletime = handletime;
            _troubleshooter = troubleshooter;
            _description = description;
            _handleprocess = handleprocess;
            _handleresult = handleresult;
            _componentid = componentid;
            _isreplace = isreplace;
            _breakdowntypeid = breakdowntypeid;
            _remark = remark;
            _selfhelpequipid = selfhelpid;
            _clietel = clietel;
            _usenetname = usenetname;
            _termiid = termiid;
            _districtid = districtid;
            _factoryid = factoryid;
            _equipmodename = equipmodename;
            _companyid = companyid;
            _state = state;
            _usenetno = usenetno;
            _districtname = districtname;
            _factoryname = factoryname;
            _companyname = companyname;
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
        /// 故障申告时间
        /// </summary>		
        public DateTime? NoticeDateTime
        {
            get { return _noticedatetime; }
            set
            {
                _isChanged |= (_noticedatetime != value); _noticedatetime = value;
            }
        }
        /// <summary>
        /// 人员到达时间
        /// </summary>		
        public DateTime? ReachDatetime
        {
            get { return _reachdatetime; }
            set
            {
                _isChanged |= (_reachdatetime != value); _reachdatetime = value;
            }
        }

        /// <summary>
        /// 故障解决时间
        /// </summary>		
        public DateTime? SolveDatetime
        {
            get { return _solvedatetime; }
            set
            {
                _isChanged |= (_solvedatetime != value); _solvedatetime = value;
            }
        }
        /// <summary>
        /// 故障解决时长(以分钟显示)
        /// </summary>		
        public string HandleTime
        {
            get { return _handletime; }
            set
            {
                _isChanged |= (_handletime != value); _handletime = value;
            }
        }
        /// <summary>
        /// 排障人员
        /// </summary>		
        public string TroubleShooter
        {
            get { return _troubleshooter; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TroubleShooter", value, value.ToString());

                _isChanged |= (_troubleshooter != value); _troubleshooter = value;
            }
        }
        /// <summary>
        /// 故障 现象
        /// </summary>		
        public string Description
        {
            get { return _description; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Description", value, value.ToString());

                _isChanged |= (_description != value); _description = value;
            }
        }
        /// <summary>
        /// 故障处理过程
        /// </summary>		
        public string HandleProcess
        {
            get { return _handleprocess; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for HandleProcess", value, value.ToString());

                _isChanged |= (_handleprocess != value); _handleprocess = value;
            }
        }
        /// <summary>
        /// 故障处理结果
        /// </summary>		
        public string HandleResult
        {
            get { return _handleresult; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for HandleResult", value, value.ToString());

                _isChanged |= (_handleresult != value); _handleresult = value;
            }
        }
        /// <summary>
        /// 是否更换配件
        /// </summary>		
        public short IsReplace
        {
            get { return _isreplace; }
            set
            {
                _isChanged |= (_isreplace != value); _isreplace = value;
            }
        }
        /// <summary>
        /// 更换配件名称
        /// </summary>		
        public string ReplaceEquipName
        {
            get { return _replaceequipname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for replaceEquipName", value, value.ToString());

                _isChanged |= (_replaceequipname != value); _replaceequipname = value;
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
        /// 故障大类型
        /// </summary>		
        public string BreakdownTypeId
        {
            get { return _breakdowntypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BreakdownTypeId", value, value.ToString());

                _isChanged |= (_breakdowntypeid != value); _breakdowntypeid = value;
            }
        }
        /// <summary>
        /// 故障部件ID
        /// </summary>		
        public string ComponentId
        {
            get { return _componentid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ComponentId", value, value.ToString());

                _isChanged |= (_componentid != value); _componentid = value;
            }
        }
        /// <summary>
        /// 设备ID
        /// </summary>		
        public string SelfHelpEquipId
        {
            get { return _selfhelpequipid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SelfHelpEquipId", value, value.ToString());

                _isChanged |= (_selfhelpequipid != value); _selfhelpequipid = value;
            }
        }
        /// <summary>
        /// 客户联系电话
        /// </summary>		
        public string ClieTel
        {
            get
            {
                if (_clietel != null)
                    return _clietel.Trim();
                else
                    return _clietel;
            }
            set
            {
                if (value != null)
                    if (value.Length > 15)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieTel", value, value.ToString());

                _isChanged |= (_clietel != value); _clietel = value;
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
        /// 设备厂家
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
        /// 设备型号名称
        /// </summary>		
        public string EquipModelName
        {
            get { return _equipmodename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipModelName", value, value.ToString());

                _isChanged |= (_equipmodename != value); _equipmodename = value;
            }
        }
        /// <summary>
        /// 县市
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
        /// 维护单位名称
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
        /// 维护单位
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
