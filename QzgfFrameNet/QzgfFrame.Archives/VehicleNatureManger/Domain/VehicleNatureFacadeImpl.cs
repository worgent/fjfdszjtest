using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.VehicleNatureManger.Models;
using QzgfFrame.Archives.VehicleNatureManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.VehicleNatureManger.Domain
{
    public class VehicleNatureFacadeImpl : VehicleNatureFacade
    {
        private IRepository<ArchiveVehicleNature> vehicleNatureRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveVehicleNature Get(object id)
        {
            return vehicleNatureRepository.Get(id.ToString());
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='VehicleNature'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='VehicleNature'";
                    result = relationRepository.DeleteHql(sql);
                    result = vehicleNatureRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='VehicleNature'";
                    result = relationRepository.DeleteHql(sql);
                    result = vehicleNatureRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }
        public ArchiveVehicleNature GetHql(string vehicleNatureName)
        {
            string Hql = " VehicleNatureName = '"+vehicleNatureName+"'";
            IList<ArchiveVehicleNature> vehicleNatures = vehicleNatureRepository.LoadAll("Id", Hql);
            if (vehicleNatures != null)
            {
                if (vehicleNatures.Count > 0)
                    return vehicleNatures[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveVehicleNature entity)
        {
            entity.Id = vehicleNatureRepository.NewSequence("SYSTEM_MENU");
            return vehicleNatureRepository.Save(entity);
        }

        public bool Update(ArchiveVehicleNature entity)
        {
            return vehicleNatureRepository.Update(entity);
        }

        public IList<ArchiveVehicleNature> LoadAll()
        {
            return vehicleNatureRepository.LoadAll();
        }
        public IList<ArchiveVehicleNature> LoadAll(string order, string where)
        {
            return vehicleNatureRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveVehicleNature";
            IList<ArchiveVehicleNature> ls = vehicleNatureRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = vehicleNatureRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
