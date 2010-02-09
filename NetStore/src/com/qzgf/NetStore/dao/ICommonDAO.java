package com.qzgf.NetStore.dao;

public interface ICommonDAO {
	/**
	 * 心跳操作,防止长时间无操作造成session失效
	 */
	public void nop();
}
