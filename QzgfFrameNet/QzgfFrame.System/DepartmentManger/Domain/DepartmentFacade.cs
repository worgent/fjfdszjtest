/****************************************************************** 
 * 文件名.........: MenuController.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System.Collections.Generic;
using QzgfFrame.System.DepartmentManger.Models;

namespace QzgfFrame.System.DepartmentManger.Domain

{
    public interface DepartmentFacade
    {
        #region 基本操作

        SystemDepartment Get(object id);

        bool Delete(string id);

        bool Save(SystemDepartment entity);

        bool Update(SystemDepartment entity);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder);

        #endregion

        #region 加强

        string GetFather();
        string GetFather(string selid, string type);

        bool UseState(string id, string state);

        #endregion

        #region 保留

        IList<SystemDepartment> LoadAll();

        IList<SystemDepartment> LoadAll(string order, string where);

        #endregion

    }
}
