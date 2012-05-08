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
using QzgfFrame.System.DepartmentManger.Domain;
using QzgfFrame.System.DepartmentManger.Models;

namespace QzgfFrame.Controllers.System.DepartmentManger
{
    public class DepartmentController : BaseController
    {
        #region 变量定义
       
        private DepartmentFacade departmentFacade { set; get; }

        #endregion

        #region 基本操作

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
           var tmp= currentUser.MenuPermission;
            return View();
        }
        //public ActionResult add()
        //{
        //    return View();
        //}

        ////扩展页面
        //public ActionResult IndexPermissions()
        //{
        //    return View();
        //}


        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            var result = new SystemDepartment();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SystemDepartment result = departmentFacade.Get(id);
            return View("Edit", result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SystemDepartment entity)
        {
            bool result = false;
            string msg = "操作失败";
            entity.Id = id;
            try
            {
                if (id == "0")
                    result=this.departmentFacade.Save(entity);
                else
                    result=this.departmentFacade.Update(entity);
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
            result = departmentFacade.Delete(id.ToString());
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
            return departmentFacade.FindByPage(page, pagesize, sortname, sortorder);
        }


        #endregion

        #region 加强

        //扩展页面
        public ActionResult IndexPermissions()
        {
            return View();
        }

        /// <summary>
        /// 取得父结点信息
        /// </summary>
        /// <returns></returns>
        public string GetFather()
        {
            string listfather = departmentFacade.GetFather();
            return listfather;
        }
        /// <summary>
        /// 取得父结点信息
        /// </summary>
        /// <returns></returns>
        //public string GetCompany(string id)
        //{
        //    string listfather = departmentFacade.GetFather(id, "5");
        //    return listfather;
        //}
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

            result = departmentFacade.UseState(id, state);
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

            result = departmentFacade.UseState(id, state);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        #endregion

    }
}
