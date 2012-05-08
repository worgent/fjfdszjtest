using System.Data;
using System.Collections.Generic;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;

namespace QzgfFrame.Controllers.Resources.EquipmentManger

{
    public interface EquipmentFacadeEx
    {
        bool Save(EquipClie entity);
        bool Update(EquipClie entity);
        bool Delete(string id);
        bool DeleteFalse(string id, out bool DelFlag);
        EquipClie Get(string id);
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName);
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid);
    }
}
