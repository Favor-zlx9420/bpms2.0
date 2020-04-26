package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqManagerRanking extends TqPageListBase {

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

    @ApiModelProperty(value = "排名周期(1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7成产以来8今年以来)", name = "searchInterval", required = true)
    private String searchInterval;

    @ApiModelProperty(value = "从业年限{1:20年以上,2:15年以上,3:10年以上,4:5年以上,5:5年以下,6:不详}", name = "year")
    private String year;

    @ApiModelProperty(value = "经理背景{1:公募,2:券商,3:媒体,4:海外,5:实业,6:其他金融机构,7:民间,8:保险,9:信托,10:其他,11:学者,12:私募,13:银行,14:期货}", name = "background")
    private String background;

    @ApiModelProperty(hidden = true)
    private Integer limitStart;

    @ApiModelProperty(hidden = true)
    private Integer limitEnd;

    @ApiModelProperty(hidden = true)
    private String orderBy;
}