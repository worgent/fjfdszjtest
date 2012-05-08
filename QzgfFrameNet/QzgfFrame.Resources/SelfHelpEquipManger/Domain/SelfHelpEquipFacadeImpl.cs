using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Resources.EquipComponentManger.Models;
using QzgfFrame.Resources.EquipComponentManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.SelfHelpEquipManger.Domain
{
    public class SelfHelpEquipFacadeImpl : SelfHelpEquipFacade
    {
        private IRepository<ResourceSelfHelpEquip> selfHelpEquipRepository { set; get; }
        private IRepository<ArchiveSelfHelpEquipModel> selfHelpEquipModelRepository { set; get; }
        private IRepository<ArchiveSelfHelpEquipType> selfHelpEquipTypeRepository { set; get; }
        private IRepository<ArchiveSelfHelpFactory> selfHelpFactoryRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }
        private IRepository<ResourceEquipComponent> equipComponentRepository { set; get; }

        public ResourceSelfHelpEquip Get(object id)
        {
            return selfHelpEquipRepository.Get(id.ToString());
        }
        public ResourceSelfHelpEquip GetHql(object TermiId)
        {
            string Hql = " TermiId = '" + TermiId + "'  and (DelFlag!=1 or DelFlag is null)";
                IList<ResourceSelfHelpEquip> entitys = selfHelpEquipRepository.LoadAll("Id", Hql);
                if (entitys != null)
                {
                    if (entitys.Count > 0)
                        return entitys[0];
                    else return null;
                }
                else
                    return null;
        }

        public ResourceSelfHelpEquip GetSql(object TermiId)
        {
            string hql =
               @"select new ResourceSelfHelpEquip(main.Id,main.TermiId,main.DistrictId,ad.DistrictName,main.UseNetNo,
            main.UseNetName,main.FactoryId,asf.Abbrevia as FactoryName,main.EquipModelName)
                      from ResourceSelfHelpEquip main,ArchiveSelfHelpFactory asf,ArchiveDistrict ad
            where main.DistrictId=ad.Id and main.FactoryId=asf.Id and (main.DelFlag!=1 or main.DelFlag is null)";

            string strHql =hql+ " and TermiId = '" + TermiId + "'  and (DelFlag!=1 or DelFlag is null)";
            return selfHelpEquipRepository.GetbyHql(strHql);
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = selfHelpEquipRepository.Delete(s);
            }
            return result;
        }
        /// <summary>
        /// 同时退网多行记录,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Quit(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string hql = " State='1' where Id='" + s + "'";
                result = selfHelpEquipRepository.Update(hql);
            }
            return result;
        }

        public bool Save(ResourceSelfHelpEquip entity, string no)
        {
            bool result = false;
            
             entity.Id = selfHelpEquipRepository.NewSequence("SYSTEM_MENU",no);
                entity.CreateDate = DateTime.Now;
                DateTime dt = DateTime.Now;
                DateTime butdt = Convert.ToDateTime(entity.BuyDatetime).AddYears(entity.Life);
                if (DateTime.Compare(dt, butdt) > 0)
                {
                    entity.IsOverInsuran = 1;
                }
                else
                    entity.IsOverInsuran = 0;
                //添加关系信息
                int i = 0;
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "SelfHelpFactory";
                sre1.ControllerName = "SelfHelpEquip";
                sre1.MId = entity.Id;
                sre1.CId = entity.FactoryId;
                sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre1);
                i++;
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "SelfHelpEquip";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;

                SystemRelation sre3 = new SystemRelation();
                sre3.RelationName = "SelfHelpEquipType";
                sre3.ControllerName = "SelfHelpEquip";
                sre3.MId = entity.Id;
                sre3.CId = entity.EquipTypeId;
                sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre3);
                i++;
                SystemRelation sre4 = new SystemRelation();
                sre4.RelationName = "OutletsType";
                sre4.ControllerName = "SelfHelpEquip";
                sre4.MId = entity.Id;
                sre4.CId = entity.NetType;
                sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre4);

                result = selfHelpEquipRepository.Save(entity);
                return result;
        }

        public bool Update(ResourceSelfHelpEquip entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='SelfHelpEquip'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "SelfHelpFactory";
            sre1.ControllerName = "SelfHelpEquip";
            sre1.MId = entity.Id;
            sre1.CId = entity.FactoryId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "SelfHelpEquip";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;

            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SelfHelpEquipType";
            sre3.ControllerName = "SelfHelpEquip";
            sre3.MId = entity.Id;
            sre3.CId = entity.EquipTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "OutletsType";
            sre4.ControllerName = "SelfHelpEquip";
            sre4.MId = entity.Id;
            sre4.CId = entity.NetType;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
             return selfHelpEquipRepository.Update(entity);
        }

        public IList<ResourceSelfHelpEquip> LoadAll()
        {
            return selfHelpEquipRepository.LoadAll();
        }
        public IList<ResourceSelfHelpEquip> LoadAll(string order, string where)
        {
            return selfHelpEquipRepository.LoadAll(order, where);
        }
        public IList<ResourceSelfHelpEquip> LoadAll(string where)
        {
            string hql =
            @"select new ResourceSelfHelpEquip(main.Id,main.TermiId,main.DistrictId,ad.DistrictName,main.UseNetNo,
            main.UseNetName,main.FactoryId,asf.Abbrevia as FactoryName,main.EquipModelName)
                      from ResourceSelfHelpEquip main,ArchiveSelfHelpFactory asf,ArchiveDistrict ad
            where main.DistrictId=ad.Id and main.FactoryId=asf.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += where;
            hql += " order by main.Id";
            return selfHelpEquipRepository.LoadAllbyHql(hql);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourceSelfHelpEquip main where (main.DelFlag!=1 or main.DelFlag is null) ";
            hql +=gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var lsSelfHelpEquip = selfHelpEquipRepository.FindByPage(pageNo, pageSize, vSql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsType = selfHelpEquipTypeRepository.LoadAllbyHql("from ArchiveSelfHelpEquipType");
            var lsFactory = selfHelpFactoryRepository.LoadAllbyHql("from ArchiveSelfHelpFactory");

            var jsonlist = (from vlsSelfHelpEquip in lsSelfHelpEquip
                            join vlsDistrict in lsDistrict
                                on vlsSelfHelpEquip.DistrictId equals vlsDistrict.Id into joinvlsSelfHelpEquipDistrict
                            from vlsSelfHelpEquipDistrict in joinvlsSelfHelpEquipDistrict.DefaultIfEmpty()

                            join vlsType in lsType
                                on vlsSelfHelpEquip.EquipTypeId equals vlsType.Id into joinvlsSelfHelpEquipType
                            from vlsSelfHelpEquipType in joinvlsSelfHelpEquipType.DefaultIfEmpty()

                            join vlsFactory in lsFactory
                                on vlsSelfHelpEquip.FactoryId equals vlsFactory.Id into joinvlsSelfHelpEquipFactory
                            from vlsSelfHelpEquipFactory in joinvlsSelfHelpEquipFactory.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsSelfHelpEquip.Id,
                                TermiId = vlsSelfHelpEquip.TermiId,
                                UseNetName = vlsSelfHelpEquip.UseNetName,
                                NetAddress = vlsSelfHelpEquip.NetAddress,
                                StartDatetime = vlsSelfHelpEquip.StartDatetime,
                                State = ExtensionMethods.GetEquipState(vlsSelfHelpEquip.State),
                                EquipModelName = vlsSelfHelpEquip.EquipModelName,
                                DistrictName = vlsSelfHelpEquipDistrict != null ? vlsSelfHelpEquipDistrict.DistrictName : "",
                                EquipTypeName = vlsSelfHelpEquipType != null ? vlsSelfHelpEquipType.SelfHelpEquipTypeName : "",
                                FactoryName = vlsSelfHelpEquipFactory != null ? vlsSelfHelpEquipFactory.Abbrevia : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = selfHelpEquipRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;  DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='SelfHelpEquip'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquip'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = selfHelpEquipRepository.Update(hql);
                    //删除自助设备部件关系表数据
                    string shql = " SelfHelpEquipId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='EquipComponent'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceEquipComponent> ces = equipComponentRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceEquipComponent ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='EquipComponent'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        }
                        result = equipComponentRepository.Update(" DelFlag='1' where  " + shql);
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceEquipComponent> ces = equipComponentRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceEquipComponent ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='EquipComponent'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        }
                        result = equipComponentRepository.Update(" DelFlag='1' where  " + shql);
                    }
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquip'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = selfHelpEquipRepository.Update(hql);
                    //删除自助设备部件关系表数据
                    string shql = " SelfHelpEquipId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='EquipComponent'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceEquipComponent> ces = equipComponentRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceEquipComponent ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='EquipComponent'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        }
                        result = equipComponentRepository.Update(" DelFlag='1' where  " + shql);
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceEquipComponent> ces = equipComponentRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceEquipComponent ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='EquipComponent'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        }
                        result = equipComponentRepository.Update(" DelFlag='1' where  " + shql);
                    }
                }
                else
                    DelFlag = true;
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
            var lsType = selfHelpEquipTypeRepository.LoadAllbyHql("from ArchiveSelfHelpEquipType");
            var lsFactory = selfHelpFactoryRepository.LoadAllbyHql("from ArchiveSelfHelpFactory");
            string hql =
           @" from tab_Self_Help_Equip main 
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_SelfHelp_Equip_Type etype on etype.Id=main.EquipTypeId 
                                      left join tab_SelfHelp_Factory factory on factory.Id=main.FactoryId   
                                      left join tab_outlets_type ntype on ntype.Id=main.NetType   
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = selfHelpEquipRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
