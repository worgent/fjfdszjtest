using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.BizTypeManger.Models;

namespace QzgfFrame.Archives.BizTypeManger.Domain
{
    public interface BizTypeFacade
    {
        ArchiveBizType Get(object id);
        ArchiveBizType Get(string order, string where);
        ArchiveBizType GetHql(string bizTypeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveBizType entity);

        bool Update(ArchiveBizType entity);

        IList<ArchiveBizType> LoadAll();

        IList<ArchiveBizType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
