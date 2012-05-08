/*
 * 文件名.........: SubjectFacade.cs
 * 作者...........:  
 * 说明...........: 试题内容业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectManger.Models;

namespace QzgfFrame.Exam.SubjectManger.Domain

{
    public interface SubjectFacade
    {

        ExamSubjectContent Get(object id);

        SubjectContent GetContent(object id);

        bool Delete(string id);

        bool Save(ExamSubjectContent entity);

        bool Update(ExamSubjectContent entity);

        IList<ExamSubjectContent> LoadAll();

        IList<ExamSubjectContent> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string TypeID, string CourseTypeID);
    }
}
