using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain
{
    public class SelfHelpEquipTypeFacadeImpl:SelfHelpEquipTypeFacade
    {
        private IRepository<ArchiveSelfHelpEquipType> selfHelpEquipTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSelfHelpEquipType Get(object id)
        {
            return selfHelpEquipTypeRepository.Get(id.ToString());
        }
        public ArchiveSelfHelpEquipType GetHql(string equipTypeName)
        {
            string Hql = " SelfHelpEquipTypeName = '" + equipTypeName + "'";
            IList<ArchiveSelfHelpEquipType> entitys = selfHelpEquipTypeRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='SelfHelpEquipType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquipType'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpEquipTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpEquipType'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpEquipTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSelfHelpEquipType entity)
        {
            entity.Id = selfHelpEquipTypeRepository.NewSequence("SYSTEM_MENU");
            return selfHelpEquipTypeRepository.Save(entity);
        }

        public bool Update(ArchiveSelfHelpEquipType entity)
        {
            return selfHelpEquipTypeRepository.Update(entity);
        }

        public IList<ArchiveSelfHelpEquipType> LoadAll()
        {
            return selfHelpEquipTypeRepository.LoadAll();
        }
        public IList<ArchiveSelfHelpEquipType> LoadAll(string order, string where)
        {
            return selfHelpEquipTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveSelfHelpEquipType";
            IList<ArchiveSelfHelpEquipType> ls = selfHelpEquipTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = selfHelpEquipTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 自助设备类型下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll(); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.SelfHelpEquipTypeName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
