using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Supervision
{
    public class SupervisionAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Supervision";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Supervision_default",
                "Supervision/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}