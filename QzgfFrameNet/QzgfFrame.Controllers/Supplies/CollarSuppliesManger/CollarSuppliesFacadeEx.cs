using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CollarSuppliesManger.Domain;
using QzgfFrame.Supplies.CollarSuppliesManger.Models;

namespace QzgfFrame.Controllers.Supplies.CollarSuppliesManger
{
    public interface CollarSuppliesFacadeEx
    {
        bool Save(CollarSupplies entity);
        bool Update(CollarSupplies entity);
        bool Delete(string id);
        bool Invalid(string id);
    }
}
