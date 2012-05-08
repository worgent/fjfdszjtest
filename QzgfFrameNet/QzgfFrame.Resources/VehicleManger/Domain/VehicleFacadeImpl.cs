using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.VehicleManger.Models;
using QzgfFrame.Resources.VehicleManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.UseManger.Models;
using QzgfFrame.Archives.UseManger.Domain;
using QzgfFrame.Archives.VehicleNatureManger.Models;
using QzgfFrame.Archives.VehicleNatureManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.VehicleManger.Domain
{
    public class VehicleFacadeImpl : VehicleFacade
    {
        private IRepository<ResourceVehicle> vehicleRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveStagnation> stagnationRepository { set; get; }
        private IRepository<ArchiveGrid> gridRepository { set; get; }
        private IRepository<ArchiveMaintainSpecialty> maintainSpecialtyRepository { set; get; }
        private IRepository<ArchiveVehicleNature> vehicleNatureRepository { set; get; }
        private IRepository<ArchiveUse> useRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }
      
        public ResourceVehicle Get(object id)
        {
            return vehicleRepository.Get(id.ToString());
        }
        public ResourceVehicle GetHql(string LicPlateNo)
        {
            string hql = " upper(LicensePlateNumber)=upper('" + LicPlateNo.Trim() + "') and (DelFlag!=1 or DelFlag is null)";
            IList<ResourceVehicle> Vehicles = vehicleRepository.LoadAll("Id", hql);
            if (Vehicles != null)
            {
                if (Vehicles.Count > 0)
                    return Vehicles[0];
                else
                    return null;
            }
            else return null;
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = vehicleRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ResourceVehicle entity, string no)
        {
            bool result = false;

            entity.Id = vehicleRepository.NewSequence("SYSTEM_MENU", no);
            entity.CreateDate = DateTime.Now;
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Vehicle";
                sre1.MId = entity.Id;
                sre1.CId = entity.CompanyId;
                sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre1);
                i++;
            }
            if (entity.DistrictId != null && entity.DistrictId != "")
            {
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "Vehicle";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Vehicle";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "VehicleNature";
            sre4.ControllerName = "Vehicle";
            sre4.MId = entity.Id;
            sre4.CId = entity.VehicleNatureId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Vehicle";
                sre5.MId = entity.Id;
                sre5.CId = entity.StagnationId;
                sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre5);
                i++;
            }
            if (entity.GridId != null && entity.GridId != "")
            {
                SystemRelation sre6 = new SystemRelation();
                sre6.RelationName = "Grid";
                sre6.ControllerName = "Vehicle";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Vehicle";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Use";
            sre8.ControllerName = "Vehicle";
            sre8.MId = entity.Id;
            sre8.CId = entity.UseId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);

            result = vehicleRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceVehicle entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Vehicle'");
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Vehicle";
                sre1.MId = entity.Id;
                sre1.CId = entity.CompanyId;
                sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre1);
                i++;
            }
            if (entity.DistrictId != null && entity.DistrictId != "")
            {
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "Vehicle";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Vehicle";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "VehicleNature";
            sre4.ControllerName = "Vehicle";
            sre4.MId = entity.Id;
            sre4.CId = entity.VehicleNatureId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Vehicle";
                sre5.MId = entity.Id;
                sre5.CId = entity.StagnationId;
                sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre5);
                i++;
            }
            if (entity.GridId != null && entity.GridId != "")
            {
                SystemRelation sre6 = new SystemRelation();
                sre6.RelationName = "Grid";
                sre6.ControllerName = "Vehicle";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Vehicle";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Use";
            sre8.ControllerName = "Vehicle";
            sre8.MId = entity.Id;
            sre8.CId = entity.UseId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);
            result = vehicleRepository.Update(entity);
            return result;
        }

        public IList<ResourceVehicle> LoadAll()
        {
            return vehicleRepository.LoadAll();
        }
        public IList<ResourceVehicle> LoadAll(string order, string where)
        {
            return vehicleRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourceVehicle main where (main.DelFlag!=1 or main.DelFlag is null) ";
            hql +=  gridsearch; 
            string  vSql = hql + @" order by main." + sortname + " " + sortorder;
            var lsVehicle = vehicleRepository.FindByPage(pageNo, pageSize, vSql);
            IList<ArchiveCompany> lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            IList<ArchiveStagnation> lsStagnation = stagnationRepository.LoadAllbyHql("from ArchiveStagnation");
            IList<ArchiveDistrict> lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            IList<ArchiveGrid> lsGrid =gridRepository.LoadAllbyHql("from ArchiveGrid");
            IList<ArchiveVehicleNature> lsVehicleNature = vehicleNatureRepository.LoadAllbyHql("from ArchiveVehicleNature");
            IList<ArchiveUse> lsUse =useRepository.LoadAllbyHql("from ArchiveUse");
            IList<ArchiveMaintainSpecialty> lsMaintainSpecialty =maintainSpecialtyRepository.LoadAllbyHql("from ArchiveMaintainSpecialty");

            var jsonlist = (from vlsVehicle in lsVehicle
                            join vlsDistrict in lsDistrict
                                on vlsVehicle.DistrictId equals vlsDistrict.Id into joinvlsVehicleDistrict
                            from vlsVehicleDistrict in joinvlsVehicleDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsVehicle.CompanyId equals vlsCompany.Id into joinvlsVehicleCompany
                            from vlsVehicleCompany in joinvlsVehicleCompany.DefaultIfEmpty()

                            join vlsStagnation in lsStagnation
                                on vlsVehicle.CompanyId equals vlsStagnation.Id into joinvlsVehicleStagnation
                            from vlsVehicleStagnation in joinvlsVehicleStagnation.DefaultIfEmpty()

                            join vlsGrid in lsGrid
                                on vlsVehicle.CompanyId equals vlsGrid.Id into joinvlsVehicleGrid
                            from vlsVehicleGrid in joinvlsVehicleGrid.DefaultIfEmpty()

                            join vlsVehicleNature in lsVehicleNature
                                on vlsVehicle.VehicleNatureId equals vlsVehicleNature.Id into joinvlsVehicleVehicleNature
                            from vlsVehicleVehicleNature in joinvlsVehicleVehicleNature.DefaultIfEmpty()

                            join vlsUse in lsUse
                                on vlsVehicle.UseId equals vlsUse.Id into joinvlsVehicleUse
                            from vlsVehicleUse in joinvlsVehicleUse.DefaultIfEmpty()

                            join vlsMaintainSpecialty in lsMaintainSpecialty
                                on vlsVehicle.MaintainSpecialtyId equals vlsMaintainSpecialty.Id into joinvlsVehicleMaintainSpecialty
                            from vlsVehicleMaintainSpecialty in joinvlsVehicleMaintainSpecialty.DefaultIfEmpty()
                            select new
                            {
                                Id = vlsVehicle.Id,
                                TwoDimensionalCode = vlsVehicle.TwoDimensionalCode,
                                LicensePlateNumber = vlsVehicle.LicensePlateNumber,
                                DrivingLicenseNo = vlsVehicle.DrivingLicenseNo,
                                StartDatetime = vlsVehicle.StartDatetime,
                                ModelSpecification = vlsVehicle.ModelSpecification,
                                DistrictName = vlsVehicleDistrict != null ? vlsVehicleDistrict.DistrictName : "",
                                CompanyName = vlsVehicleCompany != null ? vlsVehicleCompany.CompanyName : "",
                                StagnationName = vlsVehicleStagnation != null ? vlsVehicleStagnation.StagnationName : "",
                                GridName = vlsVehicleGrid != null ? vlsVehicleGrid.GridName : "",
                                VehicleNatureName = vlsVehicleVehicleNature != null ? vlsVehicleVehicleNature.VehicleNatureName : "",
                                UseName = vlsVehicleUse != null ? vlsVehicleUse.UseName : "",
                                MaintainSpecialtyName = vlsVehicleMaintainSpecialty != null ? vlsVehicleMaintainSpecialty.MaintainSpecialtyName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = vehicleRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='Vehicle'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Vehicle'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = vehicleRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Vehicle'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = vehicleRepository.Update(hql);
                }   
            }
            return result;
        }
        /// <summary>
        /// 导出信息
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public IList<object[]> FindExcel(string aryField, string gridsearch)
        {
            string hql =
           @" from tab_Vehicle main  left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District dd on dd.Id=main.CityId
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_Grid grid on grid.Id=main.GridId 
                                      left join tab_Stagnation sta on sta.Id=main.StagnationId  
                                      left join tab_Vehicle_Nature nature on nature.Id=main.VehicleNatureId  
                                      left join tab_Use use on use.Id=main.UseId  
                                      left join tab_Maintain_Specialty maintain on maintain.Id=main.MaintainSpecialtyId 
            where (main.DelFlag!=1 or main.DelFlag is null) ";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = vehicleRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
