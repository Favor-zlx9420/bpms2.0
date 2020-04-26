package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 私募基金当前收益
 * @author      : lether
 * @createDate  : 2020-02-10
 */
@Table("`tb_private_funds_current_income`")
@Data()
@Accessors(chain = true)
public class TbPrivateFundsCurrentIncome extends BaseEntity<TbPrivateFundsCurrentIncome> {
    /**
     * 证券id
     */
    @Column("`security_id`")
    @ApiModelProperty("证券id")
    private Long securityId;

    /**
     * 当前收益
     */
    @Column("`return_a`")
    @ApiModelProperty("当前收益")
    private BigDecimal returnA;

    /**
     * 累计收益
     */
    @Column("`return_accum`")
    @ApiModelProperty("累计收益")
    private BigDecimal returnAccum;

    /**
     * 收益日期
     */
    @Column("`end_date`")
    @ApiModelProperty("收益日期")
    private Date endDate;
}