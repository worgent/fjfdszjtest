using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.RegisterManger.Models;
using QzgfFrame.Supplies.RegisterManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.BuildWayManger.Domain;
using QzgfFrame.Archives.BuildWayManger.Models;
using QzgfFrame.Archives.MaintainTypeManger.Domain;
using QzgfFrame.Archives.MaintainTypeManger.Models;
using QzgfFrame.Archives.CommunityTypeManger.Domain;
using QzgfFrame.Archives.CommunityTypeManger.Models;
using QzgfFrame.Supplies.CommunityInfoManger.Models;
using QzgfFrame.Supplies.CommunityInfoManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Supplies.RegisterManger.Domain
{
    public class RegisterFacadeImpl : RegisterFacade
    {
        private IRepository<SuppliesRegister> registerRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveSaleDepartment> saleDepartmentRepository { set; get; }
        private IRepository<ArchiveMaintainType> maintainTypeRepository { set; get; }
        private IRepository<ArchiveCommunityType> communityTypeRepository { set; get; }
        private IRepository<ArchiveBuildWay> buildWayRepository { set; get; }
        private IRepository<ArchiveAccessWay> accessWayRepository { set; get; }
        private IRepository<SuppliesCommunityInfo> communityInfoRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesRegister Get(object id)
        {
            string hql =
            @"select new SuppliesRegister(main.Id,main.Num,main.PBOSSJobNo,main.MaintainerId,sm.FullName as Maintainer ,main.CommunityName,main.CommunityCode,
              main.Address,main.UserName,main.PayNumber,main.CreateDatetime, main.Remark,           
              main.CompanyId,main.DistrictId,main.SaleDepartmentId,main.CommunityTypeId,main.MaintainTypeId,
              main.BuildWayId,main.AccessWayId,ad.DistrictName,ac.CompanyName,asd.SaleDepartmentName,
              act.CommunityTypeName,amt.MaintainTypeName,abw.BuildWayName,aaw.AccessWayName,main.MaintainDatetime,main.HandleTime)
            from SuppliesRegister main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveSaleDepartment asd,
                 ArchiveCommunityType act,ArchiveMaintainType amt,ArchiveBuildWay abw,ArchiveAccessWay aaw
                ,SuppliesCommunityManager sm
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id and main.SaleDepartmentId=asd.Id and 
                  main.CommunityTypeId=act.Id and main.BuildWayId=abw.Id and main.MaintainTypeId=amt.Id
                 and main.AccessWayId=aaw.Id and sm.Id=main.MaintainerId";
            return registerRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        public SuppliesRegister GetHql(string PBOSSJobNo)
        {
            string hql = " upper(PBOSSJobNo)=upper('" + PBOSSJobNo.Trim() + "') and (DelFlag!=1 or DelFlag is null)";
            IList<SuppliesRegister> Registers = registerRepository.LoadAll("Id", hql);
            if (Registers != null)
            {
                if (Registers.Count > 0)
                    return Registers[0];
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
                result = registerRepository.Delete(s);
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
                string strsql = " CId='" + s + "' and RelationName='Register'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Register'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = registerRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Register'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = registerRepository.Update(hql);
                }
            }
            return result;
        }
        public bool Save(SuppliesRegister entity, string no)
        {
            entity.Id = registerRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
             //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Register";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Register";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SaleDepartment";
            sre3.ControllerName = "Register";
            sre3.MId = entity.Id;
            sre3.CId = entity.SaleDepartmentId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "CommunityManager";
            sre4.ControllerName = "Register";
            sre4.MId = entity.Id;
            sre4.CId = entity.MaintainerId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "MaintainType";
            sre5.ControllerName = "Register";
            sre5.MId = entity.Id;
            sre5.CId = entity.MaintainTypeId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            i++;
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "AccessWay";
            sre6.ControllerName = "Register";
            sre6.MId = entity.Id;
            sre6.CId = entity.AccessWayId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "BuildWay";
            sre7.ControllerName = "Register";
            sre7.MId = entity.Id;
            sre7.CId = entity.BuildWayId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "CommunityType";
            sre8.ControllerName = "Register";
            sre8.MId = entity.Id;
            sre8.CId = entity.CommunityTypeId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);
            result = registerRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesRegister entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Register'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Register";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Register";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SaleDepartment";
            sre3.ControllerName = "Register";
            sre3.MId = entity.Id;
            sre3.CId = entity.SaleDepartmentId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "CommunityManager";
            sre4.ControllerName = "Register";
            sre4.MId = entity.Id;
            sre4.CId = entity.MaintainerId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "MaintainType";
            sre5.ControllerName = "Register";
            sre5.MId = entity.Id;
            sre5.CId = entity.MaintainTypeId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            i++;
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "AccessWay";
            sre6.ControllerName = "Register";
            sre6.MId = entity.Id;
            sre6.CId = entity.AccessWayId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "BuildWay";
            sre7.ControllerName = "Register";
            sre7.MId = entity.Id;
            sre7.CId = entity.BuildWayId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "CommunityType";
            sre8.ControllerName = "Register";
            sre8.MId = entity.Id;
            sre8.CId = entity.CommunityTypeId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);

            result = registerRepository.Update(entity);
            return result;
        }

        public IList<SuppliesRegister> LoadAll()
        {
            return registerRepository.LoadAll();
        }
        public IList<SuppliesRegister> LoadAll(string order, string where)
        {
            return registerRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new SuppliesRegister(main.Id,main.Num,main.PBOSSJobNo,main.MaintainerId,sm.FullName as Maintainer ,main.CommunityName,main.CommunityCode,
              main.Address,main.UserName,main.PayNumber,main.CreateDatetime, main.Remark,           
              main.CompanyId,main.DistrictId,main.SaleDepartmentId,main.CommunityTypeId,main.MaintainTypeId,
              main.BuildWayId,main.AccessWayId,ad.DistrictName,ac.CompanyName,asd.SaleDepartmentName,
              act.CommunityTypeName,amt.MaintainTypeName,abw.BuildWayName,aaw.AccessWayName,main.MaintainDatetime)
            from SuppliesRegister main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveSaleDepartment asd,
                 ArchiveCommunityType act,ArchiveMaintainType amt,ArchiveBuildWay abw,ArchiveAccessWay aaw
                ,SuppliesCommunityManager sm
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id and main.SaleDepartmentId=asd.Id and 
                  main.CommunityTypeId=act.Id and main.BuildWayId=abw.Id and main.MaintainTypeId=amt.Id
                 and main.AccessWayId=aaw.Id and sm.Id=main.MaintainerId and (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<SuppliesRegister> ls = registerRepository.FindByPage(pageNo, pageSize, vSql);
            int recordCount = registerRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(ls) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        public IList<object[]> LoadAll(string strsql, string strYear, string strMonth)
        {
            string strhql = "";
            strhql += "select b.Id as aid, b.AccessWayName as AccessWayName";
            strhql += ",Sum(case when (c.MaintainTypeName='安装') then 1 else 0 end)as azNum";
            strhql += ",Sum(case when (c.MaintainTypeName='移机') then 1 else 0 end)as yjNum";
            strhql += " from  SuppliesRegister a,ArchiveAccessWay b,ArchiveMaintainType c";
            strhql += " where a.AccessWayId=b.Id and a.MaintainTypeId=c.Id ";
            strhql += " and "+strsql;
            strhql += " and   to_char(a.CreateDatetime,'YYYY')='" + strYear + "' and to_char(a.CreateDatetime,'MM')='" + strMonth + "'";

            strhql += " Group BY b.Id, b.AccessWayName";
            strhql += " order by b.Id";
            return this.registerRepository.LoadAllObj(strhql);
        }
        /// <summary>
        /// 按接入方式统计
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindJLByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select scm.FullName,main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,main.SaleDepartmentId,asd.SaleDepartmentName,
            Sum(case when (aaw.AccessWayName='ADSL') then 1 else 0 end) as ADSLNum,
            Sum(case when (aaw.AccessWayName='FTTH') then 1 else 0 end) as FTTHNum,scm.Id
            from SuppliesRegister main,ArchiveCompany ac,ArchiveDistrict ad,ArchiveAccessWay aaw,
                 SuppliesCommunityManager scm,ArchiveSaleDepartment asd
            where main.CompanyId=ac.Id and main.DistrictId=ad.Id and  main.SaleDepartmentId=asd.Id 
                 and main.CompanyId=scm.CompanyId and main.DistrictId=scm.DistrictId and main.SaleDepartmentId=scm.SaleDepartmentId
                 and main.AccessWayId=aaw.Id and (main.DelFlag!=1 or main.DelFlag is null) and (scm.DelFlag!=1 or scm.DelFlag is null)
                 and scm.Id=main.MaintainerId";
            string vSql = hql + gridsearch;
            vSql += @" group by scm.FullName,main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,main.SaleDepartmentId,
                     asd.SaleDepartmentName,scm.Id ";
            vSql += @" order by scm." + sortname + " " + sortorder;
            IList<object[]> ls = registerRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new
                {
                    FullName = ExtensionMethods.ToStr(ls[i][0]),
                    CompanyId = ExtensionMethods.ToStr(ls[i][1]),
                    CompanyName = ExtensionMethods.ToStr(ls[i][2]),
                    DistrictId = ExtensionMethods.ToStr(ls[i][3]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][4]),
                    SaleDepartmentId = ExtensionMethods.ToStr(ls[i][5]),
                    SaleDepartmentName = ExtensionMethods.ToStr(ls[i][6]),
                    ADSLNum = ExtensionMethods.ToInt(ls[i][7]),
                    FTTHNum = ExtensionMethods.ToInt(ls[i][8]),
                    Id = ExtensionMethods.ToInt(ls[i][9])
                });
            }
            int recordCount = registerRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(mapList) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 用户使用总量统计
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindTZByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select ac.CompanyName,ad.DistrictName,dep.Id,dep.SaleDepartmentName,detail.SuppliesTypeId,stype.SuppliesTypeName,stype.UnitName,sum(detail.Num)

           from  SuppliesRegister main,SuppliesRegisterDetail detail ,ArchiveSuppliesType stype,ArchiveSaleDepartment dep,
             ArchiveCompany ac,ArchiveDistrict ad
            where detail.RegisterId=main.Id  and stype.Id=detail.SuppliesTypeId and dep.Id=main.SaleDepartmentId
                 and main.CompanyId=ac.Id and main.DistrictId=ad.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            
            string vSql = hql + gridsearch;
            vSql += @" group by ac.CompanyName,ad.DistrictName,dep.Id,dep.SaleDepartmentName,detail.SuppliesTypeId,stype.SuppliesTypeName,stype.UnitName";
            vSql += @" order by detail.SuppliesTypeId " + sortorder;
            IList<object[]> ls = registerRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new
                {
                    CompanyName = ExtensionMethods.ToStr(ls[i][0]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][1]),
                    SaleDepartmentId = ExtensionMethods.ToStr(ls[i][2]),
                    SaleDepartmentName = ExtensionMethods.ToStr(ls[i][3]),
                    SuppliesTypeId = ExtensionMethods.ToStr(ls[i][4]),
                    SuppliesTypeName = ExtensionMethods.ToStr(ls[i][5]),
                    UnitName = ExtensionMethods.ToStr(ls[i][6]),
                    Num = ExtensionMethods.ToInt(ls[i][7])
                });
            }
            int recordCount = registerRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(mapList) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 用户使用总量统计
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindPJByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select ac.CompanyName,ad.DistrictName,dep.Id,dep.SaleDepartmentName,detail.SuppliesTypeId,stype.SuppliesTypeName,stype.UnitName,sum(detail.Num),sum(1)

           from  SuppliesRegister main,SuppliesRegisterDetail detail ,ArchiveSuppliesType stype,ArchiveSaleDepartment dep,
             ArchiveCompany ac,ArchiveDistrict ad
            where detail.RegisterId=main.Id  and stype.Id=detail.SuppliesTypeId and dep.Id=main.SaleDepartmentId
                 and main.CompanyId=ac.Id and main.DistrictId=ad.Id and (main.DelFlag!=1 or main.DelFlag is null)";

            string vHql = hql + gridsearch;
            vHql += @" group by ac.CompanyName,ad.DistrictName,dep.Id,dep.SaleDepartmentName,detail.SuppliesTypeId,stype.SuppliesTypeName,stype.UnitName";
            vHql += @" order by detail.SuppliesTypeId " + sortorder;
            IList<object[]> ls = registerRepository.FindByLinkPage(pageNo, pageSize, vHql);
            /**
            string sql=
            @"select detail.SuppliesTypeId,sum(1)
             from  SuppliesRegister main,SuppliesRegisterDetail detail
             where main.Id=detail.RegisterId ";
            string vSql = sql + gridsearch;
            vSql += @" group by detail.SuppliesTypeId";
            IList<object[]> lsstyle = registerRepository.LoadAllObj(vSql);
             * **/
            /**
            IList numList = new ArrayList();
            for (int i = 0; i < lsstyle.Count; i++)
            {
                numList.Add(new
                {
                    SuppliesTypeId = ExtensionMethods.ToStr(lsstyle[i][0]),
                    Num = ExtensionMethods.ToInt(lsstyle[i][2])
                });
            }
            **/
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new
                {
                    CompanyName = ExtensionMethods.ToStr(ls[i][0]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][1]),
                    SaleDepartmentId = ExtensionMethods.ToStr(ls[i][2]),
                    SaleDepartmentName = ExtensionMethods.ToStr(ls[i][3]),
                    SuppliesTypeId = ExtensionMethods.ToStr(ls[i][4]),
                    SuppliesTypeName = ExtensionMethods.ToStr(ls[i][5]),
                    UnitName = ExtensionMethods.ToStr(ls[i][6]),
                    Num = ExtensionMethods.ToInt(ls[i][7]),
                    ZNum = ExtensionMethods.ToInt(ls[i][8]),
                    AVGNum = ExtensionMethods.ToDouble((ExtensionMethods.ToInt(ls[i][7]) / ExtensionMethods.ToInt(ls[i][8])))
                });
            }
            /**
            var jsonlist = (from vlsmapList in ls
                            join vlsnumList in lsstyle
                               on ExtensionMethods.ToStr(vlsmapList[4]) equals ExtensionMethods.ToStr(vlsnumList[0]) into joinvlsmapListnumList
                            from vlsMapNumList in joinvlsmapListnumList.DefaultIfEmpty()
                          
                            select new
                           {
                               CompanyName = ExtensionMethods.ToStr(vlsmapList[0]),
                               DistrictName = ExtensionMethods.ToStr(vlsmapList[1]),
                               SaleDepartmentId = ExtensionMethods.ToStr(vlsmapList[2]),
                               SaleDepartmentName = ExtensionMethods.ToStr(vlsmapList[3]),
                               SuppliesTypeId = ExtensionMethods.ToStr(vlsmapList[4]),
                               SuppliesTypeName = ExtensionMethods.ToStr(vlsmapList[5]),
                               UnitName = ExtensionMethods.ToStr(vlsmapList[6]),
                               Num = ExtensionMethods.ToInt(vlsmapList[7]),
                               ZNum = vlsMapNumList != null ? ExtensionMethods.ToInt(vlsMapNumList[1]) : 0,
                               AVGNum =((ExtensionMethods.ToInt(vlsmapList[7]))/(vlsMapNumList != null ? ExtensionMethods.ToInt(vlsMapNumList[1]) : 0))
                           }
                           ).OrderBy(m => m.SuppliesTypeId).ToArray();
             * **/
            int recordCount = registerRepository.FindByPageLinkCount(vHql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(mapList) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
