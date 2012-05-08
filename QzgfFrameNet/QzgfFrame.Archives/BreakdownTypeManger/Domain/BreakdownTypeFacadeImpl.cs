using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.BreakdownTypeManger.Models;
using QzgfFrame.Archives.BreakdownTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.BreakdownTypeManger.Domain
{
    public class BreakdownTypeFacadeImpl:BreakdownTypeFacade
    {
        private IRepository<ArchiveBreakdownType> breakdownTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveBreakdownType Get(object id)
        {
            return breakdownTypeRepository.Get(id.ToString());
        }
        public ArchiveBreakdownType Get(string order, string where)
        {
            IList<ArchiveBreakdownType> bizTypes= breakdownTypeRepository.LoadAll(order, where);
            if (bizTypes.Count > 0)
                return bizTypes[0];
            else
                return null;
        }
        public ArchiveBreakdownType GetHql(string breakdownTypeName)
        {
            string Hql = " BreakdownTypeName ='" + breakdownTypeName + "'";
            IList<ArchiveBreakdownType> citys = breakdownTypeRepository.LoadAll("Id", Hql);
            if (citys != null)
            {
                if (citys.Count > 0)
                    return citys[0];
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
                string strsql = " CId='" + s + "' and RelationName='BreakdownType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='BreakdownType'";
                    result = relationRepository.DeleteHql(sql);
                    result = breakdownTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='BreakdownType'";
                    result = relationRepository.DeleteHql(sql);
                    result = breakdownTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveBreakdownType entity)
        {
            entity.Id = breakdownTypeRepository.NewSequence("SYSTEM_MENU");
            return breakdownTypeRepository.Save(entity);
        }

        public bool Update(ArchiveBreakdownType entity)
        {
            return breakdownTypeRepository.Update(entity);
        }

        public IList<ArchiveBreakdownType> LoadAll()
        {
            return breakdownTypeRepository.LoadAll();
        }
        public IList<ArchiveBreakdownType> LoadAll(string order, string where)
        {
            return breakdownTypeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveBreakdownType";
            IList<ArchiveBreakdownType> ls = breakdownTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = breakdownTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
