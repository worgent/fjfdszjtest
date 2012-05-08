using System;
using System.Text.RegularExpressions;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.BreakdownTypeManger.Models;
using QzgfFrame.Archives.BreakdownTypeManger.Domain;
using QzgfFrame.Resources.EquipFaultManger.Domain;
using QzgfFrame.Resources.EquipFaultManger.Models;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Resources.EquipFaultManger
{
    public class EquipFaultFacadeExImpl : EquipFaultFacadeEx
    {
        private BreakdownTypeFacade breakdownTypeFacade { set; get; }
        private EquipFaultFacade equipFaultFacade { set; get; }        
        private ComponentFacade componentFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private SelfHelpFactoryFacade selfHelpFactoryFacade { set; get; }
        private SelfHelpEquipModelFacade selfHelpEquipModelFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(ResourceEquipFault entity)
        {
            bool result = false;
            result = equipFaultFacade.Save(entity,"0");
            ResourceSelfHelpEquip selfHelpEquip = selfHelpEquipFacade.GetHql(entity.TermiId);
            if (selfHelpEquip != null)
            {
                selfHelpEquip.FaultNum++;
                result = selfHelpEquipFacade.Update(selfHelpEquip);
            }
            return result;
        }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 20)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "县市")
                {
                    procInfo += "文件中未找到'县市'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网点编号")
                {
                    procInfo += "文件中未找到'网点编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网点名称")
                {
                    procInfo += "文件中未找到'网点名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "客户联系电话")
                {
                    procInfo += "文件中未找到'客户联系电话'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "终端ID")
                {
                    procInfo += "文件中未找到'终端ID'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "代维单位")
                {
                    procInfo += "文件中未找到'代维单位'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备厂家")
                {
                    procInfo += "文件中未找到'设备厂家'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备型号")
                {
                    procInfo += "文件中未找到'设备型号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障申告日期")
                {
                    procInfo += "文件中未找到'故障申告日期'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障申告时间")
                {
                    procInfo += "文件中未找到'故障申告时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "人员到达时间")
                {
                    procInfo += "文件中未找到'人员到达时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障解决时间")
                {
                    procInfo += "文件中未找到'故障解决时间'列";
                    isAllValid = false;
                }

                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障处理时长")
                {
                    procInfo += "文件中未找到'故障处理时长'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "排障人员")
                {
                    procInfo += "文件中未找到'排障人员'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障现象")
                {
                    procInfo += "文件中未找到'故障现象'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障部件")
                {
                    procInfo += "文件中未找到'故障部件'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障类型")
                {
                    procInfo += "文件中未找到'故障类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障处理过程")
                {
                    procInfo += "文件中未找到'故障处理过程'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "故障处理结果")
                {
                    procInfo += "文件中未找到'故障处理结果'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否更换配件")
                {
                    procInfo += "文件中未找到'是否更换配件'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 20)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                ArchiveDistrict district = new ArchiveDistrict();
                if (str.Trim() != "")
                {
                    if (str.Trim() == "泉州")
                        str = "市区";
                    district = districtFacade.GetLikeHql(str);
                    if (district == null)
                        procInfo += "该县市在系统中不存在!!";
                }
                else
                    procInfo += "县市不可不可为空!!";

                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "网点编号不可为空!!";
                }
                else
                {
                    int netno = ExtensionMethods.ToInt(str);
                    if (netno == 0 || str.Length !=7)
                    {
                        procInfo += "网点编号格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "网点名称不可为空!!";
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str != "")
                {
                    if (!ExtensionMethods.IsNumeric(str)||str.Length>15)
                    {
                        procInfo += "客户联系电话格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceSelfHelpEquip rse = selfHelpEquipFacade.GetHql(str.Trim());
                    if (rse == null)
                    {
                        procInfo += "终端ID在自助设备信息不存在,不可填写该故障信息!!";
                    }
                }
                else
                {
                    procInfo += "终端ID不可为空!!";
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                ArchiveCompany company = new ArchiveCompany();
                if (str.Trim() != "")
                {
                    company = companyFacade.GetHql(str);
                    if (company == null)
                        procInfo += "该代维单位在系统中不存在!!";
                }
                else
                    procInfo += "该代维单位在系统中不存在!!";

                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveSelfHelpFactory factory = new ArchiveSelfHelpFactory();
                if (str.Trim() != "")
                {
                    factory = selfHelpFactoryFacade.GetHql(str);
                    if (factory == null)
                        procInfo += "该设备厂家在系统中不存在!!";
                }
                else
                    procInfo += "该设备厂家在系统中不存在!!";

                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "设备型号不可为空!!";
                }
                value = myArray.GetValue(1, 9);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "故障申告日期不可为空或格式错误!!";
                    }
                }
                else
                {
                    procInfo += "故障申告日期不可为空!!";
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障申告时间不可为空!!";
                }
                value = myArray.GetValue(1, 11);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "人员到达时间不可为空或格式错误!!";
                    }
                }
                else
                {
                    procInfo += "人员到达时间不可为空!!";
                }
                value = myArray.GetValue(1, 12);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "故障解决时间不可为空或格式错误!!";
                    }
                }
                else
                {
                    procInfo += "故障解决时间不可为空!!";
                }

                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理时长不可为空!!";
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "排障人员不可为空!!";
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障现象不可为空!!";
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                ArchiveComponent component = new ArchiveComponent();
                if (str.Trim() != "")
                {
                    component = componentFacade.GetHql(str);
                    if (component == null)
                        procInfo += "该故障部件在系统中不存在!!";
                }
                else
                    procInfo += "故障部件不可为空!!";

                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                ArchiveBreakdownType breakdownType = new ArchiveBreakdownType();
                if (str.Trim() != "")
                {
                    breakdownType = breakdownTypeFacade.GetHql(str);
                    if (breakdownType == null)
                        procInfo += "该故障类型在系统中不存在!!";
                }
                else
                    procInfo += "故障类型不可为空!!";

                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理过程不可为空!!";
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理结果不可为空!!";
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (str != "是" && str != "否")
                        procInfo += "是否更换配件不可为空格式错误!!";
                }
                else
                    procInfo += "是否更换配件不可为空!!";
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid, string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 20)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceEquipFault entity = new ResourceEquipFault();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                ArchiveDistrict district = new ArchiveDistrict();
                if (str.Trim() != "")
                {
                    if (str.Trim() == "泉州")
                        str = "市区";
                    district = districtFacade.GetLikeHql(str);
                    if (district == null)
                    {
                        procInfo += "该县市在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.DistrictId = district.Id;
                }
                else
                {
                    procInfo += "县市不可不可为空!!";
                    isAllValid = false;
                }

                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "网点编号不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    int netno = ExtensionMethods.ToInt(str);
                   if (netno == 0 || str.Length!=7)
                    {
                        procInfo += "网点编号格式不对!!";
                        isAllValid = false;
                    }
                    else
                        entity.UseNetNo = str.Trim();
                }

                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "网点名称不可为空!!";
                    isAllValid = false;
                }
                entity.UseNetName = str;

                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str != "")
                {
                    if (!ExtensionMethods.IsNumeric(str)||str.Length>15)
                    {
                        procInfo += "客户联系电话格式不对!!";
                        isAllValid = false;
                    }
                    else
                       entity.ClieTel = str;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceSelfHelpEquip rse = selfHelpEquipFacade.GetHql(str.Trim());
                    if (rse == null)
                    {
                        procInfo += "终端ID在自助设备信息不存在,不可填写该故障信息!!";
                        isAllValid = false; 
                    }
                    else
                    {
                        entity.SelfHelpEquipId = rse.Id;
                    }
                }
                else
                {
                    procInfo += "终端ID不可为空!!";
                    isAllValid = false;
                }
                entity.TermiId = str;
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                ArchiveCompany company = new ArchiveCompany();
                if (str.Trim() != "")
                {
                    company = companyFacade.GetHql(str);
                    if (company == null)
                    {
                        procInfo += "该代维单位在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.CompanyId = company.Id;
                }
                else
                {
                    procInfo += "该代维单位在系统中不存在!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveSelfHelpFactory factory = new ArchiveSelfHelpFactory();
                if (str.Trim() != "")
                {
                    factory = selfHelpFactoryFacade.GetHql(str);
                    if (factory == null)
                    {
                        procInfo += "该设备厂家在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.FactoryId = factory.Id;
                }
                else
                {
                    procInfo += "该设备厂家在系统中不存在!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    entity.EquipModelName = str;
                }
                else
                {
                    procInfo += "该设备型号不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                string NoticeDateTime = "";
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "故障申告日期不可为空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        NoticeDateTime = Convert.ToDateTime(dt).ToShortDateString();
                }
                else
                {
                    procInfo += "故障申告日期不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                string NoticeTime = "";
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "故障申告时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        NoticeTime = Convert.ToDateTime(dt).ToShortTimeString();
                }
                else
                {
                    procInfo += "故障申告时间不可为空!!";
                    isAllValid = false;
                }
                NoticeDateTime = NoticeDateTime + " " + NoticeTime;
                entity.NoticeDateTime = Convert.ToDateTime(NoticeDateTime);

                value = myArray.GetValue(1, 11);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "人员到达时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        entity.ReachDatetime = dt;
                }
                else
                {
                    procInfo += "人员到达时间不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "故障解决时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        entity.SolveDatetime = dt;
                }
                else
                {
                    procInfo += "故障解决时间不可为空!!";
                    isAllValid = false;
                }

                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理时长不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    string[] t1 = Regex.Split(str, "分钟", RegexOptions.IgnoreCase);
                    entity.HandleTime = t1[0];
                }
                // entity.HandleTime =TimeSpan.Parse(str);

                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "排障人员不可为空!!";
                    isAllValid = false;
                }
                entity.TroubleShooter = str;
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障现象不可为空!!";
                    isAllValid = false;
                }
                entity.Description = str;
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                ArchiveComponent component = new ArchiveComponent();
                if (str.Trim() != "")
                {
                    component = componentFacade.GetHql(str);
                    if (component == null)
                    {
                        procInfo += "该故障部件在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.ComponentId = component.Id;
                }
                else
                {
                    procInfo += "故障部件不可为空!!";
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                ArchiveBreakdownType breakdownType = new ArchiveBreakdownType();
                if (str.Trim() != "")
                {
                    breakdownType = breakdownTypeFacade.GetHql(str);
                    if (breakdownType == null)
                    {
                        procInfo += "该故障类型在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.BreakdownTypeId = breakdownType.Id;
                }
                else
                {
                    procInfo += "故障类型不可为空!!";
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理过程不可为空!!";
                    isAllValid = false;
                }
                entity.HandleProcess = str;
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "故障处理结果不可为空!!";
                    isAllValid = false;
                }
                entity.HandleResult = str;
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "是否更换配件不可为空!!";
                    isAllValid = false;
                }
                entity.IsReplace = ExtensionMethods.ObjStrToShort(value);

                value = myArray.GetValue(1, 21);
                entity.Remark = ExtensionMethods.ToStr(value);
                entity.CreateUserId = userid;
                if (isAllValid)
                {
                    isAllValid = equipFaultFacade.Save(entity,iFlag);
                    ResourceSelfHelpEquip selfHelpEquip = selfHelpEquipFacade.GetHql(entity.TermiId);
                    if (selfHelpEquip != null)
                    {
                        selfHelpEquip.FaultNum++;
                        isAllValid = selfHelpEquipFacade.Update(selfHelpEquip);
                    }
                }
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
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/EquipFault");
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
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/EquipFault");
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
