using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Warehouse.RepertotyManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class WarehouseRepertoty
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _companyid;
        private string _districtid;
        private string _address;
        private string _tel;
        private string _muserid;
        private string _remark;
        private string _repertotyno;
        private string _repertotyname;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public WarehouseRepertoty()
        {
            _id = String.Empty;
            //_filedata=;
            _companyid = String.Empty;
            _districtid = String.Empty;
            _address = String.Empty;
            _tel = String.Empty;
            _remark = String.Empty;
            _muserid = String.Empty;
            _repertotyno = String.Empty;
            _repertotyname = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public WarehouseRepertoty(string id, string companyid, string districtid, string address,
            string tel, string remark, string muserid, string repertotyno, string repertotyname)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _companyid = companyid;
            _districtid = districtid;
            _address = address;
            _tel = tel;
            _remark = remark;
            _muserid = muserid;
            _repertotyno = repertotyno;
            _repertotyname = repertotyname;
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
        /// 维护单位
        /// </summary>		
        public string CompanyId
        {
            get { return _companyid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CompanyId", value, value.ToString());

                _isChanged |= (_companyid != value); _companyid = value;
            }
        }
        /// <summary>
        /// 所属区域
        /// </summary>		
        public string DistrictId
        {
            get { return _districtid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictId", value, value.ToString());

                _isChanged |= (_districtid != value); _districtid = value;
            }
        }
        /// <summary>
        /// 仓库地址
        /// </summary>		
        public string Address
        {
            get { return _address; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DistrictId", value, value.ToString());

                _isChanged |= (_address != value); _address = value;
            }
        }

        /// <summary>
        /// 联系电话
        /// </summary>		
        public string Tel
        {
            get { return _tel; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Tel", value, value.ToString());

                _isChanged |= (_tel != value); _tel = value;
            }
        }
        /// <summary>
        /// 仓库管理员；用户1|用户2
        /// </summary>		
        public string MUserId
        {
            get { return _muserid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MUserId", value, value.ToString());

                _isChanged |= (_muserid != value); _muserid = value;
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
        /// 仓库编号
        /// </summary>		
        public string RepertotyNo
        {
            get { return _repertotyno; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RepertotyNo", value, value.ToString());

                _isChanged |= (_repertotyno != value); _repertotyno = value;
            }
        }
        /// <summary>
        /// 仓库名称
        /// </summary>		
        public string RepertotyName
        {
            get { return _repertotyname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for RepertotyName", value, value.ToString());

                _isChanged |= (_repertotyname != value); _repertotyname = value;
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
