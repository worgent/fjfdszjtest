namespace QzgfFrame.Utility.Common
{
    public sealed class SysContext
    {
        public static void SetCurrent(int userid, string loginname)
        {
            SetCurrent(userid, loginname, null);
        }
        public static void SetCurrent(int userid, string loginname, string language)
        {
            CurrentUserID = userid;
            CurrentLoginName = loginname;
        }
        public static void ClearUserStatus()
        {
            System.Web.HttpContext ctx = System.Web.HttpContext.Current;
            ctx.Session.Remove("CurrentUserID");
            ctx.Session.Remove("CurrentLoginName");
            ctx.Session.Remove("CurrentLanguage");
        }


        public static int CurrentUserID
        {
            get
            {
                try
                {
                    System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                    object value = ctx.Session["CurrentUserID"];
                    return value.ToInt();
                }
                catch
                {
                    return 0;
                }
            }
            set
            {
                System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                ctx.Session["CurrentUserID"] = value;
            }
        }
         

        public static string CurrentLoginName
        {
            get
            {
                try
                {
                    System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                    object value = ctx.Session["CurrentLoginName"];
                    return value.ToStr();
                }
                catch
                {
                    return null;
                }
            }
            set
            {
                System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                ctx.Session["CurrentLoginName"] = value;
            }
        }

        public static string CurrentLanguage
        {
            get
            {
                try
                {
                    System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                    object value = ctx.Session["CurrentLanguage"];
                    return value.ToStr();
                }
                catch
                {
                    return null;
                }
            }
            set
            {
                System.Web.HttpContext ctx = System.Web.HttpContext.Current;
                ctx.Session["CurrentLanguage"] = value;
            }
        }
    }
}
