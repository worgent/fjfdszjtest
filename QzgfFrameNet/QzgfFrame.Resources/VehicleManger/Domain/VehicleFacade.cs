using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Resources.VehicleManger.Models;

namespace QzgfFrame.Resources.VehicleManger.Domain
{
    public interface VehicleFacade
    {
        ResourceVehicle Get(object id);
        ResourceVehicle GetHql(string LicPlateNo);
        bool Delete(string id);

        bool Save(ResourceVehicle entity, string no);

        bool Update(ResourceVehicle entity);

        IList<ResourceVehicle> LoadAll();

        IList<ResourceVehicle> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch);
        bool DeleteFalse(string id);
        IList<object[]> FindExcel(string aryField, string gridsearch);
    }
}
