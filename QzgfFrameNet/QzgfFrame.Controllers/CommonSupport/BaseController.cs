using System.Web.Mvc;
using System.Web;
using System;
using System.Data;
using QzgfFrame.Utility.Common.Helpers;
using QzgfFrame.Controllers.CommonSupport.Filter;

namespace QzgfFrame.Controllers.CommonSupport
{
    // 所有Controller都要继承的基类
    [AuthorizeFilter]
    //[ExceFilter]
    public class BaseController : Controller
    {
        public CurrentUser currentUser { set; get; }
        public CurrentExaminee currentExaminee { set; get; }
        public virtual string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return @"{""Rows"":[],""Total"":""0""}";
        }
        public virtual string GridSelPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {

            return @"{""Rows"":[],""Total"":""0""}";
        }
        public virtual bool LoadFile(HttpPostedFileBase file, string savePath, out string strFileName, out string msg, out string ShowInfo)
        {
            strFileName = "";
            ShowInfo = ""; msg = "";
            if (file.ContentLength == 0)
            {
                msg = "请选择要上传的文件";
                return false;
            }
            int retValue = 0;
            retValue = CommLoadFile.UploadXlsFile(out strFileName, file, savePath);
            if (retValue == 0)
            {
                ShowInfo += "文件成功上传<br>";
            }
            else if (retValue == -1)
            {
                msg = "上传时出错，错误信息如下：<br>！！" + strFileName;
                return false;
            }
            else
            {
                msg = "出现不预期错误";
                return false;
            }
            return true;
        }
    }
}
