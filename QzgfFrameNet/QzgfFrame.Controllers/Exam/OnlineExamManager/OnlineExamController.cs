using System.Collections.Generic;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using System.Collections;
using QzgfFrame.Exam.AchievementManager.Domain;
using QzgfFrame.Exam.OnlineExamManager.Domain;
using QzgfFrame.Exam.AchievementxManager.Domain;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.TestTypeManger.Domain;
using QzgfFrame.Exam.TestSubjectTypeManger.Domain;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestTypeManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.OnlineExamManager.Models;
using Newtonsoft.Json;
using QzgfFrame.Exam.AnswerExamManager.Models;
using QzgfFrame.Exam.AchievementManager.Models;
using System;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.AnswerExamManager.Domain;
using QzgfFrame.Exam.MultipleAnswerManager.Models;
using QzgfFrame.Exam.MultipleAnswerManager.Domain;
using QzgfFrame.Exam.FillBlanksAnswerManager.Models;
using QzgfFrame.Exam.JudgeAnswerManager.Models;
using QzgfFrame.Exam.JudgeAnswerManager.Domain;
using QzgfFrame.Exam.FillBlanksAnswerManager.Domain;

namespace QzgfFrame.Controllers.Exam.OnlineExamManager
{
    /// <summary>
    /// 在线考试
    /// </summary>
    public class OnlineExamController : BaseController
    {
        #region Private Members
        private AchievementFacade achievementFacade { set; get; }
        private AchievementxFacade achievementxFacade { set; get; }
        private OnlineExamFacade onlineExamFacade { set; get; }

        private TestFacade testFacade { set; get; }
        private TestSetFacade testsetFacade { set; get; }
        private TestTypeFacade testtypeFacade { set; get; }
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
        /// 显示"在线考试"列表页
        /// </summary>
        /// <returns></returns>
        public ActionResult ExamList()
        {
            return View();
        }

        /// <summary>
        /// 显示"在线考试"
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Exam(string id)
        {
            //浏览时返回具体值
            TestContent result = testFacade.GetContent(id);
            IList<ExamTestSet> ExamTestSet = testsetFacade.LoadAll("ID", "TestID='" + id + "'");
            if (ExamTestSet.Count != 0) result.ExamTestSet = ExamTestSet[0];
            //保存在数据库中的时间是以分钟为单位的，而页面是以秒钟为单位刷新的，所以必须乘于60
            ViewData["hbnTimeLength"] = Convert.ToInt32(ExamTestSet[0].TimeLength) * 60;

            ExamTestType TestType = testtypeFacade.Get(result.ExamTestContent.TypeID);
            result.TestType = TestType.Type;

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
            return View(result);
        }

        /// <summary>
        /// 显示"模拟考试"列表页
        /// </summary>
        /// <returns></returns>
        public ActionResult SimulationList()
        {
            return View();
        }

        /// <summary>
        /// 显示"模拟考试"
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Simulation(string id)
        {
            //浏览时返回具体值
            TestContent result = testFacade.GetContent(id);
            IList<ExamTestSet> ExamTestSet = testsetFacade.LoadAll("ID", "TestID='" + id + "'");

            if (ExamTestSet.Count != 0) result.ExamTestSet = ExamTestSet[0];
            //保存在数据库中的时间是以分钟为单位的，而页面是以秒钟为单位刷新的，所以必须乘于60
            ViewData["hbnTimeLength"] = Convert.ToInt32(ExamTestSet[0].TimeLength) * 60;

            ExamTestType TestType = testtypeFacade.Get(result.ExamTestContent.TypeID);
            result.TestType = TestType.Type;

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
            return View(result);
        }

        /// <summary>
        /// 显示"在线练习"列表页
        /// </summary>
        /// <returns></returns>
        public ActionResult ExercisesList()
        {
            return View();
        }

        /// <summary>
        /// 显示"在线练习"
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Exercises(string id)
        {
            //浏览时返回具体值
            TestContent result = testFacade.GetContent(id);
            IList<ExamTestSet> ExamTestSet = testsetFacade.LoadAll("ID", "TestID='" + id + "'");
            if (ExamTestSet.Count != 0) result.ExamTestSet = ExamTestSet[0];

            ExamTestType TestType = testtypeFacade.Get(result.ExamTestContent.TypeID);
            result.TestType = TestType.Type;

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
            return View(result);
        }

        /// <summary>
        /// 考试后，提交页面数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult SubmissionOfData(TestContent entity)
        {
            List<StatisticalResults[]> lst = null;
            string examAchievementId = "";
            string answerExamId = "";
            if (entity.exercisesData != null)
            {
                lst = (List<StatisticalResults[]>)JavaScriptConvert.DeserializeObject(entity.exercisesData, typeof(List<StatisticalResults[]>));
            }

            //考生做过的试卷表
            ExamAchievement examAchievement = new ExamAchievement();
            examAchievement.UserId = this.currentUser.UserInfo.Id;
            examAchievement.AnswerStartTime = DateTime.Now;
            examAchievement.AnswerEndTime = DateTime.Now;
            examAchievement.IsSubmitted = 1;
            examAchievement.MarkingState = 0;
            examAchievement.TestId = entity.ExamTestContent.ID;
            examAchievementId = achievementFacade.Saves(examAchievement);

            string hql = "";
            foreach (StatisticalResults[] statisticalResultsAry in lst)
            {
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
                        if (multiplesubjectFacade.LoadAll("Id", hql).Count > 0){
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else{
                            examAnswerExam.ProblemScore = "0";
                        }
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamMultipleAnswer examMultipleAnswer = new ExamMultipleAnswer();
                        examMultipleAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        examMultipleAnswer.AnswerExamId = answerExamId;
                        multipleAnswerFacade.Save(examMultipleAnswer);
                        break;
                    case "填空题":
                        hql = " SUBJECTID='" + examTestSubject.SubjectID + "' and ORDERNO='" + statisticalResultsAry[0].OrderNo
                            + "' and ANSWER='" + statisticalResultsAry[0].Answer + "' ";
                        if (fillblankssubjectFacade.LoadAll("Id", hql).Count > 0){
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else{
                            examAnswerExam.ProblemScore = "0";
                        }
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamFillBlanksAnswer examFillBlanksAnswer = new ExamFillBlanksAnswer();
                        examFillBlanksAnswer.AnswerExamId = answerExamId;
                        examFillBlanksAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        fillBlanksAnswerFacade.Save(examFillBlanksAnswer);
                        break;
                    case "判断题":
                        hql = " SUBJECTID='" + examTestSubject.SubjectID + "' and ANSWER='" + statisticalResultsAry[0].Answer + "' ";
                        if (judgesubjectFacade.LoadAll("Id", hql).Count > 0){
                            examAnswerExam.ProblemScore = examTestSubject.Score;
                        }
                        else{
                            examAnswerExam.ProblemScore = "0";
                        }
                        answerExamId = answerExamFacade.Saves(examAnswerExam);
                        ExamJudgeAnswer examJudgeAnswer = new ExamJudgeAnswer();
                        examJudgeAnswer.AnswerExamId = answerExamId;
                        examJudgeAnswer.AnswerContent = statisticalResultsAry[0].Answer;
                        judgeAnswerFacade.Save(examJudgeAnswer);
                        break;
                }
            }

            return View("ExercisesList");
        }

        /// <summary>
        /// 分页显示列表
        /// </summary>
        /// <param name="page"></param>
        /// <param name="pagesize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridviewname"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return onlineExamFacade.FindByPage(page, pagesize, gridsearch);
        }
    }
}
