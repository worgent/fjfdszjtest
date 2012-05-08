using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.OtherInfoManger.Models;

namespace QzgfFrame.Resources.OtherInfoManger.Domain
{
    public interface OtherInfoFacade
    {
        ResourceOtherInfo Get(object id);

        bool Delete(string id);

        bool Save(ResourceOtherInfo entity, string no);

        bool Update(ResourceOtherInfo entity);

        IList<ResourceOtherInfo> LoadAll();

        IList<ResourceOtherInfo> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        bool DeleteFalse(string id);
    }
}
