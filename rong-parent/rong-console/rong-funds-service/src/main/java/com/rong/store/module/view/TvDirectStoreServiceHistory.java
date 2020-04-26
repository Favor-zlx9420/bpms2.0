package com.rong.store.module.view;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvDirectStoreServiceHistory extends TbDirectStoreServiceHistory {
    @ApiModelProperty("直营店客服/基金经理/机构代理用户名")
    private String customerUserName;
    @ApiModelProperty("被服务用户名")
    private String investorUserName;
    @ApiModelProperty("直营店客服/基金经理/机构代理用户姓名")
    private String customerUserRealName;
    @ApiModelProperty("被服务用户姓名")
    private String investorUserRealName;
    @ApiModelProperty("手机号")
    private String investorUserPhone;
    @ApiModelProperty("用户类型type=（0：普通用户；1：合格投资者；2：金融从业者）")
    private Integer userType;
    @ApiModelProperty("用户类型type=（0：普通用户；1：合格投资者；2：金融从业者）")
    private String userTypeStr;

    private String replyMessage;

    public String getUserTypeStr() {
        return CommonEnumContainer.ReservationUserType.getDesc(this.getUserType());
    }
}