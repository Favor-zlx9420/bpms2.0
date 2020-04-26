package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_nav`")
@Data()
@Accessors(chain = true)
public class TbPfundNav {
    /**
     * 
     */
    @Column("`ID`")
    private Long id;

    /**
     * 
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 
     */
    @Column("`PUBLISH_DATE`")
    private Date publishDate;

    /**
     * 
     */
    @Column("`END_DATE`")
    private Date endDate;

    /**
     * 
     */
    @Column("`NAV`")
    private BigDecimal nav;

    /**
     * 
     */
    @Column("`ACCUM_NAV`")
    private BigDecimal accumNav;

    /**
     * 
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;

    /**
     * 
     */
    @Column("`ADJ_NAV`")
    private BigDecimal adjNav;

    /**
     * 
     */
    @Column("`NAV_UNIT`")
    private BigDecimal navUnit;

    /**
     * 单位净值最小区间收益率
     */
    @Column("`RETURN_RATE`")
    private BigDecimal returnRate;

    /**
     * 调整净值最小区间收益率
     */
    @Column("`ADJ_RETURN_RATE`")
    private BigDecimal adjReturnRate;
}