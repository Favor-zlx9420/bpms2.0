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
public class TvMaxDrawdownTrend {

    @ApiModelProperty(value = "日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "最大回撤")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String maxDrawdown;
}
