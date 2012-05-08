/*
 * 文件名.........: CourseFacade.cs
 * 作者...........:  
 * 说明...........: 科目类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.CourseManger.Models;

namespace QzgfFrame.Exam.CourseManger.Domain

{
    public interface CourseFacade
    {

        ExamCourseType Get(object id);

        ExamCourseType Get(string order, string where);

        bool Delete(string id);

        bool Save(ExamCourseType entity);

        bool Update(ExamCourseType entity);

        IList<ExamCourseType> LoadAll();

        IList<ExamCourseType> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
