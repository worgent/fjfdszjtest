using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.DutyManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ArchiveDuty
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _Dutyname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveDuty()
        {
            _id = String.Empty;
            _Dutyname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveDuty(string id, string Dutyname)
        {
            _id = id;
            _Dutyname = Dutyname;
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
        /// 职责名称
        /// </summary>		
        public string DutyName
        {
            get { return _Dutyname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DutyName", value, value.ToString());

                _isChanged |= (_Dutyname != value); _Dutyname = value;
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
