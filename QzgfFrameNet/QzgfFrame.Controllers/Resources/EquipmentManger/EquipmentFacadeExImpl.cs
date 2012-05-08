using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.PortTypeManger.Domain;
using QzgfFrame.Archives.PortTypeManger.Models;
using Newtonsoft.Json;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Resources.EquipmentManger
{
    public class EquipmentFacadeExImpl : EquipmentFacadeEx
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private PortTypeFacade portTypeFacade { set; get; }
        private EquipmentFacade equipmentFacade { set; get; }
        private ClieEquipFacade clieEquipFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipTypeFacade equipTypeFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private NetworkingModeFacade networkingModeFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(EquipClie entity)
        {
            bool result = false;
                result = this.equipmentFacade.Save(entity.equipment,"0");
                ClieEquip[] equipclies = (ClieEquip[])JavaScriptConvert.DeserializeObject(entity.equipclies, typeof(ClieEquip[]));
                int i = 0;
                foreach (ClieEquip rf in equipclies)
                {
                    ResourceClieEquip clieEquip = new ResourceClieEquip();
                    clieEquip.ClieId = rf.ClieId;
                    clieEquip.OccupySlot = rf.OccupySlot;
                    clieEquip.OccupyPort = rf.OccupyPort;
                    clieEquip.BoardType = rf.BoardType;
                    clieEquip.PortTypeId = rf.PortTypeId;
                    clieEquip.EquipId = entity.equipment.Id;
                    result = clieEquipFacade.Save(clieEquip,i.ToString());                    
                    i++;
                }
            if(result==false)
                throw new Exception("操作失败!!");
                return result;           
        }
        public bool Update(EquipClie entity)
        {
            bool result = false;
            result = this.equipmentFacade.Update(entity.equipment);
            result = clieEquipFacade.Delete(entity.equipment.Id.ToString());
            ClieEquip[] equipclies = (ClieEquip[])JavaScriptConvert.DeserializeObject(entity.equipclies, typeof(ClieEquip[]));
            int i = 0;
            foreach (ClieEquip rf in equipclies)
            {
                ResourceClieEquip clieEquip = new ResourceClieEquip();
                clieEquip.ClieId = rf.ClieId;
                clieEquip.OccupySlot = rf.OccupySlot;
                clieEquip.OccupyPort = rf.OccupyPort;
                clieEquip.BoardType = rf.BoardType;
                clieEquip.EquipId = entity.equipment.Id;
                clieEquip.PortTypeId = rf.PortTypeId;
                result = clieEquipFacade.Save(clieEquip,i.ToString());
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Delete(string id)
        {
            bool result = false;
            result = equipmentFacade.Delete(id.ToString());
            result = clieEquipFacade.Delete(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool DeleteFalse(string id, out bool DelFlag)
        {
            bool result = false;
            result = equipmentFacade.DeleteFalse(id.ToString(), out DelFlag);
           // result = clieEquipFacade.DeleteFalse(id.ToString());
           return result;           
        }
        public EquipClie Get(string id)
        {
            EquipClie entity = new EquipClie();
            entity.equipment = equipmentFacade.Get(id);
            string hql = "select e.ClieId,e.OccupySlot,e.BoardType,e.OccupyPort,g.ClieNo,g.ClieName,e.PortTypeId,p.PortTypeName  from ResourceClieEquip e,ResourceGroupClie g,ArchivePortType p where e.ClieId=g.Id and p.Id=e.PortTypeId and e.EquipId='" + entity.equipment.Id + "'";
            
            entity.ClieEquips = clieEquipFacade.LoadAll(hql);
            return entity;
        }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 24)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团名称")
                {
                    procInfo += "文件中未找到'集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团编号")
                {
                    procInfo += "文件中未找到'集团编号'列";
                    isAllValid = false;
                } value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属区县")
                {
                    procInfo += "文件中未找到'所属区县'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备名称")
                {
                    procInfo += "文件中未找到'设备名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备类型")
                {
                    procInfo += "文件中未找到'设备类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备型号")
                {
                    procInfo += "文件中未找到'设备型号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备厂家")
                {
                    procInfo += "文件中未找到'设备厂家'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备序列号")
                {
                    procInfo += "文件中未找到'设备序列号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备安装位置")
                {
                    procInfo += "文件中未找到'设备安装位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "基站/机房名称")
                {
                    procInfo += "文件中未找到'基站/机房名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "MAC地址")
                {
                    procInfo += "文件中未找到'MAC地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备启用时间")
                {
                    procInfo += "文件中未找到'设备启用时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "登录方式")
                {
                    procInfo += "文件中未找到'登录方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "路由器用户名")
                {
                    procInfo += "文件中未找到'路由器用户名'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "密码")
                {
                    procInfo += "文件中未找到'密码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "SNMP只读共同体")
                {
                    procInfo += "文件中未找到'SNMP只读共同体'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "SNMP只写共同体")
                {
                    procInfo += "文件中未找到'SNMP只写共同体'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网管IP")
                {
                    procInfo += "文件中未找到'网管IP'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "WEB端口")
                {
                    procInfo += "文件中未找到'WEB端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "组网方式")
                {
                    procInfo += "文件中未找到'组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "占用槽位")
                {
                    procInfo += "文件中未找到'占用槽位'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "板卡类型")
                {
                    procInfo += "文件中未找到'板卡类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "设备占用端口")
                {
                    procInfo += "文件中未找到'设备占用端口'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 26)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                ResourceGroupClie clie = new ResourceGroupClie();
                if (str.Trim() != "")
                {
                    clie = groupClieFacade.GetHql(str);
                    if (clie == null)
                        procInfo += "该集团编号在系统中不存在!!";
                }
                else
                {
                    procInfo += "集团编号为空!!";
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                ArchiveDistrict district = new ArchiveDistrict();
                if (str.Trim() != "")
                {
                    district = districtFacade.GetHql(str);
                    if (district == null)
                        procInfo += "该所属县区在系统中不存在!!";
                }
                else
                    procInfo += "所属县区不可为空!!";
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                ArchiveCompany company = new ArchiveCompany();
                if (str.Trim() != "")
                {
                    company = companyFacade.GetHql(str);
                    if (company == null)
                        procInfo += "该所属公司在系统中不存在!!";
                }
                else
                    procInfo += "所属公司不可为空!!";
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "设备名称不可为空!!";
                }
                else if (str.Length > 40)
                {
                    procInfo += "设备名称长度超过预定!!";
                }
                else
                {
                    ResourceEquipment equip = equipmentFacade.GetHql(str);
                    if (equip != null)
                        procInfo += "设备名称在系统中已经存在!!";
                }

                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                ArchiveEquipType equipType = new ArchiveEquipType();
                if (str.Trim() != "")
                {
                    equipType = equipTypeFacade.GetHql(str);
                    if (equipType == null)
                        procInfo += "该设备类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "设备类型为空!!";
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "设备型号不可为空!!";
                }
                else if(str.Length>40)
                    procInfo += "设备型号长度超过预定!!";

                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                ArchiveFactory factory = new ArchiveFactory();
                if (str.Trim() != "")
                {
                    factory = factoryFacade.GetHql(str);
                    if (factory == null)
                        procInfo += "该设备厂家在系统中不存在!!";
                }
                else
                {
                    procInfo += "设备厂家为空!!";
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "设备安装位置不可为空!!";
                }
                else if (str != "局端" && str != "用户机房")
                {
                    procInfo += "设备安装位置格式错误!!";
                } 
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "基站/杋房名称不可为空!!";
                }
                else if (str.Length > 50)
                {
                    procInfo += "基站/杋房名称长度超过预定!!";
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (!ExtensionMethods.MacCheck(str))
                        procInfo += "MAC地址格式错误!!";
                }
                else if (equipType != null)
                {

                    if (equipType.EquipTypeName == "ONU")
                    {
                        procInfo += "MAC地址不可为空!!";
                    }
                }
                value = myArray.GetValue(1, 13);
                DateTime? starttime = ExtensionMethods.ToDateOANull(value);
                if (starttime == null)
                {
                    procInfo += "设备启用时间不可为空或格式错误!!";
                }
                if (equipType != null)
                {
                    if (equipType.EquipTypeName == "路由器" || equipType.EquipTypeName == "IAD" || equipType.EquipTypeName == "IPPBX")
                    {
                        value = myArray.GetValue(1, 15);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "路由器用户名不可为空!!";
                        }
                        else if (str.Length > 20)
                        {
                            procInfo += "路由器用户名长度超过预定!!";
                        }
                        value = myArray.GetValue(1, 16);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "密码不可为空!!";
                        }
                        else if (str.Length > 20)
                        {
                            procInfo += "密码长度超过预定!!";
                        }
                        value = myArray.GetValue(1, 19);
                        str = ExtensionMethods.ToStr(value);
                        if (str != "")
                        {
                            if (!ExtensionMethods.IPCheck(str))
                                procInfo += "网管IP格式错误!!";
                        }
                        else
                        {
                            procInfo += "网管IP不可为空!!";
                        }
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "WEB端口不可为空!!";
                        }
                        else if (str.Length > 40)
                        {
                            procInfo += "WEB端口长度超过预定!!";
                        }
                    }
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                ArchiveNetworkingMode NetworkingMode = new ArchiveNetworkingMode();
                if (str != "")
                {
                    NetworkingMode = networkingModeFacade.GetHql(str);
                    if (NetworkingMode == null)
                        procInfo += "该组网方式在系统中不存在!!";
                }
                else
                {
                    procInfo += "组网方式不可为空!!";
                }
                if (NetworkingMode != null)
                {
                    if (NetworkingMode.ModeName == "MSAP")
                    {
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "占用槽位不可为空!!";
                        }
                        else if (str.Length > 20)
                        {
                            procInfo += "WEB端口长度超过预定!!";
                        }
                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "板卡类型不可为空!!";
                        }
                        else if (str.Length > 30)
                        {
                            procInfo += "板卡类型长度超过预定!!";
                        }
                    }
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Length==0 || str.Length > 20)
                {
                    procInfo += "设备占用端口不可为空或长度超过预定!!";
                }           
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                ArchivePortType portType = new ArchivePortType();
                if (str != "")
                {
                    portType = portTypeFacade.GetHql(str);
                    if (portType == null)
                        procInfo += "该端口类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "端口类型不可为空!!";
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Length > 200)
                {
                    procInfo += "备注长度超过预定!!";
                }            
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid,string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 26)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceEquipment entity = new ResourceEquipment();
                ResourceClieEquip clieEquip = new ResourceClieEquip();
                string str = ""; object value = null;

                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "设备名称不可为空!!";
                    isAllValid = false;
                }
                else if (str.Length > 40)
                {
                    procInfo += "设备名称长度超过预设!!";
                    isAllValid = false;
                }
                else
                {
                    ResourceEquipment equip = equipmentFacade.GetHql(str);
                    if (equip != null)
                    {
                        procInfo += "该设备名称在系统中已经存在!!";
                        isAllValid = false;
                    }
                    else
                    {
                        entity.EquipName = str;
                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        ResourceGroupClie clie = new ResourceGroupClie();
                        if (str.Trim() != "")
                        {
                            clie = groupClieFacade.GetHql(str);
                            if (clie == null)
                            {
                                procInfo += "该集团编号在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                clieEquip.ClieId = clie.Id;
                        }
                        else
                        {
                            procInfo += "集团编号为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 3);
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
                        else
                        {
                            procInfo += "所属区县不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 4);
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
                        else
                        {
                            procInfo += "所属公司不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveEquipType equipType = new ArchiveEquipType();
                        if (str.Trim() != "")
                        {
                            equipType = equipTypeFacade.GetHql(str);
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
                            procInfo += "设备类型为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "设备型号不可为空!!";
                            isAllValid = false;
                        }
                        else if (str.Length > 100)
                        {
                            procInfo += "设备型号长度超过预定!!";
                            isAllValid = false;
                        }
                        else
                        {
                            entity.EquipModelName = str.Trim();
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveFactory factory = new ArchiveFactory();
                        if (str.Trim() != "")
                        {
                            factory = factoryFacade.GetHql(str);
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
                            procInfo += "设备厂家为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        entity.SeqNumber = str;
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "设备安装位置不可为空!!";
                            isAllValid = false;
                        }
                        else if (str != "局端" && str != "用户机房")
                        {
                            procInfo += "设备安装位置格式错误!!";
                            isAllValid = false;
                        }
                        else
                            entity.Position = ExtensionMethods.ObjLocalToShort(value);
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "基站/机房名称不可为空!!";
                            isAllValid = false;
                        }
                        else if (str.Length > 50)
                        {
                            procInfo += "基站/机房名称长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.BaseStationName = str;
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.MacCheck(str))
                            {
                                procInfo += "MAC地址格式错误!!";
                                isAllValid = false;
                            }
                        }
                        else if (equipType != null)
                        {
                            if (equipType.EquipTypeName == "ONU" && str == "")
                            {
                                procInfo += "MAC地址不可为空!!";
                                isAllValid = false;
                            }
                        }
                        entity.BaseStationName = str;

                        value = myArray.GetValue(1, 13);
                        if (value != null)
                        {
                            DateTime? dt = ExtensionMethods.ToDateOANull(value);
                            if (dt == null)
                            {
                                procInfo += "开通时间为空或格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.StartDatetime = dt;
                        }
                        else
                        {
                            procInfo += "开通时间为空或格式错误!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 14);
                        entity.LoginStyle = ExtensionMethods.ToStr(value);
                        if (equipType != null)
                        {
                            if (equipType.EquipTypeName == "路由器" || equipType.EquipTypeName == "IAD" || equipType.EquipTypeName == "IPPBX")
                            {
                                value = myArray.GetValue(1, 15);
                                str = ExtensionMethods.ToStr(value);
                                if (str == "")
                                {
                                    procInfo += "路由器用户名不可为空!!";
                                    isAllValid = false;
                                }
                                else if (str.Length > 20)
                                {
                                    procInfo += "路由器用户名长度超过预定!!";
                                    isAllValid = false;
                                }
                                entity.UserName = str;
                                value = myArray.GetValue(1, 16);
                                str = ExtensionMethods.ToStr(value);
                                if (str == "")
                                {
                                    procInfo += "密码不可为空!!";
                                    isAllValid = false;
                                }
                                else if (str.Length > 20)
                                {
                                    procInfo += "密码长度超过预定!!";
                                    isAllValid = false;
                                }
                                entity.PassWord = str;
                                value = myArray.GetValue(1, 19);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Trim() != "")
                                {
                                    if (!ExtensionMethods.IPCheck(str))
                                    {
                                        procInfo += "网管IP格式错误!!";
                                        isAllValid = false;
                                    }
                                }
                                else
                                {
                                    procInfo += "网管IP不可为空!!";
                                    isAllValid = false;
                                }
                                entity.NetManageIp = str;
                                value = myArray.GetValue(1, 20);
                                str = ExtensionMethods.ToStr(value);
                                if (str == "")
                                {
                                    procInfo += "WEB端口不可为空!!";
                                    isAllValid = false;
                                }
                                else if (str.Length > 40)
                                {
                                    procInfo += "WEB端口长度超过预定!!";
                                    isAllValid = false;
                                }
                                entity.WebPort = str;
                            }
                        }
                        value = myArray.GetValue(1, 17);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length > 30)
                        {
                            procInfo += "SNMP只读共同体长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.SnmpOnlyRead =str ;
                        value = myArray.GetValue(1, 18);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length > 30)
                        {
                            procInfo += "SNMP只写共同体长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.SnmpOnlyWrite = str;

                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveNetworkingMode NetworkingMode = new ArchiveNetworkingMode();
                        if (str != "")
                        {
                            NetworkingMode = networkingModeFacade.GetHql(str);
                            if (NetworkingMode == null)
                            {
                                procInfo += "该组网方式在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.NetWorkingModeId = NetworkingMode.Id;
                        }
                        else
                        {
                            procInfo += "组网方式为空!!";
                            isAllValid = false;
                        }
                        if (NetworkingMode != null)
                        {
                            if (NetworkingMode.ModeName == "MSAP")
                            {
                                value = myArray.GetValue(1, 22);
                                str = ExtensionMethods.ToStr(value);
                                if (str == "")
                                {
                                    procInfo += "占用槽位不可为空!!";
                                    isAllValid = false;
                                }
                                else if (str.Length > 20)
                                {
                                    procInfo += "占用槽位长度超过预定!!";
                                    isAllValid = false;
                                }
                                clieEquip.OccupySlot = str;
                                value = myArray.GetValue(1, 23);
                                str = ExtensionMethods.ToStr(value);
                                if (str == "")
                                {
                                    procInfo += "板卡类型不可为空!!";
                                    isAllValid = false;
                                }
                                else if (str.Length > 30)
                                {
                                    procInfo += "板卡类型长度超过预定!!";
                                    isAllValid = false;
                                }
                                clieEquip.BoardType = str;

                            }
                        }
                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        if (str == "")
                        {
                            procInfo += "设备占用端口不可为空!!";
                            isAllValid = false;
                        }
                        else if (str.Length > 20)
                        {
                            procInfo += "设备占用端口长度超过预定!!";
                            isAllValid = false;
                        }
                        clieEquip.OccupyPort = str;

                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        ArchivePortType portType = new ArchivePortType();
                        if (str != "")
                        {
                            portType = portTypeFacade.GetHql(str);
                            if (portType == null)
                            {
                                procInfo += "该端口类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                clieEquip.PortTypeId = portType.Id;
                        }
                        else
                        {
                            procInfo += "端口类型不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 26);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length > 200)
                        {
                            procInfo += "备注长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.Remark = str;
                        
                        entity.CreateUserId = userid;
                        if (isAllValid)
                            isAllValid = equipmentFacade.Save(entity, iFlag);
                        clieEquip.EquipId = entity.Id;
                        if (isAllValid)
                            isAllValid = clieEquipFacade.Save(clieEquip, iFlag);

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
            int sheetColumn = 26; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "Z" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn+2];
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
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn+1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow -2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/Equipment");
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
            int sheetColumn = 26; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "Z" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn+2];
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
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn+1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow -2) + "条，成功" + m + "条,未成功" + (sheetRow - 2- m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/Equipment");
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
