using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.EquipComponentManger.Models;
using QzgfFrame.Resources.EquipComponentManger.Domain;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;

namespace QzgfFrame.Resources.SelfHelpEquipManger.Models
{
    public class SelfHelpEquip
    {
        public virtual string Id { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string Abbrevia { get; set; }
        public virtual string EquipTypeName { get; set; }
        public virtual string EquipModelName { get; set; }
        public virtual string EquipModelId { get; set; }
        public virtual string TermiId { get; set; }
        public virtual string UseNetName { get; set; }
        public virtual string NetAddress { get; set; }
        public virtual ResourceSelfHelpEquip selfHelpEquip { get; set; }
        public virtual IList<ResourceEquipComponent> equipComponent { get; set; }
        public virtual IList<ArchiveComponent> Components { get; set; }
        public virtual string equipComponents { get; set; }
        public virtual string EquipTypeId { get; set; }
        public virtual string FactoryId { get; set; }
    }
}
