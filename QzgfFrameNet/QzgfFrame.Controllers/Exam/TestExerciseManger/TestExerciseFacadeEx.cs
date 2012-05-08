/*
 * 文件名.........: TestExerciseFacadeEx.cs
 * 作者...........:  
 * 说明...........: 练习试卷设置业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Exam.TestExerciseManger.Models;

namespace QzgfFrame.Controllers.Exam.TestExerciseManger

{
    public interface TestExerciseFacadeEx
    {
        bool Save(TestExercise entity);
        bool Update(TestExercise entity);
        bool Delete(string id);
    }
}
