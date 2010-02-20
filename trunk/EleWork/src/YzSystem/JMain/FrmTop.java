package YzSystem.JMain;

import java.util.*;

import YzSystem.J_System.*;

/**
 * <p>Title:top.jsp窗体处理代码 </p>
 *
 * <p>Description: 处理top.jsp主程序</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-11  生成代码
 */
public class FrmTop {
	private String userId="";
    private String userName = "";
    private String groupName="";
    private String deptName="";
    private String menu = "";
    /**
     * FrmTop
     * 功能：初始化窗体：用户名，菜单.
     */
    public FrmTop() throws wlglException {
    	BeanLogin userInfo = (BeanLogin) UtilWebTools.getSession().
                                getAttribute("userInfo");
        if (userInfo != null) {
            setUserName(userInfo.getEmployeName());
            setUserId(userInfo.getLoginSeqn());
            setDeptName(userInfo.getDeptName());
            setGroupName(userInfo.getGroupName());
            setMenu(genMenu());
        }
    }

    /**
     * genMenu
     * 功能：根据用户seqn获取用户菜单.
     * @return String            菜单
     */
    public String getMenu() {
        return menu;
    }
    public String genMenu() {
        String returnValue = "";
        ArrayList functionList = (ArrayList) UtilWebTools.getSession().
                              getAttribute("userFunctions");
        if (functionList == null) {
            return returnValue;
        }
        Iterator itx = functionList.iterator();

        HttpBase td1 = new HttpBase();
        td1.setTag("td");
        td1.getProperties().setProperty("align","right");
        td1.getProperties().setProperty("valign","top");

        HttpBase table11=new HttpBase();
        table11.setTag("table");
        table11.getProperties().setProperty("border","0");
        table11.getProperties().setProperty("cellspacing","0");
        table11.getProperties().setProperty("cellpadding","0");
        table11.getProperties().setProperty("height","22");
        td1.getElements().add(table11);

        HttpBase tr111=new HttpBase();
        tr111.setTag("tr");
        table11.getElements().add(tr111);

        while (itx.hasNext()) {
            BeanSystemFunctionCode aFunction = (BeanSystemFunctionCode) itx.next();
            String imgName = aFunction.getImg();
            if (imgName == null) {
                imgName = "ccgl.gif";
            } else {
                if (imgName.equals("")) {
                    imgName = "ccgl.gif";
                }
            }
            HttpBase td1111=new HttpBase();
            td1111.setTag("td");
            td1111.getProperties().setProperty("width","120");
            td1111.getProperties().setProperty("nowrap","true");
            td1111.getProperties().setProperty("align","right");
            tr111.getElements().add(td1111);

            HttpBase a11111=new HttpBase();
            a11111.setTag("a");
            a11111.getProperties().setProperty("href","left.faces?tseqn="+aFunction.getTheCode());
            a11111.getProperties().setProperty("target","leftFrame");
            td1111.getElements().add(a11111);

            HttpBase img111111=new HttpBase();
            img111111.setTag("img");
            img111111.getProperties().setProperty("src","../images/"+imgName);
            img111111.getProperties().setProperty("width","18");
            img111111.getProperties().setProperty("height","18");
              img111111.getProperties().setProperty("hspace","2");
            img111111.getProperties().setProperty("align","absmiddle");
            img111111.getProperties().setProperty("border","0");
            a11111.getElements().add(img111111);

            HttpBase val111112=new HttpBase();
            val111112.setValue(aFunction.getTheName());
            a11111.getElements().add(val111112);

        }
        returnValue= td1.GenHtml();
        return returnValue;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getUserName() {
        return userName;
    }



	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
}
