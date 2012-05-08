using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.OtherInfoManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class ResourceOtherInfo
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
       // private Byte[] _filedata;
        private string _infofilename;
        private string _infofiletype;
        private int _infofilesize;
        private DateTime _localdatetime;
        private string _lineid;
        private string _loadfilename;
        private short _delflag;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public ResourceOtherInfo()
        {
            _id = String.Empty;
            //_filedata=;
            _infofilename = String.Empty;
            _infofiletype = String.Empty;
            _infofilesize = 0;
            _localdatetime = DateTime.Now;
            _lineid = String.Empty;
            _loadfilename = String.Empty;
            _delflag = 0;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public ResourceOtherInfo(string id, string infofilename, string infofiletype, int infofilesize, DateTime localdatetime,
            string lineid, string loadfilename, short delflag)
        {
            _id = id;
            //_filedata = filedata; Byte[] filedata,
            _infofilename = infofilename;
            _infofiletype = infofiletype;
            _infofilesize = infofilesize;
            _localdatetime = localdatetime;
            _lineid = lineid;
            _loadfilename = loadfilename;
            _delflag = delflag;
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
        /**
        /// <summary>
        /// 二进制文件
        /// </summary>		
        public Byte[] FileData
        {
            get { return _filedata; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for FileData", value, value.ToString());

                _isChanged |= (_filedata != value); _filedata = value;
            }
        }**/
        /// <summary>
        /// 文件名
        /// </summary>		
        public string InfoFileName
        {
            get { return _infofilename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for InfoFileName", value, value.ToString());

                _isChanged |= (_infofilename != value); _infofilename = value;
            }
        }
        /// <summary>
        /// 文件类型
        /// </summary>		
        public string InfoFileType
        {
            get { return _infofiletype; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for InfoFileType", value, value.ToString());

                _isChanged |= (_infofiletype != value); _infofiletype = value;
            }
        }
        /// <summary>
        /// 文件大小
        /// </summary>		
        public int InfoFileSize
        {
            get { return _infofilesize; }
            set
            {
                _isChanged |= (_infofilesize != value); _infofilesize = value;
            }
        }

        /// <summary>
        /// 文件类型
        /// </summary>		
        public string LoadFileName
        {
            get { return _loadfilename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LoadFileName", value, value.ToString());

                _isChanged |= (_loadfilename != value); _loadfilename = value;
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
        /// 专线ID
        /// </summary>		
        public string LineId
        {
            get { return _lineid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for LineId", value, value.ToString());

                _isChanged |= (_lineid != value); _lineid = value;
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
