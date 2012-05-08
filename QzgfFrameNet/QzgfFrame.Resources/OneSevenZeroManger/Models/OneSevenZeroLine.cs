using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Models;

namespace QzgfFrame.Resources.OneSevenZeroManger.Models
{
    public class OneSevenZeroLine
    {
        public virtual DedicateLine Line { get; set; }
        public virtual ResourceOneSevenZero OneSevenZero { get; set; }
        public virtual string equips { get; set; }
    }
}
