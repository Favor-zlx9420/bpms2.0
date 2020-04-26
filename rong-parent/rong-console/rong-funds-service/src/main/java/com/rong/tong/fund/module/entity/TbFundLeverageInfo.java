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
 * @author      : lether
 * @createDate  : 2020-02-21
 */
@Table("`tong-rong`.`fund_leverage_info`")
@Data()
@Accessors(chain = true)
public class TbFundLeverageInfo {
    /**
     * 
     */
    @Column("`ID`")
    @ApiModelProperty("")
    private Long id;

    /**
     * 
     */
    @Column("`FUND_ID`")
    @ApiModelProperty("")
    private Long fundId;

    /**
     * 
     */
    @Column("`CATEGORY`")
    @ApiModelProperty("")
    private String category;

    /**
     * 
     */
    @Column("`IDX_ID`")
    @ApiModelProperty("")
    private Long idxId;

    /**
     * 
     */
    @Column("`IDX_CN`")
    @ApiModelProperty("")
    private String idxCn;

    /**
     * 
     */
    @Column("`SECURITY_M`")
    @ApiModelProperty("")
    private Long securityM;

    /**
     * 
     */
    @Column("`SECURITY_A`")
    @ApiModelProperty("")
    private Long securityA;

    /**
     * 
     */
    @Column("`SECURITY_B`")
    @ApiModelProperty("")
    private Long securityB;

    /**
     * 
     */
    @Column("`A_SHARE`")
    @ApiModelProperty("")
    private Long aShare;

    /**
     * 
     */
    @Column("`B_SHARE`")
    @ApiModelProperty("")
    private Long bShare;

    /**
     * 
     */
    @Column("`IS_CONVERT`")
    @ApiModelProperty("")
    private Boolean isConvert;

    /**
     * 
     */
    @Column("`A_RATIO_IN_TA`")
    @ApiModelProperty("")
    private Long aRatioInTa;

    /**
     * 
     */
    @Column("`B_RATIO_IN_TA`")
    @ApiModelProperty("")
    private Long bRatioInTa;

    /**
     * 
     */
    @Column("`MIN_MERGE_M`")
    @ApiModelProperty("")
    private Long minMergeM;

    /**
     * 
     */
    @Column("`MIN_MERGE_A`")
    @ApiModelProperty("")
    private Long minMergeA;

    /**
     * 
     */
    @Column("`MIN_MERGE_B`")
    @ApiModelProperty("")
    private Long minMergeB;

    /**
     * 
     */
    @Column("`MIN_SPLIT_M`")
    @ApiModelProperty("")
    private Long minSplitM;

    /**
     * 
     */
    @Column("`MIN_SPLIT_A`")
    @ApiModelProperty("")
    private Long minSplitA;

    /**
     * 
     */
    @Column("`MIN_SPLIT_B`")
    @ApiModelProperty("")
    private Long minSplitB;

    /**
     * 
     */
    @Column("`ESTABLISH_DATE`")
    @ApiModelProperty("")
    private Date establishDate;

    /**
     * 
     */
    @Column("`END_DATE`")
    @ApiModelProperty("")
    private Date endDate;

    /**
     * 
     */
    @Column("`REGULAR_SPLIT_DATE`")
    @ApiModelProperty("")
    private Date regularSplitDate;

    /**
     * 
     */
    @Column("`UP_THRESHOLD`")
    @ApiModelProperty("")
    private BigDecimal upThreshold;

    /**
     * 
     */
    @Column("`DOWN_THRESHOLD`")
    @ApiModelProperty("")
    private BigDecimal downThreshold;

    /**
     * 
     */
    @Column("`SPLIT_NOTE`")
    @ApiModelProperty("")
    private String splitNote;

    /**
     * 
     */
    @Column("`IS_SUB_M`")
    @ApiModelProperty("")
    private Boolean isSubM;

    /**
     * 
     */
    @Column("`IS_RED_M`")
    @ApiModelProperty("")
    private Boolean isRedM;

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

    /**
     * 
     */
    @Column("`FIXED_RETURN_A`")
    @ApiModelProperty("")
    private String fixedReturnA;

    /**
     * 
     */
    @Column("`MAX_APPLY`")
    @ApiModelProperty("")
    private BigDecimal maxApply;

    /**
     * 
     */
    @Column("`MAX_REDEEM`")
    @ApiModelProperty("")
    private BigDecimal maxRedeem;

    /**
     * 
     */
    @Column("`APPLY_PATTERN`")
    @ApiModelProperty("")
    private Long applyPattern;

    /**
     * 
     */
    @Column("`REDEEM_PATTERN`")
    @ApiModelProperty("")
    private Long redeemPattern;

    /**
     * 
     */
    @Column("`IS_FIXED`")
    @ApiModelProperty("")
    private Boolean isFixed;

    /**
     * 
     */
    @Column("`INITIAL_LEVER`")
    @ApiModelProperty("")
    private BigDecimal initialLever;
}