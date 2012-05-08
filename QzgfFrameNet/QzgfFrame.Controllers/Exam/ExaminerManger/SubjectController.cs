/*
 * 文件名.........: SubjectController.cs
 * 作者...........:  
 * 说明...........: 试题控制器类  
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
*/

using System;
using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.ExaminerManger
{
    public class SubjectController : BaseController
    {
        private SubjectFacade subjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private ScaleGradeFacade scalegradeFacade { set; get; }
        private FillBlanksSubjectFacade fillblankssubjectFacade { set; get; }
        private MultipleSubjectFacade multiplesubjectFacade { set; get; }
        private JudgeSubjectFacade judgesubjectFacade { set; get; }
        private SubjectFacadeEx subjectFacadeEx { set; get; }



        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 选择与试卷中试题类型和试卷类型相匹配的试题
        /// </summary>
        /// <returns></returns>
        public ActionResult Display(string id)
        {
            string[] strPrams = id.Split(',');
            ViewBag.typeID = strPrams[0];
            ViewBag.courseTypeID = strPrams[1];
            return View();
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {          
            IList<ExamSubjectType> subjectType = subjecttypeFacade.LoadAll();    //题目类型    
            ViewBag.selsubjectType = new SelectList(subjectType, "ID", "Type");
            return View("Edit");
        }

        // 选择题编辑
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult EditMultiple(string id)
        {
            SubjectContent result = new SubjectContent();
            string where = " Type='选择题'";
            ExamSubjectType subjectType = subjecttypeFacade.Get("ID", where);
            IList<ExamCourseType> courseType = courseFacade.LoadAll();    //科目类型
            IList<ExamScaleGrade> scalegradeType = scalegradeFacade.LoadAll();   //等级类型
            ViewBag.selcourseType = new SelectList(courseType, "Id", "Name");
            ViewBag.selscalegradeType = new SelectList(scalegradeType, "ID", "Type");

            IList<ExamMultipleSubject> Multiples = new List<ExamMultipleSubject>();
            for (int i = 0; i < 4; i++)
            {
                ExamMultipleSubject Multiple = new ExamMultipleSubject();
                Multiple.OrderNo = (i + 1).ToString();
                Multiples.Add(Multiple);
            }

            Multiples[0].Answer = true;   //设置第一个选项为true答案

            if (id == "0")
            {
                //新增时返回空对象
                ExamSubjectContent es = new ExamSubjectContent();
                es.TypeId = subjectType.ID;
                es.CreatorId = this.currentUser.UserInfo.Id;
                result.ExamSubContent = es;
                result.MultipleSubject = Multiples;
                ViewBag.state = "add";
            }
            else
            {
                //编辑时返回具体值
                SubjectContent subContent = subjectFacade.GetContent(id);
                subContent.MultipleSubject = multiplesubjectFacade.LoadAll("OrderNo", "SubjectID='" + subContent.ExamSubContent.Id + "'");
                if (subContent.MultipleSubject.Count == 0)
                {
                    subContent.MultipleSubject = Multiples;
                }
                result = subContent;
                ViewBag.state = "modify";
            }
            return View("EditMultiple", result);
        }

        // 填空题编辑
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult EditFillBlank(string id)
        {

            SubjectContent result = new SubjectContent();
            string where = " Type='填空题'";
            ExamSubjectType subjectType = subjecttypeFacade.Get("ID", where);
            IList<ExamCourseType> courseType = courseFacade.LoadAll();    //科目类型
            IList<ExamScaleGrade> scalegradeType = scalegradeFacade.LoadAll();   //等级类型
            ViewBag.selcourseType = new SelectList(courseType, "Id", "Name");
            ViewBag.selscalegradeType = new SelectList(scalegradeType, "ID", "Type");

            IList<ExamFillBlanksSubject> FillBlankSubjects = new List<ExamFillBlanksSubject>();
            for (int i = 0; i < 1; i++)
            {
                ExamFillBlanksSubject FillBlankSubject = new ExamFillBlanksSubject();
                FillBlankSubject.OrderNo = (i + 1).ToString();
                FillBlankSubjects.Add(FillBlankSubject);
            }

            if (id == "0")
            {
                //新增时返回空对象
                ExamSubjectContent es = new ExamSubjectContent();
                es.TypeId = subjectType.ID;
                es.CreatorId = this.currentUser.UserInfo.Id;
                result.ExamSubContent = es;
                result.FillBlanksSubject = FillBlankSubjects;
                ViewBag.state = "add";
            }
            else
            {
                //编辑时返回具体值
                SubjectContent subContent = subjectFacade.GetContent(id);
                subContent.FillBlanksSubject = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + subContent.ExamSubContent.Id + "'");
                if (subContent.FillBlanksSubject.Count == 0)
                {
                    subContent.FillBlanksSubject = FillBlankSubjects;
                }
                result = subContent;
                ViewBag.state = "modify";
            }
            return View("EditFillBlank", result);
        }

        // 判断题编辑
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult EditJudge(string id)
        {
            SubjectContent result = new SubjectContent();
            string where = " Type='判断题'";
            ExamSubjectType subjectType = subjecttypeFacade.Get("ID", where);
            IList<ExamCourseType> courseType = courseFacade.LoadAll();    //科目类型
            IList<ExamScaleGrade> scalegradeType = scalegradeFacade.LoadAll();   //等级类型
            ViewBag.selcourseType = new SelectList(courseType, "Id", "Name");
            ViewBag.selscalegradeType = new SelectList(scalegradeType, "ID", "Type");

            IList<ExamJudgeSubject> JudgeSubjects = new List<ExamJudgeSubject>();
            for (int i = 0; i < 1; i++)
            {
                ExamJudgeSubject JudgeSubject= new ExamJudgeSubject();
                JudgeSubject.Answer = false;
                JudgeSubjects.Add(JudgeSubject);
            }

            if (id == "0")
            {
                //新增时返回空对象
                ExamSubjectContent es = new ExamSubjectContent();
                es.TypeId = subjectType.ID;
                es.CreatorId = this.currentUser.UserInfo.Id;
                result.ExamSubContent = es;
                result.JudgeSubject = JudgeSubjects;
                ViewBag.state = "add";
            }
            else
            {
                //编辑时返回具体值
                SubjectContent subContent = subjectFacade.GetContent(id);
                subContent.JudgeSubject = judgesubjectFacade.LoadAll("ID", "SubjectID='" + subContent.ExamSubContent.Id + "'");
                if (subContent.JudgeSubject.Count == 0)
                {
                    subContent.JudgeSubject = JudgeSubjects;
                }
                result = subContent;
                ViewBag.state = "modify";
            }
            return View("EditJudge", result);
        
        }

        // 选择题预览
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ViewMultiple(string id)
        {
            SubjectContent result = new SubjectContent();
            SubjectContent subContent = subjectFacade.GetContent(id);
            ExamCourseType courseType = courseFacade.Get(subContent.ExamSubContent.CourseTypeId);    //科目类型
            ExamScaleGrade scalegradeType = scalegradeFacade.Get(subContent.ExamSubContent.ScaleGradeId);   //等级类型
            subContent.CourseName = courseType.Name;
            subContent.ScaleGradeType = scalegradeType.Type;
            subContent.MultipleSubject = multiplesubjectFacade.LoadAll("OrderNo", "SubjectID='" + subContent.ExamSubContent.Id + "'");
            result = subContent;
            return View("ViewMultiple", result);
        }

        // 填空题预览
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ViewFillBlank(string id)
        {
            SubjectContent result = new SubjectContent();
            SubjectContent subContent = subjectFacade.GetContent(id);
            ExamCourseType courseType = courseFacade.Get(subContent.ExamSubContent.CourseTypeId);    //科目类型
            ExamScaleGrade scalegradeType = scalegradeFacade.Get(subContent.ExamSubContent.ScaleGradeId);   //等级类型
            subContent.CourseName = courseType.Name;
            subContent.ScaleGradeType = scalegradeType.Type;
            subContent.FillBlanksSubject = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + subContent.ExamSubContent.Id + "'");
            result = subContent;
            return View("ViewFillBlank", result);
        }
        // 判断题预览
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ViewJudge(string id)
        {
            SubjectContent result = new SubjectContent();
            SubjectContent subContent = subjectFacade.GetContent(id);
            ExamCourseType courseType = courseFacade.Get(subContent.ExamSubContent.CourseTypeId);    //科目类型
            ExamScaleGrade scalegradeType = scalegradeFacade.Get(subContent.ExamSubContent.ScaleGradeId);   //等级类型
            subContent.CourseName = courseType.Name;
            subContent.ScaleGradeType = scalegradeType.Type;
            subContent.JudgeSubject = judgesubjectFacade.LoadAll("ID", "SubjectID='" + subContent.ExamSubContent.Id + "'");
            result = subContent;
            return View("ViewJudge", result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(SubjectContent entity)
        {
            bool result = false; string msg = "操作失败"; 
            try
            {
                if (entity.ExamSubContent.Id == null)
                {
                    result = subjectFacadeEx.Save(entity);
                }
                else
                {
                    result = subjectFacadeEx.Update(entity);
                }
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);         
            }
            catch (Exception ex)
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id, string typeid)
        {
            bool result = false;
            string msg = "操作失败";
            result = subjectFacadeEx.Delete(id,typeid);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            string TypeID = "";
            string CourseTypeID = "";
            if (gridsearch != null)
            {
                string[] strAry = gridsearch.Split(',');
               TypeID = strAry[0];
               CourseTypeID = strAry[1];
            }
          
            return subjectFacade.FindByPage(page, pagesize, TypeID, CourseTypeID);
        }

    }
}
