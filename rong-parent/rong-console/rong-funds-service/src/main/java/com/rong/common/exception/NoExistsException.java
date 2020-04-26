package com.rong.common.exception;


import com.rong.common.consts.CommonEnumContainer;

/**
 * 记录不存在
 * @author lether
 *
 */
public class NoExistsException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoExistsException(){
		this(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message
	 */
	public NoExistsException(String message){
		super(message,CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST);
	}
}
