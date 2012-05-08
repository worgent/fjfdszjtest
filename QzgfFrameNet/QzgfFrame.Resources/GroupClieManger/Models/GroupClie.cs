using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Resources.GroupClieManger.Models
{
    public class GroupClie
    {
        public virtual string Id { get; set; }
        public virtual string ClieNo { get; set; }
        public virtual string ClieName { get; set; }
        public virtual string CityName { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string CompanyName { get; set; }
        public virtual string GridName { get; set; }
        public virtual string AssuranLeveName { get; set; }
        public virtual string StarLeveName { get; set; }
        public virtual string ScaleGradeName { get; set; }
    }
}
