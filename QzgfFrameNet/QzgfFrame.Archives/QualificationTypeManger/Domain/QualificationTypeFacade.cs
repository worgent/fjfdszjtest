using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.QualificationTypeManger.Models;

namespace QzgfFrame.Archives.QualificationTypeManger.Domain
{
    public interface QualificationTypeFacade
    {
        ArchiveQualificationType Get(object id);
        ArchiveQualificationType GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveQualificationType entity);

        bool Update(ArchiveQualificationType entity);

        IList<ArchiveQualificationType> LoadAll();

        IList<ArchiveQualificationType> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
