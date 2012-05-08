using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Core.JSON;

namespace QzgfFrame.Archives.FactoryManger.Domain
{
    public class FactoryFacadeImpl:FactoryFacade
    {
        private IRepository<ArchiveFactory> factoryRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveFactory Get(object id)
        {
            return factoryRepository.Get(id.ToString());
        }
        public ArchiveFactory GetHql(string abbrevia)
        {
            string Hql = " Abbrevia = '" + abbrevia + "'";
            IList<ArchiveFactory> entitys = factoryRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='Factory'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Factory'";
                    result = relationRepository.DeleteHql(sql);
                    result = factoryRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Factory'";
                    result = relationRepository.DeleteHql(sql);
                    result = factoryRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveFactory entity)
        {
            entity.Id = factoryRepository.NewSequence("SYSTEM_MENU");
            return factoryRepository.Save(entity);
        }

        public bool Update(ArchiveFactory entity)
        {
            return factoryRepository.Update(entity);
        }

        public IList<ArchiveFactory> OSZLoadAll()
        {
            IList<ArchiveFactory> Factorys = new List<ArchiveFactory>();
            ArchiveFactory Factory = new ArchiveFactory();
            Factory.Id = "0";
            Factory.Abbrevia = "";
            Factorys.Add(Factory);
            IList<ArchiveFactory> ArchiveFactorys = factoryRepository.LoadAll();
            foreach (ArchiveFactory ac in ArchiveFactorys)
            {
                Factorys.Add(ac);
            }
            return Factorys;
        }
        public IList<ArchiveFactory> LoadAll()
        {
            return factoryRepository.LoadAll();
        }
        public IList<ArchiveFactory> LoadAll(string order, string where)
        {
            return factoryRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveFactory";
            IList<ArchiveFactory> ls = factoryRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = factoryRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 厂家下拉框,
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
