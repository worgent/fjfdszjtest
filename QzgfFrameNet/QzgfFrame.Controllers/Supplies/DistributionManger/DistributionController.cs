using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Supplies.DistributionManger.Domain;
using QzgfFrame.Supplies.DistributionManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Supplies.DistributionDetailManger.Models;
using QzgfFrame.Supplies.DistributionDetailManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.DistributionManger
{
    public class DistributionController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private UnitFacade unitFacade { set; get; }
        private DistributionDetailFacade distributionDetailFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }

        private DistributionFacade distributionFacade { set; get; }
        private DistributionFacadeEx distributionFacadeEx { set; get; }
        #region 分配方
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据，所有未确定分配单，可修改,即草稿单
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据，所有未确定分配单，可修改,即草稿单
        /// </summary>
        /// <returns></returns>
        public ActionResult AllIndex()
        {
            return View();
        }
        #endregion
        #region 接收方
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据，所有待确定接收单，不可修改，操作：确认，退回（写原因）
        /// </summary>
        /// <returns></returns>
        public ActionResult WRIndex()
        {
             ViewData["leveNo"] = this.currentUser.UserInfo.LEVELNO.Trim();
            return View();
        }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据，所有已确定接收单，不可修改
        /// </summary>
        /// <returns></returns>
        public ActionResult YRIndex()
        {
            return View();
        }/// <summary>
        /// 首页信息调用grid,时通过service取得数据，所有被退回分配单，可修改
        /// </summary>
        /// <returns></returns>
        public ActionResult TRIndex()
        {
            ViewData["leveNo"] = this.currentUser.UserInfo.LEVELNO.Trim();
            return View();
        }
        /// <summary>
        /// 区县分配营业部
        /// </summary>
        /// <returns></returns>
        public ActionResult YWIndex()            
        {
            return View("YWIndex");
        }
        #endregion
        /// <summary>
        /// 为选择集团客户，通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Display()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult YwSearch(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("YwSearch");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult AllSearch(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("AllSearch");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            Distribution result = new Distribution();
            result.suppliesDistribution = distributionFacade.Get(id);
            ArchiveDistrict district = districtFacade.Get(result.suppliesDistribution.CityId);
            result.suppliesDistribution.CityId = district.Id;
            result.suppliesDistribution.DistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(result.suppliesDistribution.CompanyId);
            result.suppliesDistribution.CompanyId = company.Id;
            result.suppliesDistribution.CompanyName = company.CompanyName;
            
            IList<SuppliesDistributionDetail> DistributionDetails = new List<SuppliesDistributionDetail>();
            SuppliesDistributionDetail DistributionDetail = new SuppliesDistributionDetail();
            DistributionDetails.Add(DistributionDetail);
            result.distributionDetailList = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + result.suppliesDistribution.Id + "'");
            if (result.distributionDetailList.Count == 0)
            {
                result.distributionDetailList = DistributionDetails;
            }
            result.Id = id;
            return View("Display", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string TypeFlag, string id, string frameid)
        {
            var result = new Distribution();
            SuppliesDistribution suppliesDistribution = new SuppliesDistribution();
            suppliesDistribution.Id = "0";
            ArchiveDistrict district = districtFacade.Get(this.currentUser.UserInfo.Areaid);
            suppliesDistribution.CityId = district.Id;
            suppliesDistribution.DistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(this.currentUser.UserInfo.Departmentid);
            suppliesDistribution.CompanyId = company.Id;
            suppliesDistribution.CompanyName = company.CompanyName;
            suppliesDistribution.DistributionUser = this.currentUser.UserInfo.Username;

            IList<SuppliesDistributionDetail> DistributionDetails = new List<SuppliesDistributionDetail>();
            SuppliesDistributionDetail DistributionDetail = new SuppliesDistributionDetail();
            DistributionDetails.Add(DistributionDetail);
            result.distributionDetailList = DistributionDetails;
            result.suppliesDistribution = suppliesDistribution;
            ViewData["frameid"] = frameid;
            if (TypeFlag.Trim() == "YW")
                return  View("YwEdit", result);
            else
               return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string TypeFlag, string id, string frameid)
        {
            Distribution result = new Distribution();
            if (TypeFlag.Trim() == "YW")
                result.suppliesDistribution = distributionFacade.GetYw(id);
            else
                result.suppliesDistribution = distributionFacade.Get(id);

            IList<SuppliesDistributionDetail> dDetailLists = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + result.suppliesDistribution.Id + "'");
            IList<STMapList> mpLists = new List<STMapList>();
            foreach (SuppliesDistributionDetail sd in dDetailLists)
            {
                STMapList mpList = new STMapList();
                string hql = " and main.DistrictId='"+result.suppliesDistribution.CityId+"'";
                hql += " and main.CompanyId='"+result.suppliesDistribution.CompanyId+"'";
                hql += " and main.SuppliesTypeId='"+sd.SuppliesTypeId+"' and main.State='0'";
                SuppliesSuppliesStock supliesStock = suppliesStockFacade.GetHql(hql);
                int maxNum = supliesStock.Num;
                mpList.sddetail = sd;
                mpList.maxNum = maxNum + sd.Num;
                mpLists.Add(mpList);
            }
            result.stMapLists = mpLists;
            result.Id = id;
            ViewData["frameid"] = frameid;
            if (TypeFlag.Trim() == "YW")
            {
                return View("YwModify", result);
            }
            else
            {
                return View("Modify", result);
            }
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
        public ActionResult Edit(string id, Distribution entity)
        {
            try
            {
                bool result = false;
                entity.suppliesDistribution.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                    result = this.distributionFacadeEx.Save(entity);
                else
                    result = this.distributionFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult YwEdit(string id, Distribution entity)
        {
            try
            {
                bool result = false;
                entity.suppliesDistribution.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                    result = this.distributionFacadeEx.SaveYw(entity);
                else
                    result = this.distributionFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Modify(string id, Distribution entity)
        {
            try
            {
                bool result = false;
                entity.suppliesDistribution.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                    return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
                else
                    result = this.distributionFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult YwModify(string id, Distribution entity)
        {
            try
            {
                bool result = false;
                entity.suppliesDistribution.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                    return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
                else
                    result = this.distributionFacadeEx.UpdateYw(entity);
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
                result = distributionFacadeEx.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // 作废操作
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Invalid(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = distributionFacadeEx.Invalid(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Confirm(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            Distribution result = new Distribution();
            result.suppliesDistribution = distributionFacade.Get(id);
            ArchiveDistrict district = districtFacade.Get(result.suppliesDistribution.CityId);
            result.suppliesDistribution.CityId = district.Id;
            result.suppliesDistribution.DistrictName = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(result.suppliesDistribution.CompanyId);
            result.suppliesDistribution.CompanyId = company.Id;
            result.suppliesDistribution.CompanyName = company.CompanyName;
           
            IList<SuppliesDistributionDetail> DistributionDetails = new List<SuppliesDistributionDetail>();
            SuppliesDistributionDetail DistributionDetail = new SuppliesDistributionDetail();
            DistributionDetails.Add(DistributionDetail);
            result.distributionDetailList = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + result.suppliesDistribution.Id + "'");
            if (result.distributionDetailList.Count == 0)
            {
                result.distributionDetailList = DistributionDetails;
            }
            result.Id = id;
            return View("Confirm", result);
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Confirm(Distribution entity)
        {
            try
            {
                bool result = false;
                entity.suppliesDistribution.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if(entity.suppliesDistribution.State=="2")
                   result = this.distributionFacade.UpdateH(entity.suppliesDistribution);
                if(entity.suppliesDistribution.State=="1")
                    result = this.distributionFacadeEx.Confirm(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        #region 分配方数据 
        /// <summary>
        /// 分配方显示所有分配单包括各种状态
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string AllGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO == "2")
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";

                gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.DistrictId!='' or main.DistrictId  is not null )";
                gridsearch += " and (main.DCompanyId!='' or main.DCompanyId  is not null )";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
            }
            else
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.DCompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
                //string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
                //string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                //if (strhql != "")
                    //gridsearch += " and " + strhql;
                gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
            }
            return distributionFacade.FindRByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        /// <summary>
        /// 分配方显示所有未确定分配单
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";

            gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
            gridsearch += " and (main.DistrictId!='' or main.DistrictId  is not null )";
            gridsearch += " and (main.DCompanyId!='' or main.DCompanyId  is not null )";
            gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
            gridsearch += " and main.State='0'";
            return distributionFacade.FindRByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #endregion
        #region 接收方
        /// <summary>
        /// 被分配方即接收方 已退回耗材
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string TGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO == "2")
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";

                gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.DistrictId!='' or main.DistrictId  is not null )";
                gridsearch += " and (main.DCompanyId!='' or main.DCompanyId  is not null )";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
                gridsearch += " and main.State='2'";
            }
            else{
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.DCompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            //string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
           // string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            //if (strhql != "")
               // gridsearch += " and " + strhql;
            gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
            gridsearch += " and main.State='2'";
            }
            return distributionFacade.FindRByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        /// <summary>
        /// 已确认耗材
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string YGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO == "2")
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";

                gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.DistrictId!='' or main.DistrictId  is not null )";
                gridsearch += " and (main.DCompanyId!='' or main.DCompanyId  is not null  )";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
                gridsearch += " and main.State='1'";
            }
            else
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.DCompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
                //string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
                //string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                //if (strhql != "")
                  //  gridsearch += " and " + strhql;
                gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
                gridsearch += " and main.State='1'";
            }
            return distributionFacade.FindRByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        /// <summary>
        /// 待确认耗材
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string WGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {            
            if (this.currentUser.UserInfo.LEVELNO == "2")
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";

                gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.DistrictId!='' or main.DistrictId  is not null )";
                gridsearch += " and (main.DCompanyId!='' or main.DCompanyId  is not null )";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";
                gridsearch += " and main.State='0'";
            }
            else
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and main.DCompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
                //string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
               // string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                //if (strhql != "")
                 //   gridsearch += " and " + strhql; 
                gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
                gridsearch += " and (main.SaleDepartmentId='' or main.SaleDepartmentId  is null )";           
                gridsearch += " and main.State='0'";
            }
            return distributionFacade.FindRByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        /// <summary>
        /// 所有县市分配给营业部的所有信息。
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string YwGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            gridsearch += " and main.CityId='" + this.currentUser.UserInfo.Areaid + "'";
            gridsearch += " and main.State='0' and (main.SaleDepartmentId!='' or main.SaleDepartmentId  is not null )";
            return distributionFacade.FindYwByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #endregion
    }
}
