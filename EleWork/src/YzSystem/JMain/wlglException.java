package YzSystem.JMain;

import java.io.*;

/**
 * <p>Title:异常处理</p>
 *
 * <p>Description: 异常处理 </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-07  生成代码
 */
public class wlglException extends Exception {

    /**
     * ProcessWebExceptionMessage
     * 功能：加工Web异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 远程异常码aCode分为7位:
     * 第1-3位:WLW(物流Web)
     * 第4位:1公用程序,2基础信息,3系统管理,4仓储管理,5搬运管理,6配送管理,7财务管理
     * 第5-7位:用户定义
     */
    public static String ProcessWebExceptionMessage(String aCode,
            String myString, Exception E) {
        String yc="";
        String ycxx="";
        if (wlglException.class.isInstance(E)){
            yc="YzSystemException";
            ycxx="myString";
        } else{
            yc=E.toString();
            ycxx=E.getMessage();
        }
        String returnValue = "YzSystem" + aCode +
                             "\n<br>-------------异常描述开始("+myString+")-------------------" +
                             "\n<br>本地描述:" + myString +
                             "\n<br>异常:" + yc +
                             "\n<br>异常消息:" + ycxx
                             ;
        System.err.println(returnValue);
        StringWriter sw = new StringWriter();
        PrintWriter ps = new PrintWriter(sw, true);

        E.printStackTrace(ps);
//                    out.println("<P>" + sw.getBuffer() + "</P>");
        String errorInfo = "\n<br>异常堆栈:" + sw.getBuffer()+
                           "\n<br>---------------异常描述结束("+myString+")-----------------\n";
        returnValue+=errorInfo;
        return returnValue;
    }

    /**
     * ProcessMainWebExceptionMessage
     * 功能：加工Web公用程序模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     * WLW1001 UtilWebTools.getRemoteInitialContext | 获取远程InitContext失败.
     * WLW1101 UtilEJB2Local.Init:UtilEJB2Local | 采用本地初始化，取本地Initcontext失败.
     * WLW1102 UtilEJB2Local.getEJBBean | 查找ejb bean失败.
     * WLW1103 UtilEJB2Local.getSessionLogin | 建立sessionlogin,出现远程异常.
     * WLW1104 UtilEJB2Local.getSessionLogin | 建立sessionlogin,出现建立异常.
     * WLW1105 UtilEJB2Local.checkLogin | checklogin,出现远程异常.
     * WLW1106 UtilEJB2Local.getChildTrade | getChildTrade,出现远程异常.
     * WLW1107 UtilEJB2Local.getUserInfo | getUserInfo,出现远程异常.
     * WLW1108 UtilEJB2Local.getSessionCommon | 建立sessionCommon,出现远程异常.
     * WLW1109 UtilEJB2Local.getSessionCommon | 建立sessionCommon,出现建立异常.
     * WLW1110 UtilEJB2Local.getPubSeqn | 取序列号,出现建立异常.
     * WLW1201 UtilDB.getCon | 取环境信息错误.
     * WLW1202 UtilDB.getCon | 接数据连接池失败.
     * WLW1203 UtilDB.exeUpdate | updateSQL失败.
     * WLW1204 UtilDB.exeQuery | QuerySQL失败.
     * WLW1205 UtilDB.exeUpdate | 关闭连接失败.
     * WLW1206 UtilDB.CloseCon | 关闭连接失败.
     * WLW1207 UtilDB.getCount | 获取数据纪录数目失败
     * WLW1208 UtilDB.execQueryoneRow | 执行单行返回结果失败
     * WLW1209 UtilDB.execQueryOneRow | 执行单行返回结果失败
     * WLW1210 UtilDB.getRowData | 取行数据失败
     */
    public static String ProcessMainWebExceptionMessage(String aCode,
            String myString, Exception E) throws wlglException {
        String errInfo = ProcessWebExceptionMessage("1" + aCode, myString, E);
        if (!aCode.equals("")) {
            throw new wlglException(errInfo);
        }
        return errInfo;
    }

    /**
     * ProcessBasicWebExceptionMessage
     * 功能：加工Web基础管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessBasicWebExceptionMessage(String aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("2" + aCode, myString, E);
    }

    /**
     * ProcessSysWebExceptionMessage
     * 功能：加工Web系统管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessSysWebExceptionMessage(String aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("3" + aCode, myString, E);
    }

    /**
     * ProcessStorageWebExceptionMessage
     * 功能：加工Web仓储管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessStorageWebExceptionMessage(String aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("4" + aCode, myString, E);
    }

    /**
     * ProcessConveryWebExceptionMessage
     * 功能：加工Web搬运管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessConveryWebExceptionMessage(String aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("5" + aCode, myString, E);
    }

    /**
     * ProcessAttemperWebExceptionMessage
     * 功能：加工Web调度管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessAttemperWebExceptionMessage(String
            aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("6" + aCode, myString, E);
    }

    /**
     * ProcessAccountWebExceptionMessage
     * 功能：加工Web帐务管理模块异常消息
     * @param aCode String     传入的异常代码
     * @param myString String  传入的自定义异常信息
     * @param Exception E      传入的系统异常
     * 参考:
     * ProcessWebExceptionMessage;
     * 错误消息列表:
     */
    public static String ProcessAccountWebExceptionMessage(String aCode,
            String myString, Exception E) {
        return ProcessWebExceptionMessage("7" + aCode, myString, E);
    }

    public wlglException() {
    }

    public wlglException(String message) {
        super(message);
    }


    public wlglException(String message, Throwable cause) {
        super(message, cause);
    }


    public wlglException(Throwable cause) {
        super(cause);
    }
}
