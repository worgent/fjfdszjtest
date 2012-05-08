using System.Web.Mvc;
using System.Linq;
using System;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.DistrictManger
{
    public class DistrictController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
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
            var result = new ArchiveDistrict();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            ArchiveDistrict result = districtFacade.Get(id);
            return View("Edit", result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveDistrict entity)
        {
            bool result = false;
            string msg = "操作失败";
            try
            {
                if(entity.ParentId=="" &&entity.ParentId==null)
                    return Json(new { result = result, Message = msg  }, JsonRequestBehavior.AllowGet);
                ArchiveDistrict parent = districtFacade.Get(entity.ParentId);
                entity.HNo = parent.HNo + 1;
                if (id == "0")
                    result = this.districtFacade.Save(entity);
                else
                    result = this.districtFacade.Update(entity); 
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { result = result, Message =msg+ ex.Message }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false; bool DelFlag = false;
            string msg = "操作失败";
            result = districtFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return districtFacade.FindByPage(page, pagesize);
        }

        /// <summary>
        /// 为选择区县树结点，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelDistrict(string id)
        {
            ViewData["pid"] = id;
            return View();
        }

        /// <summary>
        ///获取区县树
        /// </summary>
        /// <returns></returns>
        public string GetDistrict(string id)
        {
            return districtFacade.GetFather(id,3);
        }
        /// <summary>
        /// 取得父结点信息
        /// </summary>
        /// <returns></returns>
        public string GetFather(string id)
        {
            return districtFacade.GetFather(id,0);
            //return Json(new { Type = true, Rows = districtFacade.GetFather(id, 0) }, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 区县名称作为下拉框
        /// </summary>
        /// <returns></returns>
        public ActionResult GetCombobox()
        {
            string AreaId=this.currentUser.UserInfo.Areaid;
            int HNO=Convert.ToInt32(this.currentUser.UserInfo.LEVELNO);
            return Json(new { Type = true, Rows = districtFacade.GetCombobox(AreaId, HNO) }, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 区县名称作为下拉框
        /// </summary>
        /// <returns></returns>
        public string  GetSearchCombobox()
        {
            string AreaId = this.currentUser.UserInfo.Areaid;
            int HNO = Convert.ToInt32(this.currentUser.UserInfo.LEVELNO);
            return districtFacade.GetCombobox(AreaId, HNO);
        }
        /// <summary>
        /// 区县名称作为下拉框不包含洛江区跟清濛开发区
        /// </summary>
        /// <returns></returns>
        public string GetDistrictCombobox()
        {
            var ls = districtFacade.LoadAll("Id", "HNo='3'"); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.DistrictName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
