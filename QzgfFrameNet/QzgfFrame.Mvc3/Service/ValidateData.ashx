
using System;
using System.Web;
using ligerRM.Service;
using System.Web.SessionState;
public class ValidateData : IHttpHandler, IRequiresSessionState
{
    
    public void ProcessRequest (HttpContext context) {
        context.Response.ContentType = "text/plain";
        try
        {
            if (context.Request.Params["Action"] == "Exist")
                ValidateExist();
            if (context.Request.Params["Action"] == "Login")
                ValidateLogin();
        } 
        catch (Exception err)
        {
            context.Response.Write("true");
        }
        context.Response.End();
    }
    void ValidateExist()
    {
      
    }
    void ValidateLogin()
    {
      
    }
    public bool IsReusable {
        get {
            return false;
        }
    }

}