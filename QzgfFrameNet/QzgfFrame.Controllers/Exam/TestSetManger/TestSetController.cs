/*
 * 文件名.........: TestSetController.cs
 * 作者...........:  
 * 说明...........: 试卷设置控制器类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Collections.Generic;
using System.Web.Mvc;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.ExamineeRangeManger.Domain;
using QzgfFrame.Exam.ExamineeRangeManger.Models;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;
using QzgfFrame.Exam.AchievementManager.Domain;
using QzgfFrame.Exam.TestSubjectTypeManger.Domain;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Exam.AnswerExamManager.Domain;
using QzgfFrame.Exam.MultipleAnswerManager.Domain;
using QzgfFrame.Exam.JudgeAnswerManager.Domain;
using QzgfFrame.Exam.FillBlanksAnswerManager.Domain;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.OnlineExamManager.Models;
using Newtonsoft.Json;
using QzgfFrame.Exam.AchievementManager.Models;
using System;
using QzgfFrame.Exam.AnswerExamManager.Models;
using QzgfFrame.Exam.MultipleAnswerManager.Models;
using QzgfFrame.Exam.FillBlanksAnswerManager.Models;
using QzgfFrame.Exam.JudgeAnswerManager.Models;

namespace QzgfFrame.Controllers.Exam.TestSetManger
{
    public class TestSetController : BaseController
    {
        private TestFacade testFacade { set; get; }
        private TestSetFacade testsetFacade { set; get; }
        private ExamineeRangeFacade examineerangeFacade { set; get; }
        private TestSetFacadeEx testsetFacadeEx { set; get; }
        private CourseFacade courseFacade { set; get; }

        private AchievementFacade achievementFacade { set; get; }
        private TestSubjectTypeFacade testsubjecttypeFacade { set; get; }
        private TestSubjectFacade testsubjectFacade { set; get; }
        private SubjectFacade subjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private MultipleSubjectFacade multiplesubjectFacade { set; get; }
        private FillBlanksSubjectFacade fillblankssubjectFacade { set; get; }
        private JudgeSubjectFacade judgesubjectFacade { set; get; }
        private AnswerExamFacade answerExamFacade { set; get; }
        private MultipleAnswerFacade multipleAnswerFacade { set; get; }
        private JudgeAnswerFacade judgeAnswerFacade { set; get; }
        private FillBlanksAnswerFacade fillBlanksAnswerFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            ViewBag.UserID = this.currentUser.UserInfo.Id;
            ViewBag.Role = "0";  //出卷者
            return View();
        }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectExam()
        {
            if (this.currentExaminee.ExamineeInfo != null)
            {
                ViewBag.UserID = this.currentExaminee.ExamineeInfo.ExamExamineeInfo.ID;
                ViewBag.Role = "1";  //考生
            }
            return View("Index");
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string testid)
        {
            TestSet result = new TestSet();
            ExamTestSet ExamTestSet = new ExamTestSet();
            ExamTestSet.TestID = testid;
            ExamTestSet.CreatorID=this.currentUser.UserInfo.Id;
            ExamTestSet.ID = "0";
            result.ExamTestSet = ExamTestSet;
            ExamTestContent ExamTestContent = testFacade.Get(testid);
            ExamCourseType ExamCourseType = courseFacade.Get(ExamTestContent.TypeID);
            result.TestName = ExamTestContent.Name;
            ViewBag.testname = ExamTestContent.Name;
            ViewBag.typeid = ExamCourseType.Id;
            ViewBag.testtype = ExamCourseType.Name;
            ViewBag.points = ExamTestContent.Points;
            ViewBag.testid = testid;
            return View("Edit",result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            TestSet result=null;
            result = testsetFacade.GetTestSet(id);
            string[] testid = result.ExamTestSet.TestID.Split(',');  //拆分TestID
            result.ExamTestSet.TestID = testid[0];         //testid数组第一个字符串为主卷ID，不放入testids数组
            for (int i = 1; i < testid.Length; i++)        //考试设置表TestID第一个字符串为主卷ID，不加入可选试卷中
            {
                result.testids += testid[i];
                if (i < testid.Length - 1) result.testids += ",";
            }
            ExamTestContent ExamTestContent = testFacade.Get(testid[0]);  
            ExamCourseType ExamCourseType = courseFacade.Get(ExamTestContent.TypeID);
            result.TestName = ExamTestContent.Name;
            ViewBag.testname = ExamTestContent.Name;
            ViewBag.typeid = ExamCourseType.Id;
            ViewBag.testtype = ExamCourseType.Name;
            ViewBag.points = ExamTestContent.Points;
            ViewBag.testid = testid[0];

            IList<ExamExamineeRange> ExamineeRanges = examineerangeFacade.LoadAll("ID", "TestSetID='" + id + "'");
            string Examineeranges = "";
            for (int i = 0; i < ExamineeRanges.Count; i++)
            {
                Examineeranges += ExamineeRanges[i].ExamineeID;
                if (i < ExamineeRanges.Count - 1)
                {
                    Examineeranges += ",";
                }
            }
            result.examineeranges = Examineeranges;
            return View("Edit", result);
        }


        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(TestSet entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";

                if (entity.ExamTestSet.ID == null || entity.ExamTestSet.ID == "0")
                    result = this.testsetFacadeEx.Save(entity);
                else
                    result = this.testsetFacadeEx.Update(entity);
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
            result = testsetFacadeEx.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message=msg }, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 显示"在线考试"
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Set(string id)
        {
            string[] TestIDs = null;
            string testId = "";
            ExamTestSet examTestSet = testsetFacade.Get(id);
            if (DateTime.Now <= examTestSet.BeginTime)
            {
                //return Json(new { Type = false, Message = msg }, JsonRequestBehavior.AllowGet);
                return View("Index");
            }

            //考生进入考场后的剩余时间,是计算出秒数
            TimeSpan ts1 = new TimeSpan(DateTime.Now.Ticks);
            TimeSpan ts2 = new TimeSpan(Convert.ToDateTime(examTestSet.EndTime).Ticks);
            TimeSpan tsTime = ts2.Subtract(ts1).Duration();
            int timeLength = 0;
            timeLength = tsTime.Days * 24 * 60 * 60 + tsTime.Hours * 60 * 60 + tsTime.Minutes * 60;

            //考试开始时间
            ViewData["setBeginTime"] = examTestSet.BeginTime.ToString();
            //考试结束时间
            ViewData["setEndTime"] = examTestSet.EndTime.ToString();
            //考试剩余时间
            ViewData["hbnTimeLength"] = timeLength;

            //浏览时返回具体值
            TestContent result = null;
            TestIDs = examTestSet.TestID.Split(',');
            if (TestIDs.Length > 1)
            {
                Random random = new Random();
                int num = random.Next(0, TestIDs.Length);
                testId = TestIDs[num];
            }
            else
            {
                testId = examTestSet.TestID;
            }
            result = testFacade.GetContent(testId);
            //试卷设置表id
            result.ExamTestSet = examTestSet;

            ExamCourseType examCourseType = courseFacade.Get(result.ExamTestContent.TypeID);
            result.TestType = examCourseType.Name;


            result.TestSubjectsType = testsubjecttypeFacade.GetTestSubjectTypes(testId);
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
            return View(result);
        }

        /// <summary>
        /// 考试后，提交页面数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Set(TestContent entity)
        {
            bool bools = false;
            string msg = "";
            List<StatisticalResults[]> lst = new List<StatisticalResults[]>();
            string examAchievementId = "";
            string answerExamId = "";
            if (entity.exercisesData != null)
            {
                lst = (List<StatisticalResults[]>)JavaScriptConvert.DeserializeObject(entity.exercisesData, typeof(List<StatisticalResults[]>));
            }

            //考生做过的试卷表
            ExamAchievement examAchievement = new ExamAchievement();
            examAchievement.UserId = this.currentExaminee.ExamineeInfo.ExamExamineeInfo.ID;
            examAchievement.AnswerStartTime = DateTime.Now;
            examAchievement.AnswerEndTime = DateTime.Now;
            examAchievement.IsSubmitted = 1;
            examAchievement.MarkingState = 0;
            examAchievement.TestId = entity.ExamTestContent.ID;
            examAchievement.SetUpId = entity.ExamTestSet.ID;
            examAchievement.ExamType = "0";
            examAchievementId = achievementFacade.Saves(examAchievement);

            string hql = "";
            foreach (StatisticalResults[] statisticalResultsAry in lst)
            {
                bools = false;
                msg = "提交失败！";
                //考生考试回答表
                ExamAnswerExam examAnswerExam = new ExamAnswerExam();
                examAnswerExam.AchievementId = examAchievementId;
                examAnswerExam.TestSubjectId = statisticalResultsAry[0].TestSubjectId;

                //判断该题是否正确，并计算出得分
                ExamTestSubject examTestSubject = testsubjectFacade.Get(statisticalResultsAry[0].TestSubjectId);
                //判断试题类型
                ExamSubjectType examSubjectType = subjecttypeFacade.Get(statisticalResultsAry[0].SubjectTypeID);
                switch (examSubjectType.Type)
                {
                    case "选择题":
                        hql = " SUBJECTID='" + examTestSubject.SubjectID + "' and ORDERNO='" + statisticalResultsAry[0].OrderNo + "' and ANSWER='1' ";
                        if (multiplesubjectFacade.LoadAll("Id", hql).Count > 0)
                        {
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else
                        {
                            examAnswerExam.ProblemScore = "0";
                        }
                        examAnswerExam.SubjectAnswer = statisticalResultsAry[0].Answer;
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamMultipleAnswer examMultipleAnswer = new ExamMultipleAnswer();
                        examMultipleAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        examMultipleAnswer.AnswerExamId = answerExamId;
                        bools = multipleAnswerFacade.Save(examMultipleAnswer);
                        msg = "提交成功！";
                        break;
                    case "填空题":
                        hql = " SUBJECTID='" + examTestSubject.SubjectID + "' and ORDERNO='" + statisticalResultsAry[0].OrderNo
                            + "' and ANSWER='" + statisticalResultsAry[0].Answer + "' ";
                        if (fillblankssubjectFacade.LoadAll("Id", hql).Count > 0)
                        {
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else
                        {
                            examAnswerExam.ProblemScore = "0";
                        }
                        examAnswerExam.SubjectAnswer = statisticalResultsAry[0].Answer;
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamFillBlanksAnswer examFillBlanksAnswer = new ExamFillBlanksAnswer();
                        examFillBlanksAnswer.AnswerExamId = answerExamId;
                        examFillBlanksAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        bools = fillBlanksAnswerFacade.Save(examFillBlanksAnswer);
                        msg = "提交成功！";
                        break;
                    case "判断题":
                        hql = " SUBJECTID='" + examTestSubject.SubjectID + "' and ANSWER='" + statisticalResultsAry[0].Answer + "' ";
                        if (judgesubjectFacade.LoadAll("Id", hql).Count > 0)
                        {
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else
                        {
                            examAnswerExam.ProblemScore = "0";
                        }
                        examAnswerExam.SubjectAnswer = statisticalResultsAry[0].Answer;
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamJudgeAnswer examJudgeAnswer = new ExamJudgeAnswer();
                        examJudgeAnswer.AnswerExamId = answerExamId;
                        examJudgeAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        bools = judgeAnswerFacade.Save(examJudgeAnswer);
                        msg = "提交成功！";
                        break;
                }
            }

            //提交试卷后，将该"在线考试"试卷的“IsDelete”修改为“false”，为不可删除状态
            ExamTestSet examTestSet = new ExamTestSet();
            examTestSet = testsetFacade.Get(entity.ExamTestSet.ID);
            if (examTestSet != null)
            {
                examTestSet.IsDelete = false;
                testsetFacade.Update(examTestSet);
            }

            return Json(new { Type = bools, Message = msg }, "text/x-json");
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            string[] strAry = gridsearch.Split(',');
            string userID = strAry[0];   //用户ID
            string Role = strAry[1];     //用户角色    0表示出卷人  1表示考生

            return testsetFacade.FindByPage(page, pagesize, userID, Role);
        }
    }
}
