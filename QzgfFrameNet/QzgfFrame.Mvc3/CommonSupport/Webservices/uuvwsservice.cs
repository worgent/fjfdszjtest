#region License

/*
 * Copyright ?2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#endregion


#region Imports

using System;
using System.Collections.Generic;
using System.Data;
using System.Web.Services;
using System.Xml.Serialization;

#endregion

namespace QzgfFrame.Mvc3.CommonSupport.Webservices
{
    [WebServiceBinding(ConformsTo = WsiProfiles.None)]
    public class uuvwsservice : Iuuvwsservice
    {

        #region WX_HongYueFeng Powered 2009-08-10!

        [WebMethod(Description = "根据“角色名称”、“应用程序ID”、“应用程序密钥”获取“用户”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{{\"12\",\"31\",...},{\"usr01\",\"isr02\",...}}</font>）")]
        public string[][] GetADUserNameByRoleAndAppId(string roleName, string appID, string appKey)
        {
//wp 已支持角色中的组织。
            var ds = new string[5][];
            return ds;
        }

        [WebMethod(Description = "根据“角色ID”、“应用程序ID”、“应用程序密钥”获取“用户”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable UP_GetUsersInfoByRoleIDAndAppId(string roleID, string appID, string appKey)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据“用户AD名”、“应用程序ID”、“应用程序密钥”获取用户在系统中的“角色名称”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"查看者\",\"审核人\",...}</font>）")]
        public string[] GetRoleNameByADUserNameAndAppId(string adUserName, string appID, string appKey)
        {
            //wp 已支持角色中的组织。
            var ds = new string[5];
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据“待查用户中文名”、“角色ID”、“应用程序ID”、“应用程序密钥”查找所有匹配的用户。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable QueryARole(string userNamePart, string roleID, string appID, string appKey)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据“一组UserID”、“应用程序ID”、“应用程序密钥”查找这些用户对应的角色。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable QueryByUserIDList(List<int> userIDLst, string appID, string appKey)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取当前用户的县公司部门经理或市公司中心经理。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"usr02\",\"31\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其部门经理或中心经理！\",\"此用户不存在，无法获取其部门经理或中心经理！\"}）")]
        public string[] GetDeptManagerByADUserName(string adUserName)
        {
            var ds = new string[5];
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取指定组织的下层组织集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）（<font color=\"red\">注：要获取‘根节点’请传入 0 </font>）")]
        public DataTable GetSecondLayerByDeptID(string deptID)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取泉州移动所有组织集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）（<font color=\"red\">注：无参数 </font>）")]
        public DataTable GetAllDeptInfo()
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据AD用户名获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetUserInfoByADUserName(string adUserName)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据用户ID获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetUserInfoById(int userID)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据员工编号MIS_UserId获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetUserInfoByMIS_UserId(string MIS_UserId)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据员工姓名获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetUserInfoByUsername(string username)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取当前用户“直属公司”的‘Dept_ID’和‘公司名称’。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"20\",\"晋江分公司\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其直属公司！\",\"此用户不存在，无法获取其直属公司！\"}）")]
        public string[] GetDirectlyUnderCompanyByADUser(string adUserName)
        {
            var ds = new string[5];
            //ds.Tables =new DataTable();
            ds[0] = "hello ";
            ds[1] = "world";
            ds[2] = "say no";
            return ds;
        }


        [WebMethod(Description = "获取当前用户“直属公司”或“部门”的‘Dept_ID’和‘公司名称’(组织架构第二层)。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"20\",\"晋江分公司\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其直属公司！\",\"此用户不存在，无法获取其直属公司！\"}）")]
        public string[] GetDirectlyUnderCompanyOrDeptByADUser(string adUserName)
        {
            var ds = new string[5];
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "根据组织ID获取组织的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetDeptInfoById(string deptID)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        //小谢增加 2009年9月11日16:26:19
        // [WebMethod(Description = "根据用户ID来获取用户所有有权访问的系统。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetAppByUserID(int userID)
        {
//wp 已支持角色中的组织。
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        //小谢增加2009年9月15日9:45:47
        [WebMethod(Description = "获取所有应用系统类别。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetAppTypes()
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        //小谢增加 2010年1月4日15:08:01

        #region 用户定制系统的WebService

        [WebMethod(Description = "获取用户定制的系统列表(首页12个)。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable get_flowRss(int v_userid)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取用户定制的全部系统列表。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable get_flowRssAll(int v_userid)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取用户定制的系统信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable get_userAppRss_ByappId(string v_appid, int v_userid)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        // [WebMethod(Description = "获取用户定制的系统的最大排序。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：Int32</font>）")]
        public Int32 get_appMaxOrder(int v_userid)
        {
            return 16;
        }

        // [WebMethod(Description = "用户进行系统定制。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
        public bool Insert_flowrss(int v_userid, int v_orderNum, string v_appid)
        {
            return true;
        }

        [WebMethod(Description = "用户进行系统定制更新。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
        public bool Update_flowRss(int v_userid, int v_orderNum, string v_appid, int v_rssid)
        {
            return true;
        }

        [WebMethod(Description = "用户进行系统定制取消。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
        public bool delete_flowRss(int v_userid, string v_appid)
        {
            return true;
        }

        #endregion

        //小谢增加2009年9月15日9:45:47
        // [WebMethod(Description = "获取全部应用系统。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetAllApp()
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取应用系统中的所有用户。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetUsersByAppId(string appID, string appKey)
        {
//wp 已支持角色中的组织。
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        [WebMethod(Description = "获取应用系统中所有‘有效’且‘未过期’的角色。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
        public DataTable GetAllRolesByAppId(string appID, string appKey)
        {
            var ds = new DataTable();
            //ds.Tables =new DataTable();
            return ds;
        }

        // [WebMethod(Description = "判断指定人员是否为“县公司部门经理或以上领导”。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
        public bool IsDeptManagerAndUpwards(string adUserName)
        {
            return true;
        }

        [WebMethod(Description = "判断指定人员是否为“市公司中心经理或以上领导”。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
        public bool IsCenterManagerAndUpwards(string adUserName)
        {
            return true;
        }

        [WebMethod(Description = "判断指定文件是否允许上传。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
        public bool IsFileAllowedToUpload(string fileName)
        {
            return true;
        }

        [WebMethod(Description = "是否为“泉州移动”组织下的员工。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）", MessageName = "IsQZMCCEmployeeByUserID")]//IsQZMCCEmployeeByUserID
        public bool IsQZMCCEmployee(int userID)
        {
            return true;
        }

         [WebMethod(Description = "是否为“泉州移动”组织下的员工。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）", MessageName = "IsQZMCCEmployeeByUserName")]
        public bool IsQZMCCEmployee(string userName)
        {
            return true;
        }

        [WebMethod(Description = "是否为“泉州移动”组织下的员工。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）", MessageName = "TT")]
        public bool IsFileAllowedToUpload(int fileName)
         {
             return true;
         }


        #endregion


        #region

        /// <summary>
        /// 获取系统中包含的角色
        /// </summary>
        /// <param name="appId">应用系统appid</param>
        /// <returns></returns>
        public DataSet GetRoleByAppId(string appId)
        {
            var ds = new DataSet();
            //ds.Tables =new DataTable();
            return ds;
        }



        public string GetUserStrIdByUserId(int userId)
        {
            return "GetUserStrIdByUserId";
        }

        #endregion
    }
}
