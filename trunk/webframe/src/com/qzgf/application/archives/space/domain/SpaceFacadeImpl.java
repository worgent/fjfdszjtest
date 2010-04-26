package com.qzgf.application.archives.space.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.context.PageList;
import com.qzgf.context.Pages;

/**
 * ��ܲ���ģ��
 * @author lsr
 *
 */
public class SpaceFacadeImpl implements SpaceFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SpaceFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	
	@SuppressWarnings("unchecked")
	public int insertSpace(HashMap map){
		int st=0;

		st = baseSqlMapDAO.update("Space.insertSpace", map);
		
		return st;
	}
	
	@SuppressWarnings("unchecked")
	public int deleteSpaceById(HashMap map) {
		int num = baseSqlMapDAO.update("Space.deleteSpaceById", map);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public int updateSpaceById(HashMap map) {
		int num = baseSqlMapDAO.update("Space.updateSpaceById", map);
		if (num == 1) {
			// �޸ĳɹ�
			return 1;
		}
		// �޸�ʧ��
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public PageList findSpace(HashMap map,Pages pages) {
		//�ӷֲ�����
		PageList pl = new PageList();
		//�������
		if (pages.getTotalNum() == -1) {
			int total= ((Integer)baseSqlMapDAO.queryForObject("Space.findSpaceCount", map)).intValue();
			pages.setTotalNum(total);
		}
		//�����ҳ��
		pages.executeCount();
		//��ʼ��¼
		map.put("START", pages.getSpage());
		//�����ʾ��¼��
		map.put("END", pages.getEpage());
		//�б�����
		List testList = baseSqlMapDAO.queryForList("Space.findSpacePage", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
	}

	
	public List isExistSpaceList(HashMap map) {
		return  baseSqlMapDAO.queryForList("Space.isExistSpaceList", map);
		
	}
	
	
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	@SuppressWarnings("unchecked")
	public PageList pageOnlyOneList(HashMap map,Pages pages) {
		PageList pl = new PageList();
		List testList = baseSqlMapDAO.queryForList("Space.pageOnlyOneList", map);
		pl.setObjectList(testList);
		//��ҳ��Ϣ
		pl.setPages(pages);
		return pl;
		
	}
	
	public int deleteSpace(HashMap map) {
		int num=0;
		num = baseSqlMapDAO.update("Space.deleteSpace", map);
		return num;
	}	
	
	
	
	
}
