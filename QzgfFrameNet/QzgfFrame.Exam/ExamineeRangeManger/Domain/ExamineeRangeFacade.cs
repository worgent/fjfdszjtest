/*
 * 文件名.........: ExamineeRangeFacade.cs
 * 作者...........:  
 * 说明...........: 考生范围业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.ExamineeRangeManger.Models;

namespace QzgfFrame.Exam.ExamineeRangeManger.Domain

{
    public interface ExamineeRangeFacade
    {

        ExamExamineeRange Get(object id);

        string Get(string order, string where);

        bool Delete(string id);

        bool Save(ExamExamineeRange entity);

        bool Update(ExamExamineeRange entity);

        IList<ExamExamineeRange> LoadAll();

        IList<ExamExamineeRange> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
