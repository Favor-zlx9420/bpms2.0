package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvPfundInstInfo extends TbPfundInstInfo {
    private Boolean visible = Boolean.TRUE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    private String partyShortName;
    private String partyFullName;
    private String userName;
    private String realName;

    @ApiModelProperty("管理规模")
    private String scale;
    @ApiModelProperty("代表产品id")
    private String repSecurityId;

    @ApiModelProperty("近一年收益")
    private BigDecimal returnOfLatestYear;
    @ApiModelProperty("累计收益")
    private BigDecimal returnAccum;
    @ApiModelProperty("年化收益")
    private BigDecimal returnA;
    @ApiModelProperty("是否会员")
    private Boolean isMember;


}