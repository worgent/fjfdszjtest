namespace QzgfFrame.Utility.Core.QzgfException
{
    /// <summary>
    /// 重复验证异常
    /// </summary>
    public class ExistException : ValidationException
    {
        public ExistException() : base("输入的值在一定范围内已存在") { }

        public ExistException(string fieldName) : base(fieldName + ":输入的值在一定范围内已存在") { }
    }
}
