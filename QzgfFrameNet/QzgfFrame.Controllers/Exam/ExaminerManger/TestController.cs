/*
 * 文件名.........: TestController.cs
 * 作者...........:  
 * 说明...........: 试卷控制器类  
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
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.GetSubjectManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Domain;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Exam.ExaminerManger
{
    public class TestController: BaseController
    {
        private SubjectFacade subjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private FillBlanksSubjectFacade fillblankssubjectFacade { set; get; }
        private MultipleSubjectFacade multiplesubjectFacade { set; get; }
        private JudgeSubjectFacade judgesubjectFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private TestFacade testFacade { set; get; }
        private TestSetFacade testsetFacade { set; get; }
        private TestSubjectTypeFacade testsubjecttypeFacade { set; get; }
        private TestSubjectFacade testsubjectFacade { set; get; }
        private TestFacadeEx testFacadeEx { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
       
        //随机出卷
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult AddRandom(string id)
        {
            IList<ExamCourseType> courseType = courseFacade.LoadAll();    //试卷类型    
            ViewBag.selcourseType = new SelectList(courseType, "ID", "Name");

            IList<ExamSubjectType> subjectType = subjecttypeFacade.LoadAll();    //试题类型    
            ViewBag.selsubjectType = new SelectList(subjectType, "ID", "Type");


            GetSubject GetSubject = new GetSubject();
            IList<ExamGetSubject> ExamGetSubjects = new List<ExamGetSubject>();
            for (int i = 0; i < 3; i++)
            {
                ExamGetSubject ExamGetSubject = new ExamGetSubject();
                ExamGetSubject.OrderNo = (i + 1).ToString();
                ExamGetSubject.SubjectTypeName = "TypeName";
                ExamGetSubjects.Add(ExamGetSubject);
            }
            GetSubject.CreatorID = this.currentUser.UserInfo.Id;
            GetSubject.ExamGetSubject = ExamGetSubjects;

            return View("EditRandom", GetSubject);
        }

        //
        // GET: /User/Home/Edit/5
        //新增手动出卷 
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult AddHand(string id)
        {
            TestContent result = new TestContent();

            IList<ExamCourseType> courseType = courseFacade.LoadAll();
            ViewBag.selcourseType = new SelectList(courseType, "ID", "Name");

            int totalPoints = 100;       //试卷总分
            int currentPoints = 0;       //当前试卷所有题目总分

            ExamTestContent ExamTestContent = new ExamTestContent();
            ExamTestSubjectType ExamTestSubjectType = new ExamTestSubjectType();
            ExamTestSubject ExamTestSubject = new ExamTestSubject();

            ExamTestContent.ID = "0";
            ExamTestContent.Points = totalPoints.ToString();
            ExamTestContent.Name = "试卷名称";
            ExamTestContent.CreatorID = this.currentUser.UserInfo.Id; 
            result.CurrentPoints = currentPoints.ToString();
            result.ExamTestContent = ExamTestContent;

            return View("EditTest", result);
        }

        //
        // GET: /User/Home/Edit/5
        //编辑手动出卷 
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ModifyHand(string id)
        {
            TestContent result = new TestContent();

            IList<ExamCourseType> courseType = courseFacade.LoadAll();
            ViewBag.selcourseType = new SelectList(courseType, "ID", "Name");

            int currentPoints = 0;       //当前试卷所有题目总分
            int testsubjectPoints = 0;   //题型总分
        
            TestContent TestContent = testFacade.GetContent(id);
            IList<ExamTestSet> ExamTestSet = testsetFacade.LoadAll("ID", "TestID='" + id + "'");
            if (ExamTestSet.Count != 0) TestContent.ExamTestSet = ExamTestSet[0];

            TestContent.TestSubjectsType = testsubjecttypeFacade.GetTestSubjectTypes(id);
            if (TestContent.TestSubjectsType.Count != 0)
            {
                foreach (TestSubjectType tst in TestContent.TestSubjectsType)
                {
                    testsubjectPoints = 0;
                    tst.TestSubjects = testsubjectFacade.LoadAll("OrderNo", "TestSubjectTypeID='" + tst.ExamTestSubjectType.ID + "'");
                    if (tst.TestSubjects.Count != 0)
                    {
                        IList<SubjectContent> SubjectContents = new List<SubjectContent>();
                        foreach (ExamTestSubject ts in tst.TestSubjects)
                        {
                            testsubjectPoints += int.Parse(ts.Score);
                            currentPoints += int.Parse(ts.Score);

                            SubjectContent SubjectContent = new SubjectContent();
                            SubjectContent.ExamSubContent = subjectFacade.Get(ts.SubjectID);
                            ExamSubjectType ExamSubjectType = subjecttypeFacade.Get(SubjectContent.ExamSubContent.TypeId);
                            SubjectContent.SubjectType = ExamSubjectType.Type;
                            switch (ExamSubjectType.Type)
                            {
                                case "选择题":
                                    SubjectContent.MultipleSubject = multiplesubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                    break;
                                case "填空题":
                                    SubjectContent.FillBlanksSubject = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                    break;
                                case "判断题":
                                    SubjectContent.JudgeSubject = judgesubjectFacade.LoadAll("ID", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                    break;
                            }
                            SubjectContents.Add(SubjectContent);
                        }
                        tst.SubjectContents = SubjectContents;
                    }
                    tst.testsubjectPoints = testsubjectPoints.ToString();
                }
            }
            TestContent.CurrentPoints = currentPoints.ToString();
            result = TestContent;

            return View("EditTest", result);
        }

        //
        // GET: /User/Home/Edit/5
        //预览试卷
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ViewTest(string id)
        {             
                //浏览时返回具体值
                TestContent result = testFacade.GetContent(id);
                IList<ExamTestSet> ExamTestSet = testsetFacade.LoadAll("ID","TestID='"+id+"'");   
                if(ExamTestSet.Count!=0) result.ExamTestSet=ExamTestSet[0];

                ExamCourseType CourseType = courseFacade.Get(result.ExamTestContent.TypeID);
                result.TestType = CourseType.Name;

                result.TestSubjectsType = testsubjecttypeFacade.GetTestSubjectTypes(id);
                if (result.TestSubjectsType.Count != 0)
                {
                    foreach (TestSubjectType tst in result.TestSubjectsType)
                    {
                        tst.TestSubjects = testsubjectFacade.LoadAll("OrderNo", "TestSubjectTypeID='" + tst.ExamTestSubjectType.ID + "'");
                        if (tst.TestSubjects.Count != 0)
                        {
                            IList<SubjectContent> SubjectContents = new List<SubjectContent>();
                            foreach (ExamTestSubject ts in tst.TestSubjects)
                            {
                                SubjectContent SubjectContent = new SubjectContent();
                                SubjectContent.ExamSubContent = subjectFacade.Get(ts.SubjectID);
                                ExamSubjectType ExamSubjectType = subjecttypeFacade.Get(SubjectContent.ExamSubContent.TypeId);
                                SubjectContent.SubjectType = ExamSubjectType.Type;
                                switch (ExamSubjectType.Type)
                                {
                                    case "选择题":
                                        SubjectContent.MultipleSubject = multiplesubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                        break;
                                    case "填空题":
                                        SubjectContent.FillBlanksSubject = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                        break;
                                    case "判断题":
                                        SubjectContent.JudgeSubject = judgesubjectFacade.LoadAll("ID", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                        break;
                                }
                                SubjectContents.Add(SubjectContent);
                            }
                            tst.SubjectContents = SubjectContents;
                        }
                        
                    }
                }
                return View("ViewTest", result);
        }

        //
        // GET: /User/Home/Edit/5
        //提交随机出卷
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult EditRandom(GetSubject entity)
        {
            TestID testID = new TestID();
            try
            {
                if (entity != null)
                {
                    testID = testFacadeEx.GetTestContent(entity);
                }
                return Json(new { Type = testID.result, Message = testID.msg, ID = testID.ID }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { Type = testID.result, Message = testID.msg, ID = testID.ID }, JsonRequestBehavior.AllowGet);
            }
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(TestContent entity)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                if (entity.ExamTestContent.ID == "0")
                {
                    result = testFacadeEx.Save(entity);
                }
                else
                {
                    result = testFacadeEx.Update(entity);
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
            result = testFacadeEx.Delete(id);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }


        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            string TypeID = "";
            string TestID = "";
            if (gridsearch != null)
            {
                string[] strAry = gridsearch.Split(',');
                TypeID = strAry[0];
                TestID = strAry[1];
            }
           
            return testFacade.FindByPage(page, pagesize, TypeID, TestID);
        }
    }
}
