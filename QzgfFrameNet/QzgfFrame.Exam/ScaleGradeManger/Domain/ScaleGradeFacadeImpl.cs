/*
 * 文件名.........: ScaleGradeFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试题等级业务逻辑类 
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
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.ScaleGradeManger.Domain
{
    public class ScaleGradeFacadeImpl : ScaleGradeFacade
    {
        private IRepository<ExamScaleGrade> scalegradeRepository { set; get; }

        public ExamScaleGrade Get(object id)
        {
            return scalegradeRepository.Get(id.ToString());
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
                result = scalegradeRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamScaleGrade entity)
        {
            entity.ID = scalegradeRepository.NewSequence("SYSTEM_MENU");
            return scalegradeRepository.Save(entity);
        }

        public bool Update(ExamScaleGrade entity)
        {
            return scalegradeRepository.Update(entity);
        }

        public IList<ExamScaleGrade> LoadAll()
        {
            return scalegradeRepository.LoadAll();
        }
        public IList<ExamScaleGrade> LoadAll(string order, string where)
        {
            return scalegradeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamScaleGrade";
            IList<ExamScaleGrade> ls = scalegradeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = scalegradeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
