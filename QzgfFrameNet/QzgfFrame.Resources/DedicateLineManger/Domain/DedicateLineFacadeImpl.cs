using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.SignalModelManger.Models;
using QzgfFrame.Archives.SignalModelManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Resources.LineEquipManger.Models;
using QzgfFrame.Resources.LineEquipManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.NumberGroupManger.Domain;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.DedicateLineManger.Domain
{
    public class DedicateLineFacadeImpl : DedicateLineFacade
    {
        private IRepository<ResourceDedicateLine> lineRepository { set; get; }
        private IRepository<ResourceLineEquip> lineEquipRepository { set; get; }
        private IRepository<ResourceGroupClie> clieRepository { set; get; }
        private IRepository<ArchiveBizType> bizTypeRepository { set; get; }
        private IRepository<ArchiveNetworkingMode> modeRepository { set; get; }
        private IRepository<ArchiveSignalModel> signalModelRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }
        private IRepository<ResourceFiberCore> fiberCoreRepository { set; get; }
        private IRepository<ResourceNumberGroup> numberRepository { set; get; }

        public ResourceDedicateLine Get(object id)
        {
            return lineRepository.Get(id.ToString());
        }
        public DedicateLine GetLine(object id)
        {
            ResourceDedicateLine deline = lineRepository.GetbyHql("from ResourceDedicateLine main where main.Id='" + id.ToString() + "' and (main.DelFlag!=1 or main.DelFlag is null)");
            ResourceGroupClie clie = clieRepository.GetbyHql("from ResourceGroupClie main where  main.Id='" + deline.ClieId.ToString() + "' and (main.DelFlag!=1 or main.DelFlag is null)");
            
            DedicateLine DedicateLine = new DedicateLine();
            DedicateLine.DediLine = deline;
            DedicateLine.State = ExtensionMethods.GetLineState(deline.State);
            DedicateLine.ClieId = DedicateLine.DediLine.ClieId;
            DedicateLine.ZClieId = DedicateLine.DediLine.ZClieId;
            DedicateLine.ClieNo = clie.ClieNo;
            DedicateLine.ClieName = clie.ClieName;
            return DedicateLine;
        }
        public ResourceDedicateLine GetHql(string ProductNo)
        {
            string hql = " upper(ProductNo)=upper('" + ProductNo.Trim() + "') and (DelFlag!=1 or DelFlag is null)";
            IList<ResourceDedicateLine> dedicateLines = lineRepository.LoadAll("Id", hql);
            if (dedicateLines != null)
            {
                if (dedicateLines.Count > 0)
                    return dedicateLines[0];
                else
                    return null;
            }
            else return null;
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
                result = lineRepository.Delete(s);
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
                string hql = " State='2' where Id='" + s + "'";
                result = lineRepository.Update(hql);
            }
            return result;
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
                string strsql = " CId='" + s + "' and RelationName='DedicateLine'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='DedicateLine'";
                    result = relationRepository.DeleteHql(sql);

                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = lineRepository.Update(hql);

                    //删除专线设备表数据
                    string shql = " LineId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='LineEquip'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceLineEquip> ces = lineEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceLineEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='LineEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                            result = lineEquipRepository.Update(" DelFlag='1' where  " + shql);
                        } 
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceLineEquip> ces = lineEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceLineEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='LineEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                            result = lineEquipRepository.Update(" DelFlag='1' where  " + shql);
                        }
                    }
                    else
                        DelFlag = true;
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='DedicateLine'";
                    result = relationRepository.DeleteHql(sql);

                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = lineRepository.Update(hql);
                    //删除专线设备表数据
                    string shql = " LineId='" + s + "'";
                    string strhsql = " CId='" + s + "' and RelationName='LineEquip'";
                    IList<SystemRelation> sress = relationRepository.LoadAll("CId", strhsql);
                    if (sress == null)
                    {
                        IList<ResourceLineEquip> ces = lineEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceLineEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='LineEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                            result = lineEquipRepository.Update(" DelFlag='1' where  " + shql);
                        } 
                    }
                    else if (sress.Count == 0)
                    {
                        IList<ResourceLineEquip> ces = lineEquipRepository.LoadAll("Id", shql + " and  (DelFlag!=1 or DelFlag is null)");
                        string ssql = "(";
                        foreach (ResourceLineEquip ce in ces)
                        {
                            if (ssql == "(")
                                ssql += " MId='" + ce.Id + "'";
                            else
                                ssql += "  or MId='" + ce.Id + "'";
                        }
                        if (ssql != "(")
                        {
                            ssql += ") and  ControllerName='LineEquip'";
                            string Vsql = " from SystemRelation where " + ssql;
                            result = relationRepository.DeletebyHql(Vsql);
                            result = lineEquipRepository.Update(" DelFlag='1' where  " + shql);
                        }
                    }
                    else
                        DelFlag = true;
                }
                else
                    DelFlag = true;
            }
            return result;
        }
        public bool Save(ResourceDedicateLine entity)
        {
            bool result = false;
            entity.Id = lineRepository.NewSequence("SYSTEM_MENU");
            entity.CreateDate = DateTime.Now;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "DedicateLine";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "DedicateLine";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "NetWorkingMode";
            sre3.ControllerName = "DedicateLine";
            sre3.MId = entity.Id;
            sre3.CId = entity.NetWorkingModeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "BizAssuranLeve";
            sre4.ControllerName = "DedicateLine";
            sre4.MId = entity.Id;
            sre4.CId = entity.BizAssuranLeveId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "GroupClie";
            sre5.ControllerName = "DedicateLine";
            sre5.MId = entity.Id;
            sre5.CId = entity.ClieId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            i++;

            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "BizType";
            sre6.ControllerName = "DedicateLine";
            sre6.MId = entity.Id;
            sre6.CId = entity.BizTypeId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            if (entity.SignalModel != null && entity.SignalModel != "")
            {
                SystemRelation sre7 = new SystemRelation();
                sre7.RelationName = "BizType";
                sre7.ControllerName = "DedicateLine";
                sre7.MId = entity.Id;
                sre7.CId = entity.SignalModel;
                sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre7);
            }
            result = lineRepository.Save(entity);
            return result;
        }
        /**
        public bool Save(ResourceDedicateLine entity)
        {
            entity.Id = lineRepository.NewSequence("SYSTEM_MENU");
            return lineRepository.Save(entity);
        }**/

        public bool Update(ResourceDedicateLine entity)
        {
            bool result = false;
            result = lineRepository.Update(entity);
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='DedicateLine'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "DedicateLine";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "DedicateLine";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "NetWorkingMode";
            sre3.ControllerName = "DedicateLine";
            sre3.MId = entity.Id;
            sre3.CId = entity.NetWorkingModeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "BizAssuranLeve";
            sre4.ControllerName = "DedicateLine";
            sre4.MId = entity.Id;
            sre4.CId = entity.BizAssuranLeveId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            i++;
            SystemRelation sre5 = new SystemRelation();
            sre5.RelationName = "GroupClie";
            sre5.ControllerName = "DedicateLine";
            sre5.MId = entity.Id;
            sre5.CId = entity.ClieId;
            sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre5);
            i++;

            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "BizType";
            sre6.ControllerName = "DedicateLine";
            sre6.MId = entity.Id;
            sre6.CId = entity.BizTypeId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);
            i++;
            if (entity.SignalModel != null && entity.SignalModel != "")
            {
                SystemRelation sre7 = new SystemRelation();
                sre7.RelationName = "BizType";
                sre7.ControllerName = "DedicateLine";
                sre7.MId = entity.Id;
                sre7.CId = entity.SignalModel;
                sre7.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre7);
            }
            return result;
        }

        public IList<ResourceDedicateLine> LoadAll()
        {
            return lineRepository.LoadAll();
        }
        public IList<ResourceDedicateLine> LoadAll(string order, string where)
        {
            return lineRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourceDedicateLine main,ResourceGroupClie clie,ArchiveBizType ab ,";
            hql += "ArchiveNetworkingMode an,ArchiveBizAssuranLeve bal  ";
            hql += "where main.ClieId=clie.Id and main.BizTypeId=ab.Id and main.BizAssuranLeveId=bal.Id and ";
            hql += "main.NetWorkingModeId=an.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += " and (clie.DelFlag!=1 or clie.DelFlag is null)";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = lineRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<DedicateLine> dcs = new List<DedicateLine>();
            for (int i = 0; i < ls.Count; i++)
            {
                ResourceDedicateLine rdl = (ResourceDedicateLine)ls[i][0];
                ResourceGroupClie rg = (ResourceGroupClie)ls[i][1];
                ArchiveBizType ab = (ArchiveBizType)ls[i][2];
                ArchiveNetworkingMode an = (ArchiveNetworkingMode)ls[i][3];
                ArchiveBizAssuranLeve bal = (ArchiveBizAssuranLeve)ls[i][4];

                DedicateLine dc = new DedicateLine();
                dc.Id = rdl.Id;
                dc.ClieId = rg.Id;
                dc.ClieNo = rg.ClieNo;
                dc.ClieName = rg.ClieName;
                dc.DediLine = rdl;
                dc.BizTypeName = ab.BizTypeName;
                dc.ProductNo = rdl.ProductNo;
                dc.NetWorkingMode = an.ModeName;
                dc.AssuranLeveName = bal.AssuranLeveName;
                dc.State = ExtensionMethods.GetLineState(rdl.State);
                dc.IsAccessLocalNet = rdl.IsAccessLocalNet;
                dc.IsAccessProviNet = rdl.IsAccessProviNet;
                if (rdl.UpdateDate!=null)
                   dc.updatedate = ExtensionMethods.ToDateNull(rdl.UpdateDate).ToString();

                dc.createdate = ExtensionMethods.ToDateNull(rdl.CreateDate);
                if (rdl.ZClieId != null)
                {
                    ResourceGroupClie zclie = clieRepository.Get(rdl.ZClieId);
                    dc.ZClieName = zclie.ClieName;
                    dc.ZClieNo = zclie.ClieNo;
                }
                dcs.Add(dc);
            }
            string rowsjson = JSONHelper.ToJSON(dcs);
            int recordCount = lineRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json; 
            
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
        public IList<object[]> FindExcel(string aryField, string gridsearch,int zcount,string biztype)
        {
            string hql =
           @" from tab_Dedicate_Line main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_networking_mode netmode on netmode.Id=main.NetWorkingModeId 
                                      left join tab_biz_assuran_leve bizleve on bizleve.Id=main.BizAssuranLeveId  
                                      left join tab_Biz_Type biztype on biztype.Id=main.BizTypeId  
                                      left join tab_Group_Clie clie on clie.Id=main.ClieId   
                                      left join tab_Signal_Model signal on signal.Id=main.SignalModel  
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = lineRepository.LoadAllSqlObj(vSql);
            IList<object[]> dcs = new List<object[]>();
            for (int i = 0; i < ls.Count; i++)
            {
                object[] dc = new object[zcount];
                IList<ResourceFiberCore> Cores = fiberCoreRepository.LoadAll("SeqNo", " LineId='" + ls[i][0].ToString() + "'");
                string str = ""; int j = 0;
                 bool coreFlag0 = true; bool coreFlag1 = true;
                if (Cores.Count == 2)
                {
                    if (Cores[0].BaseStationName == null && Cores[0].ODUStation == null && Cores[0].Core == null)
                        coreFlag0 = false;
                    if (Cores[1].BaseStationName == null && Cores[1].ODUStation == null && Cores[1].Core == null)
                        coreFlag1 = false;
                }
                if (coreFlag0 && coreFlag1)
                {
                    foreach (ResourceFiberCore core in Cores)
                    {
                        if (j == 0)
                        {
                            str += "从" + core.BaseStationName;
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        else if (j == Cores.Count - 1)
                        {
                            str += "至客户端";
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        else
                        {
                            str += "经" + core.BaseStationName;
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        j++;
                    }
                }
                int lscount = ls[i].Length;
                if (biztype == "VOIP")
                {
                    for (int k = 0; k < ls[i].Length - 1; k++)
                    {
                        dc[k] = ls[i][k + 1];
                    }
                    dc[zcount - 2] = str;
                }
                if (biztype == "Internet")
                {
                    for (int k = 0; k < ls[i].Length - 2; k++)
                    {
                        dc[k] = ls[i][k + 2];
                    }
                    dc[zcount - 3] = str;
                    string equipstr = "";
                    string strhql =
          @"select new ResourceLineEquip(main.Id,main.EquipId,main.ClieId,equip.EquipName)
            from ResourceLineEquip main,ResourceEquipment equip
            where main.EquipId=equip.Id  
            and (main.DelFlag!=1 or main.DelFlag is null)";
                    strhql += " and main.LineId='" + ls[i][0].ToString() + "'";
                    strhql += " and main.ClieId='" + ls[i][1].ToString() + "'";
                    strhql += " order by main.Id desc";
                    IList<ResourceLineEquip> LineEquips = lineEquipRepository.LoadAllbyHql(strhql);
                    if (LineEquips != null)
                    {
                        foreach (ResourceLineEquip equip in LineEquips)
                        {
                            if (equipstr != "")
                            {
                                equipstr += "," + equip.EquipName;
                            }
                            else
                            {
                                equipstr += equip.EquipName;
                            }
                        }
                        dc[zcount - 2] = equipstr;
                    }
                }
                if (biztype == "IMS")
                {
                    for (int k = 0; k < ls[i].Length - 2; k++)
                    {
                        dc[k] = ls[i][k + 1];
                    }
                    dc[zcount - 3] = str;
                    string numstr = ""; 
                    IList<ResourceNumberGroup> Numbers = numberRepository.LoadAll("Id", " LineId='" + ls[i][0].ToString() + "'");
                    foreach (ResourceNumberGroup number in Numbers)
                    {
                        if (numstr != "")
                        {
                            numstr +=","+ number.TelNumber;
                            numstr += "-" + number.UserName;
                            numstr += "-" + number.PassWord;
                        }
                        else
                        {
                            numstr += number.TelNumber;
                            numstr += "-" + number.UserName;
                            numstr += "-" + number.PassWord;
                        }
                    }
                    dc[zcount - 2] = numstr;
                }
                dc[zcount - 3] = ls[i][ls[i].Length - 3];
                dc[zcount - 2] = ls[i][ls[i].Length - 2];
                dc[zcount - 1] = ls[i][ls[i].Length - 1];
                dcs.Add(dc);
            }
            return dcs;
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
        public IList<object[]> FindWideExcel(string aryField, string gridsearch, int zcount)
        {
            string hql =
           @" from tab_Dedicate_Line main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_networking_mode netmode on netmode.Id=main.NetWorkingModeId 
                                      left join tab_networking_mode znetmode on znetmode.Id=main.ZNetWorkingModeId 
                                      left join tab_biz_assuran_leve bizleve on bizleve.Id=main.BizAssuranLeveId  
                                      left join tab_Biz_Type biztype on biztype.Id=main.BizTypeId  
                                      left join tab_Group_Clie clie on clie.Id=main.ClieId   
                                      left join tab_Group_Clie zclie on zclie.Id=main.ZClieId   
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = lineRepository.LoadAllSqlObj(vSql);
            IList<object[]> dcs = new List<object[]>();
            for (int i = 0; i < ls.Count; i++)
            {
                object[] dc = new object[zcount];
                IList<ResourceFiberCore> Cores = fiberCoreRepository.LoadAll("SeqNo", " LineId='" + ls[i][0].ToString() + "'");
                string str = ""; int j = 0;
                bool coreFlag0 = true; bool coreFlag1 = true;
                if (Cores.Count == 2)
                {
                    if (Cores[0].BaseStationName == null && Cores[0].ODUStation == null && Cores[0].Core == null)
                        coreFlag0 = false;
                    if (Cores[1].BaseStationName == null && Cores[1].ODUStation == null && Cores[1].Core == null)
                        coreFlag1 = false;
                }
                if (coreFlag0 && coreFlag1)
                {
                    foreach (ResourceFiberCore core in Cores)
                    {
                        if (j == 0)
                        {

                            str += "从" + core.BaseStationName;
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        else if (j == Cores.Count - 1)
                        {
                            str += "至客户端";
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        else
                        {
                            str += "经" + core.BaseStationName;
                            str += "(" + core.ODUStation;
                            str += ")至(" + core.Core + ")";
                        }
                        j++;
                    }
                }
                for (int k = 0; k <  16; k++)
                {
                    dc[k] = ls[i][k + 3];
                }                
                string equipstr = "";
                string strhql =
      @"select new ResourceLineEquip(main.Id,main.EquipId,main.ClieId,equip.EquipName)
            from ResourceLineEquip main,ResourceEquipment equip
            where main.EquipId=equip.Id  
            and (main.DelFlag!=1 or main.DelFlag is null)";
                strhql += " and main.LineId='" + ls[i][0].ToString() + "'";
                strhql += " and main.ClieId='" + ls[i][1].ToString() + "'";
                strhql += " order by main.Id desc";
                IList<ResourceLineEquip> LineEquips = lineEquipRepository.LoadAllbyHql(strhql);
                if (LineEquips != null)
                {
                    string equipportstr = "";
                    foreach (ResourceLineEquip equip in LineEquips)
                    {
                        if (equipstr != "")
                        {
                            equipstr += "," + equip.EquipName;
                            equipportstr += "," + equip.OccupyPort;
                        }
                        else
                        {
                            equipstr += equip.EquipName;
                            equipportstr +=  equip.OccupyPort;
                        }
                    }
                    dc[16] = equipstr;
                    dc[17] = equipportstr;
                }
                for (int k = 18; k <  27; k++)
                {
                    dc[k] = ls[i][k + 1];
                }   
                string zequipstr = "";
                string zstrhql =
      @"select new ResourceLineEquip(main.Id,main.EquipId,main.ClieId,equip.EquipName)
            from ResourceLineEquip main,ResourceEquipment equip
            where main.EquipId=equip.Id  
            and (main.DelFlag!=1 or main.DelFlag is null)";
                zstrhql += " and main.LineId='" + ls[i][0].ToString() + "'";
                zstrhql += " and main.ClieId='" + ls[i][2] + "'";
                zstrhql += " order by main.Id desc";
                IList<ResourceLineEquip> ZLineEquips = lineEquipRepository.LoadAllbyHql(zstrhql);
                if (ZLineEquips != null)
                {
                    string zequipportstr = "";
                    foreach (ResourceLineEquip zequip in ZLineEquips)
                    {
                        if (zequipstr != "")
                        {
                            zequipstr += "," + zequip.EquipName;
                            zequipportstr += "," + zequip.OccupyPort;
                        }
                        else
                        {
                            zequipstr += zequip.EquipName;
                            zequipportstr += zequip.OccupyPort;
                        }
                    }
                    dc[27] = zequipstr;
                    dc[28] = zequipportstr;
                }
                for (int k = 29; k < 33; k++)
                {
                    dc[k] = ls[i][k -1];
                }
                dc[33] = str;
                for (int k = 34; k < ls[i].Length+2 ; k++)
                {
                    dc[k] = ls[i][k -2];
                }  
                dcs.Add(dc);
            }
            return dcs;
        }
    }
}
