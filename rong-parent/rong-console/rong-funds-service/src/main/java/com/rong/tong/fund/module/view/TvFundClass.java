package com.rong.tong.fund.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.StringUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.tong.fund.module.entity.TbFundClass;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvFundClass extends TbFundClass {
    private Boolean visible = Boolean.TRUE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    @ApiModelProperty("基金简称（不含分级）")
    private String secShortName;
    @ApiModelProperty("基金名称（不含分级）")
    private String secFullName;

    @ApiModelProperty("基金简称（含分级）")
    private String secShortNameWc;
    @ApiModelProperty("基金名称（含分级）")
    private String secFullNameWc;
    @ApiModelProperty("最新净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal nav;
    @ApiModelProperty("累计净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal accumNav;
    @ApiModelProperty("累计%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("年化%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnA;

    public String getSecShortNameWc() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            secShortNameWc = this.secShortName + this.getClassName();
        }else{
            secShortNameWc = secShortName;
        }
        return secShortNameWc;
    }

    public String getSecFullNameWc() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            secFullNameWc = this.secFullName + this.getClassName();
        }else{
            secFullNameWc = secFullName;
        }
        return secFullNameWc;
    }
}