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
public class TqMemRegister extends TqBase {
    /**
     * 注册手机号码
     */
    @ApiModelProperty(value = "注册手机号码/登录手机号码（假如用户已经注册直接返回，假如没有则使用该手机注册一个用户）",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.PHONE_NUMBER)
    private String phone;

    /**
     * 密码:传过来的应是md5加密之后的数据
     */
    @ApiModelProperty(value = "登录密码",required = true)
    //@RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH255)
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "接收到的验证码",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH8)
    private String verificationCode;
    @IntegerRangeValidator(min = 0, max = 3)
    private Integer from = MemEnumContainer.RegFrom.手机号码注册.getValue();
    /**
     * 注册email
     */
//    @RequireValidator
//    @RegexValidator(regexStr = Validator.EMAIL,errorContent = "请输入正确的email")
    @ApiModelProperty(value = "用户email")
    private String email;
    /**
     * 推荐码
     */
    @ApiModelProperty(value = "推荐注册的推荐码")
    private String recommendCode;

    /**
     * 保存时长：单位：秒
     */
    @ApiModelProperty(value = "登录信息保存时长（单位：秒，最低30分钟，最高7天）")
    @IntegerRangeValidator(min = 60*30,max = 24*7*60*60)
    private Integer storeSeconds;
}
