/*
 * 文件名.........: SubjectFacadeEx.cs
 * 作者...........:  
 * 说明...........: 试题业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System.Web;
using System.Data;
using System.Collections.Generic;
using QzgfFrame.Exam.SubjectManger.Models;

namespace QzgfFrame.Controllers.Exam.ExaminerManger

{
    public interface SubjectFacadeEx
    {
         bool Save(SubjectContent entity);
         bool Update(SubjectContent entity);
         bool Delete(string id, string typeid);
         /// <summary>
         /// 序号转字母
         /// </summary>
         /// <param name="number"></param>
         /// <returns></returns>
         string numberConvert(string number);
    }
}
