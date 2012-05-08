using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.SessionState;
using Newtonsoft.Json;
using QzgfFrame.Controllers.CommonSupport;
using Spring.Context;
using Spring.Context.Support;

namespace QzgfFrame.Mvc3.Service
{
    /// <summary>
    /// SystemData 的摘要说明
    /// </summary>
    public class SystemData : IHttpHandler, IRequiresSessionState
    {
        // private readonly MenuFacadeImpl menuFacade = new MenuFacadeImpl();
        //private MenuFacade menuFacade { set; get; }

        public void ProcessRequest(HttpContext context)
        {
            context.Response.ContentType = "text/plain";
            try
            {
                switch (context.Request.Params["Action"])
                {
                    case "GetApplication":
                        GetApplication();
                        break;
                    case "GetMenu":
                        GetMenu();
                        break;
                    case "GetButton":
                        GetButton();
                        break;
                }
            }
            catch (Exception err)
            {
                var message = err.Message;
                if (err.InnerException != null && err.InnerException.Message != null)
                    message += "<BR>" + err.InnerException.Message;
                var a = new { Type = "Error", Message = message };
                var json = JsonConvert.SerializeObject(a);
                context.Response.Write(json);
            }
            context.Response.End();
        }
        /// <summary>
        /// 用于原首页模式中的模块菜单,现在暂时不用
        /// </summary>
        void GetApplication()
        {
            var context = HttpContext.Current;
            List<Hashtable> jsonlist = new List<Hashtable>();
            Hashtable hs=new Hashtable();
            hs.Add("code", "1000");
            hs.Add("name", "系统管理");
            hs.Add("desc", "系统管理测试项目");
            jsonlist.Add(hs);
            string json = JsonConvert.SerializeObject(jsonlist.ToArray());
            context.Response.Write(json);
        }
        /// <summary>
        /// 取得功能菜单列表格式为:{text:name,url:url,childe:tree};
        /// </summary>
        void GetMenu()
        {
            var context = HttpContext.Current;

            //=====================以下是测试数据===============
            //测试数据
            //List<Hashtable> jsonlist = new List<Hashtable>();
            //Hashtable hs = new Hashtable();
            //hs.Add("name", "主页");
            //hs.Add("menuno", "1");
            //hs.Add("url", "/home/welcome");
            //hs.Add("icon", "");
            //jsonlist.Add(hs);

            //hs.Clear();
            //hs.Add("name", "主页2");
            //hs.Add("menuno", "2");
            //hs.Add("url", "/home/welcome");
            //hs.Add("icon", "");
            //jsonlist.Add(hs);

            //hs.Clear();
            //hs.Add("name", "菜单");
            //hs.Add("menuno", "3");
            //hs.Add("url", "/system/menu/index");
            //hs.Add("icon", "");
            //jsonlist.Add(hs);
            //=====================================================

            //取得spring容器中的数据
            IApplicationContext ctx = ContextRegistry.GetContext();
            SystemDataPro sd = (SystemDataPro)ctx.GetObject("SystemDataPro");
            context.Response.Write(sd.GetMenu());
        }
        /// <summary>
        /// 动态菜单生成
        /// </summary>
        void GetButton()
        {
            var context = HttpContext.Current;
            string menuNo = context.Request.Params["MenuNo"];
            //==========================测试数据==========================
            /*
             if (IsAdministrator())
            {
                var systemservice = new SystemService();
                list = systemservice.GetButtons(MenuNo);
            }
            else
            {
                var userservice = new UserService(); 
                list = userservice.GetButtons(MenuNo);
            }
            var jsonlist = (from a in list
                            select new
                            {
                                id = a.BtnNo,
                                icon = a.BtnIcon,
                                name = a.BtnName
                            }
                           ).ToArray();
            */

            //List<Hashtable> jsonlist = new List<Hashtable>();
            //Hashtable hs = new Hashtable();
            //hs.Add("id", "add");
            //hs.Add("icon", "");
            //hs.Add("name", "增加");
            //jsonlist.Add(hs);

            //hs.Clear();
            //hs.Add("id", "delete");
            //hs.Add("icon", "");
            //hs.Add("name", "删除");
            //jsonlist.Add(hs);

            //hs.Clear();
            //hs.Add("id", "modify");
            //hs.Add("icon", "");
            //hs.Add("name", "修改");
            //jsonlist.Add(hs);

            //hs.Clear();
            //hs.Add("id", "button");
            //hs.Add("icon", "");
            //hs.Add("name", "普通按钮");
            //jsonlist.Add(hs);
            //==========================================================

            //取得spring容器中的数据
            IApplicationContext ctx = ContextRegistry.GetContext();
            SystemDataPro sd = (SystemDataPro)ctx.GetObject("SystemDataPro");
            context.Response.Write(sd.GetButton(menuNo));
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