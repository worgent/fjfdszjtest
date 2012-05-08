using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.CollarSuppliesDetailManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesCollarSuppliesDetail
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
        private string _unitname;
        private string _suppliesTypeId;
        private string _suppliesTypeName;
        private string _collarsuppliesid;
        private short _delflag;

        private string _remark;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesCollarSuppliesDetail()
        {
            _id = String.Empty;
            //_filedata=;
            _num = 0;
            _unitname = String.Empty;
            _suppliesTypeId = String.Empty;
            _suppliesTypeName = String.Empty;
            _collarsuppliesid = String.Empty;
            _remark = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCollarSuppliesDetail(string id, int num, string unitname, string suppliesTypeId,
            string suppliesTypeName, DateTime collardate, string collarsuppliesid, short delflag)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _unitname = unitname;
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypeName = suppliesTypeName;
            _collarsuppliesid = collarsuppliesid;
            _delflag = delflag;
        }

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCollarSuppliesDetail(string id, int num, string suppliesTypeId, string unitname, 
            string suppliesTypeName)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _num = num;
            _unitname = unitname;
            _suppliesTypeId = suppliesTypeId;
            _suppliesTypeName = suppliesTypeName;
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
        /// 单位
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
        /// 耗材类型ID
        /// </summary>		
        public string SuppliesTypeId
        {
            get { return _suppliesTypeId; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeId", value, value.ToString());


                _isChanged |= (_suppliesTypeId != value); _suppliesTypeId = value;
            }
        }

        /// <summary>
        /// 耗材类型名称
        /// </summary>		
        public string SuppliesTypeName
        {
            get { return _suppliesTypeName; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SuppliesTypeName", value, value.ToString());


                _isChanged |= (_suppliesTypeName != value); _suppliesTypeName = value;
            }
        }
        /// <summary>
        ///操作用户
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
        /// 备注
        /// </summary>		
        public string Remark
        {
            get { return _remark; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Remark", value, value.ToString());

                _isChanged |= (_remark != value); _remark = value;
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
