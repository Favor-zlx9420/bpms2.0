package com.rong.store.module.view;

import com.rong.common.util.DateUtil;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TvDirectStoreOrg extends TbDirectStoreOrg {
    private List<TbDirectStoreOrgLabel> orgLabels;
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;
    @ApiModelProperty("机构用户真实姓名")
    private String realName;
    @ApiModelProperty("机构用户名")
    private String userName;
    @ApiModelProperty("机构用户注册时间")
    private Date regDate;
    @ApiModelProperty("驻店客服数量")
    private int customerUserCount;
    @ApiModelProperty("未审核客服数量")
    private int unAuditCustomerUserCount;
    @ApiModelProperty("服务用户人次")
    private int serviceNumCount;
    @ApiModelProperty("服务用户数")
    private int serviceUserCount;
    @ApiModelProperty("用户关注数（收藏数量）")
    private int favUserCount;

    @ApiModelProperty("蓝vip到期日期")
    private Date vipEndDay;

    @ApiModelProperty("蓝vip剩余时间（单位：天）")
    private Integer leftVipDays;

    public Integer getLeftVipDays() {
        try {
            if (null != vipEndDay) {
                leftVipDays = DateUtil.difference(new Date(), vipEndDay);
            }
            return leftVipDays;
        }catch (Exception e){
            return null;
        }
    }
}