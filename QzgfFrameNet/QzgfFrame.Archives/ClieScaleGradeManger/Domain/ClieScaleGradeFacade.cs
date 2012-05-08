using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.ClieScaleGradeManger.Models;

namespace QzgfFrame.Archives.ClieScaleGradeManger.Domain
{
    public interface ClieScaleGradeFacade
    {
        ArchiveClieScaleGrade Get(object id);
        ArchiveClieScaleGrade GetHql(object ScaleGradeName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveClieScaleGrade entity);

        bool Update(ArchiveClieScaleGrade entity);

        IList<ArchiveClieScaleGrade> LoadAll();

        IList<ArchiveClieScaleGrade> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
