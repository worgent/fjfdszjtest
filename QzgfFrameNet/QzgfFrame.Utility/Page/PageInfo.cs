using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Utility.Page
{
    public class PageInfo
    {
        //公共的页数，记录数，当前页等
         int _totalCount, _pageSize, _currentPage, _pageCount, _pageCode;
        public  int totalCount
        {
            set
            {
                _totalCount = value;
            }
            get
            {
                return _totalCount;
            }
        }
        public  int pageCount
        {
            set
            {
                _pageCount = value;
            }
            get
            {
                return _pageCount;
            }
        }
        public  int pageSize
        {
            set
            {
                _pageSize = value;
            }
            get
            {
                return _pageSize;
            }
        }
        public  int currentPage
        {
            set
            {
                _currentPage = value;
            }
            get
            {
                return _currentPage;
            }
        }
        public  int pageCode
        {
            set
            {
                _pageCode = value;
            }
            get
            {
                return _pageCode;
            }
        }
    }
}
