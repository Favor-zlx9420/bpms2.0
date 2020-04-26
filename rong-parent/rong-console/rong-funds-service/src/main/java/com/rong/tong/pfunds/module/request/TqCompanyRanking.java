package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqCompanyRanking extends TqPageListBase {

    @ApiModelProperty(value = "搜索关键字", name = "key")
    private String key;

    @ApiModelProperty(value = "所在地区(110000北京310000上海440100广州440300深圳)", name = "regCity")
    private String regCity;

    @ApiModelProperty(value = "所在城市（中文）", name = "regCityStr")
    private String regCityStr;

    @ApiModelProperty(hidden = true)
    private String regProvince;

    @ApiModelProperty(value = "所在省份（中文）", name = "regProvinceStr")
    private String regProvinceStr;

    @ApiModelProperty(value = "收益周期(1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7成产以来8今年以来)", name = "searchInterval", required = true)
    private String searchInterval;

    @ApiModelProperty(value = "0-1亿,1-10亿,10-20亿,20-50亿,50亿以上(直接传中文)", name = "scale")
    private String scale;

    @ApiModelProperty(hidden = true)
    private Integer limitStart;

    @ApiModelProperty(hidden = true)
    private Integer limitEnd;

    @ApiModelProperty(hidden = true)
    private String orderBy;
}