package YzSystem.JMain;

import java.util.*;

import YzSystem.J_System.*;

/**
 * <p>Title:left.jsp窗体处理代码 </p>
 *
 * <p>Description: 处理left.jsp主程序</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-12  生成代码
 */
public class FrmLeft {

    private String rootMenu = ""; // 使用的主菜单名
    private String menuDetail = ""; // 菜单信息
    private BeanSystemFunctionCode mainTrade = null; // 请求的主菜单详情
    private String firstMenuPage = ""; // 第一个菜单
    /**
     * FrmTop
     * 功能：初始化窗体：主菜单名.
     */
    public FrmLeft() {
        String tseqn = UtilWebTools.getRequestParameter("tseqn");
        String aTradeName = "加载中...";
        if (!tseqn.equals("")) {
            mainTrade = UtilWebTools.getTradeInfoBySeqn(tseqn);
            if (mainTrade != null) {
                aTradeName = mainTrade.getTheName();
            }
        }
        if (aTradeName != null) {
            setRootMenu(aTradeName);
        }

        String DetailMenu = genDetailMenu();

        if (DetailMenu != null) {
            setMenuDetail(DetailMenu);
        }

        BeanSystemFunctionCode aFirstFuction;

        aFirstFuction = genFirstFunction(mainTrade);

        String aFirstMenuPage;
        if (aFirstFuction != null) {
            aFirstMenuPage = UtilCommon.NVL(aFirstFuction.getRefPage());
        } else {
            aFirstMenuPage = "";
        }

        if (aFirstMenuPage.equals("")) {
            aFirstMenuPage = "main/noFound.faces?tseqn=" + tseqn;
        } else {
            if (aFirstFuction!=null){
                aFirstMenuPage = aFirstMenuPage + "?tseqn=" + UtilCommon.NVL(
                		aFirstFuction.getTheCode());
            }
            else
                aFirstMenuPage = "main/noFound.faces?tseqn=" + tseqn;

        }
        setFirstMenuPage(aFirstMenuPage);

    }

    /**
     * genFirstMenuPage
     * 功能：产生第一个菜单的功能页面;
     * @return String     产生的菜单连接
     */
    private BeanSystemFunctionCode genFirstFunction(BeanSystemFunctionCode aFunction) {
    	BeanSystemFunctionCode returnValue = null;
        if (aFunction == null) {
            return returnValue;
        }
        if (UtilCommon.NVL(aFunction.getChildFlag()).equals("2")) {
            returnValue = aFunction;
            return returnValue;
        } else {
            if (UtilCommon.NVL(aFunction.getChildFlag()).equals("1")) {
                ArrayList myTrades = aFunction.getChildSystemFunctionCode();
                if (myTrades == null) {
                    return returnValue;
                }
                Iterator itx = myTrades.iterator();
                String curHasChild = "1";
                while (itx.hasNext() & curHasChild.equals("1")) {
                	BeanSystemFunctionCode curTrade = (BeanSystemFunctionCode) itx.next();
                    if (UtilCommon.NVL(curTrade.getChildFlag()).equals("1")) {
                        returnValue = genFirstFunction(curTrade);
                        if (returnValue != null) {
                            curHasChild = "2";
                            return returnValue;
                        }
                    } else {
                        curHasChild = "2";
                        returnValue = curTrade;
                    }
                }
            }
        }

        return returnValue;
    }


    /**
     * genDetailMenu
     * 功能：产生菜单详情.
     * @return String     产生的菜单详情
     */
    private String genDetailMenu() {
        String returnValue = "";
        if (mainTrade != null) {
            if (mainTrade.getChildFlag().equals("1") & (mainTrade.getChildSystemFunctionCode()!= null)) {
                 returnValue= genMenuByRoot(mainTrade.getChildSystemFunctionCode()).GenHtml();
            }
        }
        return returnValue;
    }

    /**
     * genMenuByRoot
     * 功能：产生菜单详情.
     * @param trades ArrayList 要产生的菜单交易
     * @return String     产生的菜单详情
     */
    private HttpBase genMenuByRoot(ArrayList trades) {
        Iterator itx = trades.iterator();

        HttpBase val= new HttpBase();

        while (itx.hasNext()) {
        	BeanSystemFunctionCode trade = (BeanSystemFunctionCode) itx.next();
            if (!trade.getChildFlag().equals("1")) {
                HttpBase tr1=new HttpBase();
                tr1.setTag("tr");
                val.getElements().add(tr1);

                HttpBase td11=new HttpBase();
                td11.setTag("td");
                td11.getProperties().setProperty("width","9%");
                td11.getProperties().setProperty("height","18");
                tr1.getElements().add(td11);

                HttpBase val111=new HttpBase();
                val111.setTag("");
                val111.setValue("&nbsp;");
                td11.getElements().add(val111);

                HttpBase td12=new HttpBase();
                td12.setTag("td");
                td12.getProperties().setProperty("width","91%");
                td12.getProperties().setProperty("valign","bottom");
                tr1.getElements().add(td12);

                HttpBase img121=new HttpBase();
                img121.setTag("img");
                img121.getProperties().setProperty("src","../images/small0.gif");
                img121.getProperties().setProperty("width","13");
                img121.getProperties().setProperty("height","13");
                img121.getProperties().setProperty("hspace","3");
                img121.getProperties().setProperty("align","absmiddle");
                td12.getElements().add(img121);

                HttpBase a122=new HttpBase();
                a122.setTag("a");
                String refpage=UtilCommon.NVL(trade.getRefPage());
                if (refpage.equals("")){
                    refpage="main/trade.faces";
                }
                a122.getProperties().setProperty("href","../"+refpage+"?tseqn="+trade.getTheCode());
                a122.getProperties().setProperty("target","mainFrame");
                td12.getElements().add(a122);

                HttpBase val1221= new HttpBase();
                val1221.setValue(trade.getTheName());
                a122.getElements().add(val1221);
            }
            if (trade.getChildFlag().equals("1") & (trade.getChildSystemFunctionCode() != null)) {
                String imgName = trade.getImg();
                if (imgName == null) {
                    imgName = "shequ_man.gif";
                } else {
                    if (imgName.equals("")) {
                        imgName = "shequ_man.gif";
                    }
                }

                HttpBase tr2 = new HttpBase();
                tr2.setTag("tr");
                val.getElements().add(tr2);

                HttpBase td21 = new HttpBase();
                td21.setTag("td");
                td21.getProperties().setProperty("background","../images/rebg04.gif");
                tr2.getElements().add(td21);

                HttpBase val211 = new HttpBase();
                val211.setValue("&nbsp;");
                td21.getElements().add(val211);

                HttpBase td22 = new HttpBase();
                td22.setTag("td");
                td22.getProperties().setProperty("bgcolor","#E2F3C0");
                tr2.getElements().add(td22);

                HttpBase img221 = new HttpBase();
                img221.setTag("img");
                img221.getProperties().setProperty("src","../images/"+imgName);
                img221.getProperties().setProperty("width","18");
                img221.getProperties().setProperty("height","18");
                img221.getProperties().setProperty("hspace","3");
                img221.getProperties().setProperty("align","absmiddle");
                img221.getProperties().setProperty("border","0");
                td22.getElements().add(img221);

                HttpBase val222 = new HttpBase();
                val222.setValue(trade.getTheName());
                td22.getElements().add(val222);

                HttpBase td23 =new HttpBase();
                td23.setTag("td");
                td23.getProperties().setProperty("background","../images/rebg02.gif");
                tr2.getElements().add(td23);

                HttpBase val231 = new HttpBase();
                val231.setValue("&nbsp;");
                td23.getElements().add(val231);

                HttpBase tr3 = new HttpBase();
                tr3.setTag("tr");
                val.getElements().add(tr3);

                HttpBase td31 = new HttpBase();
                td31.setTag("td");
                td31.getProperties().setProperty("background","../images/rebg04.gif");
                tr3.getElements().add(td31);

                HttpBase val311 = new HttpBase();
                val311.setValue("&nbsp;");
                td31.getElements().add(val311);

                HttpBase td32 = new HttpBase();
                td32.setTag("td");
                td32.getProperties().setProperty("bgcolor","#E2F3C0");
                tr3.getElements().add(td32);

                HttpBase table321 = new HttpBase();
                table321.setTag("table");
                table321.getProperties().setProperty("width","100%");
                table321.getProperties().setProperty("border","0");
                table321.getProperties().setProperty("cellspacing","0");
                table321.getProperties().setProperty("cellpadding","1");
                td32.getElements().add(table321);

                HttpBase val3211 = genMenuByRoot(trade.getChildSystemFunctionCode());
                table321.getElements().add(val3211);

                HttpBase td33 = new HttpBase();
                td33.setTag("td");
                td33.getProperties().setProperty("background","../images/rebg02.gif");
                tr3.getElements().add(td33);

                HttpBase val331 = new HttpBase();
                val331.setValue("&nbsp;");
                td33.getElements().add(val331);

                HttpBase tr4 = new HttpBase();
                tr4.setTag("tr");
                val.getElements().add(tr4);

                HttpBase td41 = new HttpBase();
                td41.setTag("td");
                td41.getProperties().setProperty("background","../images/rebg04.gif");
                tr4.getElements().add(td41);

                HttpBase val411 = new HttpBase();
                val411.setValue("&nbsp;");
                td41.getElements().add(val411);

                HttpBase td42 = new HttpBase();
                td42.setTag("td");
                td42.getProperties().setProperty("bgcolor","#E2F3C0");
                tr4.getElements().add(td42);

                HttpBase img421 = new HttpBase();
                img421.setTag("img");
                img421.getProperties().setProperty("src","../images/lefthr.gif");
                img421.getProperties().setProperty("width","113");
                img421.getProperties().setProperty("height","5");
                td42.getElements().add(img421);

                HttpBase td43 = new HttpBase();
                td43.setTag("td");
                td43.getProperties().setProperty("background","../images/rebg02.gif");
                tr4.getElements().add(td43);

                HttpBase val431 = new HttpBase();
                val431.setValue("&nbsp;");
                td43.getElements().add(val431);
            }
        }
        return val;
    }

    public void setRootMenu(String RootMenu) {
        this.rootMenu = RootMenu;
    }

    public void setMenuDetail(String MenuDetail) {
        this.menuDetail = MenuDetail;
    }

    public void setFirstMenuPage(String firstMenuPage) {
        this.firstMenuPage = firstMenuPage;
    }

    public String getRootMenu() {
        return rootMenu;
    }

    public String getMenuDetail() {
        return menuDetail;
    }

    public String getFirstMenuPage() {
        return firstMenuPage;
    }

}
