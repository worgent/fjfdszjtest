/*

 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能

*/

using QzgfFrame.System.SmsManger.Models;

namespace QzgfFrame.System.SmsManger.Domain
{
    /// <summary>
    /// auto gen
    /// </summary>
    public interface SmsFacade
    {
        #region 基本操作

        TabQywyySmsSend Get(object id);
        bool Delete(string id);
        bool Save(TabQywyySmsSend entity);
        bool Update(TabQywyySmsSend entity);
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder);

        #endregion

        #region 加强

        bool UseState(string id, string state);

        #endregion

        #region 保留

        #endregion
    }
}