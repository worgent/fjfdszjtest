using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Core.JSON;

namespace QzgfFrame.Archives.EquipTypeManger.Domain
{
    public class EquipTypeFacadeImpl:EquipTypeFacade
    {
        private IRepository<ArchiveEquipType> equipTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveEquipType Get(object id)
        {
            return equipTypeRepository.Get(id.ToString());
        }
        public ArchiveEquipType GetHql(string equipTypeName)
        {
            string Hql = " EquipTypeName = '" + equipTypeName + "'";
            IList<ArchiveEquipType> entitys = equipTypeRepository.LoadAll("Id", Hql);
            if (entitys.Count > 0)
                return entitys[0];
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
                string strsql = " CId='" + s + "' and RelationName='EquipType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipType'";
                    result = relationRepository.DeleteHql(sql);
                    result = equipTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipType'";
                    result = relationRepository.DeleteHql(sql);
                    result = equipTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveEquipType entity)
        {
            entity.Id = equipTypeRepository.NewSequence("SYSTEM_MENU");
            return equipTypeRepository.Save(entity);
        }

        public bool Update(ArchiveEquipType entity)
        {
            return equipTypeRepository.Update(entity);
        }

        public IList<ArchiveEquipType> LoadAll()
        {
            return equipTypeRepository.LoadAll();
        }
        public IList<ArchiveEquipType> LoadAll(string order, string where)
        {
            return equipTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveEquipType";
            IList<ArchiveEquipType> ls = equipTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = equipTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 设备类型下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll(); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.EquipTypeName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
