using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Resources.VehicleManger.Domain;
using QzgfFrame.Resources.VehicleManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.UseManger.Models;
using QzgfFrame.Archives.UseManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.VehicleNatureManger.Models;
using QzgfFrame.Archives.VehicleNatureManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Resources.VehicleManger
{
    public class VehicleFacadeExImpl : VehicleFacadeEx
    {

        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private UseFacade useFacade { set; get; }
        private VehicleFacade vehicleFacade { set; get; }
        private StagnationFacade stagnationFacade { set; get; }
        private VehicleNatureFacade vehicleNatureFacade { set; get; }
        private MaintainSpecialtyFacade maintainSpecialtyFacade { set; get; }

        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            if (myArray.Length < 21)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
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
                if (str.Trim() != "所属县区")
                {
                    procInfo += "文件中未找到'所属县区'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "车牌号 *")
                {
                    procInfo += "文件中未找到'车牌号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "维护专业 *")
                {
                    procInfo += "文件中未找到'维护专业'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开始使用时间 *")
                {
                    procInfo += "文件中未找到'开始使用时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开始使用里程数 *")
                {
                    procInfo += "文件中未找到'开始使用里程数'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "性质 *")
                {
                    procInfo += "文件中未找到'性质'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "用途 *")
                {
                    procInfo += "文件中未找到'用途'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "行驶证号 *")
                {
                    procInfo += "文件中未找到'行驶证号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "发动机号 *")
                {
                    procInfo += "文件中未找到'发动机号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "年检时间 *")
                {
                    procInfo += "文件中未找到'年检时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "生产厂家")
                {
                    procInfo += "文件中未找到'生产厂家'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "型号规格")
                {
                    procInfo += "文件中未找到'型号规格'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "排气量")
                {
                    procInfo += "文件中未找到'排气量'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属网格")
                {
                    procInfo += "文件中未找到'所属网格'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "姓名")
                {
                    procInfo += "文件中未找到'姓名'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "联系电话")
                {
                    procInfo += "文件中未找到'联系电话'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 21)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                    procInfo += "车牌号不可为空!!";
                else
                {
                    ResourceVehicle Vehicle = vehicleFacade.GetHql(str);
                    if (Vehicle == null)
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
                        /**
                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "车牌号不可为空!!";
                        }**/
                        value = myArray.GetValue(1, 7);
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
                        value = myArray.GetValue(1, 8);
                        DateTime? dt1 = ExtensionMethods.ToDateOANull(value);
                        if (dt1 == null)
                        {
                            procInfo += "开始使用时间不可为空或格式错误!!";
                        }
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str != "" && str != "0")
                        {
                            int lcno = ExtensionMethods.ToInt(str);
                            if (lcno == 0)
                            {
                                procInfo += "开始使用里程数格式不对,只能是数字!!";
                            }
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveVehicleNature vehicleNature = new ArchiveVehicleNature();
                        if (str.Trim() != "")
                        {
                            vehicleNature = vehicleNatureFacade.GetHql(str);
                            if (vehicleNature == null)
                                procInfo += "该性质在系统中不存在!!";
                        }
                        else
                            procInfo += "性质不可为空!!";

                        value = myArray.GetValue(1, 11);
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
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "行驶证号不可为空!!";
                        }
                        value = myArray.GetValue(1, 13);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "发动机号不可为空!!";
                        }
                        value = myArray.GetValue(1, 14);
                        DateTime? dt = ExtensionMethods.ToDateOANull(value);
                        if (dt == null)
                        {
                            procInfo += "年检时间不可为空或格式错误或格式错误!!";
                        }
                        value = myArray.GetValue(1, 19);
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
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "姓名不可为空!!";
                        }
                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                            {
                                procInfo += "联系电话格式不对,只能是小于等于15位数字!!";
                            }
                        }
                        else
                            procInfo += "联系电话不可为空!!";
                    }
                    else
                        procInfo += "该车辆信息在系统中已存在!!";
                }
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid, string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 21)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceVehicle entity = new ResourceVehicle();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "车牌号不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    entity = vehicleFacade.GetHql(str);
                    if (entity == null)
                    {
                        entity = new ResourceVehicle();
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
                                procInfo += "该所属区县在系统中不存在!!";
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
                        entity.LicensePlateNumber = str;

                        value = myArray.GetValue(1, 7);
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
                        value = myArray.GetValue(1, 8);
                        DateTime? dt1 = ExtensionMethods.ToDateOANull(value);
                        if (dt1 == null)
                        {
                            procInfo += "开始使用时间不可为空!!";
                            isAllValid = false;
                        }
                        entity.StartDatetime = dt1;
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str != "" && str != "0")
                        {
                            int lcno = ExtensionMethods.ToInt(str);
                            if (lcno == 0)
                            {
                                procInfo += "开始使用里程数格式不对,只能是数字!!";
                            } 
                            entity.UseMileage = lcno;
                        } 

                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveVehicleNature vehicleNature = new ArchiveVehicleNature();
                        if (str.Trim() != "")
                        {
                            vehicleNature = vehicleNatureFacade.GetHql(str);
                            if (vehicleNature == null)
                            {
                                procInfo += "该性质在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.VehicleNatureId = vehicleNature.Id;
                        }
                        else
                        {
                            procInfo += "性质不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 11);
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
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "行驶证号不可为空!!";
                            isAllValid = false;
                        }
                        entity.DrivingLicenseNo = str;

                        value = myArray.GetValue(1, 13);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "发动机号不可为空!!";
                            isAllValid = false;
                        }
                        entity.EngineNo = str;

                        value = myArray.GetValue(1, 14);
                        DateTime? dt = ExtensionMethods.ToDateOANull(value);
                        if (dt == null)
                        {
                            procInfo += "年检时间不可为空或格式错误或格式错误!!";
                            isAllValid = false;
                        }
                        entity.AnnualInspectTime = dt;

                        value = myArray.GetValue(1, 15);
                        entity.ManuFacturer = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 16);
                        entity.ModelSpecification = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 17);
                        entity.Displacement = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 18);
                        entity.Remark = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 19);
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
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "姓名不可为空!!";
                            isAllValid = false;
                        }
                        entity.FullName = str;
                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                            {
                                procInfo += "联系电话格式不对,只能是小于等于15位数字!!";
                                isAllValid = false;
                            }
                        }
                        else
                        {
                            procInfo += "联系电话不可为空!!";
                            isAllValid = false;
                        }
                        entity.LinkTel = str;
                    }
                    else
                    {
                        procInfo += "该车辆信息在系统中已存在!!";
                        isAllValid = false;
                    }
                }
                entity.CreateUserId = userid;
                if (isAllValid)
                    isAllValid = vehicleFacade.Save(entity, iFlag);
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
            int sheetColumn = 21; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "U" + i.ToString());
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
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/Vehicle");
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
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 21; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "U" + i.ToString());
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
            procInfo = "数据库总导入" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";

            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/Vehicle");
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
            File.Delete(strFileName);
            return isAllValid;
        }  
    }
}
