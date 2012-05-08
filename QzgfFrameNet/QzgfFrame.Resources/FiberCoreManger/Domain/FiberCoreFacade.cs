using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.FiberCoreManger.Models;

namespace QzgfFrame.Resources.FiberCoreManger.Domain
{
    public interface FiberCoreFacade
    {
        ResourceFiberCore Get(object id);

        bool Delete(string id);

        bool Save(ResourceFiberCore entity, string no);

        bool Update(ResourceFiberCore entity);

        IList<ResourceFiberCore> LoadAll();

        IList<ResourceFiberCore> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        bool DeleteFalse(string id);
    }
}
