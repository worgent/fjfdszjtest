using System;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.SaleDepartmentManger
{
    public class SaleDepartmentController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveCompany> citys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(citys, "Id", "CompanyName");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");   
            var result = new ArchiveSaleDepartment();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveCompany> citys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(citys, "Id", "CompanyName");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            ArchiveSaleDepartment result = saleDepartmentFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveSaleDepartment entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.saleDepartmentFacade.Save(entity);
                else
                    result = this.saleDepartmentFacade.Update(entity);
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
            bool result = false; bool DelFlag = false;
            string msg = "操作失败";
            result = saleDepartmentFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 显示查询页面
        /// </summary>
        /// <param name="frameid"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return saleDepartmentFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        public override string GridSelPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return saleDepartmentFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        public ActionResult GetCombobox()
        {
            bool result = false;
            string msg = "操作失败"; string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

            if (strhql != "" && hql != "")
                hql += " and " + strhql;
            else if (strhql != "" && hql == "")
                hql += strhql;

            string droplist = saleDepartmentFacade.GetCombobox(hql);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg, Rows = droplist }, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 营业部作为下拉框
        /// </summary>
        /// <returns></returns>
        public string GetSearchCombobox()
        {
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql += " and (CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null";
            string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);
            if (strhql != "")
                hql += " and " + strhql;
            return saleDepartmentFacade.GetCombobox(hql);
        }
    }
}
