package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员银行卡
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_bankcard`")
@Data()
@Accessors(chain = true)
public class TbMemBankcard extends BaseEntity<TbMemBankcard> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 会员ID
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 银行卡表ID
     */
    @Column("`bank_id`")
    private Long bankId;

    /**
     * 开户行地址
     */
    @Column("`branch`")
    private String branch;

    /**
     * 银行卡号
     */
    @Column("`number`")
    private String number;

    /**
     * 备注
     */
    @Column("`memo`")
    private String memo;

    /**
     * 省
     */
    @Column("`province`")
    private String province;

    /**
     * 市
     */
    @Column("`city`")
    private String city;

    /**
     * 区
     */
    @Column("`area`")
    private String area;

    /**
     * 卡类型
     */
    @Column("`card_type`")
    private Integer cardType;

    /**
     * 银行名称
     */
    @Column("`bank_name`")
    private String bankName;
}