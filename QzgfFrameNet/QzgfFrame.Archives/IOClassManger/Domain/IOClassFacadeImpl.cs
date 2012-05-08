using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.IOClassManger.Models;
using QzgfFrame.Archives.IOClassManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.IOClassManger.Domain
{
    public class IOClassFacadeImpl:IOClassFacade
    {
        private IRepository<ArchiveIOClass> ioClassRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveIOClass Get(object id)
        {
            return ioClassRepository.Get(id.ToString());
        }
        public ArchiveIOClass Get(string order, string where)
        {
            IList<ArchiveIOClass> ioClasss= ioClassRepository.LoadAll(order, where);
            if (ioClasss.Count > 0)
                return ioClasss[0];
            else
                return null;
        }
        public ArchiveIOClass GetHql(string ioClassName)
        {
            string Hql = " IOClassName like '%" + ioClassName + "%'";
            IList<ArchiveIOClass> entitys = ioClassRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            } 
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
                string strsql = " CId='" + s + "' and RelationName='IOClass'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='IOClass'";
                    result = relationRepository.DeleteHql(sql);
                    result = ioClassRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='IOClass'";
                    result = relationRepository.DeleteHql(sql);
                    result = ioClassRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveIOClass entity)
        {
            entity.Id = ioClassRepository.NewSequence("SYSTEM_MENU");
            return ioClassRepository.Save(entity);
        }

        public bool Update(ArchiveIOClass entity)
        {
            return ioClassRepository.Update(entity);
        }

        public IList<ArchiveIOClass> LoadAll()
        {
            return ioClassRepository.LoadAll();
        }
        public IList<ArchiveIOClass> LoadAll(string order, string where)
        {
            return ioClassRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveIOClass";
            IList<ArchiveIOClass> ls = ioClassRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = ioClassRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
