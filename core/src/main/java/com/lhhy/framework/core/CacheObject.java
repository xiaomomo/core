package com.lhhy.framework.core;

import java.util.Date;

public interface CacheObject {

	Object getObject();
	
	Date getExpired();
}
