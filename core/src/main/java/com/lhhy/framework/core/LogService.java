package com.lhhy.framework.core;

/**
 * <ul>日志服务提供者:
 * 	<li>1.提供记录"登录日志"的入口.</li>
 * 	<li>2.提供记录"异常日志"的入口.</li>
 * 	<li>3.提供记录"重要操作日志"的入口.</li>
 * </ul>
 * 
 * @author breeze
 *
 */
public interface LogService {

	/**
	 * 记录 登录日志
	 * @param userId
	 * @param ip
	 * @param loginType
	 * @param srcType
	 * @param content
	 */
	void logLogin(String userId, String ip, int loginType, int srcType, String content, String appVersion);
	/**
	 * 记录错误日志
	 * @param userId
	 * @param ip
	 * @param content
	 * @param t
	 */
	void logError(String userId, String ip, String content, Integer srcType, Throwable t);
	/**
	 * 记录操作日志
	 * @param userId
	 * @param ip
	 * @param srcType
	 * @param modelName
	 * @param content
	 */
	void logOp(String userId, String ip, int srcType, String modelName, String content);
	/**
	 * 记录短信日志
	 * @param phone
	 * @param ip
	 * @param srcType
	 * @param status
	 * @param content
	 * @param errContent
	 */
	void logSms(String phone, String ip, int srcType, int status, String content, String errContent, int msgType, String refId);
	/**
	 * 批量记录发送的短信
	 * 
	 * @param objects
	 */
	void logSms(java.util.List<?> objects);
	
	/**
	 * 记录APP推送日志
	 * @param userId
	 * @param ip
	 * @param content
	 * @param result
	 * @param msgType
	 * @param refId
	 * @param srcType
	 */
	void log2App(String userId, String alias, String ip, String content, String result, int msgType, String refId, int srcType);
	
	/**
	 * 批量记录App推送日志
	 * 
	 * @param objects
	 */
	void log2App(java.util.List<?> objects);
}
