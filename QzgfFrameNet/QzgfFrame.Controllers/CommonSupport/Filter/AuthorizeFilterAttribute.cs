using System;
using System.Collections;
using System.Linq;
using System.Collections.Generic;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using System.Xml;
using QzgfFrame.System.LogManger.Models;
using QzgfFrame.Utility.Core.Common;
using Spring.Util;

namespace QzgfFrame.Controllers.CommonSupport.Filter
{
    /// <summary>
    /// 权限拦截
    /// </summary>
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Method, AllowMultiple = false)]
    public class AuthorizeFilterAttribute : ActionFilterAttribute
    {
        //存储不需验证的control
        private Hashtable ht = new Hashtable();

        #region 权限拦截
        public override void OnActionExecuting(ActionExecutingContext filterContext)
        {
            if (filterContext == null)
            {
                throw new ArgumentNullException("filterContext");
            }
            var path = filterContext.HttpContext.Request.Path.ToLower();

            if (ht.Count == 0)
            {
                var context = HttpContext.Current;
                var xml = new XmlDocument();
                xml.Load(context.Server.MapPath("~/Config/pageextend.xml"));
                var views = xml.SelectNodes("/pages/page");
                foreach (XmlNode view in views)
                {
                    if (view.Attributes != null && !ht.ContainsKey(view.Attributes["controller"].Value))
                    {
                        ht.Add(view.Attributes["controller"].Value, view.Attributes["name"].Value); 
                    }
                    
                }
            }

            if (path == "/" ||path == "/home/login" || path == "/home/checklogin" || path.IndexOf("/home/authcode") == 0 || path == "/exam/examinee/login" || path == "/exam/examinee/checklogin" || path.IndexOf("/exam/examinee/authcode") == 0)
                
                return;//忽略对Login登录页的权限判定  path == "/" || 

            switch (this.AuthorizeCore(filterContext, path))
            {
                case "1"://正常
                    break;
                case "2"://暂未实现
                    filterContext.Result = new RedirectToRouteResult("Default", new RouteValueDictionary(new { controller = "Home", action = "login" }));
                    break;
                case "3"://session过期
                    //filterContext.Result = new RedirectToRouteResult("Default", new RouteValueDictionary(new { controller = "Home", action = "login" }));
                    filterContext.Result = new ContentResult { Content = @"<script>
                                            var isSure = confirm('页面Session过期，是否转到登陆页?');
                                            if(isSure) {
                                                        if(parent.window==this){location.href = '/home/login';}else{parent.window.location.href='/home/login';}
                                            }
                                            </script>"
                    };//功能权限弹出提示框
                    break;
                case "4"://无权限提示
                    //filterContext.Result = new HttpUnauthorizedResult();//直接URL输入的页面地址跳转到登陆页
                    filterContext.Result = new ContentResult { Content = @"<script>alert('抱歉,你不具有当前操作的权限！')</script>" };//功能权限弹出提示框
                    break;
                default:
                    break;
            }
        }
        //权限判断业务逻辑
        protected virtual string AuthorizeCore(ActionExecutingContext filterContext, string path)
        {
            if (filterContext.Controller is BaseController)
            {
                var bc = filterContext.Controller as BaseController;
                var user = bc.currentUser; //获取当前用户信息
                if (!user.IsHaveUser())
                {
                    return "3";//用户session异常
                }
                else
                {
                    var controllerName = filterContext.RouteData.Values["controller"].ToString().ToLower();
                    var actionName = filterContext.RouteData.Values["action"].ToString().ToLower();

                    //用户配置的优先级最高,还有是系统最高级用户,不拦截.
                    if ((ht != null && (ht.ContainsKey(controllerName))) || user.UserInfo.Id.Trim() == "1")
                    {
                        return "1";
                    }
                    bool flag = PathMatcher.Match(@"/(index)|(add)|(modify)|(delete)|(usestart)|(usestop)|(loadfile)|(quit)|(search)/", path, true);
                    //初始化权限列表
                    var hs = new Dictionary<string, int> { };
                    hs.Add("index", 0);
                    hs.Add("add", 1);
                    hs.Add("delete", 2);
                    hs.Add("modify", 3);
                    hs.Add("usestart", 4);
                    hs.Add("usestop", 5);
                    hs.Add("loadfile", 6);
                    hs.Add("quit", 7);
                    hs.Add("search", 8);

                    if (flag)
                    {
                        var ls = user.MenuPermission.Where(m => (m.Url != null) && (m.Url.ToLower().IndexOf(controllerName) > 0));
                        //多角色同时关连时，需叠代处理(或运算)
                        int optval = ls.Aggregate(0, (current, menuval) => current | menuval.Optval);
                        if (hs.ContainsKey(actionName))
                        {
                            int powval = Int32.Parse(Math.Pow(2, hs[actionName]).ToString());
                            if ((optval & powval) != powval) return "4";
                        }
                    }
                }
            }
            return "1";
        }
        #endregion

        #region 日志拦截
        public override void OnActionExecuted(ActionExecutedContext  filterContext)
        {
            //过滤器异常
            if (filterContext == null)
            {
                throw new ArgumentNullException("filterContext");
            }

            if (filterContext.Controller is BaseController)
            {
                var path = filterContext.HttpContext.Request.Path.ToLower();
                var controllerName = filterContext.RouteData.Values["controller"].ToString().ToLower();
                var actionName = filterContext.RouteData.Values["action"].ToString().ToLower();
                var bc = filterContext.Controller as BaseController;
                var user = bc.currentUser; //获取当前用户信息
                if (!user.IsHaveUser())
                {
                    return;//用户session异常
                }
                //写日志，需要过滤方法
                bool flag = PathMatcher.Match(@"/(add)|(modify)|(delete)|(usestart)|(usestop)|(loadfile)|(quit)/", actionName, true);
                
                if(flag)
                {
                    //用户信息，时间，操作，
                    var entity = new SystemLog();
                    entity.Operdate = DateTime.Now;
                    entity.Operip = CommonHelper.ClientIP();
                    entity.Userid = user.UserInfo.Id;
                    entity.Opercode = actionName;//从菜单中提取
                    int indexno = path.LastIndexOf(controllerName) + controllerName.Length;
                    controllerName = path.Substring(0, indexno);
                    entity.Controllerscode = controllerName;
                    user.logFacade.Save(entity); 
                }
            }
        }


        #endregion
    }
}
