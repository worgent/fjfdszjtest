/*
 * 文件名.........: JudgeSubjectController.cs
 * 作者...........:  
 * 说明...........: 判断题控制器类  
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
*/
using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.JudgeSubjectManger
{
    public class JudgeSubjectController : BaseController
    {
        private JudgeSubjectFacade judgesubjectFacade { set; get; }

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
        public string GetList(string id)
        {
            string result = null;
            IList<ExamJudgeSubject> ejss = judgesubjectFacade.LoadAll("ID", "SubjectID='" + id + "'");
            result = JavaScriptConvert.SerializeObject(ejss);
            return result;
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            ExamJudgeSubject result = null;
            if(id=="0")
            {
                //新增时返回空对象
            }else
            {
                //编辑时返回具体值
                result = judgesubjectFacade.Get(id);
            }
            return View(result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ExamJudgeSubject entity)
        {
            try
            {
                if (id == "0")
                    this.judgesubjectFacade.Save(entity);
                else
                    this.judgesubjectFacade.Update(entity);
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
            result = judgesubjectFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return judgesubjectFacade.FindByPage(page, pagesize);
        }
    }
}
