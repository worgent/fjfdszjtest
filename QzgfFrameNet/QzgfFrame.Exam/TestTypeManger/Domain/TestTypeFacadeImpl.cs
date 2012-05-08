/*
 * 文件名.........: TestTypeFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试卷类型业务逻辑类 
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
using QzgfFrame.Exam.TestTypeManger.Models;
using QzgfFrame.Exam.TestTypeManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.TestTypeManger.Domain
{
    public class TestTypeFacadeImpl : TestTypeFacade
    {
        private IRepository<ExamTestType> testtypeRepository { set; get; }

        public ExamTestType Get(object id)
        {
            return testtypeRepository.Get(id.ToString());
        }
        public ExamTestType Get(string order, string where)
        {
            IList<ExamTestType> testTypes = testtypeRepository.LoadAll(order, where);
            if (testTypes.Count > 0)
                return testTypes[0];
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
                result = testtypeRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamTestType entity)
        {
            entity.ID = testtypeRepository.NewSequence("SYSTEM_MENU");
            return testtypeRepository.Save(entity);
        }

        public bool Update(ExamTestType entity)
        {
            return testtypeRepository.Update(entity);
        }

        public IList<ExamTestType> LoadAll()
        {
            return testtypeRepository.LoadAll();
        }
        public IList<ExamTestType> LoadAll(string order, string where)
        {
            return testtypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamTestType";
            IList<ExamTestType> ls = testtypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = testtypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
