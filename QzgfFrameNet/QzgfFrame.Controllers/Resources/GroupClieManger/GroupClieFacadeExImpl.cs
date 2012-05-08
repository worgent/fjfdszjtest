using System;
using System.IO;
using System.Web;
using System.Text;
using System.Collections.Generic;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.ClieScaleGradeManger.Models;
using QzgfFrame.Archives.ClieScaleGradeManger.Domain;
using QzgfFrame.Archives.ClieServStarLeveManger.Models;
using QzgfFrame.Archives.ClieServStarLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;


namespace QzgfFrame.Controllers.Resources.GroupClieManger
{
    public class GroupClieFacadeExImpl : GroupClieFacadeEx
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private ClieScaleGradeFacade clieScaleGradeFacade { set; get; }
        private ClieServStarLeveFacade clieServStarLeveFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }

        public bool CheckExcelData(string strFileName, out string procInfo, out string reFileName)
        {
            procInfo = ""; reFileName = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];
            
            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng =null;string myValue="";
            int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "N" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                //string[] strArray = ConvertArrayToString(myArray);

                if (i == 1)
                {
                    rng = (Excel.Range)workSheet.Cells[i, 1];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户编号")
                    {
                        procInfo += "文件中未找到'客户编号'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 2];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "归属县")
                    {
                        procInfo += "文件中未找到'归属县'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 3];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户名称")
                    {
                        procInfo += "文件中未找到'客户名称'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 4];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户地址")
                    {
                        procInfo += "文件中未找到'客户地址'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 5];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "维护单位")
                    {
                        procInfo += "文件中未找到'维护单位'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 6];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "所属网格名称")
                    {
                        procInfo += "文件中未找到'所属网格名称'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 7];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户级别")
                    {
                        procInfo += "文件中未找到'客户级别'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 8];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户服务级别")
                    {
                        procInfo += "文件中未找到'客户服务级别'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 9];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "经度")
                    {
                        procInfo += "文件中未找到'经度'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 10];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "纬度")
                    {
                        procInfo += "文件中未找到'纬度'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 11];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户经理")
                    {
                        procInfo += "文件中未找到'客户经理'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 12];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户经理联系电话")
                    {
                        procInfo += "文件中未找到'客户经理联系电话'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 13];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户业务联系人")
                    {
                        procInfo += "文件中未找到'客户业务联系人'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 14];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户业务联系电话")
                    {
                        procInfo += "文件中未找到'客户业务联系电话'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 15];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color=0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = "验证结果";

                }
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    rng = (Excel.Range)workSheet.Cells[i, 1];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    string myText = "";
                    if (myValue == "")
                    {
                        myText = "客户编号不可为空!!";
                    }
                    else
                    {
                        ResourceGroupClie clie = groupClieFacade.GetHql(myValue);
                        if (clie == null)
                        {
                            rng = (Excel.Range)workSheet.Cells[i, 2];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveDistrict District = new ArchiveDistrict();
                            if (myValue != "")
                            {
                                District = districtFacade.GetHql(myValue);
                                if (District == null)
                                    myText = "该区县在系统中不存在!!";
                            }
                            else
                                myText = "区县不可为空!!";
                            rng = (Excel.Range)workSheet.Cells[i, 3];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            if (myValue.Trim() != "")
                            {
                                string Hql = " ClieName = '" + myValue.Trim() + "' and (DelFlag!=1 or DelFlag is null)";
                                ResourceGroupClie cliename = groupClieFacade.GetStrHql(Hql);
                                if (cliename != null)
                                {
                                    myText = "客户名称已在系统中存在!!";
                                }
                            }
                            else
                            {
                                myText = "客户名称不可为空!!";
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 4];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            rng = (Excel.Range)workSheet.Cells[i, 5];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveCompany company = new ArchiveCompany();
                            if (myValue != "")
                            {
                                company = companyFacade.GetHql(myValue);
                                if (company == null)
                                    myText = "该维护单位在系统中不存在!!";
                            }
                            else
                                myText = "维护单位不可为空!!";
                            rng = (Excel.Range)workSheet.Cells[i, 6];
                            myValue = ExtensionMethods.ToStr(rng.Value2);

                            if (myValue != "")
                            {
                                string grids = "";
                                string[] gridAry = myValue.Split(';');
                                if (gridAry.Length <= 1)
                                    gridAry = myValue.Split('；');
                                for (int j = 0; j < gridAry.Length; j++)
                                {
                                    ArchiveGrid grid = new ArchiveGrid();
                                    grid = gridFacade.GetHql(gridAry[j]);
                                    grids += grid.Id + ";";
                                }
                                if (grids == "")
                                    myText = "该所属网格名称在系统中不存在!!";
                            }
                            else
                                myText = "所属网格名称不可为空!!";
                            
                            rng = (Excel.Range)workSheet.Cells[i, 7];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveClieServStarLeve servStarLeve = new ArchiveClieServStarLeve();
                            if (myValue != "")
                            {
                                servStarLeve = clieServStarLeveFacade.GetHql(myValue);
                                if (servStarLeve == null)
                                    myText += "该客户级别在系统中不存在!!";
                            }
                            else
                            {
                                myText += "客户级别不可为空!!";
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 8];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveClieScaleGrade ScaleGrade = new ArchiveClieScaleGrade();
                            if (myValue != "")
                            {
                                ScaleGrade = clieScaleGradeFacade.GetHql(myValue);
                                if (ScaleGrade == null)
                                    myText = "该客户服务级别在系统中不存在!!";
                            }
                            else
                                myText = "客户服务级别不可为空!!";

                            rng = (Excel.Range)workSheet.Cells[i, 12];
                            string str = ExtensionMethods.ToStr(rng.Value2);
                            if (str != "")
                            {
                                if (!ExtensionMethods.IsNumeric(str)||str.Length>15)
                                {
                                    myText += "客户联系电话格式不对!!";
                                }
                            }
                        }
                        else
                            myText = "该客户已在系统存在,对应的客户名称为" + clie.ClieName + "!!";
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
                    rng = (Excel.Range)workSheet.Cells[i, 15];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow-2) + "条，成功" + m + "条,未成功" + (sheetRow -2- m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/GroupClie");
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
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid)
        {
            procInfo = "";
            reFileName = "";
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null; string myValue = "";
            int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "N" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                //string[] strArray = ConvertArrayToString(myArray);

                if (i == 1)
                {
                    rng = (Excel.Range)workSheet.Cells[i, 1];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户编号")
                    {
                        procInfo += "文件中未找到'客户编号'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 2];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "归属县")
                    {
                        procInfo += "文件中未找到'归属县'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 3];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户名称")
                    {
                        procInfo += "文件中未找到'客户名称'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 4];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户地址")
                    {
                        procInfo += "文件中未找到'客户地址'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 5];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "维护单位")
                    {
                        procInfo += "文件中未找到'维护单位'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 6];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "所属网格名称")
                    {
                        procInfo += "文件中未找到'所属网格名称'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 7];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户级别")
                    {
                        procInfo += "文件中未找到'客户级别'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 8];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户服务级别")
                    {
                        procInfo += "文件中未找到'客户服务级别'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 9];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "经度")
                    {
                        procInfo += "文件中未找到'经度'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 10];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "纬度")
                    {
                        procInfo += "文件中未找到'纬度'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 11];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户经理")
                    {
                        procInfo += "文件中未找到'客户经理'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 12];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户经理联系电话")
                    {
                        procInfo += "文件中未找到'客户经理联系电话'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 13];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户业务联系人")
                    {
                        procInfo += "文件中未找到'客户业务联系人'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 14];
                    myValue = ExtensionMethods.ToStr(rng.Value2);
                    if (myValue != "客户业务联系电话")
                    {
                        procInfo += "文件中未找到'客户业务联系电话'列";
                        isAllValid = false;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, 15];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic,1);
                    rng.Value2 = "验证结果";

                }
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    bool result = true;
                    rng = (Excel.Range)workSheet.Cells[i, 1];
                    string clieNo = ExtensionMethods.ToStr(rng.Value2);
                    ResourceGroupClie clie = new ResourceGroupClie();
                    string myText = "";
                    if (clieNo != "")
                    {
                        clie = groupClieFacade.GetHql(clieNo);
                        if (clie == null)
                        {
                            ResourceGroupClie entity = new ResourceGroupClie();
                            entity.ClieNo = clieNo;
                            entity.CityId = districtFacade.GetHql("泉州市").Id;
                            rng = (Excel.Range)workSheet.Cells[i, 2];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveDistrict District = new ArchiveDistrict();
                            if (myValue != "")
                            {
                                District = districtFacade.GetHql(myValue);
                                if (District == null)
                                {
                                    myText = "该区县在系统中不存在!!";
                                    result = false;
                                }
                                else
                                    entity.DistrictId = District.Id;
                            }
                            else
                            {
                                myText = "区县不可为空!!";
                                result = false;
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 3];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            if (myValue.Trim() != "")
                            {
                                string Hql = " ClieName = '" + myValue.Trim() + "' and (DelFlag!=1 or DelFlag is null)";
                                ResourceGroupClie cliename = groupClieFacade.GetStrHql(Hql);
                                if (cliename != null)
                                {
                                    myText = "客户名称已在系统中存在!!";
                                    result = false;
                                }
                            }
                            else
                            {
                                myText = "客户名称不可为空!!";
                                result = false;
                            }
                            entity.ClieName = myValue;
                            rng = (Excel.Range)workSheet.Cells[i, 4];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            entity.ClieAddress = myValue;
                            rng = (Excel.Range)workSheet.Cells[i, 5];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveCompany company = new ArchiveCompany();
                            if (myValue != "")
                            {
                                company = companyFacade.GetHql(myValue);
                                if (company == null)
                                {
                                    myText = "该维护单位在系统中不存在!!";
                                    result = false;
                                }
                                else
                                    entity.CompanyId = company.Id;
                            }
                            else
                            {
                                myText = "维护单位不可为空!!";
                                result = false;
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 6];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            if (myValue != "")
                            {
                                string grids = "";
                                string[] gridAry = myValue.Split(';');
                                if (gridAry.Length <= 1)
                                    gridAry = myValue.Split('；');
                                for (int j = 0; j < gridAry.Length; j++)
                                {
                                    ArchiveGrid grid = new ArchiveGrid();
                                    grid = gridFacade.GetHql(gridAry[j]);
                                    grids += grid.Id + ";";
                                }
                                if (grids == "")
                                {
                                    myText = "该所属网格名称在系统中不存在!!";
                                    result = false;
                                }
                                else
                                    entity.GridIds = grids.Substring(0, grids.Length - 1);
                            }
                            else
                            {
                                myText = "所属网格名称不可为空!!";
                                result = false;
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 7];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveClieServStarLeve servStarLeve = new ArchiveClieServStarLeve();
                            if (myValue != "")
                            {
                                servStarLeve = clieServStarLeveFacade.GetHql(myValue);
                                if (servStarLeve == null)
                                {
                                    myText += "该客户级别在系统中不存在!!";
                                    result = false;
                                }
                                else
                                    entity.StarLeveId = servStarLeve.Id;
                            }
                            else
                            {
                                myText += "客户级别不可为空!!";
                                result = false;
                            }

                            rng = (Excel.Range)workSheet.Cells[i, 8];
                            myValue = ExtensionMethods.ToStr(rng.Value2);
                            ArchiveClieScaleGrade ScaleGrade = new ArchiveClieScaleGrade();
                            if (myValue != "")
                            {
                                ScaleGrade = clieScaleGradeFacade.GetHql(myValue);
                                if (ScaleGrade == null)
                                {
                                    myText = "该客户规模在系统中不存在!!";
                                    result = false;
                                }
                                else
                                    entity.ScaleGradeId = ScaleGrade.Id;
                            }
                            else
                            {
                                myText = "客户规模不可为空!!";
                                result = false;
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 9];
                            entity.ClieAreaLongitude = ExtensionMethods.ToStr(rng.Value2);
                            rng = (Excel.Range)workSheet.Cells[i, 10];
                            entity.ClieAreaLatitude = ExtensionMethods.ToStr(rng.Value2);
                            rng = (Excel.Range)workSheet.Cells[i, 11];
                            entity.ClieManager = ExtensionMethods.ToStr(rng.Value2);
                            rng = (Excel.Range)workSheet.Cells[i, 12];
                            string str = ExtensionMethods.ToStr(rng.Value2); 
                            if (str != "")
                            {
                                if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                                {
                                    myText += "客户联系电话格式不对!!";
                                    result = false;
                                }
                                else
                                    entity.ClieManagerTel = str;
                            }
                            rng = (Excel.Range)workSheet.Cells[i, 13];
                            entity.ClieBizContacts = ExtensionMethods.ToStr(rng.Value2);
                            rng = (Excel.Range)workSheet.Cells[i, 14];
                            entity.ClieBizContacTel = ExtensionMethods.ToStr(rng.Value2);
                            entity.CreateUserId = userid;
                            if (result)
                                isAllValid = groupClieFacade.Save(entity, i.ToString());
                            if (isAllValid && result)
                            {
                                myText += "导入数据库成功";
                                m++;
                            }
                            else
                            {
                                myText += "导入数据库失败!!";
                                range.Interior.ColorIndex = 15;//背景颜色
                            }
                        }
                        else
                        {
                            myText = "该客户已在系统存在,对应的客户名称为" + clie.ClieName + "!!";
                            range.Interior.ColorIndex = 15;//背景颜色
                        }
                    }
                    else
                    {
                        myText = "客户编号不可为空!!";
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                   // procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, 15];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow-2) + "条，成功" + m + "条,未成功" + (sheetRow-2 - m) + "条。";

            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/GroupClie");
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
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        } 
    }
}
