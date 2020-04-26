package com.rong.tong.fund.module.entity;

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
 * @createDate  : 2020-02-26
 */
@Table("`tong-rong`.`fund_adj_nav`")
@Data()
@Accessors(chain = true)
public class TbFundAdjNav {
    /**
     * 
     */
    @Column("`ID`")
    @ApiModelProperty("")
    private Long id;

    /**
     * 
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("")
    private Long securityId;

    /**
     * 
     */
    @Column("`END_DATE`")
    @ApiModelProperty("")
    private Date endDate;

    /**
     * 
     */
    @Column("`ADJ_NAV`")
    @ApiModelProperty("")
    private BigDecimal adjNav;

    /**
     * 
     */
    @Column("`ADJ_FACTOR`")
    @ApiModelProperty("")
    private BigDecimal adjFactor;

    /**
     * 
     */
    @Column("`RETURN_RATE`")
    @ApiModelProperty("")
    private BigDecimal returnRate;

    /**
     * 
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;
}