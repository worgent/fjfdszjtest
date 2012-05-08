using System;
using System.Collections.Generic;
using System.Web;
using QzgfFrame.System.LogManger.Domain;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.MenufieldManger.Models;
using QzgfFrame.System.PermissionsManger.Domain;
using QzgfFrame.System.UserManger.Domain;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Cache;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;

namespace QzgfFrame.Controllers.CommonSupport
{
    public class CurrentUser
    {
        #region 变量定义

        //spring中注入
        public ICache cacheFacade { set; get; }
        public UserFacade userFacade { set; get; }
        public PermissionsFacade permissionsFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        public SystemLogFacade logFacade { set; get; }
         
        //方法中设置这些属性的值
        //用户信息
        public SystemUser UserInfo { get; set; }
        //菜单权限
        public IList<SystemMenu> MenuPermission { get; set; }
        //数据权限
        public IList<SystemMenufield> MenufieldPermission { get; set; }

        #endregion

        #region 基本操作

        /// <summary>
        /// 在登录成功后,将用户信息加入到session中
        /// </summary>
        /// <param name="userid"></param>
        public void SetUser(string userid)
        {
            UserInfo = userFacade.Get(userid);
            //放入区域名称，公司名称，是否代维信息
            if(UserInfo.Areaid!=null)
            {
                ArchiveDistrict district = districtFacade.Get(UserInfo.Areaid);
                UserInfo.Areaname = (district == null ? "" : district.DistrictName);
                UserInfo.LEVELNO = (district == null ? "" : district.HNo.ToString()); 
            }
            if(UserInfo.Departmentid!=null)
            {
                ArchiveCompany company = companyFacade.Get(UserInfo.Departmentid);
                UserInfo.Departmentname = (company == null ? "" : company.CompanyName);
                UserInfo.Isrepair = company == null ? "" : company.IsMaintain.ToString();
            }

            //数据权限
            MenuPermission = permissionsFacade.GetMenubyUserId(UserInfo.Id);
            MenufieldPermission = permissionsFacade.GetMenufieldUserId(UserInfo.Id);

            cacheFacade.SetSessionCache(Constant.Userkey, this);
        }

        /// <summary>
        /// 从缓存中取得用户信息
        /// </summary>
        /// <returns>false:seesion过期,true:可用</returns>
        public bool IsHaveUser()
        {
            if (cacheFacade.GetSessionCache(Constant.Userkey) == null)
            {
                return false;
            }
            else
            {
                var u = (CurrentUser)cacheFacade.GetSessionCache(Constant.Userkey);
                UserInfo = u.UserInfo;
                MenuPermission = u.MenuPermission;
                MenufieldPermission = u.MenufieldPermission;
                return true;
            }
        }

        /// <summary>
        /// 移除用户session
        /// </summary>
        public void RemoveUser()
        {
            cacheFacade.RemoveSessionCache(Constant.Userkey);
        }

        #endregion


        #region 加强

        /// <summary>
        /// 调用session存储信息
        /// </summary>
        /// <param name="key"></param>
        /// <param name="obj"></param>
        public void SetSession(string key, object obj)
        {
            cacheFacade.SetSessionCache(key, obj);
        }

        /// <summary>
        /// 取得session信息
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public object GetSession(string key)
        {
            return cacheFacade.GetSessionCache(key);
        }

        #endregion


    }
}
