using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.EquipmentManger.Domain
{
    public class EquipmentFacadeImpl : EquipmentFacade
    {
        private IRepository<ResourceEquipment> equipRepository { set; get; }
        private IRepository<ArchiveEquipModel> equipModelRepository { set; get; }
        private IRepository<ArchiveEquipType> equipTypeRepository { set; get; }
        private IRepository<ArchiveFactory> factoryRepository { set; get; }
        private IRepository<ResourceGroupClie> clieRepository { set; get; }
        private IRepository<ResourceClieEquip> clieEquipRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourceEquipment Get(object id)
        {
            return equipRepository.Get(id.ToString());
        }
        public ResourceEquipment GetHql(string EquipName)
        {
            string hql =
              @"select new ResourceEquipment(main.Id,main.EquipName,clieEquip.OccupyPort as OccupyPort)
            from ResourceEquipment main,ResourceClieEquip clieEquip,ResourceGroupClie clie
            where clie.Id=clieEquip.ClieId and clieEquip.EquipId=main.Id and (main.DelFlag!=1 or main.DelFlag is null)";

            return equipRepository.GetbyHql(hql + " and upper(main.EquipName)=upper('" + EquipName + "')");            
        }
        public ResourceEquipment GetHql(string EquipName,string ClieId)
        {
            string hql =
              @"select new ResourceEquipment(main.Id,main.EquipName,clieEquip.OccupyPort as OccupyPort)
            from ResourceEquipment main,ResourceClieEquip clieEquip,ResourceGroupClie clie
            where clie.Id=clieEquip.ClieId and clieEquip.EquipId=main.Id and (main.DelFlag!=1 or main.DelFlag is null)";

            return equipRepository.GetbyHql(hql + " and upper(main.EquipName)=upper('" + EquipName + "') and clieEquip.ClieId='" + ClieId + "'");
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
                result = equipRepository.Delete(s);
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
                result = equipRepository.Update(hql);
            }
            return result;
        }

        public bool Save(ResourceEquipment entity,string no)
        {
            entity.Id = equipRepository.NewSequence("SYSTEM_MENU", no);
            entity.CreateDate = DateTime.Now;
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Equipment";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Equipment";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "EquipType";
            sre3.ControllerName = "Equipment";
            sre3.MId = entity.Id;
            sre3.CId = entity.EquipTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "Factory";
            sre4.ControllerName = "Equipment";
            sre4.MId = entity.Id;
            sre4.CId = entity.FactoryId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "NetWorkingMode";
            sre5.ControllerName = "Equipment";
            sre5.MId = entity.Id;
            sre5.CId = entity.NetWorkingModeId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            i++;

            result = equipRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceEquipment entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Equipment'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Equipment";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Equipment";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "EquipType";
            sre3.ControllerName = "Equipment";
            sre3.MId = entity.Id;
            sre3.CId = entity.EquipTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "Factory";
            sre4.ControllerName = "Equipment";
            sre4.MId = entity.Id;
            sre4.CId = entity.FactoryId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "NetWorkingMode";
            sre5.ControllerName = "Equipment";
            sre5.MId = entity.Id;
            sre5.CId = entity.NetWorkingModeId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            result = equipRepository.Update(entity);
            return result;
        }

        public IList<ResourceEquipment> LoadAll()
        {
            return equipRepository.LoadAll();
        }
        public IList<ResourceEquipment> LoadAll(string order, string where)
        {
            return equipRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "select distinct main.Id,main.EquipName,main.Position,main.StartDatetime,factory.Abbrevia,";
            hql += "equipType.EquipTypeName,main.EquipModelName,main.State  ";
            hql += " from ResourceEquipment main,ArchiveFactory factory ,ArchiveEquipType equipType, ResourceClieEquip clieEquip, ResourceGroupClie clie";
            hql += " where main.FactoryId=factory.Id and main.EquipTypeId=equipType.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += " and main.Id=clieEquip.EquipId and clie.Id=clieEquip.ClieId  and (clie.DelFlag!=1 or clie.DelFlag is null)";
           string vSql = hql + gridsearch;
           vSql +=  @" order by main." + sortname + " " + sortorder;

           IList<object[]> ls = equipRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<Equipment> Equipments = new List<Equipment>();
            for (int i = 0; i < ls.Count; i++)
            {
                Equipment Equipment = new Equipment();
                Equipment.Id = ls[i][0].ToString();
                Equipment.EquipName = ExtensionMethods.ToStr(ls[i][1]);
                Equipment.Position = ExtensionMethods.ToShort(ls[i][2]);
                Equipment.StartDatetime = ExtensionMethods.ToDateNull(ls[i][3]);
                Equipment.Abbrevia = ExtensionMethods.ToStr(ls[i][4]);
                Equipment.EquipTypeName = ExtensionMethods.ToStr(ls[i][5]);
                Equipment.EquipModelName = ExtensionMethods.ToStr(ls[i][6]);
                Equipment.State = ExtensionMethods.GetEquipState(ExtensionMethods.ToShort(ls[i][7]));
                IList<object[]> ClieEquip =
                clieEquipRepository.LoadAllObj("select rce.Id,rgc.ClieName,rgc.ClieNo,rgc.Id from ResourceClieEquip rce,ResourceGroupClie rgc  where rgc.Id=rce.ClieId and rce.EquipId='" + ls[i][0].ToString() + "'");
                string clienames = "";
                foreach (var vls in ClieEquip)
                {
                    clienames += vls[1].ToString() + ";";
                }
                if (clienames != "")
                    clienames = clienames.Substring(0, clienames.Length - 1);
                Equipment.ClieNames = clienames;
                Equipments.Add(Equipment);
            }
            string rowsjson = JSONHelper.ToJSON(Equipments);
            int recordCount = equipRepository.FindByPageLinkCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        public string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select main.Id,main.EquipName,main.Position,main.StartDatetime,factory.Abbrevia,
             equipType.EquipTypeName,main.EquipModelName,main.State,clie.ClieNo,clie.ClieName,ac.CompanyName,
             ad.DistrictName,clieEquip.ClieId,clieEquip.OccupyPort
             from ResourceEquipment main,ArchiveFactory factory ,ArchiveEquipType equipType, ResourceClieEquip clieEquip,
                  ResourceGroupClie clie,ArchiveCompany ac,ArchiveDistrict ad
            where main.FactoryId=factory.Id and main.EquipTypeId=equipType.Id and main.CompanyId=ac.Id and main.DistrictId=ad.Id
            and (main.DelFlag!=1 or main.DelFlag is null) and (clieEquip.DelFlag!=1 or clieEquip.DelFlag is null)
            and main.Id=clieEquip.EquipId and clie.Id=clieEquip.ClieId  and (clie.DelFlag!=1 or clie.DelFlag is null)";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = equipRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new
                {
                    Id = ls[i][0].ToString(),
                    EquipName = ExtensionMethods.ToStr(ls[i][1]),
                    Position = ExtensionMethods.ToShort(ls[i][2]),
                    StartDatetime = ExtensionMethods.ToDateNull(ls[i][3]),
                    Abbrevia = ExtensionMethods.ToStr(ls[i][4]),
                    EquipTypeName = ExtensionMethods.ToStr(ls[i][5]),
                    EquipModelName = ExtensionMethods.ToStr(ls[i][6]),
                    State = ExtensionMethods.GetEquipState(ExtensionMethods.ToShort(ls[i][7])),
                    ClieNo = ExtensionMethods.ToStr(ls[i][8]),
                    ClieName = ExtensionMethods.ToStr(ls[i][9]),
                    CompanyName = ExtensionMethods.ToStr(ls[i][10]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][11]),
                    ClieId = ExtensionMethods.ToStr(ls[i][12]),
                    OccupyPort = ExtensionMethods.ToStr(ls[i][13])
                });
            }
            string rowsjson = JSONHelper.ToJSON(mapList);
            int recordCount = equipRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
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
            bool result = false; DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='Equipment'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " from SystemRelation where  MId='" + s + "' and ControllerName='Equipment'";
                    result = relationRepository.DeletebyHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = equipRepository.Update(hql);
                    //删除客户设备表数据
                    string shql = " EquipId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='ClieEquip'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceClieEquip> ces = clieEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceClieEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='ClieEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        }
                        result = clieEquipRepository.Update(" DelFlag='1' where  " + shql);
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceClieEquip> ces = clieEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceClieEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='ClieEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        } 
                        result = clieEquipRepository.Update(" DelFlag='1' where  " + shql);
                    }
                }
                else if (sres.Count == 0)
                {
                    string sql = " from SystemRelation where   MId='" + s + "' and ControllerName='Equipment'";
                    result = relationRepository.DeletebyHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = equipRepository.Update(hql);
                    //删除客户设备表数据
                    string shql = " EquipId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='ClieEquip'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceClieEquip> ces = clieEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceClieEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='ClieEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        } 
                        result = clieEquipRepository.Update(" DelFlag='1' where  " + shql);
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceClieEquip> ces = clieEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceClieEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='ClieEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                        } 
                        result = clieEquipRepository.Update(" DelFlag='1' where  " + shql);
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
            string hql =
           @" from tab_Equipment main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District ad on ad.Id=main.DistrictId 
                                      left join tab_Equip_Type etype on etype.Id=main.EquipTypeId  
                                      left join tab_Factory factory on factory.Id=main.FactoryId  
                                      left join tab_clie_equip cliequip on cliequip.EquipId=main.Id
                                      left join tab_Group_Clie clie on clie.Id=   cliequip.ClieId
                                      left join tab_networking_mode netmode on netmode.Id=main.NetWorkingModeId 
                                      left join tab_Port_Type ptype on ptype.Id=cliequip.PortTypeId 
            where  (main.DelFlag!=1 or main.DelFlag is null) and (clie.DelFlag!=1 or clie.DelFlag is null) and clie.Id is not null
                   and (cliequip.DelFlag!=1 or cliequip.DelFlag is null) ";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = equipRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
