using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Supplies
{
    public class SuppliesAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Supplies";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Supplies_default",
                "Supplies/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
