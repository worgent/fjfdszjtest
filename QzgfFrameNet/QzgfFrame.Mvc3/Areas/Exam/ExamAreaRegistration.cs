using System.Web.Mvc;

namespace QzgfFrame.Mvc3.Areas.Exam
{
    public class ExamAreaRegistration : AreaRegistration
    {
        public override string AreaName
        {
            get
            {
                return "Exam";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context)
        {
            context.MapRoute(
                "Exam_default",
                "Exam/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
            context.MapRoute(
               "Exam_Subject",
               "Exam/{controller}/{action}/{id}/{typeid}",
               new { controller = "Subject", action = "Delete", id = UrlParameter.Optional, typeid = UrlParameter.Optional }
           );
        }
    }
}
