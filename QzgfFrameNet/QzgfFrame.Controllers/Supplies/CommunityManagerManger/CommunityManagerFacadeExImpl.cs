using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Supplies.CommunityManagerManger
{
    public class CommunityManagerFacadeExImpl : CommunityManagerFacadeEx
    {

        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }

        private CommunityManagerFacade communityManagerFacade { set; get; }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "姓名")
            {
                procInfo += "文件中未找到'姓名'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 2);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "联系方式")
            {
                procInfo += "文件中未找到'联系方式'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 3);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "身份证号")
            {
                procInfo += "文件中未找到'身份证号'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "所属县市")
            {
                procInfo += "文件中未找到'所属县市'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 5);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "维护单位")
            {
                procInfo += "文件中未找到'维护单位'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 6);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "营业部")
            {
                procInfo += "文件中未找到'营业部'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 7);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "入职时间")
            {
                procInfo += "文件中未找到'入职时间'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 8);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "兼职/专职")
            {
                procInfo += "文件中未找到'兼职/专职'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 9);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "在职/离职")
            {
                procInfo += "文件中未找到'在职/离职'列";
                isAllValid = false;
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                    procInfo += "身份证不可为空!!";
                else
                {
                    SuppliesCommunityManager CommunityManager = communityManagerFacade.GetHql(str);
                    if (CommunityManager == null)
                    {
                        value = myArray.GetValue(1, 1);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "" && str.Length > 20)
                        {
                            procInfo += "姓名不可为空或长度大于20!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                            {
                                procInfo += "联系方式格式不对,只能是数字!!";
                            }
                        }
                        else
                        {
                            procInfo += "联系方式不可为空!!";
                        }

                        value = myArray.GetValue(1, 3);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.CardCheck(str))
                            {
                                procInfo += "身份证号格式不对,只能等于15位数字或18位数字!!";
                            }
                        }

                        value = myArray.GetValue(1, 4);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict district = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            district = districtFacade.GetHql(str);
                            if (district == null)
                                procInfo += "该所属县市在系统中不存在!!";
                        }
                        else
                        {
                            procInfo += "所属县市不可为空!!";
                        }
                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCompany company = new ArchiveCompany();
                        if (str.Trim() != "")
                        {
                            company = companyFacade.GetHql(str);
                            if (company == null)
                                procInfo += "该维护单位在系统中不存在!!";
                        }
                        else
                        {
                            procInfo += "维护单位不可为空!!";
                        }

                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveSaleDepartment saleDepartment = new ArchiveSaleDepartment();
                        if (str.Trim() != "")
                        {
                            saleDepartment = saleDepartmentFacade.GetHql(str);
                            if (company == null)
                                procInfo += "该营业部在系统中不存在!!";
                        }
                        else
                        {
                            procInfo += "营业部不可为空!!";
                        }

                        //工作时间可为空
                        value = myArray.GetValue(1, 7);
                        DateTime? EntryDate = ExtensionMethods.ToDateOANull(value);
                        if (EntryDate == null)
                        {
                            procInfo += "入职时间不可为空或格式错误!!";
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (str.Trim() != "兼职" && str.Trim() != "专职")
                                procInfo += "兼职/专职格式错误!!";
                        }
                        else
                            procInfo += "兼职/专职不可为空!!";
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (str.Trim() != "在职" && str.Trim() != "离职")
                                procInfo += "在职/离职格式错误!!";
                        }
                        else
                            procInfo += "在职/离职不可为空!!";

                    }
                    else
                        procInfo += "该社区经理在系统中已存在!!";
                }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string iFlag, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            SuppliesCommunityManager entity = new SuppliesCommunityManager();
            string str = ""; object value = null;
            value = myArray.GetValue(1, 3);
            str = ExtensionMethods.ToStr(value);
            if (str == "")
                procInfo += "身份证不可为空!!";
            else
            {
                entity = communityManagerFacade.GetHql(str);
                if (entity == null)
                {
                    entity = new SuppliesCommunityManager();
                    value = myArray.GetValue(1, 1);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() == "" && str.Length>20)
                    {
                        procInfo += "姓名不可为空或长度大于20!!";
                        isAllValid = false;
                    }
                    entity.FullName = str;
                    value = myArray.GetValue(1, 2);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() != "")
                    {
                        if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                        {
                            procInfo += "联系方式格式不对,只能是数字!!";
                            isAllValid = false;
                        }
                    }
                    else
                    {
                        procInfo += "联系方式不可为空!!";
                        isAllValid = false;
                    }
                    entity.LinkTel = str;

                    value = myArray.GetValue(1, 3);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() != "")
                    {
                        if (!ExtensionMethods.CardCheck(str))
                        {
                            procInfo += "身份证号格式不对,只能等于15位数字或18位数字!!";
                            isAllValid = false;
                        }
                    }
                    else
                    {
                        procInfo += "身份证号不可为空!!";
                        isAllValid = false;
                    }
                    entity.IDCardNumber = str;
                    value = myArray.GetValue(1, 4);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveDistrict district = new ArchiveDistrict();
                    if (str.Trim() != "")
                    {
                        district = districtFacade.GetHql(str);
                        if (district == null)
                        {
                            procInfo += "该所属县市在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.DistrictId = district.Id;
                    }
                    else
                    {
                        procInfo += "所属县市不可为空!!";
                        isAllValid = false;
                    }
                    value = myArray.GetValue(1, 5);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveCompany company = new ArchiveCompany();
                    if (str.Trim() != "")
                    {
                        company = companyFacade.GetHql(str);
                        if (company == null)
                        {
                            procInfo += "该维护单位在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.CompanyId = company.Id;
                    }
                    else
                    {
                        procInfo += "维护单位不可为空!!";
                        isAllValid = false;
                    }

                    value = myArray.GetValue(1, 6);
                    str = ExtensionMethods.ToStr(value);
                    ArchiveSaleDepartment saleDepartment = new ArchiveSaleDepartment();
                    if (str.Trim() != "")
                    {
                        saleDepartment = saleDepartmentFacade.GetHql(str);
                        if (saleDepartment == null)
                        {
                            procInfo += "该营业部在系统中不存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.SaleDepartmentId = saleDepartment.Id;
                    }
                    else
                    {
                        procInfo += "营业部不可为空!!";
                        isAllValid = false;
                    }
                    //工作时间可为空
                    value = myArray.GetValue(1, 7);
                    DateTime? EntryDate = ExtensionMethods.ToDateOANull(value);
                    if (EntryDate == null)
                    {
                        procInfo += "入职时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                    entity.EntryDate = EntryDate;

                    value = myArray.GetValue(1, 8);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() != "")
                    {
                        if (str.Trim() != "兼职" && str.Trim() != "专职")
                        {
                            procInfo += "兼职/专职格式错误!!";
                            isAllValid = false;
                        }
                        else
                            entity.IsFullTime = str != "专职" ? short.Parse("1") : short.Parse("0");
                    }
                    else
                    {
                        procInfo += "兼职/专职不可为空!!";
                        isAllValid = false;
                    }

                    value = myArray.GetValue(1, 9);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() != "")
                    {
                        if (str.Trim() != "在职" && str.Trim() != "离职")
                        {
                            procInfo += "在职/离职格式错误!!";
                            isAllValid = false;
                        }
                        else
                            entity.IsFullTime = str != "在职" ? short.Parse("1") : short.Parse("0");
                    }
                    else
                    {
                        procInfo += "在职/离职不可为空!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "该社区经理在系统中已存在!!";
                    isAllValid = false;
                }
            }
            entity.CreateUserId = userid;
            if (isAllValid)
                isAllValid = communityManagerFacade.Save(entity, iFlag);
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
            int sheetColumn = 9; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "I" + i.ToString());
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
                else if (i == 2)
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
                        myText += "验证失败!!";
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
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/CommunityManager");
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
            int sheetColumn = 9; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "I" + i.ToString());
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
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = LoadBodyData(myArray, out myText, i.ToString(), userid);
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
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/CommunityManager");
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
