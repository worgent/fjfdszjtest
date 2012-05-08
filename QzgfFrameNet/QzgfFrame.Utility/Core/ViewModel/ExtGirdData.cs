using System.Collections.Generic;

namespace QzgfFrame.Utility.Core.ViewModel
{
    public class ExtGirdData<T>
    {
        public long total { get; set; }
        public IList<T> data { get; set; }
    }
}
