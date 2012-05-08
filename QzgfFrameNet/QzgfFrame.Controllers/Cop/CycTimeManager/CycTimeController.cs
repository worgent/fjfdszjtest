using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Cop.CycTimeManager.Domain;
using QzgfFrame.Cop.CycTimeManager.Models;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;

namespace QzgfFrame.Controllers.Cop.CycTimeManager
{
    /// <summary>
    /// 专线巡检周期
    /// </summary>
    public class CycTimeController : BaseController
    {
        #region

        /// <summary>
        /// 专线巡检周期
        /// </summary>
        private CycTimeFacade cycTimeFacade { set; get; }

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

            CopCycTime result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = cycTimeFacade.Get(id);
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
            CopCycTime result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = cycTimeFacade.Get(id);
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
        public ActionResult Edit(string id, CopCycTime entity)
        {
            bool reasult = false;
            try
            {
                if (id == "0")
                {
                    string sql = " CycTime = '" + entity.CycTime + "'";
                    IList<CopCycTime> copCycTimes = cycTimeFacade.LoadAll("id",sql);
                    if (copCycTimes.Count == 0)
                    {
                        reasult = this.cycTimeFacade.Save(entity);
                    }
                }
                else
                {
                    reasult = this.cycTimeFacade.Update(entity);
                }
                return View();
            }
            catch
            {
                return View();
            }
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

        public ActionResult GetAll()
        {
            IList<CopCycTime> citys = cycTimeFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopCycTime item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                    cycTime = item.CycTime
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 批量删除
        /// </summary>
        /// <param name="id">要删除的id字符串</param>
        /// <returns></returns>
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = cycTimeFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return cycTimeFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
