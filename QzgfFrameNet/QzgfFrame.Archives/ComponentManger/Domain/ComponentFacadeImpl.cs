using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.ComponentManger.Domain
{
    public class ComponentFacadeImpl:ComponentFacade
    {
        private IRepository<ArchiveComponent> componentRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveComponent Get(object id)
        {
            return componentRepository.Get(id.ToString());
        }
        public ArchiveComponent Get(string order, string where)
        {
            IList<ArchiveComponent> bizTypes= componentRepository.LoadAll(order, where);
            if (bizTypes.Count > 0)
                return bizTypes[0];
            else
                return null;
        }
        public ArchiveComponent GetHql(string componentName)
        {
            string Hql = " ComponentName = '" + componentName + "'";
            IList<ArchiveComponent> citys = componentRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='Component'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Component'";
                    result = relationRepository.DeleteHql(sql);
                    result = componentRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Component'";
                    result = relationRepository.DeleteHql(sql);
                    result = componentRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveComponent entity)
        {
            entity.Id = componentRepository.NewSequence("SYSTEM_MENU");
            return componentRepository.Save(entity);
        }

        public bool Update(ArchiveComponent entity)
        {
            return componentRepository.Update(entity);
        }

        public IList<ArchiveComponent> LoadAll()
        {
            IList<ArchiveComponent> components =new  List<ArchiveComponent>();

            ArchiveComponent component = new ArchiveComponent();
            component.Id = "0";
            component.ComponentName = "";
            components.Add(component);
            IList<ArchiveComponent> archiveComponents = componentRepository.LoadAll();
            foreach (ArchiveComponent ac in archiveComponents)
            {
                components.Add(ac);
            }
            return components;
        }
        public IList<ArchiveComponent> LoadAll(string order, string where)
        {
            return componentRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveComponent";
            IList<ArchiveComponent> ls = componentRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = componentRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
