/*
 * 刘冬 博客园
 * www.cnblogs.com/GoodHelper
 */

using System;
using System.Web.Mvc;
using System.Web.SessionState;
using Spring.Context;
using Spring.Context.Support;

namespace QzgfFrame.Utility.Core
{
    /// <summary>
    /// Spring.NET ControllerFacotry
    /// </summary>
    public class SpringControllerFactory : IControllerFactory
    {
        /// <summary>
        /// Default ControllerFactory
        /// </summary>
        private static DefaultControllerFactory _default = null;

        public IController CreateController(System.Web.Routing.RequestContext requestContext, string controllerName)
        {
            //get spring context
            WebApplicationContext ctx = ContextRegistry.GetContext() as WebApplicationContext;
            string controller = controllerName + "Controller";//所有控制器配置文件name都以Action结尾
            //查找是否配置该Controller
            if (ctx.ContainsObject(controller))
            {
                object controllerf = ctx.GetObject(controller);
                return (IController)controllerf;
            }
            if (_default == null)
            {
                _default = new DefaultControllerFactory();
            }

            //返回异常打印信息
            IController myController=null;
            try
            {
                myController = _default.CreateController(requestContext, controllerName);
            }
            catch (Exception ex)
            {
                System.Console.WriteLine(ex.ToString());
            }
            return myController;

        }
        public SessionStateBehavior GetControllerSessionBehavior(System.Web.Routing.RequestContext requestContext, string controllerName)
        {
            return SessionStateBehavior.Default;
        }
        public void ReleaseController(IController controller)
        {
            //get spring context
            IApplicationContext ctx = ContextRegistry.GetContext();
            if (!ctx.ContainsObject(controller.GetType().Name))
            {
                if (_default == null)
                {
                    _default = new DefaultControllerFactory();
                }

                _default.ReleaseController(controller);
            }
        }

    }
}
