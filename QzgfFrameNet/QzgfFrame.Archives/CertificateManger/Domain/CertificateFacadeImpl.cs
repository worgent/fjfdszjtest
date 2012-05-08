using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.CertificateManger.Models;
using QzgfFrame.Archives.CertificateManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.CertificateManger.Domain
{
    public class CertificateFacadeImpl : CertificateFacade
    {
        private IRepository<ArchiveCertificate> certificateRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveCertificate Get(object id)
        {
            return certificateRepository.Get(id);
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
                string strsql = " CId='" + s + "' and RelationName='Certificate'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Certificate'";
                    result = relationRepository.DeleteHql(sql);
                    result = certificateRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Certificate'";
                    result = relationRepository.DeleteHql(sql);
                    result = certificateRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveCertificate GetHql(string certificateName)
        {
            string Hql = " CertificateName = '" + certificateName + "'";
            IList<ArchiveCertificate> Certificates = certificateRepository.LoadAll("Id", Hql);
            if (Certificates != null)
            {
                if (Certificates.Count > 0)
                    return Certificates[0];
                else
                    return null;
            }
            else return null;
        }
        public bool Save(ArchiveCertificate entity)
        {
            entity.Id = certificateRepository.NewSequence("SYSTEM_MENU");
            return certificateRepository.Save(entity);
        }

        public bool Update(ArchiveCertificate entity)
        {
            return certificateRepository.Update(entity);
        }

        public IList<ArchiveCertificate> LoadAll()
        {
            IList<ArchiveCertificate> certificates = new List<ArchiveCertificate>(); 
            ArchiveCertificate certificate = new ArchiveCertificate();
            certificate.Id = "0";
            certificate.CertificateName = "";
            certificates.Add(certificate);
            IList<ArchiveCertificate> archiveCertificates = certificateRepository.LoadAll();
            foreach(ArchiveCertificate  ac in archiveCertificates)
            {
                certificates.Add(ac);
            }
            return certificates;
        }
        public IList<ArchiveCertificate> LoadAll(string order, string where)
        {
            return certificateRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveCertificate";
            IList<ArchiveCertificate> ls = certificateRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = certificateRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
