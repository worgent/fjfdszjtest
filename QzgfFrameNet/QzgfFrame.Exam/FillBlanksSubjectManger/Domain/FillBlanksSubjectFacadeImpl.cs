/*
 * 文件名.........: FillBlanksSubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 填空题业务逻辑类 
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
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.FillBlanksSubjectManger.Domain
{
    public class FillBlanksSubjectFacadeImpl : FillBlanksSubjectFacade
    {
        private IRepository<ExamFillBlanksSubject> fillblankssubjectRepository { set; get; }

        public ExamFillBlanksSubject Get(object id)
        {
            return fillblankssubjectRepository.Get(id.ToString());
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
                result = fillblankssubjectRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamFillBlanksSubject entity)
        {
            entity.ID = fillblankssubjectRepository.NewSequence("SYSTEM_MENU");
            return fillblankssubjectRepository.Save(entity);
        }
        public bool Update(ExamFillBlanksSubject entity)
        {
            return fillblankssubjectRepository.Update(entity);
        }

        public IList<ExamFillBlanksSubject> LoadAll()
        {
            return fillblankssubjectRepository.LoadAll();
        }
        public IList<ExamFillBlanksSubject> LoadAll(string order, string where)
        {
            return fillblankssubjectRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamFillBlanksSubject";
            IList<ExamFillBlanksSubject> ls = fillblankssubjectRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = fillblankssubjectRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
