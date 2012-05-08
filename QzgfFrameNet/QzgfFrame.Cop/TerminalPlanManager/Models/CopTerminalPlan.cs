using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.TerminalPlanManager.Models
{
    /// <summary>
    /// 自助终端巡检计划
    /// </summary>
    [Serializable]
    public sealed class CopTerminalPlan
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
        /// 自助终端ID
        /// </summary>
        private string _selfhelpequipid;
        /// <summary>
        /// 自助终端巡检起始时间
        /// </summary>
        private string _startterminaltime;
        /// <summary>
        /// 自助终端下次巡检时间
        /// </summary>
        private string _nextterminaltime;
        /// <summary>
        /// 自助终端巡检周期ID
        /// </summary>
        private string _terminaltimeid;
        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        private string _terminaltime;
        /// <summary>
        /// 是否删除:0为可用,1为删除
        /// 本删除为假删除,删除后,在页面将不可见,但是数据仍然存在
        /// </summary>
        private int _isdelete;
        /// <summary>
        /// 自助终端计划创建时间
        /// </summary>
        private DateTime? _creationtime;
        /// <summary>
        /// 自助终端计划删除时间
        /// </summary>
        private DateTime? _deletetime;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public CopTerminalPlan()
        {
            _id = String.Empty;
            _selfhelpequipid = String.Empty;
            _startterminaltime = String.Empty;
            _nextterminaltime = String.Empty;
            _terminaltimeid = String.Empty;
            _terminaltime = String.Empty;
            _isdelete = 0;
            _creationtime = null;
            _deletetime = null;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public CopTerminalPlan(string id, string selfhelpequipid, string startterminaltime,
            string nextterminaltime, string terminaltimeid, string terminaltime, int isdelete, DateTime? creationtime, 
            DateTime? deletetime)
        {
            _id = id;
            _selfhelpequipid = selfhelpequipid;
            _startterminaltime = startterminaltime;
            _nextterminaltime = nextterminaltime;
            _terminaltimeid = terminaltimeid;
            _terminaltime = terminaltime;
            _isdelete = isdelete;
            _creationtime = creationtime;
            _deletetime = deletetime;
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
        /// 自助终端ID
        /// </summary>		
        public string SelfHelpEquipId
        {
            get { return _selfhelpequipid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for SelfHelpEquipId", value, value.ToString());

                _isChanged |= (_selfhelpequipid != value); _selfhelpequipid = value;
            }
        }

        /// <summary>
        /// 自助终端巡检起始时间
        /// </summary>		
        public string StartTerminalTime
        {
            get { return _startterminaltime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for StartTerminalTime", value, value.ToString());
                _isChanged |= (_startterminaltime != value); _startterminaltime = value;
            }
        }

        /// <summary>
        /// 自助终端下次巡检时间
        /// </summary>		
        public string NextTerminalTime
        {
            get { return _nextterminaltime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for NextTerminalTime", value, value.ToString());
                _isChanged |= (_nextterminaltime != value); _nextterminaltime = value;
            }
        }

        /// <summary>
        /// 自助终端巡检周期ID
        /// </summary>		
        public string TerminalTimeId
        {
            get { return _terminaltimeid; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TerminalTimeId", value, value.ToString());

                _isChanged |= (_terminaltimeid != value); _terminaltimeid = value;
            }
        }

        /// <summary>
        /// 自助终端巡检周期
        /// </summary>		
        public string TerminalTime
        {
            get { return _terminaltime; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for TerminalTime", value, value.ToString());

                _isChanged |= (_terminaltime != value); _terminaltime = value;
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
        /// 自助终端计划创建时间
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
        /// 自助终端计划删除时间
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
