using System;
using System.IO;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Archives.PortTypeManger.Domain;
using QzgfFrame.Archives.PortTypeManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.EquipmentManger
{
    public class EquipmentController : BaseController
    {
        private EquipmentFacade equipmentFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipTypeFacade equipTypeFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
        private EquipmentFacadeEx equipmentFacadeEx { set; get; }
        private NetworkingModeFacade networkingModeFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private PortTypeFacade portTypeFacade { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        /// <summary>
        /// 为选择设备，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            string[] ary = id.Split(';');
            ViewData["clieid"] = ary[0];
            ViewData["eids"] = ary[1];
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExcelOut(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("ExcelOut");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            EquipClie result = equipmentFacadeEx.Get(id);
            ArchiveFactory factory = factoryFacade.Get(result.equipment.FactoryId);
            ViewData["factory"] = factory.Abbrevia;
            ArchiveEquipType equipType = equipTypeFacade.Get(result.equipment.EquipTypeId);
            ViewData["equipType"] = equipType.EquipTypeName;
            ArchiveNetworkingMode  networkingMode  = networkingModeFacade.Get(result.equipment.NetWorkingModeId);
            ViewData["network"] = networkingMode.ModeName;
            ArchiveDistrict district = districtFacade.Get(result.equipment.DistrictId);
            ArchiveCompany company = companyFacade.Get(result.equipment.CompanyId);
            ViewData["company"] = company.CompanyName;
            ViewData["district"] = district.DistrictName;
            
            result.FactoryId = result.equipment.FactoryId;
            result.EquipTypeId = result.equipment.EquipTypeId;
            return View("Display", result);
        }

        public ActionResult GetDropDownList()
        {
            string msg = "操作失败";
            IList<ArchivePortType> PortTypes = portTypeFacade.LoadAll();
            string list = JSONHelper.ToJSON(PortTypes);
            return Json(new { Type = true, Message = msg, Rows = list }, JsonRequestBehavior.AllowGet);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveNetworkingMode> networkingModes = networkingModeFacade.LoadAll();
            ViewData["network"] = new SelectList(networkingModes, "Id", "ModeName");
            IList<ArchivePortType> PortTypes = portTypeFacade.LoadAll();

            var result = new EquipClie();
            result.PortTypes = PortTypes;
            ResourceEquipment equipment = new ResourceEquipment();
            result.equipment = equipment;
            IList<ResourceClieEquip> clieEquips = new List<ResourceClieEquip>();
            ResourceClieEquip clieEquip = new ResourceClieEquip();
            clieEquips.Add(clieEquip);
            result.ClieEquips = clieEquips;
            result.Id = "0";
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveNetworkingMode> networkingModes = networkingModeFacade.LoadAll();
            ViewData["network"] = new SelectList(networkingModes, "Id", "ModeName");

            IList<ArchivePortType> PortTypes = portTypeFacade.LoadAll();
            EquipClie result = equipmentFacadeEx.Get(id);
            result.PortTypes = PortTypes;
            result.FactoryId = result.equipment.FactoryId;
            result.EquipTypeId = result.equipment.EquipTypeId;
            
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, EquipClie entity)
        {
            bool result = false; string msg = "操作失败";                
            try
            {
                entity.equipment.CreateUserId = this.currentUser.UserInfo.Id;
                entity.equipment.FactoryId = entity.FactoryId;
                entity.equipment.EquipTypeId = entity.EquipTypeId;
                if (id == "0")
                    result = this.equipmentFacadeEx.Save(entity);
                else
                    result = this.equipmentFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View();
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UploadFile(string btnFlag)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string ShowInfo = ""; string reFileName = "";
                string savePath = "../Upload/LoadFile/Equipment\\";
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");

                if (btnFlag == "0")
                    result = equipmentFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = equipmentFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo + msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = result, Message = msg + ex.Message }, "text/html");
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = equipmentFacadeEx.Delete(id.ToString());
                if (result) msg = "操作成功";
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
        public ActionResult DeleteFalse(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败"; bool DelFlag = false;
                result = equipmentFacadeEx.DeleteFalse(id.ToString(),out DelFlag);
                if (result) msg = "操作成功";
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
                result = equipmentFacade.Quit(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }

        /// <summary>
        /// 为选择专线设备，通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Display()
        {
            return View();
        }

        /// <summary>
        /// 巡检登记中，批量添加专线设备，显示专线列表
        /// </summary>
        /// <returns></returns>
        public ActionResult BatchAddList()
        {
            return View();
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            if (strhql != "")
                gridsearch += " and " + strhql; 
            return equipmentFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        public override string GridSelPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            if (strhql != "")
                gridsearch += " and " + strhql;
            gridsearch += " and main.State='0'";
            return equipmentFacade.FindSelByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "集团名称","集团编号","所属区县","所属公司","设备名称"
                            ,"设备类型","设备型号", "设备厂家", "设备序列号","设备安装位置"
                            , "基站/机房名称","MAC地址", "设备启用时间", "登录方式","路由器用户名"
                            , "密码","SNMP只读共同体","SNMP只写共同体","网管IP","WEB端口","组网方式","占用槽位","板卡类型"
                            ,"设备占用端口","端口类型","备注"
                           };
        private string[] aryType = { "str","str","str","str","str"
                            ,"str","str", "str", "str","position","str"
                            , "str","str", "date", "str","str"
                            , "str","str", "str", "str","str"
                            , "str","str", "str", "str","str"
                           };
        private string aryField = @"clie.ClieName,clie.ClieNo
                               ,ad.DistrictName,ac.CompanyName,main.EquipName,etype.EquipTypeName,main.EquipModelName,factory.Abbrevia
                            ,main.SeqNumber,main.Position, main.BaseStationName, main.MacAddress,main.StartDatetime
                            , main.LoginStyle,main.UserName, main.PassWord, main.SnmpOnlyRead
                            , main.SnmpOnlyWrite, main.NetManageIp,main.WebPort,netmode.ModeName
                            ,cliequip.OccupySlot,cliequip.BoardType,cliequip.OccupyPort,ptype.PortTypeName,main.Remark";
        #endregion
        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut(string gridsearch)
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
                    gridsearch += " and " + strhql; IList<object[]> ls = equipmentFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("专线设备信息", aryTilte, ls, aryType,null);
                return View();
            }
            catch
            {
                return View();
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetEquipName(string id)
        {
            try
            {
                id = Server.UrlDecode(id);                
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Trim().Length > 40)
                        result = false;
                    else
                    {
                        ResourceEquipment rse = equipmentFacade.GetHql(id);
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
    }
}
