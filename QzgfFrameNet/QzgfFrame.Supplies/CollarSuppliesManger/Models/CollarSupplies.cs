using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Models;

namespace QzgfFrame.Supplies.CollarSuppliesManger.Models
{
    public class CollarSupplies
    {
        public virtual string Id { get; set; }
        public virtual SuppliesCollarSupplies suppliesCollarSupplies { get; set; }
        public virtual IList<SuppliesCollarSuppliesDetail> SuppliesDetailList { get; set; }
        public virtual string collarSuppliesDetails { get; set; }
        public virtual IList<ArchiveSuppliesType> suppliesTypes { get; set; }
        public virtual string suppliesTypeIds { get; set; }
        public virtual IList<SuppliesCollar> CollarList { get; set; }
        public virtual string Collars { get; set; }
    }
}
