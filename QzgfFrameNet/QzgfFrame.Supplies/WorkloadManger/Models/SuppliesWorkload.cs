using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.WorkloadManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesWorkload
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private short _workyear;
        private short _workmonth;
        private int _examination;
        private int _monthlyamount;
        private Decimal _monthlyremuneration;
        private int _yearamount;
        private Decimal _yearremuneration;
        private int _fixedamount;
        private Decimal _fixedyearamount;
        private Decimal _reissue;
        private string _reissuenote;
        private int _saleamount;
        private int _adslinstallations;
        private int _adslrelocation;
        private int _ftthinstallations;
        private int _ftthrelocation;
        private string _companyid;
        private string _cityid;
        private string _districtid;
        private string _saledepartmentid;
        private string _communitymanagerid;
        private string _nydate;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesWorkload()
        {
            _id = String.Empty;
            //_filedata=;
            _workyear = short.Parse(DateTime.Now.Year.ToString());
            _workmonth = short.Parse(DateTime.Now.Month.ToString());
            _examination = 0;
            _monthlyamount = 0;
            _monthlyremuneration = 0;
            _yearamount = 0;
            _yearremuneration = 0;
            _fixedamount = 0;
            _fixedyearamount = 0;
            _reissue = 0;
            _saleamount = 0;
            _adslinstallations = 0;
            _adslrelocation = 0;
            _ftthinstallations = 0;
            _ftthrelocation = 0;
            _companyid = String.Empty;
            _cityid = String.Empty;
            _districtid = String.Empty;
            _communitymanagerid = String.Empty;
            _reissuenote = String.Empty;
            _saledepartmentid = String.Empty;
            _nydate = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesWorkload(string id, short workyear, short workmonth, int examination,
            int monthlyamount, Decimal monthlyremuneration,int yearamount, Decimal yearremuneration,
            int fixedamount, Decimal fixedyearamount,Decimal reissue,int saleamount,int adslinstallations,
            int adslrelocation, int ftthinstallations, int ftthrelocation, string companyid, string cityid,
            string districtid, string communitymanagerid, string reissuenote, string saledepartmentid,string nydate)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _workyear = workyear;
            _workmonth = workmonth;
            _examination = examination;
            _monthlyamount = monthlyamount;
            _monthlyremuneration = monthlyremuneration;
            _yearamount = yearamount;
            _yearremuneration = yearremuneration;
            _fixedamount = fixedamount;
            _fixedyearamount = fixedyearamount;
            _reissue = reissue;
            _saleamount = saleamount;
            _adslinstallations = adslinstallations;
            _adslrelocation = adslrelocation;
            _ftthinstallations = ftthinstallations;
            _ftthrelocation = ftthrelocation;
            _companyid = companyid;
            _cityid = cityid;
            _districtid = districtid;
            _communitymanagerid = communitymanagerid;
            _reissuenote = reissuenote;
            _saledepartmentid = saledepartmentid;
            _nydate = nydate;
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
        /// 年
        /// </summary>		
        public short WorkYear
        {
            get { return _workyear; }
            set
            {
                _isChanged |= (_workyear != value); _workyear = value;
            }
        }
        /// <summary>
        ///月份
        /// </summary>		
        public short WorkMonth
        {
            get { return _workmonth; }
            set
            {
                _isChanged |= (_workmonth != value); _workmonth = value;
            }
        }
        /// <summary>
        /// 考核分
        /// </summary>		
        public int Examination
        {
            get { return _examination; }
            set
            {
                _isChanged |= (_examination != value); _examination = value;
            }
        }

        /// <summary>
        /// 宽带包月量
        /// </summary>		
        public int MonthlyAmount
        {
            get { return _monthlyamount; }
            set
            {
                _isChanged |= (_monthlyamount != value); _monthlyamount = value;
            }
        }
        /// <summary>
        /// 宽带包月酬金/部
        /// </summary>		
        public Decimal MonthlyRemuneration
        {
            get { return _monthlyremuneration; }
            set
            {
                _isChanged |= (_monthlyremuneration != value); _monthlyremuneration = value;
            }
        }
        /// <summary>
        /// 宽带包年量
        /// </summary>		
        public int YearAmount
        {
            get { return _yearamount; }
            set
            {
                _isChanged |= (_yearamount != value); _yearamount = value;
            }
        }
        /// <summary>
        /// 宽带包年酬金/部
        /// </summary>		
        public Decimal YearRemuneration
        {
            get { return _yearremuneration; }
            set
            {
                _isChanged |= (_yearremuneration != value); _yearremuneration = value;
            }
        }
        /// <summary>
        /// 固话量
        /// </summary>		
        public int FixedAmount
        {
            get { return _fixedamount; }
            set
            {
                _isChanged |= (_fixedamount != value); _fixedamount = value;
            }
        }
        /// <summary>
        /// 固话包年酬金/部
        /// </summary>		
        public Decimal FixedYearAmount
        {
            get { return _fixedyearamount; }
            set
            {
                _isChanged |= (_fixedyearamount != value); _fixedyearamount = value;
            }
        }
        /// <summary>
        /// 补发酬金
        /// </summary>		
        public Decimal Reissue
        {
            get { return _reissue; }
            set
            {
                _isChanged |= (_reissue != value); _reissue = value;
            }
        }
        /// <summary>
        /// 补发说明
        /// </summary>		
        public string ReissueNote
        {
            get { return _reissuenote; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for ReissueNote", value, value.ToString());

                _isChanged |= (_reissuenote != value); _reissuenote = value;
            }
        }
        /// <summary>
        /// ADSL装机量
        /// </summary>		
        public int ADSLInstallations
        {
            get { return _adslinstallations; }
            set
            {
                _isChanged |= (_adslinstallations != value); _adslinstallations = value;
            }
        }
        /// <summary>
        /// ADSL移机量
        /// </summary>		
        public int ADSLRelocation
        {
            get { return _adslrelocation; }
            set
            {
                _isChanged |= (_adslrelocation != value); _adslrelocation = value;
            }
        }
        /// <summary>
        /// FTTH装机量
        /// </summary>		
        public int FTTHInstallations
        {
            get { return _ftthinstallations; }
            set
            {
                _isChanged |= (_ftthinstallations != value); _ftthinstallations = value;
            }
        }
        /// <summary>
        /// FTTH移装机量
        /// </summary>		
        public int FTTHRelocation
        {
            get { return _ftthrelocation; }
            set
            {
                _isChanged |= (_ftthrelocation != value); _ftthrelocation = value;
            }
        }
        /// <summary>
        /// 营销量
        /// </summary>		
        public int SaleAmount
        {
            get { return _saleamount; }
            set
            {
                _isChanged |= (_saleamount != value); _saleamount = value;
            }
        }
        /// <summary>
        /// 所属公司
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
        /// 所属地市
        /// </summary>		
        public string CityId
        {
            get { return _cityid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CityId", value, value.ToString());

                _isChanged |= (_cityid != value); _cityid = value;
            }
        }
        /// <summary>
        /// 所属县区
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
        /// 社区经理
        /// </summary>		
        public string CommunityManagerId
        {
            get { return _communitymanagerid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityManagerId", value, value.ToString());

                _isChanged |= (_communitymanagerid != value); _communitymanagerid = value;
            }
        }
        /// <summary>
        /// 营业部/片区营销中心
        /// </summary>		
        public string SaleDepartmentId
        {
            get { return _saledepartmentid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SaleDepartmentId", value, value.ToString());

                _isChanged |= (_saledepartmentid != value); _saledepartmentid = value;
            }
        }
        /// <summary>
        /// 年月
        /// </summary>		
        public string NYDate
        {
            get { return _nydate; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NYDate", value, value.ToString());

                _isChanged |= (_nydate != value); _nydate = value;
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
