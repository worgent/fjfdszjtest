namespace QzgfFrame.Utility.Core.QzgfException
{
    /// <summary>
    /// 为空验证异常
    /// </summary>
    public class NotNullException: ValidationException
    {
        public NotNullException() : base("对象或值不能为空") { }
    }
}
