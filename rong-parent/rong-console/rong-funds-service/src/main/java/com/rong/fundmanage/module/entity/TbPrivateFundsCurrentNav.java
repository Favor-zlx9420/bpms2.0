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
 * @description : 私募基金当前净值
 * @author      : lether
 * @createDate  : 2020-02-10
 */
@Table("`tb_private_funds_current_nav`")
@Data()
@Accessors(chain = true)
public class TbPrivateFundsCurrentNav extends BaseEntity<TbPrivateFundsCurrentNav> {
    /**
     * 证券id
     */
    @Column("`security_id`")
    @ApiModelProperty("证券id")
    private Long securityId;

    /**
     * 基金净值
     */
    @Column("`nav`")
    @ApiModelProperty("基金净值")
    private BigDecimal nav;

    /**
     * 累计净值
     */
    @Column("`accum_nav`")
    @ApiModelProperty("累计净值")
    private BigDecimal accumNav;

    /**
     * 复权净值,净值变动
     */
    @Column("`adj_nav`")
    @ApiModelProperty("复权净值,净值变动")
    private BigDecimal adjNav;

    /**
     * 调整净值回报
     */
    @Column("`return_rate`")
    @ApiModelProperty("调整净值回报")
    private BigDecimal returnRate;

    /**
     * 净值日期
     */
    @Column("`end_date`")
    @ApiModelProperty("净值日期")
    private Date endDate;

    /**
     * 人物id
     */
    @Column("`person_id`")
    @ApiModelProperty("人物id")
    private Long personId;

    /**
     * 机构id
     */
    @Column("`party_id`")
    @ApiModelProperty("机构id")
    private Long partyId;
}