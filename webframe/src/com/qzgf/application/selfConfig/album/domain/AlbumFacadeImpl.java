package com.qzgf.application.selfConfig.album.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * 地图日志持久层实现类
 * @author lsr
 *
 */
public class AlbumFacadeImpl implements AlbumFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(AlbumFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	
	@SuppressWarnings("unchecked")
	public PageList findClientAlbums(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Album.findAlbumCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Album.findAlbumPageByUserId", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 根据相册编号查找该相册的相关信息
	 */
	@SuppressWarnings("unchecked")
	public List findClientAlbumByAlbumId(HashMap map) {
		List testList = baseSqlMapDAO.queryForList("Album.findAlbumByAlbumId", map);
		
		return testList;
	}
	
	/**
	 * 根据相册编号,查询该相册下面所有的相片
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findClientPhotoByAlbumId(HashMap map,Pages pages) {
		//加分页控制
		PageList pl = new PageList();
		
		//列表内容
		List photoList = baseSqlMapDAO.queryForList("Album.findPhotosByAlbumId", map);
		pl.setObjectList(photoList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 根据照片编号,查询该照片的相关评论信息
	 * @param map
	 * @param pages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList findPhotoWordByPhotoId(HashMap map,Pages pages) {
		//加分页控制
		PageList pl = new PageList();
		
		//列表内容
		List photoList = baseSqlMapDAO.queryForList("Album.findAlbumWordByPhotoId", map);
		pl.setObjectList(photoList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}
	
	/**
	 * 插入新照片
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertPhoto(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("Album.insertPhoto", map);
		return st;
	}
	
	/**
	 * 插入照片评论
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertPhotoWord(HashMap map)throws Exception{
		int st=0;
		st = baseSqlMapDAO.update("Album.insertPhotoWord", map);
		return st;
	}
	
	/**
	 * 修改album信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateAlbumById(HashMap map) {
		int num = baseSqlMapDAO.update("Album.updateAlbumByAlbumId", map);
		if (num == 1) {
			// 修改成功
			return true;
		}else{
			// 修改失败
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int insertAlbum(HashMap map){
		int st=0;
		st = baseSqlMapDAO.update("Album.insertAlbum", map);
		return st;
	}
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
