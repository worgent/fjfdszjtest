using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.CertificateManger.Models;

namespace QzgfFrame.Archives.CertificateManger.Domain
{
    public interface CertificateFacade
    {
        ArchiveCertificate Get(object id);
        ArchiveCertificate GetHql(string certificateName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveCertificate entity);

        bool Update(ArchiveCertificate entity);

        IList<ArchiveCertificate> LoadAll();

        IList<ArchiveCertificate> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
