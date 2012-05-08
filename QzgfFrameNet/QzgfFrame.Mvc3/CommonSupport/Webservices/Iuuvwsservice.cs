

using System;
using System.Collections.Generic;
using System.Data;
using System.Web.Services;

namespace QzgfFrame.Mvc3.CommonSupport.Webservices
{
    //[WebServiceBinding(ConformsTo = WsiProfiles.None)]
    public interface Iuuvwsservice
    {

        //[WebMethod(Description = "根据“角色名称”、“应用程序ID”、“应用程序密钥”获取“用户”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{{\"12\",\"31\",...},{\"usr01\",\"isr02\",...}}</font>）")]
         string[][] GetADUserNameByRoleAndAppId(string roleName, string appID, string appKey);

        //[WebMethod(Description = "根据“角色ID”、“应用程序ID”、“应用程序密钥”获取“用户”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable UP_GetUsersInfoByRoleIDAndAppId(string roleID, string appID, string appKey);

        //[WebMethod(Description = "根据“用户AD名”、“应用程序ID”、“应用程序密钥”获取用户在系统中的“角色名称”集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"查看者\",\"审核人\",...}</font>）")]
         string[] GetRoleNameByADUserNameAndAppId(string adUserName, string appID, string appKey);

        //[WebMethod(Description = "根据“待查用户中文名”、“角色ID”、“应用程序ID”、“应用程序密钥”查找所有匹配的用户。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable QueryARole(string userNamePart, string roleID, string appID, string appKey);

        //[WebMethod(Description = "根据“一组UserID”、“应用程序ID”、“应用程序密钥”查找这些用户对应的角色。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable QueryByUserIDList(List<int> userIDLst, string appID, string appKey);

        //[WebMethod(Description = "获取当前用户的县公司部门经理或市公司中心经理。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"usr02\",\"31\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其部门经理或中心经理！\",\"此用户不存在，无法获取其部门经理或中心经理！\"}）")]
         string[] GetDeptManagerByADUserName(string adUserName);

        //[WebMethod(Description = "获取指定组织的下层组织集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）（<font color=\"red\">注：要获取‘根节点’请传入 0 </font>）")]
         DataTable GetSecondLayerByDeptID(string deptID);

        //[WebMethod(Description = "获取泉州移动所有组织集合。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）（<font color=\"red\">注：无参数 </font>）")]
         DataTable GetAllDeptInfo();

        //[WebMethod(Description = "根据AD用户名获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetUserInfoByADUserName(string adUserName);

        //[WebMethod(Description = "根据用户ID获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetUserInfoById(int userID);

        //[WebMethod(Description = "根据员工编号MIS_UserId获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetUserInfoByMIS_UserId(string MIS_UserId);

        //[WebMethod(Description = "根据员工姓名获取用户的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetUserInfoByUsername(string username);

        //[WebMethod(Description = "获取当前用户“直属公司”的‘Dept_ID’和‘公司名称’。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"20\",\"晋江分公司\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其直属公司！\",\"此用户不存在，无法获取其直属公司！\"}）")]
         string[] GetDirectlyUnderCompanyByADUser(string adUserName);


        //[WebMethod(Description = "获取当前用户“直属公司”或“部门”的‘Dept_ID’和‘公司名称’(组织架构第二层)。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：{\"20\",\"晋江分公司\"}</font>）（注：失败返回错误信息{\"此用户不存在，无法获取其直属公司！\",\"此用户不存在，无法获取其直属公司！\"}）")]
         string[] GetDirectlyUnderCompanyOrDeptByADUser(string adUserName);

        //[WebMethod(Description = "根据组织ID获取组织的相关信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetDeptInfoById(string deptID);

        //小谢增加 2009年9月11日16:26:19
        // [WebMethod(Description = "根据用户ID来获取用户所有有权访问的系统。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetAppByUserID(int userID);

        //小谢增加2009年9月15日9:45:47
        //[WebMethod(Description = "获取所有应用系统类别。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetAppTypes();

        //小谢增加 2010年1月4日15:08:01


        //[WebMethod(Description = "获取用户定制的系统列表(首页12个)。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable get_flowRss(int v_userid);

        //[WebMethod(Description = "获取用户定制的全部系统列表。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable get_flowRssAll(int v_userid);

        //[WebMethod(Description = "获取用户定制的系统信息。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable get_userAppRss_ByappId(string v_appid, int v_userid);

        // [WebMethod(Description = "获取用户定制的系统的最大排序。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：Int32</font>）")]
         Int32 get_appMaxOrder(int v_userid);

        // [WebMethod(Description = "用户进行系统定制。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
         bool Insert_flowrss(int v_userid, int v_orderNum, string v_appid);

        //[WebMethod(Description = "用户进行系统定制更新。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
         bool Update_flowRss(int v_userid, int v_orderNum, string v_appid, int v_rssid);
        //[WebMethod(Description = "用户进行系统定制取消。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：bool</font>）")]
         bool delete_flowRss(int v_userid, string v_appid);


        //小谢增加2009年9月15日9:45:47
        // [WebMethod(Description = "获取全部应用系统。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetAllApp();

        //[WebMethod(Description = "获取应用系统中的所有用户。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetUsersByAppId(string appID, string appKey);

        //[WebMethod(Description = "获取应用系统中所有‘有效’且‘未过期’的角色。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：DataTable</font>）")]
         DataTable GetAllRolesByAppId(string appID, string appKey);

        // [WebMethod(Description = "判断指定人员是否为“县公司部门经理或以上领导”。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
         bool IsDeptManagerAndUpwards(string adUserName);

        //[WebMethod(Description = "判断指定人员是否为“市公司中心经理或以上领导”。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
         bool IsCenterManagerAndUpwards(string adUserName);

        //[WebMethod(Description = "判断指定文件是否允许上传。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）")]
         bool IsFileAllowedToUpload(string fileName);

        // [WebMethod(Description = "是否为“泉州移动”组织下的员工。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）", MessageName = "IsQZMCCEmployeeByUserID")]
         bool IsQZMCCEmployee(int userID);

        // [WebMethod(Description = "是否为“泉州移动”组织下的员工。<font color=\"gray\">［<a href=\"mailto:13905068831@139.com;15980018216@139.com;hyf@xminfoport.com?subject=[UUV Web Services]\">技术支持</a>］</font><br/>（<font color=\"red\">返回：true 或 false</font>）", MessageName = "IsQZMCCEmployeeByUserName")]
        // bool IsQZMCCEmployee(string userName);
         bool IsFileAllowedToUpload(int fileName);

        /// <summary>
        /// 获取系统中包含的角色
        /// </summary>
        /// <param name="appId">应用系统appid</param>
        /// <returns></returns>
         DataSet GetRoleByAppId(string appId);



         string GetUserStrIdByUserId(int userId);

    }
}
