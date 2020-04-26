package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 消息发送历史
 * @author      : lether
 * @createDate  : 2020-03-31
 */
@Table("`tb_message_history`")
@Data()
@Accessors(chain = true)
public class TbMessageHistory extends BaseEntity<TbMessageHistory> {

    /**
     * 状态
     */
    @Column("`state`")
    @ApiModelProperty("状态")
    private Integer state;

    /**
     * 发送类型（短信文本(1),短信语音(2),短信图形码(3),Email文本(4),Email语音(5),Email图形码(6)）
     */
    @Column("`code_type`")
    @ApiModelProperty("发送类型（短信文本(1),短信语音(2),短信图形码(3),Email文本(4),Email语音(5),Email图形码(6)）")
    private Integer codeType;

    /**
     * 业务类型（注册(1),验证手机号码(2),解除手机号码(3),身份验证(4),找回密码(5),广告内容(6),其他内容(7),企业登录(8),企业审核通知(9),）
     */
    @Column("`content_type`")
    @ApiModelProperty("业务类型（注册(1),验证手机号码(2),解除手机号码(3),身份验证(4),找回密码(5),广告内容(6),其他内容(7),企业登录(8),企业审核通知(9),）")
    private Integer contentType;

    /**
     * 标题
     */
    @Column("`title`")
    @ApiModelProperty("标题")
    private String title;

    /**
     * 内容
     */
    @Column("`content`")
    @ApiModelProperty("内容")
    private String content;

    /**
     * 关联id
     */
    @Column("`relation_uuid`")
    @ApiModelProperty("关联id")
    private String relationUuid;

    /**
     * 发送目标
     */
    @Column("`target`")
    @ApiModelProperty("发送目标")
    private String target;

    /**
     * local
     */
    @Column("`local`")
    @ApiModelProperty("local")
    private String local;

    /**
     * ip
     */
    @Column("`ip`")
    @ApiModelProperty("ip")
    private String ip;
}