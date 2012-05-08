using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.CollarSuppliesManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesCollar
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private int _num;
        private string _collarsuppliesid;
        private DateTime _collardate;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesCollar()
        {
            _id = String.Empty;
            //_filedata=;
            _num = 0;
            _collardate = DateTime.Now;
            _collarsuppliesid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCollar(string id, int num, DateTime collardate, string collarsuppliesid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _collarsuppliesid = collarsuppliesid;
            _collardate = collardate;
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
        /// 数量
        /// </summary>		
        public int Num
        {
            get { return _num; }
            set
            {
                _isChanged |= (_num != value); _num = value;
            }
        }
        /// <summary>
        ///领料时间
        /// </summary>		
        public DateTime CollarDate
        {
            get { return _collardate; }
            set
            {
                _isChanged |= (_collardate != value); _collardate = value;
            }
        }
        /// <summary>
        ///采购单ID
        /// </summary>		
        public string CollarSuppliesId
        {
            get { return _collarsuppliesid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CollarSuppliesId", value, value.ToString());

                _isChanged |= (_collarsuppliesid != value); _collarsuppliesid = value;
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
