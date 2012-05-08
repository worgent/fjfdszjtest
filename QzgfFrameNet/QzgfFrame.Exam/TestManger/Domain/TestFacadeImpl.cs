/*
 * 文件名.........: TestFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试卷业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.SubjectManger.Domain;


using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestManger.Domain
{
    public class TestFacadeImpl: TestFacade
    {
        private IRepository<ExamTestContent> testRepository { set; get; }
        private CourseFacade courseFacade { set; get; }


        public ExamTestContent Get(object id)
        {
            return testRepository.Get(id.ToString());
        }

        public TestContent GetContent(object id)
        {
            TestContent TestContent = new TestContent();
            TestContent.ExamTestContent = testRepository.Get(id.ToString());
            return TestContent;
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = testRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamTestContent entity)
        {
            entity.ID = testRepository.NewSequence("SYSTEM_MENU");
            return testRepository.Save(entity);
        }

        public bool Update(ExamTestContent entity)
        {
            return testRepository.Update(entity);
        }

        public IList<ExamTestContent> LoadAll()
        {
            return testRepository.LoadAll();
        }
        public IList<ExamTestContent> LoadAll(string order, string where)
        {
            return testRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string typeid, string testid)
        {
            string hql = "";
            if (typeid == "" || testid == "")
                hql = "from ExamTestContent";
            else hql = "from ExamTestContent where TypeID='" + typeid + "' And ID<>'" + testid + "'";

            IList<ExamTestContent> esc = testRepository.FindByPage(pageNo, pageSize, hql);

            IList<TestContent> sc = new List<TestContent>();
            for (int i = 0; i < esc.Count; i++)
            {
                if (typeid != "" || testid != "")
                {
                    //考卷的状态如果是已组织练习考或已组织模拟考，就不放在可选考卷里面
                    if (esc[i].State == TestState.已组织练习考 || esc[i].State == TestState.已组织模拟考)
                    {
                        continue;
                    }
                }
                TestContent TestContent = new TestContent();
                ExamCourseType CourseType = courseFacade.Get(esc[i].TypeID); 
      
                TestContent.TestID = esc[i].ID;
                TestContent.TestName = esc[i].Name;
                TestContent.Points = esc[i].Points;
                TestContent.CreateTime = esc[i].CreateTime;
                TestContent.TestType = CourseType.Name;
                TestContent.TestState = esc[i].State.ToString();
                TestContent.ExamTestContent = esc[i];
                TestContent.ViewTestUrl = "<a href='/Exam/Test/ViewTest/?id=" + esc[i].ID + "' target='_blank'>预览</a>";
                sc.Add(TestContent);
            }
            string rowsjson = JsonConvert.SerializeObject(sc);
            int recordCount = testRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

    }
}
