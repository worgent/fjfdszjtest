using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.LineEquipManger.Models;
using QzgfFrame.Resources.LineEquipManger.Domain;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.LineEquipManger.Domain
{
    public class LineEquipFacadeImpl : LineEquipFacade
    {
        private IRepository<ResourceLineEquip> lineEquipRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }
        private IRepository<ResourceClieEquip> clieEquipRepository { set; get; }

        public ResourceLineEquip Get(object id)
        {
            return lineEquipRepository.Get(id.ToString());
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
                string hql = " LineId='" + s + "'";
                string strhql = " from ResourceLineEquip where  (DelFlag!=1 or DelFlag is null) and LineId='" + s + "'";
                ResourceLineEquip re = lineEquipRepository.GetbyHql(strhql);
                if (re == null)
                    result= true;
                else
                {
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
                        result = lineEquipRepository.DeleteHql(hql);
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
                        result = lineEquipRepository.DeleteHql(hql);
                    }           
                }
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
                string hql = " DelFlag='1' where LineId='" + s + "'";
                string strhql = " from ResourceLineEquip where  (DelFlag!=1 or DelFlag is null) and LineId='" + s + "'";
                IList<ResourceLineEquip> res = lineEquipRepository.LoadAll("Id",strhql);
                if (res == null)
                    result = true;
                else if (res.Count == 0)
                    result = true;
                else
                {
                    result = lineEquipRepository.Update(hql);
                }
            }
            return result;
        }

        public bool Save(ResourceLineEquip entity, string no)
        {
            entity.Id = lineEquipRepository.NewSequence("SYSTEM_MENU", no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Equipment";
            sre1.ControllerName = "LineEquip";
            sre1.MId = entity.Id;
            sre1.CId = entity.EquipId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = lineEquipRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceLineEquip entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='LineEquip'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Equipment";
            sre1.ControllerName = "LineEquip";
            sre1.MId = entity.Id;
            sre1.CId = entity.EquipId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = lineEquipRepository.Update(entity);
            return result;
        }

        public IList<ResourceLineEquip> LoadAll()
        {
            return lineEquipRepository.LoadAll();
        }
        public IList<ResourceLineEquip> LoadAll(string where)
        {
            string hql =
           @"select new ResourceLineEquip(main.Id,main.EquipId,main.ClieId,equip.EquipName)
            from ResourceLineEquip main,ResourceEquipment equip
            where main.EquipId=equip.Id  
            and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += where;
            hql += " order by main.Id desc" ;
            IList<ResourceLineEquip> lineEquips = lineEquipRepository.LoadAllbyHql(hql);
            IList<ResourceLineEquip> lineEquipList = new List<ResourceLineEquip>();
            string strhql =
           @"select new ResourceClieEquip(main.Id,main.OccupyPort,main.EquipId,main.ClieId)
            from ResourceClieEquip main
            where (main.DelFlag!=1 or main.DelFlag is null)";
            foreach (ResourceLineEquip equip in lineEquips)
            { 
                string vstrhql=strhql+" and main.ClieId='"+equip.ClieId+"' and main.EquipId='"+equip.EquipId+"'";
                ResourceClieEquip clieequip=clieEquipRepository.GetbyHql(vstrhql);
                if(clieequip!=null)
                   equip.OccupyPort = clieequip.OccupyPort;
                lineEquipList.Add(equip);
            }
            return lineEquipList;
        }
        public IList<ResourceLineEquip> LoadAll(string order, string where)
        {
            return lineEquipRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string gridsearch)
        {
            string hql = "from ResourceLineEquip where SelfHelpEquipId='" + gridsearch + "'";
            IList<ResourceLineEquip> ls = lineEquipRepository.FindByPage(pageNo, pageSize, hql);
            if (ls.Count == 0)
            {
                ResourceLineEquip LineEquip = new ResourceLineEquip();
                ls.Add(LineEquip);
            }
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = lineEquipRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
