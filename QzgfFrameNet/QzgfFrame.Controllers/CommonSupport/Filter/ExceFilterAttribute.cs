using System;
using System.Web.Mvc;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.QzgfException;
using QzgfFrame.Utility.Core.ViewModel;

namespace QzgfFrame.Controllers.CommonSupport.Filter
{
    /// <summary>
    /// 拦截Action的异常，输出Json给EXT捕获(目前loaddata类操作在JS中暂时没有处理)
    /// </summary>
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Method, AllowMultiple = false)]
    public class ExceFilterAttribute : ActionFilterAttribute
    {
        public override void OnActionExecuted(ActionExecutedContext filterContext)
        {
            if (filterContext.Exception != null)
            {
                object[] attrs = filterContext.ActionDescriptor.GetCustomAttributes(typeof(ExtResultAttribute), true);
                if (attrs.Length == 1)//判断是否属于ExtResult的Action
                {
                    string msgTmp;
                    if(filterContext.Exception is ValidationException)//如果是验证类异常 ，就不出详细异常信息了
                        msgTmp = "<b>验证错误:  </b>{0}";
                    else
                        msgTmp = "<b>异常消息:  </b>{0}</p><b>触发Action:  </b>{1}</p><b>异常类型:  </b>{2}";
                    var excResult = new JsonResult();
                    excResult.Data = new ExtResult { 
                        success = false,
                        msg = string.Format(msgTmp, 
                                filterContext.Exception.GetBaseException().Message,
                                filterContext.ActionDescriptor.ActionName,
                                filterContext.Exception.GetBaseException().GetType().ToString())
                    };
                    filterContext.Result = excResult;
                }
                else
                {
                    var excResult = new ContentResult();
                    excResult.Content = String.Format(@"JsHelper.ShowError('<b>异常消息:  </p>{0}</br><b>触发Action:  </p>{1}</br><b>异常类型:  </b>{2}')",
                        filterContext.Exception.GetBaseException().Message,
                        filterContext.ActionDescriptor.ActionName,
                        filterContext.Exception.GetBaseException().GetType().ToString());
                    filterContext.Result = excResult;

                }
                filterContext.ExceptionHandled = true;
            }
        }
    }
}
