/*
 * 文件名.........: TestSubjectController.cs
 * 作者...........:  
 * 说明...........: 试卷题目控制器类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.TestSubjectTypeManger
{
    public class TestSubjectController : BaseController
    {
        private TestSubjectFacade testsubjectFacade { set; get; }

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
            ExamTestSubject result = null;
            if(id=="0")
            {
                //新增时返回空对象
            }else
            {
                //编辑时返回具体值
                result = testsubjectFacade.Get(id);
            }
            return View(result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ExamTestSubject entity)
        {
            try
            {
                return View();
            }
            catch
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
            result = testsubjectFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return testsubjectFacade.FindByPage(page, pagesize);
        }
    }
}
