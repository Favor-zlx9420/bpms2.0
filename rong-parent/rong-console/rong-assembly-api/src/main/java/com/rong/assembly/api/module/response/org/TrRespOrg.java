package com.rong.assembly.api.module.response.org;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.assembly.api.module.response.TrRespUser;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.store.TrStoreDesign;
import com.rong.assembly.api.module.response.store.TrStoreInfo;
import com.rong.common.util.DateUtil;
import com.rong.common.util.NumberUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.store.module.view.TvDirectStoreUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class TrRespOrg implements Serializable {
    @ApiModelProperty("机构id")
    private Long partyId;
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;

    @ApiModelProperty("基金管理机构公示编码")
    private String instId;

    @ApiModelProperty("核心人物")
    private String keyPerson;
    @ApiModelProperty("基本信息,介绍")
    private String profile;
    @ApiModelProperty("办公地址")
    private String officeAddr;
    @ApiModelProperty("注册城市")
    private String regCity;
    @ApiModelProperty("备案")
    private String regCd;
    @ApiModelProperty("登记日期/成立日期")
    @JsonFormat(pattern = DateUtil.JSONINPUT_yyyy_MM_dd_EN, timezone = "GMT+8")
    private Date recordDate;
    @ApiModelProperty("登记日期/成立日期")
    @JsonFormat(pattern = DateUtil.JSONINPUT_yyyy_MM_dd_EN, timezone = "GMT+8")
    private Date regDate;

    @ApiModelProperty("投资理念")
    private String ideaStrategy;
    @ApiModelProperty("投资策略，只有代表产品才有，没有的话取投资理念")
    private String investStrategy;

    @ApiModelProperty("注册资金,单位：万")
    private BigDecimal regCap;
    @ApiModelProperty("实缴资金,单位：万")
    private BigDecimal actCap;

    @ApiModelProperty("代表产品id")
    private String repSecurityId;
    @ApiModelProperty("代表产品全称")
    private String repSecurityShortName;
    @ApiModelProperty("代表产品全称")
    private String repSecurityFullName;
    @ApiModelProperty("代表产品近一年收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal repSecReturnOfLatestYear;
    @ApiModelProperty("代表产品成立以来收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal repSecReturnOfEstablish;
    @ApiModelProperty(value = "产品数")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private Integer numAll;


    //以下需要关联
    @ApiModelProperty("管理规模，可排序")
    private String scale;
    @ApiModelProperty("机构投资类型")
    private String mainFundType;
    @ApiModelProperty("机构类型(0:私募，2：公募)")
    private Integer orgType;

    @ApiModelProperty("近一年收益%，可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnOfLatestYear;
    @ApiModelProperty("近一年风险%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal riskOfLatestYear;
    @ApiModelProperty("累计收益，可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("年化收益，可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnA;

    @ApiModelProperty("机构代理列表")
    List<TrRespUser> proxys;

    @ApiModelProperty("驻店客服列表")
    List<TvDirectStoreUser> customerServers;
    @ApiModelProperty("装潢信息列表")
    List<TrStoreDesign> storeDesigns;
    @ApiModelProperty("旗下基金经理列表")
    List<TrManager> managers;

    @ApiModelProperty("直营店对象")
    TrStoreInfo storeInfo;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;
    @ApiModelProperty(value = "是否是直营机构")
    private boolean storeUser;

    @ApiModelProperty("近一年收益评级")
    private String incomeRating;
    @ApiModelProperty("近一年收益评级")
    private String riskRating;

    public Date getRecordDate() {
        if(null != regDate){
            return regDate;
        }
        return recordDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public BigDecimal getRegCap() {
        if(null != regCap){
            if(null != regCap){
                return regCap.divide(new BigDecimal("10000"),2, RoundingMode.HALF_UP);
            }
        }
        return regCap;
    }

    public BigDecimal getActCap() {
        return actCap;
    }

    public String getIncomeRating() {
        if(null != returnOfLatestYear){
            double _r = returnOfLatestYear.doubleValue();
            if(_r > 50){
                return "优";
            }else if(_r > 25){
                return "良";
            }else if(_r > 0){
                return "中";
            }else{
                return "一般";
            }
        }
        return incomeRating;
    }

    public String getRiskRating() {
        if(null != riskOfLatestYear){
            double _r = riskOfLatestYear.doubleValue();
            if(_r > 40){
                return "优";
            }else if(_r > 30){
                return "良";
            }else if(_r > 15){
                return "中";
            }else if(_r > 0){
                return "一般";
            }
        }
        return riskRating;
    }
    private Double scaleD;

    public String getScale() {
        if(null != scaleD){
            return NumberUtil.respScale2MDownC(scaleD);
        }
        return scale;
    }
}
