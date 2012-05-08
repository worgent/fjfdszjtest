/*
 * 文件名.........: TestSubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试卷试题业务逻辑类 
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
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;

using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestSubjectManger.Domain
{
    public class TestSubjectFacadeImpl : TestSubjectFacade
    {
        private IRepository<ExamTestSubject> testsubjectRepository { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private ScaleGradeFacade scalegradeFacade { set; get; }


        public ExamTestSubject Get(object id)
        {
            return testsubjectRepository.Get(id.ToString());
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
                string hql = "TestSubjectTypeID='" + s + "'";
                result = testsubjectRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamTestSubject entity)
        {
            entity.ID = testsubjectRepository.NewSequence("SYSTEM_MENU");
            return testsubjectRepository.Save(entity);
        }

        public bool Update(ExamTestSubject entity)
        {
            return testsubjectRepository.Update(entity);
        }

        public IList<ExamTestSubject> LoadAll()
        {
            return testsubjectRepository.LoadAll();
        }
        public IList<ExamTestSubject> LoadAll(string order, string where)
        {
            return testsubjectRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamTestSubject";
            IList<ExamTestSubject> ls = testsubjectRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = testsubjectRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
