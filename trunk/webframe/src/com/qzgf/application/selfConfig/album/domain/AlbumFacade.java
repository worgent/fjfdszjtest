package com.qzgf.application.selfConfig.album.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
/**
 * 地图日志持久层接口
 * @author lsr
 *
 */
public interface AlbumFacade {

	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);


	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract PageList findClientAlbums(HashMap map,Pages pages);

	/**
	 * 根据相册编号,查询该相册下面所有的相片
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findClientPhotoByAlbumId(HashMap map,Pages pages);
	
	/**
	 * 插入新照片
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertPhoto(HashMap map)throws Exception;
	
	/**
	 * 插入照片评论
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertPhotoWord(HashMap map)throws Exception;

	/**
	 * 根据照片编号,查询该照片的相关评论信息
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findPhotoWordByPhotoId(HashMap map,Pages pages);
	
	/**
	 * 根据相册编号查找该相册的相关信息
	 */
	@SuppressWarnings("unchecked")
	public List findClientAlbumByAlbumId(HashMap map);
	
	/**
	 * 修改album信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateAlbumById(HashMap map);
	
	@SuppressWarnings("unchecked")
	public int insertAlbum(HashMap map);
		
}