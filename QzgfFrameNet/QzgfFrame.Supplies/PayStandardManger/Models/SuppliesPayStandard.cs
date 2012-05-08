using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.PayStandardManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesPayStandard
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private DateTime _startdate;
        private DateTime _enddate;
        private Decimal _pay;
        private string _accesswayid;
        private string _maintaintypeid;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesPayStandard()
        {
            _id = String.Empty;
            //_filedata=;
            _startdate = DateTime.Now;
            _enddate = DateTime.Now;
            _pay = 0;
            _accesswayid = String.Empty;
            _maintaintypeid = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesPayStandard(string id, DateTime startdate, DateTime enddate, Decimal pay,
            string accesswayid, string maintaintypeid)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _startdate = startdate;
            _enddate = enddate;
            _pay = pay;
            _accesswayid = accesswayid;
            _maintaintypeid = maintaintypeid;
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
        /// 起始时间
        /// </summary>		
        public DateTime StartDate
        {
            get { return _startdate; }
            set
            {
                _isChanged |= (_startdate != value); _startdate = value;
            }
        }
        /// <summary>
        /// 终止时间
        /// </summary>		
        public DateTime EndDate
        {
            get { return _enddate; }
            set
            {
                _isChanged |= (_enddate != value); _enddate = value;
            }
        }
        /// <summary>
        /// 薪酬标准
        /// </summary>		
        public Decimal Pay
        {
            get { return _pay; }
            set
            {
                _isChanged |= (_pay != value); _pay = value;
            }
        }

        /// <summary>
        /// 接入方式
        /// </summary>		
        public string AccessWayId
        {
            get { return _accesswayid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessWayId", value, value.ToString());


                _isChanged |= (_accesswayid != value); _accesswayid = value;
            }
        }
        /// <summary>
        /// 装维类型
        /// </summary>		
        public string MaintainTypeId
        {
            get { return _maintaintypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for MaintainTypeId", value, value.ToString());

                _isChanged |= (_maintaintypeid != value); _maintaintypeid = value;
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
