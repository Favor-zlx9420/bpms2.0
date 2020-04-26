package com.rong.assembly.api.module.response.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.module.TvPageList;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd2s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.view.TvUserFundAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TrUserFundAccountIndex {
    @ApiModelProperty("总参考市值，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal totalMarkValue;
    @ApiModelProperty("总参考本金，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal totalPrincipal;
    @ApiModelProperty("总参考收益=总市值-总本金，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal totalProfit;
    @ApiModelProperty("首页列表")
    TvPageList<TvUserFundAccount> pageList;

    @ApiModelProperty("总参考市值[私募]，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal totalPriMarkValue;
    @ApiModelProperty("总参考市值[公募]，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal totalRaisedMarkValue;

    public BigDecimal getTotalMarkValue() {
        if(null != totalPriMarkValue && null != totalRaisedMarkValue){
            return totalPriMarkValue.add(totalRaisedMarkValue);
        }else if(null != totalRaisedMarkValue){
            return totalRaisedMarkValue;
        }
        return totalPriMarkValue;
    }

    public BigDecimal getTotalProfit() {
        BigDecimal _totalMarkValue = getTotalMarkValue();
        if(null != _totalMarkValue && null != totalPrincipal){
            return _totalMarkValue.subtract(totalPrincipal);
        }
        return totalProfit;
    }
}
