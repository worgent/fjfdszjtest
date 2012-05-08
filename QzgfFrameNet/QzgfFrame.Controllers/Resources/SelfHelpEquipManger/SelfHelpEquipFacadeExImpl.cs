using System;
using System.Text.RegularExpressions;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;
using QzgfFrame.Resources.EquipComponentManger.Domain;
using QzgfFrame.Resources.EquipComponentManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;
using Newtonsoft.Json;

namespace QzgfFrame.Controllers.Resources.SelfHelpEquipManger
{
    public class SelfHelpEquipFacadeExImpl : SelfHelpEquipFacadeEx
    {

        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        private SelfHelpEquipModelFacade selfHelpEquipModelFacade { set; get; }
        private SelfHelpEquipTypeFacade selfHelpEquipTypeFacade { set; get; }
        private SelfHelpFactoryFacade selfHelpFactoryFacade { set; get; }
        private OutletsTypeFacade outletsTypeFacade { set; get; }
        private ComponentFacade componentFacade { set; get; }
        private EquipComponentFacade equipComponentFacade { set; get; }

        public bool Save(SelfHelpEquip entity)
        {
            bool result = false;
            result = this.selfHelpEquipFacade.Save(entity.selfHelpEquip,"0");            
            if (entity.equipComponents != null)
            {
                ResourceEquipComponent[] Components = (ResourceEquipComponent[])JavaScriptConvert.DeserializeObject(entity.equipComponents, typeof(ResourceEquipComponent[]));
                int j = 0;
                foreach (ResourceEquipComponent ng in Components)
                {
                    ng.SelfHelpEquipId = entity.selfHelpEquip.Id;
                    result = equipComponentFacade.Save(ng, j.ToString());
                    j++;
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(SelfHelpEquip entity)
        {
            bool result = false;
            result = selfHelpEquipFacade.Update(entity.selfHelpEquip);
            result = equipComponentFacade.Delete(entity.selfHelpEquip.Id.ToString());
            if (entity.equipComponents != null)
            {
                ResourceEquipComponent[] Components = (ResourceEquipComponent[])JavaScriptConvert.DeserializeObject(entity.equipComponents, typeof(ResourceEquipComponent[]));
                int j = 0;
                foreach (ResourceEquipComponent ng in Components)
                {
                    ng.SelfHelpEquipId = entity.selfHelpEquip.Id;
                    result = equipComponentFacade.Save(ng, j.ToString());
                    j++;
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id)
        {
            bool result = false;
            result = selfHelpEquipFacade.Delete(id.ToString());
            result = equipComponentFacade.Delete(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool DeleteFalse(string id,out bool DelFlag)
        {
            bool result = false;
            result = selfHelpEquipFacade.DeleteFalse(id.ToString(), out DelFlag);
            result = equipComponentFacade.DeleteFalse(id.ToString());
           // if (result == false)
             //   throw new Exception("操作失败!!");
            return result;
        }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 17)
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
                if (str.Trim() != "设备厂家")
                {
                    procInfo += "文件中未找到'设备厂家'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备类型")
                {
                    procInfo += "文件中未找到'设备类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备型号")
                {
                    procInfo += "文件中未找到'设备型号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备安装时间")
                {
                    procInfo += "文件中未找到'设备安装时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "终端ID")
                {
                    procInfo += "文件中未找到'终端ID'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网点类型")
                {
                    procInfo += "文件中未找到'网点类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网点编号")
                {
                    procInfo += "文件中未找到'网点编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "使用网点名称")
                {
                    procInfo += "文件中未找到'使用网点名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "使用网点地址")
                {
                    procInfo += "文件中未找到'使用网点地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "终端IP地址")
                {
                    procInfo += "文件中未找到'终端IP地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "掩码")
                {
                    procInfo += "文件中未找到'掩码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "终端MAC地址")
                {
                    procInfo += "文件中未找到'终端MAC地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备采购时间")
                {
                    procInfo += "文件中未找到'设备采购时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "可使用年限")
                {
                    procInfo += "文件中未找到'可使用年限'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否过保")
                {
                    procInfo += "文件中未找到'是否过保'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "部件信息")
                {
                    procInfo += "文件中未找到'部件信息'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
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
            if (myArray.Length < 17)
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
                    district = districtFacade.GetLikeHql(str);
                    if (district == null)
                        procInfo += "该县市在系统中不存在!!";
                }
                else
                    procInfo += "县市不可为空!!";
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                ArchiveSelfHelpFactory factory = new ArchiveSelfHelpFactory();
                if (str.Trim() != "")
                {
                    factory = selfHelpFactoryFacade.GetHql(str);
                    if (factory == null)
                        procInfo += "该设备厂家在系统中不存在!!";
                }
                else
                    procInfo += "设备厂家不可为空!!";
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                ArchiveSelfHelpEquipType equipType = new ArchiveSelfHelpEquipType();
                if (str.Trim() != "")
                {
                    equipType = selfHelpEquipTypeFacade.GetHql(str);
                    if (equipType == null)
                        procInfo += "该设备类型在系统中不存在!!";
                }
                else
                    procInfo += "设备类型不可为空!!";
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {

                    procInfo += "设备型号不可为空!!";
                }
                value = myArray.GetValue(1, 5);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "设备安装时间空或格式错误!!";
                    }
                }
                else
                {
                    procInfo += "设备安装时间不可为空!!";
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    int terimno = ExtensionMethods.ToInt(str);
                    if (terimno == 0 || str.Length != 8)
                    {
                        procInfo += "终端ID格式不对,只能8位数字!!";
                    }
                    else
                    {
                        ResourceSelfHelpEquip she = selfHelpEquipFacade.GetHql(str.Trim());
                        if (she != null)
                            procInfo += "终端ID已在系统中存在!!";
                    }
                }
                else
                    procInfo += "终端ID不可为空!!";
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveOutletsType outletsType = new ArchiveOutletsType();
                if (str.Trim() != "")
                {
                    outletsType = outletsTypeFacade.GetHql(str);
                    if (outletsType == null)
                        procInfo += "该网点类型在系统中不存在!!";
                }
                else
                    procInfo += "网点类型不可为空!!";
                value = myArray.GetValue(1, 8);
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
                        procInfo += "网点编号格式不对,只能7位数字!!";
                    }
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "使用网点名称不可为空!!";
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.IPCheck(str))
                        procInfo += "终端IP地址格式错误!!";
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.IPCheck(str))
                        procInfo += "子网掩码格式错误!!";
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.MacCheck(str))
                        procInfo += "MAC地址格式错误!!";
                }
                value = myArray.GetValue(1, 14);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "设备采购时间不可为空或格式错误!!";
                    }
                }
                else
                {
                    procInfo += "设备采购时间不可为空!!";
                } 
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str != "")
                {
                    string[] life = Regex.Split(str, "年", RegexOptions.IgnoreCase);
                    int netno = ExtensionMethods.ToInt(life[0]);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "可使用年限格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (str.Trim() != "是" && str.Trim() != "否")
                        procInfo += "是否过保格式错误!!";
                }
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid,string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 17)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceSelfHelpEquip entity = new ResourceSelfHelpEquip();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                ArchiveDistrict district = new ArchiveDistrict();
                if (str.Trim() != "")
                {
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
                    procInfo += "县市不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
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
                    procInfo += "设备厂家不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                ArchiveSelfHelpEquipType equipType = new ArchiveSelfHelpEquipType();
                if (str.Trim() != "")
                {
                    equipType = selfHelpEquipTypeFacade.GetHql(str);
                    if (equipType == null)
                    {
                        procInfo += "该设备类型在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.EquipTypeId = equipType.Id;
                }
                else
                {
                    procInfo += "设备类型不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    entity.EquipModelName = str;
                }
                else
                {
                    procInfo += "设备型号不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "设备安装时间空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        entity.StartDatetime = dt;
                }
                else
                {
                    procInfo += "设备安装时间不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    int netno = ExtensionMethods.ToInt(value);
                    if (netno == 0 || str.Length != 8)
                    {
                        procInfo += "终端ID格式不对!!";
                        isAllValid = false;
                    }
                    else
                    {
                        ResourceSelfHelpEquip she = selfHelpEquipFacade.GetHql(str.Trim());
                        if (she != null)
                        {
                            procInfo += "终端ID已在系统中存在!!";
                            isAllValid = false;
                        }
                        else
                            entity.TermiId = str;
                    }
                }
                else
                {
                    procInfo += "终端ID不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveOutletsType outletsType = new ArchiveOutletsType();
                if (str.Trim() != "")
                {
                    outletsType = outletsTypeFacade.GetHql(str);
                    if (outletsType == null)
                    {
                        procInfo += "该网点类型在系统中不存在!!";
                        isAllValid = false;
                    }
                    else
                        entity.NetType = outletsType.Id;
                }
                else
                {
                    procInfo += "网点类型不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "网点编号不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    int netno = ExtensionMethods.ToInt(value);
                    if (netno == 0 || str.Length != 7)
                    {
                        procInfo += "网点编号格式不对!!";
                        isAllValid = false;
                    }
                    else
                        entity.UseNetNo = str.Trim();
                }

                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "使用网点名称不可为空!!";
                    isAllValid = false;
                }
                entity.UseNetName = str;

                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                entity.NetAddress = str;

                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.IPCheck(str))
                    {
                        procInfo += "终端IP地址格式错误!!";
                        isAllValid = false;
                    }
                }
                entity.TerimIP = str;

                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.IPCheck(str))
                    {
                        procInfo += "子网掩码格式错误!!";
                        isAllValid = false;
                    }
                }
                entity.SubnetMask = str;
                
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.MacCheck(str))
                    {
                        procInfo += "MAC地址格式错误!!";
                        isAllValid = false;
                    }
                }
                entity.MacAddress = str;

                value = myArray.GetValue(1, 14);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "设备采购时间空或格式错误!!";
                        isAllValid = false;
                    }
                    else
                        entity.BuyDatetime = dt;
                }
                else
                {
                    procInfo += "设备采购时间不可为空!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str != "")
                {
                    string[] life = Regex.Split(str, "年", RegexOptions.IgnoreCase);
                    int netno = ExtensionMethods.ToInt(life[0]);
                    if (netno == 0 || str.Length >10)
                    {
                        procInfo += "可使用年限格式不对!!";
                        isAllValid = false;
                    }
                    entity.Life = int.Parse(life[0]);
                }

                value = myArray.GetValue(1, 16);
                entity.IsOverInsuran = ExtensionMethods.ObjStrToShort(value);

                entity.Remark = ExtensionMethods.ToStr(value);

                value = myArray.GetValue(1, 18);
                entity.Remark = ExtensionMethods.ToStr(value);
                if (isAllValid)
                {
                    entity.CreateUserId = userid;
                    isAllValid = selfHelpEquipFacade.Save(entity, iFlag);
                    value = myArray.GetValue(1, 17);
                    if (value != null)
                    {
                        str = ExtensionMethods.ToStr(value);
                        string[] Ary = str.Split("，");
                        int i = 0;
                        foreach (string strary in Ary)
                        {
                            string[] tAry = strary.Split(",");
                            if (tAry.Length > 0)
                            {
                                foreach (string strar in tAry)
                                {
                                    string[] commponet = strar.Split("-");
                                    ResourceEquipComponent rec = new ResourceEquipComponent();
                                    ArchiveComponent ac = componentFacade.GetHql(commponet[0]);
                                    if (ac != null)
                                    {
                                        rec.ComponentId = ac.Id;
                                        rec.ComponentModel = commponet[1];
                                        rec.SelfHelpEquipId = entity.Id;
                                        isAllValid = equipComponentFacade.Save(rec, i.ToString());
                                        i++;
                                    }
                                }
                            }
                            else
                            {
                                string[] commponet = strary.Split("-");
                                ResourceEquipComponent rec = new ResourceEquipComponent();
                                ArchiveComponent ac = componentFacade.GetHql(commponet[0]);
                                if (ac != null)
                                {
                                    rec.ComponentId = ac.Id;
                                    rec.ComponentModel = commponet[1];
                                    rec.SelfHelpEquipId = entity.Id;
                                    isAllValid = equipComponentFacade.Save(rec, i.ToString());
                                    i++;
                                }
                            }
                        }

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
            int sheetColumn = 18; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "R" + i.ToString());
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
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/SelfHelpEquip");
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
            int sheetColumn = 18; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "R" + i.ToString());
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
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/SelfHelpEquip");
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
