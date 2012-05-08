using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Archives.PortTypeManger.Models;

namespace QzgfFrame.Resources.EquipmentManger.Models
{
    public class EquipClie
    {
        public virtual string Id { get; set; }
        public virtual ResourceEquipment equipment { get; set; }
        public virtual string equipclies { get; set; }
        public virtual IList<ResourceClieEquip> ClieEquips { get; set; }
        public virtual string EquipTypeId { get; set; }
        public virtual string FactoryId { get; set; }
        public virtual IList<ArchivePortType> PortTypes { get; set; }
        
    }
}
