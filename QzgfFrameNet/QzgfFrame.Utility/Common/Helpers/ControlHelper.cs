namespace QzgfFrame.Utility.Common.Helpers
{
    public class ControlHelper
    {
        public static string GetValuePropertyName(System.Web.UI.Control control)
        {
            if (control is System.Web.UI.WebControls.TextBox)
            {
                return "Text";
            }
            if (control is System.Web.UI.WebControls.ListControl)
            {
                return "SelectedValue";
            }
            return "Value";
        }

        public static string GetValue(System.Web.UI.Control control)
        {
            return ReflectionHelper.GetProperty(control, GetValuePropertyName(control)).ToStr();
        }
        public static void SetValue(System.Web.UI.Control control, string value)
        {
            ReflectionHelper.SetProperty(control, GetValuePropertyName(control), value);
        }
    }
}
