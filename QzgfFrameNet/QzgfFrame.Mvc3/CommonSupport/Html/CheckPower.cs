using System.Web;
using System.Web.Mvc;
using System.Linq;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Utility.Core;

namespace QzgfFrame.Mvc3.CommonSupport.Html
{
    public static class HtmlExtensions
    {
        public static bool IsPrint(this HtmlHelper helper, string permissionsflag, string fieldcode)
        { 
            var currentUser = (HttpContext.Current.Session[Constant.Userkey]) as CurrentUser;
            if(currentUser!=null)
            {
                if (currentUser.UserInfo.Id.Trim() == "1")
                {
                    return true;
                }
                var curmenufieldlist = currentUser.MenufieldPermission.Where(m => (m.Permissionsflag == permissionsflag) && (m.Fieldcode == fieldcode));
                return curmenufieldlist.Any(curmenufield => curmenufield.IsPrint);
            }
            return true;
        }

        public static bool IsShow(this HtmlHelper helper, string permissionsflag, string fieldcode)
        {
            var currentUser = (HttpContext.Current.Session[Constant.Userkey]) as CurrentUser;
            if (currentUser != null)
            {
                if (currentUser.UserInfo.Id.Trim() == "1")
                {
                    return true;
                }
                var curmenufieldlist = currentUser.MenufieldPermission.Where(m => ((null != m.Permissionsflag ? m.Permissionsflag.ToLower().Trim() : "") == permissionsflag.ToLower().Trim()) && (m.Fieldcode.ToLower().Trim() == fieldcode.ToLower().Trim()));
                return curmenufieldlist.Any(curmenufield => curmenufield.IsShow);
            } 
            return true;
        }

        public static bool IsExport(this HtmlHelper helper, string permissionsflag, string fieldcode)
        {
            var currentUser = (HttpContext.Current.Session[Constant.Userkey]) as CurrentUser;
            if (currentUser != null)
            {
                if (currentUser.UserInfo.Id.Trim() == "1")
                {
                    return true;
                }
                var curmenufieldlist = currentUser.MenufieldPermission.Where(m => (m.Permissionsflag == permissionsflag) && (m.Fieldcode == fieldcode));
                return curmenufieldlist.Any(curmenufield => curmenufield.IsExport);
            } 
            return true;
        }
    }
}
