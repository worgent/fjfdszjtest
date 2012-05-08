using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.CommunityManagerManger.Domain
{
    public class CommunityManagerFacadeImpl : CommunityManagerFacade
    {
        private IRepository<SuppliesCommunityManager> communityManagerRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesCommunityManager Get(object id)
        {
            return communityManagerRepository.Get(id.ToString());
        }
        public SuppliesCommunityManager GetHql(string IDCardNo)
        {
            string hql = " upper(IDCardNumber)=upper('" + IDCardNo + "') and (DelFlag!=1 or DelFlag is null)";
            IList<SuppliesCommunityManager> CommunityManagers = communityManagerRepository.LoadAll("Id", hql);
            if (CommunityManagers != null)
            {
                if (CommunityManagers.Count > 0)
                    return CommunityManagers[0];
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
                result = communityManagerRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SuppliesCommunityManager entity, string no)
        {
            entity.Id = communityManagerRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "CommunityManager";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "CommunityManager";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SaleDepartment";
            sre3.ControllerName = "CommunityManager";
            sre3.MId = entity.Id;
            sre3.CId = entity.SaleDepartmentId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            result = communityManagerRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesCommunityManager entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='CommunityManager'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "CommunityManager";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "CommunityManager";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SaleDepartment";
            sre3.ControllerName = "CommunityManager";
            sre3.MId = entity.Id;
            sre3.CId = entity.SaleDepartmentId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            result = communityManagerRepository.Update(entity);
            return result;
        }

        public IList<SuppliesCommunityManager> LoadAll()
        {
            return communityManagerRepository.LoadAll();
        }
        public IList<SuppliesCommunityManager> LoadAll(string order, string where)
        {
            return communityManagerRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
             @"select new SuppliesCommunityManager(main.Id,main.FullName,main.IDCardNumber,main.LinkTel,main.EntryDate,
            main.IsFullTime,main.IsInService,main.CompanyId,main.SaleDepartmentId,main.DistrictId,ac.CompanyName,
            asd.SaleDepartmentName,ad.DistrictName)
            from SuppliesCommunityManager main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveSaleDepartment asd
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id and main.SaleDepartmentId=asd.Id and (main.DelFlag!=1 or main.DelFlag is null) ";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesCommunityManager> ls = communityManagerRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = communityManagerRepository.FindByPageCount(hql);
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
                string strsql = " CId='" + s + "' and RelationName='CommunityManager'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityManager'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = communityManagerRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityManager'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = communityManagerRepository.Update(hql);
                }   
            }
            return result;
        }
        /// <summary>
        /// 装维人员下拉框
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox(string hql)
        {
            var ls = LoadAll("Id", hql); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.FullName,
                                id = a.Id,
                                sid = a.SaleDepartmentId
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
