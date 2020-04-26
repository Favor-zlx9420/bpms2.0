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
 * @description : 
 * @author      : Administrator
 * @createDate  : 2020-02-24
 */
@Table("`tb_raised_fund_current_performance`")
@Data()
@Accessors(chain = true)
public class TbRaisedFundCurrentPerformance extends BaseEntity<TbRaisedFundCurrentPerformance> {
    /**
     * 产品id
     */
    @Column("`security_id`")
    @ApiModelProperty("产品id")
    private Long securityId;

    /**
     * 截至日期
     */
    @Column("`end_date`")
    @ApiModelProperty("截至日期")
    private Date endDate;

    /**
     * 近一周收益率
     */
    @Column("`return_rate_1w`")
    @ApiModelProperty("近一周收益率")
    private BigDecimal returnRate1w;

    /**
     * 近一年收益率
     */
    @Column("`return_rate_1y`")
    @ApiModelProperty("近一年收益率")
    private BigDecimal returnRate1y;

    /**
     * 周平均收益率	
     */
    @Column("`stdev`")
    @ApiModelProperty("周平均收益率	")
    private BigDecimal stdev;

    /**
     * 风险
     */
    @Column("`sharpe_ratio`")
    @ApiModelProperty("风险")
    private BigDecimal sharpeRatio;

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