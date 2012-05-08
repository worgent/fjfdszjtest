/*
 * 文件名.........: ScaleGradeFacade.cs
 * 作者...........:  
 * 说明...........: 试题等级业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.ScaleGradeManger.Models;

namespace QzgfFrame.Exam.ScaleGradeManger.Domain

{
    public interface ScaleGradeFacade
    {

        ExamScaleGrade Get(object id);

        bool Delete(string id);

        bool Save(ExamScaleGrade entity);

        bool Update(ExamScaleGrade entity);

        IList<ExamScaleGrade> LoadAll();

        IList<ExamScaleGrade> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
