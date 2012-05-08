/*
 * 文件名.........: TestTypeController.cs
 * 作者...........:  
 * 说明...........: 试卷类型控制器类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.TestTypeManger.Domain;
using QzgfFrame.Exam.TestTypeManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.TestTypeManger
{
    public class TestTypeController : BaseController
    {
        private TestTypeFacade testtypeFacade { set; get; }

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
            ExamTestType result = new ExamTestType();
            result.ID = "0";
            return View("Edit", result);
        }

        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            ExamTestType result = testtypeFacade.Get(id);
            return View("Edit", result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ExamTestType entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.testtypeFacade.Save(entity);
                else
                    result = this.testtypeFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = testtypeFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return testtypeFacade.FindByPage(page, pagesize);
        }
    }
}
