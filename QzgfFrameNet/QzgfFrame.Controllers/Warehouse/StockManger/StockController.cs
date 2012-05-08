using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using System.Collections.Generic;
using QzgfFrame.Warehouse.StockManger.Domain;
using QzgfFrame.Warehouse.StockManger.Models;
using QzgfFrame.Archives.StockTypeManger.Models;
using QzgfFrame.Archives.StockTypeManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.StockManger
{
    public class StockController : BaseController
    {
        private StockFacade stockFacade { set; get; }
        private StockTypeFacade stockTypeFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
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
        /// 调拨备件,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            string[] ary = id.Split(':');
            ViewData["districtdids"] = ary[0];
            ViewData["companyids"] = ary[1];
            ViewData["tids"] = ary[2];
            return View("SelIndex");
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
            string hql = " IsMaintain ='1'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveFactory> factorys = factoryFacade.LoadAll();
            ViewData["factorys"] = new SelectList(factorys, "Id", "Abbrevia");
            IList<ArchiveEquipModel> equipModels = equipModelFacade.LoadAll();
            ViewData["equipModels"] = new SelectList(equipModels, "Id", "EquipModelName");
            IList<ArchiveUnit> units = unitFacade.LoadAll();
            ViewData["units"] = new SelectList(units, "Id", "UnitName");
            IList<ArchiveStockType> stockTypes = stockTypeFacade.LoadAll();
            ViewData["stockTypes"] = new SelectList(stockTypes, "Id", "StockTypeName");
            var result = new WarehouseStock();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
            string hql = " IsMaintain ='1'";
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", hql);
            ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveFactory> factorys = factoryFacade.LoadAll();
            ViewData["factorys"] = new SelectList(factorys, "Id", "Abbrevia");
            IList<ArchiveEquipModel> equipModels = equipModelFacade.LoadAll();
            ViewData["equipModels"] = new SelectList(equipModels, "Id", "EquipModelName");
            IList<ArchiveUnit> units = unitFacade.LoadAll();
            ViewData["units"] = new SelectList(units, "Id", "UnitName");
            IList<ArchiveStockType> stockTypes = stockTypeFacade.LoadAll();
            ViewData["stockTypes"] = new SelectList(stockTypes, "Id", "StockTypeName");
            WarehouseStock result = stockFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseStock entity)
        {
            try
            {
                if (id == "0")
                    this.stockFacade.Save(entity, "0");
                else
                    this.stockFacade.Update(entity);
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
            result = stockFacade.Delete(id.ToString(), savePath);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return stockFacade.FindByPage(page, pagesize);
        }
        public override string GridSelPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            gridsearch += " and main.State='0' and main.Num>0 ";
            return stockFacade.FindSelByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
