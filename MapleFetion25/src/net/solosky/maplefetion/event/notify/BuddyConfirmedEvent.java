 /*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 /**
 * Project  : MapleFetion2
 * Package  : net.solosky.maplefetion.event.notify
 * File     : BuddyConfirmedEvent.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2010-6-3
 * License  : Apache License 2.0 
 */
package net.solosky.maplefetion.event.notify;

import net.solosky.maplefetion.bean.Buddy;
import net.solosky.maplefetion.event.NotifyEvent;
import net.solosky.maplefetion.event.NotifyEventType;

/**
 *
 * 好友回复了用户添加好友的请求
 *
 * @author solosky <solosky772@qq.com>
 *
 */
public class BuddyConfirmedEvent extends NotifyEvent
{

	/**
	 * 好友对象，在好友列表中，之前的关系是未确认(Relation.UNCONFIRMED)
	 */
	private Buddy buddy;
	
	/**
	 * 好友是否同意了用户添加好友的请求
	 */
	private boolean isAgreed;
	/**
	 * 
	 * @param buddy
	 * @param isAgreed
	 */
	public BuddyConfirmedEvent(Buddy buddy, boolean isAgreed)
	{
		this.buddy = buddy;
		this.isAgreed = isAgreed;
	}

	/* (non-Javadoc)
	 * @see net.solosky.maplefetion.event.NotifyEvent#getEventType()
	 */
	@Override
	public NotifyEventType getEventType()
	{
		return NotifyEventType.BUDDY_CONFIRMED;
	}

	/**
	 * @return the buddy
	 */
	public Buddy getBuddy()
	{
		return buddy;
	}

	/**
	 * @return the isAgreed
	 */
	public boolean isAgreed()
	{
		return isAgreed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BuddyConfirmedEvent [buddy=" + buddy + ", isAgreed=" + isAgreed
				+ ", EventType=" + getEventType() + "]";
	}
	
	
}
