using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.DistributionDetailManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Models;

namespace QzgfFrame.Supplies.DistributionManger.Models
{
    public class Distribution
    {
        public virtual string Id { get; set; }
        public virtual SuppliesDistribution suppliesDistribution { get; set; }
        public virtual IList<SuppliesDistributionDetail> distributionDetailList { get; set; }
        public virtual string distributionDetails { get; set; }
        public virtual IList<ArchiveSuppliesType> suppliesTypes { get; set; }
        public virtual string OrgIds { get; set; }
        public virtual IList<STMapList> stMapLists { get; set; }
    }
}
