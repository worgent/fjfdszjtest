/*
 * 文件名.........: ExamTypeFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 考试类型业务逻辑类 
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
using QzgfFrame.Exam.ExamTypeManger.Models;
using QzgfFrame.Exam.ExamTypeManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.ExamTypeManger.Domain
{
    public class ExamTypeFacadeImpl : ExamTypeFacade
    {
        private IRepository<ExamType> examtypeRepository { set; get; }

        public ExamType Get(object id)
        {
            return examtypeRepository.Get(id.ToString());
        }
        public ExamType Get(string order, string where)
        {
            IList<ExamType> examTypes = examtypeRepository.LoadAll(order, where);
            if (examTypes.Count > 0)
                return examTypes[0];
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
                result = examtypeRepository.Delete(s);
            }
            return result;
        }

        public ExamType GetHql(string type)
        {
            string Hql = " Type like '%" + type + "%'";
            IList<ExamType> ExamTypes = examtypeRepository.LoadAll("ID", Hql);
            if (ExamTypes != null)
            {
                if (ExamTypes.Count > 0)
                    return ExamTypes[0];
                else return null;
            }
            return null;
        }

        public bool Save(ExamType entity)
        {
            entity.ID = examtypeRepository.NewSequence("SYSTEM_MENU");
            return examtypeRepository.Save(entity);
        }

        public bool Update(ExamType entity)
        {
            return examtypeRepository.Update(entity);
        }

        public IList<ExamType> LoadAll()
        {
            return examtypeRepository.LoadAll();
        }
        public IList<ExamType> LoadAll(string order, string where)
        {
            return examtypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamType";
            IList<ExamType> ls = examtypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = examtypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
