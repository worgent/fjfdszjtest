package YzSystem.J_System;

import YzSystem.JMain.*;

import java.util.*;
import javax.faces.context.*;
import javax.naming.*;
import javax.servlet.http.*;

/**
 * <p>Title:login.jsp窗体处理代码 </p>
 *
 * <p>Description: 处理login.jsp主程序</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-07  生成代码
 * 
 * @author szj
 * @version 2.0
 * 说明:修改这个类使支持Tomcat,不用访问EJB.
 * 2008-5-23
 */

public class FrmLogin {
    private String userID; // 用户ID
    private String pwd; // 密码
    private String hint; // 提示
    FacesContext faceContext;
    Hashtable menu;
    Context ctx;
    UtilEJB2Local utilEJB2Local;

    /**
     * doLogin
     * 功能：登陆验证.
     * @return boolean         ""succ"成功,"fail"失败.
     */
    public String doLogin() throws Exception {
        String returnValue = "fail";
        hint = "";
        if (UtilWebTools.getUtilEJB2Local().checkLogin(userID, pwd)) {
            UtilWebTools.ClearSessionENV();
            setLoginInfo(userID);
            returnValue = "succ";
            /*
            BeanLogin userinfo = ((BeanLogin) session.getAttribute(
                   "userInfo"));
                   */
            BeanLogin userinfo = ((BeanLogin) UtilWebTools.getSession().
            		getAttribute("userInfo"));
            getFunctions(userinfo.getGroupPowerCode());
        } else {
            if (UtilWebTools.getUtilEJB2Local().getErrorInfo().equals("")) {
                hint = new String("登陆失败，您的账号或密码有误！");
            } else {
                hint = UtilWebTools.getUtilEJB2Local().getErrorInfo();
            }
        }
        if (!hint.equals("")) {
            returnValue = "fail";
        }
        return returnValue;
    }


    /**
     * setLoginInfo
     * 功能：根据用户ID设置用户登陆信息到session.
     * @param userID String    用户ID
     */
    public void setLoginInfo(String userID) throws wlglException {
        BeanLogin groupuser = UtilWebTools.getUtilEJB2Local().getUserInfo(
                userID);
        UtilWebTools.getSession().setAttribute("userInfo", groupuser);
        
    }
    
    /**
     * getTrades
     * 功能：根据用户seqn取得用户交易信息列表放到session.
     * @param userSeqn String    用户seqn
     * @return ArrayList       交易表
     */
    private ArrayList getFunctions(String powercode) throws wlglException {
        ArrayList returnValue;
        returnValue = (ArrayList) UtilWebTools.getSession().getAttribute("userFunctions");
        if (returnValue == null) {
            returnValue = getChildFunctions(powercode, "0");
            UtilWebTools.getSession().setAttribute("userFunctions", returnValue);
        }
        return returnValue;
    }

    /**
     * getChildTrades
     * 功能：根据用户ID,upperseqn取得用户交易信息列表.可以无限层次取得.
     * @param usersSeqn String    用户Seqn
     * @param upperSeqn String    上级交易Seqn
     * @return ArrayList          交易表
     */
    private ArrayList getChildFunctions(String powercode, String upperSeqn) throws
            wlglException {
        ArrayList returnValue = null;
        if ((upperSeqn == null) | (powercode == null)) {
            return returnValue;
        }
        returnValue = (ArrayList) UtilWebTools.getUtilEJB2Local().
                      getChildFunction(powercode, upperSeqn);
        Iterator itx = returnValue.iterator();
        while (itx.hasNext()) {
            BeanSystemFunctionCode aFunction = (BeanSystemFunctionCode) itx.next();
            if (aFunction.getChildFlag().equals("1")) {
                ArrayList childtrades = getChildFunctions(
                		powercode, aFunction.getTheCode());
                aFunction.setChildSystemFunctionCode(childtrades);
            }
        }
        return returnValue;

    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUserID(String UserID) {
        this.userID = UserID;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUserID() {
        return userID;
    }

    public String getHint() {
        return hint;
    }

    public FrmLogin() throws wlglException {
        utilEJB2Local = UtilWebTools.getUtilEJB2Local();
    }
	

}
