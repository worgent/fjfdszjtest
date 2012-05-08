using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.CommunityManagerManger
{
    public class CommunityManagerController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }

        private CommunityManagerFacade communityManagerFacade { set; get; }
        private CommunityManagerFacadeEx communityManagerFacadeEx { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
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
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveSaleDepartment> saleDepartments = saleDepartmentFacade.LoadAll();
            ViewData["saleDepartment"] = new SelectList(saleDepartments, "Id", "SaleDepartmentName");
            List<SelectListItem> IsFullTimeList = new List<SelectListItem>();
            IsFullTimeList.Add(new SelectListItem { Text = "专职", Value = "0", Selected = false });
            IsFullTimeList.Add(new SelectListItem { Text = "兼职", Value = "1", Selected = false });
            ViewData["isFullTime"] = IsFullTimeList;
            List<SelectListItem> IsInServiceList = new List<SelectListItem>();
            IsInServiceList.Add(new SelectListItem { Text = "在职", Value = "0", Selected = false });
            IsInServiceList.Add(new SelectListItem { Text = "离职", Value = "1", Selected = false });

            ViewData["isInService"] = IsInServiceList;

            var result = new SuppliesCommunityManager();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SuppliesCommunityManager result = communityManagerFacade.Get(id);
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveSaleDepartment> saleDepartments = saleDepartmentFacade.LoadAll();
            ViewData["saleDepartment"] = new SelectList(saleDepartments, "Id", "SaleDepartmentName");

            List<SelectListItem> IsFullTimeList = new List<SelectListItem>();
            IsFullTimeList.Add(new SelectListItem { Text = "专职", Value = "0", Selected = result.IsFullTime != 0 ? false : true });
            IsFullTimeList.Add(new SelectListItem { Text = "兼职", Value = "1", Selected = result.IsFullTime != 1 ? false : true });
            ViewData["isFullTime"] = IsFullTimeList;
            List<SelectListItem> IsInServiceList = new List<SelectListItem>();
            IsInServiceList.Add(new SelectListItem { Text = "在职", Value = "0", Selected = result.IsInService != 0 ? false : true });
            IsInServiceList.Add(new SelectListItem { Text = "离职", Value = "1", Selected = result.IsInService != 1 ? false : true });

            ViewData["isInService"] = IsInServiceList;
            return View("Edit", result);
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
                string ShowInfo = ""; string reFileName = "";
                string savePath = "../Upload/LoadFile/CommunityManager\\";
               // string savePath = Server.MapPath("../Upload/LoadFile/CommunityManager\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/x-json");
                if (btnFlag == "0")
                    result = communityManagerFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = communityManagerFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName, this.currentUser.UserInfo.Id);//导入数据库
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/x-json");
                if (result) msg = ShowInfo+"<br>"+msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/x-json");
            }
            catch (Exception ex)
            {
                return Json(new { Type = false, Message = msg+ex.Message }, "text/x-json");
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SuppliesCommunityManager entity)
        {
            try
            {
                bool result = false;
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                {
                    SuppliesCommunityManager rse = communityManagerFacade.GetHql(entity.IDCardNumber.Trim());
                    if (rse != null)
                        return Json(new { Type = false, Message = "操作失败,该身份证号在人员信息中已存在，不可重复添加", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    result = this.communityManagerFacade.Save(entity, "0");
                }
                else
                    result = this.communityManagerFacade.Update(entity);
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
                result = communityManagerFacade.Delete(id.ToString());
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
            return communityManagerFacade.FindByPage(page, pagesize,sortname,sortorder,gridsearch);
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult getIdCardNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Trim().Length == 18 || id.Trim().Length == 15)
                    {
                        SuppliesCommunityManager rse = communityManagerFacade.GetHql(id);
                        if (rse != null)
                            return Json(new { Type = true, Message = "操作成功", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    }
                    else
                        return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);

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
        //
        // 假删除操作
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteFalse(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = communityManagerFacade.DeleteFalse(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        /// <summary>
        /// 社区经理做为装维人员作为下拉框
        /// </summary>
        /// <returns></returns>

        public ActionResult GetCombobox()
        {
            bool result = false;
            string msg = "操作失败"; string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql = " CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

            if (strhql != "" && hql != "")
                hql += " and " + strhql;
            else if (strhql != "" && hql == "")
                hql += strhql;
            hql += " and (DelFlag!=1 or DelFlag is null) and IsInService='0'";
            string droplist = communityManagerFacade.GetCombobox(hql);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg, Rows = droplist }, JsonRequestBehavior.AllowGet);
        }
    }
}
