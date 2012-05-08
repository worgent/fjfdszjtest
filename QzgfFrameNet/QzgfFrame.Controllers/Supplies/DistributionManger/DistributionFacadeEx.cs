using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.DistributionManger.Domain;
using QzgfFrame.Supplies.DistributionManger.Models;

namespace QzgfFrame.Controllers.Supplies.DistributionManger
{
    public interface DistributionFacadeEx
    {
        bool Save(Distribution entity);
        bool SaveYw(Distribution entity);
        bool Update(Distribution entity);
        bool UpdateYw(Distribution entity);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Confirm(Distribution entity);
        bool Invalid(string id);
    }
}
