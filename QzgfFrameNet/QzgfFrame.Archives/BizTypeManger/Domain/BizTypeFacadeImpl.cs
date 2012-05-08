using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.BizTypeManger.Domain
{
    public class BizTypeFacadeImpl:BizTypeFacade
    {
        private IRepository<ArchiveBizType> bizTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveBizType Get(object id)
        {
            return bizTypeRepository.Get(id.ToString());
        }
        public ArchiveBizType Get(string order, string where)
        {
            IList<ArchiveBizType> bizTypes= bizTypeRepository.LoadAll(order, where);
            if (bizTypes.Count > 0)
                return bizTypes[0];
            else
                return null;
        }
        public ArchiveBizType GetHql(string bizTypeName)
        {
            string Hql = " BizTypeName like '%" + bizTypeName + "%'";
            IList<ArchiveBizType> entitys = bizTypeRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else
                    return null;
            }
            else return null;
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
                string strsql = " CId='" + s + "' and RelationName='BizType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='BizType'";
                    result = relationRepository.DeleteHql(sql);
                    result = bizTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='BizType'";
                    result = relationRepository.DeleteHql(sql);
                    result = bizTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveBizType entity)
        {
            entity.Id = bizTypeRepository.NewSequence("SYSTEM_MENU");
            return bizTypeRepository.Save(entity);
        }

        public bool Update(ArchiveBizType entity)
        {
            return bizTypeRepository.Update(entity);
        }

        public IList<ArchiveBizType> LoadAll()
        {
            return bizTypeRepository.LoadAll();
        }
        public IList<ArchiveBizType> LoadAll(string order, string where)
        {
            return bizTypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveBizType";
            IList<ArchiveBizType> ls = bizTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = bizTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
