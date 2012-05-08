using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.VehicleNatureManger.Models;

namespace QzgfFrame.Archives.VehicleNatureManger.Domain
{
    public interface VehicleNatureFacade
    {
        ArchiveVehicleNature Get(object id);
        ArchiveVehicleNature GetHql(string Hql);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveVehicleNature entity);

        bool Update(ArchiveVehicleNature entity);

        IList<ArchiveVehicleNature> LoadAll();

        IList<ArchiveVehicleNature> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
