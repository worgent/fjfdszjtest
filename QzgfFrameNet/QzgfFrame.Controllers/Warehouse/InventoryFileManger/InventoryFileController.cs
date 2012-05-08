using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using QzgfFrame.Warehouse.InventoryFileManger.Domain;
using QzgfFrame.Warehouse.InventoryFileManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.InventoryFileManger
{
    public class InventoryFileController : BaseController
    {
        private InventoryFileFacade inventoryFileFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new WarehouseInventoryFile();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            WarehouseInventoryFile result = inventoryFileFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseInventoryFile entity)
        {
            try
            {
                if (id == "0")
                    this.inventoryFileFacade.Save(entity,"0");
                else
                    this.inventoryFileFacade.Update(entity);
                return View();
            }
            catch
            {
                return View();
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View();
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UploadFile(string btnFlag)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string savePath = "../Upload/LoadFile/InventoryLedger\\";
                string strsavePath = Request.MapPath(savePath);
                if (!Directory.Exists(strsavePath))
                {
                    Directory.CreateDirectory(strsavePath);
                }
                HttpPostedFileBase file = Request.Files[0];
                string filepath = strsavePath + Path.GetFileName(file.FileName);
                if (file.ContentLength > 0)
                {
                    file.SaveAs(filepath);
                        //得到文件数组
                        byte[] fileData = new Byte[file.ContentLength];
                        file.InputStream.Position = 0; //此句很关键
                        file.InputStream.Read(fileData, 0, file.ContentLength);
                        //得到文件大小
                        int fileLength = file.ContentLength;
                        //得到文件名字
                        string fileName = Path.GetFileName(file.FileName);
                        WarehouseInventoryFile entity = inventoryFileFacade.GetHql(fileName);
                        if(entity!=null)
                            return Json(new { Type = false, Message = "该文件已经存在,请先删除再导入新文件!!" }, "text/html");
                        //得到文件类型
                        string fileType = file.ContentType;
                        //自定义文件对象
                        WarehouseInventoryFile inventoryFile = new WarehouseInventoryFile();
                        inventoryFile.LedgerFileName = fileName;
                        inventoryFile.LedgerFileType = fileType;
                        inventoryFile.LedgerFileSize = fileLength;
                        result = inventoryFileFacade.Save(inventoryFile,"0");
                        if (!result)
                            return Json(new { Type = result, Message = msg }, "text/html");
                }
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, "text/html");
            }
            catch (Exception ex)
            {
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            string savePath = "../../../Upload/LoadFile/InventoryLedger\\";
                       
            bool result = false;
            string msg = "操作失败";
            result = inventoryFileFacade.Delete(id.ToString(), savePath);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return inventoryFileFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
