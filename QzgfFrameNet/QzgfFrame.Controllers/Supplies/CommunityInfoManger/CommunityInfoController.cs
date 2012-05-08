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
using QzgfFrame.Archives.CommunityTypeManger.Domain;
using QzgfFrame.Archives.CommunityTypeManger.Models;
using QzgfFrame.Supplies.CommunityInfoManger.Domain;
using QzgfFrame.Supplies.CommunityInfoManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Supplies.CommunityInfoManger
{
    public class CommunityInfoController : BaseController
    {
        private AccessWayFacade accessWayFacade { set; get; }
        private BuildWayFacade buildWayFacade { set; get; }
        private CommunityTypeFacade communityTypeFacade { set; get; }

        private CommunityInfoFacade communityInfoFacade { set; get; }
        private CommunityInfoFacadeEx communityInfoFacadeEx { set; get; }
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
            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveBuildWay> buildWays = buildWayFacade.LoadAll();
            ViewData["buildWay"] = new SelectList(buildWays, "Id", "BuildWayName");
            IList<ArchiveCommunityType> communityTypes = communityTypeFacade.LoadAll();
            ViewData["communityType"] = new SelectList(communityTypes, "Id", "CommunityTypeName");
            
            var result = new SuppliesCommunityInfo();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SuppliesCommunityInfo result = communityInfoFacade.Get(id);
            IList<ArchiveAccessWay> accessWays = accessWayFacade.LoadAll();
            ViewData["accessWay"] = new SelectList(accessWays, "Id", "AccessWayName");
            IList<ArchiveBuildWay> buildWays = buildWayFacade.LoadAll();
            ViewData["buildWay"] = new SelectList(buildWays, "Id", "BuildWayName");
            IList<ArchiveCommunityType> communityTypes = communityTypeFacade.LoadAll();
            ViewData["communityType"] = new SelectList(communityTypes, "Id", "CommunityTypeName");

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
                string savePath = "../Upload/LoadFile/CommunityInfo\\";
               // string savePath = Server.MapPath("../Upload/LoadFile/CommunityInfo\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/x-json");
                if (btnFlag == "0")
                    result = communityInfoFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = communityInfoFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName, this.currentUser.UserInfo.Id);//导入数据库
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
        public ActionResult Edit(string id, SuppliesCommunityInfo entity)
        {
            try
            {
                bool result = false;
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                string msg = "操作失败";
                if (id == "0")
                {
                    SuppliesCommunityInfo rse = communityInfoFacade.GetHql(entity.CommunityCode.Trim());
                    if (rse != null)
                        return Json(new { Type = false, Message = "操作失败,该小区编码在小区信息中已存在，不可重复添加", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    result = this.communityInfoFacade.Save(entity, "0");
                }
                else
                    result = this.communityInfoFacade.Update(entity);
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
                result = communityInfoFacade.Delete(id.ToString());
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
            return communityInfoFacade.FindByPage(page, pagesize,sortname,sortorder,gridsearch);
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
                result = communityInfoFacade.DeleteFalse(id.ToString());
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
        public ActionResult GetCheckCode(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Length >20)
                        result = false;
                    else
                    {
                        SuppliesCommunityInfo item = communityInfoFacade.GetSql(id);
                        if (item != null)
                            return Json(new
                            {
                                Type = true,
                                Message = "操作成功",
                                Rows = new
                                {
                                    Id = item.Id,
                                    CommunityCode = item.CommunityCode,
                                    CommunityName = item.CommunityName,
                                    CommunityTypeId = item.CommunityTypeId,
                                    CommunityTypeName = item.CommunityTypeName,
                                    BuildWayId = item.BuildWayId,
                                    BuildWayName = item.BuildWayName,
                                    AccessWayId = item.AccessWayId,
                                    AccessWayName = item.AccessWayName
                                }
                            }, JsonRequestBehavior.AllowGet);
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

        public ActionResult GetCommunityCode(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<SuppliesCommunityInfo> result = communityInfoFacade.LoadAll(" and CommunityCode like'%" + list + "%'");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (SuppliesCommunityInfo item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        CommunityCode = item.CommunityCode,
                        CommunityName = item.CommunityName,
                        CommunityTypeId = item.CommunityTypeId,
                        CommunityTypeName = item.CommunityTypeName,
                        BuildWayId = item.BuildWayId,
                        BuildWayName = item.BuildWayName,
                        AccessWayId = item.AccessWayId,
                        AccessWayName = item.AccessWayName
                    });
                }
                // string rowsjson = JsonConvert.SerializeObject(mapList);
                return Json(new { Type = true, Data = mapList }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
            }
        }
    }
}
