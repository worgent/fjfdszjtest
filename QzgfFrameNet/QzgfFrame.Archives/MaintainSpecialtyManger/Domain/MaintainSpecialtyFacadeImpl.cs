using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.MaintainSpecialtyManger.Domain
{
    public class MaintainSpecialtyFacadeImpl : MaintainSpecialtyFacade
    {
        private IRepository<ArchiveMaintainSpecialty> maintainSpecialtyRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveMaintainSpecialty Get(object id)
        {
            return maintainSpecialtyRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='MaintainSpecialty'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='MaintainSpecialty'";
                    result = relationRepository.DeleteHql(sql);
                    result = maintainSpecialtyRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='MaintainSpecialty'";
                    result = relationRepository.DeleteHql(sql);
                    result = maintainSpecialtyRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveMaintainSpecialty GetHql(string maintainSpecialtyName)
        {
            string Hql = " MaintainSpecialtyName = '"+maintainSpecialtyName+"'";
            IList<ArchiveMaintainSpecialty> maintainSpecialtys = maintainSpecialtyRepository.LoadAll("Id", Hql);
            if (maintainSpecialtys != null)
            {
                if (maintainSpecialtys.Count > 0)
                    return maintainSpecialtys[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveMaintainSpecialty entity)
        {
            entity.Id = maintainSpecialtyRepository.NewSequence("SYSTEM_MENU");
            return maintainSpecialtyRepository.Save(entity);
        }

        public bool Update(ArchiveMaintainSpecialty entity)
        {
            return maintainSpecialtyRepository.Update(entity);
        }

        public IList<ArchiveMaintainSpecialty> LoadAll()
        {
            return maintainSpecialtyRepository.LoadAll();
        }
        public IList<ArchiveMaintainSpecialty> LoadAll(string order, string where)
        {
            return maintainSpecialtyRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveMaintainSpecialty";
            IList<ArchiveMaintainSpecialty> ls = maintainSpecialtyRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = maintainSpecialtyRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
