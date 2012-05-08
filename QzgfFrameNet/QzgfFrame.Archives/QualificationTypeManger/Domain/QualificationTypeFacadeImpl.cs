using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.QualificationTypeManger.Models;
using QzgfFrame.Archives.QualificationTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.QualificationTypeManger.Domain
{
    public class QualificationTypeFacadeImpl : QualificationTypeFacade
    {
        private IRepository<ArchiveQualificationType> qualificationTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveQualificationType Get(object id)
        {
            return qualificationTypeRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='QualificationType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='QualificationType'";
                    result = relationRepository.DeleteHql(sql);
                    result = qualificationTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='QualificationType'";
                    result = relationRepository.DeleteHql(sql);
                    result = qualificationTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveQualificationType GetHql(string qualificationTypeName)
        {
            string Hql = " QualificationTypeName = '"+qualificationTypeName+"'";
            IList<ArchiveQualificationType> qualificationTypes = qualificationTypeRepository.LoadAll("Id", Hql);
            if (qualificationTypes != null)
            {
                if (qualificationTypes.Count > 0)
                    return qualificationTypes[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveQualificationType entity)
        {
            entity.Id = qualificationTypeRepository.NewSequence("SYSTEM_MENU");
            return qualificationTypeRepository.Save(entity);
        }

        public bool Update(ArchiveQualificationType entity)
        {
            return qualificationTypeRepository.Update(entity);
        }

        public IList<ArchiveQualificationType> LoadAll()
        {
            return qualificationTypeRepository.LoadAll();
        }
        public IList<ArchiveQualificationType> LoadAll(string order, string where)
        {
            return qualificationTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveQualificationType";
            IList<ArchiveQualificationType> ls = qualificationTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = qualificationTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
