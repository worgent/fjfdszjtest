/*
 * 文件名.........: ExamineeInfoFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 考生信息业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamTypeManger.Models;
using QzgfFrame.Exam.ExamTypeManger.Domain;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Core.Common;

namespace QzgfFrame.Exam.ExamineeInfoManger.Domain
{
    public class ExamineeInfoFacadeImpl : ExamineeInfoFacade
    {
        private IRepository<ExamExamineeInfo> examineeinfoRepository { set; get; }
        private DiplomaFacade diplomaFacade { set; get; }
        private ExamTypeFacade examtypeFacade { set; get; }

        public ExamExamineeInfo Get(object id)
        {
            return examineeinfoRepository.Get(id.ToString());
        }
        public string Get(string order, string where)
        {
            string str="";
            IList<ExamExamineeInfo> ExamineeInfo = examineeinfoRepository.LoadAll(order, where);
            return str;
        }

        public ExamineeInfo GetExamineeInfo(object id)
        {
            ExamineeInfo ExamineeInfo = new ExamineeInfo();
            ExamineeInfo.ExamExamineeInfo = examineeinfoRepository.Get(id.ToString());
            return ExamineeInfo;
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
            foreach (var s in idarr)
            {
                result = examineeinfoRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamExamineeInfo entity)
        {
            if (entity.PassWord == "")
                entity.PassWord = "123456";
            string pwd = CommonHelper.GetMd5Str32(entity.PassWord);
            entity.PassWord = pwd;
            entity.ID = examineeinfoRepository.NewSequence("SYSTEM_MENU");
            if (entity.LoginName == "")
                entity.LoginName = entity.ID;
            return examineeinfoRepository.Save(entity);
        }

        public bool Update(ExamExamineeInfo entity)
        {
            string pwd = CommonHelper.GetMd5Str32(entity.PassWord);
            entity.PassWord = pwd;
            return examineeinfoRepository.Update(entity);
        }
        /// <summary>
        /// 考生登录验证
        /// </summary>
        /// <param name="loginname">考生登录名</param>
        /// <param name="password">密码</param>
        /// <param name="viewexaminee">考生视图信息</param>
        /// <returns></returns>
        public string CheckLogin(string loginname, string password, out ExamExamineeInfo viewexaminee)
        {
            password = CommonHelper.GetMd5Str32(password);
            string vSql = @"select main from ExamExamineeInfo main where main.LoginName='" + loginname +
                          "' and main.PassWord='" + password + "'";
            viewexaminee = examineeinfoRepository.GetbyHql(vSql);
            if (viewexaminee != null)
            {
                return "true";
            }
            else
            {
                return "false";
            }
        }

         /// <summary>
        /// 修改考生密码
        /// </summary>
        /// <param name="examineeid">考生id</param>
        /// <param name="oldpwd">旧密码</param>
        /// <param name="newpwd">新密码</param>
        /// <returns>1:更新成功,2:原密码错误,3:更新失败</returns>
        public string ModifyPwd(string examineeid, string oldpwd, string newpwd)
        {
            oldpwd = CommonHelper.GetMd5Str32(oldpwd);
            string vSql = @"from ExamExamineeInfo where id='" + examineeid +
                          "' and PassWord='" + oldpwd + "'";
            var viewexaminee = examineeinfoRepository.GetbyHql(vSql);
            if (viewexaminee != null)
            {
                newpwd = CommonHelper.GetMd5Str32(newpwd);
                viewexaminee.PassWord = newpwd;
                return examineeinfoRepository.Update(viewexaminee) ? "1" : "3";//1:更新成功,3更新失败
            }
            else
            {
                return "2";//原密码错误码
            }
        }


        /// <summary>
        /// 考生登录名是否存在,在增加考生登录名时,如果系统中已经有该登录名则不允许加入.
        /// </summary>
        /// <param name="loginname">考生登录名</param>
        /// <returns>是/否</returns>
        public bool IsLoginNameExist(string loginname)
        {
            string vSql = @"from ExamExamineeInfo where LoginName='" + loginname + "'";
            ExamExamineeInfo viewexaminee = examineeinfoRepository.GetbyHql(vSql);

            if (viewexaminee != null)    //存在
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public IList<ExamExamineeInfo> LoadAll()
        {
            return examineeinfoRepository.LoadAll();
        }
        public IList<ExamExamineeInfo> LoadAll(string order, string where)
        {
            return examineeinfoRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamExamineeInfo";
            IList<ExamExamineeInfo> eei = examineeinfoRepository.FindByPage(pageNo, pageSize, hql);
            IList<ExamineeInfo> ei = new List<ExamineeInfo>();
            for (int i = 0; i < eei.Count; i++)
            {
                ExamineeInfo ExamineeInfo = new ExamineeInfo();
                ExamineeInfo.ID = eei[i].ID;
                ExamineeInfo.FullName = eei[i].FullName;
                if (eei[i].Sex == 0)
                    ExamineeInfo.Sex = "男";
                else
                    ExamineeInfo.Sex = "女";
                ExamineeInfo.LoginName = eei[i].LoginName;
                ExamineeInfo.IDCardNumber = eei[i].IDCardNumber;
                ExamineeInfo.MobileNumber = eei[i].MobileNumber;
                ArchiveDiploma diploma = diplomaFacade.Get(eei[i].DiplomaID);
                ExamineeInfo.Diploma = diploma.DiplomaName;
                ExamineeInfo.BirthDate = eei[i].BirthDate;
                ExamType examtype= examtypeFacade.Get(eei[i].ExamTypeID);
                ExamineeInfo.ExamType = examtype.Type;
                ExamineeInfo.ExamExamineeInfo = eei[i];
                ei.Add(ExamineeInfo);
            }
            string rowsjson = JsonConvert.SerializeObject(ei);
            int recordCount = examineeinfoRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
