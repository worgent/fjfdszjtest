/****************************************************************** 
 * 文件名.........: MenuController.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System.Collections;
using System.Collections.Generic;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.MenufieldManger.Models;
using QzgfFrame.System.PermissionsManger.Models;
using QzgfFrame.System.UserManger.Models;

namespace QzgfFrame.System.PermissionsManger.Domain
{
    public interface PermissionsFacade
    {

        #region 基本操作

        bool Delete(string id);

        bool Save(string rolemenustr, string rolepowerval, string roleid);

        bool Update(SystemRolefiledpower entity);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder);

        #endregion

        #region 加强

        string LoadAllViewrolemenu(string roleid);
        string LoadAllViewrolepowerval(string roleid);

        IList<SystemMenu> GetMenubyUserId(string userid);
        IList<SystemMenufield> GetMenufieldUserId(string userid);

        #endregion

        #region 保留

        IList<SystemRolefiledpower> LoadAll();

        IList<SystemRolefiledpower> LoadAll(string order, string where);

        #endregion


    }
}
