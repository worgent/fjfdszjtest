using System.Collections.Generic;
using QzgfFrame.Resources.EquipFaultManger.Models;

namespace QzgfFrame.Controllers.Resources.EquipFaultManger

{
    public interface EquipFaultFacadeEx
    {
        bool Save(ResourceEquipFault entity);
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
