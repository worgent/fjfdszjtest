using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.OneSevenZeroManger.Models;

namespace QzgfFrame.Resources.OneSevenZeroManger.Domain
{
    public interface OneSevenZeroFacade
    {
        ResourceOneSevenZero Get(object id);

        bool Delete(string id);

        bool Save(ResourceOneSevenZero entity, string no);

        bool Update(ResourceOneSevenZero entity);
        bool Update(string id);

        IList<ResourceOneSevenZero> LoadAll();

        IList<ResourceOneSevenZero> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
