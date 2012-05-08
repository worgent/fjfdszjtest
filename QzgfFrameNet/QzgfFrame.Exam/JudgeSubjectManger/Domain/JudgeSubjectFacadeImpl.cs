/*
 * 文件名.........: JudgeSubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 判断题业务逻辑类 
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
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.JudgeSubjectManger.Domain
{
    public class JudgeSubjectFacadeImpl : JudgeSubjectFacade
    {
        private IRepository<ExamJudgeSubject>judgesubjectRepository { set; get; }

        public ExamJudgeSubject Get(object id)
        {
            return judgesubjectRepository.Get(id.ToString());
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
                result = judgesubjectRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamJudgeSubject entity)
        {
            entity.ID = judgesubjectRepository.NewSequence("SYSTEM_MENU");
            return judgesubjectRepository.Save(entity);
        }

        public bool Update(ExamJudgeSubject entity)
        {
            return judgesubjectRepository.Update(entity);
        }

        public IList<ExamJudgeSubject> LoadAll()
        {
            return judgesubjectRepository.LoadAll();
        }
        public IList<ExamJudgeSubject> LoadAll(string order, string where)
        {
            return judgesubjectRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamJudgeSubject";
            IList<ExamJudgeSubject> ls = judgesubjectRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = judgesubjectRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
