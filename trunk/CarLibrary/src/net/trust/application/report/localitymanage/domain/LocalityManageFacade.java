package net.trust.application.report.localitymanage.domain;

import java.util.HashMap;
import java.util.List;
/**
 * 综合报表 -》 位置管理
 * @author chenqf
 *
 */
public interface LocalityManageFacade {
	
	public int findLocalityManageCount(HashMap map);
	
	public List findLocalityManage(HashMap map);
}
