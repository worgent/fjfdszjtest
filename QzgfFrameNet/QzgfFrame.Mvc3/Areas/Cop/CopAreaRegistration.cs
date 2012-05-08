using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Cop
{
    public class CopAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Cop";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Cop_default",
                "Cop/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
