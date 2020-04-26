package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员信用记录
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_credit_history`")
@Data()
@Accessors(chain = true)
public class TbMemCreditHistory extends BaseEntity<TbMemCreditHistory> {
    /**
     * 会员Id
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 记录类型
     */
    @Column("`record_type`")
    private Integer recordType;

    /**
     * 记录值
     */
    @Column("`credit_value`")
    private Integer creditValue;

    /**
     * 结余
     */
    @Column("`balance`")
    private Integer balance;

    /**
     * 备注
     */
    @Column("`memo`")
    private String memo;

    /**
     * 相关凭证链接(一般是图片/视频/录音等多媒体)
     */
    @Column("`relation_links`")
    private String relationLinks;

    /**
     * 后台审核用户
     */
    @Column("`adm_user_id`")
    private Long admUserId;

    /**
     * 后台操作用户名称
     */
    @Column("`adm_user_name`")
    private String admUserName;
}