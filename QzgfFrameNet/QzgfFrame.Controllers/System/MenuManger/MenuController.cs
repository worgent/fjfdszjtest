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
using QzgfFrame.System.MenuManger.Domain;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.System.MenuManger
{
    public class MenuController : BaseController
    {
        #region 变量定义

        private MenuFacade menuFacade { set; get; }
        //测试多模型关连
        private MenuFacadeEx menuFacadeEx { set; get; }

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
            var result = new SystemMenu();
            result.Id = "0";
            result.Ismenu = "1";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            SystemMenu result = menuFacade.Get(id);
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
        public ActionResult Edit(string id, SystemMenu entity)
        {
            bool result = false;
            string msg = "操作失败";
            entity.Id = id;
            //entity.Ismenu = "1";
            try
            {
                if (id == "0")
                    result=this.menuFacade.Save(entity);
                else
                    result=this.menuFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
                //return View("Edit");
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
            result = menuFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname,
                                         string gridsearch)
        {
            return menuFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        #endregion

        #region 加强

        /// <summary>
        /// 按钮控制
        /// </summary>
        /// <param name="menuid">父结点</param>
        /// <returns></returns>
        public string GetButton(string menuid)
        {
            if (menuid == null) menuid = "null";
            //return menuFacade.GetButton(menuid);//不调用内部实现,从session中处理
            //超级管理员,可以操作所有菜单
            var ls = menuFacade.LoadAll("Id", "Ismenu='2'");

            //多级运算
            var curlist = currentUser.MenuPermission.Where(m => (m.Id.Trim() ?? "") == menuid.Trim()).ToArray();
            //多角色同时关连时，需叠代处理(或运算)
            int optval = curlist.Aggregate(0, (current, menuval) => current | menuval.Optval);

            if (currentUser.UserInfo.Id.Trim() == "1" || menuid == "" || menuid == "null")
            {
                optval = 0xFFFF;//16个按钮控制位
            }
            //1.与权限相关,设置成
            var jsonlist = (ls.Select(a => new
            {
                id = a.Url.Trim().ToLower(),
                icon = a.Pic,
                name = a.Name,
                optval = a.Orderno
            })).Where(m => (optval & Int32.Parse(m.optval)) == Int32.Parse(m.optval)).Distinct().ToList();//.ToArray()
            //2.不进行权限控制
            //Ismenu=0//不进行权限控置，Ismenu=1是菜单的,Ismenu=2进行权限分配置，用户不能增加的,系统默认配置
            var lsex = menuFacade.LoadAll("Id", "Ismenu='0' and Father='" + menuid + "'");
            var jsonlsex = (lsex.Select(a => new
            {
                id = a.Url.Trim().ToLower(),
                icon = a.Pic,
                name = a.Name,
                optval = a.Orderno
            })).Distinct().ToList();

            if (jsonlsex.Count != 0) jsonlist.AddRange(jsonlsex);

            if (jsonlist.Count == 0) jsonlist = jsonlsex;

            return JsonConvert.SerializeObject(jsonlist);
        }

        /// <summary>
        /// 左边菜单
        /// </summary>
        /// <param name="menuid"></param>
        /// <returns></returns>
        public string GetMenu()
        {
            //return menuFacade.GetMenu();//暂不用实现层,通过session取得用户菜单信息
            //超级管理员,可以操作所有菜单
            if (currentUser.UserInfo.Id.Trim() == "1")
            {
                return menuFacade.GetMenu();
            }

            //需要验证过滤用户菜单
            var list = currentUser.MenuPermission.Where(m => m.Ismenu == "1");
            var jsonlist = (list.Select(a => new
            {
                text = a.Name,
                id = a.Id.Trim(),
                url = a.Url,
                icon = a.Pic,
                pid = a.Father.Trim()
            })
               ).Distinct().ToArray();

            return JsonConvert.SerializeObject(jsonlist);

        }

        #endregion

    }
}
