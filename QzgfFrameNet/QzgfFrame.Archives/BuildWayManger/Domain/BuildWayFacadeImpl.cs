using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.BuildWayManger.Models;
using QzgfFrame.Archives.BuildWayManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.BuildWayManger.Domain
{
    public class BuildWayFacadeImpl : BuildWayFacade
    {
        private IRepository<ArchiveBuildWay> buildWayRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveBuildWay Get(object id)
        {
            return buildWayRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='BuildWay'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='BuildWay'";
                    result = relationRepository.DeleteHql(sql);
                    result = buildWayRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='BuildWay'";
                    result = relationRepository.DeleteHql(sql);
                    result = buildWayRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveBuildWay GetHql(string buildWayName)
        {
            string Hql = " BuildWayName = '"+buildWayName+"'";
            IList<ArchiveBuildWay> buildWays = buildWayRepository.LoadAll("Id", Hql);
            if (buildWays != null)
            {
                if (buildWays.Count > 0)
                    return buildWays[0];
                else
                    return null;
            }
            else return null;
        }
        public bool Save(ArchiveBuildWay entity)
        {
            entity.Id = buildWayRepository.NewSequence("SYSTEM_MENU");
            return buildWayRepository.Save(entity);
        }

        public bool Update(ArchiveBuildWay entity)
        {
            return buildWayRepository.Update(entity);
        }

        public IList<ArchiveBuildWay> LoadAll()
        {
            return buildWayRepository.LoadAll();
        }
        public IList<ArchiveBuildWay> LoadAll(string order, string where)
        {
            return buildWayRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveBuildWay";
            IList<ArchiveBuildWay> ls = buildWayRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = buildWayRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
