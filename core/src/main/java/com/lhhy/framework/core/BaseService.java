package com.lhhy.framework.core;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseService {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	@Qualifier(value="dao") 
	private DBProcess dao;
    
	@Autowired
	private LogService logService;
	
	public DBProcess getDao() {
		return dao;
	}
	
	public LogService getLog(){
		return this.logService;
	}
}
