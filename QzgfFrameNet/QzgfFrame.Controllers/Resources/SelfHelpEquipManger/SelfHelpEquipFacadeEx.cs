using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;

namespace QzgfFrame.Controllers.Resources.SelfHelpEquipManger
{
    public interface SelfHelpEquipFacadeEx
    {
        bool Save(SelfHelpEquip entity);
        bool Update(SelfHelpEquip entity);
        bool Delete(string id);
        bool DeleteFalse(string id, out bool DelFlag);
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
