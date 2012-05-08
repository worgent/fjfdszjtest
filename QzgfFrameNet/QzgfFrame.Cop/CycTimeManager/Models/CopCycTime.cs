/****************************************************************** 
 * 文件名.........: CopCycTime.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......: 时间 人员 备注 
 *                  --------- ------- 
 * --------------------------------------- 
 *                  2011-01-15 XXXX 创建文件，初始设计 
 *                  2011-01-16 XXXX 增加安全部分的加密功能 
 ******************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Cop.CycTimeManager.Models
{
    /// <summary>
    /// 专线巡检周期
    /// </summary>
    [Serializable]
    public sealed class CopCycTime
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        /// <summary>
        /// 巡检时间
        /// </summary>
        private string _cyctime;

        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public CopCycTime()
        {
            _id = String.Empty;
            _cyctime = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public CopCycTime(string id, string cyctime)
        {
            _id = id;
            _cyctime = cyctime;
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
        /// 巡检时间
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
