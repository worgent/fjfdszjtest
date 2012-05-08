using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.StagnationManger
{
    public class StagnationController : BaseController
    {
        private StagnationFacade stagnationFacade { set; get; }
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
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName"); 
            var result = new ArchiveStagnation();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName"); 
            ArchiveStagnation result = stagnationFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveStagnation entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.stagnationFacade.Save(entity);
                else
                    result = this.stagnationFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult GetAll()
        {
            IList<ArchiveStagnation> stagnations = stagnationFacade.LoadAll();
            if (stagnations.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (ArchiveStagnation item in stagnations)
            {
                mapList.Add(new
                {
                    stagnationid = item.Id,
                    stagnationname = item.StagnationName
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败"; bool DelFlag = false;
            result = stagnationFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return stagnationFacade.FindByPage(page, pagesize);
        }
    }
}
