package net.trust.application.systemManage.flower.domain;


import java.util.HashMap;
import java.util.List;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

public class FlowerFacadeImpl implements FlowerFacade {
	private BaseSqlMapDAO baseSqlMapDAO;
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}
	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	/**
	 * 流程定义信息总数
	 * @param map
	 * @return
	 */
	public int findFlowerDefineCount(HashMap map) {
		return ((Integer) baseSqlMapDAO.queryForObject("FlowerInfo.findFlowerDefineCount", map)).intValue();
	}
	/**
	 * 流程定义信息
	 */
	public List findFlowerDefine(HashMap map) {
		return baseSqlMapDAO.queryForList("FlowerInfo.findFlowerDefine", map);
	}
	/**
	 * 创建派车记录
	 */
	public HashMap insertFlowerDefine(HashMap map, HashMap flowernode) throws Exception {
		//流程定义主键
		String flowerdefineId = baseSqlMapDAO.sequences("flowerdefine_id");
		map.put("flowerdefineId", flowerdefineId);
		// 插入派车信息表
		int st = baseSqlMapDAO.update("FlowerInfo.insertFlowerDefine", map);
		if (st == 0) {
			throw new Exception("添加流程定义失败.");
		}
		// 插入流程定义结点信息，多条纪录
		if (null != flowernode && flowernode.size() > 0) {
			this.addFlowerNode(flowernode, Integer.valueOf(flowernode.get("num").toString()).intValue(), flowerdefineId);
		}
		map.clear();
		map.put("st", st);
		map.put("flowerdefineId", flowerdefineId);
		return map;
	}

	/**
	 * 修改流程定义信息
	 */
	public HashMap updateFlowerDefine(HashMap map, HashMap flowerNode) throws Exception {
		int st = 0;
		String flowerdefineId = map.get("flowerdefineId").toString();
		
		//主表更新
		st = baseSqlMapDAO.update("FlowerInfo.updateFlowerDefine", map);
		if (st == 0) {
			throw new Exception("修改流程定义时出错");
		}
		//更新流程结点信息
		this.deleteFlowerNode(map);// 将中转站信息全设为归档，后面会有重新创建新的中转站信息
		if (st == 0) {
			throw new Exception("清除流程结点信息出错");
		}
		
		// 插入中转站信息表，多条纪录
		if (null != flowerNode && flowerNode.size() > 0) {
			this.addFlowerNode(flowerNode, Integer.valueOf(flowerNode.get("num").toString()).intValue(), flowerdefineId);
		}
		
		map.clear();
		map.put("st", st);
		map.put("flowernodeId", flowerdefineId);
		return map;
	}

	/**
	 * 删除流程定义信息
	 */
	public int deleteFlowerDefine(HashMap map) throws Exception{
		int st = 0;
		//删除主表
		st = baseSqlMapDAO.update("FlowerInfo.deleteFlowerDefine", map);
		if (st == 0){
			throw new Exception("删除流程定义信息时出错");
		}
		//删除子表
		st = baseSqlMapDAO.update("FlowerInfo.deleteFlowerNode", map);
		if (st == 0){
			throw new Exception("删除流程定义信息时出错");
		}		
		return st;
	}

	/**
	 * 流程结点信息
	 * @param map
	 * @return
	 */
	public List findFlowerNode(HashMap map) {
		return baseSqlMapDAO.queryForList("FlowerInfo.findFlowerNode", map);
	}

	public int insertFlowerNode(HashMap map) {
		return baseSqlMapDAO.update("FlowerInfo.insertFlowerNode", map);
	}

	public int updateFlowerNode(HashMap map) {
		return baseSqlMapDAO.update("FlowerInfo.updateFlowerNode", map);
	}

	public int deleteFlowerNode(HashMap map) {
		return baseSqlMapDAO.update("FlowerInfo.deleteFlowerNode", map);

	}
    //增加流程结点信息
	public void addFlowerNode(HashMap flowerNode, int num, String flowerdefineId)
			throws Exception {
		int st = 0;
		HashMap map = new HashMap();
		if (num == 1) {
			flowerNode.put("flowerdefineId", flowerdefineId);
			flowerNode.put("flowernodeRowno",0);
			st = baseSqlMapDAO.update("FlowerInfo.insertFlowerNode", flowerNode);// 添加中转站点情况
			if (st < 1) {
				throw new Exception("添加流程结点信息失败.");
			}
		}else if (num > 1){
			String[] flowernodeName = (String[]) flowerNode.get("flowernodeName");
			String[] isFlowmust = (String[]) flowerNode.get("isFlowmust");
			String[] isSamedep = (String[]) flowerNode.get("isSamedep");
			String[] isFlowers = (String[]) flowerNode.get("isFlowers");
			String[] approveCodearray = (String[]) flowerNode.get("approveCodearray");
			String[] approveCodearrayname = (String[]) flowerNode.get("approveCodearrayname");

			for (int i = 0; i < num; i++) {
				map.clear();
				map.put("flowernodeName", flowernodeName[i]);
				map.put("isFlowmust", isFlowmust[i]);
				map.put("isSamedep", isSamedep[i]);
				map.put("isFlowers", isFlowers[i]);
				map.put("approveCodearray",approveCodearray[i]);
				map.put("approveCodearrayname",approveCodearrayname[i]);
				
				map.put("flowerdefineId",flowerdefineId);
				map.put("flowernodeRowno",i);
				
				st = baseSqlMapDAO.update("FlowerInfo.insertFlowerNode",map);// 添加流程结点信息
				if (st < 1) {
					throw new Exception("添加流程结点信息失败.");
				}
			}
		}
	}
}
