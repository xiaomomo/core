package com.lhhy.framework.ex;

/**
 * 参数为空的异常
 * 
 * @author breeze
 *
 */
public class OutOfLengthException extends RuntimeException{

	private static final long serialVersionUID = 7073914919040762235L;

	public OutOfLengthException(){
		super("参数错误：输入字符超出");
	}
	
	public OutOfLengthException(String message){
		super(message);
	}
	
	public OutOfLengthException(String message, Throwable t){
		super(message, t);
	}
}
