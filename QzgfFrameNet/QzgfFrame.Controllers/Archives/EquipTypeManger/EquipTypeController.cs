using System.Web.Mvc;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.EquipTypeManger
{
    public class EquipTypeController : BaseController
    {
        private EquipTypeFacade equipTypeFacade { set; get; }

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
            var result = new ArchiveEquipType();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            ArchiveEquipType result = equipTypeFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveEquipType entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.equipTypeFacade.Save(entity);
                else
                    result = this.equipTypeFacade.Update(entity);
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
            result = equipTypeFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return equipTypeFacade.FindByPage(page, pagesize);
        }
        /// <summary>
        /// 设备类型名称作为下拉框
        /// </summary>
        /// <returns></returns>
        public string GetCombobox()
        {
            return equipTypeFacade.GetCombobox();
        }
    }
}
