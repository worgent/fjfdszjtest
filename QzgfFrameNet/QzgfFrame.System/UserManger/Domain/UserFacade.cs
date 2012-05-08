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
using QzgfFrame.System.UserManger.Models;

namespace QzgfFrame.System.UserManger.Domain
{
    public interface UserFacade
    {
        #region 基础操作

        SystemUser Get(object id);

        bool Delete(string id);

        bool Save(SystemUser entity);

        bool Update(SystemUser entity);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder);

        #endregion

        #region 加强

        string CheckLogin(string username, string password, out SystemUser viewuser);
        string ModifyPwd(string userid, string oldpwd, string newpwd);
        bool IsUserNameExist(string name);
        bool UseState(string id, string state);

        bool UpdateRole(SystemUser entity);
        #endregion

        #region 保留

        IList<SystemUser> LoadAll();

        IList<SystemUser> LoadAll(string order, string where);
        //因业务模型跨包，所以将原本的实现类放到控制器中
        IList<SystemUser> FindByPage(int pageNo, int pageSize, string hql);
        int FindByPageCount(string hql);

        #endregion

    }
}
