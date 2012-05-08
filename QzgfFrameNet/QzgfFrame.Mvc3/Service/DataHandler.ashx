
using System;
using System.Linq;
using System.Collections;
using System.Collections.Generic;
using System.Web;
using ligerRM.Service;
using System.Web.SessionState;
public class DataHandler : IHttpHandler, IRequiresSessionState
{

    public void ProcessRequest(HttpContext context)
    {
        context.Response.ContentType = "text/plain";
        try
        {
            if (context.Request.Params["Action"] == "Delete")
            {
                TryDoDelete();
            }

            context.Response.Write("{\"Type\":\"Success\"}");
        }
        catch (Exception err)
        {
            context.Response.Write("{\"Type\":\"Error\",\"Message\":\"" + err.Message + "\"}");
        } 
    }
    void TryDoDelete()
    {

    }
    public bool IsReusable {
        get {
            return false;
        }
    }

}