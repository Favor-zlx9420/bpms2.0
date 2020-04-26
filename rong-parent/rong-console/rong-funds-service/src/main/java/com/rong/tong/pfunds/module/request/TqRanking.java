package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqPageListBase;
import com.rong.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TqRanking extends TqPageListBase {

    @ApiModelProperty(value = "搜索关键字", name = "key")
    private String key;

    @ApiModelProperty(value = "投资策略(1股票策略2相对价值3宏观策略4事件驱动5组合基金6债券策略7管理期货8复合策略9其他策略,多个用,号隔开)", name = "investStrategy")
    private String investStrategy;

    @ApiModelProperty(value = "年化收益(1:50%以上,2:40%～50%,3:30%~40%,4:20%~30%,5:10%~20%)", name = "returnOfEstablish")
    private String returnOfEstablish;

    @ApiModelProperty(value = "产品类型(1私募证券投资基金2有限合伙3信托4券商集合理财5期货资管6海外基金7公募专户及子公司8单账户9其他10保险公司及其子公司的资产管理计划14私募创业投资基金15私募股权投资基金16私募资产配置基金,多个用,号隔开)", name = "pfStyle")
    private String pfStyle;

    @ApiModelProperty(value = "是否分级(0不分级1分级)", name = "valueNumCd")
    private String valueNumCd;

    @ApiModelProperty(value = "所在地区(110000北京310000上海440100广州440300深圳)", name = "regCity")
    private String regCity;

    @ApiModelProperty(value = "所在城市（中文）", name = "regCityStr")
    private String regCityStr;

    @ApiModelProperty(hidden = true)
    private String regProvince;

    @ApiModelProperty(value = "所在省份（中文）", name = "regProvinceStr")
    private String regProvinceStr;

    @ApiModelProperty(value = "成立时间", name = "establishDate")
    private String establishDate;

    @ApiModelProperty(value = "成立时间开始年份", name = "establishDateStart")
    private String establishDateStart;

    @ApiModelProperty(value = "成立时间结束年份", name = "establishDateEnd")
    private String establishDateEnd;

    @ApiModelProperty(value = "是否伞形(0非伞形1伞形)", name = "san")
    private String san;

    @ApiModelProperty(value = "搜索类型pc端(0区间收益1年度收益)", name = "searchType")
    private String searchType;

    @ApiModelProperty(value = "收益区间(1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7最近五年8成立以来9今年以来)", name = "searchInterval")
    private String searchInterval;

    @ApiModelProperty(value = "年度收益(1前一年2前二年3前三年4前四年5前五年6前六年7前七年8成立以来9今年以来)", name = "searchYear")
    private String searchYear;

    @ApiModelProperty(hidden = true)
    private Integer limitStart;

    @ApiModelProperty(hidden = true)
    private Integer limitEnd;

    @ApiModelProperty(hidden = true)
    private String orderBy;

    @ApiModelProperty(hidden = true)
    private Date endDate;
}