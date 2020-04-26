package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员收货地址
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_rec_info`")
@Data()
@Accessors(chain = true)
public class TbMemRecInfo extends BaseEntity<TbMemRecInfo> {
    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 会员id
     */
    @Column("`member_id`")
    private Long memberId;

    /**
     * 收货人
     */
    @Column("`receiver`")
    private String receiver;

    /**
     * 国家
     */
    @Column("`country`")
    private String country;

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
     * 详细地址
     */
    @Column("`address`")
    private String address;

    /**
     * 邮编
     */
    @Column("`post_code`")
    private String postCode;

    /**
     * 手机
     */
    @Column("`phone`")
    private String phone;

    /**
     * 固话
     */
    @Column("`call`")
    private String call;

    /**
     * 是否默认
     */
    @Column("`defaulted`")
    private Boolean defaulted;
}