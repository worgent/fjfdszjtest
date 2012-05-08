using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Web.Mvc;

namespace QzgfFrame.Utility.Core
{
    /**
通过reflector找到视图引擎的构造接口类VirtualPathProviderViewEngine
在MVC2.0中，自定义自己的视图引擎，继承它即可，但在3.0中，我发现继承它会缺少一个函数。
 再reflector获得了BuildManagerViewEngine的抽象类，因为RazorViewEngine继承的是该抽象类。
     */
    public class MvcViewEngine : BuildManagerViewEngine
    {
        public MvcViewEngine()
        {
            MasterLocationFormats = new[] {
                "~/{3}/{2}/{1}/{0}.master",
                "~/{3}/SharedView/{0}.master"
            };

            ViewLocationFormats = new[] {
                "~/{3}/{2}/{1}/{0}.aspx",
                "~/{3}/{2}/{1}/{0}.ascx",
                "~/Shared/{0}.aspx",
                "~/Shared/{0}.ascx"
            };

            //=====暂不对Areas区域处理=============
            AreaMasterLocationFormats = new[] {
                "~/Areas/{2}/Views/{1}/{0}.master",
                "~/Areas/{2}/Views/Shared/{0}.master"
            };

            AreaViewLocationFormats = new[] {
                "~/Areas/{2}/Views/{1}/{0}.aspx",
                "~/Areas/{2}/Views/{1}/{0}.ascx",
                "~/Areas/{2}/Views/Shared/{0}.aspx",
                "~/Areas/{2}/Views/Shared/{0}.ascx"
            };

            PartialViewLocationFormats = ViewLocationFormats;
            AreaPartialViewLocationFormats = AreaViewLocationFormats;

            FileExtensions=new string[] { "aspx", "ascx", "master" };

        }


        protected override IView CreatePartialView(ControllerContext controllerContext, string partialPath)
        {
            string app=controllerContext.RouteData.GetRequiredString("app");
            string module=controllerContext.RouteData.GetRequiredString("module");

            

            string layoutPath = null;
            const bool runViewStartPages = false;
            IEnumerable<string> fileExtensions = base.FileExtensions;
            return new RazorView(controllerContext, partialPath, layoutPath, runViewStartPages, fileExtensions, base.ViewPageActivator);
        }

        protected override IView CreateView(ControllerContext controllerContext, string viewPath, string masterPath)
        {
            //1.asp解析时可以用return new WebFormView(controllerContext,viewPath, masterPath);
            //2.Razor
            string app = controllerContext.RouteData.GetRequiredString("app");
            string module = controllerContext.RouteData.GetRequiredString("module");

            string layoutPath = null;
            const bool runViewStartPages = false;
            IEnumerable<string> fileExtensions = base.FileExtensions;
            return new RazorView(controllerContext, viewPath, layoutPath, runViewStartPages, fileExtensions, base.ViewPageActivator);
        }

        //=====================================重写:目的修改view结构===========================

        ////重写view读取方式
        //public override ViewEngineResult FindView(ControllerContext controllerContext, string viewName, string masterName, bool useCache)
        //{
        //    string[] strArray;
        //    string[] strArray2;
        //    if (controllerContext == null)
        //    {
        //        throw new ArgumentNullException("controllerContext");
        //    }
        //    if (string.IsNullOrEmpty(viewName))
        //    {
        //        throw new ArgumentException("ex", "viewName");
        //    }
        //    string requiredString = controllerContext.RouteData.GetRequiredString("controller");
        //    string str2 = this.GetPath(controllerContext, this.ViewLocationFormats, this.AreaViewLocationFormats, "ViewLocationFormats", viewName, requiredString, "View", useCache, out strArray);
        //    string str3 = this.GetPath(controllerContext, this.MasterLocationFormats, this.AreaMasterLocationFormats, "MasterLocationFormats", masterName, requiredString, "Master", useCache, out strArray2);
        //    if (!string.IsNullOrEmpty(str2) && (!string.IsNullOrEmpty(str3) || string.IsNullOrEmpty(masterName)))
        //    {
        //        return new ViewEngineResult(this.CreateView(controllerContext, str2, str3), this);
        //    }
        //    return new ViewEngineResult(strArray.Union<string>(strArray2));
        //}


        //public override ViewEngineResult FindPartialView(ControllerContext controllerContext, string partialViewName, bool useCache)
        //{
        //    string[] strArray;
        //    if (controllerContext == null)
        //    {
        //        throw new ArgumentNullException("controllerContext");
        //    }
        //    if (string.IsNullOrEmpty(partialViewName))
        //    {
        //        throw new ArgumentException("ex", "partialViewName");
        //    }
        //    string requiredString = controllerContext.RouteData.GetRequiredString("controller");
        //    string str2 = this.GetPath(controllerContext, this.PartialViewLocationFormats, this.AreaPartialViewLocationFormats, "PartialViewLocationFormats", partialViewName, requiredString, "Partial", useCache, out strArray);
        //    if (string.IsNullOrEmpty(str2))
        //    {
        //        return new ViewEngineResult(strArray);
        //    }
        //    return new ViewEngineResult(this.CreatePartialView(controllerContext, str2), this);
        //}


        //private string GetPath(ControllerContext controllerContext, string[] locations, string[] areaLocations, string locationsPropertyName, string name, string controllerName, string cacheKeyPrefix, bool useCache, out string[] searchedLocations)
        //{
        //    searchedLocations = _emptyLocations;
        //    if (string.IsNullOrEmpty(name))
        //    {
        //        return string.Empty;
        //    }
        //    string areaName = AreaHelpers.GetAreaName(controllerContext.RouteData);
        //    bool flag = !string.IsNullOrEmpty(areaName);
        //    List<ViewLocation> viewLocations = GetViewLocations(locations, flag ? areaLocations : null);
        //    if (viewLocations.Count == 0)
        //    {
        //        throw new InvalidOperationException(string.Format(CultureInfo.CurrentCulture, MvcResources.Common_PropertyCannotBeNullOrEmpty, new object[] { locationsPropertyName }));
        //    }
        //    bool flag2 = IsSpecificPath(name);
        //    string key = this.CreateCacheKey(cacheKeyPrefix, name, flag2 ? string.Empty : controllerName, areaName);
        //    if (useCache)
        //    {
        //        return this.ViewLocationCache.GetViewLocation(controllerContext.HttpContext, key);
        //    }
        //    if (!flag2)
        //    {
        //        return this.GetPathFromGeneralName(controllerContext, viewLocations, name, controllerName, areaName, key, ref searchedLocations);
        //    }
        //    return this.GetPathFromSpecificName(controllerContext, name, key, ref searchedLocations);
        //}

 



    }
}
