using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.OutletsTypeManger.Domain
{
    public class OutletsTypeFacadeImpl : OutletsTypeFacade
    {
        private IRepository<ArchiveOutletsType> outletsTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveOutletsType Get(object id)
        {
            return outletsTypeRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='OutletsType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='OutletsType'";
                    result = relationRepository.DeleteHql(sql);
                    result = outletsTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='OutletsType'";
                    result = relationRepository.DeleteHql(sql);
                    result = outletsTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveOutletsType GetHql(string outletsTypeName)
        {
            string Hql = " OutletsTypeName = '" + outletsTypeName + "'";
            IList<ArchiveOutletsType> OutletsTypes = outletsTypeRepository.LoadAll("Id", Hql);
            if (OutletsTypes != null)
            {
                if (OutletsTypes.Count > 0)
                    return OutletsTypes[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveOutletsType entity)
        {
            entity.Id = outletsTypeRepository.NewSequence("SYSTEM_MENU");
            return outletsTypeRepository.Save(entity);
        }

        public bool Update(ArchiveOutletsType entity)
        {
            return outletsTypeRepository.Update(entity);
        }

        public IList<ArchiveOutletsType> LoadAll()
        {
            return outletsTypeRepository.LoadAll();
        }
        public IList<ArchiveOutletsType> LoadAll(string order, string where)
        {
            return outletsTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveOutletsType";
            IList<ArchiveOutletsType> ls = outletsTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = outletsTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
