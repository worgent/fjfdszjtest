using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.EquipFaultManger.Models
{
    public class EquipFault
    {
        public virtual string Id { get; set; }
        public virtual string UseNetName { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string TermiId { get; set; }
        public virtual string Abbrevia { get; set; }
        public virtual short IsOverInsuran { get; set; }
        public virtual string BreakDownDate { get; set; }
        public virtual string BreakDownTime { get; set; }
        public virtual string TroubleShooter { get; set; }
        public virtual string HandleTime { get; set; }
        public virtual short IsReplace { get; set; }
    }
}
