using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.ApparatusManger.Domain;
using QzgfFrame.Resources.ApparatusManger.Models;

namespace QzgfFrame.Controllers.Resources.ApparatusManger
{
    public interface ApparatusFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName,string userid);
    }
}
