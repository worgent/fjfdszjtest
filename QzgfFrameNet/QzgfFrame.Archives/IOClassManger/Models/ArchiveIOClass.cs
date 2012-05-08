using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.IOClassManger.Models
{
    /// <summary>
    /// 业务类型表
    /// </summary>
    [Serializable]
    public sealed class ArchiveIOClass
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _typename;
        private string _stylename;
        private string _classname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveIOClass()
        {
            _id = String.Empty;
            _typename = String.Empty;
            _stylename = String.Empty;
            _classname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveIOClass(string id, string typename, string stylename, string classname)
        {
            _id = id;
            _typename = typename;
            _stylename = stylename;
            _classname = classname;
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
        /// 类型:入库\出库\调拨\维修
        /// </summary>		
        public string TypeName
        {
            get { return _typename.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TypeName", value, value.ToString());

                _isChanged |= (_typename != value); _typename = value;
            }
        }
        /// <summary>
        /// 方式:全新设备入库\借用出库等
        /// </summary>		
        public string StyleName
        {
            get { return _stylename.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StyleName", value, value.ToString());

                _isChanged |= (_stylename != value); _stylename = value;
            }
        }

        /// <summary>
        /// 英文缩写
        /// </summary>		
        public string ClassName
        {
            get { return _classname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ClassName", value, value.ToString());

                _isChanged |= (_classname != value); _classname = value;
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
