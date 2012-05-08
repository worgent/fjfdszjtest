using System;
using NHibernate;
using QzgfFrame.Utility.Business;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Utility.Page
{
    public class PageBase : System.Web.UI.Page
    {

        public ISession CurrentSession
        {
            get
            {
                return NhibernateHelper.GetCurrentSession();
            }
        }

        protected override void OnLoad(EventArgs e)
        {
            if (SysContext.CurrentUserID == 0)
            {
                Response.Redirect("~/admin/login.aspx?FromUrl=" + Request.Path);
            }
            base.OnLoad(e);
        }
         
    }
}
