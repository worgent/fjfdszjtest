using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.CommunityTypeManger.Models;
using QzgfFrame.Archives.CommunityTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.CommunityTypeManger.Domain
{
    public class CommunityTypeFacadeImpl : CommunityTypeFacade
    {
        private IRepository<ArchiveCommunityType> communityTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveCommunityType Get(object id)
        {
            return communityTypeRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='CommunityType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityType'";
                    result = relationRepository.DeleteHql(sql);
                    result = communityTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='CommunityType'";
                    result = relationRepository.DeleteHql(sql);
                    result = communityTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }
        public ArchiveCommunityType GetHql(string communityTypeName)
        {
            string Hql = " CommunityTypeName = '"+communityTypeName+"'";
            IList<ArchiveCommunityType> communityTypes = communityTypeRepository.LoadAll("Id", Hql);
            if (communityTypes != null)
            {
                if (communityTypes.Count > 0)
                    return communityTypes[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveCommunityType entity)
        {
            entity.Id = communityTypeRepository.NewSequence("SYSTEM_MENU");
            return communityTypeRepository.Save(entity);
        }

        public bool Update(ArchiveCommunityType entity)
        {
            return communityTypeRepository.Update(entity);
        }

        public IList<ArchiveCommunityType> LoadAll()
        {
            return communityTypeRepository.LoadAll();
        }
        public IList<ArchiveCommunityType> LoadAll(string order, string where)
        {
            return communityTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveCommunityType";
            IList<ArchiveCommunityType> ls = communityTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = communityTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
