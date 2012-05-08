/****************************************************************** 
 * 文件名.........: SubjectTypeFacadeImpl.cs 
 * 作者...........: 
 * 说明...........: 试题类型业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-12-09 XXXX 
 * ******************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectTypeManger.Models;

namespace QzgfFrame.Exam.SubjectTypeManger.Domain

{
    public interface SubjectTypeFacade
    {
        #region 基本操作

        ExamSubjectType Get(object id);

        bool Delete(string id);

        bool Save(ExamSubjectType entity);

        bool Update(ExamSubjectType entity);

        //分页
        string FindByPage(int pageNo, int pageSize);

        #endregion

        #region 加强

        ExamSubjectType Get(string order, string where);
        
        #endregion


        #region 保留

        IList<ExamSubjectType> LoadAll();

        IList<ExamSubjectType> LoadAll(string order, string where);

        #endregion
    }
}
