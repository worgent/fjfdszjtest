using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SelfHelpFactoryManger.Domain
{
    public class SelfHelpFactoryFacadeImpl:SelfHelpFactoryFacade
    {
        private IRepository<ArchiveSelfHelpFactory> selfHelpFactoryRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSelfHelpFactory Get(object id)
        {
            return selfHelpFactoryRepository.Get(id.ToString());
        }
        public ArchiveSelfHelpFactory GetHql(string abbrevia)
        {
            string Hql = " Abbrevia = '" + abbrevia + "'";
            IList<ArchiveSelfHelpFactory> entitys = selfHelpFactoryRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='SelfHelpFactory'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpFactory'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpFactoryRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SelfHelpFactory'";
                    result = relationRepository.DeleteHql(sql);
                    result = selfHelpFactoryRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSelfHelpFactory entity)
        {
            entity.Id = selfHelpFactoryRepository.NewSequence("SYSTEM_MENU");
            return selfHelpFactoryRepository.Save(entity);
        }

        public bool Update(ArchiveSelfHelpFactory entity)
        {
            return selfHelpFactoryRepository.Update(entity);
        }

        public IList<ArchiveSelfHelpFactory> LoadAll()
        {
            return selfHelpFactoryRepository.LoadAll();
        }
        public IList<ArchiveSelfHelpFactory> LoadAll(string order, string where)
        {
            return selfHelpFactoryRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveSelfHelpFactory";
            IList<ArchiveSelfHelpFactory> ls = selfHelpFactoryRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = selfHelpFactoryRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 自助设备厂家下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll(); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.Abbrevia,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
