package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbPfundManager;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvPfundManager extends TbPfundManager {
    private Boolean visible = Boolean.TRUE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    private String userName;
    private String partyShortName;
    private String manageName;

    private String secShortName;
    private String secFullName;

    @ApiModelProperty("累计净值")
    private BigDecimal accumNav;
    @ApiModelProperty("最新净值")
    private BigDecimal nav;
    @ApiModelProperty("年化收益")
    private BigDecimal returnA;
    @ApiModelProperty("累计年化收益")
    private BigDecimal returnAccum;
    @ApiModelProperty("近一年收益")
    private BigDecimal returnOfLatestYear;

    @ApiModelProperty("是否代表作")
    private Boolean isResp;
}