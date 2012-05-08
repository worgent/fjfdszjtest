using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.NumberGroupManger.Models;

namespace QzgfFrame.Resources.NumberGroupManger.Domain
{
    public interface NumberGroupFacade
    {
        ResourceNumberGroup Get(object id);

        bool Delete(string id);

        bool Save(ResourceNumberGroup entity, string no);

        bool Update(ResourceNumberGroup entity);

        IList<ResourceNumberGroup> LoadAll();

        IList<ResourceNumberGroup> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        bool DeleteFalse(string id);
    }
}
