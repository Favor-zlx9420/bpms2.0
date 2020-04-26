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
@Table("`tong-rong`.`fund_fee`")
@Data()
@Accessors(chain = true)
public class TbFundFee {
    /**
     * 信息编码
     */
    @Column("`ID`")
    @ApiModelProperty("信息编码")
    private Long id;

    /**
     * 证券内部ID
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("证券内部ID")
    private Long securityId;

    /**
     * 发布日期
     */
    @Column("`PUBLISH_DATE`")
    @ApiModelProperty("发布日期")
    private Date publishDate;

    /**
     * 是否执行
     */
    @Column("`IS_EXE`")
    @ApiModelProperty("是否执行")
    private Boolean isExe;

    /**
     * 开始日期
     */
    @Column("`BEGIN_DATE`")
    @ApiModelProperty("开始日期")
    private Date beginDate;

    /**
     * 结束日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("结束日期")
    private Date endDate;

    /**
     * 费率大类
     */
    @Column("`CHARGE_TYPE`")
    @ApiModelProperty("费率大类")
    private String chargeType;

    /**
     * 收费模式
     */
    @Column("`CHARGE_MODE`")
    @ApiModelProperty("收费模式")
    private String chargeMode;

    /**
     * 场内场外
     */
    @Column("`CHARGE_EXCH_CD`")
    @ApiModelProperty("场内场外")
    private String chargeExchCd;

    /**
     * 收费场所
     */
    @Column("`CHARGE_P`")
    @ApiModelProperty("收费场所")
    private String chargeP;

    /**
     * 客户类型
     */
    @Column("`CLIENT_TYPE`")
    @ApiModelProperty("客户类型")
    private String clientType;

    /**
     * 最低费率
     */
    @Column("`MIN_CHAR_RATE`")
    @ApiModelProperty("最低费率")
    private BigDecimal minCharRate;

    /**
     * 最高费率
     */
    @Column("`MAX_CHAR_RATE`")
    @ApiModelProperty("最高费率")
    private BigDecimal maxCharRate;

    /**
     * 费率单位
     */
    @Column("`CHARGE_UNIT`")
    @ApiModelProperty("费率单位")
    private String chargeUnit;

    /**
     * 费率描述
     */
    @Column("`CHARGE_DESC`")
    @ApiModelProperty("费率描述")
    private String chargeDesc;

    /**
     * 条件描述
     */
    @Column("`CHAR_CON_DESC`")
    @ApiModelProperty("条件描述")
    private String charConDesc;

    /**
     * 条件类型
     */
    @Column("`CHAR_CON1`")
    @ApiModelProperty("条件类型")
    private String charCon1;

    /**
     * 条件序号
     */
    @Column("`CHAR_CON_NO1`")
    @ApiModelProperty("条件序号")
    private Boolean charConNo1;

    /**
     * 条件单位
     */
    @Column("`CHAR_CON_UNIT1`")
    @ApiModelProperty("条件单位")
    private String charConUnit1;

    /**
     * 开始区间
     */
    @Column("`CHAR_START1`")
    @ApiModelProperty("开始区间")
    private BigDecimal charStart1;

    /**
     * 结束区间
     */
    @Column("`CHAR_END1`")
    @ApiModelProperty("结束区间")
    private BigDecimal charEnd1;

    /**
     * 是否包含开始区间
     */
    @Column("`IS_CHAR_START1`")
    @ApiModelProperty("是否包含开始区间")
    private Boolean isCharStart1;

    /**
     * 是否包含结束区间
     */
    @Column("`IS_CHAR_END1`")
    @ApiModelProperty("是否包含结束区间")
    private Boolean isCharEnd1;

    /**
     * 条件类型2
     */
    @Column("`CHAR_CON2`")
    @ApiModelProperty("条件类型2")
    private String charCon2;

    /**
     * 条件序号2
     */
    @Column("`CHAR_CON_NO2`")
    @ApiModelProperty("条件序号2")
    private Boolean charConNo2;

    /**
     * 条件单位2
     */
    @Column("`CHAR_CON_UNIT2`")
    @ApiModelProperty("条件单位2")
    private String charConUnit2;

    /**
     * 开始区间2
     */
    @Column("`CHAR_START2`")
    @ApiModelProperty("开始区间2")
    private BigDecimal charStart2;

    /**
     * 结束区间2
     */
    @Column("`CHAR_END2`")
    @ApiModelProperty("结束区间2")
    private BigDecimal charEnd2;

    /**
     * 是否包含开始区间2
     */
    @Column("`IS_CHAR_START2`")
    @ApiModelProperty("是否包含开始区间2")
    private Boolean isCharStart2;

    /**
     * 是否包含结束区间2
     */
    @Column("`IS_CHAR_END2`")
    @ApiModelProperty("是否包含结束区间2")
    private Boolean isCharEnd2;

    /**
     * 备注
     */
    @Column("`NOTES`")
    @ApiModelProperty("备注")
    private String notes;

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