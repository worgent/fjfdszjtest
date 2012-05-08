using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supplies.CommunityInfoManger.Domain;
using QzgfFrame.Supplies.CommunityInfoManger.Models;

namespace QzgfFrame.Controllers.Supplies.CommunityInfoManger
{
    public interface CommunityInfoFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
