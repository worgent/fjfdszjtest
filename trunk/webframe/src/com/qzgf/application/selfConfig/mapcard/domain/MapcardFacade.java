package com.qzgf.application.selfConfig.mapcard.domain;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;


public interface MapcardFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);

	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertMapcard(HashMap map);

	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteMapcardById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateMapcardById(HashMap map);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findMapcard(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findMapcardPage(HashMap map,Pages pages);
	
	
	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertSpecialintro(HashMap map);

	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int deleteSpecialintroById(HashMap map);

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateSpecialintroById(HashMap map);
	
	
	/**
	 * 改:2009-11-03:更新地图名片和商品信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int updateMapcardSpecialintroById(HashMap map,HashMap producths);

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findSpecialintro(HashMap map);
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findSpecialintroPage(HashMap map,Pages pages);
	
	
	/**
	 * 2009-11-15
	 * 查询前6条记录,通过客户确定
	 * @param map
	 * @return
	 */
	public List findSpecialintroex(HashMap map);
	/**
	 * 得到前台的菜单
	 * @param map
	 * @return
	 */
	public  abstract List getmenu(HashMap map);
	
	
	/**
	 * 增(2009-11-03:地图名片和商品信息都增加)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int insertMapcardSpecial(HashMap map,HashMap product);

	
	
	/**
	 * 商家类别的增,删除,改,查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertmenu(HashMap map);
	
	@SuppressWarnings("unchecked")
	public int deletemenu(HashMap map);
	
	@SuppressWarnings("unchecked")
	public int updatemenu(HashMap map);
	@SuppressWarnings("unchecked")
	public List findmenu(HashMap map);
	
	
	/**
	 * 查2009-11-04:查看一定范围内的商品500m
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List findSpecialintro500m(HashMap map);
	
	/**
	 * 保存用户上传的图片
	 * @param file:struct组件
	 * @param filename:组件对应文件名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract String saveimg(File file,String filename);
}