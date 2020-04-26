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
@Table("`tong-rong`.`pfund_inst_amac`")
@Data()
@Accessors(chain = true)
public class TbPfundInstAmac {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 基金管理人公示编码
     */
    @Column("`INST_ID`")
    private String instId;

    /**
     * 基金管理人全称(中文)
     */
    @Column("`PARTY_FULL_NAME`")
    private String partyFullName;

    /**
     * 基金管理人全称(英文)
     */
    @Column("`PARTY_FULL_NAME_EN`")
    private String partyFullNameEn;

    /**
     * 登记编号
     */
    @Column("`REG_CD`")
    private String regCd;

    /**
     * 组织机构代码
     */
    @Column("`BIZ_CD`")
    private String bizCd;

    /**
     * 登记日期
     */
    @Column("`RECORD_DATE`")
    private Date recordDate;

    /**
     * 注册日期
     */
    @Column("`REG_DATE`")
    private Date regDate;

    /**
     * 注册地址
     */
    @Column("`REG_ADDR`")
    private String regAddr;

    /**
     * 办公地址
     */
    @Column("`OFFICE_ADDR`")
    private String officeAddr;

    /**
     * 注册资本(万元)(人民币)
     */
    @Column("`REG_CAP`")
    private BigDecimal regCap;

    /**
     * 实缴资本(万元)(人民币)
     */
    @Column("`ACT_CAP`")
    private BigDecimal actCap;

    /**
     * 企业性质
     */
    @Column("`INST_NATURE`")
    private String instNature;

    /**
     * 注册资本实缴比例
     */
    @Column("`ACT_CAP_RATE`")
    private BigDecimal actCapRate;

    /**
     * 机构类型
     */
    @Column("`INST_TYPE`")
    private String instType;

    /**
     * 业务类型
     */
    @Column("`SERVICE_TYPE`")
    private String serviceType;

    /**
     * 员工人数
     */
    @Column("`EMP_NUM`")
    private Long empNum;

    /**
     * 机构网址
     */
    @Column("`WEBSITE`")
    private String website;

    /**
     * 是否为符合提供投资建议条件的第三方机构
     */
    @Column("`IS_QUALIFIED_ADVISER`")
    private String isQualifiedAdviser;

    /**
     * 是否为会员
     */
    @Column("`IS_MEMBER`")
    private String isMember;

    /**
     * 会员代表
     */
    @Column("`MEMBER_REP`")
    private String memberRep;

    /**
     * 当前会员类型
     */
    @Column("`MEMBER_TYPE`")
    private String memberType;

    /**
     * 入会时间
     */
    @Column("`INITIATION_TIME`")
    private Date initiationTime;

    /**
     * 法律意见书状态
     */
    @Column("`LEGAL_OP_STATUS`")
    private String legalOpStatus;

    /**
     * 律师事务所名称
     */
    @Column("`LAW_FIRM`")
    private String lawFirm;

    /**
     * 律师姓名
     */
    @Column("`LAWYER_NAME`")
    private String lawyerName;

    /**
     * 法定代表人/执行事务合伙人(委派代表)
     */
    @Column("`LEGAL_REP`")
    private String legalRep;

    /**
     * 法人是否有基金从业资格
     */
    @Column("`IS_QUALIFIED`")
    private String isQualified;

    /**
     * 法人从业资格认定方式
     */
    @Column("`QUALIFY_WAY`")
    private String qualifyWay;

    /**
     * 机构信息最后更新时间
     */
    @Column("`LAST_REPORT_DATE`")
    private Date lastReportDate;

    /**
     * 机构内部编码
     */
    @Column("`PARTY_ID`")
    private Long partyId;

    /**
     * 备案状态
     */
    @Column("`RECORD_STATUS`")
    private Long recordStatus;

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
     * 取得基金从业人数
     */
    @Column("`EMP_NUM_QUALIFIED`")
    private Long empNumQualified;

    /**
     * 机构诚信信息
     */
    @Column("`INST_INT_INF`")
    private String instIntInf;

    /**
     * 特别提示信息
     */
    @Column("`NOTE`")
    private String note;
}