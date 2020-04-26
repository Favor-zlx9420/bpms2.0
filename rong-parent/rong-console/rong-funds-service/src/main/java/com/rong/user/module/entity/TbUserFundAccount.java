package com.rong.user.module.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.module.BaseEntity;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd2s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 基金记账表
 * @author      : Administrator
 * @createDate  : 2020-02-28
 */
@Table("`tb_user_fund_account`")
@Data()
@Accessors(chain = true)
public class TbUserFundAccount extends BaseEntity<TbUserFundAccount> {
    /**
     * 产品id
     */
    @Column("`security_id`")
    @ApiModelProperty("产品id")
    private Long securityId;

    /**
     * 购买用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("购买用户id")
    private Long userId;

    /**
     * 购买时日期
     */
    @Column("`buy_date`")
    @ApiModelProperty("购买时日期")
    private Date buyDate;

    /**
     * 购买时净值
     */
    @Column("`then_nav`")
    @ApiModelProperty("购买时净值，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal thenNav;

    /**
     * 购买份数
     */
    @Column("`share`")
    @ApiModelProperty("购买份数")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal share;

    /**
     * 本金（单位：元）
     */
    @Column("`principal`")
    @ApiModelProperty("本金（单位：元）")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal principal;
}