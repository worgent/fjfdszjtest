using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.SuppliesStockManger
{
    public class SuppliesStockController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 区县库存（含营业部）
        /// </summary>
        /// <returns></returns>
        public ActionResult QxIndex()
        {
            return View();
        }
        /// <summary>
        /// 营业部库存
        /// </summary>
        /// <returns></returns>
        public ActionResult YwIndex()
        {
            return View();
        }
        /// <summary>
        /// 全区库存
        /// </summary>
        /// <returns></returns>
        public ActionResult AllIndex()
        {
            return View();
        }

        /// <summary>
        /// 登记耗材,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelRegIndex(string id)
        {
            string[] ary = id.Split(';');
            ViewData["saledeparmentid"] = ary[0];
            ViewData["tids"] = ary[1];
            return View();
        }
        /// <summary>
        /// 修改分配耗材,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelSupIndex(string id)
        {
            string[] ary = id.Split(';');
            ViewData["cityid"] = ary[0];
            ViewData["companyid"] = ary[1];
            ViewData["tids"] = ary[2];
            return View();
        }
        /// <summary>
        /// 分配耗材,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            string[] ary=id.Split(':');
            ViewData["districtdids"] = ary[0];
            ViewData["tids"] = ary[1];
            return View("SelStock");
        }
        /// <summary>
        /// 分配耗材,有营业部，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelYwIndex(string id)
        {
            string[] ary = id.Split(';');
            ViewData["sids"] = ary[0];
            ViewData["tids"] = ary[1];
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
        public ActionResult YwSearch(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("YwSearch");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult SjSearch(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("SjSearch");
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new SuppliesSuppliesStock();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SuppliesSuppliesStock result = suppliesStockFacade.Get(id);
            return View("Edit", result);
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
        public ActionResult Edit(string id, SuppliesSuppliesStock entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.suppliesStockFacade.Save(entity, "0");
                else
                    result = this.suppliesStockFacade.Update(entity);
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
                result = suppliesStockFacade.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public override string GridSelPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            //gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            gridsearch += " and main.State='0' and main.Num>0 ";
            return suppliesStockFacade.FindSelByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        //市公司库存
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            if (this.currentUser.UserInfo.LEVELNO == "3")
            {
                ArchiveDistrict District = districtFacade.Get(this.currentUser.UserInfo.Areaid);
                gridsearch += " and main.DistrictId='" + District.ParentId + "'";

            }            
            else
               gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid+ "'";
            return suppliesStockFacade.FindQxByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        //县公司库存
        public string QxGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
           // gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return suppliesStockFacade.FindQxByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        //按营业部库存统计
        public string YwGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
            gridsearch += "  and (main.SaleDepartmentId!='' or main.SaleDepartmentId is not null) ";
            return suppliesStockFacade.FindYwByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        //全区库存
        public string AllGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
               gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
           // string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            //gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return suppliesStockFacade.FindQxByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
