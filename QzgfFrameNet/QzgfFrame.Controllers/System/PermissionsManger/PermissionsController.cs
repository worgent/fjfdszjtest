/****************************************************************** 
 * 文件名.........: PermissionsController.cs 
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
using QzgfFrame.System.PermissionsManger.Domain;

namespace QzgfFrame.Controllers.System.PermissionsManger
{
    public class PermissionsController : BaseController
    {
        #region 变量定义

        private PermissionsFacade PermissionsFacade { set; get; }

        #endregion

        #region 基本操作

        //测试多模型关连
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index(string id)
        {
            ViewData["roleid"] = id;
            //ViewData["menutree"] = PermissionsFacade.LoadAllViewrolemenu(id);
            //ViewData["rolename"] = rolename;
            return View();
        }

        //
        // GET: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Get)]
        //public ActionResult Edit(string id)
        //{

        //    if(id=="0")
        //    {
        //        //新增时返回空对象
        //    }else
        //    {
        //        //编辑时返回具体值
        //        result = PermissionsFacade.Get(id);
        //    }
        //    //下接框数据
        //    var ls = PermissionsFacade.LoadAll();
        //    var sList = new SelectList(ls, "Id", "Name");
        //    ViewData["DrophouseData"] = sList;

        //    return View(result);
        //}


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        //[AcceptVerbs(HttpVerbs.Post)]
        //public ActionResult Edit(string id, SystemPermissions entity)
        //{
        //    try
        //    {
        //        if (id == "0")
        //            this.PermissionsFacade.Save(entity);
        //        else
        //            this.PermissionsFacade.Update(entity);
        //        return View();
        //    }
        //    catch(Exception e)
        //    {
        //        return View();
        //    }
        //}

        public ActionResult Save(string roleid, string applicationGridData, string functionGridData)
        {
            try
            {
                //string roleid = "1";
                PermissionsFacade.Save(functionGridData, applicationGridData, roleid);
                return View("Index");
            }
            catch (Exception e)
            {
                return View("Index");
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = PermissionsFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new {Type = result, Message = msg}, JsonRequestBehavior.AllowGet);
        }


        /// <summary>
        /// 暂时无用
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
            return PermissionsFacade.FindByPage(page, pagesize, sortname, sortorder);
        }

        #endregion

        #region 加强

        /// <summary>
        /// 加载视图的角色菜单信息
        /// </summary>
        /// <returns>id,text,pid,ischeck</returns>
        public string LoadAllViewrolemenu(string id)
        {
            return PermissionsFacade.LoadAllViewrolemenu(id);
        }

        /// <summary>
        /// 加载视图角色权限值
        /// </summary>
        /// <returns></returns>
        public string LoadAllViewrolepowerval(string id)
        {
            return PermissionsFacade.LoadAllViewrolepowerval(id);
        }

        #endregion
    }
}
