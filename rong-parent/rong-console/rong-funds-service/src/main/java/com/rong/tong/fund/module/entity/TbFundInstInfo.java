package com.rong.tong.fund.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : lether
 * @createDate  : 2020-02-21
 */
@Table("`tong-rong`.`fund_inst_info`")
@Data()
@Accessors(chain = true)
public class TbFundInstInfo {
    /**
     * 
     */
    @Column("`ID`")
    @ApiModelProperty("")
    private Long id;

    /**
     * 机构内部ID
     */
    @Column("`PARTY_ID`")
    @ApiModelProperty("机构内部ID")
    private Integer partyId;

    /**
     * 机构全称
     */
    @Column("`PARTY_FULL_NAME`")
    @ApiModelProperty("机构全称")
    private String partyFullName;

    /**
     * 机构简称
     */
    @Column("`PARTY_SHORT_NAME`")
    @ApiModelProperty("机构简称")
    private String partyShortName;

    /**
     * 机构英文全称
     */
    @Column("`PARTY_FULL_NAME_EN`")
    @ApiModelProperty("机构英文全称")
    private String partyFullNameEn;

    /**
     * 机构英文简称
     */
    @Column("`PARTY_SHORT_NAME_EN`")
    @ApiModelProperty("机构英文简称")
    private String partyShortNameEn;

    /**
     * 注册日期
     */
    @Column("`REG_DATE`")
    @ApiModelProperty("注册日期")
    private Date regDate;

    /**
     * 法定代表人
     */
    @Column("`LEGAL_REP`")
    @ApiModelProperty("法定代表人")
    private String legalRep;

    /**
     * 注册国家代码
     */
    @Column("`REG_COUNTRY_CD`")
    @ApiModelProperty("注册国家代码")
    private String regCountryCd;

    /**
     * 注册省份
     */
    @Column("`REG_PROVINCE`")
    @ApiModelProperty("注册省份")
    private String regProvince;

    /**
     * 注册城市
     */
    @Column("`REG_CITY`")
    @ApiModelProperty("注册城市")
    private String regCity;

    /**
     * 注册地址
     */
    @Column("`REG_ADDR`")
    @ApiModelProperty("注册地址")
    private String regAddr;

    /**
     * 注册资金
     */
    @Column("`REG_CAP`")
    @ApiModelProperty("注册资金")
    private Long regCap;

    /**
     * 注册资金货币代码
     */
    @Column("`REG_CAP_CURR_CD`")
    @ApiModelProperty("注册资金货币代码")
    private String regCapCurrCd;

    /**
     * 办公地址
     */
    @Column("`OFFICE_ADDR`")
    @ApiModelProperty("办公地址")
    private String officeAddr;

    /**
     * 邮政编码(办公地址邮编)
     */
    @Column("`POST_CODE`")
    @ApiModelProperty("邮政编码(办公地址邮编)")
    private String postCode;

    /**
     * 联系邮箱
     */
    @Column("`EMAIL`")
    @ApiModelProperty("联系邮箱")
    private String email;

    /**
     * 机构网站
     */
    @Column("`WEBSITE`")
    @ApiModelProperty("机构网站")
    private String website;

    /**
     * 联系电话
     */
    @Column("`TEL`")
    @ApiModelProperty("联系电话")
    private String tel;

    /**
     * 联系传真
     */
    @Column("`FAX`")
    @ApiModelProperty("联系传真")
    private String fax;

    /**
     * 董事会秘书
     */
    @Column("`BOARD_SECRY`")
    @ApiModelProperty("董事会秘书")
    private String boardSecry;

    /**
     * 总经理
     */
    @Column("`GEN_MANA`")
    @ApiModelProperty("总经理")
    private String genMana;

    /**
     * 工商登记号
     */
    @Column("`LEGAL_ENTITY_ID`")
    @ApiModelProperty("工商登记号")
    private String legalEntityId;

    /**
     * 统一社会信用代码
     */
    @Column("`CREDIT_CODE`")
    @ApiModelProperty("统一社会信用代码")
    private String creditCode;

    /**
     * 公司性质
     */
    @Column("`PARTY_NATURE_CD`")
    @ApiModelProperty("公司性质")
    private String partyNatureCd;

    /**
     * 机构终止日期
     */
    @Column("`CLOSE_DATE`")
    @ApiModelProperty("机构终止日期")
    private Date closeDate;

    /**
     * 是否运作
     */
    @Column("`INST_STATUS`")
    @ApiModelProperty("是否运作")
    private Short instStatus;

    /**
     * 机构LOGO
     */
    @Column("`PHOTO`")
    @ApiModelProperty("机构LOGO")
    private String photo;

    /**
     * 机构类型
     */
    @Column("`PARTY_TYPE`")
    @ApiModelProperty("机构类型")
    private String partyType;

    /**
     * 机构派系
     */
    @Column("`PARTY_SECT`")
    @ApiModelProperty("机构派系")
    private String partySect;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("时间戳")
    private Long tmstamp;

    /**
     * 经营范围
     */
    @Column("`OPA_SCOPE`")
    @ApiModelProperty("经营范围")
    private String opaScope;

    /**
     * 主营业务
     */
    @Column("`PRIME_OPERATING`")
    @ApiModelProperty("主营业务")
    private String primeOperating;

    /**
     * 机构介绍
     */
    @Column("`PROFILE`")
    @ApiModelProperty("机构介绍")
    private String profile;
}