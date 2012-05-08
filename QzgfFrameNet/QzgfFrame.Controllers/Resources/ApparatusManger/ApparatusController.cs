using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.UseManger.Models;
using QzgfFrame.Archives.UseManger.Domain;
using QzgfFrame.Resources.ApparatusManger.Domain;
using QzgfFrame.Resources.ApparatusManger.Models;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.MeterStateManger.Models;
using QzgfFrame.Archives.MeterStateManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.Utility.Core;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.ApparatusManger
{
    public class ApparatusController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private UseFacade useFacade { set; get; }
        private MeterStateFacade meterStateFacade { set; get; }
        private StagnationFacade stagnationFacade { set; get; }
        private MaintainSpecialtyFacade maintainSpecialtyFacade { set; get; }

        private ApparatusFacade apparatusFacade { set; get; }
        private ApparatusFacadeEx apparatusFacadeEx { set; get; }
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
        /// <summary>
        /// 为选择集团客户，通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Display()
        {
            return View();
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            ResourceApparatus result = apparatusFacade.Get(id);
            ArchiveDistrict city = districtFacade.Get(result.CityId);
            ViewData["city"] = city.DistrictName;
            ArchiveMaintainSpecialty maintainSpecialty = maintainSpecialtyFacade.Get(result.MaintainSpecialtyId);
            ViewData["maintainSpecialty"] = maintainSpecialty.MaintainSpecialtyName;
            ArchiveMeterState meterState = meterStateFacade.Get(result.MeterStateId);
            ViewData["meterState"] = meterState.MeterStateName;
            ArchiveUse use = useFacade.Get(result.UseId);
            ViewData["use"] = use.UseName;
            if (result.DistrictId != null)
            {
                ArchiveDistrict district = districtFacade.Get(result.DistrictId);
                ViewData["district"] = district.DistrictName;
            }
            else
                ViewData["district"] = "";
            if (result.CompanyId != null)
            {
                ArchiveCompany company = companyFacade.Get(result.CompanyId);
                ViewData["company"] = company.CompanyName;
            }
            else
                ViewData["company"] = ""; 
            if (result.GridId != null)
            {
                ArchiveGrid grid = gridFacade.Get(result.GridId);            
                ViewData["grid"] = grid.GridName;
            }
            else
                ViewData["grid"] = "";
            if (result.StagnationId != null)
            {
                ArchiveStagnation stagnation = stagnationFacade.Get(result.StagnationId);            
                ViewData["stagnation"] = stagnation.StagnationName;
            }
            else
                ViewData["stagnation"] = "";
            return View("Display", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveMaintainSpecialty> maintainSpecialtys = maintainSpecialtyFacade.LoadAll();
            ViewData["maintainSpecialty"] = new SelectList(maintainSpecialtys, "Id", "MaintainSpecialtyName");
            IList<ArchiveMeterState> meterStates = meterStateFacade.LoadAll();
            ViewData["meterState"] = new SelectList(meterStates, "Id", "MeterStateName");
            IList<ArchiveUse> uses = useFacade.LoadAll();
            ViewData["use"] = new SelectList(uses, "Id", "UseName");
            if (this.currentUser.UserInfo.Isrepair == "1")
                ViewData["company"] = this.currentUser.UserInfo.Departmentid;
            else
                ViewData["company"] = ""; 
            var result = new ResourceApparatus();
            result.Id = "0";
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveMaintainSpecialty> maintainSpecialtys = maintainSpecialtyFacade.LoadAll();
            ViewData["maintainSpecialty"] = new SelectList(maintainSpecialtys, "Id", "MaintainSpecialtyName");
            IList<ArchiveMeterState> meterStates = meterStateFacade.LoadAll();
            ViewData["meterState"] = new SelectList(meterStates, "Id", "MeterStateName");
            IList<ArchiveUse> uses = useFacade.LoadAll();
            ViewData["use"] = new SelectList(uses, "Id", "UseName");
            ResourceApparatus result = apparatusFacade.Get(id);
            ViewData["frameid"] = frameid;
            return View("Edit", result);
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
                string savePath = "../Upload/LoadFile/Apparatus\\";
               // string savePath = Server.MapPath("../Upload/LoadFile/Apparatus\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = apparatusFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = apparatusFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName, userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo+"<br>"+msg;
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
        public ActionResult Edit(string id, ResourceApparatus entity)
        {
            try
            {
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.apparatusFacade.Save(entity, "0");
                else
                    result = this.apparatusFacade.Update(entity);
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
        public ActionResult Delete(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = apparatusFacade.Delete(id.ToString());
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
                result = apparatusFacade.DeleteFalse(id.ToString());
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
                gridsearch += " and (main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null)";
            string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            if (strhql != "")
                gridsearch += " and " + strhql;
            return apparatusFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetSeqNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Trim().Length > 50)
                        result = false;
                    else
                    {
                        ResourceApparatus rse = apparatusFacade.GetHql(id);
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
        private string[] aryTilte = { "序列号"
                            ,"所属城市","所属区县", "所属公司", "所属网格","所属驻点"
                            , "用途", "维护专业", "状态","仪表厂商"
                            , "仪表名称"  , "仪表型号" 
                           };
        private string[] aryType = { "str","str","str","str","str"
                            ,"str","str", "str", "str","str"
                            , "str" , "str"
                           };
        private string aryField = @"main.SeqNo ,dd.DistrictName as CityName,ad.DistrictName, ac.CompanyName, grid.GridName,sta.StagnationName
                            ,use.UseName, maintain.MaintainSpecialtyName, meter.MeterStateName, main.ManuFacturer
                            , main.MeterName,main.MeterModel";
        #endregion
        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut(string gridsearch)
        {
            bool result = false;
            //string msg = "操作失败";
            try
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and (main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null)";
                string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                if (strhql != "")
                    gridsearch += " and " + strhql; IList<object[]> ls = apparatusFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("仪器仪表信息", aryTilte, ls, aryType,null);
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
