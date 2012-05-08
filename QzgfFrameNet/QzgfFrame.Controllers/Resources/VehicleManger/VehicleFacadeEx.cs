using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.VehicleManger.Domain;
using QzgfFrame.Resources.VehicleManger.Models;

namespace QzgfFrame.Controllers.Resources.VehicleManger
{
    public interface VehicleFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
