/*
 * 文件名.........: TestSetFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试卷设置业务逻辑类 
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
using QzgfFrame.Exam.TestSetManger.Models;

using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Exam.AchievementManager.Models;

namespace QzgfFrame.Exam.TestSetManger.Domain
{
    public class TestSetFacadeImpl : TestSetFacade
    {
        private IRepository<ExamTestSet> testsetRepository { set; get; }
        private IRepository<ExamAchievement> achievementRepository { set; get; }
        private TestFacade testFacade { set; get; }


        public ExamTestSet Get(object id)
        {
            return testsetRepository.Get(id.ToString());
        }

        public TestSet GetTestSet(object id)
        {
            TestSet TestSet = new TestSet();
            TestSet.ExamTestSet = testsetRepository.Get(id.ToString());
            return TestSet;
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
                result = testsetRepository.Delete(s);
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
                result = testsetRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamTestSet entity)
        {
            entity.ID = testsetRepository.NewSequence("SYSTEM_MENU");
            return testsetRepository.Save(entity);
        }

        public bool Update(ExamTestSet entity)
        {
            return testsetRepository.Update(entity);
        }

        public IList<ExamTestSet> LoadAll()
        {
            return testsetRepository.LoadAll();
        }

        public IList<ExamTestSet> LoadAll(string order, string where)
        {
            return testsetRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string userID, string Role)
        {
            string hql = "";
            IList<ExamTestSet> etss = null;
            IList<object[]> ls=null;
            int ListCount=0;   //集合元素数
            int recordCount = 0;
            int beginTime = 0;
            int endTime = 0;
            string sql = "";
            string url = "";
            if (Role == "0")  //角色为出卷者
            {
                hql = "from ExamTestSet";
                etss = testsetRepository.FindByPage(pageNo, pageSize, hql);
                recordCount = testsetRepository.FindByPageCount(hql);
                ListCount=etss.Count;          
            }
            else if (Role == "1")   //角色为考生
            {
                hql = " from ExamTestSet t, ExamExamineeRange s where t.ID=s.TestSetID and s.ExamineeID='" + userID + "'";
                ls = testsetRepository.FindByLinkPage(pageNo, pageSize, hql);
                recordCount = testsetRepository.FindByPageLinkCount(hql);
                ListCount=ls.Count;
            }
              
            IList<TestSet> ts = new List<TestSet>();
           
            for (int i = 0; i < ListCount; i++)
            {
                ExamTestSet ets = null;

                if (Role == "0") ets = etss[i];                          //角色为出卷者
                else if (Role == "1") ets = (ExamTestSet)ls[i][0];       //角色为考生

                string[] testid = ets.TestID.Split(',');  //拆分TestID
                ExamTestContent ExamTestContent = testFacade.Get(testid[0]);
                TestSet TestSet = new TestSet();
                TestSet.ExamTestSet = ets;
                TestSet.TestID = ExamTestContent.ID;
                TestSet.TestName = ExamTestContent.Name;
                TestSet.BeginTime = ets.BeginTime.ToString();
                TestSet.EndTime = ets.EndTime.ToString();
                TestSet.CreateTime = ets.CreateTime;
                TestSet.TimeLength = ets.TimeLength;
                TestSet.ViewTestUrl = "<a href='/Exam/Test/ViewTest/?id=" + ExamTestContent.ID + "' target='_blank'>预览</a>";

                //当前时间与考试开始时间对比。
                beginTime = DateTime.Compare(DateTime.Now, Convert.ToDateTime(ets.BeginTime));
                //当前时间与考试结束时间对比。
                endTime = DateTime.Compare(DateTime.Now, Convert.ToDateTime(ets.EndTime));

                //判断考生是否已经考过
                sql = " UserId = '" + userID + "' and IsSubmitted = 1  and SetUpId = '" + ets.ID + "' ";
                IList<ExamAchievement> examAchievement = achievementRepository.LoadAll("Id", sql);
                if (examAchievement.Count > 0)
                {
                    url = "已经考过";
                }
                else
                {
                    if (beginTime < 0)
                    {
                        //当前时间小于"开始时间",考生不能进入考场
                        url = "<span style='color: #0000FF'>考试时间还没到</span>";
                    }
                    else if ((beginTime > 0) && (endTime < 0))
                    {
                        //当前时间在"开始时间"与"结束时间"之间,考生可以进入考场考试
                        url = "<a href='/Exam/TestSet/Set/" + ets.ID + "'>开始考试</a>";
                    }
                    else if (endTime > 0)
                    {
                        //当前时间大于"结束时间",考生不能进入考场
                        url = "考试时间已过";
                    }
                }
                TestSet.SetUrl = url;
                TestSet.TestState = ExamTestContent.State.ToString();
                ts.Add(TestSet);
            }
            string rowsjson = JsonConvert.SerializeObject(ts);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
