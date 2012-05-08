using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.NatureManger.Domain;
using QzgfFrame.Archives.NatureManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.GridManger
{
    public class GridController : BaseController
    {
        private GridFacade gridFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private NatureFacade natureFacade { set; get; }
        
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
            IList<ArchiveDistrict> citys = districtFacade.LoadAll("SeqNO","TypeFlag=2");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            IList<ArchiveDistrict> offices = districtFacade.LoadAll("SeqNO", "TypeFlag=4");
            IList<ArchiveNature> natures = natureFacade.LoadAll();
            ViewData["selnature"] = new SelectList(natures, "Id", "NatureName");
            ViewData["selcity"] = new SelectList(citys, "Id", "DistrictName");
            ViewData["seldistrict"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["seloffice"] = new SelectList(offices, "Id", "DistrictName");

            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["selcompany"] = new SelectList(companys, "Id", "CompanyName");
            var result = new ArchiveGrid();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {

            IList<ArchiveDistrict> citys = districtFacade.LoadAll("SeqNO", "TypeFlag=2");
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            IList<ArchiveDistrict> offices = districtFacade.LoadAll("SeqNO", "TypeFlag=4");
            IList<ArchiveNature> natures = natureFacade.LoadAll();
            ViewData["selnature"] = new SelectList(natures, "Id", "NatureName");
            ViewData["selcity"] = new SelectList(citys, "Id", "DistrictName");
            ViewData["seldistrict"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["seloffice"] = new SelectList(offices, "Id", "DistrictName");

            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["selcompany"] = new SelectList(companys, "Id", "CompanyName");
            ArchiveGrid result = gridFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveGrid entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.gridFacade.Save(entity);
                else
                    result = this.gridFacade.Update(entity);
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
            bool result = false;
            string msg = "操作失败"; bool DelFlag = false;
            result = gridFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return gridFacade.FindByPage(page, pagesize);
        }
        /// <summary>
        /// 网格作为下拉框
        /// </summary>
        /// <returns></returns>
        public string GetCombobox()
        {
            return gridFacade.GetCombobox();
        }
    }
}
