using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Warehouse.IOListManger.Domain;
using QzgfFrame.Warehouse.IOListManger.Models;

namespace QzgfFrame.Controllers.Warehouse.IOListManger
{
    public interface IOListFacadeEx
    {
        bool Save(IOList entity);
        bool Update(IOList entity);
        bool SaveTrac(IOList entity);
        bool UpdateTrac(IOList entity);
    }
}
