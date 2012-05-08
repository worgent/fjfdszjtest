using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;

namespace QzgfFrame.Controllers.Resources.GroupClieManger
{
    public interface GroupClieFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
