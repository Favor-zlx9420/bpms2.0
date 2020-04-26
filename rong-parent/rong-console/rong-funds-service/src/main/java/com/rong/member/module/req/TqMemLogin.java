package com.rong.member.module.req;

import com.rong.common.annotation.IntegerRangeValidator;
import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import com.rong.common.util.Validator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqMemLogin extends TqBase {
	private static final long serialVersionUID = 1L;
	/**
	 * 登录用户名：可能也通过phone号码等登录
	 */
	@ApiModelProperty(value = "登录用户名，一般为手机号码",required = true)
	@RequireValidator
	@RegexValidator(regexStr = Validator.STR_MAX_LENGTH128)
	private String userName;
	/**
	 * 登录密码：第一次加密的密码。
	 */
	@ApiModelProperty(value = "登录密码",required = true)
	@RequireValidator
	@RegexValidator(regexStr = Validator.STR_MAX_LENGTH128)
	private String password;

	/**
	 * 保存时长：单位：秒
	 */
	@ApiModelProperty(value = "登录信息保存时长（单位：秒，最低30分钟，最高7天）")
	@IntegerRangeValidator(min = 60*30,max = 24*7*60*60)
	private Integer storeSeconds;

	@ApiModelProperty("用户类别（0，直营店客服；1，基金经理；2，机构代理，3，普通用户，4，直营店用户）")
	private Integer type;
}
