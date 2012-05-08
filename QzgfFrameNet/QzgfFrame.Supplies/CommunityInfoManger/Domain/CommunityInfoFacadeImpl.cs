using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.CommunityInfoManger.Models;
using QzgfFrame.Supplies.CommunityInfoManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.CommunityInfoManger.Domain
{
    public class CommunityInfoFacadeImpl : CommunityInfoFacade
    {
        private IRepository<SuppliesCommunityInfo> communityInfoRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesCommunityInfo Get(object id)
        {
            return communityInfoRepository.Get(id.ToString());
        }
        public SuppliesCommunityInfo GetHql(string CommunityCode)
        {
            string hql = " upper(CommunityCode)=upper('" + CommunityCode + "') and (DelFlag!=1 or DelFlag is null)";
            IList<SuppliesCommunityInfo> CommunityInfos = communityInfoRepository.LoadAll("Id", hql);
            if (CommunityInfos != null)
            {
                if (CommunityInfos.Count > 0)
                    return CommunityInfos[0];
                else
                    return null;
            }
            else return null;
        }
        public SuppliesCommunityInfo GetSql(object CommunityCode)
        {
            string hql =
               @"select new SuppliesCommunityInfo(main.Id,main.CommunityCode,main.CommunityName,main.AccessWayId,aw.AccessWayName,
            main.BuildWayId,bw.BuildWayName,main.CommunityTypeId,ct.CommunityTypeName)
                      from SuppliesCommunityInfo main,ArchiveAccessWay aw,ArchiveCommunityType ct,ArchiveBuildWay bw
            where main.AccessWayId=aw.Id and main.CommunityTypeId=ct.Id and main.BuildWayId=bw.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";

            string strHql = hql + " and main.CommunityCode = '" + CommunityCode + "'";
            return communityInfoRepository.GetbyHql(strHql);
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
                string strsql = " CId='" + s + "' and RelationName='CommunityInfo'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityInfo'";
                    result = relationRepository.DeleteHql(sql);
                    result = communityInfoRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityInfo'";
                    result = relationRepository.DeleteHql(sql);
                    result = communityInfoRepository.Delete(s);
                }   
            }
            return result;
        }

        public bool Save(SuppliesCommunityInfo entity, string no)
        {
            entity.Id = communityInfoRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;            
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "AccessWay";
            sre6.ControllerName = "CommunityInfo";
            sre6.MId = entity.Id;
            sre6.CId = entity.AccessWayId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "BuildWay";
            sre7.ControllerName = "CommunityInfo";
            sre7.MId = entity.Id;
            sre7.CId = entity.BuildWayId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "CommunityType";
            sre8.ControllerName = "CommunityInfo";
            sre8.MId = entity.Id;
            sre8.CId = entity.CommunityTypeId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);

            result = communityInfoRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesCommunityInfo entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='CommunityInfo'");
            //添加关系信息
            //添加关系信息
            int i = 0;
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "AccessWay";
            sre6.ControllerName = "CommunityInfo";
            sre6.MId = entity.Id;
            sre6.CId = entity.AccessWayId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            SystemRelation sre7 = new SystemRelation();
            sre7.RelationName = "BuildWay";
            sre7.ControllerName = "CommunityInfo";
            sre7.MId = entity.Id;
            sre7.CId = entity.BuildWayId;
            sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre7);
            i++;
            SystemRelation sre8 = new SystemRelation();
            sre8.RelationName = "CommunityType";
            sre8.ControllerName = "CommunityInfo";
            sre8.MId = entity.Id;
            sre8.CId = entity.CommunityTypeId;
            sre8.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre8);
            result = communityInfoRepository.Update(entity);
            return result;
        }

        public IList<SuppliesCommunityInfo> LoadAll()
        {
            return communityInfoRepository.LoadAll();
        }
        public IList<SuppliesCommunityInfo> LoadAll(string where)
        {
            string hql =
              @"select new SuppliesCommunityInfo(main.Id,main.CommunityCode,main.CommunityName,main.AccessWayId,aw.AccessWayName,
            main.BuildWayId,bw.BuildWayName,main.CommunityTypeId,ct.CommunityTypeName)
                      from SuppliesCommunityInfo main,ArchiveAccessWay aw,ArchiveCommunityType ct,ArchiveBuildWay bw
            where main.AccessWayId=aw.Id and main.CommunityTypeId=ct.Id and main.BuildWayId=bw.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += where;
            hql += " order by main.Id";
            return communityInfoRepository.LoadAllbyHql(hql);
        }
        public IList<SuppliesCommunityInfo> LoadAll(string order, string where)
        {
            return communityInfoRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
               @"select new SuppliesCommunityInfo(main.Id,main.CommunityCode,main.CommunityName,main.AccessWayId,aw.AccessWayName,
            main.BuildWayId,bw.BuildWayName,main.CommunityTypeId,ct.CommunityTypeName)
                      from SuppliesCommunityInfo main,ArchiveAccessWay aw,ArchiveCommunityType ct,ArchiveBuildWay bw
            where main.AccessWayId=aw.Id and main.CommunityTypeId=ct.Id and main.BuildWayId=bw.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesCommunityInfo> ls = communityInfoRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = communityInfoRepository.FindByPageCount(hql);
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
                string strsql = " CId='" + s + "' and RelationName='CommunityInfo'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityInfo'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = communityInfoRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityInfo'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = communityInfoRepository.Update(hql);
                }   
            }
            return result;
        }
    }
}
