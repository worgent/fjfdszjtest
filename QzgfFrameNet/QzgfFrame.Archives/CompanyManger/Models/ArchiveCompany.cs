using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.CompanyManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ArchiveCompany
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _companyname;
        private string _fullname;
        private short _ismaintain;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ArchiveCompany()
        {
            _id = String.Empty;
            _companyname = String.Empty;
            _fullname = String.Empty;
            _ismaintain = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ArchiveCompany(string id, string companyname,string fullname, short ismaintain)
        {
            _id = id;
            _companyname = companyname;
            _fullname = fullname;
            _ismaintain = ismaintain;
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
        /// 公司名称
        /// </summary>		
        public string CompanyName
        {
            get { return _companyname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyName", value, value.ToString());

                _isChanged |= (_companyname != value); _companyname = value;
            }
        }
        /// <summary>
        /// 公司名称全称
        /// </summary>		
        public string FullName
        {
            get { return _fullname.Trim(); }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FullName", value, value.ToString());

                _isChanged |= (_fullname != value); _fullname = value;
            }
        }
        /// <summary>
        /// 是否为维护单位
        /// </summary>		
        public short IsMaintain
        {
            get { return _ismaintain; }
            set
            {               
                _isChanged |= (_ismaintain != value); _ismaintain = value;
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
