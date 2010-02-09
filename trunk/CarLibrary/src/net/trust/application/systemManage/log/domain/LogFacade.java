package net.trust.application.systemManage.log.domain;

import java.util.HashMap;
import java.util.List;

public interface LogFacade {
   public int findLogCount(HashMap map);
   public List findLogInfo(HashMap map);
}
