package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-01-21
 */
@Table("`tong-rong`.`md_security`")
@Data()
@Accessors(chain = true)
public class TbMdSecurity {
    /**
     * 证券ID
     */
    @Column("`SECURITY_ID`")
    private Integer securityId;

    /**
     * 交易代码
     */
    @Column("`TICKER_SYMBOL`")
    private String tickerSymbol;

    /**
     * 交易市场
     */
    @Column("`EXCHANGE_CD`")
    private String exchangeCd;

    /**
     * 证券全称
     */
    @Column("`SEC_FULL_NAME`")
    private String secFullName;

    /**
     * 证券简称
     */
    @Column("`SEC_SHORT_NAME`")
    private String secShortName;

    /**
     * 简称拼音
     */
    @Column("`CN_SPELL`")
    private String cnSpell;

    /**
     * 证券英文全称
     */
    @Column("`SEC_FULL_NAME_EN`")
    private String secFullNameEn;

    /**
     * 证券英文简称
     */
    @Column("`SEC_SHORT_NAME_EN`")
    private String secShortNameEn;

    /**
     * 证券展示代码
     */
    @Column("`DYID`")
    private String dyid;

    /**
     * 证券所属地区
     */
    @Column("`EX_COUNTRY_CD`")
    private String exCountryCd;

    /**
     * 交易货币代码
     */
    @Column("`TRANS_CURR_CD`")
    private String transCurrCd;

    /**
     * 证券类型
     */
    @Column("`ASSET_CLASS`")
    private String assetClass;

    /**
     * 上市状态
     */
    @Column("`LIST_STATUS_CD`")
    private String listStatusCd;

    /**
     * 上市日期
     */
    @Column("`LIST_DATE`")
    private Date listDate;

    /**
     * 摘牌日期
     */
    @Column("`DELIST_DATE`")
    private Date delistDate;

    /**
     * 机构内部ID
     */
    @Column("`PARTY_ID`")
    private Long partyId;

    /**
     * DY使用标识
     */
    @Column("`DY_USE_FLG`")
    private Boolean dyUseFlg;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;
}