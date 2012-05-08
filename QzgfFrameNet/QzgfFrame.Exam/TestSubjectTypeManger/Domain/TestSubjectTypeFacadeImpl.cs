/*
 * 文件名.........: TestSubjectTypeFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试卷试题类型业务逻辑类 
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
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;

using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestSubjectTypeManger.Domain
{
    public class TestSubjectTypeFacadeImpl : TestSubjectTypeFacade
    {
        private IRepository<ExamTestSubjectType> testsubjecttypeRepository { set; get; }


        public ExamTestSubjectType Get(object id)
        {
            return testsubjecttypeRepository.Get(id.ToString());
        }

        public IList<TestSubjectType> GetTestSubjectTypes(object id)
        {
            IList<TestSubjectType> TestSubjectTypes = new List<TestSubjectType>();
            IList<ExamTestSubjectType> ExamTestSubjectTypes = testsubjecttypeRepository.LoadAll("ORDERNO", "TestID='" + id + "'");
            for (int i = 0; i < ExamTestSubjectTypes.Count; i++)
            {
                TestSubjectType TestSubjectType = new TestSubjectType();
                TestSubjectType.ExamTestSubjectType = ExamTestSubjectTypes[i];
                TestSubjectTypes.Add(TestSubjectType);
            }
            return TestSubjectTypes;
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
                string hql = "TestID='" + s + "'";
                result = testsubjecttypeRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamTestSubjectType entity)
        {
            entity.ID = testsubjecttypeRepository.NewSequence("SYSTEM_MENU");
            return testsubjecttypeRepository.Save(entity);
        }

        public bool Update(ExamTestSubjectType entity)
        {
            return testsubjecttypeRepository.Update(entity);
        }

        public IList<ExamTestSubjectType> LoadAll()
        {
            return testsubjecttypeRepository.LoadAll();
        }
        public IList<ExamTestSubjectType> LoadAll(string order, string where)
        {
            return testsubjecttypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamTestSubjectType";
            IList<ExamTestSubjectType> ls = testsubjecttypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = testsubjecttypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
