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
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.MenufieldManger.Models;

namespace QzgfFrame.System.MenufieldManger.Domain

{
    public interface MenufieldFacade
    {

        #region 基本操作

        SystemMenufield Get(object id);

        bool Delete(string id);

        bool Save(SystemMenufield entity);

        bool Update(SystemMenufield entity);


        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder);

        #endregion

        #region 加强

        //扩展分页
        string FindByPage(string sortname, string sortorder);

        #endregion

        #region 保留

        IList<SystemMenufield> LoadAll();

        IList<SystemMenufield> LoadAll(string order, string where);

        #endregion

    }
}
