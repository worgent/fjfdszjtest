/*

 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能

*/

using System;
using System.Web.Mvc;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.System.LogManger.Domain;
using QzgfFrame.System.LogManger.Models;


namespace QzgfFrame.Controllers.System.LogManger
{
    /// <summary>
    /// auto gen
    /// </summary>
    public class SystemLogController : BaseController
    {
        #region 变量定义,申明

        private SystemLogFacade logFacade { set; get; }

        #endregion

        #region 基本操作

        public ActionResult Index()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new SystemLog();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SystemLog result = logFacade.Get(id);
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string id)
        {
            return View("Search");
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SystemLog entity)
        {
            try
            {
                if (id == "0")
                    logFacade.Save(entity);
                else
                    logFacade.Update(entity);
            }
            catch (Exception e)
            {
            }
            return View();
        }

        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = logFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new {Type = result, Message = msg}, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname,
                                         string gridsearch)
        {

            return logFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        #endregion

        #region 加强
        #endregion

        #region 保留

        #endregion
    }
}
