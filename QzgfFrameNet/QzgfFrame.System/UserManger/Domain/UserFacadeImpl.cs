/****************************************************************** 
 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.System.DepartmentManger.Models;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core.Common;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.UserManger.Domain
{
    public class UserFacadeImpl : UserFacade
    {
        #region 变量定义
        //private IRepository<SystemDepartment> departmentRepository { set; get; }
        private IRepository<SystemUser> userRepository { set; get; }
        private IRepository<SystemUserrole> userroleRepository { set; get; }

        private string hql =
            @"select new SystemUser(main.Id,main.Username,main.Nickname,main.Tel,main.Email,main.Password,main.Departmentid,main.Areaid,main.Remark,main.State,
            case main.State when '1' then '启用' when '2' then '停用' else '删除' END as Statename) 
            from SystemUser main where main.State!='0'  ";

        #endregion


        #region 基本操作

        /// <summary>
        /// 查询单行记录
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemUser Get(object id)
        {
            SystemUser vuser = userRepository.GetbyHql(hql + "  and main.Id='" + id.ToString() + "'");

            if (vuser == null) return default(SystemUser);//
            //取得角色值
            IList<SystemUserrole> ls =
                userroleRepository.LoadAllbyHql("from SystemUserrole where Userid='" + id.ToString() + "'");
            string roldids = "";
            foreach (var vls in ls)
            {
                roldids += vls.Roleid + ";";
            }
            if (roldids != "")
                roldids = roldids.Substring(0, roldids.Length - 1);
            vuser.Roleids = roldids;
            //取得部门名称
            //IList<ArchiveCompany> companys = companyFacade.LoadAll();
            //SystemDepartment vdepartment = departmentRepository.Get(vuser.Departmentid);
            //vuser.Departmentname = vdepartment != null ? vdepartment.Departname : "";//部门名称
            //vuser.Isrepair =  vdepartment != null ?vdepartment.Isrepair:"";//是否代维
            return vuser;
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
                SystemUser entity = userRepository.Get(s);
                entity.State = "0";
                result = userRepository.Update(entity); //假删
            }
            return result;
        }


        public bool Save(SystemUser entity)
        {
            entity.Id = userRepository.NewSequence("SYSTEM_USER");

            string pwd = CommonHelper.GetMd5Str32(entity.Password);
            entity.Password = pwd;
            entity.Createdate = DateTime.Now;
            entity.State = "1";
            entity.Createman = "1";
            //保存关系表
            string vroleids = "";
            if (entity.Roleids != null)
                vroleids = entity.Roleids;

            string[] roleids = vroleids.Split(';');
            foreach (var roleid in roleids)
            {
                var entityuserrole = new SystemUserrole();
                entityuserrole.Id = userroleRepository.NewSequence("SYSTEM_USERROLE");
                entityuserrole.Roleid = roleid;
                entityuserrole.Userid = entity.Id;
                userroleRepository.Save(entityuserrole);
            }
            //保存用户信息
            return userRepository.Save(entity);
        }

        /// <summary>
        /// 用户更新
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(SystemUser entity)
        {
            entity.Id = entity.Id;
            string pwd = CommonHelper.GetMd5Str32(entity.Password);
            entity.Password = pwd;
            entity.State = "1";
            entity.Createman = "1";
            //保存关系表
            userroleRepository.DeletebyHql("from SystemUserrole where Userid='" + entity.Id + "'");
            string vroleids = "";
            if (entity.Roleids != null)
                vroleids = entity.Roleids;

            string[] roleids = vroleids.Split(';');

            foreach (var roleid in roleids)
            {
                var entityuserrole = new SystemUserrole();
                entityuserrole.Id = userroleRepository.NewSequence("SYSTEM_USERROLE");
                entityuserrole.Roleid = roleid;
                entityuserrole.Userid = entity.Id;
                userroleRepository.Save(entityuserrole);
            }
            return userRepository.Update(entity);
        }

        /// <summary>
        /// 页面列表信息
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <returns></returns>
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            string vSql = hql + @" and main.Id!='1' order by main." + sortname + " " + sortorder;
            IList<SystemUser> lsuser = userRepository.FindByPage(pageNo, pageSize, vSql);
            //IList<SystemDepartment> lsdepartments = departmentRepository.LoadAll();
            IList<SystemDepartment> lsdepartments = null;
            var jsonlist = (from vlsuser in lsuser
                            join vlsdepartments in lsdepartments
                                on vlsuser.Departmentid equals vlsdepartments.Id into joineduser
                            from vlsuser1 in joineduser.DefaultIfEmpty()
                            select new
                            {
                                vlsuser.Id,
                                vlsuser.Nickname,
                                vlsuser.Departmentid,
                                Departmentname = vlsuser1 != null ? vlsuser1.Departname : "",
                                vlsuser.Email,
                                vlsuser.Password,
                                vlsuser.Remark,
                                vlsuser.Statename,
                                vlsuser.Tel,
                                vlsuser.Username
                            }
                            ).ToArray();

            string rowsjson = JsonConvert.SerializeObject(jsonlist);
            int recordCount = userRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        /// <summary>
        /// 标识启用与停用
        /// </summary>
        /// <param name="id">主键</param>
        /// <param name="state">1:启用,2停用</param>
        /// <returns></returns>
        public bool UseState(string id, string state)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                SystemUser entity = userRepository.Get(s);
                entity.State = state; //停用标识
                result = userRepository.Update(entity); //假删
            }
            return result;
        }



        /// <summary>
        /// 用户是否存在,在增加用户时,如果系统中已经有改用户名称则不允许加入.
        /// </summary>
        /// <param name="name">用户名</param>
        /// <returns>是/否</returns>
        public bool IsUserNameExist(string name)
        {
            string vSql = @"select main from SystemUser main where main.State!=0 and main.Username='" + name + "'";
            SystemUser viewuser = userRepository.GetbyHql(vSql);

            if (viewuser != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        /// <summary>
        /// 用户登录验证
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="viewuser">用户视图信息</param>
        /// <returns></returns>
        public string CheckLogin(string username, string password, out SystemUser viewuser)
        {
            password = CommonHelper.GetMd5Str32(password);
            string vSql = @"select main from SystemUser main where main.State='1'  and main.Username='" + username +
                          "' and main.Password='" + password + "'";
            viewuser = userRepository.GetbyHql(vSql);
            if (viewuser != null)
            {
                return "true";
            }
            else
            {
                return "false";
            }
        }

        /// <summary>
        /// 修改用户密码
        /// </summary>
        /// <param name="userid"></param>
        /// <param name="oldpwd"></param>
        /// <param name="newpwd"></param>
        /// <returns>1:更新成功,2:原密码错误,3:更新失败</returns>
        public string ModifyPwd(string userid, string oldpwd, string newpwd)
        {
            oldpwd = CommonHelper.GetMd5Str32(oldpwd);
            string vSql = @"select main from SystemUser main where main.State='1'  and main.id='" + userid +
                          "' and main.Password='" + oldpwd + "'";
            var viewuser = userRepository.GetbyHql(vSql);
            if (viewuser != null)
            {
                newpwd = CommonHelper.GetMd5Str32(newpwd);
                viewuser.Password = newpwd;
                return userRepository.Update(viewuser) ? "1" : "3";//1:更新成功,3更新失败
            }
            else
            {
                return "2";//原密码错误码
            }
        }

        /// <summary>
        /// 保存角色
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool UpdateRole(SystemUser entity)
        {
            bool result=true;
            //分析提交的角色信息
            string vroleids = "";
            if (entity.Roleids != null)
                vroleids = entity.Roleids;
            string[] roleids = vroleids.Split(';');
            //保存关系表
            userroleRepository.DeletebyHql("from SystemUserrole where Userid='" + entity.Id + "'");
            foreach (var roleid in roleids)
            {
                var entityuserrole = new SystemUserrole();
                entityuserrole.Id = userroleRepository.NewSequence("SYSTEM_USERROLE");
                entityuserrole.Roleid = roleid;
                entityuserrole.Userid = entity.Id;
                result=result & userroleRepository.Save(entityuserrole);
            }
            //更新对像信息
            SystemUser newentity = Get(entity.Id);
            newentity.Areaid = entity.Areaid;
            newentity.Departmentid = entity.Departmentid;
            result = result & userRepository.Update(newentity);
            return result;
        }
        #endregion

        #region 保留

        public IList<SystemUser> LoadAll()
        {
            return userRepository.LoadAll();
        }

        public IList<SystemUser> LoadAll(string order, string where)
        {
            return userRepository.LoadAll(order, where);
        }

        //因业务模型跨包，所以将原本的实现类放到控制器中
        public IList<SystemUser> FindByPage(int pageNo, int pageSize, string hql)
        {
            return userRepository.FindByPage(pageNo, pageSize, hql);
        }

        public int FindByPageCount(string hql)
        {
            return userRepository.FindByPageCount(hql);
        }
        #endregion

    }
}
