using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.EquipModelManger.Domain
{
    public class EquipModelFacadeImpl : EquipModelFacade
    {
        private IRepository<ArchiveEquipModel> equipModelRepository { set; get; }
        private IRepository<ArchiveEquipType> equipTypeRepository { set; get; }
        private IRepository<ArchiveFactory> factoryRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveEquipModel Get(object id)
        {
            return equipModelRepository.Get(id.ToString());
        }
        public ArchiveEquipModel GetHql(string equipModelName)
        {
            string Hql = " EquipModelName = '" + equipModelName + "'";
            IList<ArchiveEquipModel> entitys = equipModelRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
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
                string strsql = " CId='" + s + "' and RelationName='EquipModel'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = equipModelRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = equipModelRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveEquipModel entity)
        {
            entity.Id = equipModelRepository.NewSequence("SYSTEM_MENU");
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Factory";
            sre1.ControllerName = "EquipModel";
            sre1.MId = entity.Id;
            sre1.CId = entity.FactoryId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "EquipType";
            sre2.ControllerName = "EquipModel";
            sre2.MId = entity.Id;
            sre2.CId = entity.EquipTypeId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = equipModelRepository.Save(entity);
            return result;
        }

        public bool Update(ArchiveEquipModel entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='EquipModel'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Factory";
            sre1.ControllerName = "EquipModel";
            sre1.MId = entity.Id;
            sre1.CId = entity.FactoryId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "EquipType";
            sre2.ControllerName = "EquipModel";
            sre2.MId = entity.Id;
            sre2.CId = entity.EquipTypeId;
            result = relationRepository.Save(sre2);
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = equipModelRepository.Update(entity);
            return result;
        }

        public IList<ArchiveEquipModel> LoadAll()
        {
            return equipModelRepository.LoadAll();
        }
        public IList<ArchiveEquipModel> OSZLoadAll()
        {
            IList<ArchiveEquipModel> EquipModels = new List<ArchiveEquipModel>();
            ArchiveEquipModel EquipModel = new ArchiveEquipModel();
            EquipModel.Id = "0";
            EquipModel.EquipModelName = "";
            EquipModels.Add(EquipModel);
            IList<ArchiveEquipModel> ArchiveEquipModels = equipModelRepository.LoadAll();
            foreach (ArchiveEquipModel ac in ArchiveEquipModels)
            {
                EquipModels.Add(ac);
            }
            return EquipModels;
        }
        public IList<ArchiveEquipModel> LoadAll(string order, string where)
        {
            return equipModelRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select new ArchiveEquipModel(main.Id,main.EquipModelName,main.FactoryId,main.EquipTypeId,type.EquipTypeName,
              factory.Abbrevia)
              from ArchiveEquipModel main,ArchiveFactory factory,ArchiveEquipType type
              where main.FactoryId=factory.Id and main.EquipTypeId=type.Id ";
            string vSql = hql + gridsearch;
            vSql += " order by main." + sortname + " " + sortorder;
            IList<ArchiveEquipModel> lsSuppliesStock = equipModelRepository.FindByPage(pageNo, pageSize, vSql);
            int recordCount = equipModelRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsSuppliesStock) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
