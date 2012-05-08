/*
 * 文件名.........: TestMockFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 模拟试卷设置业务逻辑类 
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
using QzgfFrame.Exam.TestMockManger.Models;

using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestMockManger.Domain
{
    public class TestMockFacadeImpl : TestMockFacade
    {
        private IRepository<ExamTestMock> testmockRepository { set; get; }
        private TestFacade testFacade { set; get; }


        public ExamTestMock Get(object id)
        {
            return testmockRepository.Get(id.ToString());
        }

        public TestMock GetTestMock(object id)
        {
            TestMock TestMock = new TestMock();
            TestMock.ExamTestMock = testmockRepository.Get(id.ToString());
            return TestMock;
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
                result = testmockRepository.Delete(s);
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
                result = testmockRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamTestMock entity)
        {
            entity.ID = testmockRepository.NewSequence("SYSTEM_MENU");
            return testmockRepository.Save(entity);
        }

        public bool Update(ExamTestMock entity)
        {
            return testmockRepository.Update(entity);
        }

        public IList<ExamTestMock> LoadAll()
        {
            return testmockRepository.LoadAll();
        }
        public IList<ExamTestMock> LoadAll(string order, string where)
        {
            return testmockRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string userID, string Role)
        {
            string hql = "";
            IList<ExamTestMock> etms = null;
            IList<object[]> ls = null;
            int ListCount = 0;   //集合元素数
            int recordCount = 0;
            if (Role == "0")  //角色为出卷者
            {
                hql = "from ExamTestMock";
                etms = testmockRepository.FindByPage(pageNo, pageSize, hql);
                recordCount = testmockRepository.FindByPageCount(hql);
                ListCount = etms.Count;
            }
            else if (Role == "1")   //角色为考生
            {
                hql = " from ExamTestMock t, ExamExamineeRange s where t.ID=s.TestSetID and s.ExamineeID='" + userID + "'";
                ls = testmockRepository.FindByLinkPage(pageNo, pageSize, hql);
                recordCount = testmockRepository.FindByPageLinkCount(hql);
                ListCount = ls.Count;
            }

            IList<TestMock> tm = new List<TestMock>();

            for (int i = 0; i < ListCount; i++)
            {
                ExamTestMock etm = null;
                if (Role == "0") etm = etms[i];                          //角色为出卷者
                else if (Role == "1") etm = (ExamTestMock)ls[i][0];       //角色为考生
                ExamTestContent ExamTestContent = testFacade.Get(etm.TestID);
                TestMock TestMock = new TestMock();
                TestMock.ExamTestMock = etm;
                TestMock.TestID = ExamTestContent.ID;
                TestMock.TestName = ExamTestContent.Name;
                TestMock.CreateTime = etm.CreateTime;
                TestMock.TimeLength = etm.TimeLength;
                TestMock.ViewTestUrl = "<a href='/Exam/Test/ViewTest/?id=" + ExamTestContent.ID + "' target='_blank'>预览</a>";
                TestMock.MockUrl = "<a href='/Exam/TestMock/Mock/" + ExamTestContent.ID.ToString() + "'>开始考试</a>";
                tm.Add(TestMock);
            }
            string rowsjson = JsonConvert.SerializeObject(tm);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
