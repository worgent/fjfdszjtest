/****************************************************************** 
 * 文件名.........: SubjectTypeFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 试题类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-12-09 XXXX 
 * ******************************************************************/
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.SubjectTypeManger.Domain
{
    public class SubjectTypeFacadeImpl : SubjectTypeFacade
    {
        #region 变量定义

        private IRepository<ExamSubjectType> subjecttypeRepository { set; get; }
       
        #endregion

        #region 基本操作

        public ExamSubjectType Get(object id)
        {
            return subjecttypeRepository.Get(id.ToString());
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
                result = subjecttypeRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamSubjectType entity)
        {
            entity.ID = subjecttypeRepository.NewSequence("SYSTEM_MENU");
            return subjecttypeRepository.Save(entity);
        }

        public bool Update(ExamSubjectType entity)
        {
            return subjecttypeRepository.Update(entity);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamSubjectType";
            IList<ExamSubjectType> ls = subjecttypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = subjecttypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        public ExamSubjectType Get(string order, string where)
        {
            IList<ExamSubjectType> subjectTypes = subjecttypeRepository.LoadAll(order, where);
            if (subjectTypes.Count > 0)
                return subjectTypes[0];
            else
                return null;
        }

        #endregion

        #region 保留

        public IList<ExamSubjectType> LoadAll()
        {
            return subjecttypeRepository.LoadAll();
        }
        public IList<ExamSubjectType> LoadAll(string order, string where)
        {
            return subjecttypeRepository.LoadAll(order, where);
        }
        
        #endregion
       

      
    }
}
