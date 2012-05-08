using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.EquipComponentManger.Models;
using QzgfFrame.Resources.EquipComponentManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.EquipComponentManger.Domain
{
    public class EquipComponentFacadeImpl : EquipComponentFacade
    {
        private IRepository<ResourceEquipComponent> equipComponentRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourceEquipComponent Get(object id)
        {
            return equipComponentRepository.Get(id.ToString());
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
                    result = equipComponentRepository.DeleteHql(shql); 
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
                    result = equipComponentRepository.DeleteHql(shql); 
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
                string hql = " DelFlag='1' where SelfHelpEquipId='" + s + "'";
                string strhql = " from ResourceEquipComponent where  (DelFlag!=1 or DelFlag is null) and SelfHelpEquipId='" + s + "'";
                ResourceEquipComponent re = equipComponentRepository.GetbyHql(strhql);
                if (re == null)
                    return true;
                else
                {
                    string strsql = " CId='" + s + "' and RelationName='EquipComponent'";
                    IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                    if (sres == null)
                    {
                        string sql = " MId='" + s + "' and ControllerName='EquipComponent'";
                        result = relationRepository.DeleteHql(sql);
                        result = equipComponentRepository.Update(hql);
                    }
                    else if (sres.Count == 0)
                    {
                        string sql = " MId='" + s + "' and ControllerName='EquipComponent'";
                        result = relationRepository.DeleteHql(sql);
                        result = equipComponentRepository.Update(hql);
                    }
                }
            }
            return result;
        }

        public bool Save(ResourceEquipComponent entity, string no)
        {
            bool result = false;
            entity.Id = equipComponentRepository.NewSequence("SYSTEM_MENU", no);
           
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Component";
            sre1.ControllerName = "EquipComponent";
            sre1.MId = entity.Id;
            sre1.CId = entity.ComponentId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = equipComponentRepository.Save(entity);
            return result;
        }

        public bool Update(ResourceEquipComponent entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='EquipComponent'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Component";
            sre1.ControllerName = "EquipComponent";
            sre1.MId = entity.Id;
            sre1.CId = entity.ComponentId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = equipComponentRepository.Update(entity);
            return result;
        }

        public IList<ResourceEquipComponent> LoadAll()
        {
            return equipComponentRepository.LoadAll();
        }
        public IList<ResourceEquipComponent> LoadAll(string order, string where)
        {
            return equipComponentRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string gridsearch)
        {
            string hql = "from ResourceEquipComponent where SelfHelpEquipId='" + gridsearch + "'";
            IList<ResourceEquipComponent> ls = equipComponentRepository.FindByPage(pageNo, pageSize, hql);
            if (ls.Count == 0)
            {
                ResourceEquipComponent EquipComponent = new ResourceEquipComponent();
                ls.Add(EquipComponent);
            }
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = equipComponentRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
