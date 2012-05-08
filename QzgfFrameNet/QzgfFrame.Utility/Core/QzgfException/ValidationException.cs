namespace QzgfFrame.Utility.Core.QzgfException
{
    /// <summary>
    /// 逻辑判断错误
    /// </summary>
    public class ValidationException : DemoHisException
    {
        public ValidationException(string message) : base(message) { }
    }
}
