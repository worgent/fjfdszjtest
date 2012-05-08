using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using QzgfFrame.Exam.AchievementxManager.Models;
using QzgfFrame.Exam.AchievementxManager.Domain;
using System.Collections;

namespace QzgfFrame.Controllers.Exam.AchievementxManager
{
    public class AchievementxController : BaseController
    {
        private AchievementxFacade achievementxFacade { set; get; }

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
            ExamAchievementx result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = achievementxFacade.Get(id);
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
        public ActionResult Edit(string id, ExamAchievementx entity)
        {
            try
            {
                if (id == "0")
                    this.achievementxFacade.Save(entity);
                else
                    this.achievementxFacade.Update(entity);
                return View();
            }
            catch
            {
                return View();
            }
        }
        public ActionResult GetAll()
        {
            IList<ExamAchievementx> citys = achievementxFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (ExamAchievementx item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = achievementxFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return achievementxFacade.FindByPage(page, pagesize);
        }
    }
}
