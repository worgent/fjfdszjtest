/**
 * 
 */
package com.apricot.eating.engine;

import java.sql.Connection;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
/**
 * @author Administrator
 */
public class DyncUserTransaction implements UserTransaction {
	private Connection conn;

	public DyncUserTransaction(Connection conn) {
		super();
		this.conn = conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#begin()
	 */
	public void begin() throws NotSupportedException, SystemException {
		try {
			// TODO Auto-generated method stub
			this.conn.setAutoCommit(false);
		} catch (Exception e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#commit()
	 */
	public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException,
			IllegalStateException, SystemException {
		try {
			this.conn.commit();
		} catch (Exception e) {
		}
		// TODO Auto-generated method stub
		try {
			this.conn.setAutoCommit(true);
		} catch (Exception e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#getStatus()
	 */
	public int getStatus() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#rollback()
	 */
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		// TODO Auto-generated method stub
		try {
			this.conn.rollback();
		} catch (Exception e) {
		}
		// TODO Auto-generated method stub
		try {
			this.conn.setAutoCommit(true);
		} catch (Exception e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#setRollbackOnly()
	 */
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.transaction.UserTransaction#setTransactionTimeout(int)
	 */
	public void setTransactionTimeout(int arg0) throws SystemException {
		// TODO Auto-generated method stub
	}
}
