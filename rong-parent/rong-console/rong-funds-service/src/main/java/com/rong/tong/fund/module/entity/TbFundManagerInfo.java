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
@Table("`tong-rong`.`fund_manager_info`")
@Data()
@Accessors(chain = true)
public class TbFundManagerInfo {
    /**
     * 信息编码
     */
    @Column("`ID`")
    @ApiModelProperty("信息编码")
    private Long id;

    /**
     * 人物ID
     */
    @Column("`PERSON_ID`")
    @ApiModelProperty("人物ID")
    private Long personId;

    /**
     * 人物名字
     */
    @Column("`NAME`")
    @ApiModelProperty("人物名字")
    private String name;

    /**
     * 人物性别
     */
    @Column("`GENDER`")
    @ApiModelProperty("人物性别")
    private String gender;

    /**
     * 出生日期
     */
    @Column("`BIRTHDAY`")
    @ApiModelProperty("出生日期")
    private String birthday;

    /**
     * 国籍
     */
    @Column("`NATIONALITY_CD`")
    @ApiModelProperty("国籍")
    private String nationalityCd;

    /**
     * 证书
     */
    @Column("`CERTIFICATE`")
    @ApiModelProperty("证书")
    private String certificate;

    /**
     * 照片
     */
    @Column("`PHOTO`")
    @ApiModelProperty("照片")
    private String photo;

    /**
     * 教育
     */
    @Column("`EDUCATION`")
    @ApiModelProperty("教育")
    private Integer education;

    /**
     * 从业开始日期
     */
    @Column("`PRACTICE_DATE`")
    @ApiModelProperty("从业开始日期")
    private String practiceDate;

    /**
     * 是否公转私/私转公
     */
    @Column("`MF_TO_PF`")
    @ApiModelProperty("是否公转私/私转公")
    private Boolean mfToPf;

    /**
     * 获奖信息
     */
    @Column("`AWARDS`")
    @ApiModelProperty("获奖信息")
    private String awards;

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
     * 毕业院校
     */
    @Column("`GRAD_UNIV`")
    @ApiModelProperty("毕业院校")
    private String gradUniv;

    /**
     * 所学专业
     */
    @Column("`MAJOR`")
    @ApiModelProperty("所学专业")
    private String major;

    /**
     * 本科学校2
     */
    @Column("`GRAD_UNIV2`")
    @ApiModelProperty("本科学校2")
    private String gradUniv2;

    /**
     * 本科专业2
     */
    @Column("`MAJOR2`")
    @ApiModelProperty("本科专业2")
    private String major2;

    /**
     * 研究生（硕士）学校1
     */
    @Column("`POST_GRAD_UNIV1`")
    @ApiModelProperty("研究生（硕士）学校1")
    private String postGradUniv1;

    /**
     * 研究生（硕士）专业1
     */
    @Column("`POST_GRAD_MAJOR1`")
    @ApiModelProperty("研究生（硕士）专业1")
    private String postGradMajor1;

    /**
     * 研究生（硕士）学校2
     */
    @Column("`POST_GRAD_UNIV2`")
    @ApiModelProperty("研究生（硕士）学校2")
    private String postGradUniv2;

    /**
     * 研究生（硕士）专业2
     */
    @Column("`POST_GRAD_MAJOR2`")
    @ApiModelProperty("研究生（硕士）专业2")
    private String postGradMajor2;

    /**
     * 博士学校1
     */
    @Column("`DR_GRAD_UNIV1`")
    @ApiModelProperty("博士学校1")
    private String drGradUniv1;

    /**
     * 博士专业1
     */
    @Column("`DR_GRAD_MAJOR1`")
    @ApiModelProperty("博士专业1")
    private String drGradMajor1;

    /**
     * 博士学校2
     */
    @Column("`DR_GRAD_UNIV2`")
    @ApiModelProperty("博士学校2")
    private String drGradUniv2;

    /**
     * 博士专业2
     */
    @Column("`DR_GRAD_MAJOR2`")
    @ApiModelProperty("博士专业2")
    private String drGradMajor2;

    /**
     * 定期公告中的简介
     */
    @Column("`BACKGROUND_DESC`")
    @ApiModelProperty("定期公告中的简介")
    private String backgroundDesc;
}