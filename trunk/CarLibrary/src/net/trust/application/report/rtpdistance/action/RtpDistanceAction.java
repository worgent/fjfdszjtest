package net.trust.application.report.rtpdistance.action;

import java.util.HashMap;
import java.util.List;

import net.trust.PaginactionAction;
import net.trust.application.report.rtpdistance.domain.RtpDistanceFacade;
import net.trust.application.report.runrecord.domain.RunRecordFacade;
import net.trust.application.systemManage.manager.dto.UserInfo;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.xwork.ActionContext;
/**
 * 综合报表 -》 行车记录
 * @author chenqf
 *
 */
public class RtpDistanceAction extends PaginactionAction{
	private Log log = LogFactory.getLog(RtpDistanceAction.class);
	private RtpDistanceFacade rtpDistanceFacade;

	private HashMap search = new HashMap();
	private List rtpDistanceList;
	
	private String actionType;		
	private String action;
	private String xml;
	
	public String execute() throws Exception {		
		UserInfo userInfo=(UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		if ("".equals(actionType) || null == actionType){
			if(super.queryFlag==0){//判断查询状态				
				super.setPageRecord(15);//设置页面单页显示总记录数
				super.setParameter(search);	//设置查询参数
				super.setCount(rtpDistanceFacade.findRtpDistanceCount(search));//获取查询结果总记录数
			}
			this.exportFlag = "runRecord";
			search = (HashMap)super.getParameter();
			search.put("staffId", userInfo.getStaffId());
			rtpDistanceList = rtpDistanceFacade.findRtpDistance(search);
			return "search";
			
		}
		return ERROR;
	}



	/**
	 * @return the rtpDistanceFacade
	 */
	public RtpDistanceFacade getRtpDistanceFacade() {
		return rtpDistanceFacade;
	}



	/**
	 * @param rtpDistanceFacade the rtpDistanceFacade to set
	 */
	public void setRtpDistanceFacade(RtpDistanceFacade rtpDistanceFacade) {
		this.rtpDistanceFacade = rtpDistanceFacade;
	}



	/**
	 * @return the rtpDistanceList
	 */
	public List getRtpDistanceList() {
		return rtpDistanceList;
	}



	/**
	 * @param rtpDistanceList the rtpDistanceList to set
	 */
	public void setRtpDistanceList(List rtpDistanceList) {
		this.rtpDistanceList = rtpDistanceList;
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
