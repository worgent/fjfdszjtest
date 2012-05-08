using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using QzgfFrame.Utility.Core.JSON;
using System.Collections.Generic;
using QzgfFrame.Warehouse.IOListManger.Domain;
using QzgfFrame.Warehouse.IOListManger.Models;
using QzgfFrame.Archives.IOClassManger.Models;
using QzgfFrame.Archives.IOClassManger.Domain;
using QzgfFrame.Warehouse.IODetailManger.Models;
using QzgfFrame.Warehouse.IODetailManger.Domain;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.IOListManger
{
    public class IOListController : BaseController
    {
        private IOClassFacade ioClassFacade { set; get; }
        private IOListFacade ioListFacade { set; get; }
        private IOListFacadeEx ioListFacadeEx { set; get; }
        private UnitFacade unitFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private IODetailFacade ioDetailFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 所有入库信息
        /// </summary>
        /// <returns></returns>
        public ActionResult IList()
        {
            return View();
        }
        /// <summary>
        /// 所有调拨信息
        /// </summary>
        /// <returns></returns>
        public ActionResult TracList()
        {
            return View();
        }
        /// <summary>
        /// 所有出库信息
        /// </summary>
        /// <returns></returns>
        public ActionResult OList()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string TypeName, string id, string frameid)
        {
            IOList result = new IOList();
            string hql = " TypeName ='" + TypeName + "'"; //'IList'
            string strRet = "IListEdit";
            if (TypeName == "IList")
            {
                strRet = "IListEdit";
            }
            if (TypeName == "Trac")
            {
                IList<ArchiveDistrict> districts = districtFacade.LoadAll("Id", " TypeFlag='3'");
                ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
                IList<ArchiveCompany> companys = companyFacade.LoadAll();
                ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
                strRet = "TracListEdit";
            }
            WarehouseIOList iolist = new WarehouseIOList();
            IList<WarehouseIODetail> IODetails = new List<WarehouseIODetail>(); 
            IList<ArchiveIOClass> ioClasses = ioClassFacade.LoadAll("Id", hql);
            ViewData["ioClasses"] = new SelectList(ioClasses, "Id", "StyleName");
            IList<ArchiveUnit> units = unitFacade.LoadAll();
            ViewData["unit"] = new SelectList(units, "UnitName", "UnitName");
            ArchiveDistrict district = districtFacade.Get(this.currentUser.UserInfo.Areaid);
            iolist.IDistrictId = district.Id;
            iolist.IDistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(this.currentUser.UserInfo.Departmentid);
            iolist.ICompanyId = company.Id;
            iolist.ICompanyName = company.CompanyName;

            if (id == "0")
            {
                //新增时返回空对象
                WarehouseIODetail iodetail = new WarehouseIODetail();
                IODetails.Add(iodetail);
                iolist.BadNum = 0;
                iolist.ScrapNum = 0;
                iolist.TotalNum = 0;
                result.Id = "0";
            }
            else
            {
                //编辑时返回具体值
                iolist = ioListFacade.Get(id);
                result.Id = iolist.Id;
            }
            List<SelectListItem> StateList = new List<SelectListItem>();
            StateList.Add(new SelectListItem { Text = "正常", Value = "0", Selected = false });
            StateList.Add(new SelectListItem { Text = "坏件", Value = "1", Selected = false });
            StateList.Add(new SelectListItem { Text = "报废", Value = "2", Selected = false });
            ViewData["stateList"] = StateList;

            result.ioDetailList = IODetails;
            result.warehouseIOList = iolist;
            ViewData["frameid"] = frameid;
            return View(strRet, result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string TypeName, string id, string frameid)
        {
            IOList result = new IOList();
            string hql = " TypeName ='" + TypeName + "'"; //'IList'
            string strRet = "IListEdit";
            if (TypeName == "IList")
            {
                strRet = "IListEdit";
            }
            if (TypeName == "Trac")
            {
                IList<ArchiveDistrict> districts = districtFacade.LoadAll("Id", " TypeFlag='3'");
                ViewData["districts"] = new SelectList(districts, "Id", "DistrictName");
                IList<ArchiveCompany> companys = companyFacade.LoadAll();
                ViewData["companys"] = new SelectList(companys, "Id", "CompanyName");
                strRet = "TracListEdit";
            }               
            WarehouseIOList iolist = ioListFacade.Get(id);

            IList<WarehouseIODetail> IODetails = new List<WarehouseIODetail>();
            IList<ArchiveIOClass> ioClasses = ioClassFacade.LoadAll("Id", hql);
            ViewData["ioClasses"] = new SelectList(ioClasses, "Id", "StyleName");
            IList<ArchiveUnit> units = unitFacade.LoadAll();
            ViewData["unit"] = new SelectList(units, "UnitName", "UnitName");
            ArchiveDistrict district = districtFacade.Get(iolist.IDistrictId);
            iolist.IDistrictId = district.Id;
            iolist.IDistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(iolist.ICompanyId);
            iolist.ICompanyId = company.Id;
            iolist.ICompanyName = company.CompanyName;

            IList<WarehouseIODetail> details = ioDetailFacade.LoadAll(" and main.IOListId='" + iolist.Id + "'");
            if (details == null)
            {
                result.ioDetailList = IODetails;
            }
            else if (details.Count == 0)
            {
                result.ioDetailList = IODetails;
            }
            else
                result.ioDetailList = details;
            result.Id = iolist.Id;

            List<SelectListItem> StateList = new List<SelectListItem>();
            StateList.Add(new SelectListItem { Text = "正常", Value = "0", Selected = false });
            StateList.Add(new SelectListItem { Text = "坏件", Value = "1", Selected = false });
            StateList.Add(new SelectListItem { Text = "报废", Value = "2", Selected = false });
            ViewData["stateList"] = StateList;

            result.warehouseIOList = iolist;
            ViewData["frameid"] = frameid;
            return View(strRet, result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult OListEdit(string id)
        {
            WarehouseIOList result = null;
            string hql = " TypeName ='OList'";
            IList<ArchiveIOClass> ioClasses = ioClassFacade.LoadAll("Id", hql);
            ViewData["ioClasses"] = new SelectList(ioClasses, "Id", "StyleName");
            if (id == "0")
            {
                //新增时返回空对象
                result.Id = "0";
            }
            else
            {
                //编辑时返回具体值
                result = ioListFacade.Get(id);
               // result.Id = iolist.Id;
            }
            return View(result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, IOList entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
              //  entity.warehouseIOList.CreateUserId = this.currentUser.UserInfo.Id;
                if (id == "0")
                    result = this.ioListFacadeEx.Save(entity);
                else
                    result = this.ioListFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Trac(string id, IOList entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                //  entity.warehouseIOList.CreateUserId = this.currentUser.UserInfo.Id;
                if (id == "0")
                    result = this.ioListFacadeEx.SaveTrac(entity);
                else
                    result = this.ioListFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
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
            result = ioListFacade.Delete(id.ToString(), savePath);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return ioListFacade.FindByPage(page, pagesize);
        }

        /// <summary>
        /// 获取设备单位信息
        /// </summary>
        /// <returns></returns>
        public ActionResult GetUnitList()
        {
            string msg = "操作失败";
            IList<ArchiveUnit> Units = unitFacade.LoadAll();
            string list = JSONHelper.ToJSON(Units);
            return Json(new { Type = true, Message = msg, Rows = list }, JsonRequestBehavior.AllowGet);
        }
    }
}
