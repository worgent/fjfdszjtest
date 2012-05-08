using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.BuildWayManger.Domain;
using QzgfFrame.Archives.BuildWayManger.Models;
using QzgfFrame.Archives.MaintainTypeManger.Domain;
using QzgfFrame.Archives.MaintainTypeManger.Models;
using QzgfFrame.Archives.CommunityTypeManger.Domain;
using QzgfFrame.Archives.CommunityTypeManger.Models;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.RegisterManger.Domain;
using QzgfFrame.Supplies.RegisterManger.Models;
using QzgfFrame.Supplies.RegisterDetailManger.Models;
using QzgfFrame.Supplies.RegisterDetailManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.RegisterManger
{
    public class RegisterController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }
        private CommunityManagerFacade communityManagerFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private AccessWayFacade accessWayFacade { set; get; }
        private BuildWayFacade buildWayFacade { set; get; }
        private MaintainTypeFacade maintainTypeFacade { set; get; }
        private CommunityTypeFacade communityTypeFacade { set; get; }
        private RegisterFacade registerFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }
        private RegisterDetailFacade registerDetailFacade { set; get; }
        private RegisterFacadeEx registerFacadeEx { set; get; }
        /// <summary>
        /// 首页信息调用saleDepartment,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 首页信息调用saleDepartment,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult JlIndex()
        {
            return View();
        }
        /// <summary>
        /// 首页信息调用saleDepartment,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult PjIndex()
        {
            return View();
        }
        /// <summary>
        ///营业部的耗材使用统计
        /// </summary>
        /// <returns></returns>
        public ActionResult TzIndex()
        {
            return View();
        }
        /// <summary>
        /// 为选择集团客户，通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Display()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            Register result = new Register();
            result.suppliesRegister = registerFacade.Get(id);

            IList<SuppliesRegisterDetail> DistributionDetails = new List<SuppliesRegisterDetail>();
            SuppliesRegisterDetail DistributionDetail = new SuppliesRegisterDetail();
            DistributionDetails.Add(DistributionDetail);
            result.registerDetailList = registerDetailFacade.LoadAll("Id", " RegisterId='" + result.suppliesRegister.Id + "'");
            if (result.registerDetailList.Count == 0)
            {
                result.registerDetailList = DistributionDetails;
            }
            result.Id = id;
            return View("Display", result);
        }
        //
        // GET: /CommunityManagerr/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            var result = new  Register();
            IList<ArchiveDistrict> districts = new List<ArchiveDistrict>();
            if(this.currentUser.UserInfo.LEVELNO=="2")
                districts=districtFacade.LoadAll("SeqNO", "HNo=3");
            else
                districts = districtFacade.LoadAll("SeqNO", "Id='" + this.currentUser.UserInfo.Areaid + "'");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", "IsMaintain='1'");
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            
            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveBuildWay> buildWays = buildWayFacade.LoadAll();
            ViewData["buildWay"] = new SelectList(buildWays, "Id", "BuildWayName");
            IList<ArchiveMaintainType> maintainTypes = maintainTypeFacade.LoadAll();
            ViewData["maintainType"] = new SelectList(maintainTypes, "Id", "MaintainTypeName");
            IList<ArchiveCommunityType> communityTypes = communityTypeFacade.LoadAll();
            ViewData["communityType"] = new SelectList(communityTypes, "Id", "CommunityTypeName");
            SuppliesRegister register = new SuppliesRegister();
            result.suppliesRegister = register;
            SuppliesRegisterDetail RegisterDetail = new SuppliesRegisterDetail();
            IList<regMapList> mpLists = new List<regMapList>();
            regMapList mpList = new regMapList();
            mpList.regdetail = RegisterDetail;
            mpList.maxNum = 0;
            mpLists.Add(mpList);
            result.regMapLists = mpLists;
            result.Id = "0";
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            Register result = new Register();
            IList<ArchiveDistrict> districts = new List<ArchiveDistrict>();
            if (this.currentUser.UserInfo.LEVELNO == "2")
                districts = districtFacade.LoadAll("SeqNO", "HNo=3");
            else
                districts = districtFacade.LoadAll("SeqNO", "Id='" + this.currentUser.UserInfo.Areaid + "'");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll("Id", "IsMaintain='1'");
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");

            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveBuildWay> buildWays = buildWayFacade.LoadAll();
            ViewData["buildWay"] = new SelectList(buildWays, "Id", "BuildWayName");
            IList<ArchiveMaintainType> maintainTypes = maintainTypeFacade.LoadAll();
            ViewData["maintainType"] = new SelectList(maintainTypes, "Id", "MaintainTypeName");
            IList<ArchiveCommunityType> communityTypes = communityTypeFacade.LoadAll();
            ViewData["communityType"] = new SelectList(communityTypes, "Id", "CommunityTypeName");

            IList<SuppliesRegisterDetail> RegisterDetails = new List<SuppliesRegisterDetail>();
            SuppliesRegisterDetail RegisterDetail = new SuppliesRegisterDetail();
            RegisterDetails.Add(RegisterDetail);
            result.suppliesRegister = registerFacade.Get(id);

            IList<SuppliesRegisterDetail> regDetailLists = registerDetailFacade.LoadAll("Id","RegisterId='" + result.suppliesRegister.Id + "'");
            IList<regMapList> mpLists = new List<regMapList>();
            foreach (SuppliesRegisterDetail sd in regDetailLists)
            {
                regMapList mpList = new regMapList();
                string strhql = " and main.DistrictId='" + result.suppliesRegister.DistrictId + "'";
                strhql += " and main.SaleDepartmentId='" + result.suppliesRegister.SaleDepartmentId + "'";
                strhql += " and main.SuppliesTypeId='" + sd.SuppliesTypeId + "' and main.State='0'";
                SuppliesSuppliesStock supliesStock = suppliesStockFacade.GetHql(strhql);
                mpList.regdetail = sd;
                mpList.maxNum = sd.Num;
                mpLists.Add(mpList);
            }
            result.regMapLists = mpLists;
            result.MaintainerId = result.suppliesRegister.MaintainerId;
            result.SaleDepartmentId = result.suppliesRegister.SaleDepartmentId;
            result.Id = id;
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
        }
        //
        // POST: /CommunityManagerr/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, Register entity)
        {
            try
            {
                bool result = false;
                entity.suppliesRegister.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                entity.suppliesRegister.MaintainerId = entity.MaintainerId;
                entity.suppliesRegister.SaleDepartmentId = entity.SaleDepartmentId;
                if (id == "0")
                    result = this.registerFacadeEx.Save(entity);
                else
                    result = this.registerFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /CommunityManagerr/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = registerFacadeEx.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // 作废
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Invalid(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = registerFacadeEx.Invalid(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
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
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
           // gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return registerFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        public string GridPagerJL(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
            //gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return registerFacade.FindJLByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        public string GridPagerPJ(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
            //gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return registerFacade.FindPJByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        public string GridPagerTZ(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.Isrepair == "1")
                gridsearch += " and main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetWSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");
            if (strhql != "")
                gridsearch += " and " + strhql;
            //gridsearch += " and main.DistrictId='" + this.currentUser.UserInfo.Areaid + "'";
            return registerFacade.FindTZByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
          //
        // GET: /CommunityManagerr/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetCount(string id)
        {
            try
            {
                string[] idarr = id.Split(',');
                string strsql = "";
                if (idarr[0] != "" && idarr[1] != "")
                {
                    strsql = " a.SaleDepartmentId='" + idarr[0] + "' and a.DistrictId='" + idarr[1] + "'";
                }
                string[] strdate = idarr[2].Split('-');
                string strYear = strdate[0];
                string strMonth = strdate[1];

                IList<object[]> tz = registerFacade.LoadAll(strsql, strYear, strMonth);
                int ADSLInstallations = 0;
                int ADSLRelocation = 0;
                int FTTHInstallations = 0;
                int FTTHADSLRelocation = 0;
                for (int i = 0; i < tz.Count; i++)
                {
                    if (tz[i][1].ToString().Trim() == "ADSL")
                    {
                        ADSLInstallations = Convert.ToInt32(tz[i][2]);
                        ADSLRelocation = Convert.ToInt32(tz[i][3]);
                    }
                    if (tz[i][1].ToString().Trim() == "FTTH")
                    {
                        FTTHInstallations = Convert.ToInt32(tz[i][2]);
                        FTTHADSLRelocation = Convert.ToInt32(tz[i][3]);
                    }
                }
                return Json(new
                {
                    Type = true,
                    ADSLInstallations = ADSLInstallations,
                    ADSLRelocation = ADSLRelocation,
                    FTTHInstallations = FTTHInstallations,
                    FTTHADSLRelocation = FTTHADSLRelocation
                }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new
                {
                    Type = false,
                    ADSLInstallations = 0,
                    ADSLRelocation = 0,
                    FTTHInstallations = 0,
                    FTTHADSLRelocation = 0
                }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetPBOSSJobNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Trim().Length < 12 || id.Trim().Length>20)
                        result = false;
                    else
                    {
                        SuppliesRegister rse = registerFacade.GetHql(id);
                        if (rse != null)
                            return Json(new { Type = true, Message = "操作成功", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    }
                }
                else
                    result = false;
                return Json(new { Type = result, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
    }
}
