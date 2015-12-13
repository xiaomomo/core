package com.lhhy.framework.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhhy.framework.ConfigUtils;

/**
 * 签名工具类
 * 
 * @author ken
 *
 */
public final class SignUtils {
	public static final String SIGN = "sign";
	public static final Logger LOG = LoggerFactory.getLogger(SignUtils.class);
	
	private SignUtils(){}
	
	public static boolean verifySign(Map<String, String> params){
		
		if(params == null || params.isEmpty()){
			LOG.warn("Input params is empty.");
			return false;
		}
		
		String sign = (String) params.get("sign");
		if(StringUtils.isBlank(sign)){
			LOG.error("Input params donot include a sign data");
			return false;
		}
		
		params.remove("sign");
		
		StringBuilder buildSign = new StringBuilder();
		
		buildSign.append(map2String(params));
		String key = ConfigUtils.getString("secret.key");
		if(StringUtils.isNotBlank(key)){
			buildSign.append("&key=");
			buildSign.append(key);
		}

		LOG.debug("Concat secret = {}", buildSign);
		
		String md5Sign = Md5.encoderByMd5(buildSign.toString());
		
		LOG.debug("Input sign vs build sign : {} vs {} ", sign, md5Sign);
		
		return sign.equals(md5Sign);
	}
	
	/**
	 * 将map转换为url格式字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String map2String(Map<String, String> map) {
		if (null == map || map.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		return sb.toString();
	}
	
	public static void main(String[] args){
		Map<String, String> map = Utils.newTreeMap();
		map.put("account", "13503090920");
		map.put("password", "123456");
		map.put("loginType", "1");
		map.put("clientType", "3");
		map.put("appVersion", "1.0.0");
		map.put("modelType", "Iphone6");
		map.put("clientScreen", "1024*800");
		map.put("networkType", "wifi");
		map.put("sign", "e337868000b72b2cd25b3ac44db0b128");
		//map.put("sign", "cb01e03a6fbd9bec6d8029a9fac0f16a");
		
		System.out.println(SignUtils.verifySign(map));
		
	}
}
