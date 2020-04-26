package com.rong.common.exception;


import com.rong.common.consts.CommonEnumContainer;

/**
 * 无权限
 * @author lether
 *
 */
public class NoPermissionException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoPermissionException(){
		this(CommonEnumContainer.ResultStatus.WITHOUT_PERMISSION.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message 无权限异常信息
	 */
	public NoPermissionException(String message){
		super(message,CommonEnumContainer.ResultStatus.WITHOUT_PERMISSION);
	}
}
