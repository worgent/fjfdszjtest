using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.EquipFaultManger.Domain;
using QzgfFrame.Resources.EquipFaultManger.Models;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;
using QzgfFrame.Archives.BreakdownTypeManger.Models;
using QzgfFrame.Archives.BreakdownTypeManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.EquipFaultManger
{
    public class EquipFaultController : BaseController
    {
        private EquipFaultFacade equipFaultFacade { set; get; }
        private ComponentFacade componentFacade { set; get; }
        private BreakdownTypeFacade breakdownTypeFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        private EquipFaultFacadeEx equipFaultFacadeEx { set; get; }
        private SelfHelpFactoryFacade selfHelpFactoryFacade { set; get; }
        private SelfHelpEquipModelFacade selfHelpEquipModelFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }

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
            ResourceEquipFault result = equipFaultFacade.Get(id);
            if (result.ComponentId == null)
                ViewData["component"] = "";
            else if (result.ComponentId.Trim() == "0")
                ViewData["component"] = "";
            else
            {
                ArchiveComponent Component = componentFacade.Get(result.ComponentId);
                ViewData["component"] = Component.ComponentName;
            }
            ArchiveBreakdownType BreakdownType = breakdownTypeFacade.Get(result.BreakdownTypeId);

           ViewData["breakdownType"] =BreakdownType.BreakdownTypeName;
            
            return View("Display", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            IList<ArchiveComponent> Components = componentFacade.LoadAll();
            IList<ArchiveBreakdownType> BreakdownTypes = breakdownTypeFacade.LoadAll();
            IList<ResourceSelfHelpEquip> selfHelpEquips = selfHelpEquipFacade.LoadAll();
            IList<ResourceGroupClie> groupClies = groupClieFacade.LoadAll();
            IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["components"] = new SelectList(Components, "Id", "ComponentName");
            ViewData["breakdownTypes"] = new SelectList(BreakdownTypes, "Id", "BreakdownTypeName");
            ViewData["selgroupclie"] = new SelectList(groupClies, "Id", "ClieNo");
            ViewData["selfHelpEquip"] = new SelectList(selfHelpEquips, "Id", "TermiId");

            /**
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");**/
            ViewData["factory"] = new SelectList(factorys, "Id", "Abbrevia");
           ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            var result = new ResourceEquipFault();
            result.Id = "0";
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            IList<ArchiveComponent> Components = componentFacade.LoadAll();
            IList<ArchiveBreakdownType> BreakdownTypes = breakdownTypeFacade.LoadAll();
            IList<ResourceSelfHelpEquip> selfHelpEquips = selfHelpEquipFacade.LoadAll();
            IList<ResourceGroupClie> groupClies = groupClieFacade.LoadAll();
            IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
            IList<ArchiveSelfHelpEquipModel> equipModels = selfHelpEquipModelFacade.LoadAll();
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " Id='" + this.currentUser.UserInfo.Departmentid + "'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["components"] = new SelectList(Components, "Id", "ComponentName");
            ViewData["breakdownTypes"] = new SelectList(BreakdownTypes, "Id", "BreakdownTypeName");
            ViewData["selgroupclie"] = new SelectList(groupClies, "Id", "ClieNo");
            ViewData["selfHelpEquip"] = new SelectList(selfHelpEquips, "Id", "TermiId");

            ViewData["factory"] = new SelectList(factorys, "Id", "Abbrevia");
            ViewData["equipModel"] = new SelectList(equipModels, "Id", "ModelName");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            ResourceEquipFault result = equipFaultFacade.Get(id);
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ResourceEquipFault entity)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                if (entity.ComponentId == null || entity.ComponentId == "" || entity.ComponentId == "0")
                    entity.ComponentId = componentFacade.GetHql("其他").Id;
                entity.CreateUserId = this.currentUser.UserInfo.Id;
               if (id == "0")
                    result = this.equipFaultFacadeEx.Save(entity);
                else
                    result = this.equipFaultFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
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
                string savePath = "../Upload/LoadFile/EquipFault\\";
                // string savePath = Server.MapPath("../Upload/LoadFile/Apparatus\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = equipFaultFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = equipFaultFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo + "<br>" + msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
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
                result = equipFaultFacade.Delete(id.ToString());
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
                string msg = "操作失败";
                result = equipFaultFacade.DeleteFalse(id.ToString());
                if (result) msg = "操作成功";
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
                result = equipFaultFacade.Quit(id.ToString());
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
            return equipFaultFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "终端ID","所属区县","网点编号","网点名称","设备厂家"
                            ,"设备型号","客户联系电话", "代维单位", "申告日期","人员到达时间"
                            , "故障解决时间","处理时长", "排障人员", "故障部件","故障类型"
                            , "故障现象"  , "故障处理过程"  , "故障处理结果"  , "是否更换配件","状态"
                           };
        private string[] aryType = { "str","str","str","str","str"
                            ,"str","str", "str", "datetime","datetime"
                            , "datetime","str", "str", "str","str"
                            , "str" ,"str", "str", "str","state"
                           };
        private string aryField = @"main.TermiId,ad.DistrictName,main.UseNetNo,main.UseNetName,factory.Abbrevia
                            ,main.EquipModelName,main.ClieTel, ac.CompanyName, main.NoticeDateTime,main.ReachDatetime
                            , main.SolveDatetime,main.HandleTime, main.TroubleShooter, component.ComponentName, btype.BreakdownTypeName
                            , main.Description, main.HandleProcess, main.HandleResult, main.IsReplace,main.State";
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
                    gridsearch += " and " + strhql; 
                IList<object[]> ls = equipFaultFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("自助设备故障信息", aryTilte, ls, aryType,"equip");
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
