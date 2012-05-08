/*
 * 文件名.........: JudgeSubjectFacade.cs
 * 作者...........:  
 * 说明...........: 判断题业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.JudgeSubjectManger.Models;

namespace QzgfFrame.Exam.JudgeSubjectManger.Domain

{
    public interface JudgeSubjectFacade
    {

        ExamJudgeSubject Get(object id);

        bool Delete(string id);

        bool Save(ExamJudgeSubject entity);

        bool Update(ExamJudgeSubject entity);

        IList<ExamJudgeSubject> LoadAll();

        IList<ExamJudgeSubject> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
