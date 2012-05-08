/*
 * 文件名.........: ExamineeRangeFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 考生范围业务逻辑类 
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
using QzgfFrame.Exam.ExamineeRangeManger.Models;
using QzgfFrame.Exam.ExamineeRangeManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.ExamineeRangeManger.Domain
{
    public class ExamineeRangeFacadeImpl : ExamineeRangeFacade
    {
        private IRepository<ExamExamineeRange> examineerangeRepository { set; get; }

        public ExamExamineeRange Get(object id)
        {
            return examineerangeRepository.Get(id.ToString());
        }
        public string Get(string order, string where)
        {
            string str="";
            IList<ExamExamineeRange> ExamineeRange = examineerangeRepository.LoadAll(order, where);

            for(int i=0;i<ExamineeRange.Count;i++)
            {
                str += ExamineeRange[i].ExamineeID.ToString();
                if (i < ExamineeRange.Count - 1)
                {
                    str += ",";
                }
            }
            return str;
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
                string hql = "TestSetID='" + s + "'";
                result = examineerangeRepository.DeleteHql(hql);
            }
            return result;
        }

        public bool Save(ExamExamineeRange entity)
        {
            entity.ID = examineerangeRepository.NewSequence("SYSTEM_MENU");
            return examineerangeRepository.Save(entity);
        }

        public bool Update(ExamExamineeRange entity)
        {
            return examineerangeRepository.Update(entity);
        }

        public IList<ExamExamineeRange> LoadAll()
        {
            return examineerangeRepository.LoadAll();
        }
        public IList<ExamExamineeRange> LoadAll(string order, string where)
        {
            return examineerangeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamExamineeRange";
            IList<ExamExamineeRange> ls = examineerangeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = examineerangeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
