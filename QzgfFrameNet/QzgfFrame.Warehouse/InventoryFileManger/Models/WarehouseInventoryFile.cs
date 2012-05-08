using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.InventoryFileManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseInventoryFile
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _filename;
        private string _filetype;
        private int _filesize;
        private DateTime _localdatetime;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseInventoryFile()
        {
            _id = String.Empty;
            //_filedata=;
            _filename = String.Empty;
            _filetype = String.Empty;
            _filesize = 0;
            _localdatetime = DateTime.Now;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseInventoryFile(string id, string filename, string filetype, int filesize, DateTime localdatetime)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _filename = filename;
            _filetype = filetype;
            _filesize = filesize;
            _localdatetime = localdatetime;
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
        /// 文件名
        /// </summary>		
        public string LedgerFileName
        {
            get { return _filename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LedgerFileName", value, value.ToString());

                _isChanged |= (_filename != value); _filename = value;
            }
        }
        /// <summary>
        /// 文件类型
        /// </summary>		
        public string LedgerFileType
        {
            get { return _filetype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LedgerFileType", value, value.ToString());

                _isChanged |= (_filetype != value); _filetype = value;
            }
        }
        /// <summary>
        /// 文件大小
        /// </summary>		
        public int LedgerFileSize
        {
            get { return _filesize; }
            set
            {
                _isChanged |= (_filesize != value); _filesize = value;
            }
        }

        /// <summary>
        /// 创建时间
        /// </summary>		
        public DateTime LocalDatetime
        {
            get { return _localdatetime; }
            set
            {
                _isChanged |= (_localdatetime != value); _localdatetime = value;
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
