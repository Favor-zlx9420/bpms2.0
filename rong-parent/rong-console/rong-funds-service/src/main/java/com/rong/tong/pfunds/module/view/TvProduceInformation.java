package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvProduceInformation {

    @ApiModelProperty(value = "产品名称(全称)")
    private String secFullName;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "投资顾问(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "投资顾问(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "受托人")
    private String issuePlatform;

    @ApiModelProperty(value = "托管人")
    private String custodian;

    @ApiModelProperty(value = "交易经纪")
    private String tradingBroker;

    @ApiModelProperty(value = "成立日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String establishDate;

    @ApiModelProperty(value = "运行状态")
    private String status;

    @ApiModelProperty(value = "产品类型")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String pfStyle;

    @ApiModelProperty(value = "初始规模")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String scaleInitial;

    @ApiModelProperty(value = "投资策略")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String investStrategy;

    @ApiModelProperty(value = "子策略")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String investStrategyChild;

    @ApiModelProperty(value = "是否分级")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String fen;

    @ApiModelProperty(value = "是否伞形")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String san;

    @ApiModelProperty(value = "认购起点")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String subscriptionStartPoint;

    @ApiModelProperty(value = "封闭期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String closingDuraDesc;

    @ApiModelProperty(value = "开放日")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String openDateDesc;

    @ApiModelProperty(value = "认购费率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String issueFee;

    @ApiModelProperty(value = "赎回费率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String redeemFee;

    @ApiModelProperty(value = "管理费率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String managementFee;

    @ApiModelProperty(value = "业绩报酬")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String performaneceReturn;

    @ApiModelProperty(value = "存续期限")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String duration;

    @ApiModelProperty(value = "备案编码")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String recordCd;

    @ApiModelProperty(value = "追加起点")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String minAdd;

    @ApiModelProperty(value = "预警线")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String warnLine;

    @ApiModelProperty(value = "止损线")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String stopLossLine;
}
