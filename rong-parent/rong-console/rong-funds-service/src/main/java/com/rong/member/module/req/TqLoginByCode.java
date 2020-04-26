package com.rong.member.module.req;

import com.rong.common.annotation.IntegerRangeValidator;
import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import com.rong.common.util.Validator;
import com.rong.member.consts.MemEnumContainer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqLoginByCode extends TqBase {
    /**
     * 注册手机号码
     */
    @ApiModelProperty(value = "登录手机号码",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.PHONE_NUMBER)
    private String phone;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "接收到的验证码",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH8)
    private String verificationCode;

    /**
     * 保存时长：单位：秒
     */
    @ApiModelProperty(value = "登录信息保存时长（单位：秒，最低30分钟，最高7天）")
    @IntegerRangeValidator(min = 60*30,max = 24*7*60*60)
    private Integer storeSeconds;

    @ApiModelProperty("用户类别（0，直营店客服；1，基金经理；2，机构代理，3，普通用户，4，直营店用户）")
    private Integer type;

}
