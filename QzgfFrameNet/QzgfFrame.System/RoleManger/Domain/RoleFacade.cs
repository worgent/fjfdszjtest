using System.Collections.Generic;
using QzgfFrame.System.RoleManger.Models;

namespace QzgfFrame.System.RoleManger.Domain
{
    public interface RoleFacade
    {

        #region 基本操作

        SystemRole Get(object id);

        bool Delete(string id);

        bool Save(SystemRole entity);

        bool Update(SystemRole entity);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder,string gridsearch);

        #endregion

        #region 加强

        string GetCombobox();

        bool UseState(string id, string state);

        #endregion

        #region 保留

        IList<SystemRole> LoadAll();

        IList<SystemRole> LoadAll(string order, string where);

        #endregion

    }
}
