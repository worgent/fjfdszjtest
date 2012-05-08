using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Core.JSON;

namespace QzgfFrame.Archives.CompanyManger.Domain
{
    public class CompanyFacadeImpl : CompanyFacade
    {
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveCompany Get(object id)
        {
            return companyRepository.Get(id.ToString());
        }
        public ArchiveCompany GetHql(string CompanyName)
        {
            string Hql = " CompanyName ='" + CompanyName + "'";
            IList<ArchiveCompany> entitys = companyRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='Company'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Company'";
                    result = relationRepository.DeleteHql(sql);
                    result = companyRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Company'";
                    result = relationRepository.DeleteHql(sql);
                    result = companyRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveCompany entity)
        {
            entity.Id = companyRepository.NewSequence("SYSTEM_MENU");
            return companyRepository.Save(entity);
        }

        public bool Update(ArchiveCompany entity)
        {
            return companyRepository.Update(entity);
        }

        public IList<ArchiveCompany> LoadAll()
        {
            return companyRepository.LoadAll();
        }
        public IList<ArchiveCompany> LoadAll(string order, string where)
        {
            if (where == null || where == "") 
                return companyRepository.LoadAll();
            else
                return companyRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveCompany";
            IList<ArchiveCompany> ls = companyRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = companyRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 公司下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox(string hql)
        {
            IList<ArchiveCompany> ls = new List<ArchiveCompany>();
            if (hql != "")
                ls = LoadAll("Id", hql); //state!=0
            else
                ls = LoadAll();
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.CompanyName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
