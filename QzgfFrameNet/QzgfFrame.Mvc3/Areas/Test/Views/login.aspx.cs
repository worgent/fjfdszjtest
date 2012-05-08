using System;
using System.Web.UI;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Mvc3.Areas.Test.Views
{
    public partial class admin_login : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString["Action"] == "out") 
            {
                SysContext.ClearUserStatus();
            }
        }

        public string FromUrl
        {
            get {
                if (string.IsNullOrEmpty(Request.QueryString["FromUrl"]))
                    return "index.aspx";
                return Request.QueryString["FromUrl"];
            }
        }
    }
}