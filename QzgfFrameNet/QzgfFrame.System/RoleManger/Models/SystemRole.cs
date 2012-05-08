/*

comment

*/

using System;
using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace QzgfFrame.System.RoleManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SystemRole
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _rolename;
        private string _state;
        private string _statename;
        private string _createman;
        private DateTime _createdate;
        private string _remark;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SystemRole()
        {
            _id = String.Empty;
            _rolename = String.Empty;
            _remark = String.Empty;
            _state = String.Empty;
            _statename = String.Empty;
            _createman = String.Empty;
            _createdate = DateTime.MinValue;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SystemRole(string id, string rolename, string remark, string state, string createman, DateTime createdate)
        {
            _id = id;
            _rolename = rolename;
            _remark = remark;
            _state = state;
            _createman = createman;
            _createdate = createdate;
        }
        /// <summary>
        /// 列表专用
        /// </summary>
        /// <param name="id"></param>
        /// <param name="rolename"></param>
        /// <param name="remark"></param>
        /// <param name="state"></param>
        /// <param name="statename"></param>
        public SystemRole(string id, string rolename, string remark, string state, string statename)
        {
            _id = id;
            _rolename = rolename;
            _remark = remark;
            _state = state;
            _statename = statename;
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
        /// 角色名称
        /// </summary>		
        public string Rolename
        {
            get { return _rolename; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Rolename", value, value.ToString());

                _isChanged |= (_rolename != value); _rolename = value;
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
        /// 0:删除,1:正常,2停用
        /// </summary>		
        public string State
        {
            get { return _state; }
            set
            {
                if (value != null)
                    if (value.Length > 2)
                        throw new ArgumentOutOfRangeException("Invalid value for State", value, value.ToString());

                _isChanged |= (_state != value); _state = value;
            }
        }

        /// <summary>
        /// 0:删除,1:正常,2停用
        /// </summary>		
        public string Statename
        {
            get { return _statename; }
            set
            {

                _isChanged |= (_statename != value); _statename = value;
            }
        }

        /// <summary>
        /// 
        /// </summary>		
        public string Createman
        {
            get { return _createman; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Createman", value, value.ToString());

                _isChanged |= (_createman != value); _createman = value;
            }
        }

        /// <summary>
        /// 创建时间
        /// </summary>		
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd HH:mm}")]
        public DateTime Createdate
        {
            get { return _createdate; }
            set { _isChanged |= (_createdate != value); _createdate = value; }
        }

        ///// <summary>
        ///// 创建时间
        ///// </summary>		
        ///// 
        ////[JsonConverter(typeof(JavaScriptDateTimeConverter))]
        ////[JsonConverter(typeof(IsoDateTimeConverter))] 
        ////以下仅能与@Html.EditorFor结合使用相差12小时,不能与TextBoxFor
        //[DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd HH:mm}")] 
        ////Html.TextBox("name", "value", new { @class = "wdate", onfocus = "WdatePicker()" }) 
        //public DateTime Createtime
        //{
        //    get
        //    {
        //        //_createtime = _createtime.("yyyy-MM-dd hh:mm");
        //        return _createtime ;
        //    }
        //    set { _isChanged |= (_createtime != value); _createtime = value; }
        //} 

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