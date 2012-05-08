using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace QzgfFrame.Archives.DistrictManger.Models
{
    public class DistrictCity
    {
        public virtual string DistrictId { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string CityId { get; set; }
        public virtual string CityName { get; set; }
    }
}
