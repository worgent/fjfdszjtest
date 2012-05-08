using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.ClieScaleGradeManger.Models;
using QzgfFrame.Archives.ClieScaleGradeManger.Domain;
using QzgfFrame.Archives.ClieServStarLeveManger.Models;
using QzgfFrame.Archives.ClieServStarLeveManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.GroupClieManger.Domain
{
    public class GroupClieFacadeImpl : GroupClieFacade
    {
        private IRepository<ResourceGroupClie> clieRepository { set; get; }
        private IRepository<ResourceClieGrid> clieGridRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveClieScaleGrade> scaleRepository { set; get; }
        private IRepository<ArchiveClieServStarLeve> starRepository { set; get; }
        private IRepository<ArchiveGrid> gridRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourceGroupClie Get(object id)
        {
            ResourceGroupClie GroupClie = clieRepository.Get(id.ToString());
            //取得网格值
            IList<ResourceClieGrid> ls =
                clieGridRepository.LoadAllbyHql("from ResourceClieGrid where ClieId='" + id.ToString() + "'");
            string gridids = "";
            foreach (var vls in ls)
            {
                gridids += vls.GridId + ";";
            }
            if (gridids != "")
                gridids = gridids.Substring(0, gridids.Length - 1);
            GroupClie.GridIds = gridids;
            return GroupClie;
        }
        public ResourceGroupClie GetGC(object id)
        {
            ResourceGroupClie GroupClie = clieRepository.Get(id.ToString());
            
            return GroupClie;
        }
        public ResourceGroupClie GetHql(string clieNo)
        {
            string Hql = " ClieNo = '" + clieNo + "' and (DelFlag!=1 or DelFlag is null)";
            IList<ResourceGroupClie> entitys = clieRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else
                    return null;
            }
            else return null;
        }
        public ResourceGroupClie GetStrHql(string hql)
        {
            //string Hql = " ClieNo = '" + clieNo + "' and (DelFlag!=1 or DelFlag is null)";
            IList<ResourceGroupClie> entitys = clieRepository.LoadAll("Id", hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
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
                result = clieRepository.Delete(s);
                result=clieGridRepository.DeletebyHql("from ResourceClieGrid where ClieId='" + s+ "'");
            
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
                string strsql = " CId='" + s + "' and RelationName='GroupClie'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='GroupClie'";
                    result = relationRepository.DeleteHql(sql);

                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = clieRepository.Update(hql);
                    result = clieGridRepository.Update(" DelFlag='1' where  ClieId='" + s + "'");
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='GroupClie'";
                    result = relationRepository.DeleteHql(sql);

                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = clieRepository.Update(hql);
                    result = clieGridRepository.Update(" DelFlag='1' where  ClieId='" + s + "'");
                } 
            }
            return result;
        }

        public bool Save(ResourceGroupClie entity, string no)
        {
            try
            {
                bool result = false;
                entity.Id = clieRepository.NewSequence("SYSTEM_MENU", no);
                entity.CreateDate = DateTime.Now;
                result = clieRepository.Save(entity);
                string vgridids = "";
                if (entity.GridIds != null)
                    vgridids = entity.GridIds;

                string[] gridids = vgridids.Split(';');
                int j = int.Parse(no);
                foreach (var gridid in gridids)
                {
                    var entitycliegrid = new ResourceClieGrid();
                    entitycliegrid.Id = clieGridRepository.NewSequence("SYSTEM_USERROLE", j.ToString());
                    entitycliegrid.GridId = gridid;
                    entitycliegrid.ClieId = entity.Id;
                    j++;
                    SystemRelation sre5 = new SystemRelation();
                    sre5.RelationName = "Grid";
                    sre5.ControllerName = "GroupClie";
                    sre5.MId = entity.Id;
                    sre5.CId = entitycliegrid.GridId;
                    sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", j.ToString());
                    result = relationRepository.Save(sre5);

                    result=clieGridRepository.Save(entitycliegrid);
                    j++;
                }
                //添加关系信息
                int i = j;
                if (entity.CompanyId != null)
                {
                    SystemRelation sre1 = new SystemRelation();
                    sre1.RelationName = "Company";
                    sre1.ControllerName = "GroupClie";
                    sre1.MId = entity.Id;
                    sre1.CId = entity.CompanyId;
                    sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                    result = relationRepository.Save(sre1);
                    i++;
                }
                SystemRelation sre2 = new SystemRelation();
                sre2.RelationName = "District";
                sre2.ControllerName = "GroupClie";
                sre2.MId = entity.Id;
                sre2.CId = entity.DistrictId;
                sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre2);
                i++;
                SystemRelation sre3 = new SystemRelation();
                sre3.RelationName = "ServStarLeve";
                sre3.ControllerName = "GroupClie";
                sre3.MId = entity.Id;
                sre3.CId = entity.StarLeveId;
                sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre3);
                i++;
                SystemRelation sre4 = new SystemRelation();
                sre4.RelationName = "ScaleGrade";
                sre4.ControllerName = "GroupClie";
                sre4.MId = entity.Id;
                sre4.CId = entity.ScaleGradeId;
                sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre4);
                if (result == false)
                    throw new Exception("操作失败!!");
                return result;
            }
            catch(Exception ex)
            {
                return false;
            }
        }

        public bool Update(ResourceGroupClie entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='GroupClie'");
            
            //保存关系表
           result= clieGridRepository.DeletebyHql("from ResourceClieGrid where ClieId='" + entity.Id + "'");
            string vgridids = "";
            if (entity.GridIds != null)
                vgridids = entity.GridIds;

            string[] gridids = vgridids.Split(';');
            int j = 0;
            foreach (var gridid in gridids)
            {
                var entitycliegrid = new ResourceClieGrid();
                entitycliegrid.Id = clieGridRepository.NewSequence("SYSTEM_USERROLE",j.ToString());
                entitycliegrid.GridId = gridid;
                entitycliegrid.ClieId = entity.Id;
               result= clieGridRepository.Save(entitycliegrid);

               j++;
               
                //添加关系
               SystemRelation sre5 = new SystemRelation();
               sre5.RelationName = "Grid";
               sre5.ControllerName = "GroupClie";
               sre5.MId = entity.Id;
               sre5.CId = entitycliegrid.GridId;
               sre5.Id = relationRepository.NewSequence("SYSTEM_RELATION", j.ToString());
               result = relationRepository.Save(sre5);
                j++;
            }
           //添加关系信息
            int i = 0;
            if (entity.CompanyId != null)
            {
                SystemRelation sre1 = new SystemRelation();
                sre1.RelationName = "Company";
                sre1.ControllerName = "GroupClie";
                sre1.MId = entity.Id;
                sre1.CId = entity.CompanyId;
                sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre1);
                i++;
            }
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "GroupClie";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "ServStarLeve";
            sre3.ControllerName = "GroupClie";
            sre3.MId = entity.Id;
            sre3.CId = entity.StarLeveId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "ScaleGrade";
            sre4.ControllerName = "GroupClie";
            sre4.MId = entity.Id;
            sre4.CId = entity.ScaleGradeId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            result = clieRepository.Update(entity);
            return result;
        }

        public IList<ResourceGroupClie> LoadAll()
        {
            return clieRepository.LoadAll();
        }
        public IList<ResourceGroupClie> LoadAll(string order, string where)
        {
            where += " and (DelFlag!=1 or DelFlag is null)";
            return clieRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
             string hql = "select main.Id,main.ClieNo,main.ClieName,c.DistrictName,d.DistrictName,";
             hql += "u.CompanyName,cs.ScaleGradeName ";
             hql += "from ResourceGroupClie main,ArchiveDistrict c,ArchiveDistrict d,ArchiveCompany u,";
             hql += "ArchiveClieScaleGrade cs where main.CityId=c.Id and ";
             hql += "main.DistrictId=d.Id and main.CompanyId=u.Id  and  main.ScaleGradeId=cs.Id   ";
             hql += "and  (main.DelFlag!=1 or main.DelFlag is null)";
             string vSql = hql + gridsearch;
             vSql +=@" order by main." + sortname + " " + sortorder;

             IList<object[]> ls = clieRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<GroupClie> GroupClies = new List<GroupClie>();
            for (int i = 0; i < ls.Count; i++)
            {
                GroupClie groupClie = new GroupClie();
                groupClie.Id = ls[i][0].ToString();
                groupClie.ClieNo = ExtensionMethods.ToStr(ls[i][1]);
                groupClie.ClieName = ExtensionMethods.ToStr(ls[i][2]);
                groupClie.CityName = ExtensionMethods.ToStr(ls[i][3]);
                groupClie.DistrictName = ExtensionMethods.ToStr(ls[i][4]);
                groupClie.CompanyName = ExtensionMethods.ToStr(ls[i][5]);
                groupClie.ScaleGradeName = ExtensionMethods.ToStr(ls[i][6]);
                IList<object[]> ClieGrid =
                clieGridRepository.LoadAllObj("select rcg.Id,ag.GridName,rcg.GridId from ResourceClieGrid rcg,ArchiveGrid ag  where ag.Id=rcg.GridId and rcg.ClieId='" + ls[i][0].ToString() + "'");
                string gridids = ""; string gridnames = "";
                foreach (var vls in ClieGrid)
                {
                    gridnames += vls[1].ToString() + ";";
                    gridids += vls[2].ToString() + ";";
                }
                if (gridnames != "")
                    gridnames = gridnames.Substring(0, gridnames.Length - 1);
                groupClie.GridName = gridnames;
                GroupClies.Add(groupClie);
            }
            string rowsjson = JSONHelper.ToJSON(GroupClies);
            int recordCount = clieRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 集团编号下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox(string hql)
        {
            IList<ResourceGroupClie> ls = new List<ResourceGroupClie>();
            if(hql!=""||hql==null)
                ls = LoadAll("Id",hql);
            else
                ls = LoadAll();
            IList jsonlist = new ArrayList();
            if(ls!=null)
             jsonlist = (from a in ls
                            select new
                            {
                                text = a.ClieNo,
                                id = a.Id
                            }
                           ).ToArray();
          
            return JSONHelper.ToJSON(jsonlist);
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
           @" from ResourceGroupClie main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District dd on dd.Id=main.CityId
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_Grid grid on grid.Id=main.GridId 
                                      left join tab_Stagnation sta on sta.Id=main.StagnationId  
                                      left join tab_Vehicle_Nature nature on nature.Id=main.VehicleNatureId  
                                      left join tab_Use use on use.Id=main.UseId  
                                      left join tab_Maintain_Specialty maintain on maintain.Id=main.MaintainSpecialtyId 
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = clieRepository.LoadAllObj(vSql);

            return ls;
        }
    }
}
