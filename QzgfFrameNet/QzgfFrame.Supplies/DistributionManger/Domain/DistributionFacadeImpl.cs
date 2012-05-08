using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.DistributionManger.Models;
using QzgfFrame.Supplies.DistributionManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.DistributionManger.Domain
{
    public class DistributionFacadeImpl : DistributionFacade
    {
        private IRepository<SuppliesDistribution> distributionRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveSaleDepartment> saleDepartmentRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }


        public SuppliesDistribution Get(object id)
        {
            string hql =
            @"select new SuppliesDistribution(main.Id,main.CreateDatetime,main.DistributionUser,main.Num,
            main.CityId,ad.DistrictName,main.CompanyId,ac.CompanyName,
            main.DistrictId,addistrict.DistrictName as DDistrictName,main.DCompanyId,acc.CompanyName as DCompanyName,
            main.Remark,main.Reason,main.State)
            from SuppliesDistribution main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveDistrict addistrict,ArchiveCompany acc
            where main.CityId=ad.Id and main.CompanyId=ac.Id and main.DistrictId=addistrict.Id and main.DCompanyId=acc.Id
            and (main.DelFlag!=1 or main.DelFlag is null)";
            
            return distributionRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        public SuppliesDistribution GetYw(object id)
        {
            string hql =
            @"select new SuppliesDistribution(main.Id,main.CreateDatetime,main.DistributionUser,main.Num,
            main.CityId,ad.DistrictName,main.CompanyId,ac.CompanyName,
            main.DistrictId,addistrict.DistrictName as DDistrictName,main.DCompanyId,acc.CompanyName as DCompanyName,
            main.Remark,main.Reason,main.SaleDepartmentId,asd.SaleDepartmentName)
            from SuppliesDistribution main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveDistrict addistrict,
                 ArchiveCompany acc,ArchiveSaleDepartment asd
            where main.CityId=ad.Id and main.CompanyId=ac.Id and main.DistrictId=addistrict.Id and 
                  main.DCompanyId=acc.Id and main.SaleDepartmentId=asd.Id and (main.DelFlag!=1 or main.DelFlag is null)";

            return distributionRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
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
                result = distributionRepository.Delete(s);
            }
            return result;
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
                string strsql = " CId='" + s + "' and RelationName='Distribution'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Distribution'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = distributionRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Distribution'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = distributionRepository.Update(hql);
                }
            }
            return result;
        }
        public bool Save(SuppliesDistribution entity, string no)
        {
            entity.Id = distributionRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Distribution";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            if (entity.DistrictId != null && entity.DistrictId != "")
            {
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "Distribution";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }

            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Distribution";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            i++;
            if (entity.DCompanyId != "" && entity.DCompanyId != "")
            {
                SystemRelation sre4 = new SystemRelation();
                sre4.RelationName = "Company";
                sre4.ControllerName = "Distribution";
                sre4.MId = entity.Id;
                sre4.CId = entity.DCompanyId;
                sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre4);

                i++;
            }
            if (entity.SaleDepartmentId != null && entity.SaleDepartmentId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "SaleDepartment";
                sre5.ControllerName = "Distribution";
                sre5.MId = entity.Id;
                sre5.CId = entity.SaleDepartmentId;
                sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre5);
            }
            result = distributionRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesDistribution entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Distribution'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Distribution";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            if (entity.DistrictId != null && entity.DistrictId != "")
            {
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "Distribution";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
            }
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "District";
            sre3.ControllerName = "Distribution";
            sre3.MId = entity.Id;
            sre3.CId = entity.CityId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            i++;

            if (entity.DCompanyId != "" && entity.DCompanyId != "")
            {
                SystemRelation sre4 = new SystemRelation();
                sre4.RelationName = "Company";
                sre4.ControllerName = "Distribution";
                sre4.MId = entity.Id;
                sre4.CId = entity.DCompanyId;
                sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre4);

                i++;
            }
            if (entity.SaleDepartmentId != null && entity.SaleDepartmentId != "")
            {
                SystemRelation sre5 = new SystemRelation();
                sre5.RelationName = "SaleDepartment";
                sre5.ControllerName = "Distribution";
                sre5.MId = entity.Id;
                sre5.CId = entity.SaleDepartmentId;
                sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre5);
            }
            result = distributionRepository.Update(entity);
            return result;
        }

        public bool UpdateH(SuppliesDistribution entity)
        {
            string hql = " State='" + entity.State + "',Reason='" + entity.Reason + "' where Id='" + entity.Id + "'";
            return distributionRepository.Update(hql);
        }
        public IList<SuppliesDistribution> LoadAll()
        {
            return distributionRepository.LoadAll();
        }
        public IList<SuppliesDistribution> LoadAll(string order, string where)
        {
            return distributionRepository.LoadAll(order, where);
        }
        /// <summary>
        /// 接收方
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindRByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new SuppliesDistribution(main.Id,main.CreateDatetime,main.DistributionUser,main.Num,
            main.CityId,ad.DistrictName,main.CompanyId,ac.CompanyName,
            main.DistrictId,addistrict.DistrictName as DDistrictName,main.DCompanyId,acc.CompanyName as DCompanyName,main.State)
            from SuppliesDistribution main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveDistrict addistrict,ArchiveCompany acc
            where main.CityId=ad.Id and main.CompanyId=ac.Id and main.DistrictId=addistrict.Id and main.DCompanyId=acc.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesDistribution> lsDistribution = distributionRepository.FindByPage(pageNo, pageSize, hql);
            int recordCount = distributionRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsDistribution) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 分配方，没有接收客户名称
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new SuppliesDistribution(main.Id,main.CreateDatetime,main.DistributionUser,main.Num,
            main.CityId,ad.DistrictName,main.CompanyId,ac.CompanyName,
            main.DistrictId,addistrict.DistrictName as DDistrictName,main.DCompanyId,acc.CompanyName as DCompanyName,main.State)
            from SuppliesDistribution main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveDistrict addistrict,ArchiveCompany acc
            where main.CityId=ad.Id and main.CompanyId=ac.Id and main.DistrictId=addistrict.Id and main.DCompanyId=acc.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesDistribution> lsDistribution = distributionRepository.FindByPage(pageNo, pageSize, hql);
            int recordCount = distributionRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsDistribution) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        /// <summary>
        /// 营业部
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindYwByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new SuppliesDistribution(main.Id,main.CreateDatetime,main.DistributionUser,main.Num,
            main.CityId,ad.DistrictName,main.CompanyId,ac.CompanyName,
            main.DistrictId,addistrict.DistrictName as DDistrictName,main.DCompanyId,acc.CompanyName as DCompanyName,
            main.SaleDepartmentId,asd.SaleDepartmentName)
            from SuppliesDistribution main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveDistrict addistrict
                 ,ArchiveCompany acc,ArchiveSaleDepartment asd
            where main.CityId=ad.Id and main.CompanyId=ac.Id and main.DistrictId=addistrict.Id and 
                  main.DCompanyId=acc.Id and main.SaleDepartmentId=asd.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesDistribution> lsDistribution = distributionRepository.FindByPage(pageNo, pageSize, hql);
            int recordCount = distributionRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsDistribution) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
