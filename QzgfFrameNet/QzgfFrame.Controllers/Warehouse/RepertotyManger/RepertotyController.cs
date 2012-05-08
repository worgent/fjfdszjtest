using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using System.Collections.Generic;
using QzgfFrame.Warehouse.RepertotyManger.Domain;
using QzgfFrame.Warehouse.RepertotyManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.RepertotyManger
{
    public class RepertotyController : BaseController
    {
        private RepertotyFacade repertotyFacade { set; get; }
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
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
            string hql = " IsMaintain ='1'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
            var result = new WarehouseRepertoty();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
            string hql = " IsMaintain ='1'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
            WarehouseRepertoty result = repertotyFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseRepertoty entity)
        {
            bool result = false;
            string msg = "操作失败";
            try
            {
                if (id == "0")
                    result=this.repertotyFacade.Save(entity, "0");
                else
                   result= this.repertotyFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
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
                result = repertotyFacade.Delete(id.ToString());
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
            return repertotyFacade.FindByPage(page, pagesize);
        }

        /// <summary>
        /// 用户作为下拉框
        /// </summary>
        /// <returns></returns>
        public string UserCombobox()
        {
            return repertotyFacade.GetUserCombobox();
        }
    }
}
