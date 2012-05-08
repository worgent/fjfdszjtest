using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.PortTypeManger.Models;
using QzgfFrame.Archives.PortTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.PortTypeManger.Domain
{
    public class PortTypeFacadeImpl : PortTypeFacade
    {
        private IRepository<ArchivePortType> portTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchivePortType Get(object id)
        {
            return portTypeRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='PortType'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='PortType'";
                    result = relationRepository.DeleteHql(sql);
                    result = portTypeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='PortType'";
                    result = relationRepository.DeleteHql(sql);
                    result = portTypeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchivePortType GetHql(string portTypeName)
        {
            string Hql = " PortTypeName = '"+portTypeName+"'";
            IList<ArchivePortType> portTypes = portTypeRepository.LoadAll("Id", Hql);
            if (portTypes != null)
            {
                if (portTypes.Count > 0)
                    return portTypes[0];
                else
                    return null;
            }
            else return null;
        }
        public bool Save(ArchivePortType entity)
        {
            entity.Id = portTypeRepository.NewSequence("SYSTEM_MENU");
            return portTypeRepository.Save(entity);
        }

        public bool Update(ArchivePortType entity)
        {
            return portTypeRepository.Update(entity);
        }

        public IList<ArchivePortType> LoadAll()
        {
            return portTypeRepository.LoadAll();
        }
        public IList<ArchivePortType> LoadAll(string order, string where)
        {
            return portTypeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchivePortType";
            IList<ArchivePortType> ls = portTypeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = portTypeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
