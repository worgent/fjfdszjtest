using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Page;

namespace QzgfFrame.Archives.DistrictManger.Domain
{
    public class DistrictFacadeImpl : DistrictFacade
    {
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveDistrict Get(object id)
        {
            string hql =
            @"select new ArchiveDistrict(ad.Id,ad.DistrictName,ad.HNo,ad.ParentId,ad.SeqNo,
ad.State,adex.DistrictName as ParentName,ad.TypeFlag)
                      from ArchiveDistrict ad,ArchiveDistrict adex  where ad.ParentId=adex.Id ";

            return districtRepository.GetbyHql(hql + " and ad.Id='" + id.ToString() + "'");
        }
        public ArchiveDistrict GetHql(string DistrictName)
        {
            string Hql = " DistrictName ='" + DistrictName + "'";
            IList<ArchiveDistrict> entitys = districtRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
        }
        public ArchiveDistrict GetLikeHql(string DistrictName)
        {
            string Hql = " DistrictName like '%" + DistrictName + "%'";
            IList<ArchiveDistrict> entitys = districtRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='District'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='District'";
                    result = relationRepository.DeleteHql(sql);
                    result = districtRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='District'";
                    result = relationRepository.DeleteHql(sql);
                    result = districtRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveDistrict entity)
        {
            entity.Id = districtRepository.NewSequence("SYSTEM_MENU");
            return districtRepository.Save(entity);
        }

        public bool Update(ArchiveDistrict entity)
        {
            return districtRepository.Update(entity);
        }

        public IList<ArchiveDistrict> LoadAll()
        {
            return districtRepository.LoadAll("SeqNo", "1=1");
            //return districtRepository.LoadAll();
        }
        public IList<ArchiveDistrict> LoadAll(string order, string where)
        {
            return districtRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = " from  ArchiveDistrict where State='0' and ParentId!='-1'";
            IList<ArchiveDistrict> dcity = districtRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(dcity);
            int recordCount = districtRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        //得到父结点信息
        public string GetFather(string selid, int hno)
        {
            string vSql = " from ArchiveDistrict main where main.State='0' and  main.HNo>=" + hno + " order by main.SeqNo";
            
            IList<ArchiveDistrict> relist = districtRepository.LoadAllbyHql(vSql);

            var list = (from vlist in relist
                        select new
                        {
                            text = vlist.DistrictName,
                            id = vlist.Id.Trim(),
                            url = "",
                            icon = "",
                            pid = vlist.ParentId.Trim(),
                            ischecked = vlist.Id.Trim() != selid ? false : true,
                            hno = vlist.HNo
                        }
                       ).ToArray();


            return JSONHelper.ToJSON(list);
        }

        /// <summary>
        /// 区县下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox(string id, int hno)
        {
            var ls = GetDistricts(id,hno); //state!=0
            IList<ArchiveDistrict> citys = GetCitys(id, hno);
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.DistrictName,
                                id = a.Id,
                                cid = citys[0].Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
        /// <summary>
        /// 根据用户区域Id,所属城市,
        /// </summary>
        /// <returns>json数据格式</returns>
        public IList<ArchiveDistrict> GetCitys(string id,int hno)
        {
            IList<ArchiveDistrict> Districts = districtRepository.LoadAll("SeqNO", " Id='" + id + "'");
            hno = Districts[0].HNo;
            IList<ArchiveDistrict> Citys=new List<ArchiveDistrict>();
            if (hno > 2)//查上级
                Citys = GetPCity(id,2);
            else if(hno<2)
                Citys = GetCCity(id,2);
            else
               Citys= districtRepository.LoadAll("SeqNO", "HNO=2 and Id='"+id+"'");

            return Citys;
        }
        /// <summary>
        /// 根据用户区域Id,所属区县,
        /// </summary>
        /// <returns>json数据格式</returns>
        public IList<ArchiveDistrict> GetDistricts(string id, int hno)
        {
            IList<ArchiveDistrict> Districts = districtRepository.LoadAll("SeqNO", " Id='" + id + "'");
            hno = Districts[0].HNo;
            IList<ArchiveDistrict> Citys = new List<ArchiveDistrict>();
            if (hno > 3)//查上级
                Citys = GetPCity(id,3);
            else if (hno < 3)
                Citys = GetCCity(id, 3);
            else
            {
                Citys = GetCCity(id, 3);
                ArchiveDistrict district = districtRepository.Get(id);
                Citys.Add(district);
            }

            return Citys;
        }
        /// <summary>
        /// 根据用户区域Id,查询区县,并组查询条件,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetWSearch(string id, int hno, string obj)
        {
            string strhql = "";
            IList<ArchiveDistrict> Districts = GetDistricts(id, hno);
            if (Districts != null)
            {
                strhql += "(";
                foreach (ArchiveDistrict ad in Districts)
                {
                    if (obj != null)
                    {
                        if (strhql == "(")
                            strhql += obj + ".DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or " + obj + ".DistrictId='" + ad.Id + "'";
                    }
                    else
                    {
                        if (strhql == "(")
                            strhql += " DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or DistrictId='" + ad.Id + "'";
                    }
                }
                strhql += ")";
            }
            return strhql;
        }
        /// <summary>
        /// 根据用户区域Id,查询区县,包含本身，可包含市级,并组查询条件,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetSearch(string id, int hno, string obj)
        {
            string strhql = "";
            IList<ArchiveDistrict> Districts = GetDistricts(id, hno);
            if (Districts != null)
            {
                strhql += "(";
                foreach (ArchiveDistrict ad in Districts)
                {
                    if (obj != null)
                    {
                        if (strhql == "(")
                            strhql += obj+".DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or "+obj+".DistrictId='" + ad.Id + "'";
                    }
                    else
                    {
                        if (strhql == "(")
                            strhql += " DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or DistrictId='" + ad.Id + "'";
                    }
                }
                if (hno == 2)
                {
                    if (obj != null)
                    {
                        if (strhql == "(")
                            strhql += obj + ".DistrictId='" + id + "'";
                        else
                            strhql += " or " + obj + ".DistrictId='" + id + "'";
                    }
                    else
                    {
                        if (strhql == "(")
                            strhql += " DistrictId='" + id + "'";
                        else
                            strhql += " or DistrictId='" + id + "'";
                    }
                }
                 strhql +=")";
            }
            return strhql;
        }
        /// <summary>
        /// 根据用户区域Id,查询区县,并组查询条件,可为空值,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetPSearch(string id, int hno, string obj)
        {
            string strhql = "";
            IList<ArchiveDistrict> Districts = GetDistricts(id, hno);
            if (Districts != null)
            {
                strhql += "(";
                foreach (ArchiveDistrict ad in Districts)
                {
                    if (obj != null)
                    {
                        if (strhql == "(")
                            strhql += obj + ".DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or " + obj + ".DistrictId='" + ad.Id + "'";
                    }
                    else
                    {
                        if (strhql == "(")
                            strhql += " DistrictId='" + ad.Id + "'";
                        else
                            strhql += " or DistrictId='" + ad.Id + "'";
                    }
                }
                if (hno == 2)
                {
                    if (obj != null)
                    {
                        if (strhql == "(")
                            strhql += obj + ".DistrictId='" + id + "'";
                        else
                            strhql += " or " + obj + ".DistrictId='" + id + "'";
                    }
                    else
                    {
                        if (strhql == "(")
                            strhql += " DistrictId='" + id + "'";
                        else
                            strhql += " or DistrictId='" + id + "'";
                    }
                } 
                if (obj != null)
                {
                    if (strhql == "(")
                        strhql += obj + ".DistrictId='' or " + obj + ".DistrictId is null";
                    else
                        strhql += " or " + obj + ".DistrictId='' or " + obj + ".DistrictId is null";
                }
                else
                {
                    if (strhql == "(")
                        strhql += " DistrictId='' or DistrictId is null";
                    else
                        strhql += " or DistrictId='' or DistrictId is null";
                }
                strhql += ")";
            }
            return strhql;
        }
        /// <summary>
        /// 根据用户区域Id,查上级区域,
        /// </summary>
        /// <returns>json数据格式</returns>
        private IList<ArchiveDistrict> GetPCity(string id, int hno)
        {
            IList<ArchiveDistrict> Citys = districtRepository.LoadAll("SeqNO", "Id='" + id + "'"); 
            if (Citys != null)
            {
                IList<ArchiveDistrict> aryCitys = districtRepository.LoadAll("SeqNO", "Id='" + Citys[0].ParentId + "'");
                if (aryCitys != null)
                {
                    if (aryCitys[0].HNo == hno)
                        return aryCitys;
                    else
                    {
                        aryCitys = GetPCity(aryCitys[0].Id, hno);
                        return aryCitys;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }
        /// <summary>
        /// 根据用户区域Id,查下级区域,
        /// </summary>
        /// <returns>json数据格式</returns>
        private IList<ArchiveDistrict> GetCCity(string id, int  hno)
        {
            IList<ArchiveDistrict> Citys = districtRepository.LoadAll("SeqNO", "ParentId='" + id + "'");
            if (Citys != null)
            {
                if (Citys.Count > 0)
                {
                    if (Citys[0].HNo == hno)
                    {
                        IList<ArchiveDistrict> aryCitys = new List<ArchiveDistrict>();

                        foreach (ArchiveDistrict ad in Citys)
                        {
                            aryCitys.Add(ad);
                            IList<ArchiveDistrict> arCitys = GetCCity(ad.Id, hno);
                            if (arCitys != null)
                            {
                                foreach (ArchiveDistrict ard in arCitys)
                                {
                                    aryCitys.Add(ard);
                                }
                            }
                        }
                        return aryCitys;
                    }
                    else
                    {
                        IList<ArchiveDistrict> aryCitys = new List<ArchiveDistrict>();
                        foreach (ArchiveDistrict ad in Citys)
                        {
                            if (ad.TypeFlag == 3)
                                aryCitys.Add(ad);
                            IList<ArchiveDistrict> arCitys = GetCCity(ad.Id, hno);
                            if (arCitys != null)
                            {
                                foreach (ArchiveDistrict ard in arCitys)
                                {
                                    aryCitys.Add(ard);
                                }
                            }
                        }
                        return aryCitys;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }

    }
}
