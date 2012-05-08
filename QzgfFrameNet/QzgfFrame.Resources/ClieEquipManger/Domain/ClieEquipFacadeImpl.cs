using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.ClieEquipManger.Domain
{
    public class ClieEquipFacadeImpl : ClieEquipFacade
    {
        private IRepository<ResourceClieEquip> clieEquipRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourceClieEquip Get(object id)
        {
            return clieEquipRepository.Get(id.ToString());
        }

        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">分隔符</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false; 
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " EquipId='" + s + "'";
                else
                    hql += " or EquipId='" + s + "'";
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
                }               
           
            }
            result = clieEquipRepository.DeleteHql(hql);   
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
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " EquipId='" + s + "'";
                else
                    hql += " or EquipId='" + s + "'";
                string strsql = " CId='" + s + "' and RelationName='ClieEquip'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='ClieEquip'";
                    result = relationRepository.DeleteHql(sql);
                    result = clieEquipRepository.Update(" DelFlag='1' where  " + hql);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='ClieEquip'";
                    result = relationRepository.DeleteHql(sql);
                    result = clieEquipRepository.Update(" DelFlag='1' where  " + hql);
                }
            }
            return result;
        }

        public bool Save(ResourceClieEquip entity,string no)
        {
            entity.Id = clieEquipRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "GroupClie";
            sre1.ControllerName = "ClieEquip";
            sre1.MId = entity.Id;
            sre1.CId = entity.ClieId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION","0");
            result = relationRepository.Save(sre1);
            result = clieEquipRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceClieEquip entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='ClieEquip'");
            //添加关系信息
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "GroupClie";
            sre1.ControllerName = "ClieEquip";
            sre1.MId = entity.Id;
            sre1.CId = entity.ClieId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", "0");
            result = relationRepository.Save(sre1);
            result = clieEquipRepository.Update(entity);
            return result;
        }

        public IList<ResourceClieEquip> LoadAll()
        {
            return clieEquipRepository.LoadAll();
        }
        public IList<ResourceClieEquip> LoadAll(string order, string where)
        {
            return clieEquipRepository.LoadAll(order, where);
        }
        public IList<ResourceClieEquip> LoadAll(string hql)
        {
            IList<object[]> ls = clieEquipRepository.LoadAllObj(hql);
            IList<ResourceClieEquip> ClieEquips = new List<ResourceClieEquip>();
            for (int i = 0; i < ls.Count; i++)
            {
                ResourceClieEquip clieEquip = new ResourceClieEquip();
                clieEquip.ClieId = ExtensionMethods.ToStr(ls[i][0]);
                clieEquip.OccupySlot = ExtensionMethods.ToStr(ls[i][1]);
                clieEquip.BoardType = ExtensionMethods.ToStr(ls[i][2]);
                clieEquip.OccupyPort = ExtensionMethods.ToStr(ls[i][3]);
                clieEquip.ClieNo =  ExtensionMethods.ToStr(ls[i][4]);
                clieEquip.ClieName =  ExtensionMethods.ToStr(ls[i][5]);
                clieEquip.PortTypeId = ExtensionMethods.ToStr(ls[i][6]);
                clieEquip.PortTypeName = ExtensionMethods.ToStr(ls[i][7]);
                ClieEquips.Add(clieEquip);
            }
            return ClieEquips;
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourceClieEquip";
            IList<ResourceClieEquip> ls = clieEquipRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = clieEquipRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
