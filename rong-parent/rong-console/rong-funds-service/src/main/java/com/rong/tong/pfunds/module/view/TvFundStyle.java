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
public class TvFundStyle {

    @ApiModelProperty(value = "统计日期")
    private String endDate;

    @ApiModelProperty(value = "进攻能力")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String upCaptureRate;

    @ApiModelProperty(value = "防守能力")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String downCaptureRate;

}
