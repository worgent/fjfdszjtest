using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Resources.LineEquipManger.Models;

namespace QzgfFrame.Resources.DedicateLineManger.Models
{
    public class DedicateLine
    {
        public virtual string Id { get; set; }
        public virtual string ClieId { get; set; }
        public virtual string ClieNo { get; set; }
        public virtual string ClieName { get; set; }
        public virtual string ZClieId { get; set; }
        public virtual string ZClieNo { get; set; }
        public virtual string ZClieName { get; set; }
        public virtual ResourceDedicateLine DediLine { get; set; }
        public virtual IList<ResourceFiberCore> FiberCores { get; set; }
        public virtual IList<ResourceNumberGroup> NumberGroups { get; set; }
        public virtual IList<ResourceOtherInfo> OtherInfos { get; set; }

        public virtual string NetWorkingMode { get; set; }
        public virtual string ZNetWorkingMode { get; set; }
        public virtual string ProductNo { get; set; }
        public virtual string CompanyName { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string BizTypeName { get; set; }
        public virtual string AssuranLeveName { get; set; }
        public virtual string SignalModelName { get; set; }
        public virtual short IsAccessLocalNet { get; set; }
        public virtual short IsAccessProviNet { get; set; }
        public virtual string forces { get; set; }
        public virtual string numbers { get; set; }
        public virtual string delArys { get; set; }
        public virtual string equips { get; set; }
        public virtual string editFlag { get; set; }
        public virtual string State { get; set; }
        public virtual string updatedate { get; set; }
        public virtual DateTime? createdate { get; set; }
        //A端
        public virtual IList<ResourceLineEquip> lineEquipList { get; set; }
        public virtual string lineEquips { get; set; }
        //Z端
        public virtual IList<ResourceLineEquip> ZlineEquipList { get; set; }
        public virtual string ZlineEquips { get; set; }
    }
}
