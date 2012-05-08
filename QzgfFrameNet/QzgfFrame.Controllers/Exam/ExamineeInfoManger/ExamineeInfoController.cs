/*
 * 文件名.........: ExamineeInfoController.cs
 * 作者...........:  
 * 说明...........: 考生信息控制器类  
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
*/


using System.Collections.Generic;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Exam.ExamTypeManger.Domain;
using QzgfFrame.Exam.ExamTypeManger.Models;
using QzgfFrame.Resources.PersonnelManger.Domain;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.ExamineeInfoManger
{
    public class ExamineeInfoController : BaseController
    {
        private PersonnelFacade personnelFacade { set; get; }
        private ExamineeInfoFacade examineeinfoFacade { set; get; }
        private ExamineeInfoFacadeEx examineeinfoFacadeEx { set; get; }
        private ExamTypeFacade examtypeFacade { set; get; }
        private DiplomaFacade diplomaFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id)
        {
            var result = new ExamineeInfo();
            result.ExamExamineeInfo = examineeinfoFacade.Get(id);
            if (result.ExamExamineeInfo.Sex == 0)
                result.Sex = "男";
            else
                result.Sex = "女";
            ExamType examtype = examtypeFacade.Get(result.ExamExamineeInfo.ExamTypeID);
            result.ExamType = examtype.Type;
            ArchiveDiploma diploma = diplomaFacade.Get(result.ExamExamineeInfo.DiplomaID);
            result.Diploma = diploma.DiplomaName;
            return View("Display", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult SelectList(string id)
        {
            ViewBag.gridType = id;
            return View();
        }

        //批量增加考生信息
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult BatchAdd(string id)
        {
            var result = new ExamineeInfo();
            IList<ExamType> ExamTypes = examtypeFacade.LoadAll();
            for (int i = 0; i < ExamTypes.Count; i++)
            {
                if (ExamTypes[i].Type == "考核")
                {
                    result.ExamType = ExamTypes[i].ID;
                }
            }
            return View("BatchAdd", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new ExamineeInfo();
            result.ExamExamineeInfo = new ExamExamineeInfo();
            result.ExamExamineeInfo.ID = "0";
            IList<ExamType> ExamTypes = examtypeFacade.LoadAll();
            ViewBag.selexamtype = new SelectList(ExamTypes, "ID", "Type");
            IList<ArchiveDiploma> diplomas = diplomaFacade.LoadAll();
            ViewBag.diploma = new SelectList(diplomas, "Id", "DiplomaName");
            ViewBag.state = "add";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {   
            var result = examineeinfoFacade.GetExamineeInfo(id);
            IList<ExamType> ExamTypes = examtypeFacade.LoadAll();
            ViewBag.selexamtype = new SelectList(ExamTypes, "ID", "Type");
            IList<ArchiveDiploma> diplomas = diplomaFacade.LoadAll();
            ViewBag.diploma = new SelectList(diplomas, "Id", "DiplomaName");
            ViewBag.state = "modify";
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
                string savePath = "../Upload/LoadFile/ExamineeInfo\\";
                // string savePath = Server.MapPath("../Upload/LoadFile/Personnel\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = examineeinfoFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = examineeinfoFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName, userid);//导入数据库
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo + "<br>" + msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(ExamineeInfo entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (entity.examineeinfos != null)   //批量增加
                {
                    ExamExamineeInfo[] ExamExamineeInfos = (ExamExamineeInfo[])JavaScriptConvert.DeserializeObject(entity.examineeinfos, typeof(ExamExamineeInfo[]));
                    foreach (ExamExamineeInfo eei in ExamExamineeInfos)
                    {
                        result = examineeinfoFacade.Save(eei);
                    }
                }
                else if (entity.ExamExamineeInfo!=null)
                {
                    if (entity.ExamExamineeInfo.ID == "0")
                        result = this.examineeinfoFacade.Save(entity.ExamExamineeInfo);
                    else
                        result = this.examineeinfoFacade.Update(entity.ExamExamineeInfo);
                }

                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch  //Exception e
            {

                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = examineeinfoFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return examineeinfoFacade.FindByPage(page, pagesize);
        }

        public string selectGridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return personnelFacade.SelectByPage(page, pagesize);
        }

        /// <summary>
        /// 考试登录名是否存在
        /// </summary>
        /// <param name="Loginname"></param>
        /// <returns></returns>
        public string Isloginnameexist(string Loginname)
        {
            return examineeinfoFacade.IsLoginNameExist(Loginname) ? "false" : "true";
        }
    }
}
