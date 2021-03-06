/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.qzgf.NetStore.struts.action.BackAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.qzgf.NetStore.service.ICompanyIntroductService;




/** 
 * MyEclipse Struts
 * Creation date: 10-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="companyIntrSuccess" path="/JspForm/BackfuncModual/companyIntroduct.jsp"
 */
public class CompanyIntroductAction extends DispatchAction {
	private ICompanyIntroductService companyIntroductService;
	
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String companyContent=this.companyIntroductService.getCompanyIntrContent();
		request.setAttribute("companyContent", companyContent);
		
		return mapping.findForward("companyIntrSuccess");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    
		String CpyContent=request.getParameter("companyContent");
		boolean flag=false;
		String xgResult="";
		
		flag=this.companyIntroductService.updateCompanyIntrContent(CpyContent);
		if(!flag)
		{
			xgResult="条约修改成功";	
		}
		else
		{
			xgResult="条约修改失败,请重试";
		}
		
		String companyContent=this.companyIntroductService.getCompanyIntrContent();
		request.setAttribute("companyContent", companyContent);
		
		request.setAttribute("xgResult", xgResult);
		
		
		
		return mapping.findForward("companyIntrSuccess");
	}
	
	
	
	public ICompanyIntroductService getCompanyIntroductService() {
		return companyIntroductService;
	}

	public void setCompanyIntroductService(
			ICompanyIntroductService companyIntroductService) {
		this.companyIntroductService = companyIntroductService;
	}
}