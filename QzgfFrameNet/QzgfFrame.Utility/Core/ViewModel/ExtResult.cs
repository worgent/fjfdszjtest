namespace QzgfFrame.Utility.Core.ViewModel
{
    /// <summary>
    /// EXT提交后台后的返回类型
    /// </summary>
    public class ExtResult
    {
        public bool success { get; set; }
        public string msg { get; set; }
        /// <summary>
        /// 一般只有在表格内编辑模式的时候才用赋此值
        /// 为处理表格内编辑模式（RowEditor）游离对象时返回Id
        /// </summary>
        public string id { get; set; }
    }
}
