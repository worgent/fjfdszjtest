using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using QzgfFrame.Warehouse.InventoryManger.Domain;
using QzgfFrame.Warehouse.InventoryManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.InventoryManger
{
    public class InventoryController : BaseController
    {
        private InventoryFacade inventoryFacade { set; get; }

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
            var result = new WarehouseInventory();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            WarehouseInventory result = inventoryFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseInventory entity)
        {
            try
            {
                if (id == "0")
                    this.inventoryFacade.Save(entity, "0");
                else
                    this.inventoryFacade.Update(entity);
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
            result = inventoryFacade.Delete(id.ToString(), savePath);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return inventoryFacade.FindByPage(page, pagesize);
        }
    }
}
