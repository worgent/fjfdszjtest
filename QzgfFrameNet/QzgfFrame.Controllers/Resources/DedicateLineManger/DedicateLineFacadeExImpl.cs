using System;
using System.Text.RegularExpressions;
using System.IO;
using System.Web;
using System.Data;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.NumberGroupManger.Domain;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Archives.SignalModelManger.Models;
using QzgfFrame.Archives.SignalModelManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
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
using QzgfFrame.Resources.LineEquipManger.Models;
using QzgfFrame.Resources.LineEquipManger.Domain;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Cop.PlanManager.Domain;
using Newtonsoft.Json;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;
namespace QzgfFrame.Controllers.Resources.DedicateLineManger 
{
    public class DedicateLineFacadeExImpl : DedicateLineFacadeEx
    {
        private FiberCoreFacade fiberCoreFacade { set; get; }
        private NumberGroupFacade numberGroupFacade { set; get; }
        private OtherInfoFacade otherInfoFacade { set; get; }
        private DedicateLineFacade dedicateLineFacade { set; get; }
        private BizTypeFacade bizTypeFacade { set; get; }
        private NetworkingModeFacade networkingModeFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        private SignalModelFacade signalModelFacade { set; get; }
        private OneSevenZeroFacade oneSevenZeroFacade { set; get; }
        private EquipmentFacade equipmentFacade { set; get; }
        private ClieEquipFacade clieEquipFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private LineEquipFacade lineEquipFacade { set; get; }
        private ClieScaleGradeFacade clieScaleGradeFacade { set; get; }
        private ClieServStarLeveFacade clieServStarLeveFacade { set; get; }
        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private PlanFacade planFacade { set; get; }


        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool SaveOSZL(OneSevenZeroLine entity, HttpFileCollectionBase uploadfiles, string savePath, string userid)
        {
            bool result = false;
            if (entity.Line.DediLine.ClieId == "0")
            {
                ResourceOneSevenZero OneSevenZero = oneSevenZeroFacade.Get(entity.OneSevenZero.Id);
                ResourceGroupClie groupclie =new  ResourceGroupClie();
                groupclie.ClieNo = OneSevenZero.GroupId;
                groupclie.ClieName = OneSevenZero.ClientCompanyName;
                ArchiveDistrict District = new ArchiveDistrict();
                District = districtFacade.GetHql(OneSevenZero.CityCounty);
                if (District != null)
                {
                    groupclie.DistrictId=District.Id;
                    groupclie.CityId=District.ParentId;
                }
                ArchiveCompany company = new ArchiveCompany();
                company = companyFacade.GetHql(OneSevenZero.MaintenanceCompany);
                if (company != null)
                {
                    groupclie.CompanyId=company.Id;
                }
                ArchiveClieScaleGrade ScaleGrade = new ArchiveClieScaleGrade();
                string[] strAry1 = Regex.Split(OneSevenZero.ImportLevel, "类", RegexOptions.IgnoreCase);
                ScaleGrade = clieScaleGradeFacade.GetHql(strAry1[0]);
                if (ScaleGrade != null)
                {
                    groupclie.ScaleGradeId=ScaleGrade.Id;
                }
                ArchiveClieServStarLeve ServStarLeve = new ArchiveClieServStarLeve();
                string[] strAry2 = Regex.Split(OneSevenZero.ClientGrade, "类", RegexOptions.IgnoreCase);
                ServStarLeve = clieServStarLeveFacade.GetHql(strAry2[0]);
                 if (ServStarLeve != null)
                {
                    groupclie.StarLeveId=ServStarLeve.Id;
                }
                 groupclie.GridIds = gridFacade.GetOSZHql(OneSevenZero.GroupinNet);
                 groupclie.ClieManager = OneSevenZero.Name;
                 groupclie.ClieManagerTel = OneSevenZero.ManagerTel;
                 groupclie.ClieAddress = OneSevenZero.CommunicateAddress;
                groupclie.State = 1;
                groupclie.CreateUserId = userid;
                result=groupClieFacade.Save(groupclie,"0");
                entity.Line.DediLine.ClieId = groupclie.Id;
            }
            entity.Line.DediLine.CreateUserId = userid;
            result = Save(entity.Line, uploadfiles, savePath);
            ResourceEquipment[] Equips = (ResourceEquipment[])JavaScriptConvert.DeserializeObject(entity.Line.equips, typeof(ResourceEquipment[]));
            int j = 0;
            foreach (ResourceEquipment equip in Equips)
            {
                equip.StartDatetime = entity.Line.DediLine.StartDateTime;
                equip.NetWorkingModeId = entity.Line.DediLine.NetWorkingModeId;
                result = equipmentFacade.Save(equip, j.ToString());
                ResourceClieEquip clieEquip = new ResourceClieEquip();
                clieEquip.ClieId = entity.Line.DediLine.ClieId;
                clieEquip.LineId = entity.Line.DediLine.Id;
                    clieEquip.EquipId = equip.Id;
                    result = clieEquipFacade.Save(clieEquip,j.ToString());
                j++;
            }
            result = oneSevenZeroFacade.Update(entity.OneSevenZero.Id);
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Save(DedicateLine entity, HttpFileCollectionBase uploadfiles, string savePath)
        {
            bool result = false;
            result = this.dedicateLineFacade.Save(entity.DediLine);
            bool reasult = false;
            //添加专线的时候，调用的添加巡检计划方法
            //“BizAssuranLeveId”业务保障等级id
            reasult = planFacade.AddLinePlan(entity.DediLine.BizAssuranLeveId);


            ResourceFiberCore[] FiberCores = (ResourceFiberCore[])JavaScriptConvert.DeserializeObject(entity.forces, typeof(ResourceFiberCore[]));
            int j = 0;
            foreach (ResourceFiberCore rf in FiberCores)
            {
                rf.ClieId = entity.DediLine.ClieId;
                rf.LineId = entity.DediLine.Id;
                result = fiberCoreFacade.Save(rf, j.ToString());
                j++;
            } 
            int k = 0;                
            if (entity.lineEquips != null)
            {
                ResourceLineEquip[] AlineEquipLists = (ResourceLineEquip[])JavaScriptConvert.DeserializeObject(entity.lineEquips, typeof(ResourceLineEquip[]));
                foreach (ResourceLineEquip alequip in AlineEquipLists)
                {
                    alequip.LineId = entity.DediLine.Id;
                    result = lineEquipFacade.Save(alequip, k.ToString());
                    k++;
                }
            }
            if (entity.ZlineEquips != null)
            {
                ResourceLineEquip[] ZlineEquipLists = (ResourceLineEquip[])JavaScriptConvert.DeserializeObject(entity.ZlineEquips, typeof(ResourceLineEquip[]));
                foreach (ResourceLineEquip zlequip in ZlineEquipLists)
                {
                    zlequip.LineId = entity.DediLine.Id;
                    result = lineEquipFacade.Save(zlequip, k.ToString());
                    k++;
                }
            }
            if (entity.numbers != null)
            {
                ResourceNumberGroup[] Numbers = (ResourceNumberGroup[])JavaScriptConvert.DeserializeObject(entity.numbers, typeof(ResourceNumberGroup[]));
                j = 0;
                foreach (ResourceNumberGroup ng in Numbers)
                {
                    ng.ClieId = entity.DediLine.ClieId;
                    ng.LineId = entity.DediLine.Id;
                    ng.OrderNo = j.ToString();
                    result = numberGroupFacade.Save(ng, j.ToString());
                    j++;
                }
            }
            if (!Directory.Exists(savePath))
            {
                Directory.CreateDirectory(savePath);
            }
            for (int i = 0; i < uploadfiles.Count; i++)
            {
                HttpPostedFileBase file = uploadfiles[i];
                //得到文件名字
                string strfilename = Path.GetFileName(file.FileName);
                string loadfilename = DateTime.Now.ToString("yyyyMMddHHmmss") + strfilename;
                string filepath = savePath + loadfilename;
                if (file.ContentLength > 0)
                {
                    file.SaveAs(filepath);
                    if (File.Exists(filepath))
                    {
                        //得到文件数组
                        byte[] fileData = new Byte[file.ContentLength];
                        file.InputStream.Position = 0; //此句很关键
                        file.InputStream.Read(fileData, 0, file.ContentLength);
                        //得到文件大小
                        int fileLength = file.ContentLength;
                        
                        //得到文件类型
                        string fileType = file.ContentType;
                        //自定义文件对象
                        ResourceOtherInfo otherinfo = new ResourceOtherInfo();
                        //otherinfo.FileData = fileData;
                        otherinfo.InfoFileName = strfilename;
                        otherinfo.InfoFileType = fileType;
                        otherinfo.InfoFileSize = fileLength;
                        otherinfo.LineId = entity.DediLine.Id;
                        otherinfo.LoadFileName = loadfilename;
                        result = otherInfoFacade.Save(otherinfo,i.ToString());
                    }
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(DedicateLine entity, HttpFileCollectionBase uploadfiles, string savePath, out string filename)
        {
            bool result = false;
            filename="";
            result = dedicateLineFacade.Update(entity.DediLine);
            //修改专线中“业务保障等级”时，调用的修改巡检计划方法
            //“DedicateLineId”专线id
            result = planFacade.UpdateLinePlan(entity.DediLine.Id);

            result = fiberCoreFacade.Delete(entity.DediLine.Id.ToString());
            ResourceFiberCore[] FiberCores = (ResourceFiberCore[])JavaScriptConvert.DeserializeObject(entity.forces, typeof(ResourceFiberCore[]));
            int j = 0;
            foreach (ResourceFiberCore rf in FiberCores)
            {
                rf.ClieId = entity.DediLine.ClieId;
                rf.LineId = entity.DediLine.Id;
                result = fiberCoreFacade.Save(rf,j.ToString());
                j++;
            }
            int k = 0;
            result = lineEquipFacade.Delete(entity.DediLine.Id.ToString());
            if (entity.lineEquips != null)
            {
                ResourceLineEquip[] AlineEquipLists = (ResourceLineEquip[])JavaScriptConvert.DeserializeObject(entity.lineEquips, typeof(ResourceLineEquip[]));
                foreach (ResourceLineEquip alequip in AlineEquipLists)
                {
                    alequip.LineId = entity.DediLine.Id;
                    result = lineEquipFacade.Save(alequip, k.ToString());
                    k++;
                }
            }
            if (entity.ZlineEquips != null)
            {
                ResourceLineEquip[] ZlineEquipLists = (ResourceLineEquip[])JavaScriptConvert.DeserializeObject(entity.ZlineEquips, typeof(ResourceLineEquip[]));
                foreach (ResourceLineEquip zlequip in ZlineEquipLists)
                {
                    zlequip.LineId = entity.DediLine.Id;
                    result = lineEquipFacade.Save(zlequip, k.ToString());
                    k++;
                }
            }
            result = numberGroupFacade.Delete(entity.DediLine.Id.ToString());
            if (entity.numbers != null)
            {
                ResourceNumberGroup[] Numbers = (ResourceNumberGroup[])JavaScriptConvert.DeserializeObject(entity.numbers, typeof(ResourceNumberGroup[]));
                j = 0;
                foreach (ResourceNumberGroup ng in Numbers)
                {
                    ng.ClieId = entity.DediLine.ClieId;
                    ng.LineId = entity.DediLine.Id;
                    ng.OrderNo = j.ToString();
                    result = numberGroupFacade.Save(ng,j.ToString());
                    j++;
                }
            }
            if (entity.delArys != null)
            {
                string[] idarr = entity.delArys.Split(',');
                string hql = "";
                foreach (var s in idarr)
                {
                    if (hql == "")
                        hql = " Id='" + s + "'";
                    else
                        hql += " or Id='" + s + "'";
                }
                IList<ResourceOtherInfo> OtherInfos  = otherInfoFacade.LoadAll("Id", hql);
                foreach (ResourceOtherInfo roi in OtherInfos)
                {
                   // string filepath = savePath + roi.InfoFileName;
                    filename += roi.LoadFileName + ";";
                    // File.Delete(filepath);
                }
                if (filename != "")
                    filename = filename.Substring(0, filename.Length - 1);
                result = otherInfoFacade.Delete(hql);
            }
            if (!Directory.Exists(savePath))
            {
                Directory.CreateDirectory(savePath);
            }
            for (int i = 0; i < uploadfiles.Count; i++)
            {
                HttpPostedFileBase file = uploadfiles[i];
                string strfilename = Path.GetFileName(file.FileName);
                string loadfilename = DateTime.Now.ToString("yyyyMMddHHmmss") + strfilename;
                if (file.ContentLength > 0)
                {
                    file.SaveAs(savePath + loadfilename);
                    //得到文件数组
                    byte[] fileData = new Byte[file.ContentLength];
                    file.InputStream.Position = 0; //此句很关键
                    file.InputStream.Read(fileData, 0, file.ContentLength);
                    //得到文件大小
                    int fileLength = file.ContentLength;
                    //得到文件名字
                   
                    //得到文件类型
                    string fileType = file.ContentType;
                    //自定义文件对象
                    ResourceOtherInfo otherinfo = new ResourceOtherInfo();
                    //otherinfo.FileData = fileData;
                    otherinfo.InfoFileName = strfilename;
                    otherinfo.InfoFileType = fileType;
                    otherinfo.InfoFileSize = fileLength;
                    otherinfo.LineId = entity.DediLine.Id;
                    otherinfo.LoadFileName = loadfilename;
                    result = otherInfoFacade.Save(otherinfo,i.ToString());
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id, out string filename)
        {
           bool result = false;
            result = dedicateLineFacade.Delete(id.ToString());
            result = fiberCoreFacade.Delete(id);
            result = numberGroupFacade.Delete(id);
            string[] idarr = id.Split(',');
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " LineId='" + s + "'";
                else
                    hql+=" or LineId='" + s + "'";
            }
            IList<ResourceOtherInfo> OtherInfos = otherInfoFacade.LoadAll("Id", hql);
            filename = "";
            foreach (ResourceOtherInfo roi in OtherInfos)
            {
                filename += roi.LoadFileName + ";";
            }
            if (filename != "")
            {
                filename = filename.Substring(0, filename.Length - 1);
                result = otherInfoFacade.Delete(hql);
            }
            
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool DeleteFalse(string id, out string filename, string editFlag,out bool DelFlag)
        {
            bool result = false;
            result = dedicateLineFacade.DeleteFalse(id.ToString(), out DelFlag);
            result = lineEquipFacade.DeleteFalse(id);
            string[] idarr = id.Split(',');
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " LineId='" + s + "'";
                else
                    hql += " or LineId='" + s + "'";
            }
            IList<ResourceOtherInfo> OtherInfos = otherInfoFacade.LoadAll("Id", hql);
            filename = "";
            foreach (ResourceOtherInfo roi in OtherInfos)
            {
                filename += roi.LoadFileName + ";";
            }
            if (filename != "")
            {
                filename = filename.Substring(0, filename.Length - 1);
                result = otherInfoFacade.DeleteFalse(hql);
            }

            return result;
        }
        public void DeleteFile(string fileNames, string savePath)
        {
            string[] fileAry = fileNames.Split(';');
            for (int k = 0; k < fileAry.Length; k++)
            {
                string filepath = savePath + fileAry[k];
                if (File.Exists(filepath))
                {
                    //string strsavePath = HttpContext.Current.Request.MapPath(filepath);
                    //  FileInfo file = new FileInfo(strsavePath);
                    //file.Delete();
                    File.Delete(filepath);
                }
            }
        }
        private bool CheckInternetHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 33)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "专线编号")
                {
                    procInfo += "文件中未找到'专线编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "状态")
                {
                    procInfo += "文件中未找到'状态'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团名称")
                {
                    procInfo += "文件中未找到'集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团编号")
                {
                    procInfo += "文件中未找到'集团编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属区县")
                {
                    procInfo += "文件中未找到'所属区县'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务类型")
                {
                    procInfo += "文件中未找到'业务类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务保障等级")
                {
                    procInfo += "文件中未找到'业务保障等级'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "组网方式")
                {
                    procInfo += "文件中未找到'组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "合同带宽")
                {
                    procInfo += "文件中未找到'合同带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "实际带宽")
                {
                    procInfo += "文件中未找到'实际带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "VLAN号")
                {
                    procInfo += "文件中未找到'VLAN号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网关地址")
                {
                    procInfo += "文件中未找到'网关地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "客户IP地址")
                {
                    procInfo += "文件中未找到'客户IP地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "子网掩码")
                {
                    procInfo += "文件中未找到'子网掩码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "城域网接入交换机")
                {
                    procInfo += "文件中未找到'城域网接入交换机'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "城域网交换机端口")
                {
                    procInfo += "文件中未找到'城域网交换机端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "机房传输设备")
                {
                    procInfo += "文件中未找到'机房传输设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "机房传输设备端口")
                {
                    procInfo += "文件中未找到'机房传输设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入基站名称")
                {
                    procInfo += "文件中未找到'接入基站名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "基站传输设备及端口")
                {
                    procInfo += "文件中未找到'基站传输设备及端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "电路编号")
                {
                    procInfo += "文件中未找到'电路编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "DDF架传输侧位置")
                {
                    procInfo += "文件中未找到'DDF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "DDF架设备侧位置")
                {
                    procInfo += "文件中未找到'DDF架设备侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入全业务机房名称")
                {
                    procInfo += "文件中未找到'接入全业务机房名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "OLT设备及端口")
                {
                    procInfo += "文件中未找到'OLT设备及端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "LOID")
                {
                    procInfo += "文件中未找到'LOID'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否接入本地网管")
                {
                    procInfo += "文件中未找到'是否接入本地网管'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 29);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否已接入省网管监控")
                {
                    procInfo += "文件中未找到'是否已接入省网管监控'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开通时间")
                {
                    procInfo += "文件中未找到'开通时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 31);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "纤芯资料")
                {
                    procInfo += "文件中未找到'纤芯资料'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 32);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务设备")
                {
                    procInfo += "文件中未找到'业务设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckInternetBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 35)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine entity = dedicateLineFacade.GetHql(str.Trim());
                    if (entity != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!"; 
                        
                    }
                } 
                value = myArray.GetValue(1, 2);
                if (value == null)
                {
                    procInfo += "状态为空!!";
                }
                else
                {
                    short state = ExtensionMethods.ConvertToState(value);
                    if (state == 4)
                    {
                        procInfo += "该状态不存在!!";

                    }
                }
                value = myArray.GetValue(1, 4);
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
                    procInfo += "集团编号不可为空!!";
                }
                value = myArray.GetValue(1, 5);
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
                value = myArray.GetValue(1, 6);
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
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizType archiveBizType = new ArchiveBizType();
                if (str != "")
                {
                    archiveBizType = bizTypeFacade.GetHql(str);
                    if (archiveBizType == null)
                        procInfo += "该业务类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务类型不可为空!!";
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                if (str != "")
                {
                    bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                    if (bizAssuranLeve == null)
                        procInfo += "该业务保障等级在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务保障等级不可为空!!";
                }
                value = myArray.GetValue(1, 9);
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
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 10)
                {
                    procInfo += "合同带宽不可为空或长度超限!!";
                }
                else
                {
                    string[] BandwidthAry = str.Split('M');
                    if (BandwidthAry.Length <= 1)
                        BandwidthAry = str.Split('m');
                    int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "合同带宽格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "实际带宽不可为空或长度超限!!";
                }
                else
                {
                    string[] BandwidthAry = str.Split('M');
                    if (BandwidthAry.Length <= 1)
                        BandwidthAry = str.Split('m');
                    int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "实际带宽格式不对或长度超限!!";
                    }
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    int netno = ExtensionMethods.ToInt(str);
                    if (netno == 0 || str.Length >10)
                    {
                        procInfo += "VLAN号格式不对,只能为数字或长度超限!!";
                    } 
                }
                else
                    procInfo += "VLAN号不可为空!!";

                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Length==0 ||str.Length>200)
                {
                    procInfo += "网关地址不可为空或长度超限!!";
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 200)
                {
                    procInfo += "客户IP地址不可为空或长度超限!!";
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 200)
                {
                    procInfo += "子网掩码不可为空或长度超限!!";
                }
                if (NetworkingMode != null)
                {
                    if (NetworkingMode.ModeName != "裸纤")
                    {
                        value = myArray.GetValue(1, 16);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "城域网接入交换机不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 17);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "城域网接入交换机端口不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName.Contains("MSTP") || NetworkingMode.ModeName == "MSAP")
                    {
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 20)
                        {
                            procInfo += "接入基站名称不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 80)
                        {
                            procInfo += "基站传输设备及端口不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length >40)
                        {
                            procInfo += "电路编号不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName == "协转")
                    {
                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "DDF架传输侧位置不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "DDF架设备侧位置不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName == "PON")
                    {
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "接入全业务机房名称不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 26);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 80)
                        {
                            procInfo += "OLT设备及端口不可为空或长度超限!!";
                        }
                    }
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否接入本地网管不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 29);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入本地网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否接入本地网管格式错误!!";

                    }
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否已接入省网管监控不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 31);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入省网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否已接入省网管监控格式错误!!";

                    }
                } 
                value = myArray.GetValue(1, 32);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value); 
                    if (dt == null)
                    {
                        procInfo += "开通时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "开通时间不可为空或格式错误!!";
                    isAllValid = false;
                }//纤芯
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    try
                    {
                        string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                        string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                        int i = 0;
                        foreach (string mstr in MidAry)
                        {
                            string[] MidIAry = mstr.Split('(');
                            if (MidIAry.Length <= 1)
                                MidIAry = mstr.Split('（');
                            if (MidIAry.Length > 1)
                            {
                                if (MidIAry[0].Length >= 50)
                                    procInfo += "纤芯格式错误!!";
                                string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                if (MidOAry.Length <= 1)
                                {
                                    string[] MidMAry = MidOAry[0].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[0].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                else
                                {
                                    if (MidOAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                    string[] MidMAry = MidOAry[1].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[1].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                i++;
                            }
                        }
                        if (endAry.Length > 1)
                        {
                            string[] endIAry = endAry[1].Split('(');
                            if (endIAry.Length <= 1)
                                endIAry = endAry[1].Split('（');
                            if (endIAry.Length > 1)
                            {
                                string[] MidMAry = endIAry[1].Split(')');
                                if (MidMAry.Length <= 1)
                                    MidMAry = endIAry[1].Split('）');
                            }
                        }
                    }
                    catch
                    {
                        procInfo += "纤芯格式错误!!";
                    }
                }
                value = myArray.GetValue(1, 34);
                str = ExtensionMethods.ToStr(value);
                string[] aequips = null;
                if (str.Length == 0)
                {
                    procInfo += "业务设备不可为空!!";
                }
                else
                {
                    aequips = str.Split(',');
                    foreach (string equip in aequips)
                    {
                        if (equip.Length > 40)
                        {
                            procInfo += "业务设备长度超过预定!!";
                            isAllValid = false;
                        }
                        else
                        {
                            ResourceEquipment Aequip = equipmentFacade.GetHql(equip,clie.Id);
                            if (Aequip == null)
                            {
                                procInfo += "该业务设备名称对应客户在系统中不存在!!";
                                isAllValid = false;
                            }
                        }
                    }
                } 
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadInternetBodyData(Array myArray, out string procInfo, out bool isUpdate, string userid,string iFlag)
        {
            procInfo = "";
            bool isAllValid = true; isUpdate = false;
            if (myArray.Length < 35)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceDedicateLine entity = new ResourceDedicateLine();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    entity = dedicateLineFacade.GetHql(str.Trim());
                    if (entity != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!";
                        isAllValid = false;
                    }
                    else
                    {
                        entity = new ResourceDedicateLine();
                        entity.ProductNo = str.Trim();
                        value = myArray.GetValue(1, 2);
                        if (value == null)
                        {
                            procInfo += "状态为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToState(value);
                            if (state == 4)
                            {
                                procInfo += "该状态不存在!!";
                                isAllValid = false;

                            }
                            else
                                entity.State = state;
                        } value = myArray.GetValue(1, 4);
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
                            {
                                entity.ClieId = clie.Id;
                            }
                        }
                        else
                        {
                            procInfo += "集团编号不可为空!!";
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
                        else
                        {
                            procInfo += "所属区县不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 6);
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
                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        if (str != "")
                        {
                            archiveBizType = bizTypeFacade.GetHql(str);
                            if (archiveBizType == null)
                            {
                                procInfo += "该业务类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                            {
                                entity.BizTypeId = archiveBizType.Id;
                            }
                        }
                        else
                        {
                            procInfo += "业务类型不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                        if (str != "")
                        {
                            bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                            if (bizAssuranLeve == null)
                            {
                                procInfo += "该业务保障等级在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizAssuranLeveId = bizAssuranLeve.Id;
                        }
                        else
                        {
                            procInfo += "业务保障等级不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 9);
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
                            procInfo += "组网方式不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 10)
                        {
                            procInfo += "合同带宽不可为空或长度超限!!";
                        }
                        else
                        {
                            string[] BandwidthAry = str.Split('M');
                            if (BandwidthAry.Length <= 1)
                                BandwidthAry = str.Split('m');
                            int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "合同带宽格式不对!!";
                                isAllValid = false;
                            }
                            entity.ContractBandwidth = BandwidthAry[0];
                        }
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 10)
                        {
                            procInfo += "实际带宽不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        else
                        {
                            string[] BandwidthAry = str.Split('M');
                            if (BandwidthAry.Length <= 1)
                                BandwidthAry = str.Split('m');
                            int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "实际带宽格式不对!!";
                                isAllValid = false;
                            }
                            entity.ActualBandwidth = BandwidthAry[0];
                        }
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            int netno = ExtensionMethods.ToInt(str);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "VLAN号格式不对,只能为数字或长度超限!!";
                                isAllValid = false;
                            }
                        }
                        else
                        {
                            procInfo += "VLAN号不可为空!!";
                            isAllValid = false;
                        }
                        entity.VLANNumber = str;
                        value = myArray.GetValue(1, 13);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "网关地址不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.GateWay = str;
                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "客户IP地址不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.IP = str;
                        value = myArray.GetValue(1, 15);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "子网掩码不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.SubnetMask = str;
                        if (NetworkingMode != null)
                        {
                            if (NetworkingMode.ModeName != "裸纤")
                            {
                                value = myArray.GetValue(1, 16);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "城域网接入交换机不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CityNetSwitch = str;
                                value = myArray.GetValue(1, 17);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "城域网接入交换机端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CityNetSwitchPort = str;
                            }

                            if (NetworkingMode.ModeName.Contains("MSTP") || NetworkingMode.ModeName == "MSAP")
                            {
                                value = myArray.GetValue(1, 20);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 20)
                                {
                                    procInfo += "接入基站名称不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.StationName = str;
                                value = myArray.GetValue(1, 21);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 80)
                                {
                                    procInfo += "基站传输设备及端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.StationTEquipAndPort = str;
                                value = myArray.GetValue(1, 22);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "电路编号不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CircuitNumber = str;
                            }
                            if (NetworkingMode.ModeName == "协转")
                            {
                                value = myArray.GetValue(1, 23);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "DDF架传输侧位置不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.DDFT = str;
                                value = myArray.GetValue(1, 24);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "DDF架设备侧位置不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.DDFE = str;
                            }
                            if (NetworkingMode.ModeName == "PON")
                            {
                                value = myArray.GetValue(1, 25);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "接入全业务机房名称不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.BizComputRoomName = str;
                                value = myArray.GetValue(1, 26);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 80)
                                {
                                    procInfo += "OLT设备及端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.OLTEquipAPort = str;
                            }
                        }
                        value = myArray.GetValue(1, 18);
                        entity.ComputRoomtTEquip = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 19);
                        entity.ComputRoomtTEquipPort = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 27);
                        entity.LOID = ExtensionMethods.ToStr(value);


                        value = myArray.GetValue(1, 28);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否接入本地网管不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 29);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入本地网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessLocalNet = 1;
                            entity.AccessLocalNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否接入本地网管格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessLocalNet = state;
                        }
                        value = myArray.GetValue(1, 30);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否已接入省网管监控不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 31);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入省网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessProviNet = 1;
                            entity.AccessProviNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否已接入省网管监控格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessProviNet = state;
                        }
                        value = myArray.GetValue(1, 32);
                        if (value != null)
                        {
                            DateTime? dt = ExtensionMethods.ToDateOANull(value);

                            if (dt == null)
                            {
                                procInfo += "开通时间不可为空或格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.StartDateTime = dt;
                        }
                        else
                        {
                            procInfo += "开通时间不可为空或格式错误!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 34);
                        str = ExtensionMethods.ToStr(value);
                        string[] aequips = null;
                        IList<ResourceEquipment> equips = new List<ResourceEquipment>();
                        if (str.Length == 0)
                        {
                            procInfo += "业务设备不可为空!!";
                        }
                        else
                        {
                            aequips = str.Split(',');
                            foreach (string equip in aequips)
                            {
                                if (equip.Length > 40)
                                {
                                    procInfo += "业务设备长度超过预定!!";
                                    isAllValid = false;
                                }
                                else
                                {
                                    ResourceEquipment Aequip = equipmentFacade.GetHql(equip,clie.Id);
                                    equips.Add(Aequip);
                                    if (Aequip == null)
                                    {
                                        procInfo += "该业务设备名称对应于该客户在系统中不存在!!";
                                        isAllValid = false;
                                    }
                                }
                            }
                        } 
                        value = myArray.GetValue(1, 35);
                        entity.Remark = ExtensionMethods.ToStr(value);

                        entity.CreateUserId = userid;

                        if (isAllValid)
                        {
                            isAllValid = dedicateLineFacade.Save(entity);
                            isAllValid = planFacade.AddLinePlan(entity.BizAssuranLeveId);
                            //添加业务设备
                            int k = 0;
                            foreach (ResourceEquipment equip in equips)
                            {
                                ResourceLineEquip lineEquip = new ResourceLineEquip();
                                lineEquip.EquipId = equip.Id;
                                lineEquip.LineId = entity.Id;
                                lineEquip.ClieId = clie.Id;
                                isAllValid = lineEquipFacade.Save(lineEquip, k.ToString());
                                k++;
                            }
                            //纤芯
                            value = myArray.GetValue(1, 33);
                            str = ExtensionMethods.ToStr(value);
                            if (str.Trim() != "")
                            {
                                try
                                {
                                    string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                                    string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                                    int i = 0;
                                    if (endAry.Length <= 1 || MidAry.Length <= 1)
                                    {
                                        procInfo += "纤芯格式错误!!";
                                        isAllValid = false;
                                    }
                                    foreach (string mstr in MidAry)
                                    {
                                        ResourceFiberCore fibercore = new ResourceFiberCore();
                                        fibercore.SeqNo = i.ToString();
                                        string[] MidIAry = mstr.Split('(');
                                        if (MidIAry.Length <= 1)
                                            MidIAry = mstr.Split('（');
                                        if (MidIAry.Length > 1)
                                        {
                                            fibercore.BaseStationName = MidIAry[0];
                                            string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                            if (MidOAry.Length <= 1)
                                            {
                                                string[] MidMAry = MidOAry[0].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[0].Split('）');
                                                fibercore.ODUStation = MidMAry[0];
                                            }
                                            else
                                            {
                                                fibercore.ODUStation = MidOAry[0];
                                                string[] MidMAry = MidOAry[1].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[1].Split('）');
                                                fibercore.Core = MidMAry[0];
                                            }
                                            fibercore.ClieId = entity.ClieId;
                                            fibercore.LineId = entity.Id;
                                            isAllValid = fiberCoreFacade.Save(fibercore, i.ToString());
                                            i++;
                                        }
                                    }
                                    ResourceFiberCore fcore = new ResourceFiberCore();
                                    fcore.SeqNo = i.ToString();

                                    if (endAry.Length > 1)
                                    {
                                        string[] endIAry = endAry[1].Split('(');
                                        if (endIAry.Length <= 1)
                                            endIAry = endAry[1].Split('（');
                                        fcore.ODUStation = endIAry[0];

                                        if (endIAry.Length > 1)
                                        {
                                            string[] MidMAry = endIAry[1].Split(')');
                                            if (MidMAry.Length <= 1)
                                                MidMAry = endIAry[1].Split('）');
                                            fcore.Core = MidMAry[0];
                                        }
                                        fcore.ClieId = entity.ClieId;
                                        fcore.LineId = entity.Id;
                                        isAllValid = fiberCoreFacade.Save(fcore, i.ToString());
                                    }
                                }
                                catch
                                {
                                    procInfo += "纤芯格式错误!!";
                                    isAllValid = false;
                                }
                            }
                        }
                    }
                }
            }
            return isAllValid;
        }
        private bool CheckIMSHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            if (myArray.Length < 33)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "专线编号")
                {
                    procInfo += "文件中未找到'专线编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "状态")
                {
                    procInfo += "文件中未找到'状态'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团名称")
                {
                    procInfo += "文件中未找到'集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团编号")
                {
                    procInfo += "文件中未找到'集团编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属区县")
                {
                    procInfo += "文件中未找到'所属区县'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务类型")
                {
                    procInfo += "文件中未找到'业务类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务保障等级")
                {
                    procInfo += "文件中未找到'业务保障等级'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "组网方式")
                {
                    procInfo += "文件中未找到'组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "合同带宽")
                {
                    procInfo += "文件中未找到'合同带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "实际带宽")
                {
                    procInfo += "文件中未找到'实际带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "VLAN号")
                {
                    procInfo += "文件中未找到'VLAN号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "网关地址")
                {
                    procInfo += "文件中未找到'网关地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "客户IP地址")
                {
                    procInfo += "文件中未找到'客户IP地址'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "子网掩码")
                {
                    procInfo += "文件中未找到'子网掩码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "城域网接入交换机")
                {
                    procInfo += "文件中未找到'城域网接入交换机'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "城域网交换机端口")
                {
                    procInfo += "文件中未找到'城域网交换机端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "机房传输设备")
                {
                    procInfo += "文件中未找到'机房传输设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "机房传输设备端口")
                {
                    procInfo += "文件中未找到'机房传输设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入基站名称")
                {
                    procInfo += "文件中未找到'接入基站名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "基站传输设备及端口")
                {
                    procInfo += "文件中未找到'基站传输设备及端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "电路编号")
                {
                    procInfo += "文件中未找到'电路编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "DDF架传输侧位置")
                {
                    procInfo += "文件中未找到'DDF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "DDF架设备侧位置")
                {
                    procInfo += "文件中未找到'DDF架设备侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入全业务机房名称")
                {
                    procInfo += "文件中未找到'接入全业务机房名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "OLT设备及端口")
                {
                    procInfo += "文件中未找到'OLT设备及端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "LOID")
                {
                    procInfo += "文件中未找到'LOID'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否接入本地网管")
                {
                    procInfo += "文件中未找到'是否接入本地网管'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 29);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否已接入省网管监控")
                {
                    procInfo += "文件中未找到'是否已接入省网管监控'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开通时间")
                {
                    procInfo += "文件中未找到'开通时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 31);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "纤芯资料")
                {
                    procInfo += "文件中未找到'纤芯资料'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 32);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "号码群")
                {
                    procInfo += "文件中未找到'号码群'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckIMSBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 35)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine entity = dedicateLineFacade.GetHql(str.Trim());
                    if (entity != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!"; 
                        
                    }
                } 
                value = myArray.GetValue(1, 2);
                if (value == null)
                {
                    procInfo += "状态为空!!";
                }
                else
                {
                    short state = ExtensionMethods.ConvertToState(value);
                    if (state == 4)
                    {
                        procInfo += "该状态不存在!!";

                    }
                }
                value = myArray.GetValue(1, 4);
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
                    procInfo += "集团编号不可为空!!";
                }
                value = myArray.GetValue(1, 5);
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
                value = myArray.GetValue(1, 6);
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
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizType archiveBizType = new ArchiveBizType();
                if (str != "")
                {
                    archiveBizType = bizTypeFacade.GetHql(str);
                    if (archiveBizType == null)
                        procInfo += "该业务类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务类型不可为空!!";
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                if (str != "")
                {
                    bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                    if (bizAssuranLeve == null)
                        procInfo += "该业务保障等级在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务保障等级不可为空!!";
                }
                value = myArray.GetValue(1, 9);
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
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 && str.Length > 10)
                {
                    procInfo += "合同带宽不可为空或长度超限!!";
                }
                else
                {
                    string[] BandwidthAry = str.Split('M');
                    if (BandwidthAry.Length <= 1)
                        BandwidthAry = str.Split('m');
                    int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "合同带宽格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 && str.Length > 10)
                {
                    procInfo += "实际带宽不可为空或长度超限!!";
                }
                else
                {
                    string[] BandwidthAry = str.Split('M');
                    if (BandwidthAry.Length <= 1)
                        BandwidthAry = str.Split('m');
                    int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "实际带宽格式不对!!";
                    }
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    int netno = ExtensionMethods.ToInt(str);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "VLAN号格式不对,只能为数字!!";
                    }
                }
                else
                {
                    procInfo += "VLAN号不可为空或长度超限!!";
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 200)
                {
                    procInfo += "网关地址不可为空或长度超限!!";
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 200)
                {
                    procInfo += "客户IP地址不可为空或长度超限!!";
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 200)
                {
                    procInfo += "子网掩码不可为空或长度超限!!";
                }
                if (NetworkingMode != null)
                {
                    if (NetworkingMode.ModeName != "裸纤")
                    {
                        value = myArray.GetValue(1, 16);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "城域网接入交换机不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 17);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "城域网接入交换机端口不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName.Contains("MSTP")  || NetworkingMode.ModeName == "MSAP")
                    {
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 20)
                        {
                            procInfo += "接入基站名称不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 80)
                        {
                            procInfo += "基站传输设备及端口不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "电路编号不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName == "协转")
                    {
                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "DDF架传输侧位置不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "DDF架设备侧位置不可为空或长度超限!!";
                        }
                    }
                    if (NetworkingMode.ModeName == "PON")
                    {
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "接入全业务机房名称不可为空或长度超限!!";
                        }
                        value = myArray.GetValue(1, 26);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "OLT设备及端口不可为空或长度超限!!";
                        }
                    }
                }

                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否接入本地网管不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 29);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入本地网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否接入本地网管格式错误!!";

                    }
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否已接入省网管监控不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 31);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入省网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否已接入省网管监控格式错误!!";

                    }
                } 
                value = myArray.GetValue(1, 32);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value); 
                    
                    if (dt == null)
                    {
                        procInfo += "开通时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "开通时间不可为空或格式错误!!";
                    isAllValid = false;
                }//纤芯
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    try
                    {
                        string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                        string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                        if (endAry.Length <= 1 || MidAry.Length <= 1)
                            procInfo += "纤芯格式错误!!";
                        int i = 0;
                        foreach (string mstr in MidAry)
                        {
                            string[] MidIAry = mstr.Split('(');
                            if (MidIAry.Length <= 1)
                                MidIAry = mstr.Split('（');
                            if (MidIAry.Length > 1)
                            {
                                if (MidIAry[0].Length >= 50)
                                    procInfo += "纤芯格式错误!!";
                                string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                if (MidOAry.Length <= 1)
                                {
                                    string[] MidMAry = MidOAry[0].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[0].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                else
                                {
                                    if (MidOAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                    string[] MidMAry = MidOAry[1].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[1].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                i++;
                            }
                        }
                        if (endAry.Length > 1)
                        {
                            string[] endIAry = endAry[1].Split('(');
                            if (endIAry.Length <= 1)
                                endIAry = endAry[1].Split('（');
                            if (endIAry.Length > 1)
                            {
                                string[] MidMAry = endIAry[1].Split(')');
                                if (MidMAry.Length <= 1)
                                    MidMAry = endIAry[1].Split('）');
                            }
                        }
                    }
                    catch
                    {
                        procInfo += "纤芯格式错误!!";
                    }
                }

                value = myArray.GetValue(1, 34);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "号码群不可为空!!";
                }
                else
                {
                    string[] arry = str.Split(',');
                    for (int j = 0; j < arry.Length; j++)
                    {
                        string[] strArry = arry[j].Split('-');
                        if (strArry.Length!=3) procInfo += "号码群格式错误!!";                        
                    }
                }

            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadIMSBodyData(Array myArray, out string procInfo, out bool isUpdate, string userid, string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;  isUpdate = false;
            if (myArray.Length < 35)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceDedicateLine entity = new ResourceDedicateLine();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine DedicateLine = dedicateLineFacade.GetHql(str.Trim());
                    if (DedicateLine != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!";
                        isAllValid = false;
                    }
                    else
                    {
                        entity = new ResourceDedicateLine();
                        entity.ProductNo = str.Trim();
                        value = myArray.GetValue(1, 2);
                        if (value == null)
                        {
                            procInfo += "状态为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToState(value);
                            if (state == 4)
                            {
                                procInfo += "该状态不存在!!";
                                isAllValid = false;

                            }
                            else
                                entity.State = state;
                        }
                        value = myArray.GetValue(1, 4);
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
                            {
                                entity.ClieId = clie.Id;
                            }
                        }
                        else
                        {
                            procInfo += "集团编号不可为空!!";
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
                        else
                        {
                            procInfo += "所属区县不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 6);
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
                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        if (str != "")
                        {
                            archiveBizType = bizTypeFacade.GetHql(str);
                            if (archiveBizType == null)
                            {
                                procInfo += "该业务类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizTypeId = archiveBizType.Id;
                        }
                        else
                        {
                            procInfo += "业务类型不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                        if (str != "")
                        {
                            bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                            if (bizAssuranLeve == null)
                            {
                                procInfo += "该业务保障等级在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizAssuranLeveId = bizAssuranLeve.Id;
                        }
                        else
                        {
                            procInfo += "业务保障等级不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 9);
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
                            procInfo += "组网方式不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 10)
                        {
                            procInfo += "合同带宽不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        else
                        {
                            string[] BandwidthAry = str.Split('M');
                            if (BandwidthAry.Length <= 1)
                                BandwidthAry = str.Split('m');
                            int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "合同带宽格式不对!!";
                                isAllValid = false;
                            }
                            entity.ContractBandwidth = BandwidthAry[0];
                        }
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 10)
                        {
                            procInfo += "实际带宽不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        else
                        {
                            string[] BandwidthAry = str.Split('M');
                            if (BandwidthAry.Length <= 1)
                                BandwidthAry = str.Split('m');
                            int netno = ExtensionMethods.ToInt(BandwidthAry[0]);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "实际带宽格式不对!!";
                                isAllValid = false;
                            }
                            entity.ActualBandwidth = BandwidthAry[0];
                        }
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            int netno = ExtensionMethods.ToInt(str);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "VLAN号格式不对,只能为数字!!";
                                isAllValid = false;
                            }
                        }
                        else
                        {
                            procInfo += "VLAN号不可为空!!";
                            isAllValid = false;
                        }
                        entity.VLANNumber = str;
                        value = myArray.GetValue(1, 13);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "网关地址不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.GateWay = str;
                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "客户IP地址不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.IP = str;
                        value = myArray.GetValue(1, 15);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 200)
                        {
                            procInfo += "子网掩码不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.SubnetMask = str;
                        if (NetworkingMode != null)
                        {
                            if (NetworkingMode.ModeName != "裸纤")
                            {
                                value = myArray.GetValue(1, 16);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "城域网接入交换机不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CityNetSwitch = str;
                                value = myArray.GetValue(1, 17);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "城域网接入交换机端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CityNetSwitchPort = str;
                            }
                            if (NetworkingMode.ModeName.Contains("MSTP") || NetworkingMode.ModeName == "MSAP")
                            {
                                value = myArray.GetValue(1, 20);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 20)
                                {
                                    procInfo += "接入基站名称不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.StationName = str;
                                value = myArray.GetValue(1, 21);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 80)
                                {
                                    procInfo += "基站传输设备及端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.StationTEquipAndPort = str;
                                value = myArray.GetValue(1, 22);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "电路编号不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.CircuitNumber = str;
                            }
                            if (NetworkingMode.ModeName == "协转")
                            {
                                value = myArray.GetValue(1, 23);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "DDF架传输侧位置不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.DDFT = str;
                                value = myArray.GetValue(1, 24);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "DDF架设备侧位置不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.DDFE = str;
                            }
                            if (NetworkingMode.ModeName == "PON")
                            {
                                value = myArray.GetValue(1, 25);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "接入全业务机房名称不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.BizComputRoomName = str;
                                value = myArray.GetValue(1, 26);
                                str = ExtensionMethods.ToStr(value);
                                if (str.Length == 0 || str.Length > 40)
                                {
                                    procInfo += "OLT设备及端口不可为空或长度超限!!";
                                    isAllValid = false;
                                }
                                entity.OLTEquipAPort = str;
                            }
                        }
                        value = myArray.GetValue(1, 18);
                        entity.ComputRoomtTEquip = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 19);
                        entity.ComputRoomtTEquipPort = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 27);
                        entity.LOID = ExtensionMethods.ToStr(value);


                        value = myArray.GetValue(1, 28);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否接入本地网管不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 29);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入本地网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessLocalNet = 1;
                            entity.AccessLocalNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否接入本地网管格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessLocalNet = state;
                        }
                        value = myArray.GetValue(1, 30);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否已接入省网管监控不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 31);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入省网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessProviNet = 1;
                            entity.AccessProviNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否已接入省网管监控格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessProviNet = state;
                        }
                        value = myArray.GetValue(1, 32);
                        if (value != null)
                        {
                            DateTime? dt = ExtensionMethods.ToDateOANull(value);
                            if (dt == null)
                            {
                                procInfo += "开通时间不可为空或格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.StartDateTime = dt;
                        }
                        else
                        {
                            procInfo += "开通时间不可为空或格式错误!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 35);
                        entity.Remark = ExtensionMethods.ToStr(value);
                        entity.CreateUserId = userid;
                        if (isAllValid)
                        {
                            isAllValid = dedicateLineFacade.Save(entity);
                            isAllValid = planFacade.AddLinePlan(entity.BizAssuranLeveId);

                            //纤芯
                            value = myArray.GetValue(1, 33);
                            str = ExtensionMethods.ToStr(value);
                            if (str.Trim() != "")
                            {
                                try
                                {
                                    string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                                    string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                                    int i = 0;
                                    if (endAry.Length <= 1 || MidAry.Length <= 1)
                                    {
                                        procInfo += "纤芯格式错误!!";
                                        isAllValid = false;
                                    }
                                    foreach (string mstr in MidAry)
                                    {
                                        ResourceFiberCore fibercore = new ResourceFiberCore();
                                        fibercore.SeqNo = i.ToString();
                                        string[] MidIAry = mstr.Split('(');
                                        if (MidIAry.Length <= 1)
                                            MidIAry = mstr.Split('（');
                                        if (MidIAry.Length > 1)
                                        {
                                            fibercore.BaseStationName = MidIAry[0];
                                            string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                            if (MidOAry.Length <= 1)
                                            {
                                                string[] MidMAry = MidOAry[0].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[0].Split('）');
                                                fibercore.ODUStation = MidMAry[0];
                                            }
                                            else
                                            {
                                                fibercore.ODUStation = MidOAry[0];
                                                string[] MidMAry = MidOAry[1].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[1].Split('）');
                                                fibercore.Core = MidMAry[0];
                                            }
                                            fibercore.ClieId = entity.ClieId;
                                            fibercore.LineId = entity.Id;
                                            isAllValid = fiberCoreFacade.Save(fibercore, i.ToString());
                                            i++;
                                        }
                                    }
                                    ResourceFiberCore fcore = new ResourceFiberCore();
                                    fcore.SeqNo = i.ToString();

                                    if (endAry.Length > 1)
                                    {
                                        string[] endIAry = endAry[1].Split('(');
                                        if (endIAry.Length <= 1)
                                            endIAry = endAry[1].Split('（');
                                        fcore.ODUStation = endIAry[0];

                                        if (endIAry.Length > 1)
                                        {
                                            string[] MidMAry = endIAry[1].Split(')');
                                            if (MidMAry.Length <= 1)
                                                MidMAry = endIAry[1].Split('）');
                                            fcore.Core = MidMAry[0];
                                        }
                                        fcore.ClieId = entity.ClieId;
                                        fcore.LineId = entity.Id;
                                        isAllValid = fiberCoreFacade.Save(fcore, i.ToString());
                                    }
                                }
                                catch
                                {
                                    procInfo += "纤芯格式错误!!";
                                    isAllValid = false;
                                }
                            }
                        }

                        //号码群
                        value = myArray.GetValue(1, 34);
                        str = ExtensionMethods.ToStr(value);
                        if (str != "")
                        {
                            if (isAllValid)
                            {
                                string[] arry = str.Split(',');
                                for (int j = 0; j < arry.Length; j++)
                                {
                                    string[] strArry = arry[j].Split('-');
                                    if (strArry.Length != 3)
                                    {
                                        procInfo += "号码群格式错误!!";
                                        isAllValid = false;
                                    }
                                    else
                                    {
                                        ResourceNumberGroup rng = new ResourceNumberGroup();
                                        rng.OrderNo = j.ToString();
                                        rng.ClieId = entity.ClieId;
                                        rng.LineId = entity.Id;
                                        rng.TelNumber = strArry[0];
                                        rng.UserName = strArry[1];
                                        rng.PassWord = strArry[2];
                                        isAllValid = numberGroupFacade.Save(rng, (j).ToString());
                                    }
                                }
                            }
                        }
                        else
                            procInfo += "号码群不可为空!!";
                    }
                }
            }
            return isAllValid;
        }
        private bool CheckVOIPHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 27)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "专线编号")
                {
                    procInfo += "文件中未找到'专线编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "状态")
                {
                    procInfo += "文件中未找到'状态'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团名称")
                {
                    procInfo += "文件中未找到'集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "集团编号")
                {
                    procInfo += "文件中未找到'集团编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属区县")
                {
                    procInfo += "文件中未找到'所属区县'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务类型")
                {
                    procInfo += "文件中未找到'业务类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务保障等级")
                {
                    procInfo += "文件中未找到'业务保障等级'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "组网方式")
                {
                    procInfo += "文件中未找到'组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "速率")
                {
                    procInfo += "文件中未找到'速率'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "中继数量")
                {
                    procInfo += "文件中未找到'中继数量'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "信令方式")
                {
                    procInfo += "文件中未找到'信令方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "电话号码")
                {
                    procInfo += "文件中未找到'电话号码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "局端设备")
                {
                    procInfo += "文件中未找到'局端设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "局端端口")
                {
                    procInfo += "文件中未找到'局端端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端DDF架传输侧位置")
                {
                    procInfo += "文件中未找到'A端DDF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端DDF架设备侧位置")
                {
                    procInfo += "文件中未找到'A端DDF架设备侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入基站名称")
                {
                    procInfo += "文件中未找到'接入基站名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "基站传输设备及端口")
                {
                    procInfo += "文件中未找到'基站传输设备及端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端DDF架传输侧位置")
                {
                    procInfo += "文件中未找到'Z端DDF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端DDF架设备侧位置")
                {
                    procInfo += "文件中未找到'Z端DDF架设备侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "电路编号")
                {
                    procInfo += "文件中未找到'电路编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否接入本地网管")
                {
                    procInfo += "文件中未找到'是否接入本地网管'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否已接入省网管监控")
                {
                    procInfo += "文件中未找到'是否已接入省网管监控'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开通时间")
                {
                    procInfo += "文件中未找到'开通时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "纤芯资料")
                {
                    procInfo += "文件中未找到'纤芯资料'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckVOIPBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 29)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine entity = dedicateLineFacade.GetHql(str.Trim());
                    if (entity != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!"; 
                        
                    }
                } 
                value = myArray.GetValue(1, 2);
                if (value == null)
                {
                    procInfo += "状态为空!!";
                }
                else
                {
                    short state = ExtensionMethods.ConvertToState(value);
                    if (state == 4)
                    {
                        procInfo += "该状态不存在!!";

                    }
                }
                value = myArray.GetValue(1, 4);
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
                    procInfo += "集团编号不可为空!!";
                }
                value = myArray.GetValue(1, 5);
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
                value = myArray.GetValue(1, 6);
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
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizType archiveBizType = new ArchiveBizType();
                if (str != "")
                {
                    archiveBizType = bizTypeFacade.GetHql(str);
                    if (archiveBizType == null)
                        procInfo += "该业务类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务类型不可为空!!";
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                if (str != "")
                {
                    bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                    if (bizAssuranLeve == null)
                        procInfo += "该业务保障等级在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务保障等级不可为空!!";
                }
                value = myArray.GetValue(1, 9);
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
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "速率不可为空!!";
                }
                else
                {
                    string[] t1 = Regex.Split(str, "Mbit/s", RegexOptions.IgnoreCase);
                    Decimal? netno = ExtensionMethods.ToDecimalNull(t1[0]);
                    if (netno == null || str.Length > 20)
                    {
                        procInfo += "中断数量格式不对,只能为数字!!";
                    }
                } 
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    int netno = ExtensionMethods.ToInt(str);
                    if (netno == 0 || str.Length > 10)
                    {
                        procInfo += "中断数量格式不对,只能为数字!!";
                    }
                }
                else
                {
                    procInfo += "中断数量不可为空!!";
                } 
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                ArchiveSignalModel signalModel = new ArchiveSignalModel();
                if (str != "")
                {
                    signalModel = signalModelFacade.GetHql(str);
                    if (signalModel == null)
                        procInfo += "该信令方式在系统中不存在!!";
                }
                else
                {
                    procInfo += "信令方式不可为空!!";
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 20)
                {
                    procInfo += "接入基站名称不可为空!!";
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 80)
                {
                    procInfo += "基站传输设备及端口不可为空!!";
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "电路编号不可为空!!";
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否接入本地网管不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 24);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入本地网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否接入本地网管格式错误!!";

                    }
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否已接入省网管监控不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 26);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入省网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否已接入省网管监控格式错误!!";

                    }
                } 
                value = myArray.GetValue(1, 27);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "开通时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "开通时间不可为空或格式错误!!";
                    isAllValid = false;
                }
                //纤芯
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    try
                    {
                        string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                        string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                        if (endAry.Length <= 1 || MidAry.Length <= 1)
                            procInfo += "纤芯格式错误!!";
                        int i = 0;
                        foreach (string mstr in MidAry)
                        {
                            string[] MidIAry = mstr.Split('(');
                            if (MidIAry.Length <= 1)
                                MidIAry = mstr.Split('（');
                            if (MidIAry.Length > 1)
                            {
                                if (MidIAry[0].Length >= 50)
                                    procInfo += "纤芯格式错误!!";
                                string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                if (MidOAry.Length <= 1)
                                {
                                    string[] MidMAry = MidOAry[0].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[0].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                else
                                {
                                    if (MidOAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                    string[] MidMAry = MidOAry[1].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[1].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误!!";
                                }
                                i++;
                            }
                        }
                        if (endAry.Length > 1)
                        {
                            string[] endIAry = endAry[1].Split('(');
                            if (endIAry.Length <= 1)
                                endIAry = endAry[1].Split('（');
                            if (endIAry.Length > 1)
                            {
                                string[] MidMAry = endIAry[1].Split(')');
                                if (MidMAry.Length <= 1)
                                    MidMAry = endIAry[1].Split('）');
                            }
                        }
                    }
                    catch
                    {
                        procInfo += "纤芯格式错误!!";
                    }
                }

            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadVOIPBodyData(Array myArray, out string procInfo, out bool isUpdate, string userid, string iFlag)
        {
            procInfo = "";
            bool isAllValid = true; isUpdate = false;
            if (myArray.Length < 29)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceDedicateLine entity = new ResourceDedicateLine();
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine DedicateLine = dedicateLineFacade.GetHql(str.Trim());
                    if (DedicateLine != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!"; 
                        isAllValid = false;
                    }
                    else
                    {
                        entity = new ResourceDedicateLine();
                        entity.ProductNo = str.Trim();

                        value = myArray.GetValue(1, 2);
                        if (value == null)
                        {
                            procInfo += "状态为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToState(value);
                            if (state == 4)
                            {
                                procInfo += "该状态不存在!!";
                                isAllValid = false;

                            }
                            else
                                entity.State = state;
                        } value = myArray.GetValue(1, 4);
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
                            {
                                entity.ClieId = clie.Id;
                            }
                        }
                        else
                        {
                            procInfo += "集团编号不可为空!!";
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
                        else
                        {
                            procInfo += "所属区县不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 6);
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
                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        if (str != "")
                        {
                            archiveBizType = bizTypeFacade.GetHql(str);
                            if (archiveBizType == null)
                            {
                                procInfo += "该业务类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizTypeId = archiveBizType.Id;
                        }
                        else
                        {
                            procInfo += "业务类型不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                        if (str != "")
                        {
                            bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                            if (bizAssuranLeve == null)
                            {
                                procInfo += "该业务保障等级在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizAssuranLeveId = bizAssuranLeve.Id;
                        }
                        else
                        {
                            procInfo += "业务保障等级不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 9);
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
                            procInfo += "组网方式不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);

                        if (str.Trim() == "")
                        {
                            procInfo += "速率不可为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            string[] t1 = Regex.Split(str, "Mbit/s", RegexOptions.IgnoreCase);
                            Decimal? netno = ExtensionMethods.ToDecimalNull(t1[0]);
                            if (netno == null || str.Length > 20)
                            {
                                procInfo += "中断数量格式不对,只能为数字!!";
                                isAllValid = false;
                            }
                            entity.Rate = t1[0];
                        }
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            int netno = ExtensionMethods.ToInt(str);
                            if (netno == 0 || str.Length > 10)
                            {
                                procInfo += "中断数量格式不对,只能为数字!!";
                                isAllValid = false;
                            }
                        }
                        else
                        {
                            procInfo += "中断数量不可为空!!";
                            isAllValid = false;
                        }
                        entity.RalayNumber = str;

                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveSignalModel signalModel = new ArchiveSignalModel();
                        if (str != "")
                        {
                            signalModel = signalModelFacade.GetHql(str);
                            if (signalModel == null)
                            {
                                procInfo += "该信令方式在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.SignalModel = signalModel.Id;
                        }
                        else
                        {
                            procInfo += "信令方式不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 13);
                        entity.TelNumber = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 14);
                        entity.TermiEquip = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 15);
                        entity.TermiPort = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 16);
                        entity.ADDFT = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 17);
                        entity.ADDFE = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 18);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 20)
                        {
                            procInfo += "接入基站名称不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.StationName = str;
                        value = myArray.GetValue(1, 19);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 80)
                        {
                            procInfo += "基站传输设备及端口不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.StationTEquipAndPort = str;
                        value = myArray.GetValue(1, 20);
                        entity.ZDDFT = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 21);
                        entity.ZDDFE = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "电路编号不可为空或长度超限!!";
                            isAllValid = false;
                        }
                        entity.CircuitNumber = str;
                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否接入本地网管不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 24);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入本地网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessLocalNet = 1;
                            entity.AccessLocalNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否接入本地网管格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessLocalNet = state;
                        }
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否已接入省网管监控不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 26);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入省网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessProviNet = 1;
                            entity.AccessProviNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否已接入省网管监控格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessProviNet = state;
                        }
                        entity.IsAccessProviNet = ExtensionMethods.ObjStrToShort(value);
                        value = myArray.GetValue(1, 27);
                        if (value != null)
                        {
                            DateTime? dt = ExtensionMethods.ToDateOANull(value);
                            if (dt == null)
                            {
                                procInfo += "开通时间不可为空或格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.StartDateTime = dt;
                        }
                        else
                        {
                            procInfo += "开通时间不可为空或格式错误!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 29);
                        entity.Remark = ExtensionMethods.ToStr(value);
                        entity.CreateUserId = userid;

                        if (isAllValid)
                        {
                            isAllValid = dedicateLineFacade.Save(entity);
                            isAllValid = planFacade.AddLinePlan(entity.BizAssuranLeveId);

                            //纤芯
                            value = myArray.GetValue(1, 28);
                            str = ExtensionMethods.ToStr(value);
                            if (str.Trim() != "")
                            {
                                try
                                {
                                    string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                                    string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                                    int i = 0;
                                    if (endAry.Length <= 1 || MidAry.Length <= 1)
                                    {
                                        isAllValid = false;
                                        procInfo += "纤芯格式错误!!";
                                    }
                                    foreach (string mstr in MidAry)
                                    {
                                        ResourceFiberCore fibercore = new ResourceFiberCore();
                                        fibercore.SeqNo = i.ToString();
                                        string[] MidIAry = mstr.Split('(');
                                        if (MidIAry.Length <= 1)
                                            MidIAry = mstr.Split('（');
                                        if (MidIAry.Length > 1)
                                        {
                                            fibercore.BaseStationName = MidIAry[0];
                                            string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                            if (MidOAry.Length <= 1)
                                            {
                                                string[] MidMAry = MidOAry[0].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[0].Split('）');
                                                fibercore.ODUStation = MidMAry[0];
                                            }
                                            else
                                            {
                                                fibercore.ODUStation = MidOAry[0];
                                                string[] MidMAry = MidOAry[1].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[1].Split('）');
                                                fibercore.Core = MidMAry[0];
                                            }
                                            fibercore.ClieId = entity.ClieId;
                                            fibercore.LineId = entity.Id;
                                            isAllValid = fiberCoreFacade.Save(fibercore, i.ToString());
                                            i++;
                                        }
                                    }
                                    ResourceFiberCore fcore = new ResourceFiberCore();
                                    fcore.SeqNo = i.ToString();

                                    if (endAry.Length > 1)
                                    {
                                        string[] endIAry = endAry[1].Split('(');
                                        if (endIAry.Length <= 1)
                                            endIAry = endAry[1].Split('（');
                                        fcore.ODUStation = endIAry[0];

                                        if (endIAry.Length > 1)
                                        {
                                            string[] MidMAry = endIAry[1].Split(')');
                                            if (MidMAry.Length <= 1)
                                                MidMAry = endIAry[1].Split('）');
                                            fcore.Core = MidMAry[0];
                                        }
                                        fcore.ClieId = entity.ClieId;
                                        fcore.LineId = entity.Id;
                                        isAllValid = fiberCoreFacade.Save(fcore, i.ToString());
                                    }
                                }
                                catch
                                {
                                    procInfo += "纤芯格式错误!!";
                                    isAllValid = false;
                                }
                            }
                        }
                    }
                }
            }
            return isAllValid;
        }
        private bool CheckWideHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 40)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "专线编号")
                {
                    procInfo += "文件中未找到'专线编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "状态")
                {
                    procInfo += "文件中未找到'状态'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
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
                if (str.Trim() != "业务类型")
                {
                    procInfo += "文件中未找到'业务类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务保障等级")
                {
                    procInfo += "文件中未找到'业务保障等级'列";
                    isAllValid = false;
                }
                //update by cpe 20120314
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "业务描述")
                {
                    procInfo += "文件中未找到'业务描述'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "合同带宽")
                {
                    procInfo += "文件中未找到'合同带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "实际带宽")
                {
                    procInfo += "文件中未找到'实际带宽'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端使用集团名称")
                {
                    procInfo += "文件中未找到'A端使用集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端集团编号")
                {
                    procInfo += "文件中未找到'A端集团编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端组网方式")
                {
                    procInfo += "文件中未找到'A端组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端接入站点")
                {
                    procInfo += "文件中未找到'A端接入站点'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端接入机房")
                {
                    procInfo += "文件中未找到'A端接入机房'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端传输设备名称")
                {
                    procInfo += "文件中未找到'A端传输设备名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端传输设备端口")
                {
                    procInfo += "文件中未找到'A端传输设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端业务设备")
                {
                    procInfo += "文件中未找到'A端业务设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端业务设备端口")
                {
                    procInfo += "文件中未找到'A端业务设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端DDF或ODF架传输侧位置")
                {
                    procInfo += "文件中未找到'A端DDF或ODF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "A端DDF或ODF架业务设备侧位置")
                {
                    procInfo += "文件中未找到'A端DDF或ODF架业务设备侧位置'列";
                    isAllValid = false;
                }
                //以下为Z端信息
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端使用集团名称")
                {
                    procInfo += "文件中未找到'Z端使用集团名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端集团编号")
                {
                    procInfo += "文件中未找到'Z端集团编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端组网方式")
                {
                    procInfo += "文件中未找到'Z端组网方式'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端接入站点")
                {
                    procInfo += "文件中未找到'Z端接入站点'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端接入机房")
                {
                    procInfo += "文件中未找到'Z端接入机房'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端传输设备名称")
                {
                    procInfo += "文件中未找到'Z端传输设备名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端传输设备端口")
                {
                    procInfo += "文件中未找到'Z端传输设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端业务设备")
                {
                    procInfo += "文件中未找到'Z端业务设备'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 29);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端业务设备端口")
                {
                    procInfo += "文件中未找到'Z端业务设备端口'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端DDF或ODF架传输侧位置")
                {
                    procInfo += "文件中未找到'Z端DDF或ODF架传输侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 31);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "Z端DDF架或ODF设备侧位置")
                {
                    procInfo += "文件中未找到'Z端DDF或ODF架业务设备侧位置'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 32);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "开通时间")
                {
                    procInfo += "文件中未找到'开通时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "电路编号")
                {
                    procInfo += "文件中未找到'电路编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 34);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "纤芯资料")
                {
                    procInfo += "文件中未找到'纤芯资料'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 35);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "专线类型")
                {
                    procInfo += "文件中未找到'专线类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 36);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否接入本地网管")
                {
                    procInfo += "文件中未找到'是否接入本地网管'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 37);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入本地网管平台名称")
                {
                    procInfo += "文件中未找到'接入本地网管平台名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 38);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否已接入省网管监控")
                {
                    procInfo += "文件中未找到'是否已接入省网管监控'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 39);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "接入省网管平台名称")
                {
                    procInfo += "文件中未找到'接入省网管平台名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 40);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckWideBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 40)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    ResourceDedicateLine entity = dedicateLineFacade.GetHql(str.Trim());
                    if (entity != null)
                    {
                        procInfo += "该专线编号在系统中已经存在,请确认是否是您所录入，需要修改请删除系统中的在进行导入!!";
                    }
                }
                value = myArray.GetValue(1, 2);
                if (value == null)
                {
                    procInfo += "状态为空!!";
                }
                else
                {
                    short state = ExtensionMethods.ConvertToState(value);
                    if (state == 4)
                    {
                        procInfo += "该状态不存在!!";

                    }
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                ArchiveDistrict district = new ArchiveDistrict();
                if (str.Trim() != "")
                {
                    district = districtFacade.GetHql(str);
                    if (district == null)
                        procInfo += "该所属区县在系统中不存在!!";
                }
                else
                    procInfo += "所属区县不可为空!!";
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
                value = myArray.GetValue(1,5);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizType archiveBizType = new ArchiveBizType();
                if (str != "")
                {
                    archiveBizType = bizTypeFacade.GetHql(str);
                    if (archiveBizType == null)
                        procInfo += "该业务类型在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务类型不可为空!!";
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                if (str != "")
                {
                    bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                    if (bizAssuranLeve == null)
                        procInfo += "该业务保障等级在系统中不存在!!";
                }
                else
                {
                    procInfo += "业务保障等级不可为空!!";
                }

                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == ""&&str.Length>100)
                {
                    procInfo += "业务描述不可为空!!";
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "合同带宽不可为空!!";
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "实际带宽不可为空!!";
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                string acliename = str;
                if (str == "")
                {
                    procInfo += "该集团名称不可为空!!";
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                ResourceGroupClie clie = new ResourceGroupClie();
                if (str.Trim() != "")
                {
                    clie = groupClieFacade.GetHql(str);
                    if (clie == null)
                        procInfo += "该A端集团编号在系统中不存在!!";
                    else
                    {
                        if (acliename != clie.ClieName)
                        {
                            procInfo += "所填的A端集团编号跟A端使用集团名称不对应!!";
                        }
                    }
                }
                else
                {
                    procInfo += "A端集团编号不可为空!!";
                }

                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                ArchiveNetworkingMode NetworkingMode = new ArchiveNetworkingMode();
                if (str != "")
                {
                    NetworkingMode = networkingModeFacade.GetHql(str);
                    if (NetworkingMode == null)
                        procInfo += "该A端组网方式在系统中不存在!!";
                }
                else
                {
                    procInfo += "A端组网方式不可为空!!";
                }

                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 20)
                {
                    procInfo += "A端接入站点不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "A端接入机房不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "A端传输设备名称不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "A端传输设备端口不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                string abizport = str;
                if ( str.Length > 40)
                {
                    procInfo += "A端业务设备端口长度超过预定!!";
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 )
                {
                    procInfo += "A端业务设备不可为空!!";
                }
                else
                {
                    string[] aequips = str.Split(',');
                    foreach(string equip in aequips){
                        if (equip.Length > 40)
                        {
                            procInfo += "A端业务设备长度超过预定!!";
                        }
                        else
                        {
                            ResourceEquipment Aequip = equipmentFacade.GetHql(equip,clie.Id);
                            if (Aequip == null)
                            {
                                procInfo += "该A端业务设备名称对应A端客户在系统中不存在!!";
                                isAllValid = false;
                            }
                        }
                    }
                } 
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "" || str.Length > 40)
                {
                    procInfo += "A端DDF或ODF架传输侧位置不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Length > 40)
                {
                    procInfo += "A端DDF或ODF架业务设备侧位置不可为空或长度超过预定!!";
                }


                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                string zcliename = str;
                if (str == "")
                {
                    procInfo += "该Z端集团名称不可为空!!";
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                ResourceGroupClie zclie = new ResourceGroupClie();
                if (str.Trim() != "")
                {
                    zclie = groupClieFacade.GetHql(str);
                    if (zclie == null)
                        procInfo += "该Z端集团编号在系统中不存在!!";
                    else
                    {
                        if (zcliename != zclie.ClieName)
                        {
                            procInfo += "所填的Z端集团编号跟Z端使用集团名称不对应!!";
                        }
                    }
                }
                else
                {
                    procInfo += "Z端集团编号不可为空!!";
                }

                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                ArchiveNetworkingMode ZNetworkingMode = new ArchiveNetworkingMode();
                if (str != "")
                {
                    ZNetworkingMode = networkingModeFacade.GetHql(str);
                    if (ZNetworkingMode == null)
                        procInfo += "该A端组网方式在系统中不存在!!";
                }
                else
                {
                    procInfo += "A端组网方式不可为空!!";
                }

                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 20)
                {
                    procInfo += "Z端接入站点不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "Z端接入机房不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "Z端传输设备名称不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "Z端传输设备端口不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 29);
                str = ExtensionMethods.ToStr(value);
                string zbizport = str;
                if (str.Length > 40)
                {
                    procInfo += "Z端业务设备端口长度超过预定!!";
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 )
                {
                    procInfo += "Z端业务设备不可为空或长度超过预定!!";
                }
                else
                {
                    string[] zequips = str.Split(',');
                    foreach (string equip in zequips)
                    {
                        if (equip.Length > 40)
                        {
                            procInfo += "Z端业务设备长度超过预定!!";
                            isAllValid = false;
                        }
                        else
                        {
                            ResourceEquipment Zequip = equipmentFacade.GetHql(equip,zclie.Id);
                            if (Zequip == null)
                            {
                                procInfo += "该Z端业务设备名称对应Z端客户在系统中不存在!!";
                                isAllValid = false;
                            }
                        }
                    }
                } 
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "Z端DDF或ODF架传输侧位置不可为空或长度超过预定!!";
                }
                value = myArray.GetValue(1, 31);
                str = ExtensionMethods.ToStr(value);
                if ( str.Length > 40)
                {
                    procInfo += "Z端DDF或ODF架业务设备侧位置不可为空或长度超过预定!!";
                }

                value = myArray.GetValue(1, 32);
                if (value != null)
                {
                    DateTime? dt = ExtensionMethods.ToDateOANull(value);
                    if (dt == null)
                    {
                        procInfo += "开通时间不可为空或格式错误!!";
                        isAllValid = false;
                    }
                }
                else
                {
                    procInfo += "开通时间不可为空或格式错误!!";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Length == 0 || str.Length > 40)
                {
                    procInfo += "电路编号不可为空或长度超过预定!!";
                }
                //纤芯
                value = myArray.GetValue(1, 34);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    try
                    {
                        string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                        string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                        if (endAry.Length <= 1 || MidAry.Length <= 1)
                            procInfo += "纤芯格式错误 !!";
                        int i = 0;
                        foreach (string mstr in MidAry)
                        {
                            string[] MidIAry = mstr.Split('(');
                            if (MidIAry.Length <= 1)
                                MidIAry = mstr.Split('（');
                            if (MidIAry.Length > 1)
                            {
                                if (MidIAry[0].Length >= 50)
                                    procInfo += "纤芯格式错误 !!";
                                string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                if (MidOAry.Length <= 1)
                                {
                                    string[] MidMAry = MidOAry[0].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[0].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误 !!";
                                }
                                else
                                {
                                    if (MidOAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误 !!";
                                    string[] MidMAry = MidOAry[1].Split(')');
                                    if (MidMAry.Length <= 1)
                                        MidMAry = MidOAry[1].Split('）');
                                    if (MidMAry[0].Length >= 100)
                                        procInfo += "纤芯格式错误 !!";
                                }
                                i++;
                            }
                        }
                        if (endAry.Length > 1)
                        {
                            string[] endIAry = endAry[1].Split('(');
                            if (endIAry.Length <= 1)
                                endIAry = endAry[1].Split('（');
                            if (endIAry.Length > 1)
                            {
                                string[] MidMAry = endIAry[1].Split(')');
                                if (MidMAry.Length <= 1)
                                    MidMAry = endIAry[1].Split('）');
                            }
                        }
                    }
                    catch
                    {
                        procInfo += "纤芯格式错误 !!";
                    }
                }

                value = myArray.GetValue(1, 35);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "专线类型不可为空!!";
                }
                else
                {
                    string state = ExtensionMethods.ConvertToLineType(value);
                    if (state == "4")
                    {
                        procInfo += "该专线类型不存在!!";

                    }
                }
                value = myArray.GetValue(1, 36);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否接入本地网管不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 37);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入本地网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否接入本地网管格式错误!!";

                    }
                }
                value = myArray.GetValue(1, 38);
                str = ExtensionMethods.ToStr(value);
                if (value == null)
                {
                    procInfo += "是否已接入省网管监控不可为空!!";
                }
                else if (str == "是")
                {
                    var strvalue = myArray.GetValue(1, 39);
                    string str1 = ExtensionMethods.ToStr(strvalue);
                    if (str1 == "")
                    {
                        procInfo += "接入省网管平台名称不可为空!!";
                    }
                }
                else
                {
                    short state = ExtensionMethods.ConvertToBool(value);
                    if (state == 4)
                    {
                        procInfo += "该是否已接入省网管监控格式错误!!";

                    }
                } 
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadWideBodyData(Array myArray, out string procInfo, out bool isUpdate, string userid, string iFlag)
        {
            procInfo = ""; isUpdate = false;
            bool isAllValid = true;
            if (myArray.Length < 40)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourceDedicateLine entity = new ResourceDedicateLine();                
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);//ProductNo
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "专线编号不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    ResourceDedicateLine dline = dedicateLineFacade.GetHql(str.Trim());
                    if (dline != null)
                    {
                        procInfo += "该专线编号在系统中已存在,请确认是否是您所录入，需要修改请删除系统中的再进行导入!!";
                        isAllValid = false;
                    }
                    else
                    {
                        entity.ProductNo = str;
                        value = myArray.GetValue(1, 2);
                        if (value == null)
                        {
                            procInfo += "状态为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToState(value);
                            if (state == 4)
                            {
                                procInfo += "该状态不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.State = state;
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
                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        if (str != "")
                        {
                            archiveBizType = bizTypeFacade.GetHql(str);
                            if (archiveBizType == null)
                            {
                                procInfo += "该业务类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizTypeId = archiveBizType.Id;
                        }
                        else
                        {
                            procInfo += "业务类型不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveBizAssuranLeve bizAssuranLeve = new ArchiveBizAssuranLeve();
                        if (str != "")
                        {
                            bizAssuranLeve = bizAssuranLeveFacade.GetHql(str);
                            if (bizAssuranLeve == null)
                            {
                                procInfo += "该业务保障等级在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.BizAssuranLeveId = bizAssuranLeve.Id;
                        }
                        else
                        {
                            procInfo += "业务保障等级不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 100)
                        {
                            procInfo += "业务描述不可为空!!";
                            isAllValid = false;
                        }
                        entity.BizNote = str;
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "合同带宽不可为空!!";
                            isAllValid = false;
                        }
                        entity.ContractBandwidth = str;
                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "实际带宽不可为空!!";
                            isAllValid = false;
                        }
                        entity.ActualBandwidth = str;
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        string acliename = str;
                        if (str == "")
                        {
                            procInfo += "该集团名称不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 11);
                        str = ExtensionMethods.ToStr(value);
                        ResourceGroupClie clie = new ResourceGroupClie();
                        if (str.Trim() != "")
                        {
                            clie = groupClieFacade.GetHql(str);
                            if (clie == null)
                            {
                                procInfo += "该A端集团编号在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                            {
                                if (acliename != clie.ClieName)
                                {
                                    procInfo += "所填的A端集团编号跟A端使用集团名称不对应!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.ClieId = clie.Id;
                            }
                        }
                        else
                        {
                            procInfo += "A端集团编号不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveNetworkingMode NetworkingMode = new ArchiveNetworkingMode();
                        if (str != "")
                        {
                            NetworkingMode = networkingModeFacade.GetHql(str);
                            if (NetworkingMode == null)
                            {
                                procInfo += "该A端组网方式在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.NetWorkingModeId = NetworkingMode.Id;
                        }
                        else
                        {
                            procInfo += "A端组网方式不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 13);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 20)
                        {
                            procInfo += "A端接入站点不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.StationName = str;
                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "A端接入机房不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.BizComputRoomName = str;
                        value = myArray.GetValue(1, 15);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "A端传输设备名称不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ATEquip = str;
                        value = myArray.GetValue(1, 16);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "A端传输设备端口不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ATEquipPort = str;

                        value = myArray.GetValue(1, 18);
                        str = ExtensionMethods.ToStr(value);
                        string abizport = str;
                        if (str.Length > 40)
                        {
                            procInfo += "A端业务设备端口不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ABizEquipPort = str;

                        value = myArray.GetValue(1, 17);
                        str = ExtensionMethods.ToStr(value);
                        string[] aequips = null;
                        IList<ResourceEquipment> Aequips = new List<ResourceEquipment>();
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "A端业务设备不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        else
                        {
                            aequips = str.Split(','); 
                            foreach (string equip in aequips)
                            {
                                if (equip.Length > 40)
                                {
                                    procInfo += "A端业务设备长度超过预定!!";
                                }
                                else
                                {
                                    ResourceEquipment Aequip = equipmentFacade.GetHql(equip, clie.Id);
                                    Aequips.Add(Aequip); 
                                    if (Aequip == null)
                                    {
                                        procInfo += "该A端业务设备名称对应A端客户在系统中不存在!!";
                                        isAllValid = false;
                                    }
                                }
                            }
                        }

                        value = myArray.GetValue(1, 19);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "A端DDF或ODF架传输侧位置不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ADDFT = str;
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length > 40)
                        {
                            procInfo += "A端DDF或ODF架业务设备侧位置不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ADDFE = str;

                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        string zcliename = str;
                        if (str == "")
                        {
                            procInfo += "该Z端集团名称不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        ResourceGroupClie zclie = new ResourceGroupClie();
                        if (str.Trim() != "")
                        {
                            zclie = groupClieFacade.GetHql(str);
                            if (zclie == null)
                            {
                                procInfo += "该Z端集团编号在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                            {
                                if (zcliename != zclie.ClieName)
                                {
                                    procInfo += "所填的Z端集团编号跟Z端使用集团名称不对应!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.ZClieId = zclie.Id;
                            }
                        }
                        else
                        {
                            procInfo += "Z端集团编号不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveNetworkingMode ZNetworkingMode = new ArchiveNetworkingMode();
                        if (str != "")
                        {
                            ZNetworkingMode = networkingModeFacade.GetHql(str);
                            if (ZNetworkingMode == null)
                            {
                                procInfo += "该A端组网方式在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.ZNetWorkingModeId = ZNetworkingMode.Id;
                        }
                        else
                        {
                            procInfo += "A端组网方式不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 20)
                        {
                            procInfo += "Z端接入站点不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZStationName = str;
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "Z端接入机房不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZComputRoomName = str;
                        value = myArray.GetValue(1, 26);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "Z端传输设备名称不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZTEquip = str;
                        value = myArray.GetValue(1, 27);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "Z端传输设备端口不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZTEquipPort = str;

                        value = myArray.GetValue(1, 29);
                        str = ExtensionMethods.ToStr(value);
                        string zbizport = str;
                        if (str.Length > 40)
                        {
                            procInfo += "Z端业务设备端口不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZBizEquipPort = str;

                        value = myArray.GetValue(1, 28);
                        str = ExtensionMethods.ToStr(value);
                        IList<ResourceEquipment> Zequips = new List<ResourceEquipment>();
                        string[] zequips = null;
                        if (str.Length == 0)
                        {
                            procInfo += "Z端业务设备不可为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            zequips = str.Split(','); 
                            foreach (string equip in zequips)
                            {
                                if (equip.Length > 40)
                                {
                                    procInfo += "Z端业务设备长度超过预定!!";
                                }
                                else
                                {
                                    ResourceEquipment Zequip = equipmentFacade.GetHql(equip,zclie.Id);
                                    Zequips.Add(Zequip);
                                    if (Zequip == null)
                                    {
                                        procInfo += "该Z端业务设备名称对应于Z端客户在系统中不存在!!";
                                        isAllValid = false;
                                    }
                                }
                            }
                        }
                        value = myArray.GetValue(1, 30);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "Z端DDF或ODF架传输侧位置不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZDDFT = str;
                        value = myArray.GetValue(1, 31);
                        str = ExtensionMethods.ToStr(value);
                        if ( str.Length > 40)
                        {
                            procInfo += "Z端DDF或ODF架业务设备侧位置不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.ZDDFE = str;
                        value = myArray.GetValue(1, 32);
                        if (value != null)
                        {
                            DateTime? dt = ExtensionMethods.ToDateOANull(value);
                            if (dt == null)
                            {
                                procInfo += "开通时间不可为空或格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.StartDateTime = dt;
                        }
                        else
                        {
                            procInfo += "开通时间不可为空或格式错误!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 33);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Length == 0 || str.Length > 40)
                        {
                            procInfo += "电路编号不可为空或长度超过预定!!";
                            isAllValid = false;
                        }
                        entity.CircuitNumber = str;

                        value = myArray.GetValue(1, 35);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "专线类型不可为空!!";
                            isAllValid = false;
                        }
                        else
                        {
                            string state = ExtensionMethods.ConvertToLineType(value);
                            if (state == "4")
                            {
                                procInfo += "该专线类型不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.LineType = state;
                        }
                        value = myArray.GetValue(1, 36);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否接入本地网管不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 37);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入本地网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessLocalNet = 1;
                            entity.AccessLocalNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否接入本地网管格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessLocalNet = state;
                        }
                        value = myArray.GetValue(1, 38);
                        str = ExtensionMethods.ToStr(value);
                        if (value == null)
                        {
                            procInfo += "是否已接入省网管监控不可为空!!";
                            isAllValid = false;
                        }
                        else if (str == "是")
                        {
                            var strvalue = myArray.GetValue(1, 39);
                            string str1 = ExtensionMethods.ToStr(strvalue);
                            if (str1 == "")
                            {
                                procInfo += "接入省网管平台名称不可为空!!";
                                isAllValid = false;
                            }
                            entity.IsAccessProviNet = 1;
                            entity.AccessProviNet = str1;
                        }
                        else
                        {
                            short state = ExtensionMethods.ConvertToBool(value);
                            if (state == 4)
                            {
                                procInfo += "该是否已接入省网管监控格式错误!!";
                                isAllValid = false;
                            }
                            else
                                entity.IsAccessProviNet = state;
                        }
                        value = myArray.GetValue(1, 40);
                        entity.Remark = ExtensionMethods.ToStr(value);
                        entity.CreateUserId = userid;

                        if (isAllValid)
                        {
                            isAllValid = dedicateLineFacade.Save(entity);
                            isAllValid = planFacade.AddLinePlan(entity.BizAssuranLeveId);
                            //A端业务设备添加
                            int j = 0;
                            foreach (ResourceEquipment equip in Aequips)
                            {
                                ResourceLineEquip lineEquip = new ResourceLineEquip();
                                lineEquip.EquipId = equip.Id;
                                lineEquip.LineId = entity.Id;
                                lineEquip.ClieId = clie.Id;
                                isAllValid = lineEquipFacade.Save(lineEquip, j.ToString());
                                j++;
                            }
                            //Z端业务设备添加
                            foreach(ResourceEquipment zequip in Zequips){
                                ResourceLineEquip lineEquip = new ResourceLineEquip();
                                lineEquip.EquipId = zequip.Id;
                                lineEquip.LineId = entity.Id;
                                lineEquip.ClieId = zclie.Id;
                                isAllValid = lineEquipFacade.Save(lineEquip, j.ToString());
                                j++;
                            }
                            //纤芯
                            value = myArray.GetValue(1, 34);
                            str = ExtensionMethods.ToStr(value);
                            if (str.Trim() != "")
                            {
                                try
                                {
                                    string[] endAry = Regex.Split(str, "至客户端", RegexOptions.IgnoreCase);
                                    string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                                    int i = 0;
                                    if (endAry.Length <= 1 || MidAry.Length <= 1)
                                    {
                                        procInfo += "纤芯格式错误 !!";
                                        isAllValid = false;
                                    }
                                    foreach (string mstr in MidAry)
                                    {
                                        ResourceFiberCore fibercore = new ResourceFiberCore();
                                        fibercore.SeqNo = i.ToString();
                                        string[] MidIAry = mstr.Split('(');
                                        if (MidIAry.Length <= 1)
                                            MidIAry = mstr.Split('（');
                                        if (MidIAry.Length > 1)
                                        {
                                            fibercore.BaseStationName = MidIAry[0];
                                            string[] MidOAry = Regex.Split(MidIAry[1], "至", RegexOptions.IgnoreCase);
                                            if (MidOAry.Length <= 1)
                                            {
                                                string[] MidMAry = MidOAry[0].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[0].Split('）');
                                                fibercore.ODUStation = MidMAry[0];
                                            }
                                            else
                                            {
                                                fibercore.ODUStation = MidOAry[0];
                                                string[] MidMAry = MidOAry[1].Split(')');
                                                if (MidMAry.Length <= 1)
                                                    MidMAry = MidOAry[1].Split('）');
                                                fibercore.Core = MidMAry[0];
                                            }
                                            fibercore.ClieId = entity.ClieId;
                                            fibercore.LineId = entity.Id;
                                            isAllValid = fiberCoreFacade.Save(fibercore, i.ToString());
                                            i++;
                                        }
                                    }
                                    ResourceFiberCore fcore = new ResourceFiberCore();
                                    fcore.SeqNo = i.ToString();

                                    if (endAry.Length > 1)
                                    {
                                        string[] endIAry = endAry[1].Split('(');
                                        if (endIAry.Length <= 1)
                                            endIAry = endAry[1].Split('（');
                                        fcore.ODUStation = endIAry[0];

                                        if (endIAry.Length > 1)
                                        {
                                            string[] MidMAry = endIAry[1].Split(')');
                                            if (MidMAry.Length <= 1)
                                                MidMAry = endIAry[1].Split('）');
                                            fcore.Core = MidMAry[0];
                                        }
                                        fcore.ClieId = entity.ClieId;
                                        fcore.LineId = entity.Id;
                                        isAllValid = fiberCoreFacade.Save(fcore, i.ToString());
                                    }
                                }
                                catch
                                {
                                    procInfo += "纤芯格式错误!!";
                                    isAllValid = false;
                                }
                            }
                        }
                    }
                }
            }
            return isAllValid;
        }
        public bool CheckExcelData(string strFileName, out string procInfo, string bizType, out string reFileName)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            int sheetColumn = 0;
            Excel.Range rng = null; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = null;
                if (i == 1)
                {
                    if (bizType == "Internet")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckInternetHeadData(myArray, out procInfo);
                        sheetColumn = 35;
                    }
                    if (bizType == "IMS")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckIMSHeadData(myArray, out procInfo);
                        sheetColumn = 35;
                    }
                    if (bizType == "VOIP")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AC" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckVOIPHeadData(myArray, out procInfo);
                        sheetColumn = 29;
                    }
                    if (bizType == "Wide")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AN" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckWideHeadData(myArray, out procInfo);
                        sheetColumn = 40;
                    }
                    
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
                else if (i == 2 || i == 3)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    if (bizType == "Internet")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = CheckInternetBodyData(myArray, out myText);
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 35;
                    }
                    if (bizType == "IMS")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = CheckIMSBodyData(myArray, out myText);
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 35;
                    }
                    if (bizType == "VOIP")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AC" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = CheckVOIPBodyData(myArray, out myText);
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 29;
                    }
                    if (bizType == "Wide")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AN" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = CheckWideBodyData(myArray, out myText);
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 40;
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
            procInfo = "总验证" + (sheetRow - 3) + "条，成功" + m + "条,未成功" + (sheetRow - 3 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/DedicateLine");
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
        public bool SaveExcelData(string strFileName, out string procInfo, string bizType, out string reFileName, string userid)
        {
            procInfo = "";
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            int sheetColumn =0;
            Excel.Range rng = null; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = null;
                if (i == 1)
                {
                    if (bizType == "Internet")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckInternetHeadData(myArray, out procInfo);
                        sheetColumn = 35;
                    }
                    if (bizType == "IMS")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckIMSHeadData(myArray, out procInfo);
                        sheetColumn = 35;
                    }
                    if (bizType == "VOIP")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AC" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckVOIPHeadData(myArray, out procInfo);
                        sheetColumn = 29;
                    }
                    if (bizType == "Wide")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AN" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        isAllValid = CheckWideHeadData(myArray, out procInfo);
                        sheetColumn = 40;
                    }
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.Font.Color = 255;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;
                    
                }

                else if (i == 2 || i == 3)
                {
                    continue;
                }
                else
                {
                    string myText = ""; bool isUpdate = false;
                    if (bizType == "Internet")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = LoadInternetBodyData(myArray, out myText, out isUpdate, userid, i.ToString());
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 35;
                    }
                    if (bizType == "IMS")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AI" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = LoadIMSBodyData(myArray, out myText, out isUpdate, userid, i.ToString());
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 35;
                    }
                    if (bizType == "VOIP")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AC" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = LoadVOIPBodyData(myArray, out myText, out isUpdate, userid, i.ToString());
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 29;
                    }
                    if (bizType == "Wide")
                    {
                        range = workSheet.get_Range("A" + i.ToString(), "AN" + i.ToString());
                        Array myArray = (Array)range.Cells.Value2;
                        try
                        {
                            isAllValid = LoadWideBodyData(myArray, out myText, out isUpdate, userid, i.ToString());
                        }
                        catch
                        {
                            isAllValid = false;
                        } 
                        sheetColumn = 40;
                    }

                    if (isAllValid && !isUpdate)
                    {
                        myText += "导入数据库添加记录成功";
                        m++;
                    }
                    else if (isAllValid && isUpdate)
                    {
                        myText += "导入数据库更新记录成功";
                        m++;
                    }
                    else if (!isAllValid && !isUpdate)
                    {
                        myText += "导入数据库添加记录失败!!";
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    else
                    {
                        myText += "导入数据库更新记录失败!!";
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow - 3) + "条，成功" + m + "条,未成功" + (sheetRow - 3 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/DedicateLine");
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
