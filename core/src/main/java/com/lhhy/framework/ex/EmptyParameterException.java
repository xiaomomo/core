package com.lhhy.framework.ex;

/**
 * 参数为空的异常
 * 
 * @author breeze
 *
 */
public class EmptyParameterException extends RuntimeException{

	private static final long serialVersionUID = 7073914919040762235L;

	public EmptyParameterException(){
		super("参数为空");
	}
	
	public EmptyParameterException(String message){
		super(message);
	}
	
	public EmptyParameterException(String message, Throwable t){
		super(message, t);
	}
}
