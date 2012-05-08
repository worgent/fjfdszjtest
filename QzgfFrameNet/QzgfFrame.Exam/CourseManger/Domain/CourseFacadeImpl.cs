/*
 * 文件名.........: CourseFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 科目类型业务逻辑类 
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
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.CourseManger.Domain
{
    public class CourseFacadeImpl : CourseFacade
    {
        private IRepository<ExamCourseType> coursetypeRepository { set; get; }

        public ExamCourseType Get(object id)
        {
            return coursetypeRepository.Get(id.ToString());
        }
        public ExamCourseType Get(string order, string where)
        {
            IList<ExamCourseType> courseTypes = coursetypeRepository.LoadAll(order, where);
            if (courseTypes.Count > 0)
                return courseTypes[0];
            else
                return null;
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
                result = coursetypeRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamCourseType entity)
        {
            entity.Id = coursetypeRepository.NewSequence("SYSTEM_MENU");
            return coursetypeRepository.Save(entity);
        }

        public bool Update(ExamCourseType entity)
        {
            return coursetypeRepository.Update(entity);
        }

        public IList<ExamCourseType> LoadAll()
        {
            return coursetypeRepository.LoadAll();
        }
        public IList<ExamCourseType> LoadAll(string order, string where)
        {
            return coursetypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamCourseType";
            IList<ExamCourseType> ls = coursetypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = coursetypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
