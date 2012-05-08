using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Resources.PersonnelManger.Domain;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Archives.CertificateManger.Models;
using QzgfFrame.Archives.CertificateManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.Archives.DutyManger.Models;
using QzgfFrame.Archives.DutyManger.Domain;
using QzgfFrame.Archives.PostsManger.Models;
using QzgfFrame.Archives.PostsManger.Domain;
using QzgfFrame.Archives.QualificationTypeManger.Models;
using QzgfFrame.Archives.QualificationTypeManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.Archives.ItemPropertyManger.Models;
using QzgfFrame.Archives.ItemPropertyManger.Domain;
using QzgfFrame.Utility.Core;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.PersonnelManger
{
    public class PersonnelController : BaseController
    {
        private PersonnelFacade personnelFacade { set; get; }
        private PersonnelFacadeEx personnelFacadeEx { set; get; }

        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private DutyFacade dutyFacade { set; get; }
        private CertificateFacade certificateFacade { set; get; }
        private MaintainSpecialtyFacade maintainSpecialtyFacade { set; get; }
        private QualificationTypeFacade qualificationTypeFacade { set; get; }
        private ItemPropertyFacade itemPropertyFacade { set; get; }
        private StagnationFacade stagnationFacade { set; get; }
        private DiplomaFacade diplomaFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private PostsFacade postsFacade { set; get; }
        
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
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExcelOut(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("ExcelOut");
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
            ResourcePersonnel result = personnelFacade.Get(id);
            ArchiveDistrict city = districtFacade.Get(result.CityId);
            ViewData["city"] = city.DistrictName;
            ArchiveDuty duty = dutyFacade.Get(result.DutyId);
            if (duty != null)
                ViewData["duty"] = duty.DutyName;
            else
                ViewData["duty"] = "";
            ArchiveCertificate certificate1 = certificateFacade.Get(result.Certificate1);
            if (certificate1 != null)
                ViewData["certificate1"] = certificate1.CertificateName;
            else
                ViewData["certificate1"] = "";
            ArchiveCertificate certificate2 = certificateFacade.Get(result.Certificate2);
            if (certificate2 != null)
                ViewData["certificate2"] = certificate2.CertificateName;
            else
                ViewData["certificate2"] = "";
            ArchiveCertificate certificate3 = certificateFacade.Get(result.Certificate3);
            if (certificate3 != null)
                ViewData["certificate3"] = certificate3.CertificateName;
            else
                ViewData["certificate3"] = "";
            ArchiveCertificate certificate4 = certificateFacade.Get(result.Certificate4);
            if (certificate4 != null)
                ViewData["certificate4"] = certificate4.CertificateName;
            else
                ViewData["certificate4"] = "";
            ArchiveCertificate certificate5 = certificateFacade.Get(result.Certificate5);
            if (certificate5 != null)
                ViewData["certificate5"] = certificate5.CertificateName;
            else
                ViewData["certificate5"] = "";
            ArchiveMaintainSpecialty maintainSpecialty = maintainSpecialtyFacade.Get(result.MaintainSpecialtyId);
            ViewData["maintainSpecialty"] = maintainSpecialty.MaintainSpecialtyName ;
            ArchiveQualificationType qualificationType = qualificationTypeFacade.Get(result.QualificationTypeId);
            ViewData["qualificationType"] = qualificationType.QualificationTypeName ;
            ArchiveDiploma diploma = diplomaFacade.Get(result.DiplomaId);
            ViewData["diploma"] = diploma.DiplomaName ;
            ArchivePosts post = postsFacade.Get(result.PostsId);
            if (post != null)
                ViewData["posts"] = post.PostsName;
            else
                ViewData["posts"] = "";
            ArchiveItemProperty itemProperty = itemPropertyFacade.Get(result.ItemPropertyId);
            ViewData["itemProperty"] = itemProperty.ItemPropertyName;
            if (result.DistrictId != null)
            {
                ArchiveDistrict district = districtFacade.Get(result.DistrictId);
                ViewData["district"] = district.DistrictName;
            }
            else
                ViewData["district"] = "";
            if (result.CompanyId != null)
            {
                ArchiveCompany company = companyFacade.Get(result.CompanyId);
                ViewData["company"] = company.CompanyName;
            }
            else
                ViewData["company"] = "";
            if (result.GridId != null)
            {
                ArchiveGrid grid = gridFacade.Get(result.GridId);
                ViewData["grid"] = grid.GridName;
            }
            else
                ViewData["grid"] = "";
            if (result.StagnationId != null)
            {
                ArchiveStagnation stagnation = stagnationFacade.Get(result.StagnationId);
                ViewData["stagnation"] = stagnation.StagnationName;
            }
            else
                ViewData["stagnation"] = "";
            return View("Display", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveDuty> dutys = dutyFacade.LoadAll();
            ViewData["duty"] = new SelectList(dutys, "Id", "DutyName");
            IList<ArchiveCertificate> certificates = certificateFacade.LoadAll();
            ViewData["certificate"] = new SelectList(certificates, "Id", "CertificateName");
            IList<ArchiveMaintainSpecialty> maintainSpecialtys = maintainSpecialtyFacade.LoadAll();
            ViewData["maintainSpecialty"] = new SelectList(maintainSpecialtys, "Id", "MaintainSpecialtyName");
            IList<ArchiveQualificationType> qualificationTypes = qualificationTypeFacade.LoadAll();
            ViewData["qualificationType"] = new SelectList(qualificationTypes, "Id", "QualificationTypeName");
            IList<ArchiveDiploma> diplomas = diplomaFacade.LoadAll();
            ViewData["diploma"] = new SelectList(diplomas, "Id", "DiplomaName");
            IList<ArchivePosts> posts = postsFacade.LoadAll();
            ViewData["posts"] = new SelectList(posts, "Id", "PostsName");
            IList<ArchiveItemProperty> itemPropertys = itemPropertyFacade.LoadAll();
            ViewData["itemProperty"] = new SelectList(itemPropertys, "Id", "ItemPropertyName");
            if (this.currentUser.UserInfo.Isrepair == "1")
                ViewData["company"] = this.currentUser.UserInfo.Departmentid;
            else
                ViewData["company"] = ""; var result = new ResourcePersonnel();
            result.Id = "0";
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");
            IList<ArchiveDuty> dutys = dutyFacade.LoadAll();
            ViewData["duty"] = new SelectList(dutys, "Id", "DutyName");
            IList<ArchiveCertificate> certificates = certificateFacade.LoadAll();
            ViewData["certificate"] = new SelectList(certificates, "Id", "CertificateName");
            IList<ArchiveMaintainSpecialty> maintainSpecialtys = maintainSpecialtyFacade.LoadAll();
            ViewData["maintainSpecialty"] = new SelectList(maintainSpecialtys, "Id", "MaintainSpecialtyName");
            IList<ArchiveQualificationType> qualificationTypes = qualificationTypeFacade.LoadAll();
            ViewData["qualificationType"] = new SelectList(qualificationTypes, "Id", "QualificationTypeName");
            IList<ArchiveDiploma> diplomas = diplomaFacade.LoadAll();
            ViewData["diploma"] = new SelectList(diplomas, "Id", "DiplomaName");
            IList<ArchivePosts> posts = postsFacade.LoadAll();
            ViewData["posts"] = new SelectList(posts, "Id", "PostsName");
            IList<ArchiveItemProperty> itemPropertys = itemPropertyFacade.LoadAll();
            ViewData["itemProperty"] = new SelectList(itemPropertys, "Id", "ItemPropertyName");
            ResourcePersonnel result = personnelFacade.Get(id);
            ViewData["frameid"] = frameid;
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
                string savePath = "../Upload/LoadFile/Personnel\\";
               // string savePath = Server.MapPath("../Upload/LoadFile/Personnel\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = personnelFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = personnelFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo+"<br>"+msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ResourcePersonnel entity)
        {
            try
            {
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.personnelFacade.Save(entity, "0");
                else
                    result = this.personnelFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }

        public ActionResult GetDropDownList()
        {
            bool result = false;
            string msg = "操作失败";string hql="";
            if(this.currentUser.UserInfo.Isrepair=="1")
                 hql=" CompanyId='"+this.currentUser.UserInfo.Departmentid+"'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

            if (strhql != "" && hql != "")
                hql += " and " + strhql;
            else if (strhql != "" && hql == "")
                hql += strhql;
            
            string[] droplist = personnelFacadeEx.GetDropDownList(hql);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg, Rows = droplist }, JsonRequestBehavior.AllowGet);
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
                result = personnelFacade.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
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
                result = personnelFacade.DeleteFalse(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (gridsearch == "select")    //选择人员信息
                return personnelFacade.SelectByPage(page, pagesize);
            else
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and (main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null)";
                string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                if (strhql != "")
                    gridsearch += " and " + strhql;
                return personnelFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
            }
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
                    if(id.Trim().Length == 18 || id.Trim().Length == 15)
                    {
                        ResourcePersonnel rse = personnelFacade.GetHql(id);
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

        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "姓名","身份证号","手机号码","性别","出生日期","学历文凭","人员岗位","人员职责","是否骨干"
                            ,"是否自维 ","所属城市","所属区县", "所属公司", "所属网格","所属驻点","维护专业"
                            , "工作时间","入职时间",  "项目属性","认证资历类型","移动公司上岗证1","上岗证1颁发时间"
                            , "移动公司上岗证2","上岗证2颁发时间"
                           };
        private string[] aryType = { "str","str","str","sex","date","str","str","str","bool"
                            ,"bool","str","str", "str", "str","str","str"
                            , "date","date", "str", "str","str","date"
                            , "str" ,"date"
                           };
        private string aryField = @"main.FullName,main.IDCardNumber,main.MobileNumber,main.Sex,main.BirthDate,diploma.DiplomaName
                            ,duty.DutyName,posts.PostsName, main.IsBackbone, main.IsSelfMaintain
                            , dd.DistrictName  as CityName,ad.DistrictName, ac.CompanyName, grid.GridName,sta.StagnationName
                            , maintain.MaintainSpecialtyName, main.WorkDate, main.EntryDate, item.ItemPropertyName,
                             qtype.QualificationTypeName, cert1.CertificateName as certname1, 
                             main.CertificateAwardDate1, cert2.CertificateName as certname2,main.CertificateAwardDate2";
        #endregion
        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut(string gridsearch)
        {
            bool result = false;
            //string msg = "操作失败";
            try
            {
                if (this.currentUser.UserInfo.Isrepair == "1")
                    gridsearch += " and (main.CompanyId='" + this.currentUser.UserInfo.Departmentid + "' or main.CompanyId='' or main.CompanyId is null)";
                string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                if (strhql != "")
                    gridsearch += " and " + strhql;
                IList<object[]> ls = personnelFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("人员信息", aryTilte, ls, aryType, null);
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
