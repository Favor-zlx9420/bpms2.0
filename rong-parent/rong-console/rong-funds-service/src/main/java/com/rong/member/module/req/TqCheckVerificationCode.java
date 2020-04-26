package com.rong.member.module.req;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TqBase;

/**
 * creator : whh-lether
 * date    : 2019/6/20 14:37
 * desc    :
 **/
public class TqCheckVerificationCode extends TqBase {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 接受验证码的实体
     */
    @RequireValidator
    private String receiver;
    /**
     * 业务类型 比如：注册验证码，通用，广告内容等待
     */
    @RequireValidator
    private Integer contentType;
    /**
     * 逻辑类型：普通短信／语音短信等
     */
    private Integer codeType = CommonEnumContainer.TripartiteMessageCodeType.EMAIL_TEXT.getValue();
    @RequireValidator
    private String verificationCode;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public TqCheckVerificationCode(){

    }
    public TqCheckVerificationCode(Builder builder){
        this.receiver = builder.receiver;
        this.contentType = builder.contentType;
        this.codeType = builder.codeType;
        this.verificationCode = builder.verificationCode;
    }

    public final static class Builder{
        private String receiver;
        private Integer contentType;
        private Integer codeType = CommonEnumContainer.TripartiteMessageCodeType.EMAIL_TEXT.getValue();
        private String verificationCode;
        public Builder receiver(String receiver){
            this.receiver = receiver;
            return this;
        }
        public Builder contentType(Integer contentType){
            this.contentType = contentType;
            return this;
        }
        public Builder codeType(Integer codeType){
            this.codeType = codeType;
            return this;
        }
        public Builder verificationCode(String verificationCode){
            this.verificationCode = verificationCode;
            return this;
        }
        public TqCheckVerificationCode build(){
            return new TqCheckVerificationCode(this);
        }
    }
}