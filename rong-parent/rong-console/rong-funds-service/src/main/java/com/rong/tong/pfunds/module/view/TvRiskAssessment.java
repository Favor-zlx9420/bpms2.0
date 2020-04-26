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
public class TvRiskAssessment {

    @ApiModelProperty(value = "统计区间")
    private String window;

    @ApiModelProperty(value = "日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "年化收益率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String annualTotalReturn;

    @ApiModelProperty(value = "年化波动")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String annualTotalRisk;

    @ApiModelProperty(value = "最大回撤")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String maxDrawdown;

    @ApiModelProperty(value = "下行风险")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String downsideRisk;

    @ApiModelProperty(value = "夏普比率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String sharpeRatio;

    @ApiModelProperty(value = "詹森指数")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String jensensAlpha;

    @ApiModelProperty(value = "索提诺比率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String sortinoRatio;

    @ApiModelProperty(value = "进攻能力")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String upCaptureRate;

    @ApiModelProperty(value = "防守能力")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String downCaptureRate;

}
