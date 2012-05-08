using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Archives.GridManger.Models
{
    public class Grid
    {
        public virtual string Id { get; set; }
        public virtual string DistrictName { get; set; }
        public virtual string CompanyName { get; set; }
        public virtual string CityName { get; set; }
        public virtual string GridCode { get; set; }
        public virtual string GridName { get; set; }
        public virtual string Partners { get; set; }
        public virtual string Nature { get; set; }
        public virtual string GridArea { get; set; }
    }
}
