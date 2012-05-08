using System;
using System.ComponentModel;
using System.Web.UI;

namespace QzgfFrame.Utility.UI
{ 
    [DefaultProperty("PostScript")]
    [DefaultEvent("Click")]
    [ToolboxData("<{0}:CallPostControl runat=server></{0}:CallPostControl>")]
    public class CallPostControl : System.Web.UI.Control , IPostBackEventHandler
    {
        [Bindable(true)]
        [Category("Appearance")]
        [DefaultValue("f_post")]
        [Localizable(true)]
        public string PostScript
        {
            get
            {
                string s = ViewState["PostScript"] as string;
                return ((s == null) ? "f_post" : s);
            }

            set
            {
                ViewState["PostScript"] = value;
            }
        }
        protected override void Render(HtmlTextWriter writer)
        {
              if (DesignMode)
            {
                writer.Write("隐藏控件，可回发服务器,只需要在前端调用Javascript函数:" + PostScript + "。请双击我实现！(PS:这个函数名可以通过PostScript属性改变)");
            }
            else
            {
                writer.Write(@"<script type='text/javascript' >function "+PostScript+@"(){
" + Page.ClientScript.GetPostBackEventReference(this, "") + @"
}
</script>
");
            } 
        }
        
        public event EventHandler Click;
        protected virtual void OnClick(EventArgs e)
        {
            if (Click != null)
            {
                Click(this, e);
            }
        }

        #region IPostBackEventHandler 成员

        public void RaisePostBackEvent(string eventArgument)
        {
            OnClick(EventArgs.Empty);
        }

        #endregion
    }
}
