package com.lhhy.framework.core.vo;


public class Result {

	public static final int OK = 0;
	public static final int ERR = -1;
	public static final int TIMEOUTORNOLOGIN = -999;
	public static final int NOPRIVILEGE = -888;
	
	public static final Result SUCESS = new Result(0, "sucess");
	
	//返回结果定义,默认返回"-1",请求失败
	private int code = -1;
	
	private String message = "";

	private Object result;
	
	public Result(){}
	
	public Result(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public Result(int code, String message , Object result){
		this.code = code;
		this.message = message;
		this.result = result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int hashCode(){
		int ret = 31;
		ret = ret + code * 17;
		return ret;
	}
	
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		
		final Result ret = (Result)obj;
		return code == ret.getCode();
	}
	
	public String toString(){
		return String.format("result{'code':%d,'message':%s,'result':%s", code, message , result);
	}
	
}
