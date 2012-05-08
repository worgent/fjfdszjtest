using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.QualificationTypeManger.Domain;
using QzgfFrame.Archives.QualificationTypeManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.QualificationTypeManger
{
    public class QualificationTypeController : BaseController
    {
        private QualificationTypeFacade qualificationTypeFacade { set; get; }

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
            var result = new ArchiveQualificationType();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            ArchiveQualificationType result = qualificationTypeFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveQualificationType entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.qualificationTypeFacade.Save(entity);
                else
                    result = this.qualificationTypeFacade.Update(entity);
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
            IList<ArchiveQualificationType> qualificationTypes = qualificationTypeFacade.LoadAll();
            if (qualificationTypes.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (ArchiveQualificationType item in qualificationTypes)
            {
                mapList.Add(new
                {
                    qualificationTypeid = item.Id,
                    qualificationTypename = item.QualificationTypeName
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
            result = qualificationTypeFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return qualificationTypeFacade.FindByPage(page, pagesize);
        }
    }
}
