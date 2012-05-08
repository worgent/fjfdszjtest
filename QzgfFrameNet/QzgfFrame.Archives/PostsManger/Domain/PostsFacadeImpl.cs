using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.PostsManger.Models;
using QzgfFrame.Archives.PostsManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.PostsManger.Domain
{
    public class PostsFacadeImpl : PostsFacade
    {
        private IRepository<ArchivePosts> postsRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchivePosts Get(object id)
        {
            return postsRepository.Get(id);
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
                string strsql = " CId='" + s + "' and RelationName='Posts'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Posts'";
                    result = relationRepository.DeleteHql(sql);
                    result = postsRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Posts'";
                    result = relationRepository.DeleteHql(sql);
                    result = postsRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchivePosts GetHql(string postsName)
        {
            string Hql = " PostsName = '" + postsName + "'";
            IList<ArchivePosts> Postss = postsRepository.LoadAll("Id", Hql);
            if (Postss != null)
            {
                if (Postss.Count > 0)
                    return Postss[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchivePosts entity)
        {
            entity.Id = postsRepository.NewSequence("SYSTEM_MENU");
            return postsRepository.Save(entity);
        }

        public bool Update(ArchivePosts entity)
        {
            return postsRepository.Update(entity);
        }

        public IList<ArchivePosts> LoadAll()
        {
            return postsRepository.LoadAll();
        }
        public IList<ArchivePosts> LoadAll(string order, string where)
        {
            return postsRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchivePosts";
            IList<ArchivePosts> ls = postsRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = postsRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
