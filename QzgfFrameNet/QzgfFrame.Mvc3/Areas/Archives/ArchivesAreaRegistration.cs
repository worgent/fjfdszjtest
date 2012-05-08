﻿using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Archives
{
    public class ArchivesAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Archives";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Archives_default",
                "Archives/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
