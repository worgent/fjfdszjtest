using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.OneSevenZeroManger.Models;

namespace QzgfFrame.Controllers.Resources.DedicateLineManger

{
    public interface DedicateLineFacadeEx
    {
         bool Save(DedicateLine entity, HttpFileCollectionBase uploadfiles, string savePath);
         bool SaveOSZL(OneSevenZeroLine entity, HttpFileCollectionBase uploadfiles, string savePath, string userid);
         bool Update(DedicateLine entity, HttpFileCollectionBase uploadfiles, string savePath, out string filename);
         bool Delete(string id,out string filename);
         bool CheckExcelData(string strFileName, out string procInfo, string bizType, out string reFileName);
         bool SaveExcelData(string strFileName, out string procInfo, string bizType, out string reFileName, string userid);
         void DeleteFile(string fileNames, string savePath);
         bool DeleteFalse(string id, out string filename, string editFlag, out bool DelFlag);
    }
}
