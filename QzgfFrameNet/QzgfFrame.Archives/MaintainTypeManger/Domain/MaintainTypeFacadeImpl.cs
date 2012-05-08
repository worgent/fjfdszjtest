using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.MaintainTypeManger.Models;
using QzgfFrame.Archives.MaintainTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.MaintainTypeManger.Domain
{
    public class MaintainTypeFacadeImpl : MaintainTypeFacade
    {
        private IRepository<ArchiveMaintainType> maintainTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveMaintainType Get(object id)
        {
            return maintainTypeRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='MaintainType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='MaintainType'";
                    result = relationRepository.DeleteHql(sql);
                    result = maintainTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='MaintainType'";
                    result = relationRepository.DeleteHql(sql);
                    result = maintainTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveMaintainType GetHql(string maintainTypeName)
        {
            string Hql = " MaintainTypeName = '"+maintainTypeName+"'";
            IList<ArchiveMaintainType> maintainTypes = maintainTypeRepository.LoadAll("Id", Hql);
            if (maintainTypes != null)
            {
                if (maintainTypes.Count > 0)
                    return maintainTypes[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveMaintainType entity)
        {
            entity.Id = maintainTypeRepository.NewSequence("SYSTEM_MENU");
            return maintainTypeRepository.Save(entity);
        }

        public bool Update(ArchiveMaintainType entity)
        {
            return maintainTypeRepository.Update(entity);
        }

        public IList<ArchiveMaintainType> LoadAll()
        {
            return maintainTypeRepository.LoadAll();
        }
        public IList<ArchiveMaintainType> LoadAll(string order, string where)
        {
            return maintainTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveMaintainType";
            IList<ArchiveMaintainType> ls = maintainTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = maintainTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
