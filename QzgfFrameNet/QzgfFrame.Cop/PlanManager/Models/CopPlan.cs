using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.PlanManager.Models
{
    /// <summary>
    /// 专线巡检计划
    /// </summary>
    [Serializable]
    public sealed class CopPlan
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        /// <summary>
        /// id
        /// </summary>
        private string _id;
        /// <summary>
        /// 专线ID
        /// </summary>
        private string _dedicatelineid;
        /// <summary>
        /// 专线巡检起始时间
        /// </summary>
        private string _startcyctime;
        /// <summary>
        /// 专线下次巡检时间
        /// </summary>
        private string _nextcyctime;
        /// <summary>
        /// 专线巡检周期ID
        /// </summary>
        private string _cyctimeid;
        /// <summary>
        /// 专线巡检周期
        /// </summary>
        private string _cyctime;
        /// <summary>
        /// 是否删除:0为可用,1为删除
        /// 本删除为假删除,删除后,在页面将不可见,但是数据仍然存在
        /// </summary>
        private int _isdelete;

        /// <summary>
        /// 创建用户id
        /// </summary>
        private string _createuserid;
        /// <summary>
        /// 计划创建时间
        /// </summary>
        private DateTime? _creationtime;
        /// <summary>
        /// 计划删除时间
        /// </summary>
        private DateTime? _deletetime;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public CopPlan()
        {
            _id = String.Empty;
            _dedicatelineid = String.Empty;
            _startcyctime = String.Empty;
            _nextcyctime = String.Empty;
            _cyctimeid = String.Empty;
            _cyctime = String.Empty;
            _isdelete = 0;
            _createuserid = null;
            _creationtime = null;
            _deletetime = null;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public CopPlan(string id, string dedicatelineid, string startcyctime,
            string nextcyctime, string cyctimeid, string cyctime, int isdelete, DateTime? creationtime, DateTime? deletetime,
            string createuserid)
        {
            _id = id;
            _dedicatelineid = dedicatelineid;
            _startcyctime = startcyctime;
            _nextcyctime = nextcyctime;
            _cyctimeid = cyctimeid;
            _cyctime = cyctime;
            _isdelete = isdelete;
            _creationtime = creationtime;
            _deletetime = deletetime;
            _createuserid = createuserid;
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
        /// 专线ID
        /// </summary>		
        public string DedicateLineId
        {
            get { return _dedicatelineid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for DedicateLineId", value, value.ToString());

                _isChanged |= (_dedicatelineid != value); _dedicatelineid = value;
            }
        }

        /// <summary>
        /// 专线巡检起始时间
        /// </summary>		
        public string StartCycTime
        {
            get { return _startcyctime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StartCycTime", value, value.ToString());

                _isChanged |= (_startcyctime != value); _startcyctime = value;
            }
        }

        /// <summary>
        /// 专线下次巡检时间
        /// </summary>		
        public string NextCycTime
        {
            get { return _nextcyctime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NextCycTime", value, value.ToString());

                _isChanged |= (_nextcyctime != value); _nextcyctime = value;
            }
        }

        /// <summary>
        /// 专线巡检周期ID
        /// </summary>		
        public string CycTimeId
        {
            get { return _cyctimeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CycTimeId", value, value.ToString());

                _isChanged |= (_cyctimeid != value); _cyctimeid = value;
            }
        }
        /// <summary>
        /// 专线巡检周期
        /// </summary>		
        public string CycTime
        {
            get { return _cyctime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for CycTime", value, value.ToString());

                _isChanged |= (_cyctime != value); _cyctime = value;
            }
        }

        /// <summary>
        /// 是否删除:0为可用,1为删除
        /// 本删除为假删除,删除后,在页面将不可见,但是数据仍然存在
        /// </summary>		
        public int IsDelete
        {
            get { return _isdelete; }
            set
            {
                _isChanged |= (_isdelete != value); _isdelete = value;
            }
        }

        /// <summary>
        /// 创建用户id
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
        /// 计划创建时间
        /// </summary>		
        public DateTime? CreationTime
        {
            get { return _creationtime; }
            set
            {
                _isChanged |= (_creationtime != value); _creationtime = value;
            }
        }

        /// <summary>
        /// 计划删除时间
        /// </summary>		
        public DateTime? DeleteTime
        {
            get { return _deletetime; }
            set
            {
                _isChanged |= (_deletetime != value); _deletetime = value;
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
