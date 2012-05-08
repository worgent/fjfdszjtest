using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.SuppliesTypeManger.Models
{
    /// <summary>
    /// 业务类型表
    /// </summary>
    [Serializable]
    public sealed class ArchiveSuppliesType
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _suppliestypename;
        private string _unitname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveSuppliesType()
        {
            _id = String.Empty;
            _suppliestypename = String.Empty;
            _unitname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveSuppliesType(string id, string biztypename, string unitname)
        {
            _id = id;
            _suppliestypename = biztypename;
            _unitname = unitname;
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
        /// 业务类型名称
        /// </summary>		
        public string SuppliesTypeName
        {
            get { return _suppliestypename.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeName", value, value.ToString());

                _isChanged |= (_suppliestypename != value); _suppliestypename = value;
            }
        }
        
        /// <summary>
        /// 单位名称
        /// </summary>		
        public string UnitName
        {
            get { return _unitname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for UnitName", value, value.ToString());

                _isChanged |= (_unitname != value); _unitname = value;
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
