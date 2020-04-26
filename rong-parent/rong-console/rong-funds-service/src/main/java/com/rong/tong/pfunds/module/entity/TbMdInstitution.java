package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tong-rong`.`md_institution`")
@Data()
@Accessors(chain = true)
public class TbMdInstitution {
    /**
     * 
     */
    @Column("`PARTY_ID`")
    private Integer partyId;

    /**
     * 
     */
    @Column("`PARTY_FULL_NAME`")
    private String partyFullName;

    /**
     * 
     */
    @Column("`PARTY_SHORT_NAME`")
    private String partyShortName;

    /**
     * 
     */
    @Column("`PARTY_FULL_NAME_EN`")
    private String partyFullNameEn;

    /**
     * 
     */
    @Column("`PARTY_SHORT_NAME_EN`")
    private String partyShortNameEn;

    /**
     * 
     */
    @Column("`REG_DATE`")
    private Date regDate;

    /**
     * 
     */
    @Column("`REG_COUNTRY_CD`")
    private String regCountryCd;

    /**
     * 
     */
    @Column("`REG_PROVINCE`")
    private String regProvince;

    /**
     * 
     */
    @Column("`REG_CITY`")
    private String regCity;

    /**
     * 
     */
    @Column("`REG_ADDR`")
    private String regAddr;

    /**
     * 
     */
    @Column("`REG_CAP`")
    private BigDecimal regCap;

    /**
     * 
     */
    @Column("`REG_CAP_CURR_CD`")
    private String regCapCurrCd;

    /**
     * 
     */
    @Column("`OFFICE_ADDR`")
    private String officeAddr;

    /**
     * 
     */
    @Column("`EMAIL`")
    private String email;

    /**
     * 
     */
    @Column("`WEBSITE`")
    private String website;

    /**
     * 
     */
    @Column("`TEL`")
    private String tel;

    /**
     * 
     */
    @Column("`FAX`")
    private String fax;

    /**
     * 
     */
    @Column("`LEGAL_ENTITY_ID`")
    private String legalEntityId;

    /**
     * 
     */
    @Column("`PARTY_NATURE_CD`")
    private String partyNatureCd;

    /**
     * 
     */
    @Column("`IS_ISS_BOND`")
    private String isIssBond;

    /**
     * 
     */
    @Column("`CLOSE_DATE`")
    private Date closeDate;

    /**
     * 
     */
    @Column("`INST_STATUS`")
    private Short instStatus;

    /**
     * 
     */
    @Column("`DY_USE_FLG`")
    private Boolean dyUseFlg;

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
     * 董事会秘书
     */
    @Column("`BOARD_SECRY`")
    private String boardSecry;

    /**
     * 法定代表人
     */
    @Column("`LEGAL_REP`")
    private String legalRep;

    /**
     * 总经理
     */
    @Column("`GEN_MANA`")
    private String genMana;

    /**
     * 是否发行公募基金
     */
    @Column("`IS_ISS_MF`")
    private Integer isIssMf;

    /**
     * 
     */
    @Column("`PRIME_OPERATING`")
    private String primeOperating;

    /**
     * 
     */
    @Column("`PROFILE`")
    private String profile;

    /**
     * 经营范围
     */
    @Column("`OPA_SCOPE`")
    private String opaScope;
}