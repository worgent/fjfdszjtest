using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.LineEquipManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceLineEquip
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _lineid;
        private string _equipid;
        private short _delflag;
        private string _clieid;
        private string _occupyPort;
        private string _equipname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceLineEquip()
        {
            _id = String.Empty;
            _lineid = String.Empty;
            _equipid = String.Empty;
            _clieid = String.Empty;
            _occupyPort = String.Empty;
            _equipname = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceLineEquip(string id,string equipid,string clieid,string equipname)
        {
            _equipid = equipid;
            _clieid = clieid;
            _equipname = equipname;
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
        /// 专线ID号
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
        /// 客户名称ID
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
        /// 占用端口
        /// </summary>		
        public string OccupyPort
        {
            get { return _occupyPort; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for OccupyPort", value, value.ToString());

                _isChanged |= (_occupyPort != value); _occupyPort = value;
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

                _isChanged |= (_equipname != value); _equipname = value;
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
