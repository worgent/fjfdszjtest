/*
 * 文件名.........: TestExerciseFacade.cs
 * 作者...........:  
 * 说明...........: 练习试卷设置业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.TestExerciseManger.Models;

namespace QzgfFrame.Exam.TestExerciseManger.Domain

{
    public interface TestExerciseFacade
    {

        ExamTestExercise Get(object id);

        TestExercise GetTestExercise(object id);

        bool Delete(string id);

        bool DeleteTestID(string id);

        bool Save(ExamTestExercise entity);

        bool Update(ExamTestExercise entity);

        IList<ExamTestExercise> LoadAll();

        IList<ExamTestExercise> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize, string userID, string Role);
    }
}
