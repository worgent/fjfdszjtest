package net.trust.application.report.rtpdistanceerror.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;

import net.trust.application.report.rtpdistanceerror.domain.RtpDistanceErrorFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class RtpDistanceErrorAction extends PaginactionAction{
	private Log log = LogFactory.getLog(RtpDistanceErrorAction.class);
	private RtpDistanceErrorFacade rtpDistanceErrorFacade;

	private HashMap search = new HashMap();
	private List rtpDistanceErrorList;
	
	private String actionType;		
	private String action;
	private String xml;
	
	public String execute() throws Exception {		
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(rtpDistanceErrorFacade.findRtpDistanceErrorCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "runRecord";
			search = (HashMap)super.getParameter();
			search.put("staffId", userInfo.getStaffId());
			rtpDistanceErrorList = rtpDistanceErrorFacade.findRtpDistanceError(search);
			return "search";
			
		}
		return ERROR;
	}





	/**
	 * @return the rtpDistanceErrorFacade
	 */
	public RtpDistanceErrorFacade getRtpDistanceErrorFacade() {
		return rtpDistanceErrorFacade;
	}



	/**
	 * @param rtpDistanceErrorFacade the rtpDistanceErrorFacade to set
	 */
	public void setRtpDistanceErrorFacade(
			RtpDistanceErrorFacade rtpDistanceErrorFacade) {
		this.rtpDistanceErrorFacade = rtpDistanceErrorFacade;
	}



	/**
	 * @return the rtpDistanceErrorList
	 */
	public List getRtpDistanceErrorList() {
		return rtpDistanceErrorList;
	}



	/**
	 * @param rtpDistanceErrorList the rtpDistanceErrorList to set
	 */
	public void setRtpDistanceErrorList(List rtpDistanceErrorList) {
		this.rtpDistanceErrorList = rtpDistanceErrorList;
	}

	public HashMap getSearch() {
		return search;
	}

	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
}
