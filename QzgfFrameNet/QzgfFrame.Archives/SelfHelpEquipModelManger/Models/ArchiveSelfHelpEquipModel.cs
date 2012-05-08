using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.SelfHelpEquipModelManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ArchiveSelfHelpEquipModel
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _equipmodelname;
        private string _factoryid;
        private string _equiptypeid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveSelfHelpEquipModel()
        {
            _id = String.Empty;
            _equipmodelname = String.Empty;
            _factoryid = String.Empty;
            _equiptypeid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveSelfHelpEquipModel(string id, string equipmodelname, string factoryid, string equiptypeid)
        {
            _id = id;
            _equipmodelname = equipmodelname;
            _factoryid = factoryid;
            _equiptypeid = equiptypeid;
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
        /// 设备型号名称
        /// </summary>		
        public string ModelName
        {
            get { return _equipmodelname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ModelName", value, value.ToString());

                _isChanged |= (_equipmodelname != value); _equipmodelname = value;
            }
        }

        /// <summary>
        /// 所属厂家ID
        /// </summary>		
        public string SelfHelpFactoryId
        {
            get { return _factoryid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SelfHelpFactoryId", value, value.ToString());

                _isChanged |= (_factoryid != value); _factoryid = value;
            }
        }
         /// <summary>
        /// 所属设备类型ID
        /// </summary>		
        public string SelfHelpEquipTypeId
        {
            get { return _equiptypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SelfHelpEquipTypeId", value, value.ToString());

                _isChanged |= (_equiptypeid != value); _equiptypeid = value;
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
