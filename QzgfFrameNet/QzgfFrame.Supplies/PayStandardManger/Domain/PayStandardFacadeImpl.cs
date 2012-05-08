using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.PayStandardManger.Models;
using QzgfFrame.Supplies.PayStandardManger.Domain;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.MaintainTypeManger.Domain;
using QzgfFrame.Archives.MaintainTypeManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.PayStandardManger.Domain
{
    public class PayStandardFacadeImpl : PayStandardFacade
    {
        private IRepository<SuppliesPayStandard> payStandardRepository { set; get; }
        private IRepository<ArchiveAccessWay> accessWayRepository { set; get; }
        private IRepository<ArchiveMaintainType> maintainTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesPayStandard Get(object id)
        {
            return payStandardRepository.Get(id.ToString());
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
                result = payStandardRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SuppliesPayStandard entity, string no)
        {
            entity.Id = payStandardRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "AccessWay";
            sre1.ControllerName = "PayStandard";
            sre1.MId = entity.Id;
            sre1.CId = entity.AccessWayId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "MaintainType";
            sre2.ControllerName = "PayStandard";
            sre2.MId = entity.Id;
            sre2.CId = entity.MaintainTypeId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = payStandardRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesPayStandard entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='PayStandard'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "AccessWay";
            sre1.ControllerName = "PayStandard";
            sre1.MId = entity.Id;
            sre1.CId = entity.AccessWayId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "MaintainType";
            sre2.ControllerName = "PayStandard";
            sre2.MId = entity.Id;
            sre2.CId = entity.MaintainTypeId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);

            result = payStandardRepository.Update(entity);
            return result;
        }

        public IList<SuppliesPayStandard> LoadAll()
        {
            return payStandardRepository.LoadAll();
        }
        public IList<SuppliesPayStandard> LoadAll(string order, string where)
        {
            return payStandardRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsPayStandard = payStandardRepository.LoadAllbyHql("from SuppliesPayStandard");
            var lsMaintainType = maintainTypeRepository.LoadAllbyHql("from ArchiveMaintainType");
            var lsAccessWay = accessWayRepository.LoadAllbyHql("from ArchiveAccessWay");

            var jsonlist = (from vlsPayStandard in lsPayStandard                            
                            join vlsMaintainType in lsMaintainType
                                on vlsPayStandard.MaintainTypeId equals vlsMaintainType.Id into joinvlsPayStandardMaintainType
                            from vlsPayStandardMaintainType in joinvlsPayStandardMaintainType.DefaultIfEmpty()

                            join vlsAccessWay in lsAccessWay
                                on vlsPayStandard.AccessWayId equals vlsAccessWay.Id into joinvlsPayStandardAccessWay
                            from vlsPayStandardAccessWay in joinvlsPayStandardAccessWay.DefaultIfEmpty()
                            select new
                            {
                                Id = vlsPayStandard.Id,
                                StartDate = vlsPayStandard.StartDate.Date,
                                EndDate = vlsPayStandard.EndDate.Date,
                                Pay = vlsPayStandard.Pay,
                                AccessWayName = vlsPayStandardAccessWay != null ? vlsPayStandardAccessWay.AccessWayName : "",
                                MaintainTypeName = vlsPayStandardMaintainType != null ? vlsPayStandardMaintainType.MaintainTypeName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }
    }
}
