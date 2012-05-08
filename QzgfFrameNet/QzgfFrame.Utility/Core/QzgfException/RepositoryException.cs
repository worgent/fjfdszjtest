using System;

namespace QzgfFrame.Utility.Core.QzgfException
{
    [Serializable]
    public class RepositoryException : DemoHisException
    {
        public RepositoryException(string message) : base(message) { }
        public RepositoryException(string message, System.Exception inner)
            : base(message, inner) { }
    }
}
