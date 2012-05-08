using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using QzgfFrame.Cop.TerminalTimeManager.Models;
using QzgfFrame.Cop.TerminalTimeManager.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using System.Collections;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;

namespace QzgfFrame.Controllers.Cop.TerminalTimeManager
{
    /// <summary>
    /// 自助终端巡检周期
    /// </summary>
    public class TerminalTimeController : BaseController
    {
        #region

        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        private TerminalTimeFacade terminalTimeFacade { set; get; }
        /// <summary>
        /// 自助终端
        /// </summary>
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        /// <summary>
        /// 网点类型
        /// </summary>
        private OutletsTypeFacade outletsTypeFacade { set; get; }

        #endregion

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// 显示添加页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            CopTerminalTime result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = terminalTimeFacade.Get(id);
            }
            return View("Edit", result);
        }

        /// <summary>
        /// 显示编辑页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            CopTerminalTime result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = terminalTimeFacade.Get(id);
            }
            return View(result);
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, CopTerminalTime entity)
        {
            bool reasult = false;
            try
            {
                if (id == "0")
                {
                    //判断该周期是否已经存在
                    string sql = " TerminalTime = '" + entity.TerminalTime + "'";
                    IList<CopTerminalTime> copTerminalTimes = terminalTimeFacade.LoadAll("id", sql);
                    if (copTerminalTimes.Count == 0)
                    {
                        reasult = this.terminalTimeFacade.Save(entity);
                    }
                }
                else
                {
                    reasult = this.terminalTimeFacade.Update(entity);
                }
                return View();
            }
            catch
            {
                return View();
            }
        }

        public ActionResult GetAll()
        {
            IList<CopTerminalTime> citys = terminalTimeFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopTerminalTime item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                    cycTime = item.TerminalTime
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 显示查询页面
        /// </summary>
        /// <param name="frameid">菜单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }

        /// <summary>
        /// 批量删除
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = terminalTimeFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO != "2")
            {
                gridsearch += " and rEquip.DistrictId = '" + this.currentUser.UserInfo.Areaid + "' ";
            }
            return terminalTimeFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
