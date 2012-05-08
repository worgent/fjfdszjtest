using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.SupplierManger.Domain;
using QzgfFrame.Archives.SupplierManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.SupplierManger
{
    public class SupplierController : BaseController
    {
        private SupplierFacade supplierFacade { set; get; }
        private UnitFacade unitFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 采购耗材,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            ViewData["tids"] = id;
            return View();
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new ArchiveSupplier();
            IList<ArchiveUnit> citys = unitFacade.LoadAll();
            ViewData["unit"] = new SelectList(citys, "UnitName", "UnitName");
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            ArchiveSupplier result = supplierFacade.Get(id);
            IList<ArchiveUnit> citys = unitFacade.LoadAll();
            ViewData["unit"] = new SelectList(citys, "UnitName", "UnitName");
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveSupplier entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.supplierFacade.Save(entity);
                else
                    result = this.supplierFacade.Update(entity);
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
            result = supplierFacade.Delete(id.ToString(),out DelFlag);
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
            return supplierFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        public ActionResult GetSupplier(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ArchiveSupplier> result = supplierFacade.LoadAll("Id", " upper(SupplierName) like upper('%" + list + "%')");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ArchiveSupplier item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        supplierName = item.SupplierName
                    });
                }
                // string rowsjson = JsonConvert.SerializeObject(mapList);
                return Json(new { Type = true, Data = mapList }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
            }
        }
    }
}
