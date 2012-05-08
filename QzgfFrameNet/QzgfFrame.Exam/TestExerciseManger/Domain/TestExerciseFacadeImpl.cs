/*
 * 文件名.........: TestExerciseFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 练习试卷设置业务逻辑类 
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
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestExerciseManger.Models;

using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestExerciseManger.Domain
{
    public class TestExerciseFacadeImpl : TestExerciseFacade
    {
        private IRepository<ExamTestExercise> testexerciseRepository { set; get; }
        private TestFacade testFacade { set; get; }


        public ExamTestExercise Get(object id)
        {
            return testexerciseRepository.Get(id.ToString());
        }

        public TestExercise GetTestExercise(object id)
        {
            TestExercise TestExercise = new TestExercise();
            TestExercise.ExamTestExercise = testexerciseRepository.Get(id.ToString());
            return TestExercise;
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
                result = testexerciseRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 根据考卷ID删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteTestID(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string hql = "TestID='" + s + "'";
                result = testexerciseRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamTestExercise entity)
        {
            entity.ID = testexerciseRepository.NewSequence("SYSTEM_MENU");
            return testexerciseRepository.Save(entity);
        }

        public bool Update(ExamTestExercise entity)
        {
            return testexerciseRepository.Update(entity);
        }

        public IList<ExamTestExercise> LoadAll()
        {
            return testexerciseRepository.LoadAll();
        }
        public IList<ExamTestExercise> LoadAll(string order, string where)
        {
            return testexerciseRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string userID, string Role)
        {
            string hql = "";
            IList<ExamTestExercise> etms = null;
            IList<object[]> ls = null;
            int ListCount = 0;   //集合元素数
            int recordCount = 0;
            if (Role == "0")  //角色为出卷者
            {
                hql = "from ExamTestExercise";
                etms = testexerciseRepository.FindByPage(pageNo, pageSize, hql);
                recordCount = testexerciseRepository.FindByPageCount(hql);
                ListCount = etms.Count;
            }
            else if (Role == "1")   //角色为考生
            {
                hql = " from ExamTestExercise t, ExamExamineeRange s where t.ID=s.TestSetID and s.ExamineeID='" + userID + "'";
                ls = testexerciseRepository.FindByLinkPage(pageNo, pageSize, hql);
                recordCount = testexerciseRepository.FindByPageLinkCount(hql);
                ListCount = ls.Count;
            }

            IList<TestExercise> te = new List<TestExercise>();

            for (int i = 0; i < ListCount; i++)
            {
                ExamTestExercise ete = null;
                if (Role == "0") ete = etms[i];                          //角色为出卷者
                else if (Role == "1") ete = (ExamTestExercise)ls[i][0];       //角色为考生
                ExamTestContent ExamTestContent = testFacade.Get(ete.TestID);
                TestExercise TestExercise = new TestExercise();
                TestExercise.ExamTestExercise = ete;
                TestExercise.TestID = ExamTestContent.ID;
                TestExercise.TestName = ExamTestContent.Name;
                TestExercise.CreateTime = ete.CreateTime;
                TestExercise.ViewTestUrl = "<a href='/Exam/Test/ViewTest/?id=" + ExamTestContent.ID + "' target='_blank'>预览</a>";
                TestExercise.ExerciseUrl = "<a href='/Exam/TestExercise/Exercises/" + ExamTestContent.ID.ToString() + "'>开始考试</a>";
                te.Add(TestExercise);
            }
            string rowsjson = JsonConvert.SerializeObject(te);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
