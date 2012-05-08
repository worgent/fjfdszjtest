using System;
using System.IO;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Domain;
using QzgfFrame.Archives.SignalModelManger.Models;
using QzgfFrame.Archives.SignalModelManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Resources.LineEquipManger.Models;
using QzgfFrame.Resources.LineEquipManger.Domain;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Utility.Common.Helpers;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;
using QzgfFrame.Cop.PlanManager.Models;

namespace QzgfFrame.Controllers.Resources.DedicateLineManger
{
    public class DedicateLineController : BaseController
    {
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        private NetworkingModeFacade networkingModeFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private BizTypeFacade bizTypeFacade { set; get; }
        private DedicateLineFacade dedicateLineFacade { set; get; }
        private FiberCoreFacade fiberCoreFacade { set; get; }
        private NumberGroupFacade numberGroupFacade { set; get; }
        private DedicateLineFacadeEx dedicateLineFacadeEx { set; get; }
        private OtherInfoFacade otherInfoFacade { set; get; }
        private SignalModelFacade signalModelFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private LineEquipFacade lineEquipFacade { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string bizId, string frameid)
        {
            ViewData["bizTypeId"] = bizId;
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExcelOut(string BizType,string bizId, string frameid)
        {
            ViewData["bizTypeId"] = bizId;
            ViewData["frameid"] = frameid;
            ViewData["bizType"] = BizType;
            return View("ExcelOut");
        }
        public ActionResult Internet()
        {
            string where = " BizTypeName='互联网专线'";
            ArchiveBizType biztype = bizTypeFacade.Get("Id", where);
            ViewData["bizTypeId"] = biztype.Id;
            ViewData["bizTypeName"] = biztype.BizTypeName;
            return View("Internet");
        }
        public ActionResult IMS()
        {
            string where = " BizTypeName='IMS专线'";
            ArchiveBizType biztype = bizTypeFacade.Get("Id", where);
            ViewData["bizTypeId"] = biztype.Id;
            ViewData["bizTypeName"] = biztype.BizTypeName;
            return View("IMS");
        }
        public ActionResult VOIP()
        {
            string where = " BizTypeName='VOIP专线'";
            ArchiveBizType biztype = bizTypeFacade.Get("Id", where);
            ViewData["bizTypeId"] = biztype.Id;
            ViewData["bizTypeName"] = biztype.BizTypeName;
            return View("VOIP");
        }
        public ActionResult Wide()
        {
            string where = " BizTypeName='广域网专线'";
            ArchiveBizType biztype = bizTypeFacade.Get("Id", where);
            ViewData["bizTypeId"] = biztype.Id;
            ViewData["bizTypeName"] = biztype.BizTypeName;
            return View("Wide");
        }


        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string BizType, string frameid, string id)
        {
            ViewData["frameid"] = frameid;
            DedicateLine result = new DedicateLine();
            DedicateLine dl = dedicateLineFacade.GetLine(id);
            ArchiveNetworkingMode networkingMode = networkingModeFacade.Get(dl.DediLine.NetWorkingModeId);
            dl.NetWorkingMode = networkingMode.ModeName;
            ArchiveCompany company = companyFacade.Get(dl.DediLine.CompanyId);
            dl.CompanyName = company.CompanyName;
            ArchiveDistrict district = districtFacade.Get(dl.DediLine.DistrictId);
            dl.DistrictName = district.DistrictName;

            string where = ""; string strRet = "DisplayInternet";
            if (BizType.Trim() == "Internet")
            {
                where = " BizTypeName='互联网专线'";
                strRet = "DisplayInternet";
                dl.editFlag = "Internet"; 
                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();
                LineEquips.Add(LineEquip);

                IList<ResourceLineEquip> LineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "' and main.ClieId='" + dl.ClieId + "'");
                if (LineEquipLists.Count == 0)
                {
                    dl.lineEquipList = LineEquips;
                }
                else
                    dl.lineEquipList = LineEquipLists;
            }
            if (BizType.Trim() == "IMS")
            {
                where = " BizTypeName='IMS专线'";
                strRet = "DisplayIMS";
                dl.NumberGroups = numberGroupFacade.LoadAll("OrderNo", " LineId='" + dl.DediLine.Id + "'");
                if (dl.NumberGroups.Count == 0)
                {
                    IList<ResourceNumberGroup> numberGroups = new List<ResourceNumberGroup>();
                    ResourceNumberGroup numberGroup = new ResourceNumberGroup();
                    numberGroups.Add(numberGroup);
                    dl.NumberGroups = numberGroups;
                }
                dl.editFlag = "IMS";
            }
            if (BizType.Trim() == "VOIP")
            {
                where = " BizTypeName='VOIP专线'";
                strRet = "DisplayVOIP";
                ArchiveSignalModel signalModel=signalModelFacade.Get(dl.DediLine.SignalModel);
                dl.SignalModelName = signalModel.SignalModelName;
                dl.editFlag = "VOIP";
            }
            if (BizType.Trim() == "Wide")
            {
                where = " BizTypeName='广域网专线'";
                strRet = "DisplayWide";
                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();
                LineEquips.Add(LineEquip);
                if (dl.DediLine.ZNetWorkingModeId != null)
                {
                    ArchiveNetworkingMode znetworkingMode = networkingModeFacade.Get(dl.DediLine.ZNetWorkingModeId);
                    dl.ZNetWorkingMode = znetworkingMode.ModeName;
                }
                if (dl.DediLine.ZClieId != null)
                {
                    ResourceGroupClie zclie = groupClieFacade.GetStrHql(" Id='" + dl.DediLine.ZClieId.ToString() + "' and (DelFlag!=1 or DelFlag is null)");
                    dl.ZClieName = zclie.ClieName;
                    dl.ZClieNo = zclie.ClieNo;
                    IList<ResourceLineEquip> ZLineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "' and main.ClieId='" + dl.ZClieId + "'");
                    if (ZLineEquipLists.Count == 0)
                    {
                        dl.ZlineEquipList = LineEquips;
                    }
                    else
                        dl.ZlineEquipList = ZLineEquipLists;
                }
                else
                    dl.ZlineEquipList = LineEquips;

                IList<ResourceLineEquip> LineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "' and main.ClieId='" + dl.ClieId + "'");
                if (LineEquipLists.Count == 0)
                {
                    dl.lineEquipList = LineEquips;
                }
                else
                    dl.lineEquipList = LineEquipLists;
                
                dl.editFlag = "Wide";
            }
            ArchiveBizType arbiztype = bizTypeFacade.Get("Id", where);
            ArchiveBizAssuranLeve bizAssuran=bizAssuranLeveFacade.Get(dl.DediLine.BizAssuranLeveId);
            dl.AssuranLeveName=bizAssuran.AssuranLeveName;
            IList<ArchiveBizAssuranLeve> bizAssurans = bizAssuranLeveFacade.LoadAll();
            ResourceGroupClie gc = groupClieFacade.GetGC(dl.DediLine.ClieId);
            dl.ClieNo = gc.ClieNo;
            dl.ClieName = gc.ClieName;
            dl.BizTypeName = arbiztype.BizTypeName;
            IList<ResourceFiberCore> fibercores = new List<ResourceFiberCore>();
            for (int i = 0; i < 2; i++)
            {
                ResourceFiberCore fibercore = new ResourceFiberCore();
                fibercore.SeqNo = i.ToString();
                fibercores.Add(fibercore);
            }
            //编辑时返回具体值
            dl.FiberCores = fiberCoreFacade.LoadAll("SeqNo", " LineId='" + dl.DediLine.Id + "'");
            if (dl.FiberCores.Count == 0)
            {
                dl.FiberCores = fibercores;
            }
            dl.OtherInfos = otherInfoFacade.LoadAll("LocalDatetime", " LineId='" + dl.DediLine.Id + "'");
            result = dl;
            result.ClieId = dl.ClieId;
            return View(strRet, result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string BizType, string id, string frameid)
        {
            Plan plan = new Plan();


            DedicateLine result = new DedicateLine();
            ResourceDedicateLine rdl = new ResourceDedicateLine();
            string where = ""; string strRet = "EditInternet";
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            string strsql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                strsql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", strsql);
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            List<SelectListItem> StateList = new List<SelectListItem>();
            StateList.Add(new SelectListItem { Text = "未移交", Value = "3", Selected = false });
            StateList.Add(new SelectListItem { Text = "预移交", Value = "0", Selected = false });
            StateList.Add(new SelectListItem { Text = "正式移交", Value = "1", Selected = false });
            StateList.Add(new SelectListItem { Text = "退网", Value = "2", Selected = false });
            ViewData["state"] = StateList;
           
            IList<ArchiveNetworkingMode> networkingModes = networkingModeFacade.LoadAll();
            SelectList networkingMode = null;
            if (BizType.Trim() == "Internet")
            {
                where = " BizTypeName='互联网专线'";
                strRet = "EditInternet";
                networkingMode = new SelectList(networkingModes, "Id", "ModeName");

                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();

                LineEquips.Add(LineEquip);
                result.lineEquipList = LineEquips;
                rdl.ProductNo = "PRODUCT_IPLINE_";
                result.editFlag = "Internet";
            }
            if (BizType.Trim() == "IMS")
            {
                where = " BizTypeName='IMS专线'";
                strRet = "EditIMS";
                networkingMode = new SelectList(networkingModes, "Id", "ModeName");
                IList<ResourceNumberGroup> numberGroups = new List<ResourceNumberGroup>();
                ResourceNumberGroup numberGroup = new ResourceNumberGroup();
                numberGroups.Add(numberGroup);
                result.NumberGroups = numberGroups;
                rdl.ProductNo = "PRODUCT_VOICELINE_";
                result.editFlag = "IMS";
            }
            if (BizType.Trim() == "VOIP")
            {
                where = " BizTypeName='VOIP专线'";
                strRet = "EditVOIP"; 
                IList<ArchiveSignalModel> signalModels = signalModelFacade.LoadAll();
                ViewData["signalModel"] = new SelectList(signalModels, "Id", "SignalModelName");
                string selmodeid = "";
                foreach (ArchiveNetworkingMode anm in networkingModes)
                {
                    if (anm.ModeName == "协转")
                    { selmodeid = anm.Id; break; }

                }
                networkingMode = new SelectList(networkingModes, "Id", "ModeName", selmodeid);
                rdl.NetWorkingModeId = selmodeid;
                rdl.ProductNo = "PRODUCT_VOICELINE_";
                result.editFlag = "VOIP";
            }
            if (BizType.Trim() == "Wide")
            {
                where = " BizTypeName='广域网专线'";
                strRet = "EditWide"; 
                IList<ArchiveNetworkingMode> networkModes = new List<ArchiveNetworkingMode>();
                foreach (ArchiveNetworkingMode anm in networkingModes)
                {
                    if (anm.ModeName != "PON")
                        networkModes.Add(anm);
                }
                networkingMode = new SelectList(networkModes, "Id", "ModeName");

                List<SelectListItem> LineTypeList = new List<SelectListItem>();
                LineTypeList.Add(new SelectListItem { Text = "本地", Value = "0", Selected = false });
                LineTypeList.Add(new SelectListItem { Text = "跨地", Value = "1", Selected = false });
                LineTypeList.Add(new SelectListItem { Text = "跨省", Value = "2", Selected = false });
                ViewData["lineTypeList"] = LineTypeList;

                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();

                LineEquips.Add(LineEquip);
                result.lineEquipList = LineEquips;
                result.ZlineEquipList = LineEquips;

                rdl.ProductNo = "PRODUCT_TRANLINE_";
                result.editFlag = "Wide";
            }
            ArchiveBizType arbiztype = bizTypeFacade.Get("Id", where);
            IList<ArchiveBizAssuranLeve> bizAssurans = bizAssuranLeveFacade.LoadAll();
            //ViewData["selgroupclie"] = groupClieFacade.GetCombobox(null);
            
            ViewData["selbizassuran"] = new SelectList(bizAssurans, "Id", "AssuranLeveName");
            ViewData["selnetwork"] = networkingMode;
            
            ViewData["biztype"] = arbiztype.BizTypeName;
            IList<ResourceFiberCore> fibercores = new List<ResourceFiberCore>();
            for (int i = 0; i < 2; i++)
            {
                ResourceFiberCore fibercore = new ResourceFiberCore();
                fibercore.SeqNo = i.ToString();
                fibercores.Add(fibercore);
            }
            //新增时返回空对象
            rdl.Id = "0";
            rdl.BizTypeId = arbiztype.Id;
            result.DediLine = rdl;
            result.FiberCores = fibercores;
            ViewData["frameid"] = frameid;
            return View(strRet, result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string BizType, string id, string frameid)
        {
            DedicateLine result = new DedicateLine(); 
            DedicateLine dl = dedicateLineFacade.GetLine(id);


            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            string strsql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                strsql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", strsql);
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveNetworkingMode> networkingModes = networkingModeFacade.LoadAll();
            SelectList networkingMode = null;
            string where = ""; string strRet = "EditInternet";
            if (BizType.Trim() == "Internet")
            {
                where = " BizTypeName='互联网专线'";
                strRet = "EditInternet";
                networkingMode = new SelectList(networkingModes, "Id", "ModeName");
                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();
                LineEquips.Add(LineEquip);

                IList<ResourceLineEquip> LineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "'");
                if (LineEquipLists == null)
                {
                    dl.lineEquipList = LineEquips;
                }
                else if (LineEquipLists.Count == 0)
                {
                    dl.lineEquipList = LineEquips;
                }
                else
                    dl.lineEquipList = LineEquipLists;
                dl.editFlag = "Internet";
            }
            if (BizType.Trim() == "IMS")
            {
                where = " BizTypeName='IMS专线'";
                strRet = "EditIMS";
                networkingMode = new SelectList(networkingModes, "Id", "ModeName");
                dl.NumberGroups = numberGroupFacade.LoadAll("OrderNo", " LineId='" + dl.DediLine.Id + "'");
                if (dl.NumberGroups.Count == 0)
                {
                    IList<ResourceNumberGroup> numberGroups = new List<ResourceNumberGroup>();
                    ResourceNumberGroup numberGroup = new ResourceNumberGroup();
                    numberGroups.Add(numberGroup);
                    dl.NumberGroups = numberGroups;
                }
                dl.editFlag = "IMS";
            }
            if (BizType.Trim() == "VOIP")
            {
                where = " BizTypeName='VOIP专线'";
                strRet = "EditVOIP";
                IList<ArchiveSignalModel> signalModels = signalModelFacade.LoadAll();
                ViewData["signalModel"] = new SelectList(signalModels, "Id", "SignalModelName");
                string selmodeid = "";
                foreach (ArchiveNetworkingMode anm in networkingModes)
                {
                    if (anm.ModeName == "协转")
                    { selmodeid = anm.Id; break; }

                }
                networkingMode = new SelectList(networkingModes, "Id", "ModeName", selmodeid);
                dl.editFlag = "VOIP";
            }
            if (BizType.Trim() == "Wide")
            {
                where = " BizTypeName='广域网专线'";
                strRet = "EditWide";                
                IList<ArchiveNetworkingMode> networkModes = new List<ArchiveNetworkingMode>();
                foreach (ArchiveNetworkingMode anm in networkingModes)
                {
                    if (anm.ModeName != "PON")
                        networkModes.Add(anm);

                }
                networkingMode = new SelectList(networkModes, "Id", "ModeName");
                ResourceLineEquip LineEquip = new ResourceLineEquip();
                IList<ResourceLineEquip> LineEquips = new List<ResourceLineEquip>();
                LineEquips.Add(LineEquip);
                if (dl.DediLine.ZClieId != null)
                {
                    ResourceGroupClie zclie = groupClieFacade.GetStrHql(" Id='" + dl.DediLine.ZClieId.ToString() + "' and (DelFlag!=1 or DelFlag is null)");
                    dl.ZClieName = zclie.ClieName;
                    dl.ZClieNo = zclie.ClieNo;
                    dl.ZClieId = dl.DediLine.ZClieId;
                    IList<ResourceLineEquip> ZLineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "' and main.ClieId='" + dl.ZClieId + "'");
                    if (ZLineEquipLists.Count == 0)
                    {
                        dl.ZlineEquipList = LineEquips;
                    }
                    else
                        dl.ZlineEquipList = ZLineEquipLists;
                }
                else
                    dl.ZlineEquipList = LineEquips;
                List<SelectListItem> LineTypeList = new List<SelectListItem>();
                LineTypeList.Add(new SelectListItem { Text = "本地", Value = "0", Selected = dl.DediLine.LineType != "0" ? false : true });
                LineTypeList.Add(new SelectListItem { Text = "跨地", Value = "1", Selected = dl.DediLine.LineType != "1" ? false : true });
                LineTypeList.Add(new SelectListItem { Text = "跨省", Value = "2", Selected = dl.DediLine.LineType != "2" ? false : true });
                ViewData["lineTypeList"] = LineTypeList;
               

                IList<ResourceLineEquip> LineEquipLists = lineEquipFacade.LoadAll(" and main.LineId='" + dl.DediLine.Id + "' and main.ClieId='"+dl.ClieId+"'");
                if (LineEquipLists.Count == 0)
                {
                    dl.lineEquipList = LineEquips;
                }
                else
                    dl.lineEquipList = LineEquipLists;
                
                dl.editFlag = "Wide";
            }
            ArchiveBizType arbiztype = bizTypeFacade.Get("Id", where);
            IList<ArchiveBizAssuranLeve> bizAssurans = bizAssuranLeveFacade.LoadAll();
            
           // ViewData["selgroupclie"] = groupClieFacade.GetCombobox(null);
            ViewData["selbizassuran"] = new SelectList(bizAssurans, "Id", "AssuranLeveName");
            ViewData["selnetwork"] = networkingMode;
            ViewData["biztype"] = arbiztype.BizTypeName;
            IList<ResourceFiberCore> fibercores = new List<ResourceFiberCore>();
            for (int i = 0; i < 2; i++)
            {
                ResourceFiberCore fibercore = new ResourceFiberCore();
                fibercore.SeqNo = i.ToString();
                fibercores.Add(fibercore);
            }
            //编辑时返回具体值
            dl.FiberCores = fiberCoreFacade.LoadAll("SeqNo", " LineId='" + dl.DediLine.Id + "'");
            if (dl.FiberCores.Count == 0)
            {
                dl.FiberCores = fibercores;
            }
            dl.OtherInfos = otherInfoFacade.LoadAll("LocalDatetime", " LineId='" + dl.DediLine.Id + "'");
            List<SelectListItem> StateList = new List<SelectListItem>();
            StateList.Add(new SelectListItem { Text = "未移交", Value = "3", Selected = dl.DediLine.State != 3 ? false : true });
            StateList.Add(new SelectListItem { Text = "预移交", Value = "1", Selected = dl.DediLine.State != 1 ? false : true });
            StateList.Add(new SelectListItem { Text = "正式移交", Value = "0", Selected = dl.DediLine.State != 0 ? false : true });
            StateList.Add(new SelectListItem { Text = "退网", Value = "2", Selected = dl.DediLine.State != 2 ? false : true });
            ViewData["state"] = StateList;

            result = dl;
            result.Id = dl.Id;
            ViewData["frameid"] = frameid;
            return View(strRet, result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult InternetLoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["bizType"] = "Internet";
            ViewData["bizTypeName"] = "互联网专线.xlsx";
            return View("LoadFile");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult IMSLoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["bizType"] = "IMS";
            ViewData["bizTypeName"] = "IMS专线.xlsx";
            return View("LoadFile");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult VOIPLoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["bizType"] = "VOIP";
            ViewData["bizTypeName"] = "VOIP专线.xlsx";
            return View("LoadFile");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult WideLoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["bizType"] = "Wide";
            ViewData["bizTypeName"] = "广域网专线.xlsx";
            return View("LoadFile");
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UploadFile(string btnFlag, string bizType)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string ShowInfo = ""; string reFileName = "";
                string savePath = "../Upload/LoadFile/DedicateLine\\";
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = dedicateLineFacadeEx.CheckExcelData(strFileName, out msg, bizType, out  reFileName);//检测数据
                else
                    result = dedicateLineFacadeEx.SaveExcelData(strFileName, out msg, bizType, out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");

                if (result) msg = ShowInfo + msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(DedicateLine entity)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string savePath = Server.MapPath("../../Upload/OtherInfo/" + entity.editFlag + "/\\");//上传文件保存路径 
                HttpFileCollectionBase uploadfiles = Request.Files;
                if (entity.ClieId != "")
                    entity.DediLine.ClieId = entity.ClieId;
                else
                    return Json(new { Type = false, Message = "操作失败,集团客户为空!!" }, "text/html");
                if (entity.editFlag == "Wide")
                {
                    if (entity.ZClieId != "")
                        entity.DediLine.ZClieId = entity.ZClieId;
                    else
                        return Json(new { Type = false, Message = "操作失败,集团客户为空!!" }, "text/html");
                }
                entity.DediLine.CreateUserId = this.currentUser.UserInfo.Id;    
                if (entity.DediLine.Id == "0")
                {
                    ResourceDedicateLine rse = dedicateLineFacade.GetHql(entity.DediLine.ProductNo.Trim());
                    if (rse != null)
                        return Json(new { Type = false, Message = "操作失败,该专线编号已经存在!", Id = rse.Id }, "text/html");

                    result = dedicateLineFacadeEx.Save(entity, uploadfiles, savePath);
                }
                else
                {
                    entity.DediLine.UpdateDate = DateTime.Now;
                    entity.DediLine.UpdateUserId = this.currentUser.UserInfo.Id;
                    entity.DediLine.UpdateUserName = this.currentUser.UserInfo.Username;
                    string filename = "";
                    result = dedicateLineFacadeEx.Update(entity, uploadfiles, savePath, out filename);
                    if (filename != "")
                    {
                        dedicateLineFacadeEx.DeleteFile(filename, savePath);
                    }
                }
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, "text/html");
            }
            catch (Exception ex)
            {
                return Json(new { Type = result, Message = msg }, "text/html");
            }
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult EditOneSevenZero(OneSevenZeroLine entity)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string savePath = Server.MapPath("../../Upload/OtherInfo\\");//上传文件保存路径 
                HttpFileCollectionBase uploadfiles = Request.Files;
                string userid= this.currentUser.UserInfo.Id;
                result = dedicateLineFacadeEx.SaveOSZL(entity, uploadfiles, savePath,userid);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, "text/html");
            }
            catch (Exception ex)
            {
                return Json(new { Type = result, Message = msg }, "text/html");
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id, string editFlag)
        {
            try
            {
                bool result = false; string filename = "";
                string msg = "操作失败";
               // string savePath = "../../Upload/OtherInfo/" + editFlag + "/\\";
                string savePath = Server.MapPath("../../../Upload/OtherInfo/" + editFlag + "/\\");//上传文件保存路径 
                result = dedicateLineFacadeEx.Delete(id, out filename);
                if (result)
                {
                    dedicateLineFacadeEx.DeleteFile(filename, savePath);
                    msg = "操作成功";
                }
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // 假删除操作
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteFalse(string id, string editFlag)
        {
            try
            {
                bool result = false; string filename = ""; bool DelFlag = false;
                string msg = "操作失败";
                // string savePath = "../../Upload/OtherInfo/" + editFlag + "/\\";
                string savePath = Server.MapPath("../../../Upload/OtherInfo/" + editFlag + "/\\");//上传文件保存路径 
                result = dedicateLineFacadeEx.DeleteFalse(id, out filename, editFlag, out DelFlag);
                if (result)
                {
                    dedicateLineFacadeEx.DeleteFile(filename, savePath);
                    msg = "操作成功";
                }
                if (DelFlag) msg += "，部份信息存在关联无法删除！！";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Quit(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = dedicateLineFacade.Quit(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            if (strhql != "")
                gridsearch += " and " + strhql;
            return dedicateLineFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetLineNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Trim().Length > 70)
                        result = false;
                    else
                    {
                        ResourceDedicateLine rse = dedicateLineFacade.GetHql(id.Trim());
                        if (rse != null)
                            return Json(new { Type = true, Message = "操作成功", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    }
                }
                else
                    result = false;
                return Json(new { Type = result, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        /// 互联网
        private string[] aryInternetTilte = { "专线编号","状态","集团名称","集团编号","所属区县","所属公司"
                            ,"业务类型", "业务保障等级", "组网方式","合同带宽","实际带宽","VLAN号"
                            , "网关地址","客户IP地址", "子网掩码","城域网接入交换机" , "城域网交换机端口" 
                            ,  "机房传输设备", "机房传输设备端口" , "接入基站名称","基站传输设备及端口"
                            , "电路编号"  , "DDF架传输侧位置"  , "DDF架设备侧位置"  , "接入全业务机房名称"
                            , "OLT设备及端口"  , "LOID"  , "是否接入本地网管" ,"接入本地网管平台名称"
                            , "是否接入省网管监控"  ,"接入省网管平台名称"
                            , "开通时间","纤芯资料","业务设备","备注","修改时间","修改用户"  
                           };
        private string[] aryInternetType = { "str","state","str","str","str","str"
                            ,"str","str", "str", "str","str","str","str","str"
                            , "str","str", "str", "str","str","str","str"
                            ,"str","str","str","str","str","str","bool","str","bool","str"
                            , "date" ,"str","str","str", "date" , "str" 
                           };
        private string aryInternetField = @"main.Id,main.ClieId,main.ProductNo,main.State,clie.ClieName,clie.ClieNo,ad.DistrictName,ac.CompanyName
                            ,biztype.BizTypeName, bizleve.AssuranLeveName,netmode.ModeName, main.ContractBandwidth, main.ActualBandwidth,main.VLANNumber
                            , main.GateWay,main.IP, main.SubnetMask, main.CityNetSwitch,main.CityNetSwitchPort
                            ,  main.ComputRoomtTEquip, main.ComputRoomtTEquipPort,main.StationName,main.StationTEquipAndPort
                            , main.CircuitNumber,main.DDFT, main.DDFE, main.BizComputRoomName,main.OLTEquipAPort
                            , main.LOID, main.IsAccessLocalNet,main.AccessLocalNet, main.IsAccessProviNet, main.AccessProviNet
                            ,main.StartDateTime,main.Remark,main.UpdateDate,main.UpdateUserName
                            
                           ";
       //IMS专线
        private string[] aryIMSTilte = { "专线编号","状态","集团名称","集团编号","所属区县","所属公司"
                            ,"业务类型", "业务保障等级", "组网方式","合同带宽","实际带宽","VLAN号"
                            , "网关地址","客户IP地址", "子网掩码","城域网接入交换机" , "城域网交换机端口" 
                            ,  "机房传输设备", "机房传输设备端口" , "接入基站名称","基站传输设备及端口"
                            , "电路编号"  , "DDF架传输侧位置"  , "DDF架设备侧位置"  , "接入全业务机房名称"
                            , "OLT设备及端口"  , "LOID"  , "是否接入本地网管" ,"接入本地网管平台名称"
                            , "是否接入省网管监控"  ,"接入省网管平台名称"
                            , "开通时间"  ,"纤芯资料","号码群","备注" ,"修改时间","修改用户"  
                           };
        private string[] aryIMSType = { "str","state","str","str","str","str"
                            ,"str","str", "str", "str","str","str","str","str"
                            , "str","str", "str", "str","str","str","str"
                            ,"str","str","str","str","str","str","bool","str","bool","str"
                            , "date","str","str","str" , "date" , "str" 
                           };
        private string aryIMSField = @"main.Id,main.ProductNo,main.State,clie.ClieName,clie.ClieNo,ad.DistrictName,ac.CompanyName
                            ,biztype.BizTypeName, bizleve.AssuranLeveName,netmode.ModeName, main.ContractBandwidth, main.ActualBandwidth,main.VLANNumber
                            , main.GateWay,main.IP, main.SubnetMask, main.CityNetSwitch,main.CityNetSwitchPort
                            ,  main.ComputRoomtTEquip, main.ComputRoomtTEquipPort,main.StationName,main.StationTEquipAndPort
                            , main.CircuitNumber,main.DDFT, main.DDFE, main.BizComputRoomName,main.OLTEquipAPort
                            , main.LOID, main.IsAccessLocalNet,main.AccessLocalNet, main.IsAccessProviNet, main.AccessProviNet
                            ,main.StartDateTime,main.Remark,main.UpdateDate,main.UpdateUserName
                            
                           ";
        //VOIP专线
        private string[] aryVOIPTilte = { "专线编号","状态","集团名称","集团编号","所属区县","所属公司"
                            ,"业务类型",  "业务保障等级","组网方式","速率","中继数量","信令方式"
                            , "电话号码","局端设备", "局端端口", "A端DDF架传输侧位置","A端DDF架设备侧位置"
                            , "接入基站名称" , "基站传输设备及端口" , "Z端DDF架传输侧位置", "Z端DDF架设备侧位置" 
                            , "电路编号"  , "是否接入本地网管","接入本地网管平台名称" , "是否接入省网管监控"  
                            ,"接入省网管平台名称", "开通时间" ,"纤芯资料","备注"  ,"修改时间","修改用户" 
                           };
        private string[] aryVOIPType = { "str","state","str","str","str","str"
                            ,"str","str", "str", "str","str","str","str","str"
                            , "str","str", "str", "str","str","str","str"
                            ,"str","bool","str","bool","str","date","str","str", "date" , "str" 
                           };
        private string aryVOIPField = @"main.Id,main.ProductNo,main.State,clie.ClieName,clie.ClieNo,ad.DistrictName,ac.CompanyName
                            ,biztype.BizTypeName,bizleve.AssuranLeveName,netmode.ModeName, main.Rate, main.RalayNumber,signal.SignalModelName
                            , main.TelNumber,main.TermiEquip, main.TermiPort, main.ADDFT,main.ADDFE
                            , main.StationName, main.StationTEquipAndPort, main.ZDDFT,main.ZDDFE
                            , main.CircuitNumber, main.IsAccessLocalNet,main.AccessLocalNet
                            , main.IsAccessProviNet, main.AccessProviNet,main.StartDateTime,main.Remark,main.UpdateDate,main.UpdateUserName
                           ";
        //广域网专线
        private string[] aryWideTilte = { "专线编号","状态","所属区县","所属公司","业务类型",  "业务保障等级"
                            ,"业务描述","合同带宽","实际带宽","A端使用集团名称","A端集团编号","A端组网方式","A端接入站点" 
                            ,"A端接入机房","A端传输设备名称", "A端传输设备端口","A端业务设备","A端业务设备端口"
                            , "A端DDF或ODF架传输侧位置", "A端DDF或ODF架业务设备侧位置","Z端使用集团名称"
                            ,"Z端集团编号","Z端组网方式","Z端接入站点","Z端接入机房", "Z端传输设备名称"  ,  "Z端传输设备端口"  , "Z端业务设备"
                            , "Z端业务设备端口"  , "Z端DDF或ODF架传输侧位置"  , "Z端DDF或ODF架设备侧位置"  , "开通时间"
                            ,"电路编号","纤芯资料","专线类型", "是否接入本地网管" ,"接入本地网管平台名称"
                            , "是否接入省网管监控","接入省网管平台名称","备注" ,"修改时间","修改用户"  
                              
                           };
        private string[] aryWideType = { "str","state","str","str","str","str","str"
                            ,"str","str", "str", "str","str","str","str","str"
                            , "str","str", "str", "str","str","str","str"
                            ,"str","str","str","str","str","str","str","str","str","date"
                            ,"str","str","linetype","bool","str","bool","str","str", "date" , "str" 
                           };
        //,main.ABizEquip, main.ABizEquipPort业务设备,设备端口 , 纤芯,
        private string aryWideField = @"main.Id,main.ClieId,main.ZClieId,main.ProductNo,main.State,ad.DistrictName,ac.CompanyName,biztype.BizTypeName, bizleve.AssuranLeveName
                            ,main.BizNote, main.ContractBandwidth, main.ActualBandwidth,clie.ClieName,clie.ClieNo,netmode.ModeName,main.StationName
                            ,main.BizComputRoomName,main.ATEquip, main.ATEquipPort
                            , main.ADDFT,main.ADDFE,zclie.ClieName,zclie.ClieNo,znetmode.ModeName,main.ZStationName,main.ZComputRoomName
                            ,main.ZTEquip,main.ZTEquipPort, main.ZDDFT,main.ZDDFE,main.StartDateTime,main.CircuitNumber,main.LineType
                            , main.IsAccessLocalNet,main.AccessLocalNet, main.IsAccessProviNet,main.AccessProviNet,main.Remark
                            ,main.UpdateDate,main.UpdateUserName
                           ";
        #endregion
        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut(string bizType, string gridsearch)
        {
            bool result = false;
            //string msg = "操作失败";
            string[] tt = { };
            try
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and (main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null)";
                string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                if (strhql != "")
                    gridsearch += " and " + strhql;
                if (bizType == "Internet")
                {
                    IList<object[]> ls = dedicateLineFacade.FindExcel(aryInternetField, gridsearch, aryInternetTilte.Length, "Internet");
                    result = BaseFun.ExcelOut("互联网专线信息", aryInternetTilte, ls, aryInternetType, "line");
                }
                if (bizType == "IMS")
                {
                    IList<object[]> ls = dedicateLineFacade.FindExcel(aryIMSField, gridsearch, aryIMSTilte.Length, "IMS");
                    result = BaseFun.ExcelOut("IMS专线信息", aryIMSTilte, ls, aryIMSType, "line");
                }
                if (bizType == "VOIP")
                {
                    IList<object[]> ls = dedicateLineFacade.FindExcel(aryVOIPField, gridsearch, aryVOIPTilte.Length, "VOIP");
                    result = BaseFun.ExcelOut("VOIP专线信息", aryVOIPTilte, ls, aryVOIPType, "line");
                }
                if (bizType == "Wide")
                {
                    IList<object[]> ls = dedicateLineFacade.FindWideExcel(aryWideField, gridsearch, aryWideTilte.Length);
                    result = BaseFun.ExcelOut("广域网专线信息", aryWideTilte, ls, aryWideType, "line");
                }                
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
