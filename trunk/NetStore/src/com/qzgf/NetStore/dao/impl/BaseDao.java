package com.qzgf.NetStore.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.qzgf.NetStore.dao.IBaseDao;
import com.qzgf.NetStore.persistence.Menu;

public class BaseDao implements IBaseDao {
	private Logger logger = Logger.getLogger(this.getClass());

	private SessionFactory sessionFactory;

	/*************************begin 删除操作*************************************************/		
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#delete(java.lang.Object)  
	 * 删除实例
	 */
	public void delete(Object entity) {
		logger.info("delete(Object) entity.class="
				+ entity.getClass().getName());
		
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.getTransaction();
			tr.begin();
			session.delete(entity);
			tr.commit();
			commitflag = true;
			session.flush();
			session.clear();
		} catch (Exception e) {
			if (tr != null)
				tr.rollback();
		} finally {
			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.closeSession(session);
		}
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#delete(java.lang.Class, long) 
	 * 根据id得到对象删除 
	 */
	public void delete(Class clazz, long id) {
		// TODO Auto-generated method stub   
		logger.info("ClassName=" + clazz.getName() + "  ,id=" + id);
		try {
			Object entity = this.getByPk(clazz, id);
			if (entity != null)
				this.delete(entity);
			else
				logger.info(clazz.getName() + " 的关键字为 " + id + "  的对象不存在 ");
		} catch (Exception e) {
			logger.info("  delete(Class, long)  excute is error  . Error="
					+ e.toString());
		}

	}
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#delete(java.lang.Class, long) 
	 * 根据id得到对象删除 
	 */
	public void delete(Class clazz, String id) {
		// TODO Auto-generated method stub   
		logger.info("ClassName=" + clazz.getName() + "  ,id=" + id);
		try {
			Object entity = this.getByPk(clazz, id);
			if (entity != null)
				this.delete(entity);
			else
				logger.info(clazz.getName() + " 的关键字为 " + id + "  的对象不存在 ");
		} catch (Exception e) {
			logger.info("  delete(Class, long)  excute is error  . Error="
					+ e.toString());
		}

	}
	
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#delete(java.lang.Class, long) 
	 * 根据id得到对象删除 
	 */
	public void delete(Class clazz, Serializable kid) {
		// TODO Auto-generated method stub   
		logger.info("ClassName=" + clazz.getName() + "  ,id=");
		try {
			Object entity = this.loadByPk(clazz, kid);
			if (entity != null)
				this.delete(entity);
			else
				logger.info(clazz.getName() + " 的关键字为 " + "  的对象不存在 ");
		} catch (Exception e) {
			logger.info("  delete(Class, long)  excute is error  . Error="
					+ e.toString());
		}

	}
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#deleteAll(java.lang.Class) 
	 * 删除全部数据,非主键的所有数组
	 */
	public void deleteAll(Class clazz,String notkeyname,Serializable notkeyvalue) {
		logger.info("deleteAll(Class) ClassName=" + clazz.getName());
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			Query query = session.createQuery(" delete   from "+ clazz.getName()+" where "+ notkeyname+"='"+notkeyvalue+"'");
			query.executeUpdate();
			tr.commit();
			commitflag = true;
			session.flush();
		} catch (Exception e) {
			logger.info("  deleteAll(Class clazz)  excute is error  . Error="
					+ e.toString());
		} finally {
			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.closeSession(session);
		}
	}
	
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#deleteAll(java.lang.Class) 
	 * 删除全部数据 
	 */
	public void deleteAll(Class clazz) {
		logger.info("deleteAll(Class) ClassName=" + clazz.getName());
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			Query query = session.createQuery(" delete   from "
					+ clazz.getName());
			query.executeUpdate();
			tr.commit();
			commitflag = true;
			session.flush();
		} catch (Exception e) {
			logger.info("  deleteAll(Class clazz)  excute is error  . Error="
					+ e.toString());
		} finally {
			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.closeSession(session);
		}
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#deleteAll(java.util.Collection) 
	 * 连续删除多张表
	 */
	public void deleteAll(Collection entities) {
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			Iterator ite = entities.iterator();
			while (ite.hasNext())
				session.delete(ite.next());
			tr.commit();
			commitflag = true;
			session.flush();
		} catch (Exception e) {
			logger.info("  deleteAll(Collection entities)  excute is error  . Error="
					+ e.toString());
		} finally {

			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.closeSession(session);

		}

	}
	
	
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#batchDelete(java.lang.Class, long[])  
	 * 批量删除
	 */
	public void batchDelete(Class clazz, long[] id) {
		String strId = "";
		for (int i = 0; i < id.length; i++) {
			if (i > 0)
				strId += ", " + id[i];
			else
				strId = "" + id[i];
		}
		logger.info("batchDelete(Class, long[])  id[]={" + strId + "}");
		for (int i = 0; i < id.length; i++) {
			this.delete(clazz, id[i]);
		}
	}

	//
    /******************************end 删除操作***********************************************/	

    /******************************begin 对象处理***********************************************/		
	/*
	 * (non-Javadoc)
	 * @see zhenjw.hibernate.dao.IBaseDao#create(java.lang.Object)
	 * 创建实例
	 */
	public void create(Object entity) {
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			session.save(entity);
			tr.commit();
			commitflag = true;
			session.flush();
			session.clear();
			logger.info("保存" + entity.getClass().getName() + " 的实例到数据库成功！");
		} catch (Exception e) {
			logger.info("保存" + entity.getClass().getName() + " 的实例到数据库错误", e);
			e.printStackTrace();
		} finally {
			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e1) {
					logger.info("事务", e1);
				}
			}

			this.closeSession(session);

		}

	}

	
	/* 功能:更新实体
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#update(java.lang.Object)  
	 */
	public void update(Object entity) {
		logger.info("update(Object) entity.class="
				+ entity.getClass().getName());
		Session session = null;
		Transaction tr = null;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			session.update(entity);
			tr.commit();
			session.flush();
			session.clear();
		} catch (Exception e) {
			if (tr != null)
				tr.rollback();
			logger.info("update(Object entity) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}

	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getByPk(java.lang.Class, int)  
	 * 根据id得到对象int
	 */
	public Object getByPk(Class clazz, int id) {
		logger.info("getByPk(Class, Integer) class=" + clazz.getName()
				+ " , ID=" + id);
		Object result = null;
		Session session = null;
		try {
			session = this.getSession();
			result = session.get(clazz, new Integer(id));
		} catch (Exception e) {
			logger.info("getByPk(Class clazz, int id) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getByPk(java.lang.Class, long)  
	 * 根据id得到对象long
	 */
	public Object getByPk(Class clazz, long id) {
		logger.info("getByPk(Class, Long) Class=" + clazz.getName() + ",id="
				+ id);
		Object result = null;
		Session session = null;
		try {
			session = this.getSession();
			result = session.get(clazz, new Long(id));
		} catch (Exception e) {
			logger.info("getByPk(Class clazz, long id) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}

		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getByPk(java.lang.Class, java.lang.String)  
	 *  根据id得到对象String
	 */
	public Object getByPk(Class clazz, String id) {
		logger.info("getByPk(Class, String) Class=" + clazz.getName() + ",id="
				+ id);
		Object result = null;
		Session session = null;
		try {
			session = this.getSession();
			result = session.get(clazz, id);
		} catch (Exception e) {
			logger.info("getByPk(Class clazz, String id) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getByPk(java.lang.Class, java.lang.String)  
	 *  根据id得到对象String
	 */
//	public Object getByPk(Class clazz, Object id) {
//		logger.info("getByPk(Class, String) Class=" + clazz.getName() + ",id="
//				+ id);
//		Object result = null;
//		Session session = null;
//		try {
//			session = this.getSession();
//			result = session.load(clazz, id);
//		} catch (Exception e) {
//			logger.info("getByPk(Class clazz, String id) excute is error  . Error="
//					+ e.toString());
//		} finally {
//			this.closeSession(session);
//		}
//		return result;
//	}
	
	/* 功能：得到所有对象
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#loadAll(java.lang.String)  
	 */
	public List loadAll(String strhql) {
		return this.find(strhql);
	}

	/* 功能：根据主键得到对象
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#loadByPk(java.lang.Class, java.lang.String, java.lang.Object)  
	 */
	public Object loadByPk(Class clazz, String keyName, Object keyValue) { 
		Object result = null;
		String query = "from " + clazz.getName() + "  where " + keyName + "=? ";
		logger.info("loadByPk(Class, String, Object) queryString=" + query
				+ "  ,keyValue=" + keyValue);
		Session session = null;
		try {
			session = this.getSession();
			result = session.createCriteria(clazz).add(
					Restrictions.eq(keyName, keyValue)).list();
		} catch (Exception e) {
			logger.info("loadByPk(Class clazz, String keyName, Object keyValue) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}
	
	public Object loadByPk(Class clazz, Serializable kid) { 
		Object result = null;
		Session session = null;
		try {
			session = this.getSession();
			result = session.load(clazz,kid);
		} catch (Exception e) {
			logger.info("loadByPk(Class clazz, String keyName, Object keyValue) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

    /******************************end 对象处理***********************************************/		

	/***********************begin 执行更新sql语句***********************************/
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#excuteSql(java.lang.String) 
	 * 执行sql更新Update语句
	 */
	public void excuteSql(String strsql) {
		// TODO Auto-generated method stub   
		logger.info("excuteSql(String) strsql=" + strsql);
		Session session = null;
		Transaction tr = null;
		boolean commitflag = false;
		try {
			session = this.getSession();
			tr = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(strsql);
			query.executeUpdate();
			tr.commit();
			commitflag = true;
			session.flush();
		} catch (Exception e) {
			logger.info(" excuteSql(String strsql) excute is error  . Error="
					+ e.toString());
		} finally {
			if (!commitflag) {
				try {
					if (tr != null)
						tr.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.closeSession(session);
		}

	}

	/***********************end 执行更新sql语句***********************************/
	
	/***********************begin 执行查询语句(hsql,sql)***********************************/
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#find(java.lang.String) 
	 * 通过hql执行查询 
	 */
	@SuppressWarnings("unchecked")
	public List find(String strhql) {
		logger.info(" find(String) queryString=" + strhql);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			result = query.list();
		} catch (Exception e) {
			logger.info(" find(String strhql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#find(java.lang.String, java.lang.Object) 
	 * 带一个参数的查询 
	 */
	public List find(String strhql, Object param) {
		// TODO Auto-generated method stub   
		logger.info("find(String, Object) queryString=" + strhql + " ,param="
				+ param);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			query.setParameter(0, param);
			result = query.list();
		} catch (Exception e) {
			logger.info("  find(String strhql, Object param) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}
	
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#find(java.lang.String, java.lang.Object) 
	 * 带多个参数的查询 
	 * 其中 select * from table,在定hql中应该是table对应的hibernate中的类名称
	 */
	public List find(String strhql, Object[] param) {
		// TODO Auto-generated method stub   
		logger.info("find(String, Object) queryString=" + strhql + " ,param="
				+ param);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			for(int i=0;i<param.length;i++){
			    query.setParameter(i, param[i]);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("  find(String strhql, Object param) excute is error  . Error="
					+ e.toString());
			e.printStackTrace();
		} finally {
			this.closeSession(session);
		}
		return result;
	}
		
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findByNamedParam(java.lang.String, java.lang.String, java.lang.Object)
	 * 带一个参数名称的查询  
	 */
	public List find(String strhql, String name, Object param) {
		logger.info("find(String, String, Object) strhql="
				+ strhql + "name=" + name + " ,param=" + param);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			query.setParameter(name, param);
			result = query.list();
		} catch (Exception e) {
			logger.info(" findByNamedParam(String strhql, String name, Object param) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findBySql(java.lang.String)  
	 *  查询结果放入List中
	 */
	public List findBySql(String strsql) {
		logger.info("exceuteSQL(String) strsql=" + strsql);
		Session session = null;
		List result = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			result = query.list();
		} catch (Exception e) {
			logger.info(" findBySql(String strsql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findBySql(java.lang.String, java.util.List)  
	 * 多参数查询
	 */
	public List findBySql(String strsql, Object[] param) {
		String paramnameArray = "";
		/*
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				if (i > 0)
					paramnameArray += " , " + param.;
				else
					paramnameArray = "" + param.get(i);
			}
		}
		*/
		
		logger.info("excuteSql(String, List) strsql=" + strsql + " , List="
				+ paramnameArray);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			for(int i=0;i<param.length;i++){
			    query.setParameter(i, param[i]);
			}
			result = query.addEntity(Menu.class).list() ;
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info("findBySql(String strsql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}
	
	
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findBySql(java.lang.String, java.util.List)  
	 * 多参数查询
	 */
	public List findBySql(String strsql, Object[] param,Class cls) {
		String paramnameArray = "";
		/*
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				if (i > 0)
					paramnameArray += " , " + param.;
				else
					paramnameArray = "" + param.get(i);
			}
		}
		*/
		logger.info("excuteSql(String, List) strsql=" + strsql + " , List="
				+ paramnameArray);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			for(int i=0;i<param.length;i++){
			    query.setParameter(i, param[i]);
			}
			result = query.addEntity(cls).list();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info("findBySql(String strsql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findBySql(java.lang.String, java.util.List)  
	 * 多参数查询
	 */
	public List findBySql(String strsql, List params) {
		String paramnameArray = "";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (i > 0)
					paramnameArray += " , " + params.get(i);
				else
					paramnameArray = "" + params.get(i);
			}
		}
		logger.info("excuteSql(String, List) strsql=" + strsql + " , List="
				+ paramnameArray);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			result = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("findBySql(String strsql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#findBySql(java.lang.String, java.util.List)  
	 * 多参数查询
	 */
	public List findBySql(String strsql, List paramsnames, List paramvalues) {
		String paramArray = "", paramArraySql = "";
		List result = null;
		Session session = null;
		if (paramsnames.size() != paramvalues.size())
			logger.info("findBySql(String strsql, List paramsnames, List paramvalues) error");
		else {
			if (paramvalues != null) {
				for (int i = 0; i < paramvalues.size(); i++) {
					if (i > 0) {
						paramArray += " , " + paramsnames.get(i) + ":"
								+ paramvalues.get(i);
						paramArraySql = " and " + paramsnames.get(i) + "=?";
					} else {
						paramArray = "" + paramsnames.get(i) + ":"
								+ paramvalues.get(i);
						paramArraySql = strsql + "where " + paramsnames.get(i)
								+ "=?";
					}
				}
			}
			logger.info("excuteSql(String, List) strsql=" + strsql
					+ " , List=" + paramArray);
			try {
				session = this.getSession();
				SQLQuery query = session.createSQLQuery(paramArraySql);
				if (paramvalues != null) {
					for (int i = 0; i < paramvalues.size(); i++) {
						query.setParameter(i, paramvalues.get(i));
					}
				}
				result = query.list();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("findBySql(String strsql, List paramsnames, List paramvalues) excute is error  . Error="
						+ e.toString());
				} finally {
				this.closeSession(session);
			}
		}
		return result;
	}

	/***********************end 执行查询语句(hsql,sql)***********************************/

	/***********************begin 总数(hsql,sql)***********************************/
	/* (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getTotalCount(java.lang.String)  
	 * Hsql得到总数
	 * select count(*) from table;结果集为 list.get(0)为总数值 
	 */
	public int getTotalCount(String strhql) {
		logger.info("getTotalCount() strhql=" + strhql);
		int result = 0;
		Session session = null;
		try {
			String strsql = this.getQueryTotalCountString(strhql);
			session = this.getSession();
			Query query = session.createQuery(strsql);
			List list = query.list();
			result = this.getNum(list);
		} catch (Exception e) {
			logger.info("getTotalCount(String strhql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能：得到总数
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getTotalCount(java.lang.String, java.lang.Object)  
	 * Customer customer=new Customer();
	 * customer.setName(“pansl”);
	 * customer.setAge(80);
	 * Query query=session.createQuery(“from Customer c where c.name=:name and c.age=:age ”);
	 * query.setProperties(customer);
	 * setProperties()方法会自动将customer对象实例的属性值匹配到命名参数上，但是要求命名参数名称必须要与实体对象相应的属性同名。
	 */
	public int getTotalCount(String strhql, Object obj) {
		logger.info("getTotalCount(String,Object)  strhql=" + strhql + ""
				+ obj.getClass().getName());
		int result = 0;
		Session session = null;
		try {
			String strsql = this.getQueryTotalCountString(strhql);
			logger.info("strsql=" + strsql);
			session = this.getSession();
			Query query = session.createQuery(strsql);
			List list = query.setProperties(obj).list();
			result = this.getNum(list);
		} catch (Exception e) {
			logger.info("getTotalCount(String strhql, Object obj) excute is error  . Error="
					+ e.toString());
			e.printStackTrace();
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能：hsql得到总数
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getTotalCount(java.lang.String, java.util.List)  
	 */
	public int getTotalCount(String strhql, List params) {
		String paramnameArray = "";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (i > 0)
					paramnameArray += " , " + params.get(i);
				else
					paramnameArray = "" + params.get(i);
			}
		}
		logger.info("getTotalCount(String, List) strhql=" + strhql
				+ " , List=" + paramnameArray);
		int result = 0;
		Session session = null;
		try {
			String strquery = this.getQueryTotalCountString(strhql);
			session = this.getSession();
			logger.info("strquery==" + strquery);
			Query query = session.createQuery(strquery);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			List list = query.list();
			result = this.getNum(list);
		} catch (Exception e) {
			logger.info("getTotalCount(String strhql, List params) excute is error  . Error="
					+ e.toString());
			e.printStackTrace();
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能：计算出总数
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getTotalCountBySql(java.lang.String)  
	 */
	public int getTotalCountBySql(String strsql) {
		logger.info("getTotalCountBySql(String) strsql=" + strsql);
		int result = 0;
		Session session = null;
		try {
			strsql = this.getQueryTotalCountString(strsql);
			session = this.getSession();
			logger.info("strsql==" + strsql);
			List list = session.createSQLQuery(strsql).list();
			result = this.getNum(list);
		} catch (Exception e) {
			logger.info("getTotalCountBySql(String strsql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能：得到总数
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getTotalCountBySql(java.lang.String, java.util.List)  
	 */
	public int getTotalCountBySql(String strsql, List params) { 
		String paramnameArray = "";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (i > 0)
					paramnameArray += " , " + params.get(i);
				else
					paramnameArray = "" + params.get(i);
			}
		}
		logger.info("getTotalCountBySql(String, List) strsql=" + strsql
				+ " , List=" + paramnameArray);
		int result = 0;
		Session session = null;
		try {
			strsql = this.getQueryTotalCountString(strsql);
			logger.info("strsql==" + strsql);
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			List list = query.list();
			result = this.getNum(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getTotalCountBySql(String strsql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}
	
	/**  
	 * 功能：得到查询记录总数的语句（包含sql与hql）  
	 * @param queryString  
	 * @return  
	 */
	private String getQueryTotalCountString(String queryString) {
		int form_index = queryString.indexOf("from ");
		int orderby_index = queryString.indexOf(" order by ");
		String strsql = "";
		if (form_index < 0) {
			strsql = "";
		} else {
			strsql = " select count(*) ";
			if (orderby_index > -1) {
				strsql = strsql
						+ queryString.substring(form_index, orderby_index);
			} else {
				strsql = strsql + queryString.substring(form_index);
			}
		}
		return strsql;
	}

	/**  
	 * 功能：得到记录数的方法  
	 * @param list  
	 * @return  
	 */
	protected int getNum(List list) {
		int result = 0;
		if (list != null || list.size() > 0)
			result = Integer.parseInt(list.get(0).toString());
		return result;
	}

	/***********************end 总数(hsql,sql)***********************************/
	
	/***********************begin 分页处理***********************************/

	/* 功能:hsql分页查询
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#query(int, int, java.lang.String)  
	 */
	public List query(int pageNo, int pageSize, String strhql) {
		logger.info("query(int, int, String) pageNo=" + pageNo + ",pageSize="
				+ pageSize + " ,strhql=" + strhql);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			if (pageNo > 0 && pageSize > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("query(int pageNo, int pageSize, String strhql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能：hsql带参数的分页查询
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#query(int, int, java.lang.String, java.lang.Object)  
	 */
	public List query(int pageNo, int pageSize, String strhql, Object obj) {
		logger.info("query(int, int, String, Object) pageNo=" + pageNo
				+ ",pageSize=" + pageSize + "strhql=" + strhql + "  ,obj"
				+ obj.getClass().getName());
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			query.setProperties(obj);
			if (pageNo > 0 && pageSize > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("query(int pageNo, int pageSize, String strhql, Object obj) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/* 功能:Hsql分页多参数查询
	 *  (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#query(int, int, java.lang.String, java.util.List)  
	 */
	public List query(int pageNo, int pageSize, String strhql, List params) {
		logger.info("query(int, int, String, Object) pageNo=" + pageNo
				+ ",pageSize=" + pageSize + ",strhql=" + strhql);
		String paramnameArray = "";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (i > 0)
					paramnameArray += " , " + params.get(i);
				else
					paramnameArray = "" + params.get(i);
			}
		}
		logger.info("params=" + params);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(strhql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			if (pageNo > 0 && pageSize > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("query(int pageNo, int pageSize, String strhql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;

	}

	/* 功能:分页查询
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#queryBySql(int, int, java.lang.String) 
	 *  
	 */
	public List queryBySql(int pageNo, int pageSize, String strsql) {
		logger.info("query(int, int, String) pageNo=" + pageNo + ",pageSize="
				+ pageSize + " ,strsql=" + strsql);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			if (pageNo > 0 && pageSize > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("queryBySql(int pageNo, int pageSize, String strsql) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;

	}

	/* 功能：分页多值查询处理
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#queryBySql(int, int, java.lang.String, java.util.List)  
	 */
	public List queryBySql(int pageNo, int pageSize, String strsql, List params) {
		logger.info("query(int, int, String, Object) pageNo=" + pageNo
				+ ",pageSize=" + pageSize + " , strsql=" + strsql);
		String paramnameArray = "";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (i > 0)
					paramnameArray += " , " + params.get(i);
				else
					paramnameArray = "" + params.get(i);
			}
		}
		logger.info("params=" + params);
		List result = null;
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(strsql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			if (pageNo > 0 && pageSize > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		} catch (Exception e) {
			logger.info("queryBySql(int pageNo, int pageSize, String strsql, List params) excute is error  . Error="
					+ e.toString());
		} finally {
			this.closeSession(session);
		}
		return result;
	}

	/***********************end 分页处理***********************************/
	

	/***********************begin seesion处理***********************************/
	/* 功能:打开一个session
	 * (non-Javadoc)  
	 * @see zhenjw.hibernate.dao.IBaseDao#getSession()  
	 */
	public Session getSession() {
		return this.sessionFactory.openSession();
	}
	/**  
	 * 功能：关闭session  
	 * @param session  
	 */
	protected void closeSession(Session session) {
		if (session != null && session.isOpen())
			session.close();
		session = null;
	}
	/*
	 * 功能:sessionFactory的设置与读取
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/***********************end seesion处理***********************************/
}
