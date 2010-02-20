package YzSystem.JMain;

import YzSystem.J_System.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.naming.*;
import java.util.*;
import java.util.Collection;
import java.sql.SQLException;

/**
 * <p>Title:EJB系统管理客户端接口函数</p>
 *
 * <p>Description: 提供远程系统管理客户端接口</p>
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
 * 说明:不使用EJB
 * 2008-05-24
 */

public class UtilEJB2Local {
    Context ctx; // 命名空间上下文
    HttpSession session; // http会话
    private String errorInfo = ""; // 异常信息
    /**
     * Init
     * 功能：初始化session,ctx,切记只能使用一次.
     */
    public void Init() throws wlglException {
        errorInfo = "";
        session = UtilWebTools.getSession();
            try {
                ctx = new InitialContext();
            } catch (NamingException ex) {
                errorInfo = wlglException.ProcessMainWebExceptionMessage("101",
                        "UtilEJB2Local 采用本地初始化，取本地Initcontext失败.",
                        ex);
            }
    }

    /**
     * checkLogin
     * 功能：登陆验证.
     * @param  userID String   用户id
     * @param  pwd String      密码
     * @return boolean         True成功
     */
    public boolean checkLogin(String userID, String pwd) throws wlglException {
        UtilDB utilDB= new UtilDB();
        ArrayList al=new ArrayList();
        al.add(userID);
        System.err.println("======================================="
                   +"开始exequeryonerow");
        ArrayList ow=utilDB.exeQueryOneRow("select passwd from tssystemgroupuser where theCode=?",al);
        System.err.println("======================================="
                   +"exequeryonerow成功");

        System.err.println("=====checklogin时:myrs值为:"+utilDB.myRs);
        if (ow.size()!=0) {
            if (pwd.equals(ow.get(0))) {
                return true;
            }
        }
        utilDB.closeCon();
        return false;
    }

    /**
     * getChildTrade
     * 功能：取得子交易
     * @param userSeqn String          用户Seqn
     * @param UpperTradeSeqn String    父交易Seqn
     * @return Collection              交易信息纪录集
     */
    public Collection getChildFunction(String powercode, String parenteqn) throws
            wlglException {
        errorInfo = "";
        Collection returnValue = new ArrayList();
        UtilDB utildb=new UtilDB();
        ArrayList params= new ArrayList();
        params.add(powercode);
        params.add(parenteqn);
        utildb.exeQuery("select a.TheCode,a.TheShortCode,a.TheName,a.ParentCode,a.childflag,a.refpage,a.img"
                +" from tsSystemFunction a,tsSystemGroupPower b"
                +" where b.FunCode=a.TheCode and b.FunisShow=1"
                +" and b.TheCode=? and a.ParentCode=? order by a.TheCode ", params);
        try{
            while (utildb.myRs.next()){
                BeanSystemFunctionCode btc=new BeanSystemFunctionCode();
                btc.setTheCode(utildb.myRs.getString(1));
                btc.setTheShortCode(utildb.myRs.getString(2));
                btc.setTheName(utildb.myRs.getString(3));
                btc.setParentCode(utildb.myRs.getString(4));
                btc.setChildFlag(utildb.myRs.getString(5));
                btc.setRefPage(utildb.myRs.getString(6));
                btc.setImg(utildb.myRs.getString(7));
                returnValue.add(btc);
        }
       } catch (SQLException ex) {
          wlglException.ProcessMainWebExceptionMessage("105",
                "", ex);
      }finally
      {
    	  utildb.closeCon();
      }

      return returnValue;
    }

    /**
     * getUserInfo
     * 功能：取得用户信息
     * @param userID String            用户ID
     * @param UpperTradeSeqn String    父交易Seqn
     * @return Collection              用户信息纪录集
     */
    public BeanLogin getUserInfo(String userID) throws wlglException {
        errorInfo = "";
        BeanLogin returnValue = null;
        UtilDB utilDB= new UtilDB();
        ArrayList al=new ArrayList();
        al.add(userID);
        ArrayList rs=utilDB.exeQueryOneRow(
        		"select a.TheCode loginSeqn,a.passwd passwd,a.TheName loginName,b.TheName employeName, " +
        		"d.TheName DeptName,c.TheName GroupName,c.TheCode GroupPowerCode,b.TheCode employeCode " +
        		" from tsSystemGroupUser a,tbDatumemployee b,tsSystemGroup c," +
        		"tbDatumDepartment d where a.employeCode=b.TheCode and a.GroupCode=c.TheCode and " +
        		"b.DepCode=d.TheCode and a.TheCode=?",al );
        if  (rs!=null){
            returnValue=new BeanLogin();
            returnValue.setLoginSeqn((String)rs.get(0));
            returnValue.setPasswd((String)rs.get(1));
            returnValue.setLoginName((String)rs.get(2));
            returnValue.setEmployeName((String)rs.get(3));
            returnValue.setDeptName((String)rs.get(4));
            returnValue.setGroupName((String)rs.get(5));
            returnValue.setGroupPowerCode((String)rs.get(6));
            returnValue.setEmployeCode((String)rs.get(7));
            returnValue.setLoginTime(UtilCommon.getTime());
        }
        utilDB.closeCon();
        return returnValue;
    }
    /**
     * getUserInfo
     * 功能：取得用户权限信息
     * @param userID String            用户ID
     * @param UpperTradeSeqn String    父交易Seqn
     * @return Collection              用户信息纪录集
     */
    
    public BeanSystemGroupPower getGroupPower(String powercode,String funcode) throws wlglException{
        errorInfo = "";
        BeanSystemGroupPower returnValue = null;
        UtilDB utilDB= new UtilDB();
        ArrayList al=new ArrayList();
        al.add(powercode);
        al.add(funcode);
        ArrayList rs=utilDB.exeQueryOneRow(
        		"select a.TheCode TheCode,a.Remark,a.FunCode,a.FunIsShow,a.PowerValue " +
        		" from tsSystemgrouppower a where " +
        		"  a.TheCode=? and a.Funcode=?",al );
        if  (rs!=null){
            returnValue=new BeanSystemGroupPower();
            returnValue.setTheCode((String)rs.get(0));
            returnValue.setRemark((String)rs.get(1));
            returnValue.setFunCode((String)rs.get(2));
            returnValue.setFunIsShow(rs.get(3).toString());
            returnValue.setPowerValue((String)rs.get(4));
        }
        utilDB.closeCon();
        return returnValue;
    }

    public UtilEJB2Local() {
    }

    public void setErrorInfo(String ErrorIno) {
        this.errorInfo = ErrorIno;
    }

    public String getErrorInfo() {
        return errorInfo;
    }


}
