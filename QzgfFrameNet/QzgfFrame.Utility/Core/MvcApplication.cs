using System.Web.Routing;
using System.Web.Mvc;
using Spring.Context.Support;

namespace QzgfFrame.Utility.Core
{
    public class MvcApplication : System.Web.HttpApplication
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
        //控制器
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");//查看路由配置
            routes.IgnoreRoute("{resource}.ashx/{*pathInfo}");
            routes.IgnoreRoute("{resource}.gif/{*pathInfo}");
            routes.IgnoreRoute("{resource}.asmx/{*pathInfo}");//webservice调用时用到.
            ////Home层(当未登录时,会自动转到改action)
            routes.MapRoute(
                "Home", // Route name
                "", // 增加路由管理是默认应用层入口,这样view就在Application下
                new { controller = "Home", action = "Login", id = UrlParameter.Optional } // Parameter defaults
            );
            //====以下暂时通过Areas进行设置==================
            //系统管理
            //档案管理
            //通用层
            routes.MapRoute(
                "Default", // Route name
                "{controller}/{action}/{id}", // 增加路由管理是默认应用层入口,这样view就在Application下
                new { controller = "Home", action = "Index", id = UrlParameter.Optional } // Parameter defaults
            );
        }

        //这个是入口函数(从protected改为public是为了mvc3项目可以访问)
        protected void Application_Start()
        {
            //启动HibernatingRhinos,跟综hql语句执行 最新版(部署时不能加这个,会报csc.exe没法找到错误)
            //HibernatingRhinos.Profiler.Appender.NHibernate.NHibernateProfiler.Initialize();
            //HibernatingRhinos.NHibernate.Profiler.Appender.NHibernateProfiler.Initialize();
            //加载log4j
            log4net.Config.XmlConfigurator.Configure();

            //模板引擎(用原来的模板引擎)
            //ViewEngines.Engines.Clear();
            //ViewEngines.Engines.Add(new MvcViewEngine());
            AreaRegistration.RegisterAllAreas();
            //敏感信息过滤
            RegisterGlobalFilters(GlobalFilters.Filters);
            //注册spring管理的类
            ControllerBuilder.Current.SetControllerFactory(typeof(SpringControllerFactory));
            //路由表信息注册
            RegisterRoutes(RouteTable.Routes);
        }
    }
}
