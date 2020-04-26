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
@Table("`tong-rong`.`fund_manager_new`")
@Data()
@Accessors(chain = true)
public class TbFundManagerNew {
    /**
     * 信息编码
     */
    @Column("`ID`")
    @ApiModelProperty("信息编码")
    private Long id;

    /**
     * 
     */
    @Column("`PERSON_ID`")
    @ApiModelProperty("")
    private Long personId;

    /**
     * 
     */
    @Column("`FUND_ID`")
    @ApiModelProperty("")
    private Long fundId;

    /**
     * 
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("")
    private Long securityId;

    /**
     * 
     */
    @Column("`PUBLISH_DATE`")
    @ApiModelProperty("")
    private Date publishDate;

    /**
     * 
     */
    @Column("`POSITION`")
    @ApiModelProperty("")
    private String position;

    /**
     * 
     */
    @Column("`IS_INCUMBENT`")
    @ApiModelProperty("")
    private Boolean isIncumbent;

    /**
     * 
     */
    @Column("`ACCESSION_DATE`")
    @ApiModelProperty("")
    private Date accessionDate;

    /**
     * 
     */
    @Column("`DIMISSION_DATE`")
    @ApiModelProperty("")
    private Date dimissionDate;

    /**
     * 
     */
    @Column("`PARTY_ID`")
    @ApiModelProperty("")
    private Long partyId;

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
}