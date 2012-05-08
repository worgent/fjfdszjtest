namespace QzgfFrame.Utility.Core.QzgfException
{
    public class DemoHisException : System.Exception
    {
        public DemoHisException(string message) : base(message) { }
        public DemoHisException(string message, System.Exception inner)
            : base(message, inner) { }
    }
}
