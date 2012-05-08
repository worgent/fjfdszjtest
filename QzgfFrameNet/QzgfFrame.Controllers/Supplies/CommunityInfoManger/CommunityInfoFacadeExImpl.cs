using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.BuildWayManger.Domain;
using QzgfFrame.Archives.BuildWayManger.Models;
using QzgfFrame.Archives.CommunityTypeManger.Domain;
using QzgfFrame.Archives.CommunityTypeManger.Models;
using QzgfFrame.Supplies.CommunityInfoManger.Domain;
using QzgfFrame.Supplies.CommunityInfoManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Supplies.CommunityInfoManger
{
    public class CommunityInfoFacadeExImpl : CommunityInfoFacadeEx
    {

        private AccessWayFacade accessWayFacade { set; get; }
        private BuildWayFacade buildWayFacade { set; get; }
        private CommunityTypeFacade communityTypeFacade { set; get; }

        private CommunityInfoFacade communityInfoFacade { set; get; }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "小区编码")
            {
                procInfo += "文件中未找到'小区编码'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 2);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "小区名称")
            {
                procInfo += "文件中未找到'小区名称'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 3);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "小区类型")
            {
                procInfo += "文件中未找到'小区类型'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "建设方式")
            {
                procInfo += "文件中未找到'建设方式'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 5);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "接入方式")
            {
                procInfo += "文件中未找到'接入方式'列";
                isAllValid = false;
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
             value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str == ""||str.Length>20)
                procInfo += "小区编码不可为空或长度超限!!";
            else
            {
                SuppliesCommunityInfo CommunityInfo = communityInfoFacade.GetHql(str);
                if (CommunityInfo == null)
                {
                    value = myArray.GetValue(1, 2);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() == "" && str.Length > 50)
                    {
                        procInfo += "小区名称不可为空或长度大于50!!";
                        isAllValid = false;
                    }
                    value = myArray.GetValue(1, 3);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveCommunityType CommunityType = new ArchiveCommunityType();
                    if (str.Trim() != "")
                    {
                        CommunityType = communityTypeFacade.GetHql(str);
                        if (CommunityType == null)
                            procInfo += "该小区类型在系统中不存在!!";
                    }
                    else
                    {
                        procInfo += "小区类型不可为空!!";
                    }
                    value = myArray.GetValue(1, 4);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveBuildWay buildWay = new ArchiveBuildWay();
                    if (str.Trim() != "")
                    {
                        buildWay = buildWayFacade.GetHql(str);
                        if (buildWay == null)
                            procInfo += "该建设方式在系统中不存在!!";
                    }
                    else
                    {
                        procInfo += "建设方式不可为空!!";
                    }

                    value = myArray.GetValue(1, 5);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveAccessWay accessWay = new ArchiveAccessWay();
                    if (str.Trim() != "")
                    {
                        accessWay = accessWayFacade.GetHql(str);
                        if (accessWay == null)
                            procInfo += "该接入方式在系统中不存在!!";
                    }
                    else
                    {
                        procInfo += "接入方式不可为空!!";
                    }
                }
                else
                    procInfo += "小区信息在系统中已存在,该小区名称为"+CommunityInfo.CommunityName+"!!";
            }           

            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, string iFlag, out string procInfo, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            SuppliesCommunityInfo entity = new SuppliesCommunityInfo();
            string str = ""; object value = null;
             value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str == "" || str.Length > 20)
                procInfo += "小区编码不可为空或长度超限!!";
            else
            {
                entity = communityInfoFacade.GetHql(str);
                if (entity == null)
                {
                    entity = new SuppliesCommunityInfo();
                    entity.CommunityCode = str;
                    value = myArray.GetValue(1, 2);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() == "" && str.Length > 50)
                    {
                        procInfo += "小区名称不可为空或长度大于50!!";
                        isAllValid = false;
                    }
                    entity.CommunityName = str;
                    value = myArray.GetValue(1, 3);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveCommunityType CommunityType = new ArchiveCommunityType();
                    if (str.Trim() != "")
                    {
                        CommunityType = communityTypeFacade.GetHql(str);
                        if (CommunityType == null)
                        {
                            procInfo += "该小区类型在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.CommunityTypeId = CommunityType.Id;
                    }
                    else
                    {
                        procInfo += "小区类型不可为空!!";
                        isAllValid = false;
                    }
                    value = myArray.GetValue(1, 4);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveBuildWay buildWay = new ArchiveBuildWay();
                    if (str.Trim() != "")
                    {
                        buildWay = buildWayFacade.GetHql(str);
                        if (buildWay == null)
                        {
                            procInfo += "该建设方式在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.BuildWayId = buildWay.Id;
                    }
                    else
                    {
                        procInfo += "建设方式不可为空!!";
                        isAllValid = false;
                    }

                    value = myArray.GetValue(1, 5);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveAccessWay accessWay = new ArchiveAccessWay();
                    if (str.Trim() != "")
                    {
                        accessWay = accessWayFacade.GetHql(str);
                        if (accessWay == null)
                        {
                            procInfo += "该接入方式在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.AccessWayId = accessWay.Id;
                    }
                    else
                    {
                        procInfo += "接入方式不可为空!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "装维人员在系统中已存!!";
                    isAllValid = false;
                }
            }
            entity.CreateUserId = userid;
            if (isAllValid)
                isAllValid = communityInfoFacade.Save(entity, iFlag);
            return isAllValid;
        }
        public bool CheckExcelData(string strFileName, out string procInfo, out string reFileName)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn =5; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "E" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;
                    if (procInfo == "")
                        procInfo = "验证成功!!";
                }
                else if (i == 2 )
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    isAllValid = CheckBodyData(myArray, out myText);
                    if (myText == "")
                    {
                        myText = "验证成功!!";
                        m++;
                    }
                    else
                    {
                        isAllValid = false;
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/CommunityInfo");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 5; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "E" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;

                }
                else if (i == 2 )
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = LoadBodyData(myArray, i.ToString(), out myText,userid);
                    }
                    catch
                    {
                        isAllValid = false;
                    } 
                    if (isAllValid)
                    {
                        myText += "导入数据库成功";
                        m++;
                    }
                    else
                    {
                        myText += "导入数据库失败!!";
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/CommunityInfo");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }  
    }
}
