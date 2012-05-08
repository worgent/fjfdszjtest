using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.RegisterDetailManger.Models;
using QzgfFrame.Supplies.RegisterDetailManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.RegisterDetailManger.Domain
{
    public class RegisterDetailFacadeImpl : RegisterDetailFacade
    {
        private IRepository<SuppliesRegisterDetail> registerDetailRepository { set; get; }
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesRegisterDetail Get(object id)
        {
            return registerDetailRepository.Get(id.ToString());
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " RegisterId='" + s + "'";
                else
                    hql += " or RegisterId='" + s + "'";
                string strsql = " CId='" + s + "' and RelationName='RegisterDetail'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='RegisterDetail'";
                    result = relationRepository.DeleteHql(sql);
                    result = registerDetailRepository.DeleteHql(hql);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='RegisterDetail'";
                    result = relationRepository.DeleteHql(sql);
                    result = registerDetailRepository.DeleteHql(hql);
                }
            }
            return result;
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string hql)
        {
            return registerDetailRepository.Update(" DelFlag='1' where " + hql);
        }
        public bool Save(SuppliesRegisterDetail entity, string no)
        {
            entity.Id = registerDetailRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "SuppliesType";
            sre1.ControllerName = "RegisterDetail";
            sre1.MId = entity.Id;
            sre1.CId = entity.SuppliesTypeId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = registerDetailRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesRegisterDetail entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='RegisterDetail'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "SuppliesType";
            sre1.ControllerName = "RegisterDetail";
            sre1.MId = entity.Id;
            sre1.CId = entity.SuppliesTypeId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            result = registerDetailRepository.Update(entity);

            return result;
        }

        public IList<SuppliesRegisterDetail> LoadAll()
        {
            return registerDetailRepository.LoadAll();
        }
        public IList<SuppliesRegisterDetail> LoadAll(string order, string where)
        {
            var lsRegisterDetail = registerDetailRepository.LoadAllbyHql("from SuppliesRegisterDetail where " + where);
            var lsSuppliesType = suppliesTypeRepository.LoadAllbyHql("from ArchiveSuppliesType");
            var jsonlist = (from vlsRegisterDetail in lsRegisterDetail
                            join vlsSuppliesType in lsSuppliesType
                                on vlsRegisterDetail.SuppliesTypeId equals vlsSuppliesType.Id into joinvlsRegisterDetailType
                            from vlsRegisterDetailType in joinvlsRegisterDetailType.DefaultIfEmpty()
                            select new SuppliesRegisterDetail
                            {
                                Id = vlsRegisterDetail.Id,
                                Num = vlsRegisterDetail.Num,
                                RegisterId = vlsRegisterDetail.RegisterId,
                                SuppliesTypeId = vlsRegisterDetail.SuppliesTypeId,
                                SuppliesTypeName = vlsRegisterDetailType != null ? vlsRegisterDetailType.SuppliesTypeName : "",
                                UnitName = vlsRegisterDetailType != null ? vlsRegisterDetailType.UnitName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            return jsonlist;// distributionDetailRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from SuppliesRegisterDetail";
            IList<SuppliesRegisterDetail> ls = registerDetailRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = registerDetailRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
