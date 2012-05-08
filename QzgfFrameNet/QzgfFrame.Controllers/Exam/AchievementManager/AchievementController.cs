using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using QzgfFrame.Exam.AchievementManager.Domain;
using QzgfFrame.Exam.AchievementManager.Models;
using System.Collections;
using QzgfFrame.Exam.TestSubjectTypeManger.Domain;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Exam.OnlineExamManager.Models;
using Newtonsoft.Json;
using QzgfFrame.Exam.AnswerExamManager.Models;
using QzgfFrame.Exam.MultipleAnswerManager.Models;
using QzgfFrame.Exam.FillBlanksAnswerManager.Models;
using QzgfFrame.Exam.JudgeAnswerManager.Models;
using QzgfFrame.Exam.AnswerExamManager.Domain;
using QzgfFrame.Exam.MultipleAnswerManager.Domain;
using QzgfFrame.Exam.JudgeAnswerManager.Domain;
using QzgfFrame.Exam.FillBlanksAnswerManager.Domain;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.CourseManger.Models;
using System.Data;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;

namespace QzgfFrame.Controllers.Exam.AchievementManager
{
    /// <summary>
    /// 成绩管理
    /// </summary>
    public class AchievementController : BaseController
    {
        #region

        private AchievementFacade achievementFacade { set; get; }
        private TestFacade testFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private TestSetFacade testsetFacade { set; get; }
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
        /// 显示“在线考试”页面
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            ViewData["ExamType"] = "0";
            return View();
        }

        /// <summary>
        /// 显示“模拟考试”页面
        /// </summary>
        /// <returns></returns>
        public ActionResult MockIndex()
        {
            ViewData["ExamType"] = "1";
            return View("Index");
        }

        /// <summary>
        /// 显示“在线练习”页面
        /// </summary>
        /// <returns></returns>
        public ActionResult ExerciseIndex()
        {
            ViewData["ExamType"] = "2";
            return View("Index");
        }

        /// <summary>
        /// 显示编辑页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            ExamAchievement result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = achievementFacade.Get(id);
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
        public ActionResult Edit(string id, ExamAchievement entity)
        {
            try
            {
                if (id == "0")
                    this.achievementFacade.Save(entity);
                else
                    this.achievementFacade.Update(entity);
                return View();
            }
            catch
            {
                return View();
            }
        }

        public ActionResult GetAll()
        {
            IList<ExamAchievement> citys = achievementFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (ExamAchievement item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 批量删除
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = achievementFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult ReturnIndex()
        {
            string Url = "ExerciseIndex";
            return RedirectToAction(Url);
        }

        /// <summary>
        /// 显示“试卷答案”页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExamRecords(string id)
        {
            string sql = "";
            ExamAchievement examAchievement = achievementFacade.Get(id);

            //浏览时返回具体值
            TestContent result = testFacade.GetContent(examAchievement.TestId);

            ExamCourseType examCourseType = courseFacade.Get(result.ExamTestContent.TypeID);
            result.TestType = examCourseType.Name;

            result.TestSubjectsType = testsubjecttypeFacade.GetTestSubjectTypes(examAchievement.TestId);
            if (result.TestSubjectsType.Count != 0)
            {
                foreach (TestSubjectType tst in result.TestSubjectsType)
                {
                    tst.TestSubjects = testsubjectFacade.LoadAll("OrderNo", "TestSubjectTypeID='" + tst.ExamTestSubjectType.ID + "'");
                    if (tst.TestSubjects.Count != 0)
                    {
                        IList<SubjectContent> SubjectContents = new List<SubjectContent>();
                        //遍历试卷试题
                        foreach (ExamTestSubject ts in tst.TestSubjects)
                        {
                            sql = " AchievementId ='" + id + "' and TestSubjectId = '" + ts.ID + "' ";
                            IList<ExamAnswerExam> examAnswerExam = new List<ExamAnswerExam>();
                            examAnswerExam = answerExamFacade.LoadAll("Id" , sql);

                            SubjectContent SubjectContent = new SubjectContent();
                            SubjectContent.ExamSubContent = subjectFacade.Get(ts.SubjectID);
                            ExamSubjectType ExamSubjectType = subjecttypeFacade.Get(SubjectContent.ExamSubContent.TypeId);
                            SubjectContent.SubjectType = ExamSubjectType.Type;
                            switch (ExamSubjectType.Type)
                            {
                                case "选择题":
                                    SubjectContent.MultipleSubject = multiplesubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");

                                    //将查询出来的试题消息，保存到带有考生答案与得分的持久类MultipleSubject中
                                    SubjectContent.MultipleSubjectAnswer = new List<MultipleSubject>();
                                    for (int i = 0; i < SubjectContent.MultipleSubject.Count; i++)
                                    {
                                        MultipleSubject multipleSubject = new MultipleSubject();
                                        multipleSubject.Id = SubjectContent.MultipleSubject[i].ID;
                                        multipleSubject.MultipleContent = SubjectContent.MultipleSubject[i].MultipleContent;
                                        multipleSubject.SubjectID = SubjectContent.MultipleSubject[i].SubjectID;
                                        multipleSubject.OrderNo = SubjectContent.MultipleSubject[i].OrderNo;
                                        multipleSubject.Answer = SubjectContent.MultipleSubject[i].Answer;
                                        multipleSubject.CandidatesAnswer = examAnswerExam[0].SubjectAnswer;
                                        multipleSubject.ProblemScore = examAnswerExam[0].ProblemScore;

                                        SubjectContent.MultipleSubjectAnswer.Add(multipleSubject);
                                    }
                                    break;
                                case "填空题":
                                    SubjectContent.FillBlanksSubject = fillblankssubjectFacade.LoadAll("OrderNo", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                    
                                    //将查询出来的试题消息，保存到带有考生答案与得分的持久类MultipleSubject中
                                    SubjectContent.FillBlanksSubjectAnswer = new List<FillBlanksSubject>();
                                    for (int i = 0; i < SubjectContent.FillBlanksSubject.Count; i++)
                                    {
                                        FillBlanksSubject fillBlanksSubject = new FillBlanksSubject();
                                        fillBlanksSubject.Id = SubjectContent.FillBlanksSubject[i].ID;
                                        fillBlanksSubject.SubjectID = SubjectContent.FillBlanksSubject[i].SubjectID;
                                        fillBlanksSubject.OrderNo = SubjectContent.FillBlanksSubject[i].OrderNo;
                                        fillBlanksSubject.Answer = SubjectContent.FillBlanksSubject[i].Answer;
                                        fillBlanksSubject.CandidatesAnswer = examAnswerExam[0].SubjectAnswer;
                                        fillBlanksSubject.ProblemScore = examAnswerExam[0].ProblemScore;

                                        SubjectContent.FillBlanksSubjectAnswer.Add(fillBlanksSubject);
                                    }
                                    break;
                                case "判断题":
                                    SubjectContent.JudgeSubject = judgesubjectFacade.LoadAll("ID", "SubjectID='" + SubjectContent.ExamSubContent.Id + "'");
                                    
                                    //将查询出来的试题消息，保存到带有考生答案与得分的持久类MultipleSubject中
                                    SubjectContent.JudgeSubjectAnswer = new List<JudgeSubject>();
                                    for (int i = 0; i < SubjectContent.JudgeSubject.Count; i++)
                                    {
                                        JudgeSubject judgeSubject = new JudgeSubject();
                                        judgeSubject.Id = SubjectContent.JudgeSubject[i].ID;
                                        judgeSubject.SubjectID = SubjectContent.JudgeSubject[i].SubjectID;
                                        judgeSubject.Answer = SubjectContent.JudgeSubject[i].Answer == true ? "正确":"错误";
                                        judgeSubject.CandidatesAnswer = examAnswerExam[0].SubjectAnswer == "1" ? "正确" : "错误";
                                        judgeSubject.ProblemScore = examAnswerExam[0].ProblemScore;

                                        SubjectContent.JudgeSubjectAnswer.Add(judgeSubject);
                                    }
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
        /// 显示“考试排名”页面
        /// </summary>
        /// <param name="id">考生做过的试卷表id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Ranking(string id)
        {

            return View();
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, 
            string gridsearch)
        {
            string userId = "";
            if (this.currentExaminee.ExamineeInfo != null)
            {
                userId = this.currentExaminee.ExamineeInfo.ExamExamineeInfo.ID;
            }
            return achievementFacade.FindByPage(page, pagesize, gridsearch, userId);
        }

    }
}
