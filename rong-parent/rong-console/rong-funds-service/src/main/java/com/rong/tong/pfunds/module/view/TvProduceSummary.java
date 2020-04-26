package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.DateUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfDate;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.store.module.view.TvDirectStoreUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-16 15:28
 **/
@Data
public class TvProduceSummary {
    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "基金简称")
    private String secShortName;

    @ApiModelProperty(value = "基金全称")
    private String secFullName;

    @ApiModelProperty(value = "产品类型")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String produceType;

    @ApiModelProperty(value = "投资策略")
    private String investStrategy;

    @ApiModelProperty(value = "基金状态")
    private String status;

    @ApiModelProperty(value = "最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "近1年收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRate1y;

    @ApiModelProperty(value = "累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRateEst;

    @ApiModelProperty(value = "累计净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal accumNav;

    @ApiModelProperty(value = "成立以来年化")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String annualTotalReturn;

    @ApiModelProperty(value = "最大回撤")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String maxDrawdown;

    @ApiModelProperty(value = "夏普比率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal sharpeRatio;

    @ApiModelProperty(value = "成立时间")
    @JsonSerialize(using = NeedQualifiedSerializerOfDate.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private Date establishDate;

    @ApiModelProperty(value = "基金公司(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "基金公司(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "基金公司id")
    private Integer partyId;

    @ApiModelProperty(value = "基金经理")
    private List<TvMdPeople> managers;

    @ApiModelProperty(value = "基金经理")
    private String userName;

    @ApiModelProperty(value = "基金经理id")
    private Long personId;

    @ApiModelProperty(value = "备案编号")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String recordCd;

    @ApiModelProperty("驻店客服列表")
    List<TvDirectStoreUser> customerServers;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;
    @ApiModelProperty(value = "是否是直营店产品")
    private Boolean storeUser;

    public Integer getListDays() {
        try {
            return DateUtil.difference(establishDate,new Date());
        }catch (Exception e){
            return null;
        }
    }
}
