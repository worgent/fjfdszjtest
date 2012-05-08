using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using QzgfFrame.Warehouse.InventoryDetailManger.Domain;
using QzgfFrame.Warehouse.InventoryDetailManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.InventoryDetailManger
{
    public class InventoryDetailController : BaseController
    {
        private InventoryDetailFacade inventoryDetailFacade { set; get; }

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
        public ActionResult Edit(string id)
        {
            WarehouseInventoryDetail result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = inventoryDetailFacade.Get(id);
            }
            return View(result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseInventoryDetail entity)
        {
            try
            {
                if (id == "0")
                    this.inventoryDetailFacade.Save(entity, "0");
                else
                    this.inventoryDetailFacade.Update(entity);
                return View();
            }
            catch
            {
                return View();
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
            string savePath = "../../../Upload/LoadFile/InventoryLedger\\";
                       
            bool result = false;
            string msg = "操作失败";
            result = inventoryDetailFacade.Delete(id.ToString(), savePath);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return inventoryDetailFacade.FindByPage(page, pagesize);
        }
    }
}
