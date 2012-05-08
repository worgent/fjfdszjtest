using System;
using QzgfFrame.Utility.Page;
public partial class admin_index : PageBase
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    public string RealName
    {
        get
        {
           // var service = new UserService();
           // var entity = service.GetUser(SysContext.CurrentUserID);
           // return entity.RealName;
            return "RealName";
        }
    }
}