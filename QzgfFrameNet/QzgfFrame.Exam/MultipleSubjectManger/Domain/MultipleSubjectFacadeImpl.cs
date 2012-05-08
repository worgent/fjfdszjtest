/*
 * 文件名.........: MultipleSubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 选择题业务逻辑类 
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
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.MultipleSubjectManger.Domain
{
    public class MultipleSubjectFacadeImpl : MultipleSubjectFacade
    {
        private IRepository<ExamMultipleSubject> multiplesubjectRepository { set; get; }

        public ExamMultipleSubject Get(object id)
        {
            return multiplesubjectRepository.Get(id.ToString());
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
                string hql = "SubjectID='" + s + "'";
                result = multiplesubjectRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamMultipleSubject entity)
        {
            entity.ID = multiplesubjectRepository.NewSequence("SYSTEM_MENU");
            return multiplesubjectRepository.Save(entity);
        }

        public bool Update(ExamMultipleSubject entity)
        {
            return multiplesubjectRepository.Update(entity);
        }

        public IList<ExamMultipleSubject> LoadAll()
        {
            return multiplesubjectRepository.LoadAll();
        }
        public IList<ExamMultipleSubject> LoadAll(string order, string where)
        {
            return multiplesubjectRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamMultipleSubject";
            IList<ExamMultipleSubject> ls = multiplesubjectRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = multiplesubjectRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
