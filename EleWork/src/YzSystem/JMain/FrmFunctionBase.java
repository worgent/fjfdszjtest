package YzSystem.JMain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import YzSystem.J_System.*;
import YzSystem.JMain.wlglException;

public class FrmFunctionBase {
    public String tseqn = "";							 //功能主键
    String posHttp = ""; 								 // 位置信息Http代码
    public BeanSystemFunctionCode functionInfo = null; // 功能信息;
    public BeanSystemGroupPower powerInfo = null;      // 权限信息;
    public boolean hasRight = false;					// 权限  (仅作为调用使用生成powerInfo)
    public String mode = ""; 							// 模式:add,query,del,edit...    查,修,增,删,打印.
    public boolean isAjaxMode = false;                //是否启用AJAX
    public String detailMode = ""; 						// 子表编辑模式:add,query,del,edit...
    public String modeName = ""; 						//　模式中文名:add,query,del,edit...
    public boolean inDetailMode = false;             //是否明细模式
    public String parames = ""; 						//网页参数
    public String findparam = ""; 						// 基本的查询参数。
    public String selfPage; 							// 本页面
    public String caption = ""; 						// 窗体标题

    public String Table = ""; 							// 插入Table
    public String seqnField = ""; 						// 序号字段(字段名称)
    public String seqn = ""; 							// 序号    (字段值)
    public String DetailTable = ""; 					// 插入Table(明细表)
    public String DetailSeqnField = "";					// 序号字段(字段名称)
    public String detailseqn = ""; 						// 序号字段(字段值)
    
    public String detailParams = ""; 					//详细列表编辑参数


    public String otherTitleInfo = ""; 					// 功能标题的附加提示
    public boolean initpage = false; 					// 是否第一次访问此页面;
    public String pageType = ""; 						// 页面类型;
    
    

    // 查询参数
    public String qry_selectSQL = ""; // 查询SQL
    public String qry_whereSQL = ""; // whereSQL
    public String qry_orderSQL = ""; // 顺序
    public ArrayList qry_whereSQLParam = new ArrayList(); // whereSQL
    
    public String edt_selectSQL = ""; // 查询SQL
    public String edt_whereSQL = ""; // whereSQL
    public String edt_orderSQL = ""; // 顺序
    public ArrayList edt_whereSQLParam = new ArrayList(); // whereSQL
    
    
    public String Query_SQL = ""; // 整句的查询SQL
    public ArrayList Query_SQLParam = new ArrayList(); // 整句的查询SQL
    

    public ArrayList columnsInfo = new ArrayList(); // 查询结果列信息
    protected ArrayList qryComps = new ArrayList(); // 查询组件列表
    protected ArrayList qryPanels = new ArrayList(); // 查询组件列表

    // 增加参数
    public ArrayList addExtraColumns = new ArrayList(); // 增加的额外字段

    // 修改参数
    public String updateWhereSQL = ""; // 修改SQL
    public ArrayList updateWhereSQLParam = new ArrayList(); // 修改SQL
    
    public Properties editColumns = new Properties(); // 修改栏和数据列的对应关系
    public ArrayList editComps = new ArrayList(); // 编辑组件列表
    public ArrayList editPanels = new ArrayList(); // 编辑组件列表

    // 详表参数
    public String detailSelectSQL = ""; // 详表SQL
    public String detailWhereSQL = ""; // 详表条件
    public String detailOrderSQL = ""; // 详表排序
    public ArrayList detailWhereSQLParam = new ArrayList(); // 修改SQL
    public ArrayList detailColumnsInfo = new ArrayList(); // 详表查询结果列信息
    protected ArrayList DetailComps = new ArrayList(); // 添加组件列表

    /**
     * FrmTradeBase
     * 功能：取得当前交易号信息，设置页面初始参数。
     * @return boolean         是否有权限
     */
    public FrmFunctionBase() {
        // 取得当前交易号
        tseqn = UtilWebTools.getRequestParameter("tseqn");//功能主键
        BeanSystemFunctionCode aFunction = null;
        aFunction = UtilWebTools.getTradeInfoBySeqn(tseqn);//功能信息
        String curAjaxMode = UtilWebTools.getRequestParameter("isAjax");
        if (!curAjaxMode.equals("")) {
            isAjaxMode = true;
        } else {
            isAjaxMode = false;
        }
        // 设置基础参数parames
        if (!tseqn.equals("")) {
            parames = "?tseqn=" + tseqn;
        }

        // 设置功能信息
        functionInfo = aFunction;
        
        // 设置权限
        hasRight = true;
        GetPowerInfo();
        
        // 设置页面标题
        if (functionInfo != null) {
            caption = functionInfo.getTheName();
        }

        // 针对模式进行初始化，设置基础查询串
        detailParams = "?" + UtilWebTools.getRequest().getQueryString();
        detailMode = UtilWebTools.getRequestParameter("detailMode");
        if (UtilWebTools.getRequestParameter("detailMode").equals("")) {
            initMode("mode", "");
        } else {
            initMode("detailMode", "明细");
            inDetailMode = true;
        }
        genFindParam();

        // 保存当前页面url
        selfPage = UtilCommon.Replace(".jsp", ".faces",
                                      UtilWebTools.getRequestPath());
        seqn = UtilCommon.NVL(UtilWebTools.getRequestParameter("seqn"));
        detailseqn = UtilCommon.NVL(UtilWebTools.getRequestParameter("detailSeqn"));
    }

    protected void finalize()

            throws Throwable {

        super.finalize();

        // other finalization code...

    }

    public void genAjaxXML() throws wlglException {
        return;
    }

    /**
     * genFindParam
     * 功能：产生Url的查询条件串（去除模式信息）
     */
    private void genFindParam() {
        findparam = "?" + UtilWebTools.getRequest().getQueryString();
        findparam = UtilCommon.Replace(
                "&detailMode=edit",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&detailMode=add",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&detailMode=del",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&mode=edit",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&mode=view",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&mode=del",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&mode=add",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&mode=query",
                "", findparam);
        findparam = UtilCommon.Replace(
                "&action=" + UtilWebTools.getRequestParameter("action"),
                "", findparam);
        findparam = UtilCommon.Replace(
                "&detailSeqn=" + UtilWebTools.getRequestParameter("detailSeqn"),
                "", findparam);
        findparam = UtilCommon.Replace(
                "&seqn=" + UtilWebTools.getRequestParameter("seqn"),
                "", findparam);
    }

    /**
     * initMode
     * 功能：模式初始化
     * @param modeStr  String 模式状态,如mode,detailmode
     * @param modedesc String 模式描述，如详细状态
     */
    private void initMode(String modeStr,
                          String modedesc) {
        mode = UtilWebTools.getRequestParameter(modeStr);
        if (mode.equals("")) {
            initpage = true;
            mode = "query";
        }
        else if (mode.equals("del")) {
            modeName = modedesc + "查询";
        }
        else if (mode.equals("query")) {
            modeName = modedesc + "查询";
        }
        else if (mode.equals("edit")) {
            modeName = modedesc + "修改";
            otherTitleInfo = modeName;
        }
        else if (mode.equals("view")) {
            modeName = modedesc + "查看";
            otherTitleInfo = modeName;
        }
        else if (mode.equals("add")) {
            modeName = modedesc + "增加";
            otherTitleInfo = modeName;
        }
    }

    /**
     * 
     * 功能：得到用户权限powerInfo,在权限中处理
     * @param userInfo session对像中读取
     * @param tseqn String 功能树的值
     * 
     */
    private void GetPowerInfo()
    {
    	if(powerInfo==null)
    	{
            BeanLogin aUser=(BeanLogin) UtilWebTools.getSession().getAttribute("userInfo");	
            String apowercode=aUser.getGroupPowerCode();
            try{
                powerInfo=UtilWebTools.getUtilEJB2Local().getGroupPower(apowercode, tseqn);
            } catch( wlglException ex)
            {
                	System.out.println("权限值无法取得"+ex.getMessage());
            }
    	}
    	
    }
    /**
     * 2008-06-05
     * 作者:szj
     * 功能:生成明细的权限按钮
     * @param SystemPower session对像中读取powerInfo
     * 
     * 
     */
    
    protected HttpBase genHttpBaseDetailRightBtns(BeanSystemGroupPower SystemPower) throws wlglException {
        HttpBase base = new HttpBase();
        HttpBase table11 = HttpBase.genHttpTable("btd", "100%", "1", "5", "0");
        base.getElements().add(table11);

        HttpBase tr111 = HttpBase.genHttpTR("btd");
        table11.getElements().add(tr111);
        HttpBase td1111 = new HttpBase();
        td1111.setTag("td");
        td1111.getProperties().setProperty("height", "36");
        td1111.getProperties().setProperty("align", "right");
        tr111.getElements().add(td1111);
        
        HttpBase input11116 = new HttpBase();
        input11116.setTag("input");
        input11116.getProperties().setProperty("type", "button");
        input11116.getProperties().setProperty("name", "btnSave");
        input11116.getProperties().setProperty("value", "保存");
        input11116.getProperties().setProperty("onClick",
                "javascript:return doSave();");
        td1111.getElements().add(input11116);
        

        if (UtilWebTools.checkRight(SystemPower, "add")) {
            HttpBase input11111 = new HttpBase();
            input11111.setTag("input");
            input11111.getProperties().setProperty("type", "button");
            input11111.getProperties().setProperty("name", "btnAdd");
            input11111.getProperties().setProperty("value", "增加");
            input11111.getProperties().setProperty("onClick",
                    "javascript:return doDetailAdd();");
            td1111.getElements().add(input11111);
        }
        if (UtilWebTools.checkRight(SystemPower, "edit")) {
            HttpBase input11112 = new HttpBase();
            input11112.setTag("input");
            input11112.getProperties().setProperty("type", "button");
            input11112.getProperties().setProperty("name", "btnModify");
            input11112.getProperties().setProperty("value", "修改");
            input11112.getProperties().setProperty("onClick",
                    "javascript:return doDetailEdit();");
            td1111.getElements().add(input11112);
        }
        if (UtilWebTools.checkRight(SystemPower, "del")) {
            HttpBase input11113 = new HttpBase();
            input11113.setTag("input");
            input11113.getProperties().setProperty("type", "button");
            input11113.getProperties().setProperty("name", "btnDel");
            input11113.getProperties().setProperty("value", "删除");
            input11113.getProperties().setProperty("onClick",
                    "javascript:return doDetailDel();");
            td1111.getElements().add(input11113);
        }
        if (UtilWebTools.checkRight(SystemPower, "print")) {

            HttpBase input11115 = new HttpBase();
            input11115.setTag("input");
            input11115.getProperties().setProperty("type", "button");
            input11115.getProperties().setProperty("name", "btnPrint");
            input11115.getProperties().setProperty("value", "打印");
            input11115.getProperties().setProperty("onClick",
                    "javascript:return doDetailPrint();");
            td1111.getElements().add(input11115);
        }
              
        HttpBase input11117 = new HttpBase();
        input11117.setTag("input");
        input11117.getProperties().setProperty("type", "button");
        input11117.getProperties().setProperty("name", "btncancel");
        input11117.getProperties().setProperty("value", "取消");
        input11117.getProperties().setProperty("onClick",
                "javascript:return self.location.href='" + UtilWebTools.getRequestPath() + findparam + "';");
        td1111.getElements().add(input11117);
        
        return base;
    }
    
    /**
     * 2008-06-06
     * 作者:szj
     * 功能:生成主表的权限按钮
     * @param SystemPower session对像中读取powerInfo
     * 
     */
    
    protected String genHtmlRightButtons(BeanSystemGroupPower SystemPower) throws wlglException 
    {
    	String val = "";
    	HttpBase base = new HttpBase();
        HttpBase table11 = HttpBase.genHttpTable("btd", "100%", "1", "5", "0");
        base.getElements().add(table11);

        HttpBase tr111 = HttpBase.genHttpTR("btd");
        table11.getElements().add(tr111);
        HttpBase td1111 = new HttpBase();
        td1111.setTag("td");
        td1111.getProperties().setProperty("height", "36");
        td1111.getProperties().setProperty("align", "right");
        tr111.getElements().add(td1111);

        if (UtilWebTools.checkRight(SystemPower, "add")) {
            HttpBase input11111 = new HttpBase();
            input11111.setTag("input");
            input11111.getProperties().setProperty("type", "button");
            input11111.getProperties().setProperty("name", "btnAdd");
            input11111.getProperties().setProperty("value", "增加");
            input11111.getProperties().setProperty("onClick",
                    "javascript:return doAdd();");
            td1111.getElements().add(input11111);
        }
        if (UtilWebTools.checkRight(SystemPower, "edit")) {
            HttpBase input11112 = new HttpBase();
            input11112.setTag("input");
            input11112.getProperties().setProperty("type", "button");
            input11112.getProperties().setProperty("name", "btnModify");
            input11112.getProperties().setProperty("value", "修改");
            input11112.getProperties().setProperty("onClick",
                    "javascript:return doEdit();");
            td1111.getElements().add(input11112);
        }
        if (UtilWebTools.checkRight(SystemPower, "del")) {
            HttpBase input11113 = new HttpBase();
            input11113.setTag("input");
            input11113.getProperties().setProperty("type", "button");
            input11113.getProperties().setProperty("name", "btnDel");
            input11113.getProperties().setProperty("value", "删除");
            input11113.getProperties().setProperty("onClick",
                    "javascript:return doDel();");
            td1111.getElements().add(input11113);
        }
        if (UtilWebTools.checkRight(SystemPower, "print")) {

            HttpBase input11115 = new HttpBase();
            input11115.setTag("input");
            input11115.getProperties().setProperty("type", "button");
            input11115.getProperties().setProperty("name", "btnPrint");
            input11115.getProperties().setProperty("value", "打印");
            input11115.getProperties().setProperty("onClick",
                    "javascript:return doPrint();");
            td1111.getElements().add(input11115);
        }
    	val = base.GenHtml();
        return val;
    }
    
    
    /**
     * GenHttpBaseFindBtn
     * 功能：产生查询按钮栏.
     * @return HttpBase         产生查找按钮对象.
     */
    private HttpBase genHttpBaseFindBtn() {
        HttpBase base = new HttpBase();
        HttpBase table11 = HttpBase.genHttpTable("btd", "100%", "1", "5",
                                                 "0");
        base.getElements().add(table11);
        HttpBase tr111 = HttpBase.genHttpTR("btd");
        table11.getElements().add(tr111);
        HttpBase td1111 = new HttpBase();
        td1111.setTag("td");
        td1111.getProperties().setProperty("height", "36");
        td1111.getProperties().setProperty("align", "right");
        tr111.getElements().add(td1111);

        HttpBase btnFind = UtilWebFactory.genHttpBaseBtnFind("btnFind");

        td1111.getElements().add(btnFind);

        return base;
    }

    /**
     * genHtmlPos
     * 功能：产生位置信息.
     * @return String         位置信息栏.
     */
    public String genHtmlPos() {
        String val = "";
        val = UtilWebFactory.genHtmlPos(functionInfo, otherTitleInfo);
        return val;
    }

    /**
     * GenHtmlQueryComp
     * 功能：产生查询控件Html内容.
     * @return String         查询栏控件html内容.
     */
    public String genHtmlQueryComp() throws wlglException {
        String val = "";

        HttpBase base = new HttpBase();

        HttpBase table11 = new HttpBase();
        table11.setTag("table");
        table11.getProperties().setProperty("class", "btd3");
        table11.getProperties().setProperty("border", "0");
        table11.getProperties().setProperty("cellspacing", "1");
        table11.getProperties().setProperty("cellpadding", "5");
        table11.getProperties().setProperty("width", "100%");
        base.getElements().add(table11);
        if (qryPanels.size() < 1) {
            HttpBase tr111 = HttpBase.genHttpTR("btd");
            table11.getElements().add(tr111);
            Iterator itx = qryComps.iterator();
            while (itx.hasNext()) {
                tr111.getElements().add(genHttpBaseComponent((EditComponent)
                        itx.next()));
            }
        } else {
            for (int i = 0; i < qryPanels.size(); i++) {
                table11.getElements().add(qryPanels.get(i));
            }

        }
        base.getElements().add(genHttpBaseFindBtn());
        // 设置缺省值
        if (initpage) {
            Iterator itx = qryComps.iterator();
            while (itx.hasNext()) {
                EditComponent acomp = (EditComponent) itx.next();
                if (HttpBase.getCompValue(base, acomp.getID()).equals("")) {
                    HttpBase.setCompValue(base, acomp.getID(),
                                          acomp.getDefValue());
                    HttpBase.setCompValue(base, acomp.getDisID(),
                                          acomp.getDefDisValue());
                }
            }
        }
        
        HttpBase act = doAction();
        if (act != null) {
            base.getElements().add(act);
        }
        val = base.GenHtml();
        return val;
    }

    /**
     * GenHttpBaseDetail
     * 功能：产生详表信息
     * @return String         查询栏控件.
     */
    private HttpBase genHttpBaseDetail() throws wlglException {
        UtilDB utilDB = genDBDetailSQL();
        int rowCount = UtilWebTools.getSessionAtrributerI("RowCount");
        HttpBase hb = genHttpBaseQueryContext(utilDB, detailColumnsInfo,
                                              rowCount);
        utilDB.closeCon();
        return hb;
    }

    /**
     * genHttpBaseComponent
     * 功能：产生组件的httpBase
     * @return HttpBase     组件的httpBase
     */
    protected HttpBase genHttpBaseComponent(EditComponent acomp) throws
            wlglException {
    	/*
        if (acomp.getEdtType().equals("storageposseqn3")) {

            HttpBase httpBase = UtilWebFactory.genHttpBaseAhrefEdit(acomp,
                    "selectseqn('" + acomp.getID() + "','"
                    + acomp.getDisID() + "','selectStoragePos','storagename',"
                    + acomp.getFillCompArray() + "," + acomp.getFillIndexArray() +
                    ",'&storageseqn='+(document.theForm('storageseqn').value))");
            if (!UtilWebTools.
                getRequestParameter(acomp.getID()).equals("")) {
                HttpBase.setCompValue(httpBase, acomp.getDisID(),
                                      UtilWebTools.
                                      getRequestParameter(acomp.getDisID()));
                HttpBase.setCompValue(httpBase, acomp.getID(),
                                      UtilWebTools.
                                      getRequestParameter(acomp.getID()));

            }
            return httpBase;

        }
        */

        return acomp.genHttpBase();
    }

    /**
     * genHtmlMain
     * 功能：产生页面主要信息
     * @return String         httpString.
     */
    public String genHtmlMain() throws wlglException {
        String result = "";
        result = genHtmlPos();
        if (mode.equals("query")) {
            // 条件录入栏
            result += genHtmlQueryComp();
            // 查询结果栏
            result += genHtmlQueryResult();
            // 增删改查按钮动态生成根据权限值
            result+=genHtmlRightButtons(powerInfo);
        }
        else if (mode.equals("del")) {
            result += HttpBaseDoDel();
        }

        else if (mode.equals("add") || mode.equals("edit") || mode.equals("view")) {

            if (mode.equals("edit") || mode.equals("view")) {
                if (inDetailMode) {
                    setEditValue(detailSelectSQL, detailWhereSQL,
                                 seqnField, detailWhereSQLParam);
                } else {
                    setEditValue(edt_selectSQL, edt_whereSQL, seqnField,
                                 edt_whereSQLParam);
                }
            }
            result += genHtmlEditComp();

        }

        return result;
    }


    /**
     * setEditValue
     * 功能：设置编辑状态时候，各个编辑控件所对应数据库的值
     * @param selectSQL      String 设置编辑控件初始值的select SQL语句
     * @param whereSQL       String 设置编辑控件初始值的where SQL语句
     * @param seqnField      String 主键字段
     * @param whereSQLParam  ArrayList where条件中对应的查询具体数值
     */
    public void getEditDBRowTitlesAndDatas(String selectSQL, String whereSQL,
			String seqnField, ArrayList whereSQLParam, ArrayList titles,
			ArrayList datas) throws wlglException {

		UtilDB utilDB = new UtilDB();
		String tmp = "";
		ArrayList param = new ArrayList();
		if (inDetailMode) {
			if (whereSQL.equals("")) {
				tmp = selectSQL + " where " + "a." + seqnField + "=?"+ " and a."+DetailSeqnField+"=?" ;;
			} else {
				tmp = selectSQL + " where " + "a." + seqnField + "=?"+" and a."+DetailSeqnField+"=? and "  + whereSQL;
			}
			param.add(seqn);
			param.add(UtilWebTools.getRequestParameter("detailSeqn"));
		} else {
			if (whereSQL.equals("")) {
				tmp = selectSQL + " where " + "a." + seqnField + "=?";
			} else {
				tmp = selectSQL + " where " + "a." + seqnField + "=?" + " and "+ whereSQL;
			}
			
			param.add(seqn);
		}
		param.addAll(whereSQLParam);
		utilDB.exeQueryOneRowTitlesAndDatas(tmp, param, titles, datas);
		utilDB.closeCon();
	}

    private void setEditValue(String selectSQL, String whereSQL,
                              String seqnField,
                              ArrayList whereSQLParam) throws wlglException {
        ArrayList myrow = new ArrayList();
        ArrayList mytitle = new ArrayList();
        getEditDBRowTitlesAndDatas(selectSQL, whereSQL, seqnField,
                                   whereSQLParam, mytitle, myrow);
        Iterator itx = editComps.iterator();
        int i = 0;
        while (itx.hasNext()) {
            EditComponent acomp = (EditComponent) itx.next();
            String col = editColumns.getProperty(Integer.toString(i));
            if (col != null) {
                if (Integer.parseInt(col) < myrow.size()) {
                	
                    String val = (String) myrow.get(Integer.parseInt(col));
                    acomp.setDefValue(val);
                }
            }
            String cold = editColumns.getProperty(Integer.toString(i) + "d");
            if (cold != null) {
                String val = (String) myrow.get(Integer.parseInt(cold));
                acomp.setDefDisValue(val);
            }

            i++;
        }
    }

    /**
     * genEditComp
     * 功能：产生编辑控件html内容
     */
    private String genHtmlEditComp() throws wlglException {

        String val = "";

        HttpBase base = new HttpBase();
        HttpBase seqnscript = HttpBase.genHttpScript("seqn='" + seqn + "'");
        base.getElements().add(seqnscript);

        // 设置必须填写字段
        String mustFillArray = "";
        String mustFillCaptionArray = "";
        // 必须整数字段
        String mustIntArray = "";
        String mustIntCaptionArray = "";
        // 必须浮点字段
        String mustFloatArray = "";
        String mustFloatCaptionArray = "";

        Iterator itx1 = editComps.iterator();
        while (itx1.hasNext()) {
            EditComponent acomp = (EditComponent) itx1.next();
            HttpBase.setCompValue(base, acomp.getID(), acomp.getDefValue());
            HttpBase.setCompValue(base, acomp.getDisID(),
                                  acomp.getDefDisValue());

            if (acomp.isMustFill()) {
                if (mustFillArray.equals("")) {
                    mustFillArray = "\"" + acomp.getID() + "\"";
                    mustFillCaptionArray = "\"" + acomp.getCaption() + "\"";
                } else {
                    mustFillArray += ",\"" + acomp.getID() + "\"";
                    mustFillCaptionArray += ",\"" + acomp.getCaption() +
                            "\"";
                }
            }
            if (acomp.isMustInt()) {
                if (mustIntArray.equals("")) {
                    mustIntArray = "\"" + acomp.getID() + "\"";
                    mustIntCaptionArray = "\"" + acomp.getCaption() + "\"";
                } else {
                    mustIntArray += ",\"" + acomp.getID() + "\"";
                    mustIntCaptionArray += ",\"" + acomp.getCaption() +
                            "\"";
                }
            }
            if (acomp.isMustFloat()) {
                if (mustFloatArray.equals("")) {
                    mustFloatArray = "\"" + acomp.getID() + "\"";
                    mustFloatCaptionArray = "\"" + acomp.getCaption() + "\"";
                } else {
                    mustFloatArray += ",\"" + acomp.getID() + "\"";
                    mustFloatCaptionArray += ",\"" + acomp.getCaption() +
                            "\"";
                }
            }

        }
        if (!mustFillArray.equals("")) {
            HttpBase script = HttpBase.genHttpScript("mustFillArray=[" +
                    mustFillArray +
                    "];\n mustFillCaptionArray=[" +
                    mustFillCaptionArray + "];");
            base.getElements().add(script);
        }
        if (!mustIntArray.equals("")) {
            HttpBase script = HttpBase.genHttpScript("mustIntArray=[" +
                    mustIntArray +
                    "];\n mustIntCaptionArray=[" +
                    mustIntCaptionArray + "];");
            base.getElements().add(script);
        }
        if (!mustFloatArray.equals("")) {
            HttpBase script = HttpBase.genHttpScript("mustFloatArray=[" +
                    mustFloatArray +
                    "];\n mustFloatCaptionArray=[" +
                    mustFloatCaptionArray + "];");
            base.getElements().add(script);
        }

        // 设置“必须填写”提示栏
        if (!mustFillArray.equals("")) {
            HttpBase table11 = new HttpBase();
            table11.setTag("table");
            table11.getProperties().setProperty("class", "btd3");
            table11.getProperties().setProperty("border", "0");
            table11.getProperties().setProperty("cellspacing", "1");
            table11.getProperties().setProperty("cellpadding", "5");
            table11.getProperties().setProperty("width", "100%");
            base.getElements().add(table11);

            HttpBase trmust = HttpBase.genHttpTR("btd2");
            HttpBase tdmust = HttpBase.genHttpTD("", "left", "6", "");
            HttpBase valmust = HttpBase.genHttpValue("*标识必填项");
            tdmust.getElements().add(valmust);
            trmust.getElements().add(tdmust);
            table11.getElements().add(trmust);
        }
        // 设置编辑控件
        // 查看模式必须为disabled;
        if (mode.equals("view")) {
            Iterator itx = editComps.iterator();
            while (itx.hasNext()) {
                ((EditComponent) itx.next()).setMode("disabled");
            }
        }
        HttpBase table11 = new HttpBase();
        table11.setTag("table");
        table11.getProperties().setProperty("class", "btd3");
        table11.getProperties().setProperty("border", "0");
        table11.getProperties().setProperty("cellspacing", "1");
        table11.getProperties().setProperty("cellpadding", "5");
        table11.getProperties().setProperty("width", "100%");
        base.getElements().add(table11);

        if (editPanels.size() < 1) {

            HttpBase tr111 = HttpBase.genHttpTR("btd2");
            table11.getElements().add(tr111);
            Iterator itx = editComps.iterator();
            while (itx.hasNext()) {
                tr111.getElements().add(((EditComponent) itx.next()).
                                        genHttpBase());
            }
        } else {
            for (int i = 0; i < editPanels.size(); i++) {
                table11.getElements().add(editPanels.get(i));
            }

        }
        // 设置缺省值
        Iterator itxdef = editComps.iterator();
        while (itxdef.hasNext()) {
            EditComponent acomp = (EditComponent) itxdef.next();
            HttpBase.setCompValue(base, acomp.getID(), acomp.getDefValue());
            if (!acomp.getDisID().equals(acomp.getID())) {
                HttpBase.setCompValue(base, acomp.getDisID(),
                                      acomp.getDefDisValue());
            }
        }

        if (inDetailMode && (detailMode.equals("add"))) {
            int detailEditRowCount = UtilWebTools.getSessionAtrributerI(
                    "DetailEditRow");
            for (int r = 1; r < detailEditRowCount; r++) {
                if (editPanels.size() < 1) {

                    HttpBase tr111 = HttpBase.genHttpTR("btd2");
                    table11.getElements().add(tr111);
                    Iterator itx = editComps.iterator();
                    while (itx.hasNext()) {
                        EditComponent acomp = (EditComponent) itx.next();
                        EditComponent mycomp = new EditComponent(acomp);
                        mycomp.setID(acomp.getID() + r);
                        if (!acomp.getDisID().equals("")) {
                            mycomp.setDisID(acomp.getDisID() + r);
                        }
                        HttpBase.setCompValue(base, acomp.getID() + r,
                                              acomp.getDefValue());
                        if (!acomp.getDisID().equals("")) {
                            HttpBase.setCompValue(base, acomp.getDisID() + r,
                                                  acomp.getDefDisValue());
                        }

                        tr111.getElements().add(mycomp.
                                                genHttpBase());

                    }
                } else {
                    for (int i = 0; i < editPanels.size(); i++) {
                        HttpBase atr = (HttpBase) editPanels.get(
                                i);
                        HttpBase tr111 = new HttpBase(atr);
                        table11.getElements().add(tr111);
                        tr111.getElements().clear();
                        for (int j = 0; j < atr.getElements().size(); j++) {
                            HttpBase atd = (HttpBase) atr.getElements().get(j);
                            Iterator itx = editComps.iterator();
                            while (itx.hasNext()) {
                                EditComponent acomp = (EditComponent) itx.next();
                                // 如果当前行存在编辑控件，则进行重新处理
                                HttpBase hb = atr.getCompByName(atd,
                                        acomp.getID());
                                if (hb != null) {
                                    EditComponent mycomp = new EditComponent(
                                            acomp);
                                    mycomp.setFillCompArray(mycomp.
                                            getFillCompArray().replaceAll("',",
                                            r + "',"));
                                    mycomp.setFillCompArray(mycomp.
                                            getFillCompArray().replaceAll("']",
                                            r + "']"));
                                    mycomp.setID(acomp.getID() + r);
                                    if (!acomp.getDisID().equals("")) {
                                        mycomp.setDisID(acomp.getDisID() + r);
                                    }
                                    HttpBase.setCompValue(base,
                                            acomp.getID() + r,
                                            acomp.getDefValue());
                                    if (!acomp.getDisID().equals("")) {
                                        HttpBase.setCompValue(base,
                                                acomp.getDisID() + r,
                                                acomp.getDefDisValue());
                                    }
                                    tr111.getElements().add(
                                            genHttpBaseComponent(mycomp));
                                    HttpBase.setCompValue(tr111,
                                            acomp.getID() + r,
                                            acomp.getDefValue());
                                    if (!acomp.getDisID().equals("")) {
                                        HttpBase.setCompValue(tr111,
                                                acomp.getDisID() + r,
                                                acomp.getDefDisValue());
                                    }

                                    break;
                                }

                            }
                        }

                    }

                }

            }

        }

        if (inDetailMode) {
            HttpBase tablesave = genHttpBaseBtnDetailSaveCancel();
            base.getElements().add(tablesave);
        } else {
            if ((mode.equals("edit") || mode.equals("view")) &&
                (pageType.equals("masterDetail"))) {
                // 产生详细查询
                HttpBase detail = genHttpBaseDetail();
                base.getElements().add(detail);
                // 根据权限生成明细的设置“按钮”栏
                HttpBase tablebtn=genHttpBaseDetailRightBtns(powerInfo);
                base.getElements().add(tablebtn);
            } else {
                HttpBase tablesave = genHttpBaseSingleEditBtns();
                base.getElements().add(tablesave);
            }
        }
        HttpBase act = doAction();
        if (act != null) {
            base.getElements().add(act);
        }
        val = base.GenHtml();
        return val;
    }


    protected HttpBase doAction() throws wlglException {
        if (UtilWebTools.getRequestParameter("action").equals("save")) {
            return (HttpBaseDoSave());
        }
        else if (UtilWebTools.getRequestParameter("action").equals("print")) {
            return (HttpBaseDoPrint());
        }

        return null;
    }

    protected HttpBase genHttpBaseSingleEditBtns() throws wlglException {
        // 设置“保存按钮”栏
        HttpBase tablesave = new HttpBase();
        tablesave.setTag("table");
        tablesave.getProperties().setProperty("class", "btd3");
        tablesave.getProperties().setProperty("border", "0");
        tablesave.getProperties().setProperty("cellspacing", "1");
        tablesave.getProperties().setProperty("cellpadding", "5");
        tablesave.getProperties().setProperty("width", "100%");
        HttpBase trsave = HttpBase.genHttpTR("btd");
        HttpBase tdsave = HttpBase.genHttpTD("", "right", "", "");
        trsave.getElements().add(tdsave);
        tablesave.getElements().add(trsave);
        tdsave.getElements().add(UtilWebFactory.genHttpBaseBtnSave("btnSave"));
        tdsave.getElements().add(UtilWebFactory.genHttpBaseBtnCancel(
                "btnCancel"));
        return tablesave;
    }

    /**
     * genHttpBaseDetailSaveCancel
     * 功能:产生保存取消按钮的httpBase
     * @return HttpBase 保存取消按钮的httpBase对象
     */
    protected HttpBase genHttpBaseBtnDetailSaveCancel() throws wlglException {
        // 设置“保存按钮”栏
        HttpBase tablesave = new HttpBase();
        tablesave.setTag("table");
        tablesave.getProperties().setProperty("class", "btd3");
        tablesave.getProperties().setProperty("border", "0");
        tablesave.getProperties().setProperty("cellspacing", "1");
        tablesave.getProperties().setProperty("cellpadding", "5");
        tablesave.getProperties().setProperty("width", "100%");
        HttpBase trsave = HttpBase.genHttpTR("btd");
        HttpBase tdsave = HttpBase.genHttpTD("", "right", "", "");
        trsave.getElements().add(tdsave);
        tablesave.getElements().add(trsave);
        tdsave.getElements().add(UtilWebFactory.genHttpBaseBtn("btnSave", "保存",
                "doDetailSave()"));
        tdsave.getElements().add(UtilWebFactory.genHttpBaseBtnCancel(
                "btnCancel"));
        return tablesave;
    }

    /**
     * HttpBaseDoDel
     * 功能:删除处理
     * @return HttpBase  保存处理后的结果信息HttpBase
     */
    protected String HttpBaseDoDel() throws wlglException {
        String atable;
        String aseqnfield;
        String aseqn;
        if (inDetailMode) {
            atable = DetailTable;
            aseqnfield = DetailSeqnField;
            aseqn = UtilWebTools.getRequestParameter("detailSeqn");
        } else {
            atable = Table;
            aseqnfield = seqnField;
            aseqn = seqn;
        }
        String tmp;

        tmp = canDelete();
        if (tmp.equals("")) {
            doDelete(atable, aseqnfield, aseqn);

            if (inDetailMode) {
                tmp = "alert('删除成功!');\n"
                      + "self.location.href='" + UtilWebTools.getRequestPath() +
                      findparam + "&mode=edit&seqn=" + seqn + "';";
            } else {
                tmp = "alert('删除成功!');\n"
                      + "self.location.href='" + UtilWebTools.getRequestPath() +
                      findparam + "';";
            }
        } else {
            tmp = "alert('" + tmp + "');\n"
                  + "self.location.href='" + UtilWebTools.getRequestPath() +
                  findparam + "';";
        }

        HttpBase val = HttpBase.genHttpScript(tmp);
        String ret = val.GenHtml();
        return ret;
    }
    //删除之间判断
    protected String canDelete() throws wlglException {
        System.err.println("调用candelete");
        return "";

    }

    /**
     * HttpBaseDoSave
     * 功能:保存处理
     * @return HttpBase  保存处理后的结果信息HttpBase
     */
    private HttpBase HttpBaseDoSave() throws wlglException {
        HttpBase val = null;
        // 校验输入合法性。
        String info = validate();
        if (!info.equals("")) {
			String tmp = "alert('" + info + "');";
			val = HttpBase.genHttpScript(tmp);
		} else

		{
			// 增加记录
			if (mode.equals("add")) {
				val = HttpBaseAddSave();
			}

			// 修改记录
			else if (mode.equals("edit")) {
				val = HttpBaseEditSave();
			}
		}

        return val;
    }

    private HttpBase HttpBaseDoPrint() throws wlglException {
        HttpBase val = null;
        // 校验输入合法性。
        // String info = validate();
        /*
        if (!info.equals("")) {
            String tmp = "alert('" + info + "');";
            val = HttpBase.genHttpScript(tmp);
        }
        else {
			// 增加记录

			if (mode.equals("add")) {
				val = HttpBaseAddSave();
			}

			// 修改记录
			else if (mode.equals("edit")) {
				val = HttpBaseEditSave();
			}        	
        }
       */
        String tmp= doPrint();
        val = HttpBase.genHttpScript(tmp);
        return val;
    }

    /**
	 * doEditSave 功能:编辑模式的保存处理
	 * 
	 * @return HttpBase 保存处理后的结果信息HttpBase
	 */
    protected void doEditSave() throws wlglException {
        UtilDB utilDB = new UtilDB();
        utilDB.beginTransaction();
        try {
            beforeEditSave(utilDB);
            if (inDetailMode) {
                doEditSave(utilDB, DetailTable,seqnField, DetailSeqnField);
            } else {
                doEditSave(utilDB, Table, seqnField,DetailSeqnField);
            }
            afterEditSave(utilDB);
            utilDB.commit();
        } catch (wlglException ex) {
            utilDB.rollback();
            throw ex;
        } finally
        {
        	utilDB.closeCon();
        }
    }


    /**
     * doEditSave
     * 功能:编辑模式的保存处理
     * @return HttpBase  保存处理后的结果信息HttpBase
     */
    public String doPrint() throws wlglException {
        System.out.println("在这里添加进行打印过程代码.");
        return "";
    }

    static public void doEditSave(UtilDB utilDB, String Table,
			String seqnField,String DetailSeqnField, ArrayList editComps, ArrayList addExtraColumns,
			String updateWhereSQL, boolean inDetailMode,
			ArrayList updateWhereSQLParam, StringBuffer seqnbuffer)
			throws wlglException {
		String tmpSQL = " update " + Table + " a ";
		String fieldList = "";
		ArrayList fieldValues = new ArrayList();
		Iterator itx = editComps.iterator();
		int i = 0;
		while (itx.hasNext()) {
			EditComponent acomp = (EditComponent) itx.next();
			if (!hasProperty(addExtraColumns, "field", acomp.getField())) {
				if ((!acomp.getMode().equals("disabled"))
						&& !acomp.getField().equals("")) {
					fieldValues.add(acomp.getValue());
					if (fieldList.equals("")) {
						fieldList += acomp.getField() + "=" + "?";
					} else {
						fieldList += "," + acomp.getField() + "=" + "?";
					}
					i++;
				}
			}

		}
		StringBuffer myfieldlist = new StringBuffer(fieldList);
		prepairedEditExtraColumns(Table, myfieldlist, fieldValues, i,
				addExtraColumns, inDetailMode, seqnbuffer);
		fieldList = myfieldlist.toString();
		
		
		if (inDetailMode) {
			if (updateWhereSQL.equals("")) {
				tmpSQL += " set " + fieldList + " where " + "a." + seqnField
						+ "=?"+ " and a."+DetailSeqnField+"=?" ;
			} else {
				tmpSQL += " set " + fieldList + " where " + "a." + seqnField
						+ "=?" +" and a."+DetailSeqnField+"=? and "  + updateWhereSQL;
			}
			fieldValues.add(seqnbuffer.toString());
			fieldValues.add(UtilWebTools.getRequestParameter("detailSeqn"));
		} else {
			
			if (updateWhereSQL.equals("")) {
				tmpSQL += " set " + fieldList + " where " + "a." + seqnField
						+ "=?";
			} else {
				tmpSQL += " set " + fieldList + " where " + "a." + seqnField
						+ "=?" + " and " + updateWhereSQL;
			}
			fieldValues.add(seqnbuffer.toString());
		}

		fieldValues.addAll(updateWhereSQLParam);
		if (!fieldList.equals("")) {
			utilDB.exeUpdate(tmpSQL, fieldValues);
		}
	}    
    
    
    protected void doEditSave(UtilDB utilDB, String Table, String seqnField,String DetailSeqnField ) throws
            wlglException {
        StringBuffer seqnbuffer = new StringBuffer(seqn);
        doEditSave(utilDB, Table, seqnField,DetailSeqnField, editComps, addExtraColumns,
                   updateWhereSQL, inDetailMode,
                   updateWhereSQLParam, seqnbuffer);
        seqn = seqnbuffer.toString();
    }

    /**
     * HttpBaseEditSave
     * 功能:编辑模式的保存处理
     * @return HttpBase  保存处理后的结果信息HttpBase
     */
    private HttpBase HttpBaseEditSave() throws wlglException {
        doEditSave();

        String tmp = "";
        if (inDetailMode) {
            tmp = "alert('修改成功');\n"
                  + "self.location.href='" + UtilWebTools.getRequestPath() +
                  findparam + "&mode=edit&seqn=" + seqn + "';";
        } else {
            tmp = "alert('修改成功');\n"
                  + "self.location.href='" + UtilWebTools.getRequestPath() +
                  findparam + "';";
        }
        HttpBase val = HttpBase.genHttpScript(tmp);
        return val;
    }

    /**
     * prepaireEditExtraColumns
     * 功能：准备编辑状态,额外的数据列保存信息。
     * @param fieldList    StringBuffer  update语句有关field的set信息
     * @param fieldValues  ArrayList     各个额外字段的具体值
     * @param i            int           已经处理到第i个字段
     */

    private void prepairedEditExtraColumns(String Table,
			StringBuffer fieldList, ArrayList fieldValues, int i)
			throws wlglException {
		for (int j = 0; j < addExtraColumns.size(); j++) {
			// curCol数据列
			Properties prop = (Properties) addExtraColumns.get(j);
			String type = UtilCommon.NVL(prop.getProperty("type"));
			String field = UtilCommon.NVL(prop.getProperty("field"));

			if (fieldList.equals("")) {
				fieldList.append(field + "=" + "?" );
			} else {
				fieldList.append("," + field + "=" + "?");
			}
			setExtraColumnValue(Table, type, field, fieldValues, prop);
			i++;
		}
	}

    static public void prepairedEditExtraColumns(String Table,
            StringBuffer fieldList, ArrayList fieldValues, int i,
			ArrayList addExtraColumns, boolean inDetailMode,
			StringBuffer seqnbuffer) throws wlglException {
		for (int j = 0; j < addExtraColumns.size(); j++) {
			// curCol数据列
			Properties prop = (Properties) addExtraColumns.get(j);
			String type = UtilCommon.NVL(prop.getProperty("type"));
			String field = UtilCommon.NVL(prop.getProperty("field"));

			if (fieldList.equals("")) {
				fieldList.append(field + "=" + "?");
			} else {
				fieldList.append("," + field + "=" + "?");
			}
			setExtraColumnValue(Table, type, field, fieldValues, prop,
					inDetailMode, seqnbuffer);
			i++;
		}
	}

    /**
     * setExtraColumnValue
     * 功能：根据类型设置额外数据列当前值。
     * @param type        String    字段类型
     * @param field       String    字段
     * @param fieldValues ArrayList 字段值
     * @param prop        Properties字段属性
     */
    protected void setExtraColumnValue(String Table, String type,
                                       String field,
                                       ArrayList fieldValues,
                                       Properties prop) throws
            wlglException {
        StringBuffer seqnbuffer = new StringBuffer(seqn);
        setExtraColumnValue(Table, type, field,
                            fieldValues,
                            prop,
                            inDetailMode, seqnbuffer);
        seqn = seqnbuffer.toString();

    }

    static public void setExtraColumnValue(String Table, String type,
                                           String field,
                                           ArrayList fieldValues,
                                           Properties prop,
                                           boolean inDetailMode,
                                           StringBuffer seqnbuffer) throws
            wlglException {
        //创建时间，编辑时间，执行时间。
        if (type.equals("time")) {
            fieldValues.add(UtilCommon.getTime());
        }
        //单据日期
        else if (type.equals("date")) {
            fieldValues.add(UtilCommon.getDate());
        }
        else if (type.equals("DetailSeqn")) {

        	Integer aseqn=genRowNo(Table,field,prop.getProperty("mainkey"),UtilWebTools.getRequestParameter("seqn"));
            fieldValues.add(aseqn);
        }

        else if (type.equals("seqn")) {
            fieldValues.add(UtilWebTools.getRequestParameter("seqn"));
        }
        //单据号自动生成
        else if (type.equals("billno")) {
            String aseqn = genbillid(Table,field,prop.getProperty("pre"),4);
			fieldValues.add(aseqn);
			if (!inDetailMode) {
				seqnbuffer.delete(0, seqnbuffer.length());
				seqnbuffer.append(aseqn);
			}
        }	
        //登录名
        else if (type.equals("loginname")) {
        	BeanLogin userinfo = ((BeanLogin) UtilWebTools.
                    getSession().getAttribute(
                            "userInfo"));

            fieldValues.add(userinfo.getLoginName());
        }
        //登录编号
        else if (type.equals("logincode")) {
        	BeanLogin userinfo = ((BeanLogin) UtilWebTools.
                    getSession().getAttribute(
                            "userInfo"));
            fieldValues.add(userinfo.getLoginSeqn());
        }
        //员工编号
        else if (type.equals("employeecode")) {
        	BeanLogin userinfo = ((BeanLogin) UtilWebTools.
                    getSession().getAttribute(
                            "userInfo"));
            fieldValues.add(userinfo.getEmployeCode());
        }
        //数据常量设置 usestate,thestate
        else if (type.equals("data")) {
            fieldValues.add(UtilCommon.NVL(prop.getProperty("value")));
        }
        //其它
        else if (type.equals("comp")) {
            fieldValues.add(UtilWebTools.getRequestParameter(prop.getProperty("comp")));

        }
    }
    
    /**
     * genbillid
     * 功能：单据号自动生成
     * @param Table        String    字段类型
     * @param field       String    字段
     * @param pre 			String   字段值
     * @param genbillid    String   字段属性
     */

    public static String genbillid(String Table,String field,String pre,int suflength) throws
            wlglException {

    	// 取得最大序号
        UtilDB tmp2 = new UtilDB();
        String mytmpSQL2 = "select max(" + field + ") from " +
                           Table + " where " + field
                           + " like ?";
        String param2 = UtilCommon.NVL(pre) +
                        "-" + UtilCommon.getDate("yyMMdd") + "%";

        ArrayList myArray2 = new ArrayList();
        myArray2.add(param2);
        ArrayList rs2 = tmp2.exeQueryOneRow(mytmpSQL2, myArray2);
        String maxseqn = "";
        if (rs2.size() > 0) {
            maxseqn = (String) rs2.get(0);
            if (maxseqn == null || maxseqn.equals("")) {
                maxseqn = "0";
            } else {
                int lastindex = maxseqn.lastIndexOf("-");
                maxseqn = maxseqn.substring(lastindex + 1);
            }
        } else {
            maxseqn = "0";
        }
        tmp2.closeCon();
        //----------------------------------------
        maxseqn = Integer.toString(Integer.parseInt(maxseqn) +
                                   1);
        int prefixlength = suflength - maxseqn.length();
        String prefix = "";
        for (int i = 0; i < prefixlength; i++) {
            prefix += "0";
        }
        //---------------------------
        String billid = "";

        billid = UtilCommon.NVL(pre) + "-" + UtilCommon.getDate("yyMMdd") + "-"
				+ prefix + maxseqn;
		return billid;
    }
    
    /**
     * genbillid
     * 功能：单据号行号自动生成
     * @param Table       String    字段类型
     * @param field       String    字段
     * @param Mainfield  	String   主键名称
     * @param genbillid    String   主键值
     */

    public static Integer genRowNo(String Table,String field,String Mainfield ,String MainfieldValue) throws
            wlglException {

    	// 取得最大序号
        UtilDB tmp2 = new UtilDB();
        String mytmpSQL2 = "select max(" + field + ") from " +
                           Table + " where " + Mainfield + " =?";
        ArrayList myArray2 = new ArrayList();
        myArray2.add(MainfieldValue);
        ArrayList rs2 = tmp2.exeQueryOneRow(mytmpSQL2, myArray2);
        String maxsRowno ="0";
        if (rs2.size() > 0) {
        	maxsRowno = (String) rs2.get(0);
            if (maxsRowno == null || maxsRowno.equals("")) {
            	maxsRowno ="0";
            }
        } else {
        	maxsRowno = "0";
        }
        tmp2.closeCon();
        return Integer.parseInt(maxsRowno) +1;
    }

    /**
	 * HttpBaseAddSave 功能:增加模式的保存处理
	 * 
	 * @return HttpBase 保存处理后的结果信息HttpBase
	 */
    protected void afterAddSave(UtilDB utildb) throws wlglException {

    }

    protected void afterEditSave(UtilDB utildb) throws wlglException {

    }

    protected void beforeEditSave(UtilDB utildb) throws wlglException {

    }

    protected void doAddSave() throws wlglException {
        UtilDB utilDB = new UtilDB();
        utilDB.beginTransaction();
        try {
            if (inDetailMode) {
                doAddSave(utilDB, DetailTable);
            } else {
                doAddSave(utilDB, Table);
            }
            afterAddSave(utilDB);
            utilDB.commit();
        } catch (wlglException ex) {
            utilDB.rollback();
            throw ex;
        } finally
        {
        	utilDB.closeCon();
        }
    }

    protected void afterDelete(UtilDB utildb) throws
            wlglException {

    }

    protected void beforeDelete(UtilDB utildb) throws
            wlglException {

    }

    protected void doDelete(String atable, String aseqnfield, String aseqn) throws
            wlglException {

        UtilDB utilDB = new UtilDB();
        utilDB.beginTransaction();
        try {
            if (inDetailMode) {
                beforeDelete(utilDB);
                String tmpSQL =
                        "delete from " + DetailTable + " where " +
                        DetailSeqnField + " ='" +
                        detailseqn + "'  and "+seqnField+"='"+seqn+"'";
                utilDB.exeUpdate(tmpSQL);
                afterDelete(utilDB);
                utilDB.commit();
            } else {
                beforeDelete(utilDB);
                String tmpSQL = "";
                if (pageType.equals("masterDetail")) {
                    tmpSQL =
                            "delete from " + DetailTable + " where " +
                            seqnField + "='" +
                            seqn + "' ";
                    utilDB.exeUpdate(tmpSQL);
                }
                tmpSQL = "delete from " + Table + " where " + seqnField +
                         "='" +
                         seqn + "' ";
                utilDB.exeUpdate(tmpSQL);
                afterDelete(utilDB);
                utilDB.commit();
            }
        } catch (wlglException ex) {
            utilDB.rollback();
            throw ex;
        } finally
        {
            utilDB.closeCon();
        }

    }

    protected void doAddSave(UtilDB utilDB, String Table) throws wlglException {
		String tmpSQL = "insert Into " + Table;
		String fieldList = "";
		String values = "";
		ArrayList fieldValues = new ArrayList();
		Iterator itx = editComps.iterator();
		int i = 0;
		while (itx.hasNext()) {
			EditComponent acomp = (EditComponent) itx.next();
			if ((!hasProperty(addExtraColumns, "field", acomp.getField()))
					&& !acomp.getField().equals("")) {
				fieldValues.add(acomp.getValue());
				if (fieldList.equals("")) {
					fieldList += acomp.getField();
					values += "?" ;
				} else {
					fieldList += "," + acomp.getField();
					values += ",?" ;
				}
				/*
				 * 2008-06-07
				 */
				//处理主键值
				if(acomp.getField().equalsIgnoreCase(seqnField))
				{
					seqn=acomp.getValue();
				}
				if(acomp.getField().equalsIgnoreCase(DetailSeqnField))
				{
					detailseqn=acomp.getValue();
				}
			}
			i++;
		}
		StringBuffer myfl = new StringBuffer(fieldList);
		StringBuffer myvalues = new StringBuffer(values);
		prepairedAddExtraColumns(Table, myfl, myvalues, fieldValues, i);
		String SQLfieldList = myfl.toString();
		String SQLvalues = myvalues.toString();

		tmpSQL += "(" + SQLfieldList + ") values(" + SQLvalues + ")";
		utilDB.exeUpdate(tmpSQL, fieldValues);

		int detailEditRowCount = UtilWebTools
				.getSessionAtrributerI("DetailEditRow");
		// 保存多行纪录的其他纪录
		if (inDetailMode) {
			for (int r = 1; r < detailEditRowCount; r++) {
				fieldValues.clear();
				Iterator itxo = editComps.iterator();
				boolean mustinsert = true;
				while (itxo.hasNext()) {
					EditComponent acomp = (EditComponent) itxo.next();
					if ((!hasProperty(addExtraColumns, "field", acomp
							.getField()))
							&& !acomp.getField().equals("")) {
						String aval = UtilWebTools.getRequestParameter(acomp
								.getID()
								+ r);
						if (!canInsert(acomp, aval)) {
							mustinsert = false;
							break;
						}
	
						fieldValues.add(UtilWebTools.getRequestParameter(acomp
								.getID()
								+ r));
					}
				}
				StringBuffer myfla = new StringBuffer(fieldList);
				StringBuffer myvaluesa = new StringBuffer(values);
				ArrayList tmpAddExtraColumns = new ArrayList();
				for (int k = 0; k < addExtraColumns.size(); k++) {
					Properties aprops = (Properties) addExtraColumns.get(k);
					Properties myaprops = new Properties(aprops);
					if (aprops.getProperty("type").equals("comp")) {
						myaprops.setProperty("comp", aprops.getProperty("comp")
								+ r);
					}
					tmpAddExtraColumns.add(myaprops);
					System.err.println(aprops.getProperty("comp") + r);
				}

				prepairedAddExtraColumns(tmpAddExtraColumns, Table, myfla,
						myvaluesa, fieldValues, i);

				if (mustinsert) {

					utilDB.exeUpdate(tmpSQL, fieldValues);
				}
			}
		}
	}
    public boolean canInsert(EditComponent acomp,String aval){
        boolean result=true;
        if (aval.equals("") && acomp.isMustFill()) {
            result=false;
        }
        return result;
    }

    /**
	 * HttpBaseAddSave 功能:增加模式的保存处理
	 * 
	 * @return HttpBase 保存处理后的结果信息HttpBase
	 */
    private HttpBase HttpBaseAddSave() throws wlglException {
        doAddSave();
        String tmp = "";
        if (!inDetailMode && (pageType.equals("masterDetail"))) {
            tmp = "alert('增加成功');\n"
                  + "self.location.href='" +
                  UtilWebTools.getRequestPath() +
                  findparam + "&mode=edit&seqn=" + seqn + "';";
        } else if (inDetailMode) {
            tmp = "alert('增加成功');\n"
                  + "self.location.href='" +
                  UtilWebTools.getRequestPath() +
                  findparam + "&mode=edit&seqn=" + seqn + "';";
        } else {
            tmp = "alert('增加成功');\n"
                  + "self.location.href='" +
                  UtilWebTools.getRequestPath() +
                  findparam + "';";
        }

        HttpBase val = HttpBase.genHttpScript(tmp);
        return val;

    }

    /**
     * prepaireAddExtraColumns
     * 功能：准备编辑状态,额外的数据列保存信息。
     * @param fieldList    StringBuffer  insert语句有关field的信息
     * @param values       StringBuffer  insert语句values部分的信息
     * @param fieldValues  ArrayList     各个额外字段的具体值
     * @param i            int           已经处理到第i个字段
     */
    protected void prepairedAddExtraColumns(String Table,
                                            StringBuffer fieldList,
                                            StringBuffer values,
                                            ArrayList fieldValues, int i) throws
            wlglException {
        prepairedAddExtraColumns(addExtraColumns, Table, fieldList, values,
                                 fieldValues, i);
    }

    protected void prepairedAddExtraColumns(ArrayList addExtraColumns,
			String Table, StringBuffer fieldList, StringBuffer values,
			ArrayList fieldValues, int i) throws wlglException {
		for (int j = 0; j < addExtraColumns.size(); j++) {
			// curCol数据列
			Properties prop = (Properties) addExtraColumns.get(j);
			String type = UtilCommon.NVL(prop.getProperty("type"));
			String field = UtilCommon.NVL(prop.getProperty("field"));

			if (fieldList.equals("")) {
				fieldList.append(field);
				values.append("?" );
			} else {
				fieldList.append("," + field);
				values.append(",?" );
			}
			setExtraColumnValue(Table, type, field, fieldValues, prop);
			i++;
		}
	}

    /**
     * hasProperty
     * 功能：判断属性prop值是否为value
     * @param props ArrayList  属性表
     * @param prop  String     属性
     * @param value String     值
     * @return boolean         ture：属性prop值为value
     */
    static public boolean hasProperty(ArrayList props, String prop,
                                      String value) {
        for (int i = 0; i < props.size(); i++) {
            // curCol数据列
            Properties myprop = (Properties) props.get(i);
            String myValue = UtilCommon.NVL(myprop.getProperty(prop));
            if (myValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * findType
     * 功能:查询属性表数组，如果属性表的type是type类型则返回属性表，否则返回null
     * @param props ArrayList   要查询的属性表数组
     * @param type  type类型的值
     * @return Properties  type是type类型的属性表
     */
    private Properties findType(ArrayList props, String type) {
        for (int i = 0; i < props.size(); i++) {
            // curCol数据列
            Properties myprop = (Properties) props.get(i);
            String myValue = UtilCommon.NVL(myprop.getProperty("type"));
            if (myValue.equals(type)) {
                return myprop;
            }
        }
        return null;
    }

    /**
     * validate
     * 功能:校验输入内容合法性(唯一性)
     * @return String
     */
    protected String validate() throws wlglException {
        String val = "";
        Iterator itx = editComps.iterator();
        int i = 0;
        int detailEditRowCount = UtilWebTools.getSessionAtrributerI(
                "DetailEditRow");
        while (itx.hasNext()) {
            EditComponent acomp = (EditComponent) itx.next();
            if (acomp.isUnique()) {
                if (mode.equals("edit")) {
                    if (acomp.getValue().equals(acomp.getDefValue())) {
                        continue;
                    }
                }
                String atable = Table;
                if (inDetailMode) {
                    atable = DetailTable;
                }
                String tmpSQL = "select count(*) from " + atable
                                + " where " + acomp.getField() + "=?";
                ArrayList param = new ArrayList();
                UtilDB utilDB = new UtilDB();
                param.add(acomp.getValue());
                ArrayList myrow = utilDB.exeQueryOneRow(tmpSQL, param);
                String count = (String) myrow.get(0);
                if (Integer.parseInt(count) > 0) {
                    val = acomp.getCaption() + "值重复!请重新输入";
                    return val;
                }
                if (inDetailMode && detailMode.equals("add")) {
                    // 数据库不允许重复
                    for (int r = 1; r < detailEditRowCount; r++) {
                        String aval = UtilCommon.NVL(UtilWebTools.
                                getRequestParameter(acomp.
                                getID() + r));
                        if (!aval.equals("")) {
                            tmpSQL = "select count(*) from " + DetailTable
                                     + " where " + acomp.getField() + "=?";
                            param.clear();
                            param.add(aval);
                            myrow = utilDB.exeQueryOneRow(tmpSQL, param);
                            count = (String) myrow.get(0);
                            if (Integer.parseInt(count) > 0) {
                                val = acomp.getCaption() + "值重复!请重新输入";
                                return val;
                            }
                        }
                    }
                    // 录入的不允许重复
                    for (int r = 0; r < detailEditRowCount; r++) {
                        String aval = Integer.toString(r);
                        if (r == 0) {
                            aval = "";
                        }
                        aval = UtilCommon.NVL(UtilWebTools.
                                              getRequestParameter(acomp.
                                getID() + aval));
                        if (!aval.equals("")) {
                            for (int s = r + 1; s < detailEditRowCount; s++) {
                                String bval = UtilCommon.NVL(UtilWebTools.
                                        getRequestParameter(acomp.getID() +
                                        s));
                                if (!bval.equals("")) {
                                    if (aval.equals(bval)) {
                                        val = acomp.getCaption() +
                                              "值重复!请重新输入";
                                        return val;
                                    }
                                }
                            }
                        }
                    }
                }
                utilDB.closeCon();
            }
        }

        return val;
    }

    /**
     * genDBDetailSql
     * 功能:产生详细信息的数据库查询结果
     * @return utilDB  详细信息的数据库查询结果
     */
    protected UtilDB genDBDetailSQL() throws wlglException {
        UtilDB utilDB = new UtilDB();
        ArrayList params = new ArrayList();
        String myWhereStr = "";
        String conditionparam = "";
        myWhereStr = "";
        if (!detailWhereSQL.equals("")) {
            myWhereStr = detailWhereSQL + " and " + "a." + seqnField +
                         "=?";
        } else {
            myWhereStr = "a." + seqnField + "=?";
        }
        params.addAll(detailWhereSQLParam);
        params.add(seqn);

        if (!(myWhereStr.equals(""))) {
            myWhereStr = " where " + myWhereStr;
        }

        String exesql = "";
        exesql = detailSelectSQL + " " + myWhereStr +
                 " " + detailOrderSQL;
        utilDB.exeQuery(exesql,
                        params);
        return utilDB;
    }

    /**
     * genQueryDBResult
     * 功能：根据当前条件查询数据库，取得符合条件的数据库结果
     * @return UtilDB  数据库查询结果
     */
    public UtilDB genDBQueryResult() throws wlglException {
        UtilDB utilDB = new UtilDB();
        ArrayList params = new ArrayList();
        Iterator itx = qryComps.iterator();
        String mywhereStr = "";
        String conditionparam = "";
        mywhereStr = "";
        // 构造动态SQL、参数.　
        while (itx.hasNext()) {
            EditComponent comp = (EditComponent) itx.next();
            String param = "";
            String qval = "";
            if (initpage) {
                param = comp.getQryValue();
                qval = comp.getValue();
                if (param.equals("")) {
                    param = comp.getQryValue(comp.getDefValue());
                    qval = comp.getDefValue();
                }
            } else {
                param = comp.getQryValue();
                qval = comp.getValue();
            }
            if (!param.equals("")) {
                if (!comp.getField().equals("")) {
                    if (!mywhereStr.equals("")) {
                        mywhereStr += " and (upper(" + comp.getField() + ") " +
                                comp.getOper() +
                                " upper(?" +"))";
                    } else {
                        mywhereStr += "(upper(" + comp.getField() + ") " +
                                comp.getOper() +
                                " upper(?" +"))";
                    }
                    params.add(param);
                }
                if (conditionparam.equals("")) {
                    conditionparam += comp.getID() + "=" + qval;
                } else {
                    conditionparam += "&" + comp.getID() + "=" + qval;
                }

                if (!comp.getDisID().equals(comp.getID())) {
                    conditionparam += "&" + comp.getDisID() + "=" +
                            comp.getDisValue();
                }
            }

        }
        if (!qry_whereSQL.equals("")) {
            if (!mywhereStr.equals("")) {
                mywhereStr += " and " + qry_whereSQL;
            } else {
                mywhereStr = qry_whereSQL;
            }
            params.addAll(qry_whereSQLParam);
        }
        if (!(mywhereStr.equals(""))) {
            mywhereStr = " where " + mywhereStr;
        }
        findparam = "?tseqn=" + tseqn;
        if (!conditionparam.equals("")) {
            findparam += "&" + conditionparam;
        }
        Query_SQL = qry_selectSQL + " " + mywhereStr;
        Query_SQLParam = params;
        utilDB.exeQuery(qry_selectSQL + " " + mywhereStr + " " +
                        qry_orderSQL,
                        params);
        return utilDB;
    }
    
    /**
     * genHttpBaseQueryContext
     * 功能：根据已有数据库信息UtilDB产生查询结果HttpBase表格对象
     * @param utilDB      UtilDB     已有数据库信息
     * @param columnsInfo ArrayList  列名信息表
     * @param rowCount    int        每页行数。
     * @return HttpBase              httpBase 表格对象
     */
    private HttpBase genHttpBaseQueryContext(UtilDB utilDB,
                                             ArrayList columnsInfo,
                                             int rowCount) throws
            wlglException {
        String ppage = UtilWebTools.getRequestParameter("page");

        String baseparam = findparam + "&mode=" + mode;
        if (mode.equals("edit")) {
            if (!inDetailMode) {
                baseparam += "&seqn=" + seqn;
            }
        }
        if (!ppage.equals("")) {
            baseparam = UtilCommon.Replace("&page=" + ppage, "",
                                           baseparam);
        }
        String val = "";
        HttpBase base = new HttpBase();
        HttpBase table1 = HttpBase.genHttpTable("btd3", "100%", "1",
                                                "5",
                                                "0");
        base.getElements().add(table1);
        HttpBase tr1 = HttpBase.genHttpTR("btd3", "center");
        table1.getElements().add(tr1);

        // 设置grid标题栏
        for (int i = 0; i < columnsInfo.size(); i++) {
            if (((Properties) (columnsInfo.
                               get(i))).getProperty(
                                       "title").equals("")) {
                continue;
            }

            HttpBase td = HttpBase.genHttpTH();
            String width = (((Properties) (columnsInfo.get(i))).
                            getProperty(
                                    "width"));
            if ((width != null) && (width != "")) {
                td.getProperties().setProperty("width", width);
            }
            tr1.getElements().add(td);

            HttpBase span = HttpBase.genHttpSpan("ct",
                                                 (((Properties) (columnsInfo.
                    get(i))).getProperty(
                            "title")));
            td.getElements().add(span);
        }

        // 设置数据内容
        int curPage = UtilCommon.getInt(UtilWebTools.
                                        getRequestParameter(
                                                "page"));
        if (curPage == 0) {
            curPage = 1;
        }

        // 取得数据库记录
        ArrayList dbsome = utilDB.getSomeRow((curPage - 1) * rowCount +
                                             1,
                                             curPage * rowCount);
        Iterator itxsome = dbsome.iterator();
        for (int i = 0; i < rowCount; i++) {
            ArrayList dbrow;
            dbrow = null;
            if (itxsome.hasNext()) {
                dbrow = (ArrayList) itxsome.next();
            }
            // 如果没有记录，则不用生成行，直接退出
            if (dbsome.size() > 0) {
                if (dbrow == null) {
                    break;
                }
            }
            HttpBase tr = HttpBase.genHttpTR("btd2");
            table1.getElements().add(tr);
            for (int j = 0; j < columnsInfo.size(); j++) {
                if (dbrow != null) {
                    if (j > dbrow.size()) {
                        break;
                    }
                }
                if (((Properties) (columnsInfo.
                                   get(j))).getProperty(
                                           "title").equals("")) {
                    continue;
                }

                // curCol数据列
                String scol = UtilCommon.NVL(((Properties) (columnsInfo.
                        get(
                                j))).
                                             getProperty(
                        "data"));
                int curCol = j;
                if (!scol.equals("")) {
                    curCol = Integer.parseInt(scol);
                }
                String value = "";

                if (dbrow != null) {
                    if (curCol >= dbrow.size()) {
                        break;
                    }

                    value = (String) (dbrow.get(curCol));
                }
                // width 数据列宽度
                String width = UtilCommon.NVL(((Properties) (
                        columnsInfo.
                        get(j))).
                                              getProperty(
                        "width"));

                // width 数据列类型
                String type = UtilCommon.NVL(((Properties) (columnsInfo.
                        get(
                                j))).
                                             getProperty(
                        "type"));

                // 新建列
                HttpBase td = HttpBase.genHttpTD();
                if ((width != "")) {
                    td.getProperties().setProperty("width", width);
                }
                tr.getElements().add(td);
                if (dbrow == null) {
                    td.setValue("&nbsp;");
                } else {
                    // radio 类型按钮
                    if (type.equals("radio") && (dbrow != null)) {

                        td.getProperties().setProperty("align",
                                "center");
                        HttpBase input = HttpBase.
                                         genHttpInputRadio("radio",
                                value, "Javascript:doWhich();");
                        td.getElements().add(input);
                    }
                    // choose　类型按钮
                    else if (type.equals("choose")) {
                        String jsParam = "";
                        for (int tmp = 0; tmp < dbrow.size(); tmp++) {
                            String avalue = (String) (dbrow.get(tmp));
                            if (jsParam.equals("")) {
                                jsParam = "'" + avalue + "'";

                            }

                            else {
                                jsParam += "," + "'" + avalue + "'";
                            }
                        }
                        HttpBase a = HttpBase.genHttpA("CURSOR:Hand;",
                                "doChoose([" + jsParam + "]);");
                        td.getElements().add(a);
                        a.setValue(value);
                    }
                    
                    // winopen　类型按钮打开win应用程序
                    else if (type.equals("winopen")) {
                        String jsParam = "";
                        for (int tmp = 0; tmp < dbrow.size(); tmp++) {
                            String avalue = (String) (dbrow.get(tmp));
                            if (jsParam.equals("")) {
                                jsParam = "'" + avalue + "'";

                            }

                            else {
                                jsParam += "," + "'" + avalue + "'";
                            }
                        }
                        HttpBase a = HttpBase.genHttpA("CURSOR:Hand;",
                                "dowinOpen([" + jsParam + "]);");
                        td.getElements().add(a);
                        a.setValue(value);
                    }

                    // 普通数据
                    else if (type.equals("")) {
                        td.setValue(UtilCommon.NVL(value));
                    }
                }
            }
            if (dbrow == null) {
                break;
            }
        }
        // 设置页脚
        HttpBase table311 = HttpBase.genHttpTable("btd", "100%", "1",
                                                  "5",
                                                  "0");
        base.getElements().add(table311);

        HttpBase tr3111 = HttpBase.genHttpTR("btd");

        table311.getElements().add(tr3111);

        HttpBase td31111 = new HttpBase();
        td31111.setTag("td");

        td31111.getProperties().setProperty("align", "right");

        tr3111.getElements().add(td31111);

        if (curPage == 1) {
            HttpBase myval = HttpBase.genHttpValue("首 页");
            td31111.getElements().add(myval);
        } else {
            HttpBase a = new HttpBase();
            a.setTag("a");
            a.getProperties().setProperty("href",
                                          UtilWebTools.getRequestPath() +
                                          baseparam +
                                          "&page=1");
            a.setValue("首 页");
            td31111.getElements().add(a);
        }

        HttpBase val1 = HttpBase.genHttpValue(" | ");
        td31111.getElements().add(val1);

// 上页
        if (curPage == 1) {
            HttpBase myval = HttpBase.genHttpValue("上一页");

            td31111.getElements().add(myval);
        } else {
            HttpBase a = new HttpBase();
            a.setTag("a");
            a.getProperties().setProperty("href",
                                          UtilWebTools.getRequestPath() +
                                          baseparam +
                                          "&page=" + (curPage - 1));
            a.setValue("上一页");
            td31111.getElements().add(a);
        }
        HttpBase val2 = HttpBase.genHttpValue(" | ");
        td31111.getElements().add(val2);

// 下页

        if (curPage * rowCount >= utilDB.rowCount) {
            HttpBase myval = HttpBase.genHttpValue("下一页");
            td31111.getElements().add(myval);
        } else {
            HttpBase a = new HttpBase();
            a.setTag("a");
            a.getProperties().setProperty("href",
                                          UtilWebTools.getRequestPath() +
                                          baseparam +
                                          "&page=" + (curPage + 1));
            a.setValue("下一页");
            td31111.getElements().add(a);
        }
        HttpBase val3 = HttpBase.genHttpValue(" | ");
        td31111.getElements().add(val3);

        int totpage = utilDB.rowCount / rowCount;
        if (utilDB.rowCount > totpage * rowCount) {
            totpage = totpage + 1;
        }

// 尾页
        if (curPage * rowCount >= utilDB.rowCount) {
            HttpBase myval = HttpBase.genHttpValue("尾页");
            td31111.getElements().add(myval);
        } else {
            HttpBase a = new HttpBase();
            a.setTag("a");

            a.getProperties().setProperty("href",
                                          UtilWebTools.getRequestPath() +
                                          baseparam +
                                          "&page=" + totpage);

            a.setValue("尾页");
            td31111.getElements().add(a);
        }

        HttpBase val41 = HttpBase.genHttpValue(" | ");
        td31111.getElements().add(val41);

        HttpBase val4 = HttpBase.genHttpValue("第 ");
        td31111.getElements().add(val4);

        HttpBase font5 = HttpBase.genHttpFont("red",
                                              Integer.toString(curPage));
        td31111.getElements().add(font5);

        HttpBase val5 = HttpBase.genHttpValue(" 页 | 共 ");
        td31111.getElements().add(val5);

        HttpBase font6 = HttpBase.genHttpFont("red",
                                              Integer.toString(totpage));
        td31111.getElements().add(font6);

        HttpBase val6 = HttpBase.genHttpValue(" 页 | 共 ");
        td31111.getElements().add(val6);

        HttpBase font7 = HttpBase.genHttpFont("red",
                                              Integer.toString(utilDB.
                rowCount));
        td31111.getElements().add(font7);

        HttpBase val7 = HttpBase.genHttpValue(" 条 | ");
        td31111.getElements().add(val7);

        HttpBase font8 = HttpBase.genHttpFont("red",
                                              Integer.toString(rowCount));
        td31111.getElements().add(font8);

        HttpBase val8 = HttpBase.genHttpValue(" 条 / 页");
        td31111.getElements().add(val8);

        return base;
    }

    /**
     * genHtmlQueryResult
     * 功能:产生查询结果html内容
     * @return String 查询结果html内容
     */
    public String genHtmlQueryResult() throws wlglException {
        UtilDB utilDB = genDBQueryResult();
        int rowCount = UtilWebTools.getSessionAtrributerI("RowCount");
        return genHttpBaseQueryContext(utilDB, columnsInfo, rowCount).
                GenHtml();
    }

}
