using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.IODetailManger.Models;

namespace QzgfFrame.Warehouse.IOListManger.Models
{
    public class IOList
    {
        public virtual string Id { get; set; }
        public virtual WarehouseIOList warehouseIOList { get; set; }
        public virtual IList<WarehouseIODetail> ioDetailList { get; set; }
        public virtual string ioDetails { get; set; }
    }
}
