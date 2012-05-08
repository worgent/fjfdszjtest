using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.PersonnelManger.Domain;
using QzgfFrame.Resources.PersonnelManger.Models;

namespace QzgfFrame.Controllers.Resources.PersonnelManger
{
    public interface PersonnelFacadeEx
    {
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
        string[] GetDropDownList(string hql);
    }
}
