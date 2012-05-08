using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Supplies.CommunityInfoManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SuppliesCommunityInfo
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        // private Byte[] _filedata;
        private string _communityname;
        private string _communitycode;
        private string _communitytypeid;
        private string _communitytypename;
        private string _buildwayid;
        private string _accesswayid;
        private string _buildwayname;
        private string _accesswayname;
        private string _createuserid;
        private DateTime _createdate;
        private short _delflag;
        #endregion
        
        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SuppliesCommunityInfo()
        {
            _id = String.Empty;
            //_filedata=;
            _communityname = String.Empty;
            _communitycode = String.Empty;
            _communitytypeid = String.Empty;
            _buildwayname = String.Empty;
            _accesswayname = String.Empty;
            _communitytypename = String.Empty;
            _buildwayid = String.Empty;
            _accesswayid = String.Empty;

            _createuserid = String.Empty;
            _createdate = DateTime.Now;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SuppliesCommunityInfo(string id, string communitycode, string communityname, string accesswayid, string accesswayname
            , string buildwayid, string buildwayname, string communitytypeid,string communitytypename )
        {
            _id = id;
            _communitycode = communitycode;
            _communityname = communityname;
            _accesswayid = accesswayid;
            _accesswayname = accesswayname;
            _buildwayid = buildwayid;
            _buildwayname = buildwayname;
            _communitytypeid = communitytypeid;
            _communitytypename = communitytypename;
            //_filedata = filedata; Byte[] filedata,
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
        /// 小区名称
        /// </summary>		
        public string CommunityName
        {
            get { return _communityname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityName", value, value.ToString());


                _isChanged |= (_communityname != value); _communityname = value;
            }
        }

        /// <summary>
        /// 小区编码
        /// </summary>		
        public string CommunityCode
        {
            get { return _communitycode; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityCode", value, value.ToString());


                _isChanged |= (_communitycode != value); _communitycode = value;
            }
        }
        
        /// <summary>
        /// 小区类型
        /// </summary>		
        public string CommunityTypeId
        {
            get { return _communitytypeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityTypeId", value, value.ToString());

                _isChanged |= (_communitytypeid != value); _communitytypeid = value;
            }
        }
        
        /// <summary>
        /// 小区类型名称
        /// </summary>		
        public string CommunityTypeName
        {
            get { return _communitytypename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CommunityTypeName", value, value.ToString());

                _isChanged |= (_communitytypename != value); _communitytypename = value;
            }
        }
        /// <summary>
        /// 建设方式
        /// </summary>		
        public string BuildWayId
        {
            get { return _buildwayid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BuildWayId", value, value.ToString());

                _isChanged |= (_buildwayid != value); _buildwayid = value;
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
        ///操作用户
        /// </summary>		
        public string CreateUserId
        {
            get { return _createuserid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CreateUserId", value, value.ToString());

                _isChanged |= (_createuserid != value); _createuserid = value;
            }
        }
        /// <summary>
        ///操作时间
        /// </summary>		
        public DateTime CreateDate
        {
            get { return _createdate; }
            set
            {
                _isChanged |= (_createdate != value); _createdate = value;
            }
        }
        /// <summary>
        /// 建设方式Name
        /// </summary>		
        public string BuildWayName
        {
            get { return _buildwayname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for BuildWayName", value, value.ToString());


                _isChanged |= (_buildwayname != value); _buildwayname = value;
            }
        }

        /// <summary>
        /// 接入方式Name
        /// </summary>		
        public string AccessWayName
        {
            get { return _accesswayname; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for AccessWayName", value, value.ToString());


                _isChanged |= (_accesswayname != value); _accesswayname = value;
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
