package com.rong.member.module.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * creator : whh-lether
 * date    : 2018/12/19 18:25
 * desc    :
 **/
@Data
public class TmGetVerificationCode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 发送的号码
     */
    private String receiver;
    /**
     * 验证码内容
     */
    private String content;
    /**
     * 保存时长：秒
     */
    private int seconds;
}