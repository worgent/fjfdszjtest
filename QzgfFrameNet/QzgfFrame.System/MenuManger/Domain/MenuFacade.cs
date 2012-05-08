/****************************************************************** 
 * 文件名.........: MenuController.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.System.MenuManger.Models;

namespace QzgfFrame.System.MenuManger.Domain
{
    public interface MenuFacade
    {

        #region 基本操作

        SystemMenu Get(object id);

        bool Delete(string id);

        bool Save(SystemMenu entity);

        bool Update(SystemMenu entity);

        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder,string gridsearch);

        #endregion

        #region 加强

        string GetButton(string menuid);
        string GetMenu();

        #endregion

        #region 保留

        IList<SystemMenu> LoadAll();

        IList<SystemMenu> LoadAll(string order, string where);

        #endregion

    }
}
