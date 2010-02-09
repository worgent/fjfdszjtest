package net.trust.application.systemManage.flower.domain;

import java.util.HashMap;
import java.util.List;

import net.trust.application.systemManage.manager.dto.UserInfo;


public interface FlowerFacade {
	/**
	 * 流程定义信息
	 * @param map
	 * @return
	 */
    public int findFlowerDefineCount(HashMap map);
    
    public List findFlowerDefine(HashMap map);
    
    public HashMap insertFlowerDefine(HashMap map,HashMap flowerNode) throws Exception;
    
	public HashMap updateFlowerDefine(HashMap map,HashMap flowerNode)throws Exception;
	
	public int deleteFlowerDefine(HashMap map) throws Exception;	

	/**
	 * 流程节点表
	 * @param map
	 * @return
	 */
    public List findFlowerNode(HashMap map);
    
    public int insertFlowerNode(HashMap map);
    
	public int updateFlowerNode(HashMap map);
	
	public int deleteFlowerNode(HashMap map);
}
