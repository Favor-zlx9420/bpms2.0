package com.rong.member.module.req;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取手机验证码
 * 服务器不加入随机验证码来验证了，但是客户端需要，这里只是最为备用以便以后需要
 * @author lether
 *
 */
@Data
public class TqGetVerificationCode extends TqBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 手机号码：要发送的手机号码
	 */
	@ApiModelProperty(value = "接收验证码的号码",required = true)
	@RequireValidator
	private String receiver;
	/**
	 * 业务类型 比如：注册验证码，通用，广告内容等待。
	 */
	@ApiModelProperty(value = "业务类型:1,注册/验证码登录;4:身份验证,5:找回密码;6:设置交易密码",allowableValues = "1,4,5,6",required = true)
	@RequireValidator
	private Integer contentType;
	/**
	 * 逻辑类型：普通短信／语音短信等
	 */
	@ApiModelProperty(value = "短息类型：1,短信文本;2,email文本",allowableValues = "1,2",required = true)
	private Integer codeType = CommonEnumContainer.TripartiteMessageCodeType.SMS_TEXT.getValue();
	/**
	 * 验证码凭证
	 */
	@ApiModelProperty(value = "图形验证码",required = false)
	//@RequireValidator
	private String ticket;
}
