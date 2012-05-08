using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.StagnationManger.Domain
{
    public class StagnationFacadeImpl : StagnationFacade
    {
        private IRepository<ArchiveStagnation> stagnationRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveStagnation Get(object id)
        {
            return stagnationRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='Stagnation'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Stagnation'";
                    result = relationRepository.DeleteHql(sql);
                    result = stagnationRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Stagnation'";
                    result = relationRepository.DeleteHql(sql);
                    result = stagnationRepository.Delete(s);
                }
                else
                    DelFlag = true;   
            }
            return result;
        }
        public ArchiveStagnation GetHql(string stagnationName)
        {
            string Hql = " StagnationName = '"+stagnationName+"'";
            IList<ArchiveStagnation> stagnations = stagnationRepository.LoadAll("Id", Hql);
            if (stagnations != null)
            {
                if (stagnations.Count > 0)
                    return stagnations[0];
                else return null;
            }
            return null;
        }
        public ArchiveStagnation GetHql(string stagnationName,string strhql)
        {
            string Hql = " StagnationName = '" + stagnationName + "'";
            Hql += strhql;
            IList<ArchiveStagnation> stagnations = stagnationRepository.LoadAll("Id", Hql);
            if (stagnations != null)
            {
                if (stagnations.Count > 0)
                    return stagnations[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveStagnation entity)
        {
            entity.Id = stagnationRepository.NewSequence("SYSTEM_MENU");
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Stagnation";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Stagnation";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = stagnationRepository.Save(entity);
            return result;
        }

        public bool Update(ArchiveStagnation entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Stagnation'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Stagnation";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Stagnation";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = stagnationRepository.Update(entity);
            return result;
        }

        public IList<ArchiveStagnation> LoadAll()
        {
            return stagnationRepository.LoadAll();
        }
        public IList<ArchiveStagnation> LoadAll(string order, string where)
        {
            return stagnationRepository.LoadAll(order, where);
        }
        public IList<ArchiveStagnation> LoadLAll(string hql)
        {
            if (hql != "")
                hql = " from ArchiveStagnation where " + hql;
            else
                hql = " from ArchiveStagnation ";
            hql += @" order by Id "; var lsStagnation = stagnationRepository.LoadAllbyHql(hql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            IList<ArchiveStagnation> jsonlist = (from vlsStagnation in lsStagnation
                                           join vlsDistrict in lsDistrict
                                               on vlsStagnation.DistrictId equals vlsDistrict.Id into joinvlsStagnationDistrict
                                           from vlsStagnationDistrict in joinvlsStagnationDistrict.DefaultIfEmpty()
                                                 select new ArchiveStagnation
                                           {
                                               Id = vlsStagnation.Id,
                                               StagnationName = vlsStagnation.StagnationName,
                                               CompanyId = vlsStagnation.CompanyId,
                                               DistrictId = vlsStagnation.DistrictId,
                                               CityId = vlsStagnationDistrict != null ? vlsStagnationDistrict.ParentId : ""
                                           }
                           ).OrderBy(m => m.Id).ToArray();
            return jsonlist;
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveStagnation";
            IList<ArchiveStagnation> ls = stagnationRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = stagnationRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
