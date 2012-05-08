/****************************************************************** 
 * 文件名.........: MenuController.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System;
using System.Web.Mvc;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.MenufieldManger.Domain;
using QzgfFrame.System.MenufieldManger.Models;

namespace QzgfFrame.Controllers.System.MenufieldManger
{
    public class MenufieldController : BaseController
    {
        #region 变量定义

        private MenufieldFacade menufieldFacade { set; get; }

        #endregion

        #region 基本操作

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }

        //扩展页面
        public ActionResult IndexPermissions()
        {
            return View();
        }

        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var  result = new SystemMenufield();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            //编辑时返回具体值
            SystemMenufield result = menufieldFacade.Get(id);
            return View("Edit", result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SystemMenufield entity)
        {
            try
            {
                if (id == "0")
                    this.menufieldFacade.Save(entity);
                else
                    this.menufieldFacade.Update(entity);
                return View();
            }
            catch (Exception e)
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
            result = menufieldFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new {Type = result, Message = msg}, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 默认主页
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname,
                                         string gridsearch)
        {
            return menufieldFacade.FindByPage(page, pagesize, sortname, sortorder);
        }

        /// <summary>
        /// 扩展主页,其它模块调用
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string PermissionsGridPager(string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return menufieldFacade.FindByPage(sortname, sortorder);
        }

        #endregion

        #region 加强

        #endregion

    }
}
