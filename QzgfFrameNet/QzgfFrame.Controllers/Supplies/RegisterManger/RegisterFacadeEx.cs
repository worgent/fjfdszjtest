using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.RegisterManger.Domain;
using QzgfFrame.Supplies.RegisterManger.Models;

namespace QzgfFrame.Controllers.Supplies.RegisterManger
{
    public interface RegisterFacadeEx
    {
        bool Save(Register entity);
        bool Update(Register entity);
        bool Delete(string id);
        bool DeleteFalse(string id);
        bool Invalid(string id);
    }
}
