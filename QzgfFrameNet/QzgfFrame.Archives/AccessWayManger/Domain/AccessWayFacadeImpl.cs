using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.AccessWayManger.Models;
using QzgfFrame.Archives.AccessWayManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.AccessWayManger.Domain
{
    public class AccessWayFacadeImpl : AccessWayFacade
    {
        private IRepository<ArchiveAccessWay> accessWayRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveAccessWay Get(object id)
        {
            return accessWayRepository.Get(id.ToString());
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id,out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
             DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='AccessWay'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='AccessWay'";
                    result = relationRepository.DeleteHql(sql);
                    result = accessWayRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='AccessWay'";
                    result = relationRepository.DeleteHql(sql);
                    result = accessWayRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveAccessWay GetHql(string accessWayName)
        {
            string Hql = " AccessWayName = '"+accessWayName+"'";
            IList<ArchiveAccessWay> accessWays = accessWayRepository.LoadAll("Id", Hql);
            if (accessWays != null)
            {
                if (accessWays.Count > 0)
                    return accessWays[0];
                else
                    return null;
            }
            else return null;
        }
        public bool Save(ArchiveAccessWay entity)
        {
            entity.Id = accessWayRepository.NewSequence("SYSTEM_MENU");
            return accessWayRepository.Save(entity);
        }

        public bool Update(ArchiveAccessWay entity)
        {
            return accessWayRepository.Update(entity);
        }

        public IList<ArchiveAccessWay> LoadAll()
        {
            return accessWayRepository.LoadAll();
        }
        public IList<ArchiveAccessWay> LoadAll(string order, string where)
        {
            return accessWayRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveAccessWay";
            IList<ArchiveAccessWay> ls = accessWayRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = accessWayRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
