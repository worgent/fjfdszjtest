using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.ClieEquipManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceClieEquip
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _occupyport;
        private string _occupyslot;
        private string _boardtype;
        private string _equipid;
        private string _clieid;
        private string _lineid;
        private string _clieno;
        private string _cliename;
        private short _delflag;
        private string _portTypeId;
        private string _portTypeName;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceClieEquip()
        {
            _id = String.Empty;
            _occupyport = String.Empty;
            _occupyslot = String.Empty;
            _boardtype = String.Empty;
            _equipid = String.Empty;
            _clieid = String.Empty;
            _lineid = String.Empty;
            _delflag = 0;
            _portTypeId = String.Empty;
            _portTypeName = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceClieEquip(string id,string occupyport, string occupyslot, string boardtype,
            string equipid, string clieid, string lineid, short delflag)
        {
            _id = id;
            _occupyport = occupyport;
            _occupyslot = occupyslot;
            _boardtype = boardtype;
            _equipid = equipid;
            _clieid = clieid;
            _lineid = lineid;
            _delflag = delflag;
        }
        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceClieEquip(string id, string occupyport, string equipid, string clieid)
        {
            _id = id;
            _occupyport = occupyport;
            _equipid = equipid;
            _clieid = clieid;
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
        /// 占用槽位
        /// </summary>		
        public string OccupySlot
        {
            get { return _occupyslot; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OccupySlot", value, value.ToString());

                _isChanged |= (_occupyslot != value); _occupyslot = value;
            }
        }
        /// <summary>
        /// 板卡类型
        /// </summary>		
        public string BoardType
        {
            get { return _boardtype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BoardType", value, value.ToString());

                _isChanged |= (_boardtype != value); _boardtype = value;
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
        /// 端口类型ID
        /// </summary>		
        public string PortTypeId
        {
            get { return _portTypeId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PortTypeId", value, value.ToString());

                _isChanged |= (_portTypeId != value); _portTypeId = value;
            }
        }

        /// <summary>
        /// 设备ID
        /// </summary>		
        public string EquipId
        {
            get { return _equipid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for EquipId", value, value.ToString());

                _isChanged |= (_equipid != value); _equipid = value;
            }
        }  
        /// <summary>
        /// 客户ID
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
        /// 专线ID
        /// </summary>		
        public string LineId
        {
            get { return _lineid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineId", value, value.ToString());

                _isChanged |= (_lineid != value); _lineid = value;
            }
        }
        /// <summary>
        /// 客户编号
        /// </summary>		
        public string ClieNo
        {
            get { return _clieno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieId", value, value.ToString());

                _isChanged |= (_clieno != value); _clieno = value;
            }
        }
        /// <summary>
        /// 客户名称
        /// </summary>		
        public string ClieName
        {
            get { return _cliename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClieId", value, value.ToString());

                _isChanged |= (_cliename != value); _cliename = value;
            }
        }
        /// <summary>
        /// 端口类型名称
        /// </summary>		
        public string PortTypeName
        {
            get { return _portTypeName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for PortTypeName", value, value.ToString());

                _isChanged |= (_portTypeName != value); _portTypeName = value;
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
