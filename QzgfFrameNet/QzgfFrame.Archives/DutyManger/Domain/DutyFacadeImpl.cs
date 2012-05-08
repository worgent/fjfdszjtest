using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.DutyManger.Models;
using QzgfFrame.Archives.DutyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.DutyManger.Domain
{
    public class DutyFacadeImpl : DutyFacade
    {
        private IRepository<ArchiveDuty> dutyRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveDuty Get(object id)
        {
            return dutyRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='Duty'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Duty'";
                    result = relationRepository.DeleteHql(sql);
                    result = dutyRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Duty'";
                    result = relationRepository.DeleteHql(sql);
                    result = dutyRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveDuty GetHql(string dutyName)
        {
            string Hql = " DutyName = '" + dutyName + "'";
            IList<ArchiveDuty> Dutys = dutyRepository.LoadAll("Id", Hql);
            if (Dutys != null)
            {
                if (Dutys.Count > 0)
                    return Dutys[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveDuty entity)
        {
            entity.Id = dutyRepository.NewSequence("SYSTEM_MENU");
            return dutyRepository.Save(entity);
        }

        public bool Update(ArchiveDuty entity)
        {
            return dutyRepository.Update(entity);
        }

        public IList<ArchiveDuty> LoadAll()
        {
            return dutyRepository.LoadAll();
        }
        public IList<ArchiveDuty> LoadAll(string order, string where)
        {
            return dutyRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveDuty";
            IList<ArchiveDuty> ls = dutyRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = dutyRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
