using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Supplies.CollarSuppliesManger.Domain;
using QzgfFrame.Supplies.CollarSuppliesManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Models;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.CollarSuppliesManger
{
    public class CollarSuppliesController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private CollarSuppliesDetailFacade collarSuppliesDetailFacade { set; get; }

        private CollarSuppliesFacade collarSuppliesFacade { set; get; }
        private CollarSuppliesFacadeEx collarSuppliesFacadeEx { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
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
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            CollarSupplies result = new CollarSupplies();
            result.suppliesCollarSupplies = collarSuppliesFacade.Get(id);
            result.CollarList = collarSuppliesFacade.LoadAllCollar("Id", " CollarSuppliesId='" + id + "'");
            
            result.Id = id;
            return View("Display", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Confirm(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            CollarSupplies result = new CollarSupplies();
            result.suppliesCollarSupplies = collarSuppliesFacade.Get(id);

            result.Id = id;
            result.CollarList = collarSuppliesFacade.LoadAllCollar("Id", " CollarSuppliesId='" + id + "'");
            
            result.Id = id;
            return View("Collar", result);
        }
        //领料操作
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Collar(string id, CollarSupplies entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                entity.suppliesCollarSupplies.CreateUserId = this.currentUser.UserInfo.Id;
                if (id == "0")
                    return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
                else
                    result = this.collarSuppliesFacadeEx.Update(entity);

                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            var result = new CollarSupplies();
            SuppliesCollarSupplies suppliesCollarSupplies = new SuppliesCollarSupplies();
            suppliesCollarSupplies.Id = "0";
            ArchiveDistrict district = districtFacade.Get(this.currentUser.UserInfo.Areaid);
            suppliesCollarSupplies.DistrictId = district.Id;
            suppliesCollarSupplies.DistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(this.currentUser.UserInfo.Departmentid);
            suppliesCollarSupplies.CompanyId = company.Id;
            suppliesCollarSupplies.CompanyName = company.CompanyName;
            suppliesCollarSupplies.CollarUser = this.currentUser.UserInfo.Username;
            IList<ArchiveSuppliesType> SuppliesTypes = suppliesTypeFacade.LoadAll();
            result.suppliesTypes = SuppliesTypes;

            IList<SuppliesCollarSuppliesDetail> CollarSuppliesDetails = new List<SuppliesCollarSuppliesDetail>();
            SuppliesCollarSuppliesDetail CollarSuppliesDetail = new SuppliesCollarSuppliesDetail();
            CollarSuppliesDetails.Add(CollarSuppliesDetail);
            result.SuppliesDetailList = CollarSuppliesDetails;
            result.suppliesCollarSupplies = suppliesCollarSupplies;
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            SuppliesCollarSupplies result = new SuppliesCollarSupplies();
            result = collarSuppliesFacade.Get(id);

            ViewData["frameid"] = frameid;
            return View("Modify", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, CollarSupplies entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                entity.suppliesCollarSupplies.CreateUserId = this.currentUser.UserInfo.Id;
                if (id == "0")
                    result = this.collarSuppliesFacadeEx.Save(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Modify(string id, SuppliesCollarSupplies entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                if (entity.ArrivalNum > entity.Num)
                    return Json(new { Type = false, Message = "操作失败,采购总量不可小于已领数量" }, JsonRequestBehavior.AllowGet);
                if (id == "0")
                    return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
                else
                    result = this.collarSuppliesFacade.Update(entity);
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
                result = collarSuppliesFacadeEx.Delete(id.ToString());
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
        public ActionResult Invalid(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = collarSuppliesFacadeEx.Invalid(id.ToString());
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
            return collarSuppliesFacade.FindByPage(page, pagesize,sortname,sortorder,gridsearch);
        }
    }
}
