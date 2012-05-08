using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.RegisterDetailManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Models;

namespace QzgfFrame.Supplies.RegisterManger.Models
{
    public class Register
    {
        public virtual string Id { get; set; }
        public virtual SuppliesRegister suppliesRegister { get; set; }
        public virtual IList<SuppliesRegisterDetail> registerDetailList { get; set; }
        public virtual string registerDetails { get; set; }
        public virtual IList<ArchiveSuppliesType> suppliesTypes { get; set; }
        public virtual string MaintainerId { get; set; }
        public virtual string SaleDepartmentId { get; set; }
        public virtual IList<regMapList> regMapLists { get; set; }
    }
}
