using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.DiplomaManger.Domain
{
    public class DiplomaFacadeImpl : DiplomaFacade
    {
        private IRepository<ArchiveDiploma> diplomaRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveDiploma Get(object id)
        {
            return diplomaRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='Diploma'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Diploma'";
                    result = relationRepository.DeleteHql(sql);
                    result = diplomaRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Diploma'";
                    result = relationRepository.DeleteHql(sql);
                    result = diplomaRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveDiploma GetHql(string diplomaName)
        {
            string Hql = " DiplomaName = '" + diplomaName + "'";
            IList<ArchiveDiploma> Diplomas = diplomaRepository.LoadAll("Id", Hql);
            if (Diplomas != null)
            {
                if (Diplomas.Count > 0)
                    return Diplomas[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveDiploma entity)
        {
            entity.Id = diplomaRepository.NewSequence("SYSTEM_MENU");
            return diplomaRepository.Save(entity);
        }

        public bool Update(ArchiveDiploma entity)
        {
            return diplomaRepository.Update(entity);
        }

        public IList<ArchiveDiploma> LoadAll()
        {
            return diplomaRepository.LoadAll();
        }
        public IList<ArchiveDiploma> LoadAll(string order, string where)
        {
            return diplomaRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveDiploma";
            IList<ArchiveDiploma> ls = diplomaRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = diplomaRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
