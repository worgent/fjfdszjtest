using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.UnitManger.Domain
{
    public class UnitFacadeImpl:UnitFacade
    {
        private IRepository<ArchiveUnit> unitRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveUnit Get(object id)
        {
            return unitRepository.Get(id.ToString());
        }
        public ArchiveUnit Get(string order, string where)
        {
            IList<ArchiveUnit> bizTypes= unitRepository.LoadAll(order, where);
            if (bizTypes.Count > 0)
                return bizTypes[0];
            else
                return null;
        }
        public ArchiveUnit GetHql(string unitName)
        {
            string Hql = " UnitName like '%" + unitName + "%'";
            IList<ArchiveUnit> citys = unitRepository.LoadAll("Id", Hql);
            if (citys != null)
            {
                if (citys.Count > 0)
                    return citys[0];
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
                string strsql = " CId='" + s + "' and RelationName='Unit'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Unit'";
                    result = relationRepository.DeleteHql(sql);
                    result = unitRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Unit'";
                    result = relationRepository.DeleteHql(sql);
                    result = unitRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveUnit entity)
        {
            entity.Id = unitRepository.NewSequence("SYSTEM_MENU");
            return unitRepository.Save(entity);
        }

        public bool Update(ArchiveUnit entity)
        {
            return unitRepository.Update(entity);
        }

        public IList<ArchiveUnit> LoadAll()
        {
            return unitRepository.LoadAll();
        }
        public IList<ArchiveUnit> LoadAll(string order, string where)
        {
            return unitRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveUnit";
            IList<ArchiveUnit> ls = unitRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = unitRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
