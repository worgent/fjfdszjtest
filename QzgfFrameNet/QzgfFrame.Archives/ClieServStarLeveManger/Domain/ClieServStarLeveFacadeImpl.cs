using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.ClieServStarLeveManger.Models;
using QzgfFrame.Archives.ClieServStarLeveManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.ClieServStarleveManger.Domain
{
    public class ClieServStarLeveFacadeImpl : ClieServStarLeveFacade
    {
        private IRepository<ArchiveClieServStarLeve> starRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveClieServStarLeve Get(object id)
        {
            return starRepository.Get(id.ToString());
        }
        public ArchiveClieServStarLeve GetHql(object StarLeveName)
        {
            string Hql = "";
            if (StarLeveName != null)
            {
                if(StarLeveName.ToString()=="")
                   Hql = " StarLeveName = '待定'";
                else
                   Hql = " StarLeveName = '" + StarLeveName.ToString() + "'";
            }
            else
                Hql = " StarLeveName = '待定'";
            IList<ArchiveClieServStarLeve> entitys = starRepository.LoadAll("Id", Hql);
            if(entitys!=null){
            if (entitys.Count > 0)
                return entitys[0];
            else return null;
            }
            else
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
                string strsql = " CId='" + s + "' and RelationName='ServStarLeve'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='ServStarLeve'";
                    result = relationRepository.DeleteHql(sql);
                    result = starRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='ServStarLeve'";
                    result = relationRepository.DeleteHql(sql);
                    result = starRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveClieServStarLeve entity)
        {
            entity.Id = starRepository.NewSequence("SYSTEM_MENU");
            return starRepository.Save(entity);
        }

        public bool Update(ArchiveClieServStarLeve entity)
        {
            return starRepository.Update(entity);
        }

        public IList<ArchiveClieServStarLeve> LoadAll()
        {
            return starRepository.LoadAll();
        }
        public IList<ArchiveClieServStarLeve> LoadAll(string order, string where)
        {
            return starRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveClieServStarLeve";
            IList<ArchiveClieServStarLeve> ls = starRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = starRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
