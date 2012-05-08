using System;
using System.Web;
using Newtonsoft.Json;
using QzgfFrame.Utility.LigerGrid;
using System.Web.SessionState;

namespace QzgfFrame.Mvc3.Service
{
    public class BillListData : IHttpHandler, IRequiresSessionState
    {
        public void ProcessRequest(HttpContext context)
        {
           context.Response.ContentType = "text/plain";
            try
            {
                if (context.Request.Params["Action"] == "GetGridView")
                    TryGetGridViewData();
            }
            catch (Exception err)
            {
                context.Response.Write("null");
            }
            context.Response.End();
        }
        /// <summary>
        /// Grid的分页显示,过滤脚本调用该接口
        /// </summary>
        public void TryGetGridViewData()
        {
            var context = HttpContext.Current;
            var pager = new GridViewPager();
            var datajson = pager.GetDataJSON();
            context.Response.Write(datajson);
        }
        public bool IsReusable
        {
            get
            {
                return false;
            }
        }

    }
}