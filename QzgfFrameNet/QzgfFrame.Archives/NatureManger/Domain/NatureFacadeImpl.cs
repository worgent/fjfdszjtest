using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.NatureManger.Models;
using QzgfFrame.Archives.NatureManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.NatureManger.Domain
{
    public class NatureFacadeImpl : NatureFacade
    {
        private IRepository<ArchiveNature> natureRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveNature Get(object id)
        {
            return natureRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='Nature'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Nature'";
                    result = relationRepository.DeleteHql(sql);
                    result = natureRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Nature'";
                    result = relationRepository.DeleteHql(sql);
                    result = natureRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveNature GetHql(string natureName)
        {
            string Hql = " NatureName = '" + natureName + "'";
            IList<ArchiveNature> Natures = natureRepository.LoadAll("Id", Hql);
            if (Natures != null)
            {
                if (Natures.Count > 0)
                    return Natures[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveNature entity)
        {
            entity.Id = natureRepository.NewSequence("SYSTEM_MENU");
            return natureRepository.Save(entity);
        }

        public bool Update(ArchiveNature entity)
        {
            return natureRepository.Update(entity);
        }

        public IList<ArchiveNature> LoadAll()
        {
            return natureRepository.LoadAll();
        }
        public IList<ArchiveNature> LoadAll(string order, string where)
        {
            return natureRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveNature";
            IList<ArchiveNature> ls = natureRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = natureRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
