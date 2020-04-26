package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : lether
 * @createDate  : 2020-02-17
 */
@Table("`tong-rong`.`pfund_resume`")
@Data()
@Accessors(chain = true)
public class TbPfundResume {
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
     * 人物名称
     */
    @Column("`NAME`")
    @ApiModelProperty("人物名称")
    private String name;

    /**
     * 入职年份
     */
    @Column("`ACCE_YEAR`")
    @ApiModelProperty("入职年份")
    private String acceYear;

    /**
     * 入职月份
     */
    @Column("`ACCE_MONTH`")
    @ApiModelProperty("入职月份")
    private String acceMonth;

    /**
     * 离职年份
     */
    @Column("`DIMI_YEAR`")
    @ApiModelProperty("离职年份")
    private String dimiYear;

    /**
     * 离职月份
     */
    @Column("`DIMI_MONTH`")
    @ApiModelProperty("离职月份")
    private String dimiMonth;

    /**
     * 公司ID
     */
    @Column("`PARTY_ID`")
    @ApiModelProperty("公司ID")
    private Long partyId;

    /**
     * 历任公司全称
     */
    @Column("`PARTY_FULL_NAME`")
    @ApiModelProperty("历任公司全称")
    private String partyFullName;

    /**
     * 职位
     */
    @Column("`POSITION`")
    @ApiModelProperty("职位")
    private String position;

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
     * 是否在职
     */
    @Column("`IS_INCUMBENT`")
    @ApiModelProperty("是否在职")
    private String isIncumbent;
}