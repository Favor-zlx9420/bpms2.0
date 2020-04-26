package com.rong.common.exception;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;

/**
 * 自定义异常
 * @author lether
 *
 */
public class CustomerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Result result;

	public Result getResult() {
		return result;
	}

	public CustomerException(Result rb){
		super(rb.getMessage());
		this.result = rb;
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message 1
	 * @param status 2
	 */
	public CustomerException(String message,CommonEnumContainer.ResultStatus status){
		this(Result.miss(status,message));
	}
	@Override
	public String getMessage(){
		return JSONUtil.toJSONString(this.result);
	}
}
