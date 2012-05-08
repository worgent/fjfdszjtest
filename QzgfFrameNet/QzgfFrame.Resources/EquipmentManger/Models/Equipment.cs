using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.EquipmentManger.Models
{
    public class Equipment
    {
        public virtual string Id { get; set; }
        public virtual string EquipName { get; set; }
        public virtual string Abbrevia { get; set; }
        public virtual string EquipTypeName { get; set; }
        public virtual string EquipModelName { get; set; }
        public virtual short Position { get; set; }
        public virtual DateTime? StartDatetime { get; set; }
        public virtual string ClieNames { get; set; }
        public virtual string State { get; set; }
    }
}
