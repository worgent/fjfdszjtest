using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.CommunityManagerManger.Models;

namespace QzgfFrame.Controllers.Supplies.CommunityManagerManger
{
    public interface CommunityManagerFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
