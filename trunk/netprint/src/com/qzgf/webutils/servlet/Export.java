package com.qzgf.webutils.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qzgf.utils.IbatisUtils;
import com.qzgf.utils.PubFunction;
import com.qzgf.utils.StatementManager;
import com.qzgf.utils.export.ExportInfo;
import com.qzgf.utils.export.ExportUtil;
import com.qzgf.utils.pagination.PaginationImplt;



public class Export extends HttpServlet {

	private Log log = LogFactory.getLog(Export.class);
	private HashMap list = null;
	private static ApplicationContext ctx = null;
	public Export() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		list = null;
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("GBK");
		
		StatementManager statementManager = (StatementManager)ctx.getBean("statementManager");
		
		IbatisUtils iu = new IbatisUtils(statementManager.getSqlMapClient());
		//获取查询参数
		HashMap  para = (HashMap)request.getSession().getAttribute("Qparameter");
		
		//获取ExportFlag
		String exFlag = PubFunction.getNulltoStr(request.getParameter("ExportFlag"));
		if(exFlag == null){
			if(log.isErrorEnabled()){
				log.error("无法获得导出Flag!");
			}
			return ;
		}
		
		ExportInfo exportInfo = (ExportInfo) list.get(exFlag);
		String id=exportInfo.getId();
		int reportType=0;

		if(exportInfo == null){
			if(log.isErrorEnabled()){
				log.error("无法从资源文件里获得导出信息!");
			}
			return;
		}

		String type= request.getParameter("type");
		
		
		//获取分页信息
		PaginationImplt p =(PaginationImplt)request.getSession().getAttribute("Qpagination");
		if(para != null && p != null){
			para.put("START","0");
			if (p.getTotalRecord() > 65000){
				para.put("END", "65000");
			}else{
				para.put("END", ""+p.getTotalRecord());
			}
		}
		
		//获取对应的sql语句
		String sql = iu.getSql(exportInfo.getSqlId(),para) ;
		//求得生成sql所需要的参数
		Object[] objPara = iu.getSqlParam();
		//导出数据
		response.reset(); 
		response.setCharacterEncoding("GBK");
		if ("xml".equals(type)){
			response.setContentType("text/xml; charset=UTF-8");
		}else if ("xls".equals(type)){
			response.setContentType("text/msexcel; charset=UTF-8");
		}
		
		if("xlsex".equals(type))//POI输出
			response.setHeader("Content-disposition", "attachment;filename=" + exportInfo.getFileName() + ".xls" );
		else
			response.setHeader("Content-disposition", "attachment;filename=" + exportInfo.getFileName() + "." + type);
        
        
		ExportUtil eu = new ExportUtil(statementManager);
		
		try{
			if("xlsex".equals(type))//POI输出
				eu.export(response.getOutputStream(),sql,objPara,type,reportType,exportInfo.getFileName(),exportInfo.getFields(),exportInfo.getColumns());
			else
				eu.export(response.getWriter(),sql,objPara,type,reportType,exportInfo.getFileName(),exportInfo.getFields(),exportInfo.getColumns());
		}catch(Exception ex){
			if(log.isErrorEnabled())
				log.error(ex);
		}

	}
	
	public void init(ServletConfig config) throws ServletException {
		String configfile = config.getInitParameter("config");
		if(configfile == null || configfile.trim().length()==0){
			if(log.isDebugEnabled()){
				log.debug("初值化export信息失败!");
			}
			return;
		}
		if(log.isDebugEnabled()){
			log.debug("初值化export文件"+configfile);
		}
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		ExportUtil exportUtil = new ExportUtil(null);
		list=exportUtil.exportConfig(configfile);
	}

}
