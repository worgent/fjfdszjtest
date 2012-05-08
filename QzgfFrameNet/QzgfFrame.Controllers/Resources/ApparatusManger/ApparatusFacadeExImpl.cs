using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Resources.ApparatusManger.Domain;
using QzgfFrame.Resources.ApparatusManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.UseManger.Models;
using QzgfFrame.Archives.UseManger.Domain;
using QzgfFrame.Archives.MeterStateManger.Models;
using QzgfFrame.Archives.MeterStateManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Resources.ApparatusManger
{
    public class ApparatusFacadeExImpl : ApparatusFacadeEx
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private UseFacade useFacade { set; get; }
        private MeterStateFacade meterStateFacade { set; get; }
        private StagnationFacade stagnationFacade { set; get; }
        private MaintainSpecialtyFacade maintainSpecialtyFacade { set; get; }

        private ApparatusFacade apparatusFacade { set; get; }

        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 15)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "二维码")
                {
                    procInfo += "文件中未找到'二维码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属驻点")
                {
                    procInfo += "文件中未找到'所属驻点'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属地市 *")
                {
                    procInfo += "文件中未找到'所属地市'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属县区 *")
                {
                    procInfo += "文件中未找到'所属县区'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "维护专业 *")
                {
                    procInfo += "文件中未找到'维护专业'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "序列号 *")
                {
                    procInfo += "文件中未找到'序列号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "用 途 *")
                {
                    procInfo += "文件中未找到'用 途'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "仪表厂商 *")
                {
                    procInfo += "文件中未找到'仪表厂商'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "仪表名称 *")
                {
                    procInfo += "文件中未找到'仪表名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "仪表型号 *")
                {
                    procInfo += "文件中未找到'仪表型号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "状 态 *")
                {
                    procInfo += "文件中未找到'状 态'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备 注'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属网格")
                {
                    procInfo += "文件中未找到'所属网格'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 14)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "序列号不可为空!!";
                }
                else
                {
                    ResourceApparatus Apparatus = apparatusFacade.GetHql(str);
                    if (Apparatus == null)
                    {

                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCompany company = new ArchiveCompany();
                        if (str.Trim() != "")
                        {
                            company = companyFacade.GetHql(str);
                            if (company == null)
                                procInfo += "该所属公司在系统中不存在!!";
                        }
                        value = myArray.GetValue(1, 4);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict city = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            city = districtFacade.GetHql(str);
                            if (city == null)
                                procInfo += "该所属地市在系统中不存在!!";
                        }
                        else
                            procInfo += "所属地市不可为空!!";
                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict district = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            district = districtFacade.GetHql(str);
                            if (district == null)
                                procInfo += "该所属县区在系统中不存在!!";
                        }

                        value = myArray.GetValue(1, 3);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveStagnation stagnation = new ArchiveStagnation();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                stagnation = stagnationFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (stagnation == null)
                                    procInfo += "该所属驻点不在该所属区县，所属公司内!!";
                            }
                            else
                                procInfo += "请先填写区县跟公司,再能检查驻点!!";
                            
                        } 
                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMaintainSpecialty maintainSpecialty = new ArchiveMaintainSpecialty();
                        if (str.Trim() != "")
                        {
                            maintainSpecialty = maintainSpecialtyFacade.GetHql(str);
                            if (maintainSpecialty == null)
                                procInfo += "该维护专业在系统中不存在!!";
                        }
                        else
                            procInfo += "维护专业不可为空!!";
                        /**
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "序列号不可为空!!";
                }**/
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveUse use = new ArchiveUse();
                        if (str.Trim() != "")
                        {
                            use = useFacade.GetHql(str);
                            if (use == null)
                                procInfo += "该用途在系统中不存在!!";
                        }
                        else
                            procInfo += "用途不可为空!!";
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表厂商不可为空!!";
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表名称不可为空!!";
                        }
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表型号不可为空!!";
                        }
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMeterState meterState = new ArchiveMeterState();
                        if (str.Trim() != "")
                        {
                            meterState = meterStateFacade.GetHql(str);
                            if (meterState == null)
                                procInfo += "该状态在系统中不存在!!";
                        }
                        else
                            procInfo += "状态不可为空!!";
                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveGrid grid = new ArchiveGrid();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                grid = gridFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (grid == null)
                                    procInfo += "该所属网格不在该所属区县，所属公司内!!";
                            }
                            else
                                procInfo += "请先填写区县跟公司,再能检查网格!!";
                            
                        }
                    }
                    else
                        procInfo += "该仪器仪表在系统中已存在!!";
                }
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid,string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 14)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceApparatus entity = new ResourceApparatus();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "序列号不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    entity = apparatusFacade.GetHql(str);                
                    if (entity == null)
                    {
                        entity = new ResourceApparatus();
                        value = myArray.GetValue(1, 1);
                        entity.TwoDimensionalCode = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCompany company = new ArchiveCompany();
                        if (str.Trim() != "")
                        {
                            company = companyFacade.GetHql(str);
                            if (company == null)
                            {
                                procInfo += "该所属公司在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.CompanyId = company.Id;
                        }                       
                        value = myArray.GetValue(1, 4);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict city = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            city = districtFacade.GetHql(str);
                            if (city == null)
                            {
                                procInfo += "该所属地市在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.CityId = city.Id;
                        }
                        else
                        {
                            procInfo += "所属地市不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict district = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            district = districtFacade.GetHql(str);
                            if (district == null)
                            {
                                procInfo += "该所属县区在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.DistrictId = district.Id;
                        }
                        value = myArray.GetValue(1, 3);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveStagnation stagnation = new ArchiveStagnation();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                stagnation = stagnationFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (stagnation == null)
                                {
                                    procInfo += "该所属驻点不在该所属区县，所属公司内!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.StagnationId = stagnation.Id;
                            }
                            else
                            {
                                procInfo += "请先填写区县跟公司,再能检查驻点!!";
                                isAllValid = false;
                            }
                        }
                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMaintainSpecialty maintainSpecialty = new ArchiveMaintainSpecialty();
                        if (str.Trim() != "")
                        {
                            maintainSpecialty = maintainSpecialtyFacade.GetHql(str);
                            if (maintainSpecialty == null)
                            {
                                procInfo += "该维护专业在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.MaintainSpecialtyId = maintainSpecialty.Id;
                        }
                        else
                        {
                            procInfo += "维护专业不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length >50)
                        {
                            procInfo += "序列号长度大于50!!";
                            isAllValid = false;
                        }
                        entity.SeqNo = str;
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveUse use = new ArchiveUse();
                        if (str.Trim() != "")
                        {
                            use = useFacade.GetHql(str);
                            if (use == null)
                            {
                                procInfo += "该用途在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.UseId = use.Id;
                        }
                        else
                        {
                            procInfo += "用途不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表厂商不可为空!!";
                            isAllValid = false;
                        }
                        entity.ManuFacturer = str;
                        
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表名称不可为空!!";
                            isAllValid = false;
                        }
                        entity.MeterName = str;

                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "仪表型号不可为空!!";
                            isAllValid = false;
                        }
                        entity.MeterModel = str;

                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMeterState meterState = new ArchiveMeterState();
                        if (str.Trim() != "")
                        {
                            meterState = meterStateFacade.GetHql(str);
                            if (meterState == null)
                            {
                                procInfo += "该状态在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.MeterStateId = meterState.Id;
                        }
                        else
                        {
                            procInfo += "状态不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 13);
                        entity.Remark = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveGrid grid = new ArchiveGrid();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                grid = gridFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (grid == null)
                                {
                                    procInfo += "该所属网格不在该所属区县，所属公司内!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.GridId = grid.Id;
                            }
                            else
                            {
                                procInfo += "请先填写区县跟公司,再能检查网格!!";
                                isAllValid = false;
                            }
                        }
                    }
                    else
                    {
                        procInfo += "该仪器仪表在系统中已存在!!";
                        isAllValid = false;
                    }
                }
                entity.CreateUserId = userid;
                if (isAllValid)
                    isAllValid = apparatusFacade.Save(entity, iFlag);
            }
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
            int sheetColumn = 14; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "N" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Size = 12;
                    rng.Font.Color = 255;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;
                }
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = CheckBodyData(myArray, out myText);
                    }
                    catch
                    {
                        isAllValid = false;
                    } 
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
                    //procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/Apparatus");
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
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName,string userid)
        {
            procInfo = "";
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 14; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "N" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.Font.Color = 255;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;

                }
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = LoadBodyData(myArray, out myText, userid, i.ToString());
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
                    //procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/Apparatus");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
           // GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }  
    }
}
