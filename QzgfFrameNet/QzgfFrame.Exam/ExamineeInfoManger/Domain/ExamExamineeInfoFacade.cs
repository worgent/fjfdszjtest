/*
 * 文件名.........: ExamineeInfoFacade.cs
 * 作者...........:  
 * 说明...........: 考生信息业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Utility.Core.Common;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.ExamineeInfoManger.Domain

{
    public interface ExamineeInfoFacade
    {

        ExamExamineeInfo Get(object id);

        string Get(string order, string where);

        ExamineeInfo GetExamineeInfo(object id);

        bool Delete(string id);

        bool Save(ExamExamineeInfo entity);

        bool Update(ExamExamineeInfo entity);

        string CheckLogin(string loginname, string password, out ExamExamineeInfo viewexaminee);
        string ModifyPwd(string examineeid, string oldpwd, string newpwd);
        bool IsLoginNameExist(string loginname);

        IList<ExamExamineeInfo> LoadAll();

        IList<ExamExamineeInfo> LoadAll(string order, string where);

        //分页
        string FindByPage(int pageNo, int pageSize);
    }

}
