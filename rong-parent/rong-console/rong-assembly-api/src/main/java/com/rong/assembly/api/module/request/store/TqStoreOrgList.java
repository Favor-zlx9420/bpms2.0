package com.rong.assembly.api.module.request.store;

import com.rong.common.annotation.IntegerRangeValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TqStoreOrgList extends TqPageListBase {
    @ApiModelProperty(value = "是否推荐",allowableValues = ",true,false")
    private Boolean recommend;
    @ApiModelProperty(value = "类型（0私募，1信托，2公募）",allowableValues = ",0,1,2")
    private Integer type;
    @ApiModelProperty(value = "机构名称模糊搜索")
    private String partyFullName;
    @ApiModelProperty(value = "直营店标签id组合（通过资源接口可以查出直营店标签列表）")
    private List<Long> labelIds;

    @ApiModelProperty("所在地区(110000北京310000上海440100广州440300深圳)")
    private String regCity;
    @ApiModelProperty("管理规模0-1亿,1-10亿,10-20亿,20-50亿,50亿以上(直接传中文)")
    private String scale;
    @ApiModelProperty("排名周期(1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7最近五年8成产以来9今年以来)")
    @IntegerRangeValidator(min = 1,max = 9)
    private Integer searchInterval;
    @ApiModelProperty("投资策略(1股票策略2相对价值3宏观策略4事件驱动5组合基金6债券策略7管理期货8复合策略9其他策略,多个用,号隔开)")
    private String investStrategy;
    @ApiModelProperty("近一年收益起始搜索区间")
    private BigDecimal beginReturnRate1y;
    @ApiModelProperty("近一年收益截止搜索区间")
    private BigDecimal endReturnRate1y;
}
