package com.qzgf.application.systemManage.log.domain;

import java.util.HashMap;
import java.util.List;

public interface LogFacade {
	@SuppressWarnings("unchecked")
	public int findLogCount(HashMap map);

	@SuppressWarnings("unchecked")
	public List findLogInfo(HashMap map);
}
