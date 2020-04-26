package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_inst_info`")
@Data()
@Accessors(chain = true)
public class TbPfundInstInfo {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 公司ID
     */
    @Column("`PARTY_ID`")
    private Long partyId;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;

    /**
     * 登记日期
     */
    @Column("`RECORD_DATE`")
    private Date recordDate;

    /**
     * 登记编码
     */
    @Column("`REG_CD`")
    private String regCd;

    /**
     * 法定代表人/执行事务合伙人(委派代表) 
     */
    @Column("`LEGAL_REP`")
    private String legalRep;

    /**
     * 法人是否有从业资格
     */
    @Column("`IS_QUALIFIED`")
    private Boolean isQualified;

    /**
     * 法人从业资格认定方式
     */
    @Column("`QUALIFY_WAY`")
    private String qualifyWay;

    /**
     * 员工数量
     */
    @Column("`EMP_NUM`")
    private Integer empNum;

    /**
     * 管理基金主要类别
     */
    @Column("`MAIN_FUND_TYPE`")
    private String mainFundType;

    /**
     * 私募证券基金自主发行规模
     */
    @Column("`PRIVATE_IND_SCALE`")
    private String privateIndScale;

    /**
     * 私募证券基金顾问管理规模
     */
    @Column("`PRIVATE_CON_SCALE`")
    private String privateConScale;

    /**
     * 私募股权基金规模
     */
    @Column("`PE_SCALE`")
    private String peScale;

    /**
     * 创业投资基金规模
     */
    @Column("`VC_SCALE`")
    private String vcScale;

    /**
     * 其他私募基金规模
     */
    @Column("`OTHER_SCALE`")
    private String otherScale;

    /**
     * 是否公募管理人
     */
    @Column("`IS_FUND_MANA`")
    private Long isFundMana;

    /**
     * 私募资产配置基金
     */
    @Column("`PAA_SCALE`")
    private String paaScale;

    /**
     * 备案状态
     */
    @Column("`RECORD_STATUS`")
    private String recordStatus;

    /**
     * 基本信息
     */
    @Column("`PROFILE`")
    private String profile;

    /**
     * 投资理念
     */
    @Column("`IDEA_STRATEGY`")
    private String ideaStrategy;

    /**
     * 核心人物
     */
    @Column("`KEY_PERSON`")
    private String keyPerson;
}