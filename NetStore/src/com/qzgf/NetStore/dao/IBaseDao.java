package com.qzgf.NetStore.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

public interface IBaseDao {

	/*************************begin 删除操作*************************************************/		
	  
    /**  
     * 功能：删除记录  
     *   
     * @param entity  
     */  
    public void delete(Object entity);   
  
    /**  
     * 功能：删除数据  
     *   
     * @param clazz  
     * @param id  
     */  
    @SuppressWarnings("unchecked")
	public void delete(Class clazz, long id);   
    
	public void delete(Class clazz, String id);
  
    /**  
     * 功能：批量删除数据  
     *   
     * @param clazz  
     * @param id  
     */  
    @SuppressWarnings("unchecked")
	public void batchDelete(Class clazz, long[] id);   
  
    /**  
     * 功能：删除表中的所有的记录  
     *   
     * @param clazz  
     */  
    @SuppressWarnings("unchecked")
	public void deleteAll(Class clazz);   
  
    /**  
     * 功能：删除记录集中的所有的记录  
     *   
     * @param entities  
     */  
    @SuppressWarnings("unchecked")
	public void deleteAll(Collection entities);  
    
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#deleteAll(java.lang.Class) 
	 * 删除全部数据,非主键的所有数组
	 */
	public void deleteAll(Class clazz,String notkeyname,Serializable notkeyvalue);
  
	/*************************end 删除操作*************************************************/		
	
    /******************************begin 对象处理***********************************************/		
    
    /**  
     * 功能：增加记录  
     *   
     * @param entity  
     */  
    public void create(Object entity);   
  
    /**  
     * 功能：修改记录  
     *   
     * @param entity  
     */  
    public void update(Object entity);   

    /**  
     * 功能：通过主键查询记录  
     *   
     * @param clazz  
     * @param id  
     * @return Object  
     */  
  
    @SuppressWarnings("unchecked")
	public Object getByPk(Class clazz, int id);   
  
    /**  
     * 功能：通过主键查询记录  
     *   
     * @param clazz  
     * @param id  
     * @return Object  
     */  
    public Object getByPk(Class clazz, long id);   
  
    /**  
     * 功能：通过主键查询记录  
     *   
     * @param clazz  
     * @param id  
     * @return Object  
     */  
    public Object getByPk(Class clazz, String id);   
  
    /**  
     * 功能：通过关键字和值来进行查询  
     *   
     * @param clazz  
     * @param keyName  
     * @param keyValue  
     * @return 得到的Object是List  
     */  
    public Object loadByPk(Class clazz, String keyName, Object keyValue);   
    /******************************end 对象处理***********************************************/		
     
	/***********************begin 执行更新sql语句***********************************/
    /**  
     * 功能：执行SQL语句，主要是更新与删除记录的SQL语句，不需要返回值的  
     *   
     * @param strsql  
     */  
    public void excuteSql(String strsql);  
    
	/***********************end 执行更新sql语句***********************************/
    
	/***********************begin 执行查询语句(hsql,sql)***********************************/
    /**  
     * 功能：根据hql查询记录  
     *   
     * @param strhql
     * @return List  
     */    
    public List find(String strhql);
    /**  
     * 功能：根据hql查询记录  
     *   
     * @param strhql  
     * @param param  
     * @return List  
     */  
    public List find(String strhql, Object param); 
    
    
    /**  
     * 功能：根据hql查询记录  
     *   
     * @param strhql  
     * @param param  
     * @return List  
     */  
    public List find(String strhql, Object[] param);    
  
    /**  
     * 功能：根据hql查询记录  
     *    
     * @param strhql  
     * @param name  
     * @param param  
     * @return List  
     */  
    public List find(String strhql, String name, Object param);   
  
       
    /**  
     * 功能：SQL查询  
     * @param strsql  
     * @return  
     */  
    public List findBySql(String strsql);   
       
    /**  
     * 功能：查询符合条件的记录。  
     * @param strsql  
     * @param params  
     * @return  
     */  
    public List findBySql(String strsql,List params);     

	public List findBySql(String strsql, Object[] param);
	
	public List findBySql(String strsql, Object[] param,Class cls);
	
    /**  
     * 功能：查询符合多条件的记录。  
     * @param strsql  
     * @param params  
     * @return  
     */    
    public List findBySql(String strsql, List paramsnames, List paramvalues);
    
	/***********************end 执行查询语句(hsql,sql)***********************************/

	/***********************begin 总数(hsql,sql)***********************************/
    /**  
     * 功能：根据hql语句得到记录总数  
     *   
     * @param strhql  
     * @return int  
     */  
    public int getTotalCount(String strhql);   
    
    
    /**  
     * 功能：根据hql语句得到记录总数  
     *   
     * @param strhql  
     * @param obj  
     * @return int  
     */  
    public int getTotalCount(String strhql, Object obj);   
       
        
    /**  
     * 功能：根据hql语句得到记录总数  
     * @param strhql  
     * @param params  
     * @return int  
     */  
    @SuppressWarnings("unchecked")
	public int getTotalCount(String strhql, List params);   
  
    /**  
     * 功能：根据sql语句得到记录总数  
     *   
     * @param strsql  
     * @return int  
     */  
    public int getTotalCountBySql(String strsql);   
 
       
    /**  
     * 功能：根据sql语句得到记录总数  
     * @param strsql  
     * @param param  
     * @return  
     */  
    @SuppressWarnings("unchecked")
	public int getTotalCountBySql(String strsql, List params);   
       
	/***********************end 总数(hsql,sql)***********************************/
	
	/***********************begin 分页处理***********************************/  
      
    /**  
     * 功能：分页查询  
     *   
     * @param pageNo  
     * @param pageSize  
     * @param strhql  
     * @return List  
     */  
    public List query(int pageNo, int pageSize, String strhql);   
  
    /**  
     * 功能：分页查询  
     *   
     * @param pageNo  
     * @param pageSize  
     * @param queryString  
     * @param obj  
     * @return List  
     */  
    public List query(int pageNo, int pageSize, String strhql, Object obj);   
       
       
    /**  
     * 功能：分页查询  
     * @param pageNo  
     * @param pageSize  
     * @param strhql  
     * @param params  
     * @return  
     */  
    public List query(int pageNo, int pageSize, String strhql, List params );   
  
    
       
    /**  
     * 功能：分页查询  
     *   
     * @param pageNo  
     * @param pageSize  
     * @param strsql  
     * @return List  
     */  
    public List queryBySql(int pageNo, int pageSize, String strsql);    
  
    /**  
     * 功能：分页查询  
     * @param pageNo  
     * @param pageSize  
     * @param strsql  
     * @param params  
     * @return  
     */  
    public List queryBySql(int pageNo, int pageSize, String strsql, List params) ;   
       
        
	/***********************end 分页处理***********************************/
	

	/***********************begin seesion处理***********************************/

	/**  
     * 功能：获得数据库连接的Session  
     *   
     * @return Session  
     */  
    public Session getSession();   
	/***********************end seesion处理***********************************/
  
}
