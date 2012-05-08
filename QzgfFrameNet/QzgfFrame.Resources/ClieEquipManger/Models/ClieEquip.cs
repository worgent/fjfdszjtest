using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.ClieEquipManger.Models
{
    public class ClieEquip
    {
        public virtual string ClieId { get; set; }
        public virtual string ClieNo { get; set; }
        public virtual string ClieName { get; set; }
        public virtual string OccupySlot { get; set; }
        public virtual string BoardType { get; set; }
        public virtual string OccupyPort { get; set; }
        public virtual string PortTypeId { get; set; }
        public virtual string PortTypeName { get; set; }       
    }
}
