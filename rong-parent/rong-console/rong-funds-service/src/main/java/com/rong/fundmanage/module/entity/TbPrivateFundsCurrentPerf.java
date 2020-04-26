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
 * @description : 私募基金净值性能指标
 * @author      : lether
 * @createDate  : 2020-02-10
 */
@Table("`tb_private_funds_current_perf`")
@Data()
@Accessors(chain = true)
public class TbPrivateFundsCurrentPerf extends BaseEntity<TbPrivateFundsCurrentPerf> {
    /**
     * 证券id
     */
    @Column("`security_id`")
    @ApiModelProperty("证券id")
    private Long securityId;

    /**
     * 性能指标计算时间窗口长度
     */
    @Column("`window`")
    @ApiModelProperty("性能指标计算时间窗口长度")
    private String window;

    /**
     * 近一年年化收益
     */
    @Column("`return_of_latest_year`")
    @ApiModelProperty("近一年年化收益")
    private BigDecimal returnOfLatestYear;

    /**
     * 近一年风险
     */
    @Column("`risk_of_latest_year`")
    @ApiModelProperty("近一年风险")
    private BigDecimal riskOfLatestYear;

    /**
     * 收益日期
     */
    @Column("`end_date`")
    @ApiModelProperty("收益日期")
    private Date endDate;

    /**
     * 成立以来年化收益
     */
    @Column("`return_of_establish`")
    @ApiModelProperty("成立以来年化收益")
    private BigDecimal returnOfEstablish;

    /**
     * 近一个月年化收益
     */
    @Column("`return_of_latest_month`")
    @ApiModelProperty("近一个月年化收益")
    private BigDecimal returnOfLatestMonth;
    /**
     * 今年以来年化收益
     */
    @Column("`return_of_this_year`")
    @ApiModelProperty("今年以来年化收益")
    private BigDecimal returnOfThisYear;
}