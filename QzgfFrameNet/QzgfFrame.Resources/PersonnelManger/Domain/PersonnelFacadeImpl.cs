using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Resources.PersonnelManger.Domain;
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
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.Archives.DutyManger.Models;
using QzgfFrame.Archives.DutyManger.Domain;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.Archives.ItemPropertyManger.Models;
using QzgfFrame.Archives.ItemPropertyManger.Domain;
using QzgfFrame.Archives.PostsManger.Models;
using QzgfFrame.Archives.PostsManger.Domain;
using QzgfFrame.Archives.QualificationTypeManger.Models;
using QzgfFrame.Archives.QualificationTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.PersonnelManger.Domain
{
    public class PersonnelFacadeImpl : PersonnelFacade
    {
        private IRepository<ResourcePersonnel> personnelRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveStagnation> stagnationRepository { set; get; }
        private IRepository<ArchiveMaintainSpecialty> maintainSpecialtyRepository { set; get; }
        private IRepository<ArchiveGrid> gridRepository { set; get; }
        private IRepository<ArchiveDuty> dutyRepository { set; get; }
        private IRepository<ArchiveQualificationType> qualificationTypeRepository { set; get; }
        private IRepository<ArchiveItemProperty> itemPropertyRepository { set; get; }
        private IRepository<ArchiveDiploma> diplomaRepository { set; get; }
        private IRepository<ArchivePosts> postsRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourcePersonnel Get(object id)
        {
            return personnelRepository.Get(id.ToString());
        }
        public ResourcePersonnel GetHql(string IDCardNo)
        {
            string hql = " upper(IDCardNumber)=upper('" + IDCardNo.Trim() + "') and (DelFlag!=1 or DelFlag is null)";
            IList<ResourcePersonnel> Personnels = personnelRepository.LoadAll("Id", hql);
            if (Personnels != null)
            {
                if (Personnels.Count > 0)
                    return Personnels[0];
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
                result = personnelRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ResourcePersonnel entity, string no)
        {
            bool result = false;
            entity.Id = personnelRepository.NewSequence("SYSTEM_MENU", no);
            entity.CreateDate = DateTime.Now;
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Personnel";
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
                sre2.ControllerName = "Personnel";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Personnel";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "Posts";
            sre4.ControllerName = "Personnel";
            sre4.MId = entity.Id;
            sre4.CId = entity.PostsId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId!="")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Personnel";
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
                sre6.ControllerName = "Personnel";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Personnel";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Duty";
            sre8.ControllerName = "Personnel";
            sre8.MId = entity.Id;
            sre8.CId = entity.DutyId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);
            i++;
            SystemRelation sre9 = new SystemRelation();
            sre9.RelationName = "Diploma";
            sre9.ControllerName = "Personnel";
            sre9.MId = entity.Id;
            sre9.CId = entity.DiplomaId;
            sre9.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre9);
            i++;
            SystemRelation sre10 = new SystemRelation();
            sre10.RelationName = "ItemProperty";
            sre10.ControllerName = "Personnel";
            sre10.MId = entity.Id;
            sre10.CId = entity.ItemPropertyId;
            sre10.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre10);
            i++;
            SystemRelation sre11 = new SystemRelation();
            sre11.RelationName = "QualificationType";
            sre11.ControllerName = "Personnel";
            sre11.MId = entity.Id;
            sre11.CId = entity.QualificationTypeId;
            sre11.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre11);
            i++;
            if (entity.Certificate1 != null && entity.Certificate1 != "" && entity.Certificate1 != "0")
            {
            SystemRelation sre12 = new SystemRelation();
            sre12.RelationName = "Certificate";
            sre12.ControllerName = "Personnel";
            sre12.MId = entity.Id;
            sre12.CId = entity.Certificate1;
            sre12.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre12);
            i++;
            }
            if (entity.Certificate2 != null && entity.Certificate2 != "" && entity.Certificate2 != "0")
            {
                SystemRelation sre13 = new SystemRelation();
                sre13.RelationName = "Certificate";
                sre13.ControllerName = "Personnel";
                sre13.MId = entity.Id;
                sre13.CId = entity.Certificate2;
                sre13.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre13);
                i++;
            }
            if (entity.Certificate3 != null && entity.Certificate3 != "" && entity.Certificate3 != "0")
            {
                SystemRelation sre14 = new SystemRelation();
                sre14.RelationName = "Certificate";
                sre14.ControllerName = "Personnel";
                sre14.MId = entity.Id;
                sre14.CId = entity.Certificate3;
                sre14.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre14);
                i++;
            }
            if (entity.Certificate4 != null && entity.Certificate4 != "" && entity.Certificate4 != "0")
            {
                SystemRelation sre15 = new SystemRelation();
                sre15.RelationName = "Certificate";
                sre15.ControllerName = "Personnel";
                sre15.MId = entity.Id;
                sre15.CId = entity.Certificate4;
                sre15.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre15);
                i++;
            }
            if (entity.Certificate5 != null && entity.Certificate5 != "" && entity.Certificate5 != "0")
            {
                SystemRelation sre16 = new SystemRelation();
                sre16.RelationName = "Certificate";
                sre16.ControllerName = "Personnel";
                sre16.MId = entity.Id;
                sre16.CId = entity.Certificate5;
                sre16.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre16);
                i++;
            }
            result = personnelRepository.Save(entity);
            return result;  
        }

        public bool Update(ResourcePersonnel entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Personnel'");
            //添加关系信息
            int i = 0;
            if (entity.CompanyId != null && entity.CompanyId != "")
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "Personnel";
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
                sre2.ControllerName = "Personnel";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Personnel";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "Posts";
            sre4.ControllerName = "Personnel";
            sre4.MId = entity.Id;
            sre4.CId = entity.PostsId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            if (entity.StagnationId != null && entity.StagnationId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "Stagnation";
                sre5.ControllerName = "Personnel";
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
                sre6.ControllerName = "Personnel";
                sre6.MId = entity.Id;
                sre6.CId = entity.GridId;
                sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre6);
                i++;
            }
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "MaintainSpecialty";
            sre7.ControllerName = "Personnel";
            sre7.MId = entity.Id;
            sre7.CId = entity.MaintainSpecialtyId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "Duty";
            sre8.ControllerName = "Personnel";
            sre8.MId = entity.Id;
            sre8.CId = entity.DutyId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);
            i++;
            SystemRelation sre9 = new SystemRelation();
            sre9.RelationName = "Diploma";
            sre9.ControllerName = "Personnel";
            sre9.MId = entity.Id;
            sre9.CId = entity.DiplomaId;
            sre9.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre9);
            i++;
            SystemRelation sre10 = new SystemRelation();
            sre10.RelationName = "ItemProperty";
            sre10.ControllerName = "Personnel";
            sre10.MId = entity.Id;
            sre10.CId = entity.ItemPropertyId;
            sre10.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre10);
            i++;
            SystemRelation sre11 = new SystemRelation();
            sre11.RelationName = "QualificationType";
            sre11.ControllerName = "Personnel";
            sre11.MId = entity.Id;
            sre11.CId = entity.QualificationTypeId;
            sre11.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre11);
            i++;
            if (entity.Certificate1 != null && entity.Certificate1 != "" && entity.Certificate1 != "0")
            {
                SystemRelation sre12 = new SystemRelation();
                sre12.RelationName = "Certificate";
                sre12.ControllerName = "Personnel";
                sre12.MId = entity.Id;
                sre12.CId = entity.Certificate1;
                sre12.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre12);
                i++;
            }
            if (entity.Certificate2 != null && entity.Certificate2 != "" && entity.Certificate2 != "0")
            {
                SystemRelation sre13 = new SystemRelation();
                sre13.RelationName = "Certificate";
                sre13.ControllerName = "Personnel";
                sre13.MId = entity.Id;
                sre13.CId = entity.Certificate2;
                sre13.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre13);
                i++;
            }
            if (entity.Certificate3 != null && entity.Certificate3 != "" && entity.Certificate3 != "0")
            {
                SystemRelation sre14 = new SystemRelation();
                sre14.RelationName = "Certificate";
                sre14.ControllerName = "Personnel";
                sre14.MId = entity.Id;
                sre14.CId = entity.Certificate3;
                sre14.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre14);
                i++;
            }
            if (entity.Certificate4 != null && entity.Certificate4 != "" && entity.Certificate4 != "0")
            {
                SystemRelation sre15 = new SystemRelation();
                sre15.RelationName = "Certificate";
                sre15.ControllerName = "Personnel";
                sre15.MId = entity.Id;
                sre15.CId = entity.Certificate4;
                sre15.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre15);
                i++;
            }
            if (entity.Certificate5 != null && entity.Certificate5 != "" && entity.Certificate5 != "0")
            {
                SystemRelation sre16 = new SystemRelation();
                sre16.RelationName = "Certificate";
                sre16.ControllerName = "Personnel";
                sre16.MId = entity.Id;
                sre16.CId = entity.Certificate5;
                sre16.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre16);
                i++;
            }
            result = personnelRepository.Update(entity);
            return result;
        }

        public IList<ResourcePersonnel> LoadAll()
        {
            return personnelRepository.LoadAll();
        }
        public IList<ResourcePersonnel> LoadAll(string order, string where)
        {
            return personnelRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourcePersonnel main where (main.DelFlag!=1 or main.DelFlag is null) ";
            hql +=  gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var lsPersonnel = personnelRepository.FindByPage(pageNo, pageSize, vSql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            var lsStagnation = stagnationRepository.LoadAllbyHql("from ArchiveStagnation");
            var lsGrid = gridRepository.LoadAllbyHql("from ArchiveGrid");
            var lsMaintainSpecialty = maintainSpecialtyRepository.LoadAllbyHql("from ArchiveMaintainSpecialty");
            var lsQualificationType = qualificationTypeRepository.LoadAllbyHql("from ArchiveQualificationType");
            var lsItemProperty = itemPropertyRepository.LoadAllbyHql("from ArchiveItemProperty");
            var lsDuty = dutyRepository.LoadAllbyHql("from ArchiveDuty");
            var lsDiploma = diplomaRepository.LoadAllbyHql("from ArchiveDiploma");
            var lsPosts = postsRepository.LoadAllbyHql("from ArchivePosts");

            var jsonlist = (from vlsPersonnel in lsPersonnel
                            join vlsDistrict in lsDistrict
                                on vlsPersonnel.DistrictId equals vlsDistrict.Id into joinvlsPersonnelDistrict
                            from vlsPersonnelDistrict in joinvlsPersonnelDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsPersonnel.CompanyId equals vlsCompany.Id into joinvlsPersonnelCompany
                            from vlsPersonnelCompany in joinvlsPersonnelCompany.DefaultIfEmpty()

                            join vlsStagnation in lsStagnation
                                on vlsPersonnel.CompanyId equals vlsStagnation.Id into joinvlsPersonnelStagnation
                            from vlsPersonnelStagnation in joinvlsPersonnelStagnation.DefaultIfEmpty()

                            join vlsGrid in lsGrid
                                on vlsPersonnel.CompanyId equals vlsGrid.Id into joinvlsPersonnelGrid
                            from vlsPersonnelGrid in joinvlsPersonnelGrid.DefaultIfEmpty()

                            join vlsMaintainSpecialty in lsMaintainSpecialty
                                on vlsPersonnel.MaintainSpecialtyId equals vlsMaintainSpecialty.Id into joinvlsPersonnelMaintainSpecialty
                            from vlsPersonnelMaintainSpecialty in joinvlsPersonnelMaintainSpecialty.DefaultIfEmpty()

                            join vlsQualificationType in lsQualificationType
                                on vlsPersonnel.QualificationTypeId equals vlsQualificationType.Id into joinvlsPersonnelQualificationType
                            from vlsPersonnelQualificationType in joinvlsPersonnelQualificationType.DefaultIfEmpty()

                            join vlsItemProperty in lsItemProperty
                                on vlsPersonnel.ItemPropertyId equals vlsItemProperty.Id into joinvlsPersonnelItemProperty
                            from vlsPersonnelItemProperty in joinvlsPersonnelItemProperty.DefaultIfEmpty()

                            join vlsDuty in lsDuty
                                on vlsPersonnel.DutyId equals vlsDuty.Id into joinvlsPersonnelDuty
                            from vlsPersonnelDuty in joinvlsPersonnelDuty.DefaultIfEmpty()

                            join vlsDiploma in lsDiploma
                                on vlsPersonnel.DiplomaId equals vlsDiploma.Id into joinvlsPersonnelDiploma
                            from vlsPersonnelDiploma in joinvlsPersonnelDiploma.DefaultIfEmpty()

                            join vlsPosts in lsPosts
                                on vlsPersonnel.PostsId equals vlsPosts.Id into joinvlsPersonnelPosts
                            from vlsPersonnelPosts in joinvlsPersonnelPosts.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsPersonnel.Id,
                                TwoDimensionalCode = vlsPersonnel.TwoDimensionalCode,
                                FullName = vlsPersonnel.FullName,
                                MobileNumber = vlsPersonnel.MobileNumber,
                                ItemName = vlsPersonnel.ItemName,
                                IsBackbone = vlsPersonnel.IsBackbone,
                                EntryDate = vlsPersonnel.EntryDate,
                                DistrictName = vlsPersonnelDistrict != null ? vlsPersonnelDistrict.DistrictName : "",
                                CompanyName = vlsPersonnelCompany != null ? vlsPersonnelCompany.CompanyName : "",
                                StagnationName = vlsPersonnelStagnation != null ? vlsPersonnelStagnation.StagnationName : "",
                                GridName = vlsPersonnelGrid != null ? vlsPersonnelGrid.GridName : "",
                                QualificationTypeName = vlsPersonnelQualificationType != null ? vlsPersonnelQualificationType.QualificationTypeName : "",
                                ItemPropertyName = vlsPersonnelItemProperty != null ? vlsPersonnelItemProperty.ItemPropertyName : "",
                                ItemDuty = vlsPersonnelDuty != null ? vlsPersonnelDuty.DutyName : "",
                                ItemDiploma = vlsPersonnelDiploma != null ? vlsPersonnelDiploma.DiplomaName : "",
                                ItemPosts = vlsPersonnelPosts != null ? vlsPersonnelPosts.PostsName : "",
                                MaintainSpecialtyName = vlsPersonnelMaintainSpecialty != null ? vlsPersonnelMaintainSpecialty.MaintainSpecialtyName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = personnelRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        /// <summary>
        /// 选择人员信息（hld新增）只读取人员信息表
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <returns></returns>
        public string SelectByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourcePersonnel";
            IList<ResourcePersonnel> ls = personnelRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = personnelRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
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
                string strsql = " CId='" + s + "' and RelationName='Personnel'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    //删除关系
                    string sql = " MId='" + s + "' and ControllerName='Personnel'";
                    result = relationRepository.DeleteHql(sql);
                    //删除
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = personnelRepository.Update(hql);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Personnel'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = personnelRepository.Update(hql);
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
           @" from tab_Personnel main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District dd on dd.Id=main.CityId
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_Grid grid on grid.Id=main.GridId 
                                      left join tab_Stagnation sta on sta.Id=main.StagnationId  
                                      left join tab_Qualification_Type qtype on qtype.Id=main.QualificationTypeId  
                                      left join tab_Item_Property item on item.Id=main.ItemPropertyId  
                                      left join tab_Duty duty on duty.Id=main.DutyId 
                                      left join tab_Diploma diploma on diploma.Id=main.DiplomaId 
                                      left join tab_Posts posts on posts.Id=main.PostsId 
                                      left join tab_Certificate cert1 on cert1.Id=main.Certificate1 
                                      left join tab_Certificate cert2 on cert2.Id=main.Certificate2 
                                      left join tab_Maintain_Specialty maintain on maintain.Id=main.MaintainSpecialtyId 
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = personnelRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
