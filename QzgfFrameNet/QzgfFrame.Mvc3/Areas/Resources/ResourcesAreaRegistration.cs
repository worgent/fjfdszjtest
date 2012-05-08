using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Resources
{
    public class ResourcesAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Resources";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Resources_default",
                "Resources/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
