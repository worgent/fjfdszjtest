using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.IOClassManger.Domain;
using QzgfFrame.Archives.IOClassManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.IOClassManger
{
    public class IOClassController : BaseController
    {
        private IOClassFacade ioClassFacade { set; get; }

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
            List<SelectListItem> iotypes = new List<SelectListItem>();
            iotypes.Add(new SelectListItem() { Text = "入库", Value = "IList", Selected = false });
            iotypes.Add(new SelectListItem() { Text = "出库", Value = "OList", Selected = false });
            ViewData["iotypes"] = iotypes; 
            var result = new ArchiveIOClass();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            List<SelectListItem> iotypes = new List<SelectListItem>();
            iotypes.Add(new SelectListItem() { Text = "入库", Value = "IList", Selected = false });
            iotypes.Add(new SelectListItem() { Text = "出库", Value = "OList", Selected = false });
            ViewData["iotypes"] = iotypes; 
            ArchiveIOClass result = ioClassFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveIOClass entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.ioClassFacade.Save(entity);
                else
                    result = this.ioClassFacade.Update(entity);
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
            IList<ArchiveIOClass> ioClasss = ioClassFacade.LoadAll();
            if (ioClasss.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (ArchiveIOClass item in ioClasss)
            {
                mapList.Add(new
                {
                    ioClassid = item.Id,
                    ioClassname = item.StyleName
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
            result = ioClassFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return ioClassFacade.FindByPage(page, pagesize);
        }
    }
}
