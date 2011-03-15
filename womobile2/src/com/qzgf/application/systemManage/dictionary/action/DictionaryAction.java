package com.qzgf.application.systemManage.dictionary.action;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.qzgf.application.BaseMainAction;
import com.qzgf.application.systemManage.dictionary.domain.DictionaryFacade;
import com.qzgf.utils.ajax.AjaxMessagesJson;

/**
 * 数据字典管理模块
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class DictionaryAction extends BaseMainAction{
	Log log = LogFactory.getLog(DictionaryAction.class);
	private String basePath;
	private AjaxMessagesJson ajaxMessagesJson;
	private DictionaryFacade dictionaryFacade;
	@SuppressWarnings("unchecked")
	private List dictionaryList;//数据字典集
	@SuppressWarnings("unchecked")
	private List dictionarydList;//数据字典子表集
	private String oldVal;
	private String newVal;
	private String dictId;
	private String xml;

	public String getOldVal() {
		return oldVal;
	}

	public void setOldVal(String oldVal) {
		this.oldVal = oldVal;
	}

	public String getNewVal() {
		return newVal;
	}

	public void setNewVal(String newVal) {
		this.newVal = newVal;
	}

	@SuppressWarnings("unchecked")
	public String getDictionary() {
		// 数据字典集
		dictionaryList = dictionaryFacade.findDictionaryList();
		
		return "left";
	}
	
	public String getDictionaryName(){
		String dictName=this.dictionaryFacade.findDictNameByDictId(dictId);

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<value>").append(""+dictName).append("</value>");//
		sb.append("</result>");
		xml = sb.toString();
		return "xml";
	}

	/**
	 * 进入数据字典管理首页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String index() {
		return SUCCESS;
	}

	public String toQueryMark() {
		return "queryMark";
	}
	
	public String edit(){
		try {
			//判断是否是重名
			this.dictionaryFacade.updateDictNameById(search);
			//成功
		} catch (Exception e) {
			e.printStackTrace();
			//失败
		}
		
		return this.getDictionary();
	}
	
	public String del(){
		int num=this.dictionaryFacade.deleteDictById(search);
		if(num==1){
			//成功
		}else{
			//失败
		}
		return this.getDictionary();
	}
	
	@SuppressWarnings("unchecked")
	public String add(){
		try {
			//判断是否是重名
			this.dictionaryFacade.insertDictionary(search);
			//成功
		} catch (Exception e) {
			e.printStackTrace();
			//失败
		}
		
		return this.getDictionary();
	}
	
	
	/**
	 * 查询某一数据字典查询该数据字典子表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list(){
		search.put("dictName", this.dictionaryFacade.findDictNameByDictId((String)search.get("dictId")));
		this.dictionarydList=this.dictionaryFacade.findDictionarydListByDictId(search);
		return "list";
	}
	
	/**
	 * 更新数据字典子表
	 * @return
	 */
	public String updateDictionaryd(){
		try {
			//判断是否是重名
			this.dictionaryFacade.updateDictdById(search);
			//成功
		} catch (Exception e) {
			e.printStackTrace();
			//失败
		}
		return this.list();
	}
	
	
	/**
	 * 添加数据字典子表
	 * @return
	 */
	public String addDictionaryd(){
		try {
			//判断是否是重名
			this.dictionaryFacade.insertDictionaryd(search);
			//成功
		} catch (Exception e) {
			e.printStackTrace();
			//失败
		}
		
		return this.list();
	}
	
	public String delDeictionaryd(){
		int num=this.dictionaryFacade.deleteDictionarydById(search);
		if(num==1){
			//成功
		}else{
			//失败
		}
		return this.list();
		
	}


	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	
	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public DictionaryFacade getDictionaryFacade() {
		return dictionaryFacade;
	}

	public void setDictionaryFacade(DictionaryFacade dictionaryFacade) {
		this.dictionaryFacade = dictionaryFacade;
	}

	@SuppressWarnings("unchecked")
	public List getDictionaryList() {
		return dictionaryList;
	}

	@SuppressWarnings("unchecked")
	public void setDictionaryList(List dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public List getDictionarydList() {
		return dictionarydList;
	}

	public void setDictionarydList(List dictionarydList) {
		this.dictionarydList = dictionarydList;
	}

}
