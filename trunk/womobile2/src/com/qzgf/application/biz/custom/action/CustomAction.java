package com.qzgf.application.biz.custom.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.custom.domain.CustomFacade;
import com.qzgf.comm.Constant;
import com.qzgf.utils.servlet.UserSession;
import com.qzgf.utils.ui.OptionsString;

/**
 * 定制信息
 * 
 * 
 */
@SuppressWarnings("serial")
public class CustomAction extends BaseAction {
	Log log = LogFactory.getLog(CustomAction.class);
	@SuppressWarnings("unchecked")
	Map custom = new HashMap();
	private String actionType;
	private CustomFacade customFacade;
	@SuppressWarnings("unchecked")
	private List customList = new ArrayList();
	@SuppressWarnings("unchecked")
	private List customItemList = new ArrayList();
	private List<String> event = new ArrayList<String>();
	private List<String> fielddesc = new ArrayList<String>();
	private String num;
	@SuppressWarnings("unchecked")
	private List enumList;
	private List<OptionsString> eventValues = new ArrayList<OptionsString>();

	private String xml;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public CustomFacade getCustomFacade() {
		return customFacade;
	}

	public void setCustomFacade(CustomFacade customFacade) {
		this.customFacade = customFacade;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		UserSession userInfo= (UserSession)(ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY));
		if (actionType == null || "".equals(actionType)) {
			
			custom.put("maker", userInfo.getUserName().toString());
			//枚举来源
			enumList = this.customFacade.findEnums();
			List eventList = this.customFacade.findEvents();

			for (int i = 0; i < eventList.size(); i++) {
				Map map = (Map) eventList.get(i);
				eventValues.add(new OptionsString((String) map.get("id"),
						(String) map.get("dictvalue")));
			}
			return "index";
		} else if ("save".equals(actionType)) {//保存新模板
			if (num == null) {
				num = "0";
			}
			custom.put("maker", userInfo.getUserName().toString());//用户名

			//封装事件
			String rEvents = "";
			Iterator it = event.iterator();
			while (it.hasNext()) {
				String eventIt = (String) it.next();
				if (rEvents.equals("")) {
					rEvents = eventIt;
				} else {
					rEvents = rEvents + "," + eventIt;
				}
			}
			custom.put("events", rEvents);
			this.customFacade.saveFieldCustom(custom, Integer.parseInt(num));
		    search.clear();
			custom.put("maker", userInfo.getUserName().toString());
			//枚举来源
			enumList = this.customFacade.findEnums();
			List eventList = this.customFacade.findEvents();

			for (int i = 0; i < eventList.size(); i++) {
				Map map = (Map) eventList.get(i);
				eventValues.add(new OptionsString((String) map.get("id"),
						(String) map.get("dictvalue")));
			}			
			customList = this.customFacade.findPatternList(custom);
			return "list";
		} else if ("toQueryPage".equals(actionType)) {//进入模板查询列表
			//进入查询界面
			return "list";
		} else if ("query".equals(actionType)) {//模板查询
			//点击查询
			customList = this.customFacade.findPatternList(custom);
			return "list";
		} else if ("toEditPage".equals(actionType)) {//进入更新页面
			//判断是否在用，只有没在使用的模板才可以修改

			//枚举来源
			enumList = this.customFacade.findEnums();
			//全部的事件
			List eventList = this.customFacade.findEvents();
			for (int i = 0; i < eventList.size(); i++) {
				Map map = (Map) eventList.get(i);
				eventValues.add(new OptionsString((String) map.get("id"),
						(String) map.get("dictvalue")));
			}
			custom = this.customFacade.findCustomById(custom);
			customItemList=this.customFacade.findCustomItems(custom);
			String events = (String) custom.get("events");
			String str[] = events.split(",");
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null) {
					event.add(str[i]);
				}
			}
			
			return "editPage";//修改页面

		} else if ("del".equals(actionType)) {
			//判断是否在用，只有没在使用的模板才可以删除
			this.customFacade.delCustomById(custom);
		} else if("update".equals(actionType)){
			if (num == null) {
				num = "0";
			}
			custom.put("maker", userInfo.getUserName().toString());//用户名

			//封装事件
			String rEvents = "";
			Iterator it = event.iterator();
			while (it.hasNext()) {
				String eventIt = (String) it.next();
				if (rEvents.equals("")) {
					rEvents = eventIt;
				} else {
					rEvents = rEvents + "," + eventIt;
				}
			}
			custom.put("events", rEvents);
			this.customFacade.updateFieldCustom(custom, Integer.parseInt(num));
		    search.clear();
			custom.put("maker", userInfo.getUserName().toString());
			//枚举来源
			enumList = this.customFacade.findEnums();
			List eventList = this.customFacade.findEvents();

			for (int i = 0; i < eventList.size(); i++) {
				Map map = (Map) eventList.get(i);
				eventValues.add(new OptionsString((String) map.get("id"),
						(String) map.get("dictvalue")));
			}			
			customList = this.customFacade.findPatternList(custom);
			return "list";
		}

		return "index";
	}

	@SuppressWarnings("unchecked")
	public Map getCustom() {
		return custom;
	}

	@SuppressWarnings("unchecked")
	public void setCustom(Map custom) {
		this.custom = custom;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@SuppressWarnings("unchecked")
	public List getCustomList() {
		return customList;
	}

	@SuppressWarnings("unchecked")
	public void setCustomList(List customList) {
		this.customList = customList;
	}

	public List<OptionsString> getEventValues() {
		return eventValues;
	}

	public void setEventValues(List<OptionsString> eventValues) {
		this.eventValues = eventValues;
	}

	public List<String> getEvent() {
		return event;
	}

	public void setEvent(List<String> event) {
		this.event = event;
	}

	public List<String> getFielddesc() {
		return fielddesc;
	}

	public void setFielddesc(List<String> fielddesc) {
		this.fielddesc = fielddesc;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@SuppressWarnings("unchecked")
	public List getEnumList() {
		return enumList;
	}

	@SuppressWarnings("unchecked")
	public void setEnumList(List enumList) {
		this.enumList = enumList;
	}

	@SuppressWarnings("unchecked")
	public List getCustomItemList() {
		return customItemList;
	}

	@SuppressWarnings("unchecked")
	public void setCustomItemList(List customItemList) {
		this.customItemList = customItemList;
	}

}
