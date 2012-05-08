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
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using Newtonsoft.Json;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.System.UserManger.Domain;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;

namespace QzgfFrame.Controllers.System.UserManger
{
    public class UserController : BaseController
    {
        #region 变量定义
        private UserFacade userFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
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


        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        { 
            var  result = new SystemUser();
            result.Id = "0";
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            return View("Edit",result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SystemUser result = userFacade.Get(id);
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            return View("Edit",result);
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
        public ActionResult Edit(string id, SystemUser entity)
        {
            bool result = false;
            string msg = "操作失败";
            entity.Id = id;
            try
            {
                if (id == "0")
                {
                   result= this.userFacade.Save(entity);
                }
                else
                    result=this.userFacade.Update(entity);
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
            result = userFacade.Delete(id.ToString());
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

            //return userFacade.FindByPage(page, pagesize, sortname, sortorder);
            //因用户信息是跨两个类库，所以放到控制层实现
            string hql =
            @"select new SystemUser(main.Id,main.Username,main.Nickname,main.Tel,main.Email,main.Password,main.Departmentid,main.Areaid,main.Remark,main.State,
            case main.State when '1' then '启用' when '2' then '停用' else '删除' END as Statename) 
            from SystemUser main where main.State!='0'  ";
            string vSql = hql + gridsearch;
            vSql += @" and main.Id!='1' order by main." + sortname + " " + sortorder;

            IList<SystemUser> lsuser = userFacade.FindByPage(page, pagesize, vSql);
            IList<ArchiveDistrict> lsdistricts = districtFacade.LoadAll();
            IList<ArchiveCompany> lscompanys = companyFacade.LoadAll();
            var jsonlist = (from vlsuser in lsuser
                            join vlsdistricts in lsdistricts
                                on vlsuser.Areaid equals vlsdistricts.Id into joineddistricts
                            from vlsdistricts1 in joineddistricts.DefaultIfEmpty()

                            join vlscompanys in lscompanys
                                on vlsuser.Departmentid equals vlscompanys.Id into joinedcompanys
                            from vlscompanys1 in joinedcompanys.DefaultIfEmpty()
                            select new
                            {
                                vlsuser.Id,
                                vlsuser.Nickname,
                                vlsuser.Departmentid,
                                Departmentname = vlscompanys1 != null ? vlscompanys1.CompanyName : "",
                                Areaname = vlsdistricts1 != null ? vlsdistricts1.DistrictName : "",
                                vlsuser.Email,
                                vlsuser.Password,
                                vlsuser.Remark,
                                vlsuser.Statename,
                                vlsuser.Tel,
                                vlsuser.Username
                            }
                            ).ToArray();

            string rowsjson = JsonConvert.SerializeObject(jsonlist);
            int recordCount = userFacade.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        //扩展页面
        public ActionResult IndexPermissions()
        {
            return View();
        }

        /// <summary>
        /// 用户是否存在
        /// </summary>
        /// <param name="Username"></param>
        /// <returns></returns>
        public string Isusernameexist(string Username)
        {
            return userFacade.IsUserNameExist(Username) ? "false" : "true";
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
            result = userFacade.UseState(id, state);
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
            result = userFacade.UseState(id, state);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }


        /// <summary>
        /// 角色信息查看
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ModifyRole(string id)
        {
            SystemUser result = userFacade.Get(id);
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            return View("ModifyRole", result);
        }
        /// <summary>
        /// 角色信息修改
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult EditRole(string id, SystemUser entity)
        {
            bool result = false;
            string msg = "操作失败";
            entity.Id = id;
            try
            {
                result = this.userFacade.UpdateRole(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception e)
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }
        #endregion
    }
}
