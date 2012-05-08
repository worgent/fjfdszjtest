/*
 * 文件名.........: FillBlanksSubjectController.cs
 * 作者...........:  
 * 说明...........: 填空题控制器类  
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
*/
using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.FillBlanksSubjectManger
{
    public class FillBlanksSubjectController : BaseController
    {
        private FillBlanksSubjectFacade fillblankssubjectFacade { set; get; }

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
        public string GetList(string id)
        {
            string result = null;
            IList<ExamFillBlanksSubject> efss = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + id + "'");
            result = JavaScriptConvert.SerializeObject(efss);
            return result;
        }

        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            ExamFillBlanksSubject result = null;
            if(id=="0")
            {
                //新增时返回空对象
            }else
            {
                //编辑时返回具体值
                result = fillblankssubjectFacade.Get(id);
            }
            return View(result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ExamFillBlanksSubject entity)
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
            result = fillblankssubjectFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return fillblankssubjectFacade.FindByPage(page, pagesize);
        }
    }
}
