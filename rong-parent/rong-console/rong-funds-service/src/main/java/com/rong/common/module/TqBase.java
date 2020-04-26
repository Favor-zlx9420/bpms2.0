package com.rong.common.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TqBase implements Serializable {
	@ApiModelProperty("请求状态保持，例如在获取图形验证码时需要传送该参数以保证下次请求时识别上一次的请求")
	private String apiToken;
	@ApiModelProperty(value = "请求业务号，每次请求需带不同的随机号", name = "frequentNo", required = true, example = "5478595")
	private String frequentNo;
	@ApiModelProperty(hidden = true)
	private String reqIp;
	/**
	 * 用户token token
	 */
	@ApiModelProperty(value = "通过登录、注册等获取的用户token,假如用户已经登录，可以传该值", name = "userAuthToken",example = "sfer3agrtsfasdfsf",dataType = "string")
	private String userAuthToken;
	@ApiModelProperty(hidden = true)
	private long loginUserId = -1L;
	@ApiModelProperty(hidden = true)
	private UserInfo userInfo;
}
