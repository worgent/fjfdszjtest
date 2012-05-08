using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.ApparatusManger.Models;
using QzgfFrame.Resources.ApparatusManger.Domain;
using QzgfFrame.Archives.MeterStateManger.Models;
using QzgfFrame.Archives.MeterStateManger.Domain;
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
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.ApparatusManger.Domain
{
    public class ApparatusFacadeImpl : ApparatusFacade
    {
        private IRepository<ResourceApparatus> apparatusRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveStagnation> stagnationRepository { set; get; }
        private IRepository<ArchiveGrid> gridRepository { set; get; }
        private IRepository<ArchiveMaintainSpecialty> maintainSpecialtyRepository { set; get; }
        private IRepository<ArchiveUse> useRepository { set; get; }
        private IRepository<ArchiveMeterState> meterStateRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }


        public ResourceApparatus Get(object id)
        {
            return apparatusRepository.Get(id.ToString());
        }
        public ResourceApparatus GetHql(string SeqNo)
        {
            string hql = " upper(SeqNo)=upper('" + SeqNo.Trim() + "') and (DelFlag!=1 or DelFlag is null)";
            IList<ResourceApparatus> Apparatus = apparatusRepository.LoadAll("Id", hql);
            if (Apparatus != null)
            {
                if (Apparatus.Count > 0)
                    return Apparatus[0];
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
                result = apparatusRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ResourceApparatus entity, string no)
        {
            bool result = false; 
            entity.Id = apparatusRepository.NewSequence("SYSTEM_MENU", no);
            entity.CreateDate = DateTime.Now;
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Apparatus";
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
                sre2.ControllerName = "Apparatus";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Apparatus";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "MeterState";
            sre4.ControllerName = "Apparatus";
            sre4.MId = entity.Id;
            sre4.CId = entity.MeterStateId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Apparatus";
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
                sre6.ControllerName = "Apparatus";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Apparatus";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Use";
            sre8.ControllerName = "Apparatus";
            sre8.MId = entity.Id;
            sre8.CId = entity.UseId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);

            result = apparatusRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceApparatus entity)
        {
            bool result = false; 
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Apparatus'");
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Apparatus";
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
                sre2.ControllerName = "Apparatus";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Apparatus";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "MeterState";
            sre4.ControllerName = "Apparatus";
            sre4.MId = entity.Id;
            sre4.CId = entity.MeterStateId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Apparatus";
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
                sre6.ControllerName = "Apparatus";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Apparatus";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Use";
            sre8.ControllerName = "Apparatus";
            sre8.MId = entity.Id;
            sre8.CId = entity.UseId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);

            result = apparatusRepository.Update(entity);
            return result;
        }

        public IList<ResourceApparatus> LoadAll()
        {
            return apparatusRepository.LoadAll();
        }
        public IList<ResourceApparatus> LoadAll(string order, string where)
        {
            return apparatusRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourceApparatus main where (main.DelFlag!=1 or main.DelFlag is null) ";
             hql +=  gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var lsApparatus = apparatusRepository.FindByPage(pageNo, pageSize, vSql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            var lsStagnation = stagnationRepository.LoadAllbyHql("from ArchiveStagnation");
            var lsGrid = gridRepository.LoadAllbyHql("from ArchiveGrid");
            var lsMeterState = meterStateRepository.LoadAllbyHql("from ArchiveMeterState");
            var lsUse = useRepository.LoadAllbyHql("from ArchiveUse");
            var lsMaintainSpecialty = maintainSpecialtyRepository.LoadAllbyHql("from ArchiveMaintainSpecialty");

            var jsonlist = (from vlsApparatus in lsApparatus
                            join vlsDistrict in lsDistrict
                                on vlsApparatus.DistrictId equals vlsDistrict.Id into joinvlsApparatusDistrict
                            from vlsApparatusDistrict in joinvlsApparatusDistrict.DefaultIfEmpty()

                             join vlsCompany in lsCompany
                                on vlsApparatus.CompanyId equals vlsCompany.Id into joinvlsApparatusCompany
                            from vlsApparatusCompany in joinvlsApparatusCompany.DefaultIfEmpty()

                            join vlsStagnation in lsStagnation
                                on vlsApparatus.CompanyId equals vlsStagnation.Id into joinvlsApparatusStagnation
                            from vlsApparatusStagnation in joinvlsApparatusStagnation.DefaultIfEmpty()

                            join vlsGrid in lsGrid
                                on vlsApparatus.CompanyId equals vlsGrid.Id into joinvlsApparatusGrid
                            from vlsApparatusGrid in joinvlsApparatusGrid.DefaultIfEmpty()

                            join vlsMeterState in lsMeterState
                                on vlsApparatus.MeterStateId equals vlsMeterState.Id into joinvlsApparatusMeterState
                            from vlsApparatusMeterState in joinvlsApparatusMeterState.DefaultIfEmpty()

                            join vlsUse in lsUse
                                on vlsApparatus.UseId equals vlsUse.Id into joinvlsApparatusUse
                            from vlsApparatusUse in joinvlsApparatusUse.DefaultIfEmpty()

                            join vlsMaintainSpecialty in lsMaintainSpecialty
                                on vlsApparatus.MaintainSpecialtyId equals vlsMaintainSpecialty.Id into joinvlsApparatusMaintainSpecialty
                            from vlsApparatusMaintainSpecialty in joinvlsApparatusMaintainSpecialty.DefaultIfEmpty()
                            select new
                            {
                                Id = vlsApparatus.Id,
                                TwoDimensionalCode = vlsApparatus.TwoDimensionalCode,
                                ManuFacturer = vlsApparatus.ManuFacturer,
                                MeterName = vlsApparatus.MeterName,
                                MeterModel = vlsApparatus.MeterModel,
                                SeqNo = vlsApparatus.SeqNo,
                                DistrictName = vlsApparatusDistrict != null ? vlsApparatusDistrict.DistrictName : "",
                                CompanyName = vlsApparatusCompany != null ? vlsApparatusCompany.CompanyName : "",
                                StagnationName = vlsApparatusStagnation != null ? vlsApparatusStagnation.StagnationName : "",
                                GridName = vlsApparatusGrid != null ? vlsApparatusGrid.GridName : "",
                                MeterStateName = vlsApparatusMeterState != null ? vlsApparatusMeterState.MeterStateName : "",
                                UseName = vlsApparatusUse != null ? vlsApparatusUse.UseName : "",
                                MaintainSpecialtyName = vlsApparatusMaintainSpecialty != null ? vlsApparatusMaintainSpecialty.MaintainSpecialtyName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = apparatusRepository.FindByPageCount(hql);
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
            //删除关系            
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='Apparatus'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Apparatus'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = apparatusRepository.Update(hql);                
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Apparatus'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = apparatusRepository.Update(hql);  
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
           @" from tab_Apparatus main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District dd on dd.Id=main.CityId
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_Grid grid on grid.Id=main.GridId 
                                      left join tab_Stagnation sta on sta.Id=main.StagnationId  
                                      left join tab_Meter_State meter on meter.Id=main.MeterStateId  
                                      left join tab_Use use on use.Id=main.UseId  
                                      left join tab_Maintain_Specialty maintain on maintain.Id=main.MaintainSpecialtyId 
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = apparatusRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
