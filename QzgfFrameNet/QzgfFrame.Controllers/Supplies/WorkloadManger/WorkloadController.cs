using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.WorkloadManger.Domain;
using QzgfFrame.Supplies.WorkloadManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.WorkloadManger
{
    public class WorkloadController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }
        private CommunityManagerFacade communityManagerFacade { set; get; }

        private WorkloadFacade workloadFacade { set; get; }
        /// <summary>
        /// 首页信息调用saleDepartment,时通过service取得数据
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
        //
        // GET: /CommunityManagerr/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveDistrict> citys = districtFacade.LoadAll("SeqNO", "TypeFlag=2");
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveSaleDepartment> saleDepartments = saleDepartmentFacade.LoadAll();
            ViewData["saleDepartment"] = new SelectList(saleDepartments, "Id", "SaleDepartmentName");
            
            IList<SuppliesCommunityManager> communityManagers = communityManagerFacade.LoadAll();
            ViewData["communityManager"] = new SelectList(communityManagers, "Id", "CommunityManagerName");
            var result = new SuppliesWorkload();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> citys = districtFacade.LoadAll("SeqNO", "TypeFlag=2");
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveSaleDepartment> saleDepartments = saleDepartmentFacade.LoadAll();
            ViewData["saleDepartment"] = new SelectList(saleDepartments, "Id", "SaleDepartmentName");
            
            IList<SuppliesCommunityManager> communityManagers = communityManagerFacade.LoadAll();
            ViewData["communityManager"] = new SelectList(communityManagers, "Id", "CommunityManagerName");
            SuppliesWorkload result = workloadFacade.Get(id);
            return View("Edit", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
        }
        //
        // POST: /CommunityManagerr/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SuppliesWorkload entity)
        {
            try
            {
                string[] strdate = entity.NYDate.Split('-');
                entity.WorkYear = short.Parse(strdate[0]);
                entity.WorkMonth = short.Parse(strdate[1]);
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.workloadFacade.Save(entity, "0");
                else
                    result = this.workloadFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /CommunityManagerr/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = workloadFacade.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string saleDepartmentviewname, string saleDepartmentsearch)
        {
            return workloadFacade.FindByPage(page, pagesize);
        }
    }
}
