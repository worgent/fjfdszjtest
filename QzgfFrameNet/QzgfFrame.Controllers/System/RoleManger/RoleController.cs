/****************************************************************** 
 * 文件名.........: RoleController.cs 
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
using QzgfFrame.System.RoleManger.Domain;
using QzgfFrame.System.RoleManger.Models;

namespace QzgfFrame.Controllers.System.RoleManger
{
    public class RoleController : BaseController
    {
        #region 变量定义

        private RoleFacade roleFacade { set; get; }

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

        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new SystemRole();
            result.Id = "0";
            //下接框数据
            var ls = roleFacade.LoadAll();
            var sList = new SelectList(ls, "Id", "Name");
            ViewData["DrophouseData"] = sList;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SystemRole result = roleFacade.Get(id);
            //下接框数据
            var ls = roleFacade.LoadAll();
            var sList = new SelectList(ls, "Id", "Name");
            ViewData["DrophouseData"] = sList;
            return View("Edit", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string id)
        {
            return View("Search");
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SystemRole entity)
        {
            bool result = false;
            string msg = "操作失败";
            entity.Id = id;
            try
            {
                if (id == "0")
                    result=this.roleFacade.Save(entity);
                else
                    result=this.roleFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception e)
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = roleFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new {Type = result, Message = msg}, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname,
                                         string gridsearch)
        {
            return roleFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        #endregion

        #region 加强

        /// <summary>
        /// 角色菜单作为下拉框
        /// </summary>
        /// <returns></returns>
        public string GetCombobox()
        {
            return roleFacade.GetCombobox();
        }

        /// <summary>
        /// 修改用户状态
        /// </summary>
        /// <param name="id">字符串数组</param>
        /// <param name="state">状态</param>
        /// <returns></returns>
        public ActionResult UseStart(string id, string state)
        {
            bool result = false;
            string msg = "操作失败";

            result = roleFacade.UseState(id, state);
            if (result) msg = "操作成功";
            return Json(new {Type = result, Message = msg}, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 修改用户状态
        /// </summary>
        /// <param name="id">字符串数组</param>
        /// <param name="state">状态</param>
        /// <returns></returns>
        public ActionResult UseStop(string id, string state)
        {
            bool result = false;
            string msg = "操作失败";

            result = roleFacade.UseState(id, state);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        #endregion

    }
}
