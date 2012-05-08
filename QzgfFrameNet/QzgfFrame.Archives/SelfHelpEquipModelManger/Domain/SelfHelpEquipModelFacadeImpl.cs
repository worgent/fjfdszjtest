using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SelfHelpEquipModelManger.Domain
{
    public class SelfHelpEquipModelFacadeImpl : SelfHelpEquipModelFacade
    {
        private IRepository<ArchiveSelfHelpEquipModel> selfHelpEquipModelRepository { set; get; }
        private IRepository<ArchiveSelfHelpEquipType> selfHelpEquipTypeRepository { set; get; }
        private IRepository<ArchiveSelfHelpFactory> selfHelpFactoryRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSelfHelpEquipModel Get(object id)
        {
            return selfHelpEquipModelRepository.Get(id.ToString());
        }
        public ArchiveSelfHelpEquipModel GetHql(string selfHelpEquipModelName)
        {
            string Hql = " ModelName ='" + selfHelpEquipModelName + "'";
            IList<ArchiveSelfHelpEquipModel> entitys = selfHelpEquipModelRepository.LoadAll("Id", Hql);
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
        public bool Delete(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='SelfHelpEquipModel'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquipModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpEquipModelRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquipModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpEquipModelRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSelfHelpEquipModel entity)
        {
            entity.Id = selfHelpEquipModelRepository.NewSequence("SYSTEM_MENU");
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "SelfHelpFactory";
            sre1.ControllerName = "SelfHelpEquipModel";
            sre1.MId = entity.Id;
            sre1.CId = entity.SelfHelpFactoryId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "SelfHelpEquipType";
            sre2.ControllerName = "SelfHelpEquipModel";
            sre2.MId = entity.Id;
            sre2.CId = entity.SelfHelpEquipTypeId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = selfHelpEquipModelRepository.Save(entity);
            return result;
        }

        public bool Update(ArchiveSelfHelpEquipModel entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='SelfHelpEquipModel'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "SelfHelpFactory";
            sre1.ControllerName = "SelfHelpEquipModel";
            sre1.MId = entity.Id;
            sre1.CId = entity.SelfHelpFactoryId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "SelfHelpEquipType";
            sre2.ControllerName = "SelfHelpEquipModel";
            sre2.MId = entity.Id;
            sre2.CId = entity.SelfHelpEquipTypeId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = selfHelpEquipModelRepository.Update(entity);
            return result;
        }

        public IList<ArchiveSelfHelpEquipModel> LoadAll()
        {
            return selfHelpEquipModelRepository.LoadAll();
        }
        public IList<ArchiveSelfHelpEquipModel> LoadAll(string order, string where)
        {
            return selfHelpEquipModelRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsModel = selfHelpEquipModelRepository.LoadAllbyHql("from ArchiveSelfHelpEquipModel");
            var lsType = selfHelpEquipTypeRepository.LoadAllbyHql("from ArchiveSelfHelpEquipType");
            var lsFactory = selfHelpFactoryRepository.LoadAllbyHql("from ArchiveSelfHelpFactory");
            var jsonlist = (from vlsModel in lsModel
                            join vlsType in lsType
                                on vlsModel.SelfHelpEquipTypeId equals vlsType.Id into joinvlsModelType
                            from vlsModelType in joinvlsModelType.DefaultIfEmpty()

                            join vlsFactory in lsFactory
                                on vlsModel.SelfHelpFactoryId equals vlsFactory.Id into joinvlsModelFactory
                            from vlsModelFactory in joinvlsModelFactory.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsModel.Id,
                                EquipModelName = vlsModel.ModelName,
                                EquipTypeName = vlsModelType != null ? vlsModelType.SelfHelpEquipTypeName : "",
                                FactoryName = vlsModelFactory != null ? vlsModelFactory.Abbrevia : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }
    }
}
