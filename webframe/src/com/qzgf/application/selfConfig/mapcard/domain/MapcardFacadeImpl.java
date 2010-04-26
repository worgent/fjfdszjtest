package com.qzgf.application.selfConfig.mapcard.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;
import com.qzgf.utils.comm.Constant;
import com.qzgf.utils.comm.ImgUtil;
import com.qzgf.utils.comm.WebFrameUtil;

/**
 * 框架测试模块
 * @author lsr
 *
 */
public class MapcardFacadeImpl implements MapcardFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MapcardFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertMapcard(HashMap map){
		return baseSqlMapDAO.update("Mapcard.insertMapcard", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteMapcardById(HashMap map) {
		return baseSqlMapDAO.update("Mapcard.deleteMapcardById", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateMapcardById(HashMap map) {
		return baseSqlMapDAO.update("Mapcard.updateMapcardById", map);
	}
	@SuppressWarnings("unchecked")
	public List findMapcard(HashMap map) {
		return baseSqlMapDAO.queryForList("Mapcard.findMapcard", map);
	}
	
	@SuppressWarnings("unchecked")
	public PageList findMapcardPage(HashMap map,Pages pages) {
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Mapcard.findMapcardCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容findSpecialintro  findMapcardPage
		List testList = baseSqlMapDAO.queryForList("Mapcard.findMapcardPage", map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;
	}

	
	/**
	 * 增
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  int insertSpecialintro(HashMap map){
		return baseSqlMapDAO.update("Mapcard.insertSpecialintro", map);		
	}

	/**
	 * 增(2009-11-03:地图名片和商品信息都增加)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  int insertMapcardSpecial(HashMap map,HashMap product){
		String pid = baseSqlMapDAO.sequences("t_selfconfig_mapcard");
		map.put("pid", pid);
		int st=0;
		try{
		//导入主表信息
		st = baseSqlMapDAO.update("Mapcard.insertMapcard", map);
		// 插入子表商品信息表，多条纪录
		if (null != product && product.size() > 0) {
			st=st+this.addSpecialintro(product, Integer.valueOf(product.get("num").toString()).intValue(), pid);
		}
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return st;
	}
	
	/**
	 * 2009-11-03
	 * 批量增加商品信息
	 */
	public int addSpecialintro(HashMap producths, int num, String mappid){
		int st = 0;
		//增加单行记录
		if (num == 1) {
			producths.put("pmerchantid", mappid);
			//保存图片(保存)
			File pspecialicon=(File) producths.get("pspecialicon");
			String pspecialiconFileName=(String) producths.get("pspecialiconFileName");		
			String result=saveimg(pspecialicon,pspecialiconFileName);
			if (result.equals("")) {
				result = producths.get("pspecialiconex").toString();
			}
			producths.put("pspecialicon",result);
			
			st =st+ baseSqlMapDAO.update("Mapcard.insertSpecialintro", producths);// 添加中转站点情况
		} else if (num > 1) {
			String[] pspecialname = (String[]) producths.get("pspecialname");
			//===========================================================
			//图片保存处理 2099-11-15
			//隐藏的用户信息,辅助信息
			Object tmp=producths.get("pspecialiconex");
			//String sst=tmp.getClass().getName();
			String[] pspecialiconexarr={};
			String pspecialiconex="";
			int lenex=0;
			if(tmp instanceof String)
			{
				pspecialiconex=producths.get("pspecialiconex").toString();
				lenex=1;
			}else{
				 pspecialiconexarr=(String[]) producths.get("pspecialiconex");
				 lenex=pspecialiconexarr.length;
			}
			int len=num-lenex;//通个这种方式确定,File的个数
			
			File[] pspecialiconarr={};
			String[] pspecialiconFileNamearr={};
			File pspecialicon=null;
			String pspecialiconFileName="";
			if(len==1){
				pspecialicon=(File)producths.get("pspecialicon");
				pspecialiconFileName=(String) producths.get("pspecialiconFileName");
			}else
			{
				pspecialiconarr=(File[]) producths.get("pspecialicon");
				pspecialiconFileNamearr=(String[]) producths.get("pspecialiconFileName");
			}

			//===========================================================
			String[] pspecialintro = (String[]) producths.get("pspecialintro");
			HashMap map;
			for (int i = 0; i < num; i++) {
				map = new HashMap();
				map.put("pmerchantid", mappid);
				map.put("pspecialname", pspecialname[i]);

				String result="";
				if(lenex>0 &&lenex>i)
				{
					if(lenex==1)
					{
						result =pspecialiconex;
					}else{
						result =pspecialiconexarr[i];
					}
				}else
				{
					if(len==1){
						result=saveimg(pspecialicon,pspecialiconFileName);
					}else
					{
						result=saveimg(pspecialiconarr[i],pspecialiconFileNamearr[i]);
					}
				}	

				map.put("pspecialicon",result);
				//map.put("pspecialicon", pspecialicon[i]);
				map.put("pspecialintro", pspecialintro[i]);
				st = st+baseSqlMapDAO.update("Mapcard.insertSpecialintro", map);// 添加中转站点情况
			}
		}
		return st;
	}
	
	
	/**
	 * 删
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  int deleteSpecialintroById(HashMap map){	
		return baseSqlMapDAO.update("Mapcard.deleteSpecialintroById", map);			
	}

	/**
	 * 改
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  int  updateSpecialintroById(HashMap map){
		return baseSqlMapDAO.update("Mapcard.updateSpecialintro", map);		
	}
	
	/**
	 * 改:2009-11-03:更新地图名片和商品信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  int updateMapcardSpecialintroById(HashMap map,HashMap product){
		String pid=map.get("pid").toString();
		//更新主表信息
		int st = baseSqlMapDAO.update("Mapcard.updateMapcardById", map);
		// 子表商品信息表的更新，多条纪录(先清除原记录,然后批量增加)
		HashMap tmp=new HashMap();
		tmp.put("pmerchantid", pid);
		st=st+deleteSpecialintroById(tmp);
		if (null != product && product.size() > 0) {
			st=st+this.addSpecialintro(product, Integer.valueOf(product.get("num").toString()).intValue(), pid);
		}
		return st;
	}
	

	/**
	 * 查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List findSpecialintro(HashMap map){
		return baseSqlMapDAO.queryForList("Mapcard.findSpecialintro", map);	
	}
	
	/**
	 * 查(分页列表信息)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  PageList findSpecialintroPage(HashMap map,Pages pages){
		//加分布控制
		PageList pl = new PageList();
		//获得总数
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Mapcard.findSpecialintroCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//获得总页数
		pages.executeCount();
		//开始记录
		map.put("START", pages.getSpage());
		//最多显示记录数
		map.put("END", pages.getEpage());
		//列表内容
		List testList = baseSqlMapDAO.queryForList("Mapcard.findSpecialintroPage",map);
		pl.setObjectList(testList);
		//分页信息
		pl.setPages(pages);
		return pl;		
	}

	/**
	 * 2009-11-15
	 * 查询前6条记录,通过客户确定
	 * @param map
	 * @return
	 */
	public List findSpecialintroex(HashMap map) {
		return baseSqlMapDAO.queryForList("Mapcard.findSpecialintroPage", map);
	}
	
	/**
	 * 查2009-11-04:查看一定范围内的商品500m
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSpecialintro500m(HashMap map){
		return baseSqlMapDAO.queryForList("Mapcard.findSpecialintro500m", map);	
	}
	
	/**
	 * 生成菜单(商家类别树型结构)
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List getmenu(HashMap map){
		return baseSqlMapDAO.queryForList("Mapcard.getmenu", map);	
	}
	
	
	/**
	 * 私有方法,处理图片保存 
	 * @param file
	 * @param filename
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveimg(File file,String filename){
		   String result="";
	       try {
				String userId = "1";
				if (file == null || StringUtils.isBlank(filename))
	              {
					
	              }
				else{
					
					// 生成的文件名:用户Id+系统当前时间+"."+当前文件的后缀
					String distFileName = userId + System.currentTimeMillis() + "."
							+ FilenameUtils.getExtension(filename);
		
					String fullPicFile = WebFrameUtil.getUserWebFilePath(userId)
							+ distFileName;
					String fullPicFileSmall = WebFrameUtil.getUserWebFilePath(userId)
							+ distFileName + Constant.IMG_SMALL_FILEPREFIX;
		
					// 保存到本地
					OutputStream bos = new FileOutputStream(fullPicFile);
					IOUtils.copy(new FileInputStream(file), bos);
					
					
					ImgUtil.reduceImg(fullPicFile, fullPicFileSmall, 120, 50, 0);
					//关闭数据流
					bos.close();
					// 保存到数据库
					result=distFileName;
				//search.put("pmerchanticon", distFileName + "_Small");
				}
				
			} catch (Exception e) {
				//log.debug(e.toString());
				System.out.print(e.toString());
			}
			return result;
		
	}
	
	/**
	 * 商家类别的增,删除,改,查
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertmenu(HashMap map){
		return baseSqlMapDAO.update("Mapcard.insertmenu", map);
	}
	
	@SuppressWarnings("unchecked")
	public int deletemenu(HashMap map) {
		return baseSqlMapDAO.update("Mapcard.deletemenu", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updatemenu(HashMap map) {
		return baseSqlMapDAO.update("Mapcard.updatemenu", map);
	}
	@SuppressWarnings("unchecked")
	public List findmenu(HashMap map) {
		return baseSqlMapDAO.queryForList("Mapcard.findmenu", map);
	}

	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
