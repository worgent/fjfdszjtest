package YzSystem.JMain;

import  YzSystem.J_System.*;
import java.util.*;

import javax.faces.context.*;
import javax.naming.*;
import javax.servlet.http.*;
import javax.faces.el.ValueBinding;
import javax.faces.application.*;
import javax.faces.FactoryFinder;
import javax.servlet.ServletContext;

/**
 * <p>Title:本地工具</p>
 *
 * <p>Description: 处理本地相关工具</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-07  生成代码
 */
public class UtilWebTools {

    private String errorInfo = ""; //错误信息


    public static String readInitparam(String aParam) {
        String val = "";
        HttpSession session = getSession();
        return val;
    }
    public static void responseWrite(String str){
        try{
            getResponse().getWriter().write(str);
        }
        catch(Exception e){
             new wlglException("写html页面流失败!" ,e);
        }
    }
    public static void printrequestparams() {
        HttpServletRequest request=getRequest();
        Enumeration paramnames= request.getParameterNames();
        while(paramnames.hasMoreElements()){
            String paramname=(String)paramnames.nextElement();
            System.out.println("================="+paramname+"=====================");
            String[] paramvalues=request.getParameterValues(paramname) ;
            if (paramvalues.length==1){
                String paramvalue=paramvalues[0];
                if (paramvalue.length()==0)
                    System.out.println("no value");
                else
                    System.out.println(paramvalue);
            }
            else {
                for (int i=0; i<paramvalues.length;i++){
                    System.out.println(paramvalues[i]);

                }
            }
            System.out.println("");
        }
    }

    /**
     * getSession
     * 功能：取得命名上下文.
     * @return HttpSession         http会话
     */
    public static HttpSession getSession() {
        FacesContext faceContext = FacesContext.getCurrentInstance();
        return ((HttpSession) faceContext.
                getExternalContext().
                getSession(true)
                );
    }

    /**
     * ClearSessionENV
     * 功能：创立位置http内容
     * @param tradeseqn String      名字
     * @return Object   返回的valueBinding
     */
    public static void ClearSessionENV() {
        HttpSession session = getSession();
        session.removeAttribute("userInfo");
        session.removeAttribute("userFunctions");
    }

    /**
     * checkRight
     * 功能：检查权限
     * @param aTrade BeanTradeCode 交易
     * @param aRight String        要查的增删改查权限
     * @return boolean             有无权限
     */
    public static boolean checkRight(BeanSystemGroupPower aGroupPower, String aRight) {
    	
        boolean val = false;
        if (aGroupPower != null) {
            if (UtilCommon.NVL(aRight).equals("")) {
                val = true;
            } else {
                if ((aRight.equals("query")) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).length() >= 1) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).substring(0, 1)).equals(
                            "1")) {
                    val = true;
                }
                if ((aRight.equals("edit")) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).length() >= 2) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).substring(1, 2)).equals(
                            "1")) {
                    val = true;
                }

                if ((aRight.equals("add")) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).length() >= 3) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).substring(2, 3)).equals(
                            "1")) {
                    val = true;
                }
                if ((aRight.equals("del")) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).length() >= 4) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).substring(3, 4)).equals(
                            "1")) {
                    val = true;
                }
                if ((aRight.equals("print")) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).length() >= 5) &&
                    (UtilCommon.NVL(aGroupPower.getPowerValue()).substring(4, 5)).equals(
                            "1")) {
                    val = true;
                }
            }
        }

        return val;
    }

    /**
     * getValueBinding
     * 功能：取得ValueBinding.
     * @param aName String     名字
     * @return Object   返回的valueBinding
     */
    public static Object getValueBinding(String aName) {
        Object returnValue = null;
        ApplicationFactory factory = (ApplicationFactory)
                                     FactoryFinder.getFactory(FactoryFinder.
                APPLICATION_FACTORY);
        Application application = factory.getApplication();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        returnValue = application.createValueBinding("#{" + aName + "}").
                      getValue(facescontext);
        return returnValue;
    }

    /**
     * getRequestParameter
     * 功能：取得request的parameter.
     * @param akey String     参数名
     * @return String         parameter值
     */
    public static String getRequestParameter(String akey) {
        String returnValue=getRequestParameterN(akey);
        if (returnValue == null) {
            returnValue = "";
        }
        return returnValue;
    }
    public static String getRequestParameterN(String akey) {
        String returnValue = null;
        FacesContext faceContext = FacesContext.getCurrentInstance();
        Map requestParameterMap = faceContext.getExternalContext().
                                  getRequestParameterMap();
        if (requestParameterMap != null) {
            returnValue = (String) requestParameterMap.get(akey);
        }
        return returnValue;
    }

    public static String getRequestPath() {
        FacesContext faceContext = FacesContext.getCurrentInstance();
        String aVal=getRequest().getRequestURI();
        aVal=UtilCommon.Replace(".jsp", ".faces",aVal);
        return aVal;
    }
    public static HttpServletRequest getRequest() {
        HttpServletRequest aval=null;
        FacesContext faceContext = FacesContext.getCurrentInstance();
        aval=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        return aval;
    }
    public static HttpServletResponse getResponse() {
        HttpServletResponse aval=null;
        FacesContext faceContext = FacesContext.getCurrentInstance();
        aval=(HttpServletResponse)faceContext.getExternalContext().getResponse();
        return aval;
    }

    public static ServletContext getServletContext() {
        ServletContext aval=null;
        FacesContext faceContext = FacesContext.getCurrentInstance();
        aval=(ServletContext)faceContext.getExternalContext().getContext();
        return aval;
    }


    /**
     * getSessionAttributer
     * 功能：取得session的Attributer.
     * @param akey String     参数名
     * @return String         parameter值
     */
    public static Object getSessionAtrributerO(String akey) {
        HttpSession session=getSession();
        Object val=session.getAttribute(akey);
        return val;
    }
    public static String getSessionAtrributerS(String akey) {
        HttpSession session=getSession();
        String val=UtilCommon.NVL((String)(session.getAttribute(akey)));
        return val;
    }
    public static int getSessionAtrributerI(String akey) {
        int val;
        HttpSession session=getSession();
        val=Integer.parseInt(UtilCommon.NVL((String)(session.getAttribute(akey))));
        return val;
    }


    /**
     * getTradeName
     * 功能：根据交易seqn取得交易信息.
     * @param seqn String     交易seqn
     * @return BeanTradeCode         交易信息
     */
    public static BeanSystemFunctionCode getTradeInfoBySeqn(String seqn) {
    	BeanSystemFunctionCode returnValue = null;
        HttpSession session = getSession();
        ArrayList functions = (ArrayList) session.getAttribute("userFunctions");
        if (seqn.equals("0")) {
            returnValue = new BeanSystemFunctionCode();
            returnValue.setTheCode("0");
            if (functions != null) {
                returnValue.setChildFlag("1");
            } else {
                returnValue.setChildFlag("0");
            }
            returnValue.setChildSystemFunctionCode(functions);
        } else {
            returnValue = getTradeInfoByRoot(functions, seqn);
        }
        return returnValue;
    }

    private static BeanSystemFunctionCode getTradeInfoByRoot(ArrayList trades,
            String seqn) {
    	BeanSystemFunctionCode returnValue = null;
        if ((trades == null) | (seqn == null)) {
            return returnValue;
        }
        Iterator itx = trades.iterator();
        while (itx.hasNext() & (returnValue == null)) {
        	BeanSystemFunctionCode atrade = (BeanSystemFunctionCode) itx.next();
            if (UtilCommon.NVL(atrade.getTheCode()).equals(seqn)) {
                returnValue = atrade;
                return returnValue;
            } else {
                if ((UtilCommon.NVL(atrade.getChildFlag()).equals("1")) &
                    (atrade.getChildSystemFunctionCode() != null)) {
                    returnValue = getTradeInfoByRoot(atrade.getChildSystemFunctionCode(),
                            seqn);
                }
            }
        }

        return returnValue;
    }

    /**
     * getUtilEJB2Local
     * 功能：取得session中的utilEJB2Local对象.
     * @return UtilEJB2Local         session中的utilEJB2Local对象.
     */
    public static UtilEJB2Local getUtilEJB2Local() throws wlglException {
        HttpSession session = getSession();
        UtilEJB2Local utilEJB2Local = (UtilEJB2Local) session.getAttribute(
                "utilEJB2Local");
        if (utilEJB2Local == null) {
            utilEJB2Local = new UtilEJB2Local();
            utilEJB2Local.Init();
            session.setAttribute("utilEJB2Local", utilEJB2Local);
        }
        return utilEJB2Local;
    }

    public UtilWebTools() {
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

}
