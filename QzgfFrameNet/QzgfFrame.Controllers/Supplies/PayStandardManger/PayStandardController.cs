using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Supplies.PayStandardManger.Domain;
using QzgfFrame.Supplies.PayStandardManger.Models;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.Archives.MaintainTypeManger.Models;
using QzgfFrame.Archives.MaintainTypeManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.PayStandardManger
{
    public class PayStandardController : BaseController
    {
        private AccessWayFacade accessWayFacade { set; get; }
        private MaintainTypeFacade maintainTypeFacade { set; get; }

        private PayStandardFacade payStandardFacade { set; get; }
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
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveMaintainType> maintainTypes = maintainTypeFacade.LoadAll();
            ViewData["maintainType"] = new SelectList(maintainTypes, "Id", "MaintainTypeName");
            
            var result = new SuppliesPayStandard();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveMaintainType> maintainTypes = maintainTypeFacade.LoadAll();
            ViewData["maintainType"] = new SelectList(maintainTypes, "Id", "MaintainTypeName");
            
            SuppliesPayStandard result = payStandardFacade.Get(id);
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
        public ActionResult Edit(string id, SuppliesPayStandard entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.payStandardFacade.Save(entity, "0");
                else
                    result = this.payStandardFacade.Update(entity);
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
                result = payStandardFacade.Delete(id.ToString());
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
            return payStandardFacade.FindByPage(page, pagesize);
        }
    }
}
