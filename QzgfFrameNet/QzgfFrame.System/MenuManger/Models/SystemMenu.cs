/****************************************************************** 
 * 文件名.........: MenuController.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.System.MenuManger.Models
{
    /// <summary>
    /// auto gen
    /// </summary>
    [Serializable]
    public sealed class SystemMenu
    {
        #region Private Members

        // Variabili di stato
        private bool _isChanged;
        private bool _isDeleted;

        // Primary Key(s) 
        private string _id;

        // Properties 
        private string _name;
        private string _pic;
        private string _url;
        private string _order;
        private string _father;
        private string _fathername;
        private string _ismenu;
        private string _ismenuname;

        private string _permissionsflag;
        private Int32 _optval;
        #endregion

        #region Default ( Empty ) Class Constructor

        /// <summary>
        /// default constructor
        /// </summary>
        public SystemMenu()
        {
            _id = String.Empty;
            _name = String.Empty;
            _pic = String.Empty;
            _url = String.Empty;
            _order = String.Empty;
            _father = String.Empty;
            _fathername = String.Empty;
            _ismenu = "0";
            _ismenuname = "否";
            _permissionsflag = String.Empty;
        }

        #endregion // End of Default ( Empty ) Class Constructor

        #region Full Constructor

        /// <summary>
        /// full constructor
        /// </summary>
        public SystemMenu(string id, string name, string pic, string url, string order, string father, string ismenu, string permissionsflag)
        {
            _id = id;
            _name = name;
            _pic = pic;
            _url = url;
            _order = order;
            _father = father;
            _ismenu = ismenu;
            _permissionsflag = permissionsflag;
        }
        /// <summary>
        /// 权限值信息
        /// </summary>
        /// <param name="id"></param>
        /// <param name="name"></param>
        /// <param name="pic"></param>
        /// <param name="url"></param>
        /// <param name="order"></param>
        /// <param name="father"></param>
        /// <param name="ismenu"></param>
        /// <param name="permissionsflag"></param>
        public SystemMenu(string id, string name, string pic, string url, string order, string father, string ismenu, string permissionsflag, Int32 optval)
        {
            _id = id;
            _name = name;
            _pic = pic;
            _url = url;
            _order = order;
            _father = father;
            _ismenu = ismenu;
            _permissionsflag = permissionsflag;
            _optval = optval;
        }

        /// <summary>
        /// 列表信息
        /// </summary>
        /// <param name="id"></param>
        /// <param name="name"></param>
        /// <param name="pic"></param>
        /// <param name="url"></param>
        /// <param name="order"></param>
        /// <param name="father"></param>
        /// <param name="ismenu"></param>
        /// <param name="fathername"></param>
        /// <param name="ismenuname"></param>
        /// <param name="permissionsflag"></param>
        public SystemMenu(string id, string name, string pic, string url, string order, string father, string ismenu, string fathername, string ismenuname, string permissionsflag)
        {
            _id = id;
            _name = name;
            _pic = pic;
            _url = url;
            _order = order;
            _father = father;
            _ismenu = ismenu;
            _fathername = fathername;
            _ismenuname = ismenuname;
            _permissionsflag = permissionsflag;
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
        /// 菜单名称
        /// </summary>		
        public string Name
        {
            get { return _name; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Name", value, value.ToString());

                _isChanged |= (_name != value); _name = value;
            }
        }

        /// <summary>
        /// 菜单图片
        /// </summary>		
        public string Pic
        {
            get { return _pic; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Pic", value, value.ToString());

                _isChanged |= (_pic != value); _pic = value;
            }
        }

        /// <summary>
        /// 菜单地址
        /// </summary>		
        public string Url
        {
            get { return _url; }
            set
            {
                if (value != null)
                    if (value.Length > 256)
                        throw new ArgumentOutOfRangeException("Invalid value for Url", value, value.ToString());

                _isChanged |= (_url != value); _url = value;
            }
        }

        /// <summary>
        /// 排序
        /// </summary>		
        public string Orderno
        {
            get { return _order; }
            set
            {
                if (value != null)
                    if (value.Length > 10)
                        throw new ArgumentOutOfRangeException("Invalid value for Order", value, value.ToString());

                _isChanged |= (_order != value); _order = value;
            }
        }

        /// <summary>
        /// 父结点
        /// </summary>		
        public string Father
        {
            get { return _father; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Father", value, value.ToString());

                _isChanged |= (_father != value); _father = value;
            }
        }

        public string FatherName
        {
            get { return _fathername; }
            set
            {
                if (value != null)
                    if (value.Length > 20)
                        throw new ArgumentOutOfRangeException("Invalid value for Father", value, value.ToString());

                _isChanged |= (_fathername != value); _fathername = value;
            }
        }

        /// <summary>
        /// 菜单仅显示,叶子可做为功能结点(如:添加,册除,金额)
        /// </summary>		
        public string Ismenu
        {
            get { return _ismenu; }
            set { _isChanged |= (_ismenu != value); _ismenu = value; }
        }

        public string IsmenuName
        {
            get { return _ismenuname; }
            set { _isChanged |= (_ismenuname != value); _ismenuname = value; }
        }


        public Int32 Optval
        {
            get { return _optval; }
            set { _isChanged |= (_optval != value); _optval = value; }
        }

        public string Permissionsflag
        {
            get { return _permissionsflag; }
            set { _isChanged |= (_permissionsflag != value); _permissionsflag = value; }
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