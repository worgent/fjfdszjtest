using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.EquipComponentManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceEquipComponent
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _componentmodel;
        private string _componentid;
        private string _selfhelpequipid;
        private string _componentname;
        private short _delflag;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceEquipComponent()
        {
            _id = String.Empty;
            _componentmodel = String.Empty;
            _componentid = String.Empty;
            _selfhelpequipid = String.Empty;
            _componentname = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceEquipComponent(string id, string componentmodel, string componentid,
            string selfhelpequipid, string componentname, short delflag)
        {
            _id = id;
            _componentmodel = componentmodel;
            _componentid = componentid;
            _selfhelpequipid = selfhelpequipid;
            _componentname = componentname;
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
        /// 部件型号
        /// </summary>		
        public string ComponentModel
        {
            get { return _componentmodel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ComponentModel", value, value.ToString());

                _isChanged |= (_componentmodel != value); _componentmodel = value;
            }
        }
        /// <summary>
        /// 部件ID
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
        /// 部件名称
        /// </summary>		
        public string ComponentName
        {
            get { return _componentname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ComponentName", value, value.ToString());

                _isChanged |= (_componentname != value); _componentname = value;
            }
        }
        /// <summary>
        /// 自助设备ID
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
