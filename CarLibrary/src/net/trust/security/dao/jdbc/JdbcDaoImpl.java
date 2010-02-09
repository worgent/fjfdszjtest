package net.trust.security.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;



import org.springframework.context.ApplicationContextException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

import net.trust.security.MethodRoles;
import net.trust.security.PageRoles;

public class JdbcDaoImpl extends JdbcDaoSupport {
	//需要权限判断的页面，下面这个变量用于获取数据的sql
	private String authoritiesByPageIdQuery;
	private String authoritiesByMethodQuery;
	private PageRoleMapping pageRoleMapping;
	private MethodRoleMapping methodRoleMapping;
	public JdbcDaoImpl(){
	}
	public String getAuthoritiesByPageIdQuery() {
		return authoritiesByPageIdQuery;
	}
	public void setAuthoritiesByPageIdQuery(String authoritiesByPageIdQuery) {
		this.authoritiesByPageIdQuery = authoritiesByPageIdQuery;
	}
	//这个方法会自动被spring调用
    protected void initDao() throws ApplicationContextException {
    	setPageRoleMapping(new PageRoleMapping(getDataSource()));
    	setMethodRoleMapping(new MethodRoleMapping(getDataSource()));
    }
    

    
    public void setPageRoleMapping(PageRoleMapping prm){
    	pageRoleMapping = prm;
    }
	public PageRoleMapping getPageRoleMapping(){
		return pageRoleMapping;
	}
	public MethodRoleMapping getMethodRoleMapping() {
		return methodRoleMapping;
	}
	public void setMethodRoleMapping(MethodRoleMapping methodRoleMapping) {
		this.methodRoleMapping = methodRoleMapping;
	}
	public String getAuthoritiesByMethodQuery() {
		return authoritiesByMethodQuery;
	}
	public void setAuthoritiesByMethodQuery(String authoritiesByMethodQuery) {
		this.authoritiesByMethodQuery = authoritiesByMethodQuery;
	}	
	//================
    public List getPageRoleData(){
    	return pageRoleMapping.execute();
    }
    public List getMethodRoleData(){
    	return methodRoleMapping.execute();
    }
	
	//================================================================
    protected class PageRoleMapping extends MappingSqlQuery {
        protected PageRoleMapping(DataSource ds) {
            super(ds, authoritiesByPageIdQuery);
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum)
            throws SQLException {
        	//可以使用rs来读取数据
            return new PageRoles(rs.getString(1),rs.getString(2));
        }
    }
    
    protected class MethodRoleMapping extends MappingSqlQuery {
        protected MethodRoleMapping(DataSource ds) {
            super(ds, authoritiesByMethodQuery);
            compile();
        }

        protected Object mapRow(ResultSet rs, int rownum)
            throws SQLException {
        	//可以使用rs来读取数据
            return new MethodRoles(rs.getString(1),rs.getString(2));
        }
    }



}
