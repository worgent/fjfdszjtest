﻿using System.Web.Mvc;
using QzgfFrame.Resources.OtherInfoManger.Domain;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.OtherInfoManger
{
    public class OtherInfoController : BaseController
    {
        private OtherInfoFacade otherInfoFacade { set; get; }

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
        public ActionResult Edit(string id)
        {
            ResourceOtherInfo result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = otherInfoFacade.Get(id);
            }
            return View(result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ResourceOtherInfo entity)
        {
            try
            {
                
                return View();
            }
            catch
            {
                return View();
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = otherInfoFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
    }
}
